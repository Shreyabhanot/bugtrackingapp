import { ThrowStmt } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { Bug } from 'src/app/model/bug';
import { BugService } from '../bug.service';


@Component({
  selector: 'list-bug',
  templateUrl: './list-bug.component.html',
  styleUrls: ['./list-bug.component.css']
})
export class ListBugComponent implements OnInit {

  bugs: Bug[];


  isByStatus: boolean = false;
  isByPId: boolean = false;
  isByEId: boolean = false;

  filterByStatus: FormGroup;
  filterByPId: FormGroup;
  filterByEId: FormGroup;

  constructor(private bugService: BugService, private router: Router, private formBuilder: FormBuilder) { }

  ngOnInit(): void {


    this.bugService.getBugs().subscribe(data => {
      this.bugs = data;
    });

    this.filterByStatus = new FormGroup({
      status: new FormControl()
    });

    this.filterByPId = new FormGroup({
      pid: new FormControl()
    });

    this.filterByEId = new FormGroup({
      eid: new FormControl()
    });

  }



  isFilter(res) {
    if (res === 'byStatus') {
      this.isByStatus = true;
      this.isByPId = false;
      this.isByEId = false;
    }
    else if (res === 'byPId') {
      this.isByPId = true;
      this.isByStatus = false;
      this.isByEId = false;
    }
    else if (res === 'byEId') {
      this.isByEId = true;
      this.isByStatus = false;
      this.isByPId = false;
    }
  }


  createBug(): void {
    this.router.navigate(['add-bug']);
  }



  onSelectBug(bug: Bug) {

    this.bugService.setBug(bug);
    this.router.navigate(['/bug-details']);
  }

  deleteBug(bugId: number) {
    let result = confirm('Do you want to delete the bug details?')
    if (result) {
    this.bugService.deleteBug(bugId).subscribe(data => {
      this.bugService.getBugs().subscribe(data => {
        this.bugs = data;
      })
    });
  }
  }

  userRole(): boolean {
    if (localStorage.getItem('role').match('admin')) {
      return true;
    }
    else {
      return false;
    }
  }

  searchByStatus() {
    var formData = this.filterByStatus.value;
    this.bugService.searchByStatus(formData.status).subscribe(data => {
      this.bugs = data;
    })

  }
  searchByPId() {
    var formData = this.filterByPId.value;
    this.bugService.searchByPId(formData.pid).subscribe(data => {
      this.bugs = data;
    })
  }
  searchByEId() {
    var formData = this.filterByEId.value;
    this.bugService.searchByEId(formData.eid).subscribe(data => {
      this.bugs = data;
    })
  }
}