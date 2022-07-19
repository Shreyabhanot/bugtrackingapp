import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Employee } from 'src/app/model/employee';
import { EmployeeService } from '../employee.service';

@Component({
  selector: 'app-list-employee',
  templateUrl: './list-employee.component.html',
  styleUrls: ['./list-employee.component.css']
})
export class ListEmployeeComponent implements OnInit {
  employees: Employee[];
  employeeForm: FormGroup;
  SearchForm:FormGroup;
  

  constructor(private formBuilder: FormBuilder, private router: Router, private employeeService: EmployeeService) { }

  ngOnInit() {
    this.employeeForm = new FormGroup({
      empName: new FormControl(),
      email: new FormControl()
    });


     this.SearchForm = this.formBuilder.group({
       id:['',Validators.required],
      empName: ['',Validators.required]
    });
    this.employeeService.getEmployees().subscribe(data => {
      this.employees = data;
      console.log(this.employees);
    });
  }
  createEmployee(): void {
    this.router.navigate(['add-employee']);
  }
  deleteEmployee(employee: Employee): void {
    let result = confirm('Do you want to delete the Employee?')
    if (result) {
      this.employeeService.deleteEmployee(employee.employeeId)
        .subscribe(data => {
          this.employees = this.employees.filter(e => e !== employee);
        });
    }
  };
  editEmployee(employee: Employee): void {
    localStorage.removeItem("editEmployeeId");
    localStorage.setItem("editEmployeeId", employee.employeeId.toString());
    this.router.navigate(['edit-employee']);
  };

  userRole(): boolean{
    if(localStorage.getItem('role').match('admin')){
      return true;
    }
    else{
      return false;
    }
  }

  //Search name or Id
  searchempName(){
    var formdata = this.SearchForm.value;
   if(formdata.empName!=''){
     this.employeeService.getEmployeeSearchByNameOrId(formdata.empName).subscribe(data=>{
       this.employees = data;
       
     })
   }
  }

   
    
}