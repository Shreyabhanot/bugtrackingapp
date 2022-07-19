import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListBugComponent } from './list-bug.component';

describe('ListBugComponent', () => {
  let component: ListBugComponent;
  let fixture: ComponentFixture<ListBugComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListBugComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ListBugComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
