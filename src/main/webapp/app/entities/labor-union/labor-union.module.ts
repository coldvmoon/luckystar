import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { LuckystarSharedModule } from '../../shared';
import { LuckystarAdminModule } from '../../admin/admin.module';
import {
    LaborUnionService,
    LaborUnionPopupService,
    LaborUnionComponent,
    LaborUnionDetailComponent,
    LaborUnionDialogComponent,
    LaborUnionPopupComponent,
    LaborUnionDeletePopupComponent,
    LaborUnionDeleteDialogComponent,
    laborUnionRoute,
    laborUnionPopupRoute,
} from './';

const ENTITY_STATES = [
    ...laborUnionRoute,
    ...laborUnionPopupRoute,
];

@NgModule({
    imports: [
        LuckystarSharedModule,
        LuckystarAdminModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        LaborUnionComponent,
        LaborUnionDetailComponent,
        LaborUnionDialogComponent,
        LaborUnionDeleteDialogComponent,
        LaborUnionPopupComponent,
        LaborUnionDeletePopupComponent,
    ],
    entryComponents: [
        LaborUnionComponent,
        LaborUnionDialogComponent,
        LaborUnionPopupComponent,
        LaborUnionDeleteDialogComponent,
        LaborUnionDeletePopupComponent,
    ],
    providers: [
        LaborUnionService,
        LaborUnionPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class LuckystarLaborUnionModule {}
