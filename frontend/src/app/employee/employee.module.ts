import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";

import { DashboardComponent } from "./components/dashboard/dashboard.component";
import { EmployeeRoutingModule } from "./employee-routing.module";
import { EmployeeComponent } from "./employee.component";

@NgModule({
  declarations: [EmployeeComponent, DashboardComponent],
  imports: [CommonModule, EmployeeRoutingModule],
})
export class EmployeeModule {}
