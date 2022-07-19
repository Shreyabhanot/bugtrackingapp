import {Project} from "./project";
import {Employee} from "./employee";

export class Bug{
    bugId: number;
    title: string;
    bugDesc: string;
    status: string;
    startDate: Date;
    endDate: Date;
    type: string;
    priority: string;
    project: Project;
    employee: Employee;

    constructor(bugId: number, title: string, description: string, status: string, startDate: Date, endDate: Date, type: string,priority: string, project: Project, employee: Employee){
        this.bugId= bugId;
        this.title= title;
        this.bugDesc= description;
        this.status= status;
        this.startDate= startDate;
        this.endDate= endDate;
        this.type= type;
        this.priority= priority;
        this.project = project;
        this.employee= employee;
    }
}