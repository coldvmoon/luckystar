import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { LuckystarSharedModule } from '../../shared';
import {
    ChickenInfoService,
    ChickenInfoPopupService,
    ChickenInfoComponent,
    ChickenInfoDetailComponent,
    ChickenInfoDialogComponent,
    ChickenInfoPopupComponent,
    ChickenInfoDeletePopupComponent,
    ChickenInfoDeleteDialogComponent,
    chickenInfoRoute,
    chickenInfoPopupRoute,
    ChickenInfoResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...chickenInfoRoute,
    ...chickenInfoPopupRoute,
];

@NgModule({
    imports: [
        LuckystarSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        ChickenInfoComponent,
        ChickenInfoDetailComponent,
        ChickenInfoDialogComponent,
        ChickenInfoDeleteDialogComponent,
        ChickenInfoPopupComponent,
        ChickenInfoDeletePopupComponent,
    ],
    entryComponents: [
        ChickenInfoComponent,
        ChickenInfoDialogComponent,
        ChickenInfoPopupComponent,
        ChickenInfoDeleteDialogComponent,
        ChickenInfoDeletePopupComponent,
    ],
    providers: [
        ChickenInfoService,
        ChickenInfoPopupService,
        ChickenInfoResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class LuckystarChickenInfoModule {}
