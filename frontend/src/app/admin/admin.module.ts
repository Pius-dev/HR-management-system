import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";

import { HttpClientModule } from "@angular/common/http";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { SharedModuleModule } from "../shared-module/shared-module.module";
import { AdminRoutingModule } from "./admin-routing.module";
import { AdminComponent } from "./admin.component";

import { AdminDashboardComponent } from "./components/admin-dashboard/admin-dashboard.component";
import { EmployeeListComponent } from "./components/employee-list/employee-list.component";
import { RequestChartComponent } from "./components/request-chart/request-chart.component";
import { ShowDetailsComponent } from "./components/show-details/show-details.component";
import { UpdateEmployeeComponent } from "./components/update-employee/update-employee.component";

@NgModule({
  declarations: [
    AdminComponent,
    AdminDashboardComponent,
    EmployeeListComponent,
    UpdateEmployeeComponent,
    ShowDetailsComponent,
    RequestChartComponent,
  ],
  imports: [
    CommonModule,
    AdminRoutingModule,
    CommonModule,
    AdminRoutingModule,
    SharedModuleModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
  ],
})
export class AdminModule {}
