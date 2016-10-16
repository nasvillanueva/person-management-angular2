import {Http, Response} from '@angular/http';
import {Observable} from 'rxjs/Rx';

let BASE_URL = '/api/admin';

export class AdminService {
    constructor(private http: Http) {
    }

    getAllUser() {
        return this.http.get(`${BASE_URL}/list`).map((res: Response) => res.json());
    }

    // Uses Observable.forkJoin() to run multiple concurrent http.get() requests.
    // The entire operation will result in an error state if any single request fails.
    getBooksAndMovies() {
        return Observable.forkJoin(
            this.http.get('/app/books.json').map((res: Response) => res.json()),
            this.http.get('/app/movies.json').map((res: Response) => res.json())
        );
    }
}
