import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager  } from 'ng-jhipster';

import { ChickenInfo } from './chicken-info.model';
import { ChickenInfoService } from './chicken-info.service';

@Component({
    selector: 'jhi-chicken-info-detail',
    templateUrl: './chicken-info-detail.component.html'
})
export class ChickenInfoDetailComponent implements OnInit, OnDestroy {

    chickenInfo: ChickenInfo;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private chickenInfoService: ChickenInfoService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInChickenInfos();
    }

    load(id) {
        this.chickenInfoService.find(id).subscribe((chickenInfo) => {
            this.chickenInfo = chickenInfo;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInChickenInfos() {
        this.eventSubscriber = this.eventManager.subscribe(
            'chickenInfoListModification',
            (response) => this.load(this.chickenInfo.id)
        );
    }
}
