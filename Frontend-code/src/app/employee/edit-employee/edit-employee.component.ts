import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Employee } from 'src/app/model/employee';
import { EmployeeService } from '../employee.service';
import {first} from 'rxjs/operators';

@Component({
  selector: 'app-edit-employee',
  templateUrl: './edit-employee.component.html',
  styleUrls: ['./edit-employee.component.css']
})
export class EditEmployeeComponent implements OnInit {
employee: Employee;
editForm:FormGroup;
submitted: boolean=false;
  constructor(private formBuilder: FormBuilder,private router: Router, 
    private employeeService: EmployeeService) { }

  ngOnInit() {
    if(localStorage.getItem("editEmployeeId")!=null){
      let employeeId = localStorage.getItem("editEmployeeId");
      if(!employeeId) {
        alert("Invalid action.")
        return;
      }

       
      this.editForm = this.formBuilder.group({
        employeeId: [],
        empName: ['', Validators.pattern],
        email: ['', Validators.required],
        contactNo: ['',Validators.minLength]
      }); 
      
    this.employeeService.getEmployeeById(+employeeId)
    .subscribe( data => {
      this.editForm.setValue(data);
    });
    } else {
      this.router.navigate(['list-employee']);
      return;
    }
  }
  onSubmit(){

    this.submitted = true;
    if(this.editForm.invalid){
    //  alert('invalid editform');
      return;
    }
   
   

    this.employeeService.updateEmployee(this.editForm.value)
    .pipe(first())
    .subscribe(
      data => {
        this.router.navigate(['list-employee']);
      },
      error => {
        alert('error: '+error.url);
      });
  }

   

}
