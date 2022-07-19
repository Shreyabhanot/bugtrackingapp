import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BugModule } from './bug/bug.module';
import { BugService } from './bug/bug.service';
import { EmployeeModule } from './employee/employee.module';
import { ProjectModule } from './project/project.module';
import { UserModule } from './user/user.module';
import { CommonModule, DatePipe } from '@angular/common';
import { EmployeeService } from './employee/employee.service';
import { ProjectService } from './project/project.service';
import { UserService } from './user/user.service';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BugModule,
    EmployeeModule,
    ProjectModule,
    UserModule
  ],
  exports: [
    CommonModule
  ],
  providers: [BugService, EmployeeService, ProjectService, UserService, DatePipe],
  bootstrap: [AppComponent]
})
export class AppModule { }
