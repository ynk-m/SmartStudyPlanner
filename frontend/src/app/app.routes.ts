import { Routes } from '@angular/router';

export const routes: Routes = [
  { path: '', redirectTo: 'dashboard', pathMatch: 'full' },
  {
    path: 'dashboard',
    loadComponent: () =>
      import('./features/dashboard/dashboard.component').then((m) => m.DashboardComponent),
  },
  {
    path: 'calendar',
    loadComponent: () =>
      import('./features/calendar/calendar.component').then((m) => m.CalendarComponent),
  },
  {
    path: 'exams',
    loadComponent: () =>
      import('./features/exams/exams.component').then((m) => m.ExamsComponent),
  },
  {
    path: 'courses',
    loadComponent: () =>
      import('./features/courses/courses.component').then((m) => m.CoursesComponent),
  },
];
