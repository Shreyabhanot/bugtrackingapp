import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CreateUserComponent } from './create-user/create-user.component';
import { LoginComponent } from './login/login.component';
import { HomeAdminComponent } from './home-admin/home-admin.component';
import { HomeEmployeeComponent } from './home-employee/home-employee.component';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';



@NgModule({
  declarations: [CreateUserComponent, LoginComponent, HomeAdminComponent, HomeEmployeeComponent],
  imports: [
    CommonModule, ReactiveFormsModule,HttpClientModule
  ]
})
export class UserModule { }
