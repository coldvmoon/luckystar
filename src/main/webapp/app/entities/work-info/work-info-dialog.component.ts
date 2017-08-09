import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { WorkInfo } from './work-info.model';
import { WorkInfoPopupService } from './work-info-popup.service';
import { WorkInfoService } from './work-info.service';

@Component({
    selector: 'jhi-work-info-dialog',
    templateUrl: './work-info-dialog.component.html'
})
export class WorkInfoDialogComponent implements OnInit {

    workInfo: WorkInfo;
    isSaving: boolean;
    curDayDp: any;
    lastTimeDp: any;

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private workInfoService: WorkInfoService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.workInfo.id !== undefined) {
            this.subscribeToSaveResponse(
                this.workInfoService.update(this.workInfo));
        } else {
            this.subscribeToSaveResponse(
                this.workInfoService.create(this.workInfo));
        }
    }

    private subscribeToSaveResponse(result: Observable<WorkInfo>) {
        result.subscribe((res: WorkInfo) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: WorkInfo) {
        this.eventManager.broadcast({ name: 'workInfoListModification', content: 'OK'});
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
}

@Component({
    selector: 'jhi-work-info-popup',
    template: ''
})
export class WorkInfoPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private workInfoPopupService: WorkInfoPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.workInfoPopupService
                    .open(WorkInfoDialogComponent as Component, params['id']);
            } else {
                this.workInfoPopupService
                    .open(WorkInfoDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
