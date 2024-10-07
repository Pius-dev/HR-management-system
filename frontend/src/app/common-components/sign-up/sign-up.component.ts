import { Component } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { MatSnackBar } from "@angular/material/snack-bar";
import { Router } from "@angular/router";
import { AuthService } from "../../services/auth/auth.service";

@Component({
  selector: "app-sign-up",
  templateUrl: "./sign-up.component.html",
  styleUrls: ["./sign-up.component.scss"],
})
export class SignUpComponent {
  signupForm!: FormGroup;
  hidePassword = true;
  selectedFile: File | null;
  imagePreview: string | ArrayBuffer | null;

  constructor(
    private fb: FormBuilder,
    private snackBar: MatSnackBar,
    private authService: AuthService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.signupForm = this.fb.group({
      surName: [null, [Validators.required]],
      otherNames: [null, [Validators.required]],
      dateOfBirth: [null],
      idPhotoBase64: [null],
      email: [null, [Validators.required, Validators.email]],
      phoneNumber: [null, [Validators.required]],
      password: [null, [Validators.required]],
      validationCode: [null, [Validators.required]],
    });
  }

  onFileSelected(event: any) {
    this.selectedFile = event.target.files[0];
    this.previewImage();
  }

  previewImage() {
    const reader = new FileReader();
    reader.onload = () => {
      this.imagePreview = reader.result;
    };
    reader.readAsDataURL(this.selectedFile);
  }

  togglePasswordVisibility() {
    this.hidePassword = !this.hidePassword;
  }

  onSubmit(): void {
    if (this.signupForm.invalid) {
      return;
    }

    const formData = {
      surName: this.signupForm.get("surName")?.value,
      otherNames: this.signupForm.get("otherNames")?.value,
      idPhotoBase64: "", // Add logic to handle base64 image input if needed
      dateOfBirth: this.signupForm.get("dateOfBirth")?.value,
      email: this.signupForm.get("email")?.value,
      password: this.signupForm.get("password")?.value,
      phoneNumber: this.signupForm.get("phoneNumber")?.value,
      validationCode: this.signupForm.get("validationCode")?.value,
    };

    this.authService.register(formData).subscribe(
      (response) => {
        this.snackBar.open("Sign up successful!", "Close", { duration: 5000 });
        this.router.navigateByUrl("/login");
      },
      (error) => {
        this.snackBar.open("Sign up failed. Please try again.", "Close", {
          duration: 5000,
          panelClass: "error-snackbar",
        });
      }
    );
  }
}
