import { Component } from "@angular/core";
import { AdminService } from "../../service/admin.service";

@Component({
  selector: "app-admin-dashboard",
  templateUrl: "./admin-dashboard.component.html",
  styleUrl: "./admin-dashboard.component.scss",
})
export class AdminDashboardComponent {
  totalEmployees: number = 0;
  requestData: any;

  constructor(private adminService: AdminService) {}

  ngOnInit(): void {
    this.getMetrics();
  }

  getMetrics(): void {
    this.adminService.getMetrics().subscribe(
      (data) => {
        this.requestData = data;
      },
      (error) => {
        console.error("Error fetching metrics data", error);
      }
    );
  }
}
