import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { DashboardViewComponent } from './views/dashboard-view/dashboard-view.component';
import { LoginViewComponent } from './views/login-view/login-view.component';
import { AuthService } from './services/auth/auth.service';
import { GuardService } from './services/guard/guard.service';
import { HttpClientModule } from '@angular/common/http';
import { NgChartsModule } from 'ng2-charts';
import { DonutChartComponent } from './components/donut-chart/donut-chart.component';

@NgModule({
  declarations: [
    AppComponent,
    DashboardViewComponent,
    LoginViewComponent,
    DonutChartComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    NgChartsModule
  ],
  providers: [
    AuthService,
    GuardService,
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
