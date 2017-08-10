import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { TaskInfoComponent } from './task-info.component';
import { TaskInfoDetailComponent } from './task-info-detail.component';
import { TaskInfoPopupComponent } from './task-info-dialog.component';
import { TaskInfoDeletePopupComponent } from './task-info-delete-dialog.component';

export const taskInfoRoute: Routes = [
    {
        path: 'task-info',
        component: TaskInfoComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'luckystarApp.taskInfo.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'task-info/:id',
        component: TaskInfoDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'luckystarApp.taskInfo.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const taskInfoPopupRoute: Routes = [
    {
        path: 'task-info-new',
        component: TaskInfoPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'luckystarApp.taskInfo.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'task-info/:id/edit',
        component: TaskInfoPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'luckystarApp.taskInfo.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'task-info/:id/delete',
        component: TaskInfoDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'luckystarApp.taskInfo.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
