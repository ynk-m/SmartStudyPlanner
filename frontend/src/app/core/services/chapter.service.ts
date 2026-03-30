import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ApiService } from './api.service';
import { Chapter, ChapterRequest } from '../../models/chapter.model';

@Injectable({ providedIn: 'root' })
export class ChapterService {
  constructor(private api: ApiService) {}

  getByCourse(courseId: number): Observable<Chapter[]> {
    return this.api.get<Chapter[]>(`/courses/${courseId}/chapters`);
  }

  create(courseId: number, request: ChapterRequest): Observable<Chapter> {
    return this.api.post<Chapter>(`/courses/${courseId}/chapters`, request);
  }

  delete(id: number): Observable<void> {
    return this.api.delete(`/chapters/${id}`);
  }
}
