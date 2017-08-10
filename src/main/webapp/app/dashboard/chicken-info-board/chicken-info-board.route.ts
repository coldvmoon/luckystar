import {Route} from '@angular/router';

import {UserRouteAccessService} from '../../shared';
import {ChickenInfoBoardComponent} from './chicken-info-board.component';

export const chickenInfoBoardRoute: Route = {
    path: 'chicken-info-board',
    component: ChickenInfoBoardComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'luckystarApp.chickenInfo.home.title'
    },
    canActivate: [UserRouteAccessService]
};
