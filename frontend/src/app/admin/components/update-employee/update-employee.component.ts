import { Component, OnInit } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { ActivatedRoute, Router } from "@angular/router";
import { AdminService } from "../../service/admin.service";

@Component({
  selector: "app-update-employee",
  templateUrl: "./update-employee.component.html",
  styleUrls: ["./update-employee.component.scss"], // Corrected styleUrls to styleUrls
})
export class UpdateEmployeeComponent implements OnInit {
  updateEmployeeForm: FormGroup; // Declare the FormGroup
  employee: any = {};

  constructor(
    private employeeService: AdminService,
    private router: Router,
    private route: ActivatedRoute,
    private fb: FormBuilder // Inject FormBuilder
  ) {
    // Initialize the FormGroup with form controls and validations
    this.updateEmployeeForm = this.fb.group({
      surName: ["", [Validators.required, Validators.minLength(3)]],
      otherNames: ["", Validators.required],
      email: ["", [Validators.required, Validators.email]],
      phoneNumber: ["", Validators.required],
    });
  }

  ngOnInit(): void {
    const employeeNumber = this.route.snapshot.paramMap.get("employeeNumber");
    this.getEmployeeByEmployeeNumber(employeeNumber);
  }

  getEmployeeByEmployeeNumber(employeeNumber: string | null): void {
    if (employeeNumber) {
      this.employeeService
        .getEmployeeByEmployeeNumber(employeeNumber)
        .subscribe(
          (data) => {
            this.employee = data; // Populate employee details
            // Patch the form with the fetched employee data
            this.updateEmployeeForm.patchValue({
              surName: this.employee.surName,
              otherNames: this.employee.otherNames,
              email: this.employee.email,
              phoneNumber: this.employee.phoneNumber,
            });
          },
          (error) => {
            console.error("Error fetching employee data", error); // Handle error
          }
        );
    }
  }

  onSubmit(): void {
    if (this.updateEmployeeForm.valid) {
      // Check if form is valid before submitting
      const updatedEmployee = {
        ...this.employee,
        ...this.updateEmployeeForm.value,
      }; // Merge updated values
      this.employeeService.updateEmployee(updatedEmployee).subscribe(
        (data) => {
          console.log("Employee updated successfully!", data);
          this.router.navigate(["/employee-details"]);
        },
        (error) => {
          console.error("Error updating employee data", error);
        }
      );
    } else {
      console.error("Form is invalid");
    }
  }

  goToEmployeeList(): void {
    this.router.navigate(["/employee-details"]); // Navigate back to employee list
  }
}
