export interface Course {
  id: number;
  name: string;
  description: string;
  minSessionMinutes: number;
  maxSessionMinutes: number;
  breakMinutes: number;
  preferredStartTime: string;
  preferredEndTime: string;
  progress: number;
  chapterCount: number;
}

export interface CourseRequest {
  name: string;
  description?: string;
  minSessionMinutes?: number;
  maxSessionMinutes?: number;
  breakMinutes?: number;
  preferredStartTime?: string;
  preferredEndTime?: string;
}
