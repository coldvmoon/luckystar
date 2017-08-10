import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager, JhiParseLinks, JhiPaginationUtil, JhiLanguageService, JhiAlertService } from 'ng-jhipster';

import { WorkInfo } from './work-info.model';
import { WorkInfoService } from './work-info.service';
import { ITEMS_PER_PAGE, Principal, ResponseWrapper } from '../../shared';
import { PaginationConfig } from '../../blocks/config/uib-pagination.config';

@Component({
    selector: 'jhi-work-info',
    templateUrl: './work-info.component.html'
})
export class WorkInfoComponent implements OnInit, OnDestroy {
workInfos: WorkInfo[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private workInfoService: WorkInfoService,
        private alertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.workInfoService.query().subscribe(
            (res: ResponseWrapper) => {
                this.workInfos = res.json;
            },
            (res: ResponseWrapper) => this.onError(res.json)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInWorkInfos();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: WorkInfo) {
        return item.id;
    }
    registerChangeInWorkInfos() {
        this.eventSubscriber = this.eventManager.subscribe('workInfoListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.alertService.error(error.message, null, null);
    }
}
