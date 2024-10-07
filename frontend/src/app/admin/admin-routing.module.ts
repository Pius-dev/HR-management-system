import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { AdminComponent } from "./admin.component";

import { AdminDashboardComponent } from "./components/admin-dashboard/admin-dashboard.component";
import { EmployeeListComponent } from "./components/employee-list/employee-list.component";
import { UpdateEmployeeComponent } from "./components/update-employee/update-employee.component";

const routes: Routes = [
  { path: "", component: AdminComponent },
  { path: "dashboard", component: AdminDashboardComponent },
  { path: "employee-list", component: EmployeeListComponent },
  { path: "update-employee/:staffId", component: UpdateEmployeeComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class AdminRoutingModule {}
