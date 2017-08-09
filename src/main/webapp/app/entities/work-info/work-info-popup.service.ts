import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { WorkInfo } from './work-info.model';
import { WorkInfoService } from './work-info.service';

@Injectable()
export class WorkInfoPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private workInfoService: WorkInfoService

    ) {
        this.ngbModalRef = null;
    }

    open(component: Component, id?: number | any): Promise<NgbModalRef> {
        return new Promise<NgbModalRef>((resolve, reject) => {
            const isOpen = this.ngbModalRef !== null;
            if (isOpen) {
                resolve(this.ngbModalRef);
            }

            if (id) {
                this.workInfoService.find(id).subscribe((workInfo) => {
                    if (workInfo.curDay) {
                        workInfo.curDay = {
                            year: workInfo.curDay.getFullYear(),
                            month: workInfo.curDay.getMonth() + 1,
                            day: workInfo.curDay.getDate()
                        };
                    }
                    if (workInfo.lastTime) {
                        workInfo.lastTime = {
                            year: workInfo.lastTime.getFullYear(),
                            month: workInfo.lastTime.getMonth() + 1,
                            day: workInfo.lastTime.getDate()
                        };
                    }
                    this.ngbModalRef = this.workInfoModalRef(component, workInfo);
                    resolve(this.ngbModalRef);
                });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.workInfoModalRef(component, new WorkInfo());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    workInfoModalRef(component: Component, workInfo: WorkInfo): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.workInfo = workInfo;
        modalRef.result.then((result) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true });
            this.ngbModalRef = null;
        }, (reason) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true });
            this.ngbModalRef = null;
        });
        return modalRef;
    }
}
