import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-calendar',
  standalone: true,
  imports: [CommonModule],
  template: `
    <div class="bg-white rounded-lg shadow p-6">
      <h2 class="text-lg font-semibold mb-4">Calendar</h2>
      <p class="text-gray-500">Calendar view with study sessions and Google Calendar events will be implemented here.</p>
    </div>
  `,
})
export class CalendarComponent {}
