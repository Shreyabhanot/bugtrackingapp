import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Bug } from 'src/app/model/bug';
import { BugService } from '../bug.service';

@Component({
  selector: 'app-bug-details',
  templateUrl: './bug-details.component.html',
  styleUrls: ['./bug-details.component.css']
})
export class BugDetailsComponent implements OnInit {

  bug: Bug;

  constructor(private bugService: BugService, private router: Router) { }

  ngOnInit(): void {

    this.bug =this.bugService.getBug();
    
  }

  editBug(bug: Bug){
    this.bugService.setBug(bug);
    this.router.navigate(['edit-bug']);
  }

}
