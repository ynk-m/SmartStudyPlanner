import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ApiService } from './api.service';
import { Dashboard } from '../../models/dashboard.model';

@Injectable({ providedIn: 'root' })
export class DashboardService {
  constructor(private api: ApiService) {}

  getDashboard(): Observable<Dashboard> {
    return this.api.get<Dashboard>('/dashboard');
  }
}
