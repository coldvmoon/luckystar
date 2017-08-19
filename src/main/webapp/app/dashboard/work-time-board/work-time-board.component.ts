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
    laborUnionBoards:any
    labor:any
    data: any
    uniqueDate:any

    laborUnions:any

    regDate:any
    day: any;
    userName: string;
    nickName: string;
    starId: string;
    phoneNumber: string;
    qq: string;
    weiChat: string;

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
        this.workTimeBoardService.query({
            query: {
                day: this.day,
                laborUnionId:this.labor,
                date:this.regDate.year+"-"+this.regDate.month+"-"+this.regDate.day,
                userName: this.userName,
                nickName: this.nickName,
                starId: this.starId,
                phoneNumber: this.phoneNumber,
                qq: this.qq,
                weiChat: this.weiChat
            },
            page: this.page - 1,
            size: this.itemsPerPage,
            sort: this.sort()
        }).subscribe(
            (res: ResponseWrapper) => this.onSuccess(res.json, res.headers),
            (res: ResponseWrapper) => this.onError(res.json)
        );
    }

    ngOnInit(): void {
        this.day=0;
        var date = new Date();
        this.regDate={
            year:date.getFullYear(),
            month:date.getMonth()+1,
            day:date.getDate()
        }
        this.workTimeBoardService.recentTime().subscribe(
            (res: ResponseWrapper) => this.onSuccess1(res, res.headers),
            (res: ResponseWrapper) => this.onError(res.json)
        );

    }

    ngOnDestroy(): void {
    }


    private onSuccess1(data, headers) {
        var data  = data.json();
        this.laborUnions = data.laborUnions;

        this.labor = this.laborUnions[0].lId;
        this.loadAll();

    }

    private onSuccess(data, headers) {
        this.links = this.parseLinks.parse(headers.get('link'));
        // this.totalItems = headers.get('X-Total-Count');


        var map = {};
        var tmp = {};
        var uniqueId:number[] = [];
        for (let x in data) {
            if (!map[data[x].starId]) {
                map[data[x].starId] = {data: data[x], date: {}};
                uniqueId.push(data[x].starId)
            }
            map[data[x].starId].date[data[x].curDay]=data[x].workTime
            tmp[data[x].curDay] = true;
        }
        this.data=[];
        for(let x in uniqueId){
            this.data.push(map[uniqueId[x]])
        }


        var uniqueDate:string[] = [];
        for(let x in tmp){
            uniqueDate.push(x);
        }
      this.uniqueDate=  uniqueDate.sort((a,b)=>  {
          return a<b?1:-1;
      })
        this.totalItems=uniqueId.length;
        this.queryCount = this.totalItems;
    }

    private onError(error) {

    }

    sort() {
        const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
        // if (this.predicate !== 'id') {
        //     result.push('id');
        // }
        return result;
    }

    loadPage(page: number) {
        if (page !== this.previousPage) {
            this.previousPage = page;
            this.transition();
        }
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

    clear() {
        this.page = 0;
        this.router.navigate(['/labor-union', {
            page: this.page,
            sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
        }]);
        this.loadAll();
    }

    statementLabor(labor?: string): void {
        this.labor = labor;
        this.loadAll();
    }
}
