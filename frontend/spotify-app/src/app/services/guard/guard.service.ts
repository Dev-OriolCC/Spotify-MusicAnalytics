import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { AuthService } from '../auth/auth.service';

@Injectable({
  providedIn: 'root'
})
export class GuardService implements CanActivate {

  constructor(private authService: AuthService, private router: Router) {}

  canActivate(): boolean {
    if (this.authService.isAuthenticatedCheck()) {
      return true; // Allow access to the route
    } else {
      this.router.navigate(['dashboard-view']); // Redirect to login page if not authenticated
      return false; // Deny access to the route
    }
  }

}
