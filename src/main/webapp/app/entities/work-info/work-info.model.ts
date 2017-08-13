import { BaseEntity } from './../../shared';

export class WorkInfo implements BaseEntity {
    constructor(
        public id?: number,
        public starId?: number,
        public starLevel?: number,
        public starName?: string,
        public richLevel?: number,
        public richName?: string,
        public fisrtBean?: number,
        public beanTotal?: number,
        public coin?: number,
        public coinTotal?: number,
        public fansCount?: number,
        public followCount?: number,
        public experience?: number,
        public workTime?: number,
        public curMonth?: number,
        public curDay?: any,
        public lastTime?: any,
        public taskInfo?: BaseEntity,
    ) {
    }
}
