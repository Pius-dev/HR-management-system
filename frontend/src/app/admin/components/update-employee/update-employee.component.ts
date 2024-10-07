import { Component, OnInit } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { ActivatedRoute, Router } from "@angular/router";
import { AdminService } from "../../service/admin.service";

@Component({
  selector: "app-update-employee",
  templateUrl: "./update-employee.component.html",
  styleUrls: ["./update-employee.component.scss"],
})
export class UpdateEmployeeComponent implements OnInit {
  updateEmployeeForm: FormGroup;
  employee: any = {};
  staffId: number | null = null;

  constructor(
    private employeeService: AdminService,
    private router: Router,
    private route: ActivatedRoute,
    private fb: FormBuilder
  ) {
    this.updateEmployeeForm = this.fb.group({
      surName: ["", [Validators.required, Validators.minLength(3)]],
      otherNames: ["", Validators.required],
      email: ["", [Validators.required, Validators.email]],
      phoneNumber: ["", Validators.required],
    });
  }

  ngOnInit(): void {
    this.staffId = Number(this.route.snapshot.paramMap.get("staffId"));
    console.log("Staff ID in Update Component:", this.staffId);

    if (this.staffId) {
      this.getEmployeeByStaffId(this.staffId);
    }
  }

  getEmployeeByStaffId(staffId: number | null): void {
    if (staffId) {
      this.employeeService.getEmployeeById(+staffId).subscribe(
        (data) => {
          this.employee = data; // Populate employee details
          this.updateEmployeeForm.patchValue({
            surName: this.employee.surName,
            otherNames: this.employee.otherNames,
            email: this.employee.email,
            phoneNumber: this.employee.phoneNumber,
          });
        },
        (error) => {
          console.error("Error fetching employee data", error);
        }
      );
    }
  }

  onSubmit(): void {
    if (this.updateEmployeeForm.valid && this.staffId) {
      this.employeeService
        .updateEmployee(this.staffId, this.updateEmployeeForm.value)
        .subscribe(
          (data) => {
            console.log("Employee updated successfully!", data);
            this.router.navigate(["/employee-details"]); // Redirect after successful update
          },
          (error) => {
            console.error("Error updating employee data", error);
          }
        );
    } else {
      console.error("Form is invalid or staffId is missing");
    }
  }

  goToEmployeeList(): void {
    this.router.navigate(["/employees"]); // Navigate back to the employee list
  }
}
