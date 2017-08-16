import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { LuckystarSharedModule } from '../../shared';
import { LuckystarAdminModule } from '../../admin/admin.module';
import {
    workTimeBoardRoute,
    WorkTimeBoardComponent,
    WorkTimeBoardService,
    WorkTimeBoardResolvePagingParams
    // ChickenInfoBoardService,
    // ChickenInfoBoardComponent,
    // ChickenInfoBoardRoute,
    // ChickenInfoBoardResolvePagingParams
} from './';

const ENTITY_STATES = [
    ...workTimeBoardRoute
];

@NgModule({
    imports: [
        // ChickenInfoBoardService,
        // ChickenInfoBoardComponent,
        LuckystarSharedModule,
        // LuckystarAdminModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        // ChickenInfoBoardService,
        WorkTimeBoardComponent
    ],
    entryComponents: [
        // ChickenInfoBoardService,
        WorkTimeBoardComponent
    ],
    providers: [
        WorkTimeBoardService,
        WorkTimeBoardResolvePagingParams
        // ChickenInfoBoardService,
        // ChickenInfoBoardComponent
        // ChickenInfoBoardResolvePagingParams,
        // ChickenInfoBoardService
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class LuckystarWorkTimeBoardModule {}
