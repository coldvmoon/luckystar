import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { LuckystarSharedModule } from '../shared';
import { LuckystarChickenInfoBoardModule } from './chicken-info-board/chicken-info-board.module';
import {


} from './';
import {WorkTimeBoardComponent} from "./work-time-board/work-time-board.component";
import {WorkTimeBoardService} from "./work-time-board/work-time-board.service";

@NgModule({
    imports: [
        LuckystarSharedModule,
        LuckystarChickenInfoBoardModule
        // RouterModule.forRoot(dashboardState, { useHash: true })
    ],
    declarations: [
        WorkTimeBoardComponent
    ],
    providers: [
        WorkTimeBoardService,
        WorkTimeBoardComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class LuckystarDashboardModule {}
