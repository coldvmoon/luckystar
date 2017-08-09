import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager  } from 'ng-jhipster';

import { TaskInfo } from './task-info.model';
import { TaskInfoService } from './task-info.service';

@Component({
    selector: 'jhi-task-info-detail',
    templateUrl: './task-info-detail.component.html'
})
export class TaskInfoDetailComponent implements OnInit, OnDestroy {

    taskInfo: TaskInfo;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private taskInfoService: TaskInfoService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInTaskInfos();
    }

    load(id) {
        this.taskInfoService.find(id).subscribe((taskInfo) => {
            this.taskInfo = taskInfo;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInTaskInfos() {
        this.eventSubscriber = this.eventManager.subscribe(
            'taskInfoListModification',
            (response) => this.load(this.taskInfo.id)
        );
    }
}
