import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../model/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  user: User;
  baseURL: string= "http://localhost:9090/bug-api/users";

  constructor(private http:HttpClient) { }

  loginUser(user: User): Observable<object>{
    this.user= user;
    return this.http.post(this.baseURL+'/login', user);
  }

  createUser(user: User): Observable<object>{
    return this.http.post(this.baseURL, user);
  }



  


}
