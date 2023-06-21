import { NgModule } from '@angular/core';
import { DashboardViewComponent } from './views/dashboard-view/dashboard-view.component';
import { LoginViewComponent } from './views/login-view/login-view.component';
import { RouterModule, Routes } from '@angular/router';
import { GuardService } from './services/guard/guard.service';
import { DonutChartComponent } from './components/donut-chart/donut-chart.component';

const routes: Routes = [

  { path: 'dashboard-view', component: DashboardViewComponent }, //, canActivate: [GuardService] },
  { path: '', component: LoginViewComponent },
  {path: 'doughnut-chart', component: DonutChartComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
