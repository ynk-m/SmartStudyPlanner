CREATE TABLE course (
    id              BIGSERIAL PRIMARY KEY,
    name            VARCHAR(255) NOT NULL,
    description     TEXT,
    min_session_minutes   INT NOT NULL DEFAULT 25,
    max_session_minutes   INT NOT NULL DEFAULT 120,
    break_minutes         INT NOT NULL DEFAULT 10,
    preferred_start_time  TIME NOT NULL DEFAULT '08:00',
    preferred_end_time    TIME NOT NULL DEFAULT '20:00'
);

CREATE TABLE chapter (
    id        BIGSERIAL PRIMARY KEY,
    name      VARCHAR(255) NOT NULL,
    course_id BIGINT NOT NULL REFERENCES course(id) ON DELETE CASCADE
);

CREATE TABLE subchapter (
    id                 BIGSERIAL PRIMARY KEY,
    name               VARCHAR(255) NOT NULL,
    estimated_minutes  INT NOT NULL,
    readiness_score    DOUBLE PRECISION NOT NULL DEFAULT 0.0,
    next_review_at     TIMESTAMP,
    chapter_id         BIGINT NOT NULL REFERENCES chapter(id) ON DELETE CASCADE
);

CREATE TABLE exam (
    id        BIGSERIAL PRIMARY KEY,
    title     VARCHAR(255) NOT NULL,
    exam_date TIMESTAMP NOT NULL,
    notes     TEXT,
    course_id BIGINT NOT NULL REFERENCES course(id) ON DELETE CASCADE
);

CREATE TABLE study_session (
    id             BIGSERIAL PRIMARY KEY,
    start_time     TIMESTAMP NOT NULL,
    end_time       TIMESTAMP NOT NULL,
    status         VARCHAR(20) NOT NULL DEFAULT 'SCHEDULED',
    feedback       VARCHAR(10),
    subchapter_id  BIGINT NOT NULL REFERENCES subchapter(id) ON DELETE CASCADE,
    exam_id        BIGINT REFERENCES exam(id) ON DELETE SET NULL
);

CREATE TABLE calendar_event (
    id              BIGSERIAL PRIMARY KEY,
    google_event_id VARCHAR(255) NOT NULL UNIQUE,
    title           VARCHAR(500) NOT NULL,
    start_time      TIMESTAMP NOT NULL,
    end_time        TIMESTAMP NOT NULL,
    last_synced_at  TIMESTAMP NOT NULL
);

-- Indexes for common queries
CREATE INDEX idx_chapter_course ON chapter(course_id);
CREATE INDEX idx_subchapter_chapter ON subchapter(chapter_id);
CREATE INDEX idx_subchapter_readiness ON subchapter(readiness_score, next_review_at);
CREATE INDEX idx_exam_course ON exam(course_id);
CREATE INDEX idx_exam_date ON exam(exam_date);
CREATE INDEX idx_session_time ON study_session(start_time, end_time);
CREATE INDEX idx_session_subchapter ON study_session(subchapter_id);
CREATE INDEX idx_session_exam ON study_session(exam_id);
CREATE INDEX idx_session_status ON study_session(status);
CREATE INDEX idx_calendar_event_time ON calendar_event(start_time, end_time);
CREATE INDEX idx_calendar_event_google ON calendar_event(google_event_id);
