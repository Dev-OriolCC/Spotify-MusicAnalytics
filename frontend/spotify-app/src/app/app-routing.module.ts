import { NgModule } from '@angular/core';
import { DashboardViewComponent } from './views/dashboard-view/dashboard-view.component';
import { LoginViewComponent } from './views/login-view/login-view.component';
import { RouterModule, Routes } from '@angular/router';
import { GuardService } from './services/guard/guard.service';

const routes: Routes = [

  { path: 'dashboard-view', component: DashboardViewComponent }, //, canActivate: [GuardService] },
  { path: '', component: LoginViewComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
