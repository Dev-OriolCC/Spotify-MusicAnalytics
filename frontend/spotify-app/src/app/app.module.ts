import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { DashboardViewComponent } from './views/dashboard-view/dashboard-view.component';
import { LoginViewComponent } from './views/login-view/login-view.component';
import { AuthService } from './services/auth/auth.service';
import { GuardService } from './services/guard/guard.service';
import { HttpClientModule } from '@angular/common/http';


@NgModule({
  declarations: [
    AppComponent,
    DashboardViewComponent,
    LoginViewComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [
    AuthService,
    GuardService,
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
