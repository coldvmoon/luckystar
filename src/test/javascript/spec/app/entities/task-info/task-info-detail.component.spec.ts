/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { LuckystarTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { TaskInfoDetailComponent } from '../../../../../../main/webapp/app/entities/task-info/task-info-detail.component';
import { TaskInfoService } from '../../../../../../main/webapp/app/entities/task-info/task-info.service';
import { TaskInfo } from '../../../../../../main/webapp/app/entities/task-info/task-info.model';

describe('Component Tests', () => {

    describe('TaskInfo Management Detail Component', () => {
        let comp: TaskInfoDetailComponent;
        let fixture: ComponentFixture<TaskInfoDetailComponent>;
        let service: TaskInfoService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [LuckystarTestModule],
                declarations: [TaskInfoDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    TaskInfoService,
                    JhiEventManager
                ]
            }).overrideTemplate(TaskInfoDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(TaskInfoDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TaskInfoService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new TaskInfo(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.taskInfo).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
