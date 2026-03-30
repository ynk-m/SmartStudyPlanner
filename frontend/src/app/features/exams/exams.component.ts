import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ExamService } from '../../core/services/exam.service';
import { Exam } from '../../models/exam.model';

@Component({
  selector: 'app-exams',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './exams.component.html',
})
export class ExamsComponent implements OnInit {
  exams: Exam[] = [];

  constructor(private examService: ExamService) {}

  ngOnInit(): void {
    this.examService.getAll().subscribe((data) => {
      this.exams = data;
    });
  }

  generateSessions(examId: number): void {
    this.examService.generateSessions(examId).subscribe(() => {
      // Refresh exams to show updated session counts
      this.ngOnInit();
    });
  }
}
