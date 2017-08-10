import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { WorkInfoComponent } from './work-info.component';
import { WorkInfoDetailComponent } from './work-info-detail.component';
import { WorkInfoPopupComponent } from './work-info-dialog.component';
import { WorkInfoDeletePopupComponent } from './work-info-delete-dialog.component';

export const workInfoRoute: Routes = [
    {
        path: 'work-info',
        component: WorkInfoComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'luckystarApp.workInfo.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'work-info/:id',
        component: WorkInfoDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'luckystarApp.workInfo.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const workInfoPopupRoute: Routes = [
    {
        path: 'work-info-new',
        component: WorkInfoPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'luckystarApp.workInfo.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'work-info/:id/edit',
        component: WorkInfoPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'luckystarApp.workInfo.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'work-info/:id/delete',
        component: WorkInfoDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'luckystarApp.workInfo.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
