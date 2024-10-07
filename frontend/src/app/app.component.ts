import { Component } from "@angular/core";
import { Router } from "@angular/router";
import { UserStorageService } from "./services/storage/user-storage.service";

@Component({
  selector: "app-root",
  templateUrl: "./app.component.html",
  styleUrl: "./app.component.scss",
})
export class AppComponent {
  title = "frontend";
  currentYear: number = new Date().getFullYear();

  isStaffLoggedIn: boolean = UserStorageService.isStaffLoggedIn();
  isAdminLoggedIn: boolean = UserStorageService.isAdminLoggedIn();
  constructor(private router: Router) {}

  ngOnInit(): void {
    this.router.events.subscribe((event) => {
      this.isStaffLoggedIn = UserStorageService.isStaffLoggedIn();
      this.isAdminLoggedIn = UserStorageService.isAdminLoggedIn();
    });
  }

  logout() {
    UserStorageService.signOut();
    this.router.navigateByUrl("login");
  }
}
