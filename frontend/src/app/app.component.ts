import { Component, OnInit } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { ApiService } from './api.service';
import { NavbarComponent } from './navbar/navbar.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {NgForOf, NgIf} from '@angular/common';
import {forkJoin, Observable} from 'rxjs';
import {map} from 'rxjs/operators'

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, NavbarComponent, ReactiveFormsModule, FormsModule, NgForOf, NgIf],
  templateUrl: './app.component.html'
})

export class AppComponent implements OnInit {
  title = 'frontend';
  value: Array<{year: number, value: number}> = [
    {year: 1 , value: 10000},
    {year: 2 , value: 10000},
    {year: 3 , value: 10000},
    {year: 4 , value: 10000},
    {year: 5 , value: 10000},
    {year: 6 , value: 10000},
    {year: 7 , value: 10000},
    {year: 8 , value: 10000},
    {year: 9 , value: 10000},
    {year: 10 , value: 10000},
  ]
  mutualFunds: any = [];

  ticker: string | null = null
  initialInvestment: number | null = null
  time: number | null = null

  constructor(private apiService: ApiService) { }

  ngOnInit() {
    this.apiService.getMutualFund().subscribe((data: any) => {this.mutualFunds = data;})
  }

  calculateButtonClick() {
    let newValues: Observable<{year: number, value: number}>[] = []
    if (this.time && this.ticker && this.initialInvestment) {
      for (let i = 1; i <= this.time; i++) newValues.push(
        this.apiService.getFutureValue(this.ticker, this.initialInvestment, i).pipe(
          map((data: any) => ({year: i, value: data}))))

      forkJoin(newValues).subscribe((sortedValues: { year: number, value: number }[]) => {
        sortedValues.sort((a, b) => {
          return a.year - b.year;
        });
        this.value = sortedValues
        console.log(sortedValues)
      })
    } else {
      alert("All three fields are required!")
    }
  }
}
