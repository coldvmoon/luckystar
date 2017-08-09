import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { LuckystarSharedModule } from '../../shared';
import {
    TaskInfoService,
    TaskInfoPopupService,
    TaskInfoComponent,
    TaskInfoDetailComponent,
    TaskInfoDialogComponent,
    TaskInfoPopupComponent,
    TaskInfoDeletePopupComponent,
    TaskInfoDeleteDialogComponent,
    taskInfoRoute,
    taskInfoPopupRoute,
    TaskInfoResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...taskInfoRoute,
    ...taskInfoPopupRoute,
];

@NgModule({
    imports: [
        LuckystarSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        TaskInfoComponent,
        TaskInfoDetailComponent,
        TaskInfoDialogComponent,
        TaskInfoDeleteDialogComponent,
        TaskInfoPopupComponent,
        TaskInfoDeletePopupComponent,
    ],
    entryComponents: [
        TaskInfoComponent,
        TaskInfoDialogComponent,
        TaskInfoPopupComponent,
        TaskInfoDeleteDialogComponent,
        TaskInfoDeletePopupComponent,
    ],
    providers: [
        TaskInfoService,
        TaskInfoPopupService,
        TaskInfoResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class LuckystarTaskInfoModule {}
