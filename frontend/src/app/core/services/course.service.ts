import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ApiService } from './api.service';
import { Course, CourseRequest } from '../../models/course.model';

@Injectable({ providedIn: 'root' })
export class CourseService {
  constructor(private api: ApiService) {}

  getAll(): Observable<Course[]> {
    return this.api.get<Course[]>('/courses');
  }

  create(request: CourseRequest): Observable<Course> {
    return this.api.post<Course>('/courses', request);
  }

  update(id: number, request: CourseRequest): Observable<Course> {
    return this.api.put<Course>(`/courses/${id}`, request);
  }

  delete(id: number): Observable<void> {
    return this.api.delete(`/courses/${id}`);
  }
}
