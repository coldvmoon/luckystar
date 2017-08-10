import { Routes } from '@angular/router';

import {
    chickenInfoBoardRoute
} from './';

const DASHBOARD_ROUTES = [
    chickenInfoBoardRoute
];

export const dashboardState: Routes = [{
    path: '',
    children: DASHBOARD_ROUTES
}];
