import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { TaskInfo } from './task-info.model';
import { TaskInfoPopupService } from './task-info-popup.service';
import { TaskInfoService } from './task-info.service';

@Component({
    selector: 'jhi-task-info-delete-dialog',
    templateUrl: './task-info-delete-dialog.component.html'
})
export class TaskInfoDeleteDialogComponent {

    taskInfo: TaskInfo;

    constructor(
        private taskInfoService: TaskInfoService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.taskInfoService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'taskInfoListModification',
                content: 'Deleted an taskInfo'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-task-info-delete-popup',
    template: ''
})
export class TaskInfoDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private taskInfoPopupService: TaskInfoPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.taskInfoPopupService
                .open(TaskInfoDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
