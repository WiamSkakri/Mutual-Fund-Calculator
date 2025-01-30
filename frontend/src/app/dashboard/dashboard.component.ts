import {Component, OnChanges, OnInit} from '@angular/core';
import { ApiService } from '../api.service';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {CurrencyPipe, NgForOf, NgIf, PercentPipe} from '@angular/common';
import {forkJoin, Observable} from 'rxjs';
import {map} from 'rxjs/operators'
import { ChartComponent } from '../chart/chart.component';


@Component({
  selector: 'app-dashboard',
  imports: [ReactiveFormsModule, FormsModule, NgForOf, NgIf, ChartComponent, CurrencyPipe, PercentPipe],
  templateUrl: './dashboard.component.html',
})

export class DashboardComponent implements OnChanges, OnInit{
  values: Array<{year: number, value: number}> = []

  mutualFunds: any = [];

  ticker: string | null = null
  initialInvestment: number | null = null
  time: number | null = null
  marketFreeRate: number | null = null
  riskFreeRate: number | null = null
  earnings: number | null = null

  constructor(private apiService: ApiService) { }

  ngOnInit() {
    this.apiService.getMutualFunds().subscribe((data: any) => {
      this.mutualFunds = data;
      console.log(this.mutualFunds);
    })
  }

  ngOnChanges() {

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
        this.earnings = this.initialInvestment ? this.values[this.values.length - 1].value - this.initialInvestment : 0
        for (const fund of this.mutualFunds) {
          if (fund.ticker == this.ticker) {
            this.marketFreeRate = fund.marketRate
            console.log(this.marketFreeRate)
            this.riskFreeRate = fund.riskRate

          }
        }
      })
    } else {
      alert("All three fields are required!")
    }
  }
}

