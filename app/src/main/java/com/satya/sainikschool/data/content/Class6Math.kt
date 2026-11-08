package com.satya.sainikschool.data.content

import com.satya.sainikschool.data.model.ExamClass

/** AISSEE Class 6 — Mathematics: 50 questions × 3 marks = 150 marks. */
val class6Math = subject("c6_math", "Mathematics", ExamClass.CLASS_6, 50, 3, "math") {

    chapter("natural_numbers", "Natural & Whole Numbers", "Place value, face value, Indian & international systems, comparing and rounding numbers.", gen = "numbers") {
        note("Number systems",
            "Natural numbers: 1, 2, 3, … (counting numbers). Whole numbers: 0, 1, 2, 3, …",
            "Indian system: Ones, Tens, Hundreds, Thousands, Ten-thousands, Lakhs, Ten-lakhs, Crores (commas: 1,00,00,000).",
            "International system: Ones … Thousands, Millions, Billions (commas: 10,000,000).",
            "1 lakh = 100 thousand; 10 lakh = 1 million; 1 crore = 10 million.")
        note("Place value vs face value",
            "Face value of a digit = the digit itself.",
            "Place value = digit × value of its place.",
            "Place value of 0 is always 0.",
            example = "In 7,45,312 the place value of 4 is 40,000; face value is 4.")
        note("Useful facts",
            "Smallest natural number = 1; smallest whole number = 0.",
            "Greatest 5-digit number = 99,999; smallest 6-digit number = 1,00,000.",
            "Successor = number + 1; predecessor = number − 1 (0 has no whole-number predecessor).",
            "Rounding to nearest 10: look at ones digit — 5 or more rounds up.")
        q("How many zeros are there in one crore?", "6", "7", "8", "9", 1,
            "1 crore = 1,00,00,000 → 7 zeros.")
        q("The difference between the place value and face value of 6 in 56,437 is:", "5994", "6000", "5940", "6", 0,
            "Place value of 6 = 6000; face value = 6. Difference = 6000 − 6 = 5994.")
        q("Which is the smallest 4-digit number formed using the digits 0, 3, 5, 7 (each once)?", "0357", "3057", "3075", "3507", 1,
            "A number cannot start with 0. Put the smallest non-zero digit 3 first, then 0, 5, 7 → 3057.")
    }

    chapter("operations", "Operations on Numbers & Simplification (BODMAS)", "Addition, subtraction, multiplication, division and the BODMAS rule.", gen = "simplification") {
        note("BODMAS order",
            "B – Brackets: (), {}, [] solved from inner to outer.",
            "O – Of (means multiply, e.g. ½ of 20 = 10).",
            "D, M – Division and Multiplication, left to right.",
            "A, S – Addition and Subtraction, left to right.",
            example = "18 − 12 ÷ 4 × 2 = 18 − 3 × 2 = 18 − 6 = 12.")
        note("Properties",
            "Commutative: a + b = b + a; a × b = b × a.",
            "Associative: (a + b) + c = a + (b + c).",
            "Distributive: a × (b + c) = a × b + a × c.",
            "Any number × 0 = 0; division by 0 is not defined.",
            "Dividend = Divisor × Quotient + Remainder.")
        q("Find the value of 48 ÷ 6 × 2 + 4.", "8", "20", "4", "28", 1,
            "Division and multiplication left to right: 48 ÷ 6 = 8; 8 × 2 = 16; 16 + 4 = 20.")
        q("A number when divided by 9 gives quotient 15 and remainder 4. The number is:", "139", "135", "131", "149", 0,
            "Dividend = Divisor × Quotient + Remainder = 9 × 15 + 4 = 139.")
        q("Using distributive property, 99 × 25 equals:", "2425", "2475", "2500", "2575", 1,
            "99 × 25 = (100 − 1) × 25 = 2500 − 25 = 2475.")
    }

    chapter("lcm_hcf", "LCM and HCF", "Factors, multiples, prime factorisation, LCM and HCF and word problems.", gen = "lcm_hcf") {
        note("Key definitions",
            "HCF (Highest Common Factor): largest number that divides all given numbers.",
            "LCM (Lowest Common Multiple): smallest number divisible by all given numbers.",
            "For two numbers: LCM × HCF = product of the numbers.",
            "HCF of co-primes is 1; LCM of co-primes is their product.")
        note("Methods",
            "Prime factorisation: HCF = product of common primes (lowest powers); LCM = all primes (highest powers).",
            "Division method for HCF: divide larger by smaller, then divisor by remainder until remainder is 0.",
            example = "12 = 2² × 3, 18 = 2 × 3² → HCF = 2 × 3 = 6; LCM = 2² × 3² = 36.")
        note("Word-problem clues",
            "\"Largest/greatest … that divides exactly\" → HCF.",
            "\"Smallest/least … that is divisible / bells ring together again\" → LCM.")
        q("Three bells ring at intervals of 6, 8 and 12 minutes. If they ring together at 9:00 AM, when will they next ring together?", "9:24 AM", "9:36 AM", "9:48 AM", "10:12 AM", 0,
            "LCM(6, 8, 12) = 24 minutes. They ring together again at 9:00 + 0:24 = 9:24 AM.")
        q("The HCF of two numbers is 8 and their product is 384. Their LCM is:", "48", "40", "56", "64", 0,
            "LCM = Product ÷ HCF = 384 ÷ 8 = 48.")
        q("The greatest number that divides 43 and 91 leaving remainder 7 in each case is:", "9", "12", "14", "18", 1,
            "Subtract remainder: 43 − 7 = 36 and 91 − 7 = 84. HCF(36, 84) = 12.")
    }

    chapter("fractions", "Fractions", "Types of fractions, equivalent fractions, comparison and operations.", gen = "fractions") {
        note("Types",
            "Proper fraction: numerator < denominator (3/5).",
            "Improper fraction: numerator ≥ denominator (7/4).",
            "Mixed fraction: whole + proper fraction (1¾ = 7/4).",
            "Like fractions have same denominators; unlike fractions have different denominators.",
            "Unit fraction has numerator 1 (1/7).")
        note("Operations",
            "Add/subtract: make denominators equal using LCM.",
            "Multiply: numerator × numerator, denominator × denominator.",
            "Divide: multiply by the reciprocal (a/b ÷ c/d = a/b × d/c).",
            "Compare: cross-multiply — for a/b and c/d compare a × d with b × c.",
            example = "2/3 ÷ 4/9 = 2/3 × 9/4 = 18/12 = 3/2.")
        q("Which fraction is the largest: 2/3, 3/5, 5/8, 7/12?", "2/3", "3/5", "5/8", "7/12", 0,
            "LCM of 3, 5, 8, 12 = 120 → 80/120, 72/120, 75/120, 70/120. Largest is 80/120 = 2/3.")
        q("What is 3/4 of 2/9 of 36?", "6", "8", "9", "12", 0,
            "2/9 of 36 = 8; 3/4 of 8 = 6.")
        q("Convert 23/6 into a mixed fraction.", "3 5/6", "3 1/6", "4 1/6", "2 5/6", 0,
            "23 ÷ 6 = 3 remainder 5 → 3 5/6.")
    }

    chapter("decimals", "Decimal Numbers", "Place value in decimals, conversion between fractions and decimals, operations.", gen = "decimals") {
        note("Basics",
            "Places after the decimal point: tenths (1/10), hundredths (1/100), thousandths (1/1000).",
            "0.5 = 1/2, 0.25 = 1/4, 0.75 = 3/4, 0.2 = 1/5, 0.125 = 1/8.",
            "Adding zeros at the end of a decimal does not change its value (0.5 = 0.50).")
        note("Operations",
            "Add/subtract: line up the decimal points.",
            "Multiply: multiply as whole numbers; total decimal places = sum of decimal places.",
            "Multiply by 10, 100, 1000: shift decimal point 1, 2, 3 places right.",
            example = "0.4 × 0.03 = 0.012 (1 + 2 = 3 decimal places).")
        q("0.07 × 0.3 equals:", "0.21", "0.021", "0.0021", "2.1", 1,
            "7 × 3 = 21. Decimal places = 2 + 1 = 3 → 0.021.")
        q("Which is the greatest: 0.5, 0.05, 0.505, 0.055?", "0.5", "0.05", "0.505", "0.055", 2,
            "Write with 3 places: 0.500, 0.050, 0.505, 0.055. Largest is 0.505.")
    }

    chapter("ratio", "Ratio and Proportion", "Simplifying ratios, dividing in a given ratio, proportion and the rule of three.", gen = "ratio") {
        note("Ratio",
            "Ratio a : b compares two quantities of the same kind and unit = a/b.",
            "Simplify by dividing both terms by their HCF (12 : 18 = 2 : 3).",
            "To divide N in ratio a : b → shares are aN/(a + b) and bN/(a + b).")
        note("Proportion",
            "a : b :: c : d means a/b = c/d.",
            "Product of extremes = product of means: a × d = b × c.",
            "Mean proportional of a and c is √(a × c).",
            example = "Find x if 3 : 5 :: 12 : x → 3x = 60 → x = 20.")
        q("If 4 : 7 :: 20 : x, the value of x is:", "28", "35", "30", "40", 1,
            "4 × x = 7 × 20 → x = 140 ÷ 4 = 35.")
        q("Express 50 paise : ₹2 as a ratio in its simplest form.", "1 : 4", "25 : 1", "1 : 2", "4 : 1", 0,
            "Convert to same unit: ₹2 = 200 paise. 50 : 200 = 1 : 4.")
    }

    chapter("unitary", "Unitary Method", "Find value of one unit first, then the required number of units.", gen = "unitary") {
        note("Method",
            "Step 1: find the value of ONE item (divide).",
            "Step 2: multiply by the required number of items.",
            "Direct variation: more items → more cost.",
            "Inverse variation: more workers → less time (multiply first, then divide).",
            example = "If 6 pens cost ₹90, then 1 pen = ₹15, so 10 pens = ₹150.")
        q("A car covers 180 km in 4 litres of petrol. How far will it go in 7 litres?", "300 km", "315 km", "320 km", "330 km", 1,
            "1 litre → 180 ÷ 4 = 45 km. 7 litres → 45 × 7 = 315 km.")
        q("8 men can do a work in 12 days. In how many days will 6 men do it?", "9", "14", "16", "18", 2,
            "Inverse: 8 × 12 = 96 man-days. 96 ÷ 6 = 16 days.")
    }

    chapter("percentage", "Percentage", "Meaning of per cent, conversion and finding a per cent of a quantity.", gen = "percentage") {
        note("Basics",
            "Per cent means per hundred: x% = x/100.",
            "Fraction to %: multiply by 100 (3/4 = 75%).",
            "Increase by x%: new = old × (100 + x)/100.",
            "Common values: 1/2 = 50%, 1/4 = 25%, 1/5 = 20%, 1/8 = 12.5%, 1/3 = 33⅓%.")
        q("A student scored 360 marks out of 450. What is his percentage?", "75%", "80%", "85%", "90%", 1,
            "360/450 × 100 = 80%.")
        q("If 30% of a number is 45, the number is:", "135", "150", "160", "175", 1,
            "30% of N = 45 → N = 45 × 100/30 = 150.")
    }

    chapter("profit_loss", "Profit and Loss", "Cost price, selling price, profit/loss and percentage.", gen = "profit_loss") {
        note("Formulae",
            "Profit = SP − CP (when SP > CP). Loss = CP − SP (when CP > SP).",
            "Profit % = Profit/CP × 100; Loss % = Loss/CP × 100.",
            "SP = CP × (100 + P%)/100 or CP × (100 − L%)/100.",
            "Overheads (transport, repair) are added to CP.",
            example = "CP = ₹500, profit 20% → SP = 500 × 120/100 = ₹600.")
        q("Rahul bought a cycle for ₹2,400 and spent ₹100 on repairs. He sold it for ₹2,750. Find his profit %.", "8%", "10%", "12%", "14.5%", 1,
            "Total CP = 2400 + 100 = 2500. Profit = 250. Profit% = 250/2500 × 100 = 10%.")
        q("By selling an article for ₹720 a man loses 10%. What was the cost price?", "₹792", "₹800", "₹810", "₹850", 1,
            "SP = 90% of CP → CP = 720 × 100/90 = ₹800.")
    }

    chapter("simple_interest", "Simple Interest", "Principal, rate, time, interest and amount.", gen = "simple_interest") {
        note("Formulae",
            "SI = (P × R × T)/100.",
            "Amount A = P + SI.",
            "P = (SI × 100)/(R × T); R = (SI × 100)/(P × T); T = (SI × 100)/(P × R).",
            "Time in months must be converted to years (6 months = ½ year).")
        q("In how many years will ₹800 earn ₹240 interest at 6% p.a. simple interest?", "4", "5", "6", "3", 1,
            "T = SI × 100/(P × R) = 240 × 100/(800 × 6) = 24000/4800 = 5 years.")
        q("A sum doubles itself in 10 years at simple interest. The rate is:", "5%", "10%", "15%", "20%", 1,
            "Doubling → SI = P. R = 100 × P/(P × 10) = 10%.")
    }

    chapter("average", "Average", "Mean of observations and simple average problems.", gen = "average") {
        note("Basics",
            "Average = Sum of observations / Number of observations.",
            "Sum = Average × Number.",
            "Average of first n natural numbers = (n + 1)/2.",
            "If each value is increased by k, the average also increases by k.")
        q("The average of five numbers is 24. If one number is excluded the average becomes 22. The excluded number is:", "28", "30", "32", "34", 2,
            "Sum of 5 = 120. Sum of 4 = 88. Excluded = 120 − 88 = 32.")
        q("Average of first 10 natural numbers is:", "5", "5.5", "6", "10", 1,
            "(n + 1)/2 = 11/2 = 5.5.")
    }

    chapter("speed_time", "Speed, Distance and Time", "Relationship between speed, distance and time; unit conversion.", gen = "speed_time") {
        note("Formulae",
            "Speed = Distance/Time; Distance = Speed × Time; Time = Distance/Speed.",
            "km/h → m/s: × 5/18. m/s → km/h: × 18/5.",
            "Average speed for equal distances at x and y = 2xy/(x + y).")
        q("A boy walks 3 km in 45 minutes. His speed in km/h is:", "3", "4", "4.5", "5", 1,
            "45 min = 3/4 h. Speed = 3 ÷ 3/4 = 4 km/h.")
        q("A train 150 m long passes a pole in 15 seconds. Its speed is:", "10 km/h", "36 km/h", "40 km/h", "54 km/h", 1,
            "Speed = 150/15 = 10 m/s = 10 × 18/5 = 36 km/h.")
    }

    chapter("area_perimeter", "Area and Perimeter", "Perimeter and area of rectangles, squares and simple combined figures.", gen = "area_perimeter") {
        note("Formulae",
            "Rectangle: P = 2(l + b), A = l × b.",
            "Square: P = 4a, A = a²; diagonal = a√2.",
            "Triangle: A = ½ × base × height.",
            "Path around a rectangle of width w: outer length = l + 2w, outer breadth = b + 2w.",
            "1 m² = 10,000 cm²; 1 hectare = 10,000 m².")
        q("A rectangular field is 50 m long and 30 m broad. Find the cost of fencing it at ₹12 per metre.", "₹1,920", "₹1,800", "₹18,000", "₹960", 0,
            "Perimeter = 2(50 + 30) = 160 m. Cost = 160 × 12 = ₹1,920.")
        q("If the side of a square is doubled, its area becomes:", "double", "three times", "four times", "half", 2,
            "Area = (2a)² = 4a² → four times.")
    }

    chapter("volume", "Volume of Cube and Cuboid", "Volume and capacity of cubes and cuboids.", gen = "volume") {
        note("Formulae",
            "Volume of cuboid = l × b × h.",
            "Volume of cube = a³.",
            "1 litre = 1000 cm³; 1 m³ = 1000 litres.",
            "Number of small cubes that fit = big volume ÷ small volume.")
        q("How many cubes of side 2 cm can be cut from a cube of side 6 cm?", "3", "9", "27", "36", 2,
            "216 ÷ 8 = 27.")
    }

    chapter("geometry", "Lines, Angles and Plane Figures", "Types of angles, lines, triangles, quadrilaterals and circles.", gen = "angles") {
        note("Angles",
            "Acute < 90°, Right = 90°, Obtuse between 90° and 180°, Straight = 180°, Reflex between 180° and 360°, Complete = 360°.",
            "Complementary angles sum to 90°; supplementary angles sum to 180°.",
            "Vertically opposite angles are equal.")
        note("Plane figures",
            "Triangle: angle sum 180°. Equilateral (all sides equal), Isosceles (two equal), Scalene (none equal).",
            "Quadrilateral: angle sum 360°. Square, rectangle, rhombus, parallelogram, trapezium.",
            "Circle: diameter = 2 × radius; chord, arc, sector, segment.",
            "Polygon names: pentagon (5), hexagon (6), heptagon (7), octagon (8), nonagon (9), decagon (10).")
        note("Lines",
            "Line has no end points; ray has one; line segment has two.",
            "Parallel lines never meet; perpendicular lines meet at 90°.",
            "Through one point infinite lines can pass; through two points exactly one line.")
        q("The angle between the hands of a clock at 3 o'clock is:", "acute", "right", "obtuse", "straight", 1,
            "At 3:00 the hands are 3 × 30° = 90° apart → right angle.")
        q("How many diagonals does a hexagon have?", "6", "8", "9", "12", 2,
            "n(n − 3)/2 = 6 × 3/2 = 9.")
        q("The longest chord of a circle is the:", "radius", "arc", "diameter", "tangent", 2,
            "The diameter passes through the centre and is the longest chord.")
    }

    chapter("primes", "Prime & Composite Numbers, Divisibility", "Prime numbers, composite numbers and divisibility rules.", gen = "primes") {
        note("Primes",
            "Prime: exactly two factors (2, 3, 5, 7, 11 …). 2 is the only even prime.",
            "1 is neither prime nor composite.",
            "There are 25 primes between 1 and 100.",
            "Twin primes differ by 2 (3 & 5, 11 & 13). Co-primes have HCF 1.")
        note("Divisibility rules",
            "2: last digit even. 3: sum of digits divisible by 3. 4: last two digits divisible by 4.",
            "5: last digit 0 or 5. 6: divisible by 2 and 3. 8: last three digits divisible by 8.",
            "9: sum of digits divisible by 9. 10: last digit 0.",
            "11: difference of sums of alternate digits is 0 or a multiple of 11.")
        q("Which number is divisible by 9?", "45,672", "36,981", "12,345", "70,201", 1,
            "Digit sum of 36,981 = 3 + 6 + 9 + 8 + 1 = 27, divisible by 9.")
        q("How many prime numbers are there between 1 and 20?", "7", "8", "9", "10", 1,
            "2, 3, 5, 7, 11, 13, 17, 19 → 8 primes.")
    }

    chapter("roman", "Roman Numerals", "Reading and writing Roman numerals.", gen = "roman") {
        note("Symbols",
            "I = 1, V = 5, X = 10, L = 50, C = 100, D = 500, M = 1000.",
            "A symbol is repeated at most 3 times (III = 3, XXX = 30).",
            "V, L and D are never repeated and never subtracted.",
            "Smaller before larger means subtract: IV = 4, IX = 9, XL = 40, XC = 90, CD = 400, CM = 900.")
        q("XLIX equals:", "49", "51", "59", "69", 0,
            "XL = 40, IX = 9 → 49.")
    }

    chapter("units", "Measurement & Conversion of Units", "Length, mass, capacity and money conversions.", gen = "units") {
        note("Conversions",
            "Length: 1 km = 1000 m, 1 m = 100 cm, 1 cm = 10 mm.",
            "Mass: 1 kg = 1000 g, 1 quintal = 100 kg, 1 tonne = 1000 kg.",
            "Capacity: 1 L = 1000 mL.",
            "Money: ₹1 = 100 paise.",
            "Big to small unit → multiply; small to big unit → divide.")
        q("How many 250 mL glasses can be filled from a 3 L jug?", "10", "12", "14", "15", 1,
            "3 L = 3000 mL; 3000 ÷ 250 = 12.")
    }

    chapter("time_calc", "Time and Calendar", "12/24-hour clock, elapsed time, days and leap years.", gen = "time_calc") {
        note("Clock",
            "1 hour = 60 minutes; 1 minute = 60 seconds; 1 day = 24 hours.",
            "24-hour time: 3:15 PM = 15:15; 12:00 midnight = 00:00.",
            "Elapsed time: subtract start time from end time, borrowing 60 minutes if needed.")
        note("Calendar",
            "A year has 365 days (52 weeks + 1 day); leap year has 366 days.",
            "Leap year: divisible by 4; century years must be divisible by 400 (2000 was leap, 1900 was not).",
            "Months with 31 days: Jan, Mar, May, Jul, Aug, Oct, Dec.")
        q("Which of these is a leap year?", "1900", "2100", "2024", "2023", 2,
            "2024 is divisible by 4 and not a century year → leap year. 1900 and 2100 are not divisible by 400.")
        q("17:45 hours in the 12-hour clock is:", "5:45 AM", "5:45 PM", "7:45 PM", "3:45 PM", 1,
            "17 − 12 = 5 → 5:45 PM.")
    }

    chapter("temperature", "Temperature", "Celsius and Fahrenheit scales and conversion.", gen = "temperature") {
        note("Scales",
            "F = (9/5)C + 32; C = (5/9)(F − 32).",
            "Water freezes at 0 °C (32 °F) and boils at 100 °C (212 °F).",
            "Normal human body temperature ≈ 37 °C (98.6 °F).",
            "−40 °C = −40 °F.")
        q("Normal body temperature in Fahrenheit is about:", "37 °F", "98.6 °F", "100 °F", "212 °F", 1,
            "37 °C × 9/5 + 32 = 98.6 °F.")
    }
}
