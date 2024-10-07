import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { LoginComponent } from "./common-components/login/login.component";
import { SignUpComponent } from "./common-components/sign-up/sign-up.component";

const routes: Routes = [
  { path: "", pathMatch: "full", redirectTo: "login" },
  { path: "login", component: LoginComponent },
  { path: "signup", component: SignUpComponent },
  {
    path: "staff",
    loadChildren: () =>
      import("./employee/employee.module").then((m) => m.EmployeeModule),
  },
  {
    path: "admin",
    loadChildren: () =>
      import("./admin/admin.module").then((m) => m.AdminModule),
  },
  // Wildcard route for a 404 page if needed
  // { path: "**", redirectTo: "login" },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
