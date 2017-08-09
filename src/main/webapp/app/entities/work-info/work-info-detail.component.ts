import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager  } from 'ng-jhipster';

import { WorkInfo } from './work-info.model';
import { WorkInfoService } from './work-info.service';

@Component({
    selector: 'jhi-work-info-detail',
    templateUrl: './work-info-detail.component.html'
})
export class WorkInfoDetailComponent implements OnInit, OnDestroy {

    workInfo: WorkInfo;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private workInfoService: WorkInfoService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInWorkInfos();
    }

    load(id) {
        this.workInfoService.find(id).subscribe((workInfo) => {
            this.workInfo = workInfo;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInWorkInfos() {
        this.eventSubscriber = this.eventManager.subscribe(
            'workInfoListModification',
            (response) => this.load(this.workInfo.id)
        );
    }
}
