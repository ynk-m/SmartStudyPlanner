export interface Subchapter {
  id: number;
  name: string;
  chapterId: number;
  estimatedMinutes: number;
  readinessScore: number;
  nextReviewAt: string | null;
}

export interface SubchapterRequest {
  name: string;
  estimatedMinutes: number;
}
