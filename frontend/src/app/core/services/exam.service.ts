import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ApiService } from './api.service';
import { Exam, ExamRequest } from '../../models/exam.model';
import { StudySession } from '../../models/study-session.model';

@Injectable({ providedIn: 'root' })
export class ExamService {
  constructor(private api: ApiService) {}

  getAll(): Observable<Exam[]> {
    return this.api.get<Exam[]>('/exams');
  }

  create(request: ExamRequest): Observable<Exam> {
    return this.api.post<Exam>('/exams', request);
  }

  update(id: number, request: ExamRequest): Observable<Exam> {
    return this.api.put<Exam>(`/exams/${id}`, request);
  }

  delete(id: number): Observable<void> {
    return this.api.delete(`/exams/${id}`);
  }

  generateSessions(id: number): Observable<StudySession[]> {
    return this.api.post<StudySession[]>(`/exams/${id}/sessions/generate`);
  }

  getSessions(id: number): Observable<StudySession[]> {
    return this.api.get<StudySession[]>(`/exams/${id}/sessions`);
  }
}
