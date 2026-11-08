# Sainik Prep — AISSEE preparation app

Jetpack Compose app for the All India Sainik Schools Entrance Exam (Class 6 and Class 9 entry).

## Features
- **Two tracks**: Class 6 entry (Class 5 syllabus: Maths, Intelligence, Language, GK) and Class 9 entry (Class 8 NCERT: Maths, Intelligence, English, General Science, Social Science).
- **Learn**: subject → chapter notes (key points + worked examples), with *Previous/Next chapter* links.
- **Practice**: chapter quizzes that show the answer and explanation as soon as you pick an option. Maths and reasoning chapters use parametric generators (`data/content/Generators.kt`), so every attempt gives new questions with step-by-step solutions.
- **Mock tests**: 10 full mocks per class that follow the official pattern (125 Qs/300 marks/150 min and 150 Qs/400 marks/180 min). Each has a timer, sections, a question palette, mark-for-review and a qualifying check (≥25% per subject, ≥40% overall). There are also sectional tests.
- **Previous years**: 2016–2025 practice papers for every subject. You can attempt a paper or view its worked solutions.
- **Results**: the last 5 attempts are saved, with a progress chart and subject-wise analysis, and you can review every answer.
- **My Notes**: add, edit and delete notes, pin them, tag them by subject and search them.
- **Study Planner**: weekly timetable with exact alarms (`alarm/`). Alarms are re-armed after reboot, and tapping the notification opens the planner.
- **Syllabus & Admission**: eligibility, exam pattern, full syllabus, the 12-step admission process, dates, fees, reservation, documents, the 33 Sainik Schools, tips and FAQs (`data/content/AdmissionInfo.kt`, last reviewed Oct 2026).
- **Breadcrumbs** on every screen, plus a Home button in the top bar.

## Content notes
- The 2027 exam dates are *expected*. Update `AdmissionInfo.kt` once NTA publishes the 2027 information bulletin.
- The year-wise papers follow each year's pattern and use commonly asked topics. They are **not** the official question papers. To add the official papers, upload them to Firestore (see below).

## Firestore (to configure later)
1. Put `google-services.json` in `app/`, then add the `com.google.gms.google-services` plugin and the Firebase BoM (firestore, auth) to `app/build.gradle.kts`.
2. Implement `ResultRepository`, `NotesRepository` and `ScheduleRepository` (`data/store/Stores.kt`) with Firestore, using for example `users/{uid}/results`, `users/{uid}/notes` and `users/{uid}/schedules`. Then swap them in `AppGraph.init`.
3. Optional: store official papers in `papers/{id}` (with `questions[]` in the `Question` shape) and merge them in `ContentRepository.paper()`.

## Tests
`./gradlew testDebugUnitTest` checks every generator (2,000 seeds each), all curated questions, mock-paper patterns and all year-wise papers.
