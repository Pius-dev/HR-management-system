import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable, map } from "rxjs";
import { UserStorageService } from "../storage/user-storage.service";

const BASIC_URL = "http://localhost:8082/api/v1/auth/";

@Injectable({
  providedIn: "root",
})
export class AuthService {
  constructor(
    private http: HttpClient,
    private userStorageService: UserStorageService
  ) {}

  register(signupRequest: any): Observable<any> {
    return this.http.post(BASIC_URL + "registerStaff", signupRequest);
  }

  login(employeeNumber: string, password: string): any {
    const headers = new HttpHeaders().set("Content-Type", "application/json");
    const body = { employeeNumber, password };

    return this.http
      .post(BASIC_URL + "login", body, { headers, observe: "response" })
      .pipe(
        map((res) => {
          const token = res.headers.get("authorization")?.substring(7);
          const user = res.body;

          console.log("Response:", res); // Log the response

          if (token && user) {
            this.userStorageService.saveToken(token);
            this.userStorageService.saveUser(user);
            return true;
          }
          return false;
        })
      );
  }
}
