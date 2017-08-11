import {Route} from '@angular/router';

import {UserRouteAccessService} from '../../shared';
import {WorkTimeBoardComponent} from './work-time-board.component';

export const workTimeBoardRoute: Route = {
    path: 'work-time-board',
    component: WorkTimeBoardComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'luckystarApp.chickenInfo.home.title'
    },
    canActivate: [UserRouteAccessService]
};
