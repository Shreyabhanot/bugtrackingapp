export class Project{
    projectId:number;
    projectOwner:string;
    status:string;

    constructor(id: number, owner: string, status: string) {
        this.projectId = id;
        this.projectOwner= owner;
        this.status = status;
    }
}