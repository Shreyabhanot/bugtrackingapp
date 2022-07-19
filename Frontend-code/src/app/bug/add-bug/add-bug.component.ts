import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, FormsModule, Validators} from '@angular/forms';
import { BugService } from '../bug.service';
import { Bug } from '../../model/bug';
import { Router } from '@angular/router';
import {ReactiveFormsModule} from '@angular/forms';
import { Employee } from 'src/app/model/employee';

@Component({
  selector: 'add-bug',
  templateUrl: './add-bug.component.html',
  styleUrls: ['./add-bug.component.css']
})
export class AddBugComponent implements OnInit {

  bugs: Bug[] = new Array();
 
  addBug: FormGroup = new FormGroup({});
 


  constructor(private formBuilder: FormBuilder, private router: Router, private bugService: BugService) { }

  ngOnInit(): void {
    this.addBug = this.formBuilder.group({

      title: ['', Validators.required],
      bugDesc: [''],
      type: ['Syntactic Error', Validators.required],
      status: ['Open', Validators.required],
      startDate: ['', Validators.required],
      endDate: [''],
      priority: ['', Validators.required],
      project: this.formBuilder.group({
        projectId: ['', Validators.required]
      }),
      employee: this.formBuilder.group({
        employeeId: ['', Validators.required]
      })
      

    });
  }

  createBug() {
    alert()
    this.bugService.createBug(this.addBug.value).subscribe(data =>{
      alert(JSON.stringify(data));
      this.router.navigate(['list-bug']);
      
    }
      )
  }
}


