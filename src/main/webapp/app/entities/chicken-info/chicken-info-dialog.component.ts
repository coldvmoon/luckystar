import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ChickenInfo } from './chicken-info.model';
import { ChickenInfoPopupService } from './chicken-info-popup.service';
import { ChickenInfoService } from './chicken-info.service';
import { LaborUnion, LaborUnionService } from '../labor-union';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-chicken-info-dialog',
    templateUrl: './chicken-info-dialog.component.html'
})
export class ChickenInfoDialogComponent implements OnInit {

    chickenInfo: ChickenInfo;
    isSaving: boolean;

    laborunions: LaborUnion[];
    regDateDp: any;

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private chickenInfoService: ChickenInfoService,
        private laborUnionService: LaborUnionService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.laborUnionService.query()
            .subscribe((res: ResponseWrapper) => { this.laborunions = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.chickenInfo.id !== undefined) {
            this.subscribeToSaveResponse(
                this.chickenInfoService.update(this.chickenInfo));
        } else {
            this.subscribeToSaveResponse(
                this.chickenInfoService.create(this.chickenInfo));
        }
    }

    private subscribeToSaveResponse(result: Observable<ChickenInfo>) {
        result.subscribe((res: ChickenInfo) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: ChickenInfo) {
        this.eventManager.broadcast({ name: 'chickenInfoListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError(error) {
        try {
            error.json();
        } catch (exception) {
            error.message = error.text();
        }
        this.isSaving = false;
        this.onError(error);
    }

    private onError(error) {
        this.alertService.error(error.message, null, null);
    }

    trackLaborUnionById(index: number, item: LaborUnion) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-chicken-info-popup',
    template: ''
})
export class ChickenInfoPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private chickenInfoPopupService: ChickenInfoPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.chickenInfoPopupService
                    .open(ChickenInfoDialogComponent as Component, params['id']);
            } else {
                this.chickenInfoPopupService
                    .open(ChickenInfoDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
