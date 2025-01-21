import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  constructor(private http: HttpClient) { }

  getMutualFund() {
    return this.http.get('http://localhost:8080/group_4/mutualfunds')
  }
  getFutureValue() {
    return this.http.get('http://localhost:8080/group_4/mutualfunds/futurevalue')
  }
}
