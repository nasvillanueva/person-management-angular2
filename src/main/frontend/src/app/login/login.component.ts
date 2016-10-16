import {Component} from '@angular/core';

@Component({
    selector: 'my-login',
    templateUrl: './login.component.html',
    styles: [
        require('./login.component.less').toString()
    ]
})
export class LoginComponent {
    constructor() {
    }
}
