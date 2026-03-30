---
name: project_smartstudyplanner
description: Smart Study Planner - single-user study tracker with spaced repetition, readiness scoring, and Google Calendar sync
type: project
---

Single-user web app for tracking study progress across multiple exams. Core features:
- **Spaced repetition** with readiness scoring (0.0-1.0) per subchapter, driven by session feedback (GOOD/MID/BAD)
- **Google Calendar integration** — cached in PostgreSQL, periodic (15 min) + on-demand sync, used to avoid scheduling conflicts
- **Per-course study constraints** — min/max session length, break time, preferred hours
- **Scheduling algorithm** — generates sessions respecting free time, exam proximity, and readiness scores
- **Visualization** — calendar view with color-coded blocks, sidebar with exam progress, detail panel with chapter/subchapter breakdown, dashboard charts

**Why:** User wants to avoid distraction and confusion when studying for multiple exams simultaneously.
**How to apply:** All architecture decisions should prioritize the scheduling engine and readiness scoring as the core differentiator. No auth needed — single user.
