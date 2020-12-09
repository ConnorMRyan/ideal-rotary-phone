import { EmpShowTasksComponent } from './components/employee/emp-show-tasks/emp-show-tasks.component';
import { EmpHomeComponent } from './components/employee/emp-home/emp-home.component';

import { ForgotpasswordComponent } from './components/forgotpassword/forgotpassword.component';
import { ShowTasksComponent } from './components/manager/show-tasks/show-tasks.component';
import { AssignTaskComponent } from './components/manager/assign-task/assign-task.component';
import { CreateTaskComponent } from './components/manager/create-task/create-task.component';
import { AuthGuard } from './guards/auth.guard';
import { HomeComponent } from './components/manager/home/home.component';
import { ShowUsersComponent } from './components/manager/show-users/show-users.component';
import { SignupComponent } from './components/signup/signup.component';
import { SigninComponent } from './components/signin/signin.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ShowProfileComponent } from './components/manager/show-profile/show-profile.component';
import { EmpShowProfileComponent } from './components/employee/emp-show-profile/emp-show-profile.component';
import { EmpDashboardComponent } from './components/employee/emp-dashboard/emp-dashboard.component';

const routes: Routes = [

  {path: '', redirectTo: '/api/signin', pathMatch: 'full'},
  {path: 'api/signin', component: SigninComponent},
  {path: 'api/signup', component: SignupComponent},
  {path: 'api/mngHome', component: HomeComponent},
  {path: 'api/users', component: ShowUsersComponent},


  {path: 'api/users', component: ShowUsersComponent},


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
