import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { EmployeeService } from '../employee.service';

@Component({
  selector: 'app-add-employee',
  templateUrl: './add-employee.component.html',
  styleUrls: ['./add-employee.component.css']
})
export class AddEmployeeComponent implements OnInit {

  addForm: FormGroup;
  submitted: boolean = false;

  constructor(private formBuilder: FormBuilder, private router: Router, private employeeService: EmployeeService) { }

  ngOnInit(): void {
    this.addForm = this.formBuilder.group({
      employeeId: [],
      empName: ['', Validators.required],
      email: ['', Validators.required],
      contactNo: ['', Validators.required]
    });
  }
  onSubmit() {
    this.submitted = true;
    if (this.addForm.invalid) {
      return;
    }
    this.employeeService.createEmployee(this.addForm.value).subscribe(data => {
      this.router.navigate(['list-employee']);
    });

  }
}
