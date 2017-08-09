import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager, JhiParseLinks, JhiPaginationUtil, JhiLanguageService, JhiAlertService } from 'ng-jhipster';

import { LaborUnion } from './labor-union.model';
import { LaborUnionService } from './labor-union.service';
import { ITEMS_PER_PAGE, Principal, ResponseWrapper } from '../../shared';
import { PaginationConfig } from '../../blocks/config/uib-pagination.config';

@Component({
    selector: 'jhi-labor-union',
    templateUrl: './labor-union.component.html'
})
export class LaborUnionComponent implements OnInit, OnDestroy {
laborUnions: LaborUnion[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private laborUnionService: LaborUnionService,
        private alertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.laborUnionService.query().subscribe(
            (res: ResponseWrapper) => {
                this.laborUnions = res.json;
            },
            (res: ResponseWrapper) => this.onError(res.json)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInLaborUnions();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: LaborUnion) {
        return item.id;
    }
    registerChangeInLaborUnions() {
        this.eventSubscriber = this.eventManager.subscribe('laborUnionListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.alertService.error(error.message, null, null);
    }
}
