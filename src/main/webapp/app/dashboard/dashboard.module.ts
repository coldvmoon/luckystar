import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { LuckystarSharedModule } from '../shared';

import {
    ChickenInfoBoardService,
    ChickenInfoBoardComponent,
    dashboardState
} from './';
import {WorkTimeBoardComponent} from "./work-time-board/work-time-board.component";
import {WorkTimeBoardService} from "./work-time-board/work-time-board.service";

@NgModule({
    imports: [
        LuckystarSharedModule,
        RouterModule.forRoot(dashboardState, { useHash: true })
    ],
    declarations: [
        ChickenInfoBoardComponent,
        WorkTimeBoardComponent
    ],
    providers: [
        ChickenInfoBoardService,
        ChickenInfoBoardComponent,
        WorkTimeBoardService,
        WorkTimeBoardComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class LuckystarDashboardModule {}
