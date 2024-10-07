import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RequestChartComponent } from './request-chart.component';

describe('RequestChartComponent', () => {
  let component: RequestChartComponent;
  let fixture: ComponentFixture<RequestChartComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [RequestChartComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(RequestChartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
