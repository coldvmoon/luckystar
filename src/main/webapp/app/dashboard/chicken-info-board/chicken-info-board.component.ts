import { Component, OnInit, OnDestroy } from '@angular/core';


import { ITEMS_PER_PAGE, Principal, ResponseWrapper,TimeFormat } from '../../shared';

import {ChickenInfoBoardService} from './chicken-info-board.service'

@Component({
    selector: 'jhi-chicken-info-board',
    templateUrl: './chicken-info-board.component.html'
})
export class ChickenInfoBoardComponent implements OnInit, OnDestroy {
    data:any
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
        this.loadAll()
    }

    ngOnDestroy(): void {
    }

    private onSuccess(data, headers) {
        this.data = data
    }
    private onError(error) {

    }

}
