import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddBugComponent } from './bug/add-bug/add-bug.component';
import { BugDetailsComponent } from './bug/bug-details/bug-details.component';
import { EditBugComponent } from './bug/edit-bug/edit-bug.component';
import { ListBugComponent } from './bug/list-bug/list-bug.component';
import { AddEmployeeComponent } from './employee/add-employee/add-employee.component';
import { EditEmployeeComponent } from './employee/edit-employee/edit-employee.component';
import { ListEmployeeComponent } from './employee/list-employee/list-employee.component';
import { AddProjectComponent } from './project/add-project/add-project.component';
import { EditProjectComponent } from './project/edit-project/edit-project.component';
import { ListProjectComponent } from './project/list-project/list-project.component';
import { CreateUserComponent } from './user/create-user/create-user.component';
import { HomeAdminComponent } from './user/home-admin/home-admin.component';
import { LoginComponent } from './user/login/login.component';

const routes: Routes = [
  {path: '', component: LoginComponent},
  {path: 'home-admin', component: HomeAdminComponent},
  {path: 'create-user', component: CreateUserComponent},
  {path: 'login-page', component: LoginComponent},
  {path: 'edit-project', component: EditProjectComponent},
  {path: 'edit-employee', component: EditEmployeeComponent},
  {path: 'add-employee', component: AddEmployeeComponent},
  {path: 'list-employee', component: ListEmployeeComponent},
  {path: 'list-bug', component: ListBugComponent},
  {path: 'add-bug', component: AddBugComponent},
  { path: 'add-project', component: AddProjectComponent},
  { path: 'list-project', component: ListProjectComponent },
  { path: 'bug-details', component: BugDetailsComponent },
  {path : 'edit-bug', component: EditBugComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
