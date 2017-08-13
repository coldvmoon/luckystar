import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { LuckystarLaborUnionModule } from './labor-union/labor-union.module';
import { LuckystarUserInfoModule } from './user-info/user-info.module';
import { LuckystarTaskInfoModule } from './task-info/task-info.module';
import { LuckystarWorkInfoModule } from './work-info/work-info.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    imports: [
        LuckystarLaborUnionModule,
        LuckystarUserInfoModule,
        LuckystarTaskInfoModule,
        LuckystarWorkInfoModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class LuckystarEntityModule {}
