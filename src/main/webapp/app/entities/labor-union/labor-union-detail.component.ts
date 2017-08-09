import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager  } from 'ng-jhipster';

import { LaborUnion } from './labor-union.model';
import { LaborUnionService } from './labor-union.service';

@Component({
    selector: 'jhi-labor-union-detail',
    templateUrl: './labor-union-detail.component.html'
})
export class LaborUnionDetailComponent implements OnInit, OnDestroy {

    laborUnion: LaborUnion;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private laborUnionService: LaborUnionService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInLaborUnions();
    }

    load(id) {
        this.laborUnionService.find(id).subscribe((laborUnion) => {
            this.laborUnion = laborUnion;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInLaborUnions() {
        this.eventSubscriber = this.eventManager.subscribe(
            'laborUnionListModification',
            (response) => this.load(this.laborUnion.id)
        );
    }
}
