import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AddEmployeeComponent } from './add-employee/add-employee.component';
import { EditEmployeeComponent } from './edit-employee/edit-employee.component';
import { ListEmployeeComponent } from './list-employee/list-employee.component';
import { ReactiveFormsModule } from '@angular/forms';



@NgModule({
  declarations: [AddEmployeeComponent, EditEmployeeComponent, ListEmployeeComponent],
  imports: [
    CommonModule, ReactiveFormsModule
  ]
})
export class EmployeeModule { }
