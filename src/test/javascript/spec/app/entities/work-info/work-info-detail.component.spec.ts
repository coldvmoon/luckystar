/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { LuckystarTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { WorkInfoDetailComponent } from '../../../../../../main/webapp/app/entities/work-info/work-info-detail.component';
import { WorkInfoService } from '../../../../../../main/webapp/app/entities/work-info/work-info.service';
import { WorkInfo } from '../../../../../../main/webapp/app/entities/work-info/work-info.model';

describe('Component Tests', () => {

    describe('WorkInfo Management Detail Component', () => {
        let comp: WorkInfoDetailComponent;
        let fixture: ComponentFixture<WorkInfoDetailComponent>;
        let service: WorkInfoService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [LuckystarTestModule],
                declarations: [WorkInfoDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    WorkInfoService,
                    JhiEventManager
                ]
            }).overrideTemplate(WorkInfoDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(WorkInfoDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(WorkInfoService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new WorkInfo(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.workInfo).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
