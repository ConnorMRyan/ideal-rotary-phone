import {Observable, Subject} from 'rxjs';
import {UserService} from '../../../services/user.service';
import {AuthenticationService} from '../../../services/authentication.service';
import {TaskService} from '../../../services/task.service';
import {Task} from '../../../models/task';
import {NgForm} from '@angular/forms';
import {Component, OnInit} from '@angular/core';
import {User} from 'src/app/models/user';
import {debounceTime, distinctUntilChanged, switchMap} from 'rxjs/operators';
import {$} from 'protractor';

@Component({
  selector: 'app-create-task',
  templateUrl: './create-task.component.html',
  styleUrls: ['./create-task.component.css']
})
export class CreateTaskComponent implements OnInit {

  task: Task = new Task(0, '', '', '', '', '', '', 0, '', '', 0, '', '');
  myImage: string = 'assets/img/task.png';

  value: Date = new Date();
  checkDate: Date;


  users$: Observable<User[]>;
  private searchTerms = new Subject<string>();


  isFormInvalid = false;
  passwordNotMatch = false;
  emailNotAvailable = false;
  usernameNotAvailable = false;
  accountCreated = false;
  accountNotCreated = false;

  constructor(private taskService: TaskService,
              private authenticationService: AuthenticationService,
              private userService: UserService) {
  }

  ngOnInit(): void {

    this.users$ = this.searchTerms.pipe(
      // wait 300ms after each keystroke before considering a term
      debounceTime(300),

      // ignore new term if same as previosu term
      distinctUntilChanged(),

      // switch to a new search observable
      switchMap((term: string) => this.userService.searchUsers(term)),
    )

    console.log(this.users$);

  }

  search(term: string): void {
    this.searchTerms.next(term);
    // the next(value) takes in a param and pushes it to the subject (which is an observable)
  }

  // tslint:disable-next-line:typedef
  onSubmit(signInForm: NgForm) {

    console.log(signInForm);

    if (!signInForm.valid) {
      this.isFormInvalid = true;
      return;
    }

    console.log(signInForm.value);
    this.task.managerId = this.authenticationService.userId;
    console.log(this.task);
    // this.taskService.assignTask(this.task)
      // .subscribe((data: any) => {
        // console.log(data);

      // });

  }


}
