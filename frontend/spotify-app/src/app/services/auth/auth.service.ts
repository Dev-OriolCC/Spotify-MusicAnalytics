import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private isAuthenticated = false;

  constructor() { }

  loginSpotify(): void {
    fetch('http://localhost:8080/api/v1/login')
      .then(response => response.text())
      .then(data => {
        console.log(data);
        location.replace(data);
        // Check if
        //this.isAuthenticated = true;
      })
      .catch(err => {
        console.error('Error: ', err);
      })
  }

  logout(): void {
    // Perform the logout operation
    // Set the authentication status to false
    this.isAuthenticated = false;
  }

  isAuthenticatedCheck(): boolean {
    return this.isAuthenticated;
  }


}
