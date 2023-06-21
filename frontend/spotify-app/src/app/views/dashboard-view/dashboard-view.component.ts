import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { apiUrl } from 'src/app/constants';


@Component({
  selector: 'app-dashboard-view',
  templateUrl: './dashboard-view.component.html',
})
export class DashboardViewComponent implements OnInit {
  
  token?: string | null;
  code?: string | null;
  loading: boolean = true;

  constructor(private router: ActivatedRoute, private http: HttpClient) {
  }
  

  ngOnInit(): void {
    this.token = this.router.snapshot.queryParamMap.get('token');
    console.log("Token de Spring Boot: "+this.token);
    // Verify it is valid

    // Reload if correct.

    // Local Storage
    sessionStorage.setItem("token", this.token ?? "");
    // Fetch Datra
    this.fetchTopArtists();
  }

  fetchTopArtists(): void {
    const token = sessionStorage.getItem('token') ?? '';
    const headers = new HttpHeaders().set('Authorization',token);

    this.http.get(apiUrl+"api/v1/user-top-artists", { headers }).subscribe(
      (response: any) => {
        console.log("Tracks: ")
        console.log(response)
        this.loading = true;
      },
      (error) => {
        console.log("Error while fetching tracks...")
        console.log(error)
      }

    )
  }



}
