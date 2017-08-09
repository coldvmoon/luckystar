import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { ChickenInfo } from './chicken-info.model';
import { ChickenInfoService } from './chicken-info.service';

@Injectable()
export class ChickenInfoPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private chickenInfoService: ChickenInfoService

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
                this.chickenInfoService.find(id).subscribe((chickenInfo) => {
                    if (chickenInfo.regDate) {
                        chickenInfo.regDate = {
                            year: chickenInfo.regDate.getFullYear(),
                            month: chickenInfo.regDate.getMonth() + 1,
                            day: chickenInfo.regDate.getDate()
                        };
                    }
                    this.ngbModalRef = this.chickenInfoModalRef(component, chickenInfo);
                    resolve(this.ngbModalRef);
                });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.chickenInfoModalRef(component, new ChickenInfo());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    chickenInfoModalRef(component: Component, chickenInfo: ChickenInfo): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.chickenInfo = chickenInfo;
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
