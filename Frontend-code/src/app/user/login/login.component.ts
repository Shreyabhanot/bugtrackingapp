import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from 'src/app/model/user';
import { UserService } from '../user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;
  invalidLogin: boolean = false;


  constructor(private formBuilder: FormBuilder, private router: Router, private userService: UserService) { }

  ngOnInit(): void {
    this.loginForm = this.formBuilder.group({
      userId: ['', Validators.required],
      password: ['', Validators.required],
      role: ['', Validators.required]
    });

  }


  onSubmit(loginForm: FormGroup) {

    if (this.loginForm.invalid) {
      return;
    }

    this.userService.loginUser(this.loginForm.value).subscribe(data => {
      localStorage.setItem("userId",this.loginForm.controls.userId.value);
      localStorage.setItem("role",this.loginForm.controls.role.value);
      if(this.userRole()){
        this.router.navigate(['home-admin']);
      }
      else{
      this.router.navigate(['/list-bug']);
      }
    })
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


