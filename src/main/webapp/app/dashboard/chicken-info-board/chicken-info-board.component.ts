import {Component, OnInit, OnDestroy} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {Subscription} from 'rxjs/Rx';
import {JhiEventManager, JhiParseLinks, JhiPaginationUtil, JhiLanguageService, JhiAlertService} from 'ng-jhipster';

import {LaborUnionBoard} from './chicken-info-board.model';
import {ChickenInfoBoardService} from './chicken-info-board.service';
import {ITEMS_PER_PAGE, Principal, ResponseWrapper} from '../../shared';
import {PaginationConfig} from '../../blocks/config/uib-pagination.config';
import {BaseEntity} from "../../shared/model/base-entity";

@Component({
    selector: 'jhi-labor-union',
    templateUrl: './chicken-info-board.component.html'
})
export class ChickenInfoBoardComponent implements OnInit, OnDestroy {

    currentAccount: any;
    laborUnionBoards: LaborUnionBoard[];
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
    recentTime: any;
    day: any;
    userName: string;
    nickName: string;
    starId: string;
    phoneNumber: string;
    qq: string;
    weiChat: string;

    constructor(private chickenInfoBoardService: ChickenInfoBoardService,
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
        this.chickenInfoBoardService.query({
            query: {
                day: this.day,
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

    ngOnInit() {
        this.chickenInfoBoardService.recentTime().subscribe(
            (res: ResponseWrapper) => this.onSuccess1(res, res.headers),
            (res: ResponseWrapper) => this.onError(res.json)
        );


    }

    private onSuccess1(data, headers) {
        this.recentTime = data.json()
        this.day = this.recentTime[0];
        this.loadAll();

    }

    ngOnDestroy() {
        // this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: LaborUnionBoard) {
        return item.id;
    }

    registerChangeInLaborUnions() {
        this.eventSubscriber = this.eventManager.subscribe('laborUnionListModification', (response) => this.loadAll());
    }

    sort() {
        const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
        if (this.predicate !== 'id') {
            result.push('id');
        }
        return result;
    }

    private onSuccess(data, headers) {
        this.links = this.parseLinks.parse(headers.get('link'));
        this.totalItems = headers.get('X-Total-Count');
        this.queryCount = this.totalItems;
        // this.page = pagingParams.page;
        this.laborUnionBoards = data;
    }

    private onError(error) {
        this.alertService.error(error.message, null, null);
    }

    statement(day?: string): void {
        this.day = day;
        this.loadAll();
    }

}


