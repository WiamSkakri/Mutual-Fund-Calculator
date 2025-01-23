import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  constructor(private http: HttpClient) { }

  getMutualFund() : Observable<object> {
    return this.http.get('http://localhost:8080/group_4/mutualfunds')
  }
  getFutureValue(ticker: string, initialInvestment: number, time: number)  {
    return this.http.get(`http://localhost:8080/group_4/mutualfunds/futurevalue?ticker=${ticker}&initialInvestment=${initialInvestment}&time=${time}`)
  }
}
