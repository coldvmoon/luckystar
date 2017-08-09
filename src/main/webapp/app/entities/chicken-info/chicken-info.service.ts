import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils } from 'ng-jhipster';

import { ChickenInfo } from './chicken-info.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class ChickenInfoService {

    private resourceUrl = 'api/chicken-infos';

    constructor(private http: Http, private dateUtils: JhiDateUtils) { }

    create(chickenInfo: ChickenInfo): Observable<ChickenInfo> {
        const copy = this.convert(chickenInfo);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            this.convertItemFromServer(jsonResponse);
            return jsonResponse;
        });
    }

    update(chickenInfo: ChickenInfo): Observable<ChickenInfo> {
        const copy = this.convert(chickenInfo);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            this.convertItemFromServer(jsonResponse);
            return jsonResponse;
        });
    }

    find(id: number): Observable<ChickenInfo> {
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

    private convert(chickenInfo: ChickenInfo): ChickenInfo {
        const copy: ChickenInfo = Object.assign({}, chickenInfo);
        copy.regDate = this.dateUtils
            .convertLocalDateToServer(chickenInfo.regDate);
        return copy;
    }
}
