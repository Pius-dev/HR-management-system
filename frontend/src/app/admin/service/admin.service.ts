import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { map } from "rxjs/operators";

import { environment } from "../../../../environments/environment";
import { UserStorageService } from "../../services/storage/user-storage.service";

@Injectable({
  providedIn: "root",
})
export class AdminService {
  private readonly BASIC_URL = environment.apiUrl; // Use the environment API URL

  constructor(private http: HttpClient) {}

  getAllStaff(): Observable<any[]> {
    return this.http
      .get<{ responseCode: string; data: any[] }>(
        this.BASIC_URL + "admin/allStaff",
        {
          headers: this.createAuthorizationHeader(),
        }
      )
      .pipe(map((response) => response.data));
  }

  getEmployeeByEmployeeNumber(employeeNumber: string): Observable<any> {
    return this.http.get(this.BASIC_URL + `admin/employee/${employeeNumber}`, {
      headers: this.createAuthorizationHeader(),
    });
  }

  getMetrics(): Observable<any> {
    return this.http.get(this.BASIC_URL + "admin/metrics", {
      headers: this.createAuthorizationHeader(),
    });
  }

  getEmployeeById(staffId: number): Observable<any> {
    return this.http.get(this.BASIC_URL + `admin/staff/${staffId}`, {
      headers: this.createAuthorizationHeader(),
    });
  }

  updateEmployee(staffId: number, employeeData: any): Observable<any> {
    return this.http.put(
      this.BASIC_URL + `admin/update/${staffId}`,
      employeeData,
      {
        headers: this.createAuthorizationHeader(),
      }
    );
  }
  private createAuthorizationHeader(): HttpHeaders {
    return new HttpHeaders().set(
      "Authorization",
      "Bearer " + UserStorageService.getToken()
    );
  }
}
