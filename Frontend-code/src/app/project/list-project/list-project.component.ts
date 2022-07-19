import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ProjectService } from '../project.service';
import { Project } from 'src/app/model/project';

@Component({
  selector: 'app-list-project',
  templateUrl: './list-project.component.html',
  styleUrls: ['./list-project.component.css']
})
export class ListProjectComponent implements OnInit {

  projects: Project[];
  projectForm: FormGroup;
  displayForm = false;
  SearchForm: FormGroup;


  constructor(private formBuilder: FormBuilder, private router: Router, private projectSerivce: ProjectService) { }


  ngOnInit() {

    this.projectForm = new FormGroup({
      projectOwner: new FormControl(),
      status: new FormControl()
    });

    this.SearchForm = this.formBuilder.group({
      id: ['', Validators.required],
      status: ['', Validators.required]
    });
    this.projectSerivce.getAllProjects().subscribe(data => {
      this.projects = data;
      console.log(this.projects);
    })
  }

  // Modify Project
  editProject(project: Project): void {
    localStorage.removeItem("editProjectId");

    localStorage.setItem("editProjectId", project.projectId.toString());

    this.router.navigate(['edit-project']);
  };


  // Delete Project
  deleteProject(project: Project): void {
    let result = confirm('Do you want to delete the project?')
    if (result) {
      this.projectSerivce.deleteProject(project.projectId)
        .subscribe(data => {
          this.projects = this.projects.filter(p => p !== project);
        });
    }
  };
  //Add Project
  addProject(): void {
    this.router.navigate(['add-project']);
  };

  //Search status or Id
  searchstatus(){
    var formdata = this.SearchForm.value;
   if(formdata.status!=''){
     this.projectSerivce.getProjectSearchByStatusOrId(formdata.status).subscribe(data=>{
       this.projects = data;
       
     })
   }
  }

  userRole(): boolean {
    if (localStorage.getItem('role').match('admin')) {
      return true;
    }
    else {
      return false;
    }
  }
}