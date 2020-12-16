import { AuthenticationService } from './../../../services/authentication.service';
import { NgForm } from '@angular/forms';
import { Location } from '@angular/common';
import { TaskService } from './../../../services/task.service';
import { Task } from './../../../models/task';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-emp-update-task',
  templateUrl: './emp-update-task.component.html',
  styleUrls: ['./emp-update-task.component.css']
})
export class EmpUpdateTaskComponent implements OnInit {

  task : Task = new Task(0,"","","","","","",0,"","",0,"","");
  myImage: string = "assets/img/task.png";

  isFormInvalid = false;

  constructor(private route: ActivatedRoute,
              private taskService: TaskService,
              private authenticationService: AuthenticationService) { }

  ngOnInit(): void {
    this.getTask();
  }


  getTask(): void {

     const id = +this.route.snapshot.paramMap.get('id')!.valueOf();

   
    this.taskService.getTask(id)
    .subscribe((data:Task)=>{
      console.log(data);
      this.task = data;
    })
  }


  onSubmit(signInForm: NgForm) {

    console.log(signInForm)

    if (!signInForm.valid) {
      this.isFormInvalid = true;
      return;
    }

    console.log(signInForm.value)
    this.task.employeeId = this.authenticationService.userId;
    console.log(this.task)
    this.taskService.updateTask(this.task)
      .subscribe(data => {
        console.log(data);

      });

  }


 


}
