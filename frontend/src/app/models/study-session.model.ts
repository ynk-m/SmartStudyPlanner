export type SessionStatus = 'SCHEDULED' | 'COMPLETED' | 'SKIPPED';
export type SessionFeedback = 'GOOD' | 'MID' | 'BAD';

export interface StudySession {
  id: number;
  startTime: string;
  endTime: string;
  status: SessionStatus;
  feedback: SessionFeedback | null;
  subchapterId: number;
  subchapterName: string;
  examId: number | null;
  examTitle: string | null;
  courseName: string;
}

export interface SessionCompleteRequest {
  feedback: SessionFeedback;
}

export interface SessionUpdateRequest {
  startTime?: string;
  endTime?: string;
}
