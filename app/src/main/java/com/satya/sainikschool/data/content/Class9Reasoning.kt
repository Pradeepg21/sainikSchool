package com.satya.sainikschool.data.content

import com.satya.sainikschool.data.model.ExamClass

/** AISSEE Class 9 — Intelligence: 25 questions × 2 marks = 50 marks. */
val class9Intelligence = subject("c9_int", "Intelligence", ExamClass.CLASS_9, 25, 2, "brain") {

    chapter("series", "Number & Letter Series", "Arithmetic, geometric, square/cube and mixed series.", gen = "series") {
        note("Patterns",
            "Check differences, then second differences, then ratios.",
            "n² ± 1, n³ ± n, primes, Fibonacci (each term = sum of previous two).",
            "Letter series: convert letters to positions (A = 1 … Z = 26).")
        q("Find the next term: 3, 5, 9, 17, 33, ?", "49", "57", "65", "66", 2,
            "Differences double: +2, +4, +8, +16, +32 → 33 + 32 = 65. (Also 2ⁿ + 1.)")
        q("Find the next term: 1, 1, 2, 3, 5, 8, ?", "11", "12", "13", "14", 2,
            "Fibonacci: each term is the sum of the previous two → 5 + 8 = 13.")
    }

    chapter("letter_series", "Alphabet Series", "Patterns with letters and positions.", gen = "letter_series") {
        note("Tips",
            "EJOTY for positions 5, 10, 15, 20, 25.",
            "Look at alternate letters separately when the pattern breaks.")
        q("Find the next term: B, E, I, N, ?", "S", "T", "R", "U", 1,
            "Gaps +3, +4, +5, +6 → N (14) + 6 = 20 = T.")
    }

    chapter("coding", "Coding–Decoding", "Letter shift, reverse, number and substitution coding.", gen = "coding") {
        note("Types",
            "Shift coding, reverse alphabet coding (A ↔ Z), position-sum coding.",
            "Substitution: 'if sky is called sea, sea is called water…' — answer with the substituted name.")
        q("If 'sky' is called 'sea', 'sea' is called 'water', 'water' is called 'air', what do fish live in?", "sea", "water", "air", "sky", 2,
            "Fish live in water, and water is called 'air'.")
        q("If ROAD is written as URDG, how is SWAN written?", "VZDQ", "VXDQ", "UZDQ", "VZCQ", 0,
            "Each letter +3: S→V, W→Z, A→D, N→Q.")
    }

    chapter("analogy", "Analogy & Classification", "Relationship-based pairs and odd-one-out.", gen = "analogy") {
        note("Relations",
            "Cause–effect, part–whole, instrument–measure, opposite, degree.",
            "For numbers check squares, cubes, primes, multiples.")
        q("Doctor : Hospital :: Teacher : ?", "Book", "School", "Student", "Class", 1,
            "Workplace relation: a doctor works in a hospital, a teacher in a school.")
        q("Barometer : Pressure :: Hygrometer : ?", "Temperature", "Humidity", "Altitude", "Speed", 1,
            "A hygrometer measures humidity.")
    }

    chapter("odd_one", "Odd One Out", "Classification of numbers, words and letter groups.", gen = "odd_one") {
        note("Tips",
            "Look for the common property among three, then spot the misfit.")
        q("Find the odd one out: Iron, Copper, Brass, Silver", "Iron", "Copper", "Brass", "Silver", 2,
            "Brass is an alloy (copper + zinc); others are elements.")
        q("Find the odd one out: 8, 27, 64, 100, 125", "27", "64", "100", "125", 2,
            "8, 27, 64, 125 are cubes; 100 is not.")
    }

    chapter("direction", "Direction Sense", "Turns, distances and shadows.", gen = "direction") {
        note("Tips",
            "Draw the path on paper with N at top.",
            "Morning: shadow points West; evening: shadow points East.",
            "Diagonal directions: NE, NW, SE, SW (45° turns).")
        q("In the morning Ram stood facing his shadow. Which direction was he facing?", "East", "West", "North", "South", 1,
            "In the morning the Sun is in the East, so the shadow falls West. Facing the shadow means facing West.")
    }

    chapter("ranking", "Ranking, Order and Seating Arrangement", "Positions in a row, circular seating and comparison.", gen = "ranking") {
        note("Linear seating",
            "Draw blank positions and fill fixed clues first.",
            "In a row facing North, the person's right is your right.")
        note("Circular seating",
            "When everyone faces the centre, a person's left is clockwise and right is anticlockwise. Fix one person first and place others relative to them.")
        q("Five friends sit in a row facing North. A is to the right of B, E is to the left of C and right of A, B is to the right of D. Who is in the middle?", "A", "B", "C", "E", 0,
            "From clues: D, B, A, E, C (left to right). Middle (3rd) is A.")
        q("In a row of 40 students, Meena is 15th from the left. Her position from the right is:", "25th", "26th", "24th", "27th", 1,
            "40 − 15 + 1 = 26th.")
    }

    chapter("blood_relation", "Blood Relations", "Family relations and coded relations.") {
        note("Tips",
            "Start from the last person mentioned and move backwards.",
            "Coded: 'A + B' means A is B's father etc. — decode step by step.")
        q("Introducing a man, a woman said, \"His mother is the only daughter of my mother.\" How is the woman related to the man?", "Sister", "Mother", "Aunt", "Grandmother", 1,
            "Only daughter of the woman's mother is the woman herself, so she is the man's mother.")
        q("A's father is B's son. C is B's father. How is C related to A?", "Father", "Grandfather", "Great-grandfather", "Uncle", 2,
            "B is A's grandfather; C is B's father → A's great-grandfather.")
    }

    chapter("calendar", "Calendar", "Odd days, leap years and day-of-the-week problems.", gen = "calendar") {
        note("Odd days",
            "Ordinary year = 1 odd day; leap year = 2 odd days.",
            "Day numbers: 0 = Sunday, 1 = Monday … 6 = Saturday.",
            "A date repeats on the same day after 28 years in many cases (not across non-leap centuries).")
        q("If today is Tuesday, what day will it be after 59 days?", "Friday", "Saturday", "Sunday", "Thursday", 0,
            "59 = 8 weeks + 3 days, so count 3 days ahead: Tue → Wed → Thu → Fri. Answer: Friday.")
    }

    chapter("clock", "Clocks", "Angle between hands and mirror images.", gen = "clock") {
        note("Facts",
            "θ = |30H − 5.5M|.",
            "Hands are at right angles 22 times and opposite 11 times in 12 hours.",
            "Mirror time = 11:60 − given time.")
        q("At what angle are the hands of a clock at 7:30?", "30°", "45°", "60°", "75°", 1,
            "|30 × 7 − 5.5 × 30| = |210 − 165| = 45°.")
    }

    chapter("syllogism", "Syllogism & Statement–Conclusion", "Draw valid conclusions using Venn diagrams.") {
        note("Rules",
            "All A are B + All B are C → All A are C.",
            "Some A are B → Some B are A (valid).",
            "All A are B does NOT mean All B are A.",
            "No A is B → No B is A.")
        q("Statements: All pens are books. All books are tables. Conclusion I: All pens are tables. II: All tables are pens.", "Only I follows", "Only II follows", "Both follow", "Neither follows", 0,
            "Pens ⊂ Books ⊂ Tables → all pens are tables. The reverse (all tables are pens) is not certain.")
        q("Statements: Some cats are dogs. No dog is a cow. Conclusion: Some cats are not cows.", "Follows", "Does not follow", "Partly follows", "Data inadequate", 0,
            "The cats that are dogs cannot be cows, so some cats are definitely not cows.")
    }

    chapter("cube_dice", "Cubes, Dice and Figure Counting", "Painted cubes, opposite faces of dice and counting figures.") {
        note("Painted cube (n × n × n)",
            "3 faces painted = 8 (corners). 2 faces = 12(n − 2). 1 face = 6(n − 2)². 0 faces = (n − 2)³.")
        note("Dice",
            "In a standard die opposite faces sum to 7.",
            "Two adjacent positions sharing a common face: the remaining faces are opposite.")
        q("A cube painted on all faces is cut into 27 equal small cubes. How many have exactly two faces painted?", "8", "12", "6", "1", 1,
            "n = 3 → 12(n − 2) = 12.")
        q("On a standard die, the face opposite 2 is:", "4", "5", "6", "3", 1,
            "Opposite faces sum to 7 → 7 − 2 = 5.")
        q("In a 4 × 4 × 4 painted cube cut into unit cubes, how many have no face painted?", "4", "8", "16", "27", 1,
            "(n − 2)³ = 2³ = 8.")
    }

    chapter("missing_number", "Missing Numbers & Mathematical Operations", "Number grids and symbol substitution.", gen = "missing_number") {
        note("Tips",
            "Substitution: replace symbols carefully and then apply BODMAS.",
            "Grids: check rows, columns and diagonals.")
        q("If '+' means '×', '×' means '−', '−' means '÷' and '÷' means '+', then 12 + 3 × 16 − 4 ÷ 2 = ?", "34", "30", "38", "26", 0,
            "Replace: 12 × 3 − 16 ÷ 4 + 2 = 36 − 4 + 2 = 34.")
    }
}
