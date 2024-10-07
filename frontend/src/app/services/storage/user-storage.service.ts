import { Injectable } from "@angular/core";

const TOKEN = "hr-token";
const USER = "hr-user";

@Injectable({
  providedIn: "root",
})
export class UserStorageService {
  constructor() {}

  public saveToken(token: string): void {
    window.localStorage.removeItem(TOKEN);
    window.localStorage.setItem(TOKEN, token);
  }

  public saveUser(user): void {
    window.localStorage.removeItem(USER);
    window.localStorage.setItem(USER, JSON.stringify(user));
  }

  static getToken(): any {
    return window.localStorage.getItem(TOKEN);
  }

  static getUser(): any {
    const user = window.localStorage.getItem(USER);
    return user ? JSON.parse(user) : null;
  }

  static getUserId(): string {
    const user = this.getUser();
    if (user == null) {
      return "";
    }
    return user.userId;
  }

  static getUserRole(): string {
    const user = this.getUser();
    if (user == null) {
      return "";
    }
    return user.role;
  }

  static isAdminLoggedIn(): boolean {
    const token = this.getToken();
    if (token === null) {
      return false;
    }
    const role: string = this.getUserRole();
    return role === "ROLE_ADMIN";
  }

  static isStaffLoggedIn(): boolean {
    const token = this.getToken();
    if (token === null) {
      return false;
    }
    const role: string = this.getUserRole();
    return role === "ROLE_USER";
  }

  static isHrAdminLoggedIn(): boolean {
    const token = this.getToken();
    if (token === null) {
      return false;
    }
    const role: string = this.getUserRole();
    return role === "ROLE_HR";
  }

  static signOut(): void {
    window.localStorage.removeItem(TOKEN);
    window.localStorage.removeItem(USER);
  }
}
