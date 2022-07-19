import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { Employee } from '../model/employee';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {
  baseURL: string = "http://localhost:9090/bug-api/employee";

  constructor(private http: HttpClient) { }
  getEmployees() {
    return this.http.get<Employee[]>(this.baseURL);
  }

  createEmployee(employee: Employee) {
    return this.http.post(this.baseURL, employee);

  }
  deleteEmployee(employeeId: number) {
    return this.http.delete(this.baseURL + '/' + employeeId);
  }
  getEmployeeById(employeeId: number) {
    console.log('Testing' + employeeId);
    return this.http.get<Employee>(this.baseURL + '/' + employeeId);
  }

  updateEmployee(employee: Employee) {
    console.log(employee);
    return this.http.put(this.baseURL, employee);
  }

  getEmployeeSearchByNameOrId(empName: string) {
    return this.http.get<Employee[]>(this.baseURL + '/bynameOrId/' + empName);
  }
}
