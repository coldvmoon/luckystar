/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { LuckystarTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { LaborUnionDetailComponent } from '../../../../../../main/webapp/app/entities/labor-union/labor-union-detail.component';
import { LaborUnionService } from '../../../../../../main/webapp/app/entities/labor-union/labor-union.service';
import { LaborUnion } from '../../../../../../main/webapp/app/entities/labor-union/labor-union.model';

describe('Component Tests', () => {

    describe('LaborUnion Management Detail Component', () => {
        let comp: LaborUnionDetailComponent;
        let fixture: ComponentFixture<LaborUnionDetailComponent>;
        let service: LaborUnionService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [LuckystarTestModule],
                declarations: [LaborUnionDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    LaborUnionService,
                    JhiEventManager
                ]
            }).overrideTemplate(LaborUnionDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(LaborUnionDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(LaborUnionService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new LaborUnion(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.laborUnion).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
