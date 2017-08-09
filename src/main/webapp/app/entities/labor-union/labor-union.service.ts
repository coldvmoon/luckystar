import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils } from 'ng-jhipster';

import { LaborUnion } from './labor-union.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class LaborUnionService {

    private resourceUrl = 'api/labor-unions';

    constructor(private http: Http, private dateUtils: JhiDateUtils) { }

    create(laborUnion: LaborUnion): Observable<LaborUnion> {
        const copy = this.convert(laborUnion);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            this.convertItemFromServer(jsonResponse);
            return jsonResponse;
        });
    }

    update(laborUnion: LaborUnion): Observable<LaborUnion> {
        const copy = this.convert(laborUnion);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            this.convertItemFromServer(jsonResponse);
            return jsonResponse;
        });
    }

    find(id: number): Observable<LaborUnion> {
        return this.http.get(`${this.resourceUrl}/${id}`).map((res: Response) => {
            const jsonResponse = res.json();
            this.convertItemFromServer(jsonResponse);
            return jsonResponse;
        });
    }

    query(req?: any): Observable<ResponseWrapper> {
        const options = createRequestOption(req);
        return this.http.get(this.resourceUrl, options)
            .map((res: Response) => this.convertResponse(res));
    }

    delete(id: number): Observable<Response> {
        return this.http.delete(`${this.resourceUrl}/${id}`);
    }

    private convertResponse(res: Response): ResponseWrapper {
        const jsonResponse = res.json();
        for (let i = 0; i < jsonResponse.length; i++) {
            this.convertItemFromServer(jsonResponse[i]);
        }
        return new ResponseWrapper(res.headers, jsonResponse, res.status);
    }

    private convertItemFromServer(entity: any) {
        entity.regDate = this.dateUtils
            .convertLocalDateFromServer(entity.regDate);
    }

    private convert(laborUnion: LaborUnion): LaborUnion {
        const copy: LaborUnion = Object.assign({}, laborUnion);
        copy.regDate = this.dateUtils
            .convertLocalDateToServer(laborUnion.regDate);
        return copy;
    }
}
