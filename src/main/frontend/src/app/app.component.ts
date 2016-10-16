import { Component } from '@angular/core';
import '../public/css/styles.css';
@Component({
    selector: 'my-app',
    template: require('./app.component.html'),
    styles: [
        require('./app.component.less').toString()
    ]
})
export class AppComponent { }