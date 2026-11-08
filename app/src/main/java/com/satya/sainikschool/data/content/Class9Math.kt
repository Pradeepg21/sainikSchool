package com.satya.sainikschool.data.content

import com.satya.sainikschool.data.model.ExamClass

/** AISSEE Class 9 — Mathematics: 50 questions × 4 marks = 200 marks (Class 8 NCERT level). */
val class9Math = subject("c9_math", "Mathematics", ExamClass.CLASS_9, 50, 4, "math") {

    chapter("rational", "Rational Numbers", "Properties of rational numbers, additive/multiplicative inverse, number line.", gen = "rational") {
        note("Definitions",
            "A rational number can be written as p/q where p, q are integers and q ≠ 0.",
            "Closure: rationals are closed under +, −, ×; not under ÷ (division by 0).",
            "Additive identity 0; multiplicative identity 1.",
            "Additive inverse of a/b is −a/b; multiplicative inverse (reciprocal) of a/b is b/a.",
            "Between any two rationals there are infinitely many rationals; one is (a + b)/2.")
        q("The multiplicative inverse of −7/9 is:", "7/9", "−9/7", "9/7", "−7/9", 1,
            "Reciprocal of −7/9 is −9/7, since (−7/9) × (−9/7) = 1.")
        q("Which rational number has no reciprocal?", "1", "−1", "0", "1/2", 2,
            "1/0 is not defined, so 0 has no reciprocal.")
    }

    chapter("linear_eq", "Linear Equations in One Variable", "Solving equations and word problems.", gen = "linear_eq") {
        note("Solving",
            "Do the same operation on both sides; transposing changes sign (+ ↔ −, × ↔ ÷).",
            "Cross-multiplication for equations like (ax + b)/(cx + d) = m/n.",
            "Always check the answer by substituting back.")
        note("Word problems",
            "Consecutive numbers: x, x + 1, x + 2. Consecutive even/odd: x, x + 2, x + 4.",
            "Age problems: 'n years ago' → (age − n); 'after n years' → (age + n).",
            "Digits: two-digit number = 10a + b; reversed = 10b + a.")
        q("The sum of three consecutive multiples of 8 is 888. The largest is:", "288", "296", "304", "312", 2,
            "8x + 8(x + 1) + 8(x + 2) = 888 → 24x + 24 = 888 → x = 36. Largest = 8 × 38 = 304.")
        q("A father is 3 times as old as his son. After 12 years he will be twice as old. The son's present age is:", "10", "12", "14", "16", 1,
            "3x + 12 = 2(x + 12) → x = 12 years.")
    }

    chapter("squares", "Squares and Square Roots", "Perfect squares, properties and finding square roots.", gen = "squares") {
        note("Properties",
            "A perfect square never ends in 2, 3, 7 or 8.",
            "Square of an even number is even; of an odd number is odd.",
            "Number of zeros at the end of a perfect square is always even.",
            "Sum of first n odd numbers = n².",
            "Pythagorean triplet for m > 1: 2m, m² − 1, m² + 1.")
        note("Methods",
            "Prime factorisation (pair the factors).",
            "Long division method for large numbers and decimals.",
            "√(a/b) = √a / √b.")
        q("The smallest number by which 180 must be multiplied to make it a perfect square is:", "2", "3", "5", "6", 2,
            "180 = 2² × 3² × 5. The 5 is unpaired, so multiply by 5 → 900 = 30².")
        q("Which of these cannot be a perfect square?", "1024", "2025", "4097", "3969", 2,
            "Perfect squares never end in 7.")
    }

    chapter("cubes", "Cubes and Cube Roots", "Perfect cubes and cube roots by prime factorisation.", gen = "cubes") {
        note("Facts",
            "Cube of a negative number is negative.",
            "Cubes of 1–10: 1, 8, 27, 64, 125, 216, 343, 512, 729, 1000.",
            "Hardy–Ramanujan number 1729 = 1³ + 12³ = 9³ + 10³.",
            "Unit digit of a cube root can be found from the unit digit of the cube (e.g. 8 ↔ 2, 7 ↔ 3).")
        q("The smallest number by which 392 must be multiplied to make it a perfect cube is:", "2", "7", "8", "14", 1,
            "392 = 2³ × 7². The factor 7 needs one more 7 to complete a triplet, so multiply by 7 → 2744 = 14³.")
        q("The cube root of 0.027 is:", "0.03", "0.3", "0.9", "3", 1,
            "0.027 = 27/1000 → ∛ = 3/10 = 0.3.")
    }

    chapter("exponents", "Exponents and Powers", "Laws of exponents, negative exponents and standard form.", gen = "exponents") {
        note("Laws",
            "aᵐ × aⁿ = aᵐ⁺ⁿ; aᵐ ÷ aⁿ = aᵐ⁻ⁿ; (aᵐ)ⁿ = aᵐⁿ.",
            "a⁰ = 1 (a ≠ 0); a⁻ⁿ = 1/aⁿ; (a/b)⁻ⁿ = (b/a)ⁿ.",
            "Standard form: k × 10ⁿ with 1 ≤ k < 10 (e.g. 0.000035 = 3.5 × 10⁻⁵).")
        q("The value of (2⁻¹ + 3⁻¹)⁻¹ is:", "5/6", "6/5", "5", "1/5", 1,
            "2⁻¹ + 3⁻¹ = 1/2 + 1/3 = 5/6. Its inverse = 6/5.")
        q("Express 0.00045 in standard form.", "45 × 10⁻⁵", "4.5 × 10⁻⁴", "4.5 × 10⁻³", "0.45 × 10⁻³", 1,
            "Move the decimal 4 places right → 4.5 × 10⁻⁴.")
    }

    chapter("compound_interest", "Comparing Quantities (Percent, Discount, CI)", "Percentage, discount, GST, profit/loss and compound interest.", gen = "compound_interest") {
        note("Discount & tax",
            "Discount = Marked Price − Selling Price; Discount% is on Marked Price.",
            "GST/sales tax is added to the selling price.",
            "Successive discounts a% and b% = (a + b − ab/100)%.")
        note("Compound interest",
            "A = P(1 + R/100)ⁿ; CI = A − P.",
            "Half-yearly: rate R/2, time 2n. Quarterly: rate R/4, time 4n.",
            "Growth/depreciation: population P(1 ± R/100)ⁿ.",
            "Difference CI − SI for 2 years = P(R/100)².")
        q("A shirt marked ₹800 is sold at 15% discount. The selling price is:", "₹650", "₹680", "₹700", "₹720", 1,
            "Discount = 15% of 800 = 120. SP = 800 − 120 = ₹680.")
        q("The difference between CI and SI on ₹10,000 for 2 years at 10% p.a. is:", "₹50", "₹100", "₹150", "₹200", 1,
            "Difference = P(R/100)² = 10000 × 0.01 = ₹100.")
        q("A town's population of 20,000 grows at 5% annually. After 2 years it will be:", "22,000", "22,050", "21,000", "22,500", 1,
            "20000 × 1.05 × 1.05 = 22,050.")
    }

    chapter("profit_loss", "Profit, Loss and Percentage", "Advanced profit and loss with marked price.", gen = "profit_loss") {
        note("Key results",
            "If CP of x articles = SP of y articles, profit% = (x − y)/y × 100.",
            "Selling two items at the same price, one at x% gain and other at x% loss → always a loss of x²/100 %.",
            "Percentage change: (new − old)/old × 100.")
        q("If the CP of 12 pens equals the SP of 10 pens, the profit % is:", "16⅔%", "20%", "25%", "10%", 1,
            "Profit% = (12 − 10)/10 × 100 = 20%.")
        q("Two items are sold at ₹990 each, one at 10% profit and the other at 10% loss. Overall:", "No profit no loss", "1% loss", "1% profit", "2% loss", 1,
            "Same SP with equal % gain and loss → loss of (10²/100)% = 1%.")
    }

    chapter("identities", "Algebraic Expressions and Identities", "Terms, like terms, multiplication and standard identities.", gen = "identities") {
        note("Standard identities",
            "(a + b)² = a² + 2ab + b²",
            "(a − b)² = a² − 2ab + b²",
            "a² − b² = (a + b)(a − b)",
            "(x + a)(x + b) = x² + (a + b)x + ab")
        note("Uses",
            "Quick squares: 102² = (100 + 2)² = 10404.",
            "Products: 97 × 103 = 100² − 3² = 9991.")
        q("If a + b = 10 and ab = 21, then a² + b² equals:", "58", "79", "100", "121", 0,
            "a² + b² = (a + b)² − 2ab = 100 − 42 = 58.")
        q("Evaluate 98 × 102 using identity.", "9996", "9994", "10004", "9999", 0,
            "(100 − 2)(100 + 2) = 10000 − 4 = 9996.")
    }

    chapter("factorisation", "Factorisation", "Common factors, regrouping, identities and division of polynomials.") {
        note("Methods",
            "Take out common factors: 6x² + 9x = 3x(2x + 3).",
            "Regrouping: ax + bx + ay + by = (a + b)(x + y).",
            "Using identities: x² − 16 = (x − 4)(x + 4).",
            "Splitting middle term: x² + 5x + 6 = (x + 2)(x + 3) (find two numbers with sum 5, product 6).")
        q("Factorise: x² − 7x + 12", "(x − 3)(x − 4)", "(x + 3)(x + 4)", "(x − 2)(x − 6)", "(x − 1)(x − 12)", 0,
            "Numbers with sum −7 and product 12: −3 and −4.")
        q("Factorise: 49p² − 36", "(7p − 6)²", "(7p − 6)(7p + 6)", "(49p − 36)(p + 1)", "7(7p² − 6)", 1,
            "a² − b² with a = 7p, b = 6.")
        q("Divide: (10x² − 25x) ÷ 5x", "2x − 5", "2x + 5", "5x − 2", "2x − 25", 0,
            "10x²/5x − 25x/5x = 2x − 5.")
    }

    chapter("proportion", "Direct and Inverse Proportion", "Proportionality and its applications.", gen = "proportion") {
        note("Rules",
            "Direct: x/y = constant (more cost for more items).",
            "Inverse: x × y = constant (more workers, fewer days; more speed, less time).")
        q("If 15 workers build a wall in 48 hours, how many workers are needed to do it in 30 hours?", "20", "24", "25", "30", 1,
            "Inverse: 15 × 48 = 720 = x × 30 → x = 24.")
    }

    chapter("time_work", "Time and Work, Speed and Distance", "Work rate problems, pipes, trains and boats.", gen = "time_work") {
        note("Time & work",
            "If A does a job in n days, A's 1-day work = 1/n.",
            "Together: 1/a + 1/b; time = ab/(a + b).",
            "Pipes: filling is +, leaking/emptying is −.")
        note("Speed",
            "Train passing a pole: distance = train length. Passing a platform: train + platform.",
            "Relative speed: same direction → difference; opposite → sum.",
            "Boat: downstream = u + v, upstream = u − v.")
        q("A pipe fills a tank in 6 h and another empties it in 12 h. If both are open, the tank fills in:", "8 h", "10 h", "12 h", "18 h", 2,
            "Net per hour = 1/6 − 1/12 = 1/12 → 12 h.")
        q("A 200 m train at 72 km/h crosses a 300 m platform in:", "20 s", "25 s", "30 s", "15 s", 1,
            "72 km/h = 20 m/s. Distance = 500 m → 25 s.")
    }

    chapter("quadrilaterals", "Understanding Quadrilaterals & Polygons", "Polygons, angle sums and properties of quadrilaterals.", gen = "polygons") {
        note("Polygons",
            "Sum of interior angles = (n − 2) × 180°. Sum of exterior angles = 360°.",
            "Each exterior angle of a regular polygon = 360°/n.")
        note("Quadrilaterals",
            "Parallelogram: opposite sides & angles equal; diagonals bisect each other.",
            "Rhombus: all sides equal; diagonals perpendicular bisectors.",
            "Rectangle: diagonals equal; square: diagonals equal and perpendicular.",
            "Kite: two pairs of equal adjacent sides.")
        q("The number of sides of a regular polygon whose each exterior angle is 40° is:", "6", "8", "9", "10", 2,
            "n = 360/40 = 9.")
        q("In a parallelogram one angle is 70°. The adjacent angle is:", "70°", "110°", "90°", "140°", 1,
            "Adjacent angles of a parallelogram are supplementary: 180 − 70 = 110°.")
    }

    chapter("mensuration", "Mensuration (Area)", "Area of trapezium, rhombus, general quadrilateral and polygons.", gen = "mensuration") {
        note("Area formulae",
            "Trapezium = ½ × (sum of parallel sides) × height.",
            "Rhombus = ½ × d₁ × d₂.",
            "Quadrilateral = ½ × diagonal × (sum of perpendiculars on it).",
            "Triangle (Heron): √(s(s − a)(s − b)(s − c)), s = (a + b + c)/2.")
        q("The area of a trapezium is 34 cm², the parallel sides are 10 cm and 7 cm. Its height is:", "3 cm", "4 cm", "5 cm", "6 cm", 1,
            "34 = ½ × 17 × h → h = 4 cm.")
    }

    chapter("volume", "Surface Area and Volume", "Cube, cuboid and cylinder.", gen = "volume") {
        note("Formulae",
            "Cuboid: TSA = 2(lb + bh + hl); LSA = 2h(l + b); V = lbh.",
            "Cube: TSA = 6a²; LSA = 4a²; V = a³.",
            "Cylinder: CSA = 2πrh; TSA = 2πr(r + h); V = πr²h.",
            "1 m³ = 1000 L; 1 cm³ = 1 mL.")
        q("The volume of a cylinder of radius 7 cm and height 10 cm is:", "1540 cm³", "1440 cm³", "440 cm³", "154 cm³", 0,
            "πr²h = 22/7 × 49 × 10 = 1540 cm³.")
        q("If each edge of a cube is doubled, its volume becomes:", "2 times", "4 times", "6 times", "8 times", 3,
            "(2a)³ = 8a³.")
    }

    chapter("circles", "Circles", "Circumference, area, sector and ring.", gen = "circles") {
        note("Formulae",
            "Circumference = 2πr; area = πr².",
            "Area of a ring = π(R² − r²).",
            "Semicircle perimeter = πr + 2r.",
            "Distance covered in one revolution of a wheel = circumference.")
        q("A wheel of radius 35 cm makes how many revolutions to cover 440 m?", "100", "200", "250", "400", 1,
            "Circumference = 2 × 22/7 × 35 = 220 cm = 2.2 m. 440 ÷ 2.2 = 200.")
    }

    chapter("triangles", "Triangles and Pythagoras", "Angle sum, exterior angle, congruence and Pythagoras theorem.", gen = "triangles") {
        note("Properties",
            "Angle sum = 180°. Exterior angle = sum of interior opposite angles.",
            "Sum of any two sides > third side.",
            "Congruence rules: SSS, SAS, ASA, RHS.",
            "Pythagoras: hypotenuse² = base² + height² (right triangles).")
        q("A 15 m ladder reaches a window 12 m high. How far is its foot from the wall?", "8 m", "9 m", "10 m", "6 m", 1,
            "√(15² − 12²) = √81 = 9 m.")
        q("Which set can be the sides of a triangle?", "2, 3, 6", "3, 4, 8", "5, 6, 10", "1, 2, 3", 2,
            "Sum of any two sides must exceed the third: 5 + 6 > 10 ✓.")
    }

    chapter("data_handling", "Data Handling and Graphs", "Mean, median, mode, pie charts and line graphs.", gen = "data_handling") {
        note("Measures",
            "Mean = sum ÷ count; median = middle value after sorting; mode = most frequent value.",
            "Pie chart: central angle = (value/total) × 360°.",
            "Histogram for grouped data; line graph for change over time.",
            "Coordinates (x, y): x-axis horizontal, y-axis vertical; origin (0, 0).")
        q("In a pie chart showing 360 students, 90 like cricket. The central angle for cricket is:", "45°", "60°", "90°", "120°", 2,
            "90/360 × 360° = 90°.")
        q("The mode of 2, 3, 5, 3, 7, 3, 5 is:", "2", "3", "5", "7", 1,
            "3 occurs most often (3 times).")
    }

    chapter("probability", "Probability", "Chance of events with coins, dice and cards.", gen = "probability") {
        note("Basics",
            "P(E) = favourable outcomes / total outcomes; 0 ≤ P(E) ≤ 1.",
            "Sure event = 1; impossible event = 0; P(not E) = 1 − P(E).",
            "Die: 6 outcomes; two coins: 4 outcomes (HH, HT, TH, TT); a deck has 52 cards (4 suits × 13).")
        q("A die is thrown. Probability of getting a prime number is:", "1/3", "1/2", "2/3", "1/6", 1,
            "Primes on a die: 2, 3, 5 → 3/6 = 1/2.")
        q("Probability of drawing a king from a pack of 52 cards is:", "1/52", "1/13", "1/4", "4/13", 1,
            "4 kings out of 52 → 4/52 = 1/13.")
    }

    chapter("playing_numbers", "Playing with Numbers", "General form of numbers, divisibility and digit puzzles.") {
        note("Key ideas",
            "Two-digit number ab = 10a + b; ab + ba = 11(a + b); ab − ba = 9(a − b).",
            "Three-digit number abc = 100a + 10b + c; abc − cba = 99(a − c).",
            "Divisibility by 11: alternate digit-sum difference is 0 or multiple of 11.")
        q("The sum of a two-digit number and the number formed by reversing its digits is always divisible by:", "9", "10", "11", "13", 2,
            "(10a + b) + (10b + a) = 11(a + b).")
        q("If 3A × 6 = 2B2 is a valid multiplication (A, B are digits), then A + B equals:", "8", "9", "10", "11", 1,
            "6 × A must end in 2, so A = 2 or 7. 32 × 6 = 192 does not start with 2 ✗; 37 × 6 = 222 ✓ → A = 7, B = 2. A + B = 9.")
    }

    chapter("solid_shapes", "Visualising Solid Shapes", "Faces, edges, vertices, nets and Euler's formula.") {
        note("Euler's formula",
            "F + V − E = 2 for polyhedra.",
            "Cube: F 6, V 8, E 12. Triangular prism: F 5, V 6, E 9. Square pyramid: F 5, V 5, E 8.",
            "Cylinder, cone and sphere are not polyhedra (curved surfaces).")
        q("A polyhedron has 7 faces and 10 vertices. Its number of edges is:", "13", "15", "17", "12", 1,
            "F + V − E = 2 → 7 + 10 − E = 2 → E = 15.")
        q("Which of these is NOT a polyhedron?", "Cube", "Prism", "Pyramid", "Cylinder", 3,
            "A cylinder has curved surfaces, so it is not a polyhedron.")
    }

    chapter("percentage", "Percentage and Average", "Percentage change, averages and mixtures.", gen = "average") {
        note("Tips",
            "If price rises by r%, consumption must fall by r/(100 + r) × 100 % to keep expenditure same.",
            "New average after adding a value = (old sum + value)/(n + 1).")
        q("The average of 10 numbers is 40. If each number is increased by 5, the new average is:", "40", "45", "50", "400", 1,
            "Adding 5 to each number adds 5 to the average → 45.")
    }

    chapter("clock_calendar", "Clocks and Calendar", "Angle between hands and day-of-week calculations.", gen = "clock") {
        note("Clock",
            "Angle θ = |30H − 5.5M|. Hands coincide 11 times in 12 hours.",
            "Minute hand moves 6° per minute; hour hand 0.5° per minute.")
        note("Calendar",
            "Odd days: ordinary year 1, leap year 2.",
            "100 years have 5 odd days; 400 years have 0 odd days.")
        q("The angle between the hands of a clock at 4:20 is:", "0°", "10°", "20°", "30°", 1,
            "|30 × 4 − 5.5 × 20| = |120 − 110| = 10°.")
    }
}
