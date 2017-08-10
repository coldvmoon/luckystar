import { BaseEntity, User } from './../../shared';

const enum State {
    'OFF',
    'ON'
}

export class LaborUnion implements BaseEntity {
    constructor(
        public id?: number,
        public lId?: number,
        public name?: string,
        public regDate?: any,
        public state?: State,
        public chickenInfos?: BaseEntity[],
        public users?: User[],
    ) {
    }
}
