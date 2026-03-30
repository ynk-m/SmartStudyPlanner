export interface Exam {
  id: number;
  title: string;
  examDate: string;
  notes: string;
  courseId: number;
  courseName: string;
  daysRemaining: number;
  progress: number;
  sessionCount: number;
}

export interface ExamRequest {
  title: string;
  examDate: string;
  notes?: string;
  courseId: number;
}
