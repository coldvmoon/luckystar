import {Component, OnInit, OnDestroy} from '@angular/core';

// import * from 'underscore/underscore.js';

import {ITEMS_PER_PAGE, Principal, ResponseWrapper} from '../../shared';

import {WorkTimeBoardService} from './work-time-board.service'

@Component({
    selector: 'jhi-chicken-info-board',
    templateUrl: './work-time-board.component.html'
})
export class WorkTimeBoardComponent implements OnInit, OnDestroy {
    data: any
    uniqueDate:any
    constructor(private WorkTimeBoardService: WorkTimeBoardService,) {

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
        var map = {};
        var tmp = {};
        for (var x in data) {
            if (!map[data[x].starId]) {
                map[data[x].starId] = {data: data[x], date: {}};
            }
            map[data[x].starId].date[data[x].curDay]=data[x].workTime
            tmp[data[x].curDay] = true;
        }
        this.data=[];
        for(var x in map){
            this.data.push(map[x])
        }

        this.uniqueDate=[];
        for(var x in tmp){
            this.uniqueDate.push(x);
        }
    }

    private onError(error) {

    }

}
