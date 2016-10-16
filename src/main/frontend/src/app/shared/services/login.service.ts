import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import {LoginForm} from "../../login/login.component";
import {Observable} from "rxjs";

@Injectable()
export class LoginService {
    private loginUrl = "/api/login";

    constructor (private http: Http) {}

    login(loginForm: LoginForm): Observable<any> {
        return this.http.post(this.loginUrl, loginForm)
            .map((res: Response) => res.json())
            .catch((err: any) => Observable.throw(err));
    }
}