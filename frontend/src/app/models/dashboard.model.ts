import { Exam } from './exam.model';
import { StudySession } from './study-session.model';

export interface Dashboard {
  upcomingExams: Exam[];
  todaySessions: StudySession[];
  weeklyStudyHours: number;
  completedSessionsThisWeek: number;
  totalScheduledSessionsThisWeek: number;
}
