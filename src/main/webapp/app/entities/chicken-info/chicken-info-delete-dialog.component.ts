import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ChickenInfo } from './chicken-info.model';
import { ChickenInfoPopupService } from './chicken-info-popup.service';
import { ChickenInfoService } from './chicken-info.service';

@Component({
    selector: 'jhi-chicken-info-delete-dialog',
    templateUrl: './chicken-info-delete-dialog.component.html'
})
export class ChickenInfoDeleteDialogComponent {

    chickenInfo: ChickenInfo;

    constructor(
        private chickenInfoService: ChickenInfoService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.chickenInfoService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'chickenInfoListModification',
                content: 'Deleted an chickenInfo'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-chicken-info-delete-popup',
    template: ''
})
export class ChickenInfoDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private chickenInfoPopupService: ChickenInfoPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.chickenInfoPopupService
                .open(ChickenInfoDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
