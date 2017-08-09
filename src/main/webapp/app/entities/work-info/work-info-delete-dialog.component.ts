import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { WorkInfo } from './work-info.model';
import { WorkInfoPopupService } from './work-info-popup.service';
import { WorkInfoService } from './work-info.service';

@Component({
    selector: 'jhi-work-info-delete-dialog',
    templateUrl: './work-info-delete-dialog.component.html'
})
export class WorkInfoDeleteDialogComponent {

    workInfo: WorkInfo;

    constructor(
        private workInfoService: WorkInfoService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.workInfoService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'workInfoListModification',
                content: 'Deleted an workInfo'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-work-info-delete-popup',
    template: ''
})
export class WorkInfoDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private workInfoPopupService: WorkInfoPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.workInfoPopupService
                .open(WorkInfoDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
