import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ApiService } from './api.service';
import { Subchapter, SubchapterRequest } from '../../models/subchapter.model';

@Injectable({ providedIn: 'root' })
export class SubchapterService {
  constructor(private api: ApiService) {}

  getByChapter(chapterId: number): Observable<Subchapter[]> {
    return this.api.get<Subchapter[]>(`/chapters/${chapterId}/subchapters`);
  }

  create(chapterId: number, request: SubchapterRequest): Observable<Subchapter> {
    return this.api.post<Subchapter>(`/chapters/${chapterId}/subchapters`, request);
  }

  update(id: number, request: SubchapterRequest): Observable<Subchapter> {
    return this.api.patch<Subchapter>(`/subchapters/${id}`, request);
  }

  delete(id: number): Observable<void> {
    return this.api.delete(`/subchapters/${id}`);
  }
}
