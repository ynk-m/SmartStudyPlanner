import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ApiService } from './api.service';
import { CalendarEvent } from '../../models/calendar-event.model';

@Injectable({ providedIn: 'root' })
export class CalendarService {
  constructor(private api: ApiService) {}

  getEvents(start: string, end: string): Observable<CalendarEvent[]> {
    return this.api.get<CalendarEvent[]>('/calendar/events', { start, end });
  }

  sync(): Observable<void> {
    return this.api.post<void>('/calendar/sync');
  }
}
