import { Component, OnChanges, Input } from '@angular/core';
import Chart, { ChartConfiguration } from 'chart.js/auto';
import { map } from 'rxjs/operators';
import { Observable, of } from 'rxjs';

@Component({
  selector: 'app-chart',
  templateUrl: './chart.component.html',
})
export class ChartComponent implements OnChanges {
  @Input() investmentData!: Array<{ year: number, value: number }>;
  allValues: number[] = [];
  chart: Chart | undefined;

  getValues(): Observable<number[]> {
    return of(this.investmentData).pipe(
      map(data => data.map(item => item.value))
    );
  }

  ngOnChanges() {
    this.getValues().subscribe(vals => {
      this.allValues = vals;
      this.initializeChart();
    });
  }

  initializeChart() {
    const data = {
      labels: this.investmentData.map(item => (item.year).toString()),
      datasets: [
        {
          label: 'Investment Values',
          data: this.allValues,
          borderWidth: 1,
          backgroundColor: '#7399C6',
        },
      ],
    };

    const config: ChartConfiguration<'bar'> = {
      type: 'bar',
      data: data,
      options: {
        responsive: true,
        scales: {
          y: {
            beginAtZero: true,
          },
        },
      },
    };

    if (this.chart) {
      this.chart.destroy();
    }
    this.chart = new Chart('MyChart', config);
  }
}
