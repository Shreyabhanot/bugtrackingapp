import { NgModule } from '@angular/core';
import { CommonModule, DatePipe } from '@angular/common';
import { AddBugComponent } from './add-bug/add-bug.component';
import { ListBugComponent } from './list-bug/list-bug.component';
import { EditBugComponent } from './edit-bug/edit-bug.component';
import { FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { BugDetailsComponent } from './bug-details/bug-details.component';



@NgModule({
  declarations: [AddBugComponent, ListBugComponent, EditBugComponent, BugDetailsComponent],
  imports: [
    CommonModule, ReactiveFormsModule, HttpClientModule, FormsModule
  ]
})
export class BugModule { }
