import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { LaborUnionComponent } from './labor-union.component';
import { LaborUnionDetailComponent } from './labor-union-detail.component';
import { LaborUnionPopupComponent } from './labor-union-dialog.component';
import { LaborUnionDeletePopupComponent } from './labor-union-delete-dialog.component';

export const laborUnionRoute: Routes = [
    {
        path: 'labor-union',
        component: LaborUnionComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'luckystarApp.laborUnion.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'labor-union/:id',
        component: LaborUnionDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'luckystarApp.laborUnion.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const laborUnionPopupRoute: Routes = [
    {
        path: 'labor-union-new',
        component: LaborUnionPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'luckystarApp.laborUnion.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'labor-union/:id/edit',
        component: LaborUnionPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'luckystarApp.laborUnion.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'labor-union/:id/delete',
        component: LaborUnionDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'luckystarApp.laborUnion.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
