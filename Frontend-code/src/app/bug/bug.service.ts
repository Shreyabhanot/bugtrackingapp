import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Bug } from '../model/bug';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class BugService {

  bug: Bug;

  baseURL: string= "http://localhost:9090/bug-api/bugs";
  constructor(private http:HttpClient) {  }

   getBugs(){
    return this.http.get<Bug[]>(this.baseURL);
   }

  createBug(bug: Bug): Observable<object>{
    return this.http.post<Bug>(this.baseURL, bug);
    
  }

  deleteBug(bugId: number): Observable<object>{
    return this.http.delete<Bug>(this.baseURL+'/'+bugId);
  }

  public setBug(bug: Bug){
    this.bug =bug;
  }

  public getBug(){
    return this.bug;
  }

  public updateBug(bug): Observable<object>{
    return this.http.put<Bug>(this.baseURL, bug);
  }

  searchByStatus(status: string){
    console.log(status);
    return this.http.get<Bug[]>(this.baseURL+'/byStatus/'+status);
  }

  searchByPId(id: number){
    return this.http.get<Bug[]>(this.baseURL+'/byProjectId/'+id);
  }

  searchByEId(id: number){
    return this.http.get<Bug[]>(this.baseURL+'/byEmployeeId/'+id);
  }

}
