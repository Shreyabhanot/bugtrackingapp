import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { Bug } from 'src/app/model/bug';
import { BugService } from '../bug.service';
import { DatePipe } from '@angular/common'

@Component({
  selector: 'app-edit-bug',
  templateUrl: './edit-bug.component.html',
  styleUrls: ['./edit-bug.component.css']
})
export class EditBugComponent implements OnInit {

  bug: Bug;
  editBug: FormGroup = new FormGroup({});
  

  constructor(private formBuilder: FormBuilder, private bugService: BugService, private router: Router,  public datepipe: DatePipe) { }

  ngOnInit(): void {
    this.bug = this.bugService.getBug();
    
    let st_dt = this.datepipe.transform(this.bug.startDate, 'yyyy-MM-dd');
    let end_dt = this.datepipe.transform(this.bug.endDate, 'yyyy-MM-dd');

    this.editBug = this.formBuilder.group({

      bugId: [this.bug.bugId],
      title: [this.bug.title],
      bugDesc: [this.bug.bugDesc],
      type: [this.bug.type],
      status: [this.bug.status],
      startDate: [st_dt],
      endDate: [end_dt],
      priority: [this.bug.priority],
      project: this.formBuilder.group({
        projectId: [this.bug.project.projectId]
      }),
      employee: this.formBuilder.group({
        employeeId: [this.bug.employee.employeeId]
      })


    });
  }

  updateBug(){
    this.bugService.updateBug(this.editBug.value).subscribe( data =>{
      this.router.navigate(['list-bug'])
      
    });
  }
  userRole(): boolean {
    if (localStorage.getItem('role').match('admin')) {
      return true;
    }
    else {
      return false;
    }
  }

}
