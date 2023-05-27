import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth/auth.service';

@Component({
  selector: 'app-login-view',
  templateUrl: './login-view.component.html',
  styleUrls: ['./login-view.component.scss']
})
export class LoginViewComponent {
  title = 'spotify-app';

  constructor(private authService: AuthService) {}

  onLoginSuccess(): void {
    // Call the login method in the AuthService upon successful authentication
    this.authService.loginSpotify();
    // Redirect or perform any additional actions after login
    console.log(this.authService.isAuthenticatedCheck());
  }

  ngOnInit(): void {
    // Check if the user is authenticated
    const isAuthenticated = this.authService.isAuthenticatedCheck();

    // Perform actions based on authentication status
    if (isAuthenticated) {
      // User is authenticated, do something
      console.log(isAuthenticated);
      //location.replace('/dashboard-view')
    } else {
      
      // User is not authenticated, do something else
      console.log(isAuthenticated);
    }
  }
}
