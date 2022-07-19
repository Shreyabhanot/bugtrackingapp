import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ProjectService } from '../project.service';
import { first } from 'rxjs/operators';
import { Project } from 'src/app/model/project';

@Component({
  selector: 'app-edit-project',
  templateUrl: './edit-project.component.html',
  styleUrls: ['./edit-project.component.css']
})
export class EditProjectComponent implements OnInit {

  project: Project;
  editForm: FormGroup;
  submitted: boolean = false;



  constructor(private formBuilder: FormBuilder, private router: Router, private activatedRoute: ActivatedRoute,
    private projectService: ProjectService) { }
  ngOnInit() {


    if (localStorage.getItem("editProjectId") != null) {
      let projectId = localStorage.getItem("editProjectId");
      if (!projectId) {
        alert("Invalid action.")
        return;
      }


      this.editForm = this.formBuilder.group({
        projectId: [],
        projectOwner: ['', Validators.required],
        status: ['', Validators.required]
      });

      this.projectService.getProjectById(+projectId)
        .subscribe(data => {
          this.editForm.setValue(data);
        });
    } else {
      this.router.navigate(['list-project']);
      return;
    }
  }
  onSubmit() {

    this.submitted = true;
    if (this.editForm.invalid) {
      //  alert('invalid editform');
      return;
    }



    this.projectService.updateProject(this.editForm.value)
      .pipe(first())
      .subscribe(
        data => {
          this.router.navigate(['list-project']);
        },
        error => {
          alert('error: ' + error.url);
        });
  }

}
