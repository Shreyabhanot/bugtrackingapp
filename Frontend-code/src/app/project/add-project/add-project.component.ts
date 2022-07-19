import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ProjectService } from '../project.service';

@Component({
  selector: 'app-add-project',
  templateUrl: './add-project.component.html',
  styleUrls: ['./add-project.component.css']
})
export class AddProjectComponent implements OnInit {

  addForm: FormGroup;
  submitted: boolean = false;

  constructor(private formBuilder: FormBuilder, private router: Router, 
    private projectSerivce: ProjectService) { }

  ngOnInit(): void {
    this.addForm = this.formBuilder.group({
      id:[],
      projectOwner: ['', Validators.required],
      status: ['',Validators.required]
    });

  }
  onSubmit(){
    this.submitted = true;
    if(this.addForm.invalid){
      return;
    }
    this.projectSerivce.addProject(this.addForm.value).subscribe(data => {
      this.router.navigate(['list-project']);
      });

}}
