import { Component, OnInit, OnDestroy } from '@angular/core';

import { ITEMS_PER_PAGE, Principal, ResponseWrapper } from '../../shared';

import {WorkTimeBoardService} from './work-time-board.service'

@Component({
    selector: 'jhi-chicken-info-board',
    templateUrl: './work-time-board.component.html'
})
export class WorkTimeBoardComponent implements OnInit, OnDestroy {
    data:any
    constructor(
        private WorkTimeBoardService: WorkTimeBoardService,
    ) {

    }
    loadAll() {
        this.WorkTimeBoardService.query().subscribe(
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
