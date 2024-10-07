import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { map } from "rxjs/operators";

import { UserStorageService } from "../../services/storage/user-storage.service";

const BASIC_URL = "http://localhost:8082/api/v1/";

@Injectable({
  providedIn: "root",
})
export class AdminService {
  constructor(private http: HttpClient) {}

  getAllStaff(): Observable<any[]> {
    return this.http
      .get<{ responseCode: string; data: any[] }>(
        BASIC_URL + "admin/allStaff",
        {
          headers: this.createAuthorizationHeader(),
        }
      )
      .pipe(
        map((response) => response.data) // Extracting the 'data' array
      );
  }

  getEmployeeByEmployeeNumber(employeeNumber: string): Observable<any> {
    return this.http.get(BASIC_URL + `admin/employee/${employeeNumber}`, {
      headers: this.createAuthorizationHeader(),
    });
  }

  updateEmployee(employee: any): Observable<any> {
    return this.http.put(BASIC_URL + `admin/update`, employee, {
      headers: this.createAuthorizationHeader(),
    });
  }

  getMetrics(): Observable<any> {
    return this.http.get(BASIC_URL + "admin/metrics", {
      headers: this.createAuthorizationHeader(),
    });
  }

  private createAuthorizationHeader(): HttpHeaders {
    return new HttpHeaders().set(
      "Authorization",
      "Bearer " + UserStorageService.getToken()
    );
  }
}
