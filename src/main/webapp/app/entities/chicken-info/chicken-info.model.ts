import { BaseEntity } from './../../shared';

const enum State {
    'ON',
    'OFF'
}

export class ChickenInfo implements BaseEntity {
    constructor(
        public id?: number,
        public userName?: string,
        public nickName?: string,
        public starId?: number,
        public regDate?: any,
        public cookie?: string,
        public timeRate?: number,
        public beanRate?: number,
        public state?: State,
        public taskInfos?: BaseEntity[],
        public laborUnion?: BaseEntity,
    ) {
    }
}
