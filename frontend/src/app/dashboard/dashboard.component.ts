import { Component, OnInit } from '@angular/core';
import { ApiService } from '../api.service';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {NgForOf, NgIf} from '@angular/common';
import {forkJoin, Observable} from 'rxjs';
import {map} from 'rxjs/operators'
import { ChartComponent } from '../chart/chart.component';


@Component({
  selector: 'app-dashboard',
  imports: [ ReactiveFormsModule, FormsModule, NgForOf, NgIf, ChartComponent],
  templateUrl: './dashboard.component.html',
})

export class DashboardComponent implements OnInit {
  values: Array<{year: number, value: number}> = []

  mutualFunds: any = [];

  ticker: string | null = null
  initialInvestment: number | null = 10000
  time: number | null = 5

  constructor(private apiService: ApiService) { }

  ngOnInit() {
    this.apiService.getMutualFunds().subscribe((data: any) => {
      this.mutualFunds = data;
      console.log(this.mutualFunds);
    })

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
        this.values = sortedValues
        console.log(sortedValues)
      })
    } else {
      alert("All three fields are required!")
    }
  }
}

