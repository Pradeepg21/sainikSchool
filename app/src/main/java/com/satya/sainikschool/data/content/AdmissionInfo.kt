package com.satya.sainikschool.data.content

/**
 * Admission & exam information for AISSEE, compiled from public sources (NTA notices for the
 * 2025/2026/2027 cycles and education portals) as of November 2026. The 2027 schedule follows the
 * NTA notification of 3 Nov 2026 — the app always points students to the official bulletin.
 */
object AdmissionInfo {

    const val LAST_REVIEWED = "November 2026"
    const val OFFICIAL_NTA = "https://exams.nta.nic.in/sainik-school-society/"
    const val OFFICIAL_SOCIETY = "https://sainikschoolsociety.in"
    const val OFFICIAL_COUNSELLING = "https://pesa.ncog.gov.in/sainikschoolecounselling/"

    data class InfoBlock(val title: String, val lines: List<String>)

    val overview = InfoBlock("About AISSEE", listOf(
        "The All India Sainik Schools Entrance Examination (AISSEE) is conducted every year by the National Testing Agency (NTA) for admission to Class 6 and Class 9 in Sainik Schools.",
        "Sainik Schools are run by the Sainik Schools Society under the Ministry of Defence. They prepare cadets to join the National Defence Academy (NDA), Indian Naval Academy and other officer-training academies.",
        "There are 33 established Sainik Schools and a growing number of new Sainik Schools set up in partnership mode with states, NGOs and private schools.",
        "The exam is a pen-and-paper (OMR) test of multiple-choice questions with NO negative marking.",
    ))

    val eligibility = listOf(
        InfoBlock("Class 6 entry", listOf(
            "Age: 10 to 12 years as on 31 March of the admission year.",
            "Should be studying in Class 5 (or passed) at the time of admission.",
            "Open to both boys and girls.",
        )),
        InfoBlock("Class 9 entry", listOf(
            "Age: 13 to 15 years as on 31 March of the admission year.",
            "Must have passed Class 8 from a recognised school at the time of admission.",
            "Girls are eligible subject to availability of vacancies — check the current bulletin.",
        )),
    )

    data class PatternRow(val subject: String, val questions: Int, val marksEach: Int) { val total get() = questions * marksEach }

    val class6Pattern = listOf(
        PatternRow("Language", 25, 2),
        PatternRow("Mathematics", 50, 3),
        PatternRow("Intelligence", 25, 2),
        PatternRow("General Knowledge", 25, 2),
    )
    val class6PatternNotes = listOf(
        "Total: 125 questions, 300 marks, 150 minutes (2:00 PM – 4:30 PM in recent cycles).",
        "Medium: any one of 13 languages — Assamese, Bengali, English, Gujarati, Hindi, Kannada, Malayalam, Marathi, Odia, Punjabi, Tamil, Telugu, Urdu.",
        "Syllabus level: up to Class 5.",
    )

    val class9Pattern = listOf(
        PatternRow("Mathematics", 50, 4),
        PatternRow("Intelligence", 25, 2),
        PatternRow("English", 25, 2),
        PatternRow("General Science", 25, 2),
        PatternRow("Social Science", 25, 2),
    )
    val class9PatternNotes = listOf(
        "Total: 150 questions, 400 marks, 180 minutes.",
        "Medium: English only.",
        "Syllabus level: Class 8 (NCERT based).",
    )

    val qualifying = InfoBlock("Qualifying marks & merit", listOf(
        "To qualify, unreserved candidates need at least 25% in each subject and 40% in aggregate (relaxations for SC/ST are as per the official bulletin).",
        "Qualifying the exam does not guarantee a seat — admission is on merit (school-wise, category-wise and gender-wise) followed by medical fitness.",
        "Tip: Mathematics carries the most marks (150/300 for Class 6, 200/400 for Class 9). It decides most of the merit list.",
    ))

    val process = listOf(
        "1. Read the Information Bulletin on the NTA AISSEE website.",
        "2. Register online and fill the application form (candidate details, category, domicile, choice of exam centre and Sainik Schools).",
        "3. Upload photo, signature, left-thumb impression, birth certificate and category / domicile / service certificates as applicable.",
        "4. Pay the fee online (debit/credit card, net banking or UPI).",
        "5. Use the correction window, if needed, to fix errors.",
        "6. Download the admit card (released about 10 days before the exam).",
        "7. Appear for the OMR-based exam at your allotted city.",
        "8. Check the provisional answer key and raise objections if required.",
        "9. Result is declared on the NTA website (scorecard + qualifying status).",
        "10. Take part in e-counselling on the Sainik Schools Society portal — register, choose schools and track seat allotment rounds.",
        "11. Medical examination at a designated military hospital.",
        "12. Document verification and fee payment at the allotted school → admission confirmed.",
    )

    val fees = InfoBlock("Application fee (AISSEE 2026 cycle)", listOf(
        "General / Defence wards / Ex-servicemen wards / OBC-NCL: ₹850.",
        "SC / ST: ₹700.",
        "Processing charges and GST, if applicable, are extra. Check the 2027 bulletin for revised fees.",
    ))

    val dates = InfoBlock("Important dates — AISSEE 2027", listOf(
        "Online application: 3 November 2026 to 2 December 2026 (fee payment till 11:50 PM).",
        "Late application with ₹500 extra fee: 3 to 6 December 2026.",
        "Application correction window: 7 to 9 December 2026.",
        "Exam city intimation: 11 January 2027. Admit card: 25 January 2027.",
        "Exam date: Sunday, 31 January 2027 (pen-and-paper, OMR). Class 6: 150 min, Class 9: 180 min.",
        "Answer key and result: expected February–March 2027, followed by e-counselling rounds.",
        "Always confirm dates on the official NTA website — they can be revised.",
    ))

    val reservation = InfoBlock("Reservation of seats", listOf(
        "67% of seats for children domiciled in the state/UT where the school is located; 33% for children from other states/UTs.",
        "Within these: 15% SC, 7.5% ST and 27% OBC (Non-Creamy Layer) as per government norms.",
        "25% of seats (in each of the above) for wards of serving and ex-servicemen, including ex-servicemen.",
        "Girls: seats in Class 6 as notified for each school.",
        "New Sainik Schools: 40% seats via AISSEE merit and 60% for students already studying in that school who qualify the AISSEE.",
    ))

    val documents = InfoBlock("Documents to keep ready", listOf(
        "Recent passport-size photograph and signature (scanned).",
        "Birth certificate (issued by municipal/registrar authority).",
        "Domicile certificate (for home-state quota).",
        "Caste certificate (SC/ST/OBC-NCL) from a competent authority.",
        "Service certificate / discharge certificate (for Defence and ex-servicemen wards).",
        "Class 5 / Class 8 bonafide or study certificate at the time of admission.",
        "Aadhaar card.",
    ))

    data class School(val name: String, val state: String)

    val schools = listOf(
        School("Sainik School Amaravathinagar", "Tamil Nadu"),
        School("Sainik School Ambikapur", "Chhattisgarh"),
        School("Sainik School Amethi", "Uttar Pradesh"),
        School("Sainik School Balachadi", "Gujarat"),
        School("Sainik School Bhubaneswar", "Odisha"),
        School("Sainik School Bijapur", "Karnataka"),
        School("Sainik School Chandrapur", "Maharashtra"),
        School("Sainik School Chhingchhip", "Mizoram"),
        School("Sainik School Chittorgarh", "Rajasthan"),
        School("Sainik School East Siang", "Arunachal Pradesh"),
        School("Sainik School Ghorakhal", "Uttarakhand"),
        School("Sainik School Goalpara", "Assam"),
        School("Sainik School Gopalganj", "Bihar"),
        School("Sainik School Imphal", "Manipur"),
        School("Sainik School Jhansi", "Uttar Pradesh"),
        School("Sainik School Jhunjhunu", "Rajasthan"),
        School("Sainik School Kalikiri", "Andhra Pradesh"),
        School("Sainik School Kapurthala", "Punjab"),
        School("Sainik School Kazhakootam", "Kerala"),
        School("Sainik School Kodagu", "Karnataka"),
        School("Sainik School Korukonda", "Andhra Pradesh"),
        School("Sainik School Kunjpura", "Haryana"),
        School("Sainik School Mainpuri", "Uttar Pradesh"),
        School("Sainik School Nagrota", "Jammu & Kashmir"),
        School("Sainik School Nalanda", "Bihar"),
        School("Sainik School Punglwa", "Nagaland"),
        School("Sainik School Purulia", "West Bengal"),
        School("Sainik School Rewa", "Madhya Pradesh"),
        School("Sainik School Rewari", "Haryana"),
        School("Sainik School Sambalpur", "Odisha"),
        School("Sainik School Satara", "Maharashtra"),
        School("Sainik School Sujanpur Tira", "Himachal Pradesh"),
        School("Sainik School Tilaiya", "Jharkhand"),
    )

    val prepTips = listOf(
        "Finish NCERT textbooks of the previous class (Class 5 for Class 6 entry, Class 8 for Class 9 entry) first.",
        "Mathematics: practise 30–40 questions daily — speed and accuracy decide your rank.",
        "Since there is no negative marking, attempt every question; make an intelligent guess if unsure.",
        "Take one full mock test every week in exam timing, then study every wrong answer's explanation.",
        "Revise GK/Science facts with short notes; read a newspaper's headlines daily.",
        "Practise filling OMR sheets neatly with a black/blue ball-point pen.",
        "Keep yourself physically fit — a medical examination is part of admission.",
    )

    data class Faq(val q: String, val a: String)

    val faqs = listOf(
        Faq("Is there negative marking in AISSEE?", "No. There is no negative marking, so attempt all questions."),
        Faq("Is the exam online or offline?", "It is a pen-and-paper exam on an OMR sheet."),
        Faq("Can girls apply?", "Yes. Girls can apply for Class 6 in all Sainik Schools. For Class 9, admission of girls is subject to vacancies as notified in the bulletin."),
        Faq("Can I appear in Hindi?", "For Class 6 the paper is available in 13 languages including Hindi and English. Class 9 is in English only."),
        Faq("What happens after the result?", "Qualified candidates take part in e-counselling, then a medical examination, and finally document verification at the allotted school."),
        Faq("Is coaching necessary?", "No. Regular practice of the syllabus, mock tests and revision of mistakes — as provided in this app — is enough for most students."),
    )
}
