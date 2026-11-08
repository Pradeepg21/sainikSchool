package com.satya.sainikschool.data.content

import com.satya.sainikschool.data.model.ExamClass

/** AISSEE Class 6 — Intelligence: 25 questions × 2 marks = 50 marks. */
val class6Intelligence = subject("c6_int", "Intelligence", ExamClass.CLASS_6, 25, 2, "brain") {

    chapter("series", "Number Series", "Find the pattern (add, multiply, squares, cubes, alternate) and the next term.", gen = "series") {
        note("Common patterns",
            "Constant difference: 3, 7, 11, 15 (+4).",
            "Increasing difference: 2, 3, 5, 8, 12 (+1, +2, +3, +4).",
            "Multiplication: 2, 6, 18, 54 (×3).",
            "Squares 1, 4, 9, 16 …; cubes 1, 8, 27, 64 …",
            "Alternate series: two series mixed together (1, 10, 3, 20, 5, 30).")
        note("Strategy",
            "Write the differences under the series first.",
            "If differences don't help, try ratios, then squares/cubes, then alternate terms.")
        q("Find the missing term: 2, 6, 12, 20, 30, ?", "40", "42", "44", "46", 1,
            "Differences are +4, +6, +8, +10, so next is +12 → 42. (Also n × (n + 1).)")
        q("Find the wrong number: 1, 8, 27, 63, 125", "8", "27", "63", "125", 2,
            "These are cubes 1³, 2³, 3³, 4³, 5³. 4³ = 64, not 63.")
    }

    chapter("letter_series", "Alphabet & Letter Series", "Positions of letters, reverse order and letter patterns.", gen = "letter_series") {
        note("Alphabet positions",
            "Remember EJOTY: E = 5, J = 10, O = 15, T = 20, Y = 25.",
            "Reverse position = 27 − forward position (A ↔ Z, B ↔ Y, M ↔ N).",
            "Vowels: A, E, I, O, U.")
        q("Find the next term: AZ, BY, CX, ?", "DV", "DW", "EW", "DX", 1,
            "First letters go A, B, C, D; second letters go Z, Y, X, W → DW.")
    }

    chapter("alphabet", "Alphabet Test", "Letter positions, word values and dictionary order.", gen = "alphabet") {
        note("Tips",
            "Dictionary order: compare first letters, then second letters, and so on.",
            "Opposite pairs: A–Z, B–Y, C–X, D–W, E–V, F–U, G–T, H–S, I–R, J–Q, K–P, L–O, M–N.")
        q("Which word comes first in the dictionary?", "Garden", "Garland", "Gather", "Garage", 3,
            "All start with 'Ga'. Third letters: r, r, t, r. Among Garden, Garland, Garage compare 4th letters: d, l, a → Garage first.")
    }

    chapter("coding", "Coding–Decoding", "Letter shifting, reverse coding and number coding.", gen = "coding") {
        note("Types",
            "Letter shift: each letter moved by a fixed number (CAT → DBU is +1).",
            "Reverse/opposite letter coding: A ↔ Z, B ↔ Y.",
            "Number coding: letters replaced by positions (BAD = 2 1 4).",
            "Always compare at least two letters to confirm the rule.")
        q("If MANGO is coded as NBOHP, then APPLE is coded as:", "BQQMF", "BOOMF", "BQQNF", "ZOOKD", 0,
            "Each letter +1: A→B, P→Q, P→Q, L→M, E→F → BQQMF.")
        q("If in a code CAT = 24 (3 + 1 + 20), then DOG = ?", "24", "26", "28", "30", 1,
            "D = 4, O = 15, G = 7 → 4 + 15 + 7 = 26.")
    }

    chapter("analogy", "Analogy", "Find the relationship between the first pair and apply it to the second.", gen = "analogy") {
        note("Common relations",
            "Worker–tool (Carpenter : Saw), Animal–young one (Cow : Calf), Animal–home (Bee : Hive).",
            "Country–capital, Instrument–measurement (Thermometer : Temperature).",
            "Number analogies: square, cube, double, +1 etc.")
        q("Pen : Write :: Knife : ?", "Sharp", "Cut", "Steel", "Kitchen", 1,
            "A pen is used to write; a knife is used to cut (tool : function).")
        q("Lion : Den :: Horse : ?", "Kennel", "Stable", "Burrow", "Nest", 1,
            "Lion lives in a den; horse lives in a stable.")
        q("Puppy : Dog :: Kid : ?", "Goat", "Sheep", "Cow", "Horse", 0,
            "A puppy is a young dog; a kid is a young goat.")
    }

    chapter("odd_one", "Classification (Odd One Out)", "Find the item that does not belong to the group.", gen = "odd_one") {
        note("How to classify",
            "Look for category (fruits, vegetables, planets), property (even/odd, prime) or pattern.",
            "For words, check meaning first, then letters (vowels, alphabetical order).")
        q("Find the odd one out: Mercury, Venus, Moon, Mars", "Mercury", "Venus", "Moon", "Mars", 2,
            "Mercury, Venus and Mars are planets; the Moon is a satellite.")
        q("Find the odd one out: 11, 17, 21, 29", "11", "17", "21", "29", 2,
            "21 = 3 × 7 is composite; others are primes.")
        q("Find the odd one out: Rose, Lotus, Lily, Mango", "Rose", "Lotus", "Lily", "Mango", 3,
            "Mango is a fruit; others are flowers.")
    }

    chapter("direction", "Direction Sense", "Turning left/right, final direction and shortest distance.", gen = "direction") {
        note("Basics",
            "Facing North: right is East, left is West.",
            "Clockwise: N → E → S → W. Right turn = clockwise 90°.",
            "Sunrise is in the East, so in the morning your shadow falls West.",
            "Shortest distance uses Pythagoras: √(x² + y²).")
        q("Facing East, Ravi turns left, then left again. Which direction is he facing now?", "North", "South", "West", "East", 2,
            "East → left → North → left → West.")
    }

    chapter("ranking", "Ranking and Ordering", "Position from top/bottom, left/right and comparisons.", gen = "ranking") {
        note("Formulae",
            "Total = rank from top + rank from bottom − 1.",
            "Rank from bottom = Total − rank from top + 1.",
            "For comparisons (A > B, B > C) draw a line diagram.")
        q("A is taller than B. C is shorter than B. D is taller than A. Who is the shortest?", "A", "B", "C", "D", 2,
            "Order: D > A > B > C. Shortest is C.")
    }

    chapter("missing_number", "Missing Number & Figure Patterns", "Find the rule in rows, columns or circles.", gen = "missing_number") {
        note("Look for",
            "Row/column sums or products.",
            "a × b + c type rules; squares of numbers.",
            "Opposite segments in a circle adding to the same value.")
        q("Find the missing number: 4 → 17, 6 → 37, 8 → ?", "63", "64", "65", "66", 2,
            "Rule: n² + 1. 8² + 1 = 65.")
    }

    chapter("mirror", "Mirror & Water Images, Figure Reasoning", "Lateral inversion, water images, paper folding, counting figures.") {
        note("Mirror image",
            "In a mirror placed to the right/left, left and right are swapped; top and bottom stay.",
            "Letters with vertical symmetry look the same: A, H, I, M, O, T, U, V, W, X, Y.",
            "Clock in mirror: time shown = 11:60 − actual time (for times after 1 o'clock).")
        note("Water image",
            "Top and bottom are swapped; left and right stay.",
            "Letters unchanged in water: B, C, D, E, H, I, K, O, X.")
        note("Counting figures",
            "Count triangles systematically: single parts, then pairs, then bigger combinations.",
            "A square divided by both diagonals has 8 triangles.")
        q("A clock shows 3:15. What time does its mirror image show?", "8:45", "9:45", "8:15", "9:15", 0,
            "Mirror time = 11:60 − 3:15 = 8:45.")
        q("Which letter looks the same in a mirror?", "B", "E", "A", "R", 2,
            "A has vertical symmetry, so its mirror image is unchanged.")
        q("How many triangles are formed when both diagonals of a square are drawn?", "4", "6", "8", "10", 2,
            "4 small triangles + 4 larger triangles (each made of 2 small ones) = 8.")
    }

    chapter("blood_relation", "Blood Relations", "Family relationships and family-tree problems.") {
        note("Key relations",
            "Father's/mother's brother = uncle; their sister = aunt.",
            "Brother's/sister's son = nephew; daughter = niece.",
            "Father's father = grandfather; son's son = grandson.",
            "Draw a family tree: + for male, − for female, horizontal line for siblings.")
        q("Pointing to a boy, Neha says, \"He is the son of my mother's only son.\" How is the boy related to Neha?", "Brother", "Nephew", "Son", "Cousin", 1,
            "Mother's only son = Neha's brother. His son = Neha's nephew.")
        q("A is B's sister. C is B's mother. D is C's father. How is A related to D?", "Granddaughter", "Daughter", "Grandmother", "Niece", 0,
            "A is C's daughter; D is C's father, so A is D's granddaughter.")
    }

    chapter("venn", "Venn Diagrams & Logical Order", "Relations between groups and arranging words logically.") {
        note("Venn diagrams",
            "One group inside another: Dogs ⊂ Animals.",
            "Overlapping circles: Doctors and Women (some doctors are women).",
            "Separate circles: Cats and Fish.")
        note("Logical order",
            "Arrange by size, life cycle or process (Seed → Plant → Flower → Fruit).")
        q("Arrange in logical order: 1. Adult 2. Child 3. Infant 4. Teenager", "3, 2, 4, 1", "2, 3, 4, 1", "3, 4, 2, 1", "1, 4, 2, 3", 0,
            "Infant → Child → Teenager → Adult.")
        q("Which diagram best represents India, Delhi, Asia?", "Three separate circles", "Three concentric circles", "Two overlapping circles", "One circle inside, one outside", 1,
            "Delhi is in India and India is in Asia → circles one inside another.")
    }
}
