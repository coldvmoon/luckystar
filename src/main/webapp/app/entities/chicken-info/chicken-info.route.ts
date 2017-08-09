import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { ChickenInfoComponent } from './chicken-info.component';
import { ChickenInfoDetailComponent } from './chicken-info-detail.component';
import { ChickenInfoPopupComponent } from './chicken-info-dialog.component';
import { ChickenInfoDeletePopupComponent } from './chicken-info-delete-dialog.component';

@Injectable()
export class ChickenInfoResolvePagingParams implements Resolve<any> {

    constructor(private paginationUtil: JhiPaginationUtil) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const page = route.queryParams['page'] ? route.queryParams['page'] : '1';
        const sort = route.queryParams['sort'] ? route.queryParams['sort'] : 'id,asc';
        return {
            page: this.paginationUtil.parsePage(page),
            predicate: this.paginationUtil.parsePredicate(sort),
            ascending: this.paginationUtil.parseAscending(sort)
      };
    }
}

export const chickenInfoRoute: Routes = [
    {
        path: 'chicken-info',
        component: ChickenInfoComponent,
        resolve: {
            'pagingParams': ChickenInfoResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'luckystarApp.chickenInfo.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'chicken-info/:id',
        component: ChickenInfoDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'luckystarApp.chickenInfo.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const chickenInfoPopupRoute: Routes = [
    {
        path: 'chicken-info-new',
        component: ChickenInfoPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'luckystarApp.chickenInfo.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'chicken-info/:id/edit',
        component: ChickenInfoPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'luckystarApp.chickenInfo.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'chicken-info/:id/delete',
        component: ChickenInfoDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'luckystarApp.chickenInfo.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
