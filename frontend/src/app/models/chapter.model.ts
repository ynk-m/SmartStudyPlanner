import { Subchapter } from './subchapter.model';

export interface Chapter {
  id: number;
  name: string;
  courseId: number;
  progress: number;
  subchapters: Subchapter[];
}

export interface ChapterRequest {
  name: string;
}
