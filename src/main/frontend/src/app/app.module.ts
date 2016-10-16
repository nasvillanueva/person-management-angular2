import {NgModule} from '@angular/core';
import {BrowserModule}  from '@angular/platform-browser';
import {AppComponent} from './app.component';
import {FormsModule} from "@angular/forms";
import {HttpModule} from "@angular/http";
import {routing} from "./app.routing";
import {LoginComponent} from "./login/login.component";
import {HashLocationStrategy, LocationStrategy} from "@angular/common";
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {LoginService} from "./shared/services/login.service";


@NgModule({
    imports: [
        BrowserModule,
        HttpModule,
        FormsModule,
        NgbModule.forRoot(),
        routing
    ],
    providers: [
        {provide: LocationStrategy, useClass: HashLocationStrategy},
        LoginService
    ],
    declarations: [
        AppComponent,
        LoginComponent
    ],
    bootstrap: [AppComponent]
})
export class AppModule {
}
