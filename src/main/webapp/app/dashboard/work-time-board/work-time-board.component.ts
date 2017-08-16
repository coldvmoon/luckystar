import {Component, OnInit, OnDestroy} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {Subscription} from 'rxjs/Rx';
import {JhiEventManager, JhiParseLinks, JhiPaginationUtil, JhiLanguageService, JhiAlertService} from 'ng-jhipster';


import {ITEMS_PER_PAGE, Principal, ResponseWrapper} from '../../shared';
import {PaginationConfig} from '../../blocks/config/uib-pagination.config';

import {WorkTimeBoardService} from './work-time-board.service'

@Component({
    selector: 'jhi-work-time-board',
    templateUrl: './work-time-board.component.html'
})
export class WorkTimeBoardComponent implements OnInit, OnDestroy {
    currentAccount: any;
    error: any;
    success: any;
    eventSubscriber: Subscription;
    routeData: any;
    links: any;
    totalItems: any;
    queryCount: any;
    itemsPerPage: any;
    page: any;
    predicate: any;
    previousPage: any;
    reverse: any;

    data: any
    uniqueDate:any

    constructor(private workTimeBoardService: WorkTimeBoardService,
                private parseLinks: JhiParseLinks,
                private alertService: JhiAlertService,
                private principal: Principal,
                private activatedRoute: ActivatedRoute,
                private router: Router,
                private eventManager: JhiEventManager,
                private paginationUtil: JhiPaginationUtil,
                private paginationConfig: PaginationConfig) {
        this.itemsPerPage = ITEMS_PER_PAGE;
        this.routeData = this.activatedRoute.data.subscribe((data) => {
            this.page = data['pagingParams'].page;
            this.previousPage = data['pagingParams'].page;
            this.reverse = data['pagingParams'].ascending;
            this.predicate = data['pagingParams'].predicate;
        });
    }

    loadAll() {
        this.workTimeBoardService.query().subscribe(
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
        for (let x in data) {
            if (!map[data[x].starId]) {
                map[data[x].starId] = {data: data[x], date: {}};
            }
            map[data[x].starId].date[data[x].curDay]=data[x].workTime
            tmp[data[x].curDay] = true;
        }
        this.data=[];
        for(let x in map){
            this.data.push(map[x])
        }

        this.uniqueDate=[];
        for(let x in tmp){
            this.uniqueDate.push(x);
        }
    }

    private onError(error) {

    }

    transition() {
        // this.router.navigate(['/labor-union'], {
        //     queryParams: {
        //         page: this.page,
        //         size: this.itemsPerPage,
        //         sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
        //     }
        // });
        this.loadAll();
    }

}
