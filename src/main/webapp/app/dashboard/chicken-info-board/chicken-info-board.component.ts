import { Component, OnInit, OnDestroy } from '@angular/core';


import { ITEMS_PER_PAGE, Principal, ResponseWrapper,TimeFormat } from '../../shared';

import {ChickenInfoBoardService} from './chicken-info-board.service'

@Component({
    selector: 'jhi-chicken-info-board',
    templateUrl: './chicken-info-board.component.html'
})
export class ChickenInfoBoardComponent implements OnInit, OnDestroy {
    data:any
    recentTime:any
    day:any
    constructor(
        private chickenInfoBoardService: ChickenInfoBoardService,
    ) {

    }
    loadAll() {
        this.chickenInfoBoardService.query().subscribe(
            (res: ResponseWrapper) => this.onSuccess(res.json, res.headers),
            (res: ResponseWrapper) => this.onError(res.json)
        );
    }
    ngOnInit(): void {
        // this.loadAll()
        this.chickenInfoBoardService.recentTime().subscribe(
            (res: ResponseWrapper) => this.onSuccess1(res, res.headers),
            (res: ResponseWrapper) => this.onError(res.json)
        );
    }

    ngOnDestroy(): void {
    }

    private onSuccess1(data, headers) {
        this.recentTime = data.json()
        this.chickenInfoBoardService.query(this.recentTime[0]).subscribe(
            (res: ResponseWrapper) => this.onSuccess(res.json, res.headers),
            (res: ResponseWrapper) => this.onError(res.json)
        );

    }
    private onSuccess(data, headers) {
        this.data = data;

    }
    private onError(error) {

    }

    statement(day?:string):void{
        // this.day=day
        this.chickenInfoBoardService.query(day).subscribe(
            (res: ResponseWrapper) => this.onSuccess(res.json, res.headers),
            (res: ResponseWrapper) => this.onError(res.json)
        );
    }
}
