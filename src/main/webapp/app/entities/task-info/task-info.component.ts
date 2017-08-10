import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager, JhiParseLinks, JhiPaginationUtil, JhiLanguageService, JhiAlertService } from 'ng-jhipster';

import { TaskInfo } from './task-info.model';
import { TaskInfoService } from './task-info.service';
import { ITEMS_PER_PAGE, Principal, ResponseWrapper } from '../../shared';
import { PaginationConfig } from '../../blocks/config/uib-pagination.config';

@Component({
    selector: 'jhi-task-info',
    templateUrl: './task-info.component.html'
})
export class TaskInfoComponent implements OnInit, OnDestroy {
taskInfos: TaskInfo[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private taskInfoService: TaskInfoService,
        private alertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.taskInfoService.query().subscribe(
            (res: ResponseWrapper) => {
                this.taskInfos = res.json;
            },
            (res: ResponseWrapper) => this.onError(res.json)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInTaskInfos();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: TaskInfo) {
        return item.id;
    }
    registerChangeInTaskInfos() {
        this.eventSubscriber = this.eventManager.subscribe('taskInfoListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.alertService.error(error.message, null, null);
    }
}
