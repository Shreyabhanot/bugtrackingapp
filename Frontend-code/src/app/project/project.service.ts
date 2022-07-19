import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Project } from '../model/project';


@Injectable({
  providedIn: 'root'
})
export class ProjectService {


  constructor(private http: HttpClient) { }
  // baseUrl:string ='http://localhost:3000/projects';
  //baseUrl:string ='http://localhost:4200/add-project';
  // for Java service
  baseUrl: string = 'http://localhost:9090/bug-api/project';

  getAllProjects() {
    return this.http.get<Project[]>(this.baseUrl + '/');
  }
  addProject(project: Project) {
    return this.http.post(this.baseUrl + '/', project);
  }

  getProjectById(id: number) {
    console.log('Testing' + id);
    return this.http.get<Project>(this.baseUrl + '/' + id);
  }
  // Modify User
  updateProject(project: Project) {
    console.log(project);
    return this.http.put(this.baseUrl, project);

  }
  // Delete User
  deleteProject(id: number) {
    return this.http.delete(this.baseUrl + '/' + id);
  }

  getProjectSearchByStatusOrId(status: string) {
    return this.http.get<Project[]>(this.baseUrl + '/bystatusOrId/' + status);
  }



}