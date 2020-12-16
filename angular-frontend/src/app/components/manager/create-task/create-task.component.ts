import { User } from './../../../models/user';
import { Observable, Subject } from 'rxjs';
import { UserService } from './../../../services/user.service';
import { AuthenticationService } from './../../../services/authentication.service';
import { TaskService } from './../../../services/task.service';
import { Task } from './../../../models/task';
import { FormControl, NgForm } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { debounceTime, distinctUntilChanged, switchMap } from 'rxjs/operators';
import { startWith,map } from 'rxjs/operators';

@Component({
  selector: 'app-create-task',
  templateUrl: './create-task.component.html',
  styleUrls: ['./create-task.component.css']
})
export class CreateTaskComponent implements OnInit {

  task : Task = new Task(0,"","","","","","",0,"","",0,"","");
  myImage: string = "assets/img/task.png";

  users$: Observable<User[]>;
  private searchTerms = new Subject<string>();


  //////////////
  filteredOptions: Observable<User[]>;
  myControl = new FormControl();
  options: User[];
  //////////////
  isFormInvalid = false;
  passwordNotMatch = false;
  emailNotAvailable = false;
  usernameNotAvailable = false;
  accountCreated = false;
  accountNotCreated = false;

  constructor(private taskService: TaskService, 
              private userService: UserService,
              private authenticationService: AuthenticationService) { }

  ngOnInit(): void {


    // this.users$ = this.searchTerms.pipe(
    //   debounceTime(300),

    //   distinctUntilChanged(),
    //   // switch to a new search observable
    //   switchMap((term: string) => this.userService.searchUsers(term)),
    // )

    this.userService.getAllUsers().subscribe((data: User[]) => {
      this.onGetTaxList(data)
    });

  }

  //////////////////////

  onGetTaxList(data : any) {
    this.options = data;
    this.filteredOptions = this.myControl.valueChanges
      .pipe(
        startWith(''),
        map(value => value.length >= 1 ? this._filter(value) : [])
      );
  }
  private _filter(value: string) {
    const filterValue = value.toLowerCase();

    return this.options.filter(option => (option.firstName + ' ' + option.lastName)
    .toLowerCase().includes(filterValue));
  }


  /////////////////

  search(term: string): void {
    this.searchTerms.next(term)
    // the next(value) takes in a param and pushes it to the subject (which is an observable)
  }

  onSubmit(signInForm: NgForm) {

    console.log(signInForm)
    console.log('Gellow World')
    console.log(this.myControl.value)
    this.task.employee = this.myControl.value;

    if (!signInForm.valid) {
      this.isFormInvalid = true;
      return;
    }

    console.log(signInForm.value)
    this.task.managerId = this.authenticationService.userId;
    console.log(this.task)
    this.taskService.addTask(this.task)
      .subscribe(data => {
        console.log(data);

      });

  }

}
