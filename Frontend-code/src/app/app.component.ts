import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'bug-tracking';

  constructor(private router: Router){}

  onLogOut(){
    localStorage.removeItem('userId');
    localStorage.removeItem('role');
    this.router.navigate(['login-page']);
    
  }
  projectDetails(): void {
    this.router.navigate(['list-project']);
  }

  employeeDetails() : void{
    this.router.navigate(['list-employee']);
  }

  bugDetails() : void{
    this.router.navigate(['list-bug']);
  }

  home(): void{
    this.router.navigate(['home-admin']);
  }

  loggedIn(){
    return localStorage.getItem('userId');
  }

  userRole(): boolean{
    if(localStorage.getItem('role').match('admin')){
      return true;
    }
    else{
      return false;
    }
  }

}
