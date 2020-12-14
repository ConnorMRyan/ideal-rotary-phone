import { Task } from './../../../models/task';
import { TaskService } from './../../../services/task.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  tasks : Task[] = [];

  constructor(private taskService: TaskService) { }

  ngOnInit(): void {

    this.taskService.getAllTasks()
    .subscribe((data:Task[])=>{
      console.log(data);
      this.tasks = data;
    })
  }

}
