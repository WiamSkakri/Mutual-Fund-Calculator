import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  private apiUrl = environment.apiUrl;

  constructor(private http: HttpClient) { }

  getMutualFunds(): Observable<object> {
    return this.http.get(`${this.apiUrl}/mutualfunds/requests/allFunds`);
  }

  getFutureValue(ticker: string, initialInvestment: number, time: number) {
    const requestBody: any = {
      "ticker": ticker,
      "InitialInvestment": initialInvestment,
      "name": 'American Funds Growth Fund of America',
      "time": time
    };
    return this.http.post(`${this.apiUrl}/mutualfunds/requests/calculate/futureValue`, requestBody);
  }
}
