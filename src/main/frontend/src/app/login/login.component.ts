import {Component} from '@angular/core';
import {LoginService} from "../shared/services/login.service";

@Component({
    selector: 'my-login',
    template: require('./login.component.html'),
    styles: [
        require('./login.component.less').toString()
    ]
})
export class LoginComponent {
    login: LoginForm = {
        username: "",
        password: ""
    };

    errorMessage: String;

    constructor(private loginService: LoginService) {
    }

    submitLogin(){
        this.loginService.login(this.login)
            .subscribe(resp => console.log(resp), err => {
                this.errorMessage = err._body;
            });
    }
}

export class LoginForm {
    username: string;
    password: string;
}