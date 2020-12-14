import { UpdateProfileComponent } from './components/manager/update-profile/update-profile.component';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SigninComponent } from './components/signin/signin.component';
import { HomeComponent } from './components/manager/home/home.component';
import { SignupComponent } from './components/signup/signup.component';
import { CreateTaskComponent } from './components/manager/create-task/create-task.component';
import { ShowUsersComponent } from './components/manager/show-users/show-users.component';
import { ShowProfileComponent } from './components/manager/show-profile/show-profile.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { ForgotpasswordComponent } from './components/forgotpassword/forgotpassword.component';
import { EmpHomeComponent } from './components/employee/emp-home/emp-home.component';
import { EmpShowProfileComponent } from './components/employee/emp-show-profile/emp-show-profile.component';
import { EmpShowTasksComponent } from './components/employee/emp-show-tasks/emp-show-tasks.component';
import { EmpDashboardComponent } from './components/employee/emp-dashboard/emp-dashboard.component';
import { DashboardComponent } from './components/manager/dashboard/dashboard.component';
import { EmpUpdateTaskComponent } from './components/employee/emp-update-task/emp-update-task.component';

@NgModule({
  declarations: [
    AppComponent,
    SigninComponent,
    HomeComponent,
    SignupComponent,
    CreateTaskComponent,
    ShowUsersComponent,
    ShowProfileComponent,
    ForgotpasswordComponent,
    EmpHomeComponent,
    EmpShowProfileComponent,
    EmpShowTasksComponent,
    EmpDashboardComponent,
    DashboardComponent,
    UpdateProfileComponent,
    EmpUpdateTaskComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
