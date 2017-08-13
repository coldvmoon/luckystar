import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';
import { UserRouteAccessService } from '../shared';

import {ChickenInfoBoardComponent} from './chicken-info-board/chicken-info-board.component';

@Injectable()
export class ChickenInfoBoardResolvePagingParams implements Resolve<any> {

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


export const dashboardState: Routes = [{
    path: 'chicken-info-board',
    component: ChickenInfoBoardComponent,
    resolve: {
        'pagingParams': ChickenInfoBoardResolvePagingParams
    },
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'luckystarApp.ChickenInfoBoard.home.title'
    },
    canActivate: [UserRouteAccessService]
}];
