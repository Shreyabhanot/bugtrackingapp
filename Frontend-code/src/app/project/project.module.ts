import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AddProjectComponent } from './add-project/add-project.component';
import { EditProjectComponent } from './edit-project/edit-project.component';
import { ListProjectComponent } from './list-project/list-project.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';



@NgModule({
  declarations: [AddProjectComponent, EditProjectComponent, ListProjectComponent],
  imports: [
    CommonModule,FormsModule,
    ReactiveFormsModule, HttpClientModule
  ]
})
export class ProjectModule { }
