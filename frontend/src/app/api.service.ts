import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  constructor(private http: HttpClient) { }

  getMutualFunds() : Observable<object> {
    return this.http.get('http://localhost:8095/mutualfunds/requests/allFunds')
  }
  getFutureValue(ticker: string, initialInvestment: number, time: number)  {
    const requestBody: any = {
      "ticker": ticker,
      "InitialInvestment": initialInvestment,
      "name": 'American Funds Growth Fund of America',
      "time": time
    }
    return this.http.post(`http://localhost:8095/mutualfunds/requests/calculate/futureValue`, requestBody)
  }
}
