import { NgModule } from "@angular/core";
import { BrowserModule } from "@angular/platform-browser";

import { HttpClientModule } from "@angular/common/http";
import { FlexLayoutModule } from "@angular/flex-layout";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { MatCardModule } from "@angular/material/card";
import { provideAnimationsAsync } from "@angular/platform-browser/animations/async";
import { AppRoutingModule } from "./app-routing.module";
import { AppComponent } from "./app.component";
import { LoginComponent } from "./common-components/login/login.component";
import { SidebarComponent } from "./common-components/sidebar/sidebar.component";
import { SignUpComponent } from "./common-components/sign-up/sign-up.component";
import { SharedModuleModule } from "./shared-module/shared-module.module";

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    SignUpComponent,
    SidebarComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    SharedModuleModule,
    FlexLayoutModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    MatCardModule,
  ],
  providers: [provideAnimationsAsync()],
  bootstrap: [AppComponent],
})
export class AppModule {}
