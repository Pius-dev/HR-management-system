import { Component } from "@angular/core";
import { Router } from "@angular/router";
import { AdminService } from "../../service/admin.service";

@Component({
  selector: "app-employee-list",
  templateUrl: "./employee-list.component.html",
  styleUrls: ["./employee-list.component.scss"], // Corrected from styleUrl to styleUrls
})
export class EmployeeListComponent {
  employees: any[] = []; // Ensure this is an array
  displayedColumns: string[] = [
    "surName",
    "otherNames",
    "email",
    "phoneNumber",
    "actions",
  ]; // Define displayed columns

  constructor(private employeeService: AdminService, private router: Router) {}

  ngOnInit(): void {
    this.getEmployees();
  }

  getEmployees(): void {
    this.employeeService.getAllStaff().subscribe(
      (data) => {
        this.employees = data;
      },
      (error) => {
        console.error("Error fetching employees:", error);
      }
    );
  }

  updateEmployee(staffId: number): void {
    this.router.navigate([`/admin/update-employee`, staffId]);
  }

  detailsOfEmployee(id: number): void {
    this.router.navigate(["details-of-employee", id]);
  }
}
