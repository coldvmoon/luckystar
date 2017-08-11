import { Routes } from '@angular/router';

import {
    chickenInfoBoardRoute,
    workTimeBoardRoute
} from './';

const DASHBOARD_ROUTES = [
    chickenInfoBoardRoute,
    workTimeBoardRoute
];

export const dashboardState: Routes = [{
    path: '',
    children: DASHBOARD_ROUTES
}];
