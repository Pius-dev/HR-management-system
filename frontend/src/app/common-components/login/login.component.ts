import { Component } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { MatSnackBar } from "@angular/material/snack-bar";
import { Router } from "@angular/router";
import { AuthService } from "../../services/auth/auth.service";
import { UserStorageService } from "../../services/storage/user-storage.service";

@Component({
  selector: "app-login",
  templateUrl: "./login.component.html",
  styleUrl: "./login.component.scss",
})
export class LoginComponent {
  loginForm!: FormGroup;
  hidePassword = true;

  constructor(
    private fb: FormBuilder,
    private snackBar: MatSnackBar,
    private authService: AuthService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loginForm = this.fb.group({
      employeeNumber: [null, [Validators.required]],
      password: [null, [Validators.required]],
    });
  }

  togglePasswordVisibility() {
    this.hidePassword = !this.hidePassword;
  }

  onSubmit(): void {
    const employeeNumber = this.loginForm.get("employeeNumber")!.value;
    const password = this.loginForm.get("password")!.value;

    this.authService.login(employeeNumber, password).subscribe(
      (res) => {
        console.log("Login success: ", res);

        if (UserStorageService.isAdminLoggedIn()) {
          console.log("Admin logged in");
          this.router.navigateByUrl("admin/dashboard");
        } else if (UserStorageService.isStaffLoggedIn()) {
          console.log("Staff logged in");
          this.router.navigateByUrl("staff/dashboard");
        } else if (UserStorageService.isHrAdminLoggedIn()) {
          console.log("HR Admin logged in");
          this.router.navigateByUrl("hr/dashboard");
        } else {
          console.log("Unknown role, staying on login page");
          this.snackBar.open("Unauthorized role", "Error", { duration: 5000 });
        }
      },
      (error) => {
        console.error("Login failed: ", error);
        this.snackBar.open("Bad credentials", "Error", { duration: 5000 });
      }
    );
  }
}
