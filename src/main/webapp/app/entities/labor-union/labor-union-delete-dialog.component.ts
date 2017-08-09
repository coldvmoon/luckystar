import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { LaborUnion } from './labor-union.model';
import { LaborUnionPopupService } from './labor-union-popup.service';
import { LaborUnionService } from './labor-union.service';

@Component({
    selector: 'jhi-labor-union-delete-dialog',
    templateUrl: './labor-union-delete-dialog.component.html'
})
export class LaborUnionDeleteDialogComponent {

    laborUnion: LaborUnion;

    constructor(
        private laborUnionService: LaborUnionService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.laborUnionService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'laborUnionListModification',
                content: 'Deleted an laborUnion'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-labor-union-delete-popup',
    template: ''
})
export class LaborUnionDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private laborUnionPopupService: LaborUnionPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.laborUnionPopupService
                .open(LaborUnionDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
