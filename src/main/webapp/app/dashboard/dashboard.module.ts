import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { LuckystarSharedModule } from '../shared';

import {
    ChickenInfoBoardComponent,
    dashboardState
} from './';

@NgModule({
    imports: [
        LuckystarSharedModule,
        RouterModule.forRoot(dashboardState, { useHash: true })
    ],
    declarations: [
        ChickenInfoBoardComponent
    ],
    providers: [
        ChickenInfoBoardComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class LuckystarDashboardModule {}
