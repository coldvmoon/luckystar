import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import {NgbActiveModal, NgbDatepickerI18n, NgbModalRef} from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { LaborUnion } from './labor-union.model';
import { LaborUnionPopupService } from './labor-union-popup.service';
import { LaborUnionService } from './labor-union.service';
import { User, UserService } from '../../shared';
import { ResponseWrapper } from '../../shared';
import {CustomDatepickerI18n, I18n} from "../../shared/datepicker-i18n";

@Component({
    selector: 'jhi-labor-union-dialog',
    templateUrl: './labor-union-dialog.component.html',
    providers: [I18n, {provide: NgbDatepickerI18n, useClass: CustomDatepickerI18n}]
})
export class LaborUnionDialogComponent implements OnInit {

    laborUnion: LaborUnion;
    isSaving: boolean;

    users: User[];
    regDateDp: any;

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private laborUnionService: LaborUnionService,
        private userService: UserService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.userService.query()
            .subscribe((res: ResponseWrapper) => { this.users = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.laborUnion.id !== undefined) {
            this.subscribeToSaveResponse(
                this.laborUnionService.update(this.laborUnion));
        } else {
            this.subscribeToSaveResponse(
                this.laborUnionService.create(this.laborUnion));
        }
    }

    private subscribeToSaveResponse(result: Observable<LaborUnion>) {
        result.subscribe((res: LaborUnion) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: LaborUnion) {
        this.eventManager.broadcast({ name: 'laborUnionListModification', content: 'OK'});
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

    trackUserById(index: number, item: User) {
        return item.id;
    }

    getSelected(selectedVals: Array<any>, option: any) {
        if (selectedVals) {
            for (let i = 0; i < selectedVals.length; i++) {
                if (option.id === selectedVals[i].id) {
                    return selectedVals[i];
                }
            }
        }
        return option;
    }
}

@Component({
    selector: 'jhi-labor-union-popup',
    template: ''
})
export class LaborUnionPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private laborUnionPopupService: LaborUnionPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.laborUnionPopupService
                    .open(LaborUnionDialogComponent as Component, params['id']);
            } else {
                this.laborUnionPopupService
                    .open(LaborUnionDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
