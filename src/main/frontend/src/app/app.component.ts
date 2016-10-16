import {Component} from '@angular/core';

import {AdminService} from './shared';

import '../style/app.less';

@Component({
    selector: 'my-app', // <my-app></my-app>
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.less'],
})
export class AppComponent {
    url = 'https://github.com/preboot/angular2-webpack';

    constructor(private api: AdminService) {
        // Do something with api
    }
}
