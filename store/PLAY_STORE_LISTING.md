# Play Store listing: Sainik School Exam Prep

## Main store listing (English, en-IN, set as default)

| Field | Text | Length |
|---|---|---|
| **App name** | `Sainik School Exam Prep AISSEE` | 30/30 |
| **Short description** | `AISSEE 2027 Class 6 & 9 mock tests, previous-year pattern papers, notes, planner` | 80/80 |
| **Full description** | see `full_description.txt` | ~3,800/4,000 |

Name on the phone's home screen (launcher label): **Sainik Prep**, already set in `strings.xml`. Keep it short so it isn't cut off under the icon.

### Why this name ranks
Parents and students search in this order: **"sainik school"** (by far the biggest term), then "sainik school exam", "sainik school entrance exam", "AISSEE", "sainik school class 6", "sainik school mock test" and "sainik school previous year paper". Hindi users search "सैनिक स्कूल".

- The title puts the highest-volume phrase, **Sainik School**, first, followed by **Exam**, **Prep** and **AISSEE**. Google Play gives the title the most weight for ranking.
- The short description adds the next tier: **AISSEE 2027, Class 6, Class 9, mock tests, previous-year papers, notes**.
- The full description repeats these naturally (sainik school entrance exam, class 6 admission, mock test, previous year, syllabus, admission process, e-counselling). It avoids keyword stuffing, which Play penalises.
- Competing apps use generic names such as "Sainik School MCQ" or "Sainik school exam app". "Sainik School Exam Prep AISSEE" is the only one with both the brand phrase and the official exam acronym.
- Google's metadata policy bans words like "best", "#1", "top" and "free" in the title, so none of them are used.

## Hindi listing (add as a translation: hi-IN)
Hindi listings rank separately and reach many more parents in Hindi-speaking states.

| Field | Text |
|---|---|
| App name | `सैनिक स्कूल परीक्षा AISSEE` (under 30) |
| Short description | `सैनिक स्कूल परीक्षा 2027: कक्षा 6 व 9 मॉक टेस्ट, पिछले वर्ष पैटर्न पेपर, नोट्स` (78) |
| Full description | Use the English description, opening with: "सैनिक स्कूल प्रवेश परीक्षा (AISSEE 2027) की तैयारी — कक्षा 6 और कक्षा 9। ऐप की सामग्री अंग्रेज़ी में है।" |

## Graphics (all in `store/`)
| Asset | File | Size |
|---|---|---|
| App icon | `icon-512.png` | 512×512 |
| Feature graphic | `feature-graphic-1024x500.png` | 1024×500 |
| Phone screenshots | `phone/01…08` | 1080×1920 (8) |
| 7-inch tablet screenshots | `tablet-7in/01…08` | 1440×2560 (8) |
| 10-inch tablet screenshots | `tablet-10in/01…08` | 2160×3840 (8) |

All screenshots come from the real app (the tablet ones were captured at 800 dp tablet width). Sources are in `store/src/`.

## App category and contact
- Category: **Education**. Tags: Education, Exam prep, Study.
- Contact email: required (shown publicly).
- Privacy policy URL: **https://pradeepg21.github.io/sainikSchool/privacy-policy.html** (hosted with GitHub Pages from `docs/` on `develop`). Contact email: **support.satyadigital@gmail.com**

## App content (Policy → App content) answers
| Section | Answer |
|---|---|
| Privacy policy | https://pradeepg21.github.io/sainikSchool/privacy-policy.html |
| Ads | **No, my app does not contain ads** |
| App access | **All functionality is available without special access** (no login) |
| Content rating | Questionnaire → category *Reference, News, or Educational*; answer **No** to everything. Expected rating: Everyone / 3+ |
| Target audience | Choose **9–12** and **13–15** (add 16–17 / 18+ for parents if you want). Because children are included, the **Families policy** applies. The app complies: no ads, no data collection, no third-party SDKs |
| Data safety | "Does your app collect or share any of the required user data types?" → **No**. Data is stored only on the device. Encryption in transit: not applicable |
| Government apps | **No**. The app is not developed by or for a government. The disclaimer is in the description and the privacy policy |
| Financial features | None |
| Health | None |
| News app | No |
| Exact alarm permission | `SCHEDULE_EXACT_ALARM` is user-granted. If Play shows the exact-alarm declaration, choose "Calendar / alarm-clock / reminders: the user schedules study reminders at an exact time" |

## Closed testing: step by step
1. **Build a signed bundle**: Android Studio → *Build → Generate Signed App Bundle* → release, with your upload keystore. The old `app/release/app-release.aab` has the old Android icon and old dates, so do not upload it. `versionCode` is now **2**.
2. Play Console → **Test and release → Testing → Closed testing** → *Create track* (or use "Alpha").
3. **Testers**: create an email list. New personal developer accounts need **at least 12 testers opted in for 14 continuous days** before you can apply for production access.
4. **Create release** → upload the `.aab` → release name `1.0 (2)` → release notes:
   ```
   First test release: AISSEE 2027 Class 6 & 9 preparation with mock tests, chapter notes, practice quizzes, previous-year pattern papers, results tracking and study planner.
   ```
5. **Countries**: India (add others if you want).
6. Fill in everything in *App content* (table above) and the *Main store listing*.
7. **Send for review**. After approval, share the opt-in link with your testers and ask them to install from it and keep the app installed.

## Before production
- Update `AdmissionInfo.kt` fees when the AISSEE 2027 bulletin fee is confirmed. The 2027 dates are already updated.
- The year-wise papers are pattern-based practice sets, and the listing and app say so. Keep that wording; calling them "official papers" could break Play's misleading-claims policy.

## Photo credit (icon and feature graphic)
The icon and feature graphic use real Republic Day Parade 2023 photos of Indian Army contingents from Wikimedia Commons: "Indian Army contingent Republic Day parade 2023 Img1.jpg" and "…Img2.jpg". The author is the Government of India, and the licence is **GODL-India**, which allows commercial use with attribution. The credit line is at the end of `full_description.txt`; keep it there. The original files are in `store/src/v4/`. The photos show the Indian Army, not Sainik School cadets, and the listing does not claim otherwise. The non-affiliation disclaimer stays in place.
