import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { LuckystarSharedModule } from '../../shared';
import {
    WorkInfoService,
    WorkInfoPopupService,
    WorkInfoComponent,
    WorkInfoDetailComponent,
    WorkInfoDialogComponent,
    WorkInfoPopupComponent,
    WorkInfoDeletePopupComponent,
    WorkInfoDeleteDialogComponent,
    workInfoRoute,
    workInfoPopupRoute,
} from './';

const ENTITY_STATES = [
    ...workInfoRoute,
    ...workInfoPopupRoute,
];

@NgModule({
    imports: [
        LuckystarSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        WorkInfoComponent,
        WorkInfoDetailComponent,
        WorkInfoDialogComponent,
        WorkInfoDeleteDialogComponent,
        WorkInfoPopupComponent,
        WorkInfoDeletePopupComponent,
    ],
    entryComponents: [
        WorkInfoComponent,
        WorkInfoDialogComponent,
        WorkInfoPopupComponent,
        WorkInfoDeleteDialogComponent,
        WorkInfoDeletePopupComponent,
    ],
    providers: [
        WorkInfoService,
        WorkInfoPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class LuckystarWorkInfoModule {}
