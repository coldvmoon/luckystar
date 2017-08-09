/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { LuckystarTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { ChickenInfoDetailComponent } from '../../../../../../main/webapp/app/entities/chicken-info/chicken-info-detail.component';
import { ChickenInfoService } from '../../../../../../main/webapp/app/entities/chicken-info/chicken-info.service';
import { ChickenInfo } from '../../../../../../main/webapp/app/entities/chicken-info/chicken-info.model';

describe('Component Tests', () => {

    describe('ChickenInfo Management Detail Component', () => {
        let comp: ChickenInfoDetailComponent;
        let fixture: ComponentFixture<ChickenInfoDetailComponent>;
        let service: ChickenInfoService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [LuckystarTestModule],
                declarations: [ChickenInfoDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    ChickenInfoService,
                    JhiEventManager
                ]
            }).overrideTemplate(ChickenInfoDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ChickenInfoDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ChickenInfoService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new ChickenInfo(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.chickenInfo).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
