import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ApiService } from './api.service';
import {
  StudySession,
  SessionCompleteRequest,
  SessionUpdateRequest,
} from '../../models/study-session.model';

@Injectable({ providedIn: 'root' })
export class StudySessionService {
  constructor(private api: ApiService) {}

  getByDateRange(start: string, end: string): Observable<StudySession[]> {
    return this.api.get<StudySession[]>('/sessions', { start, end });
  }

  update(id: number, request: SessionUpdateRequest): Observable<StudySession> {
    return this.api.patch<StudySession>(`/sessions/${id}`, request);
  }

  complete(id: number, request: SessionCompleteRequest): Observable<StudySession> {
    return this.api.post<StudySession>(`/sessions/${id}/complete`, request);
  }
}
