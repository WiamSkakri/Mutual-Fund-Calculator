import { Component, OnInit } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { ApiService } from './api.service';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit{
  title = 'frontend';
  allFunds: Object = {};

  constructor(private apiService: ApiService) { }

  ngOnInit() {
    this.apiService.getMutualFund().subscribe((data) => {
      this.allFunds = data;
      console.log(this.allFunds);
    })
  }

}
