import {Routes} from '@angular/router';

import {UserRouteAccessService} from '../../shared';
import {WorkTimeBoardComponent} from './work-time-board.component';

export const workTimeBoardRoute: Routes = [{
    path: 'work-time-board',
    component: WorkTimeBoardComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'luckystarApp.chickenInfo.home.title'
    },
    canActivate: [UserRouteAccessService]
}];
