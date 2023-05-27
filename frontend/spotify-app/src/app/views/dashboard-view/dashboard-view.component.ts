import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';


@Component({
  selector: 'app-dashboard-view',
  templateUrl: './dashboard-view.component.html',
})
export class DashboardViewComponent implements OnInit {
  
  token?: string | null;
  code?: string | null;

  constructor(private router: ActivatedRoute) {
    //this.token = null;
  }

  ngOnInit(): void {
    console.log("This is the user code from the redirect");
    this.code = this.router.snapshot.queryParamMap.get('code');
    console.log(this.code);
    // Verify it is valid

    // Reload if correct.

    // Local Storage

    // Fetch Datra

    // this.token = this.router.snapshot.queryParamMap.get('token');
    // console.log(this.token);
    // console.log("Store token: " + this.token);
    // //const token = this.token;
    // if(this.token != null) {
    //   sessionStorage.setItem("token", this.token);
    //   const storedToken = sessionStorage.getItem('token');
    //   console.log("Session Token: "+storedToken); 
    // } 

  }

}
