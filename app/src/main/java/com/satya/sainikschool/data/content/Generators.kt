package com.satya.sainikschool.data.content

import com.satya.sainikschool.data.model.Question
import kotlin.math.abs
import kotlin.random.Random

/**
 * Parametric question generators. Every generated question computes its own answer and
 * writes a step-by-step explanation, so practice is unlimited and always correct.
 */
object Generators {

    /** A generator returns (question text, options, answer index, explanation). */
    private class Gen(val text: String, val options: List<String>, val answer: Int, val explanation: String)

    private val registry: Map<String, (Random) -> Gen> = mapOf(
        "lcm_hcf" to ::lcmHcf,
        "fractions" to ::fractions,
        "ratio" to ::ratio,
        "unitary" to ::unitary,
        "profit_loss" to ::profitLoss,
        "simple_interest" to ::simpleInterest,
        "area_perimeter" to ::areaPerimeter,
        "average" to ::average,
        "percentage" to ::percentage,
        "speed_time" to ::speedTime,
        "simplification" to ::simplification,
        "decimals" to ::decimals,
        "roman" to ::roman,
        "units" to ::units,
        "angles" to ::angles,
        "primes" to ::primes,
        "numbers" to ::numbers,
        "time_calc" to ::timeCalc,
        "temperature" to ::temperature,
        "squares" to ::squares,
        "cubes" to ::cubes,
        "exponents" to ::exponents,
        "linear_eq" to ::linearEq,
        "compound_interest" to ::compoundInterest,
        "identities" to ::identities,
        "mensuration" to ::mensuration,
        "triangles" to ::triangles,
        "probability" to ::probability,
        "data_handling" to ::dataHandling,
        "time_work" to ::timeWork,
        "proportion" to ::proportion,
        "polygons" to ::polygons,
        "circles" to ::circles,
        "rational" to ::rational,
        "volume" to ::volume,
        // Reasoning
        "series" to ::series,
        "letter_series" to ::letterSeries,
        "coding" to ::coding,
        "direction" to ::direction,
        "ranking" to ::ranking,
        "analogy" to ::analogy,
        "odd_one" to ::oddOne,
        "calendar" to ::calendar,
        "clock" to ::clock,
        "missing_number" to ::missingNumber,
        "alphabet" to ::alphabet,
    )

    fun has(key: String?) = key != null && registry.containsKey(key)

    fun generate(key: String, subjectId: String, chapterId: String, seed: Long, marks: Int): Question {
        val g = registry.getValue(key)(Random(seed))
        return Question(
            id = "gen_${chapterId}_$seed",
            subjectId = subjectId,
            chapterId = chapterId,
            text = g.text,
            options = g.options,
            answer = g.answer,
            explanation = g.explanation,
            marks = marks,
        )
    }

    // ---------- helpers ----------

    private fun gcd(a: Int, b: Int): Int = if (b == 0) abs(a) else gcd(b, a % b)
    private fun lcm(a: Int, b: Int) = a / gcd(a, b) * b

    /** Builds 4 options: the correct value plus 3 distinct distractors, shuffled. */
    private fun opts(r: Random, correct: String, vararg candidates: String): Pair<List<String>, Int> {
        val set = linkedSetOf(correct)
        for (c in candidates) if (set.size < 4) set.add(c)
        var bump = 1
        while (set.size < 4) { set.add(perturb(correct, bump)); bump++ }
        val list = set.toList().shuffled(r)
        return list to list.indexOf(correct)
    }

    /** Makes a plausible wrong answer near [correct]: 3/8 → 3/9, 172.5° → 173.5°, T → U. */
    private fun perturb(correct: String, k: Int): String {
        Regex("""^(−?)(\d+)/(\d+)$""").find(correct)?.let { m ->
            val (sign, n, d) = m.destructured
            return if (k % 2 == 1) "$sign$n/${d.toInt() + (k + 1) / 2}" else "$sign${n.toInt() + k / 2}/$d"
        }
        Regex("""^(−?)(\d+)(.*)$""").find(correct)?.let { m ->
            val (sign, n, rest) = m.destructured
            return "$sign${n.toInt() + k}$rest"
        }
        if (correct.length == 1 && correct[0].isLetter()) return "${'A' + ((correct[0] - 'A' + k + 1) % 26)}"
        return "$correct${"′".repeat(k)}"
    }

    private fun numOpts(r: Random, correct: Int, vararg d: Int, suffix: String = ""): Pair<List<String>, Int> {
        val set = linkedSetOf(correct)
        for (x in d) if (set.size < 4 && x != correct) set.add(x)
        var k = 1
        while (set.size < 4) { set.add(correct + k * (if (k % 2 == 0) -1 else 1) * (1 + r.nextInt(3))); k++ }
        val list = set.toList().shuffled(r)
        return list.map { "$it$suffix" } to list.indexOf(correct)
    }

    private fun gen(text: String, o: Pair<List<String>, Int>, exp: String) = Gen(text, o.first, o.second, exp)

    private fun primeFactors(n0: Int): String {
        var n = n0; val f = mutableListOf<Int>(); var p = 2
        while (n > 1 && p * p <= n) { while (n % p == 0) { f += p; n /= p }; p++ }
        if (n > 1) f += n
        return f.joinToString(" × ")
    }

    // ---------- Mathematics ----------

    private fun lcmHcf(r: Random): Gen {
        val k = listOf(2, 3, 4, 5, 6, 8, 9, 12).random(r)
        var x: Int; var y: Int
        do { x = r.nextInt(2, 10); y = r.nextInt(2, 10) } while (x == y || gcd(x, y) != 1)
        val a = k * x; val b = k * y
        val h = gcd(a, b); val l = lcm(a, b)
        return if (r.nextBoolean()) gen(
            "Find the HCF of $a and $b.",
            numOpts(r, h, h * 2, h / 2 + 1, l),
            "Prime factorisation:\n$a = ${primeFactors(a)}\n$b = ${primeFactors(b)}\nHCF = product of common prime factors = $h.\nCheck: $a ÷ $h = ${a / h}, $b ÷ $h = ${b / h} and these have no common factor."
        ) else gen(
            "Find the LCM of $a and $b.",
            numOpts(r, l, a * b, h, l * 2),
            "Prime factorisation:\n$a = ${primeFactors(a)}\n$b = ${primeFactors(b)}\nHCF = $h.\nLCM = (a × b) ÷ HCF = ($a × $b) ÷ $h = $l.\nShortcut: LCM × HCF = product of the two numbers."
        )
    }

    private fun fractions(r: Random): Gen {
        val b = listOf(2, 3, 4, 5, 6, 8, 10, 12).random(r)
        val d = listOf(2, 3, 4, 5, 6, 8, 9).random(r)
        val a = r.nextInt(1, b); val c = r.nextInt(1, d)
        val l = lcm(b, d)
        val num = a * (l / b) + c * (l / d)
        val g = gcd(num, l)
        val ans = frac(num / g, l / g)
        return gen(
            "Simplify: $a/$b + $c/$d",
            opts(r, ans, frac(a + c, b + d), frac(num / g + 1, l / g), frac(a * c, b * d)),
            "Step 1: LCM of denominators $b and $d = $l.\nStep 2: $a/$b = ${a * (l / b)}/$l and $c/$d = ${c * (l / d)}/$l.\nStep 3: Add numerators: ${a * (l / b)} + ${c * (l / d)} = $num → $num/$l.\nStep 4: Reduce by HCF $g → $ans.\nTip: Never add denominators directly."
        )
    }

    private fun frac(n: Int, d: Int): String {
        val g = gcd(n, d); val nn = n / g; val dd = d / g
        return if (dd == 1) "$nn" else "$nn/$dd"
    }

    private fun ratio(r: Random): Gen {
        var a: Int; var b: Int
        do { a = r.nextInt(1, 8); b = r.nextInt(1, 8) } while (a == b || gcd(a, b) != 1)
        val unit = r.nextInt(5, 60) * 5
        val total = (a + b) * unit
        return gen(
            "₹$total is divided between Ravi and Amit in the ratio $a : $b. What is Ravi's share?",
            numOpts(r, a * unit, b * unit, total / 2, a * unit + unit, suffix = "").let { (o, i) -> o.map { "₹$it" } to i },
            "Total parts = $a + $b = ${a + b}.\nValue of one part = $total ÷ ${a + b} = ₹$unit.\nRavi's share = $a × $unit = ₹${a * unit}.\n(Amit gets $b × $unit = ₹${b * unit}; check: ${a * unit} + ${b * unit} = $total.)"
        )
    }

    private fun unitary(r: Random): Gen {
        val x = r.nextInt(2, 12); val per = r.nextInt(3, 40); val y = x * per; val z = r.nextInt(2, 20)
        val item = listOf("pens", "notebooks", "pencils", "erasers", "bananas").random(r)
        return gen(
            "If $x $item cost ₹$y, what is the cost of $z $item?",
            numOpts(r, z * per, z * per + per, y + z, z * per - per).let { (o, i) -> o.map { "₹$it" } to i },
            "Unitary method:\nCost of $x $item = ₹$y\nCost of 1 = $y ÷ $x = ₹$per\nCost of $z = $z × $per = ₹${z * per}."
        )
    }

    private fun profitLoss(r: Random): Gen {
        val cp = r.nextInt(5, 60) * 20
        val pct = listOf(5, 10, 15, 20, 25, 30).random(r)
        val profit = r.nextBoolean()
        val change = cp * pct / 100
        val sp = if (profit) cp + change else cp - change
        val word = if (profit) "gain" else "loss"
        return if (r.nextBoolean()) gen(
            "A shopkeeper buys an article for ₹$cp and sells it at a $word of $pct%. Find the selling price.",
            numOpts(r, sp, if (profit) cp - change else cp + change, cp + pct, sp + 10).let { (o, i) -> o.map { "₹$it" } to i },
            "${word.replaceFirstChar { it.uppercase() }} = $pct% of CP = $pct/100 × $cp = ₹$change.\nSP = CP ${if (profit) "+" else "−"} $word = $cp ${if (profit) "+" else "−"} $change = ₹$sp.\nFormula: SP = CP × (100 ${if (profit) "+" else "−"} $word%) / 100."
        ) else gen(
            "An article bought for ₹$cp is sold for ₹$sp. Find the $word per cent.",
            numOpts(r, pct, pct + 5, pct * 2, abs(pct - 5).coerceAtLeast(1), suffix = "%"),
            "${word.replaceFirstChar { it.uppercase() }} = |SP − CP| = |$sp − $cp| = ₹$change.\n$word% = ($word ÷ CP) × 100 = ($change ÷ $cp) × 100 = $pct%.\nRemember: profit/loss % is always calculated on Cost Price."
        )
    }

    private fun simpleInterest(r: Random): Gen {
        val p = r.nextInt(5, 100) * 100; val rate = r.nextInt(2, 13); val t = r.nextInt(1, 6)
        val si = p * rate * t / 100
        return if (r.nextBoolean()) gen(
            "Find the simple interest on ₹$p at $rate% per annum for $t year${if (t > 1) "s" else ""}.",
            numOpts(r, si, si + p, p * rate / 100, si * 2).let { (o, i) -> o.map { "₹$it" } to i },
            "SI = (P × R × T) / 100\n= ($p × $rate × $t) / 100\n= ₹$si."
        ) else gen(
            "What amount will ₹$p become at $rate% simple interest per annum after $t year${if (t > 1) "s" else ""}?",
            numOpts(r, p + si, si, p + si + 100, p + p * rate / 100).let { (o, i) -> o.map { "₹$it" } to i },
            "SI = (P × R × T)/100 = ($p × $rate × $t)/100 = ₹$si.\nAmount = P + SI = $p + $si = ₹${p + si}."
        )
    }

    private fun areaPerimeter(r: Random): Gen {
        val l = r.nextInt(5, 40); val b = r.nextInt(3, l)
        return when (r.nextInt(3)) {
            0 -> gen(
                "Find the area of a rectangle of length $l m and breadth $b m.",
                numOpts(r, l * b, 2 * (l + b), l * b + l, (l + b), suffix = " m²"),
                "Area of rectangle = length × breadth = $l × $b = ${l * b} m².\nDon't confuse with perimeter = 2(l + b) = ${2 * (l + b)} m."
            )
            1 -> gen(
                "Find the perimeter of a rectangle with length $l cm and breadth $b cm.",
                numOpts(r, 2 * (l + b), l * b, l + b, 2 * l + b, suffix = " cm"),
                "Perimeter = 2 × (length + breadth) = 2 × ($l + $b) = 2 × ${l + b} = ${2 * (l + b)} cm."
            )
            else -> {
                val s = r.nextInt(4, 25)
                gen(
                    "The perimeter of a square is ${4 * s} cm. Find its area.",
                    numOpts(r, s * s, 4 * s, s * 4 * s, s * 2, suffix = " cm²"),
                    "Perimeter of square = 4 × side → side = ${4 * s} ÷ 4 = $s cm.\nArea = side² = $s × $s = ${s * s} cm²."
                )
            }
        }
    }

    private fun average(r: Random): Gen {
        val n = r.nextInt(4, 7); val avg = r.nextInt(10, 90)
        val nums = MutableList(n - 1) { avg + r.nextInt(-9, 10) }
        nums += avg * n - nums.sum()
        val sum = nums.sum()
        return gen(
            "Find the average of ${nums.joinToString(", ")}.",
            numOpts(r, avg, avg + 1, avg - 2, sum / (n - 1)),
            "Average = Sum of observations ÷ Number of observations.\nSum = ${nums.joinToString(" + ")} = $sum.\nCount = $n.\nAverage = $sum ÷ $n = $avg."
        )
    }

    private fun percentage(r: Random): Gen {
        val p = listOf(5, 10, 12, 15, 20, 25, 40, 50, 60, 75).random(r)
        val base = r.nextInt(2, 40) * 20
        val ans = base * p / 100
        return gen(
            "What is $p% of $base?",
            numOpts(r, ans, ans * 2, ans + 10, base / p),
            "$p% of $base = ($p / 100) × $base = ${p * base} / 100 = $ans."
        )
    }

    private fun speedTime(r: Random): Gen {
        return if (r.nextBoolean()) {
            val s = r.nextInt(2, 15) * 18
            gen(
                "Convert $s km/h into m/s.",
                numOpts(r, s * 5 / 18, s * 18 / 5, s / 18, s * 5 / 18 + 5, suffix = " m/s"),
                "To convert km/h to m/s multiply by 5/18 (1 km = 1000 m, 1 h = 3600 s).\n$s × 5/18 = ${s * 5 / 18} m/s."
            )
        } else {
            val s = r.nextInt(3, 16) * 5; val t = r.nextInt(2, 8)
            gen(
                "A bus travels at $s km/h. How far will it travel in $t hours?",
                numOpts(r, s * t, s + t, s * (t + 1), s * t / 2, suffix = " km"),
                "Distance = Speed × Time = $s × $t = ${s * t} km.\nRemember the triangle: D = S × T, S = D/T, T = D/S."
            )
        }
    }

    private fun simplification(r: Random): Gen {
        val b = r.nextInt(2, 10); val c = r.nextInt(2, 10); val e = r.nextInt(2, 9)
        val d = e * r.nextInt(2, 9); val a = r.nextInt(5, 50)
        val ans = a + b * c - d / e
        return gen(
            "Simplify using BODMAS: $a + $b × $c − $d ÷ $e",
            numOpts(r, ans, (a + b) * c - d / e, a + b * c - d, ((a + b * c - d) / e)),
            "BODMAS order: Brackets, Of, Division, Multiplication, Addition, Subtraction.\nDivision: $d ÷ $e = ${d / e}\nMultiplication: $b × $c = ${b * c}\nNow: $a + ${b * c} − ${d / e} = $ans."
        )
    }

    private fun decimals(r: Random): Gen {
        val a = r.nextInt(100, 9999); val b = r.nextInt(10, 999)
        val sum = a + b
        fun d2(x: Int) = "%d.%02d".format(x / 100, x % 100)
        return gen(
            "Add: ${d2(a)} + ${d2(b)}",
            opts(r, d2(sum), d2(sum + 10), d2(sum - 1), d2(sum + 100)),
            "Write the numbers one below the other, lining up the decimal points:\n  ${d2(a)}\n+ ${d2(b)}\n= ${d2(sum)}\nAdd as whole numbers and keep the decimal point in place."
        )
    }

    private val romanVals = listOf(1000 to "M", 900 to "CM", 500 to "D", 400 to "CD", 100 to "C", 90 to "XC", 50 to "L", 40 to "XL", 10 to "X", 9 to "IX", 5 to "V", 4 to "IV", 1 to "I")
    private fun toRoman(n0: Int): String { var n = n0; val sb = StringBuilder(); for ((v, s) in romanVals) while (n >= v) { sb.append(s); n -= v }; return sb.toString() }

    private fun roman(r: Random): Gen {
        val n = r.nextInt(14, 2000)
        val parts = mutableListOf<String>(); var x = n
        for ((v, s) in romanVals) while (x >= v) { parts += "$s($v)"; x -= v }
        return if (r.nextBoolean()) gen(
            "Write $n in Roman numerals.",
            opts(r, toRoman(n), toRoman(n + 1), toRoman(n + 10), toRoman(n - 1)),
            "Break $n into place values using the largest Roman symbols first:\n${parts.joinToString(" + ")}\n= ${toRoman(n)}.\nRule: a smaller symbol before a larger one is subtracted (IV = 4, IX = 9, XL = 40, XC = 90, CD = 400, CM = 900)."
        ) else gen(
            "What is the value of the Roman numeral ${toRoman(n)}?",
            numOpts(r, n, n + 10, n - 1, n + 9),
            "Read symbol groups left to right:\n${parts.joinToString(" + ")} = $n."
        )
    }

    private fun units(r: Random): Gen = when (r.nextInt(3)) {
        0 -> { val km = r.nextInt(2, 20); val m = r.nextInt(1, 999)
            gen("Convert $km km $m m into metres.", numOpts(r, km * 1000 + m, km * 100 + m, km + m, km * 1000 + m * 10, suffix = " m"),
                "1 km = 1000 m.\n$km km = ${km * 1000} m.\nTotal = ${km * 1000} + $m = ${km * 1000 + m} m.") }
        1 -> { val kg = r.nextInt(1, 15); val g = r.nextInt(1, 999)
            gen("Express $kg kg $g g in grams.", numOpts(r, kg * 1000 + g, kg * 100 + g, kg + g, kg * 1000 + g + 100, suffix = " g"),
                "1 kg = 1000 g.\n$kg kg = ${kg * 1000} g.\nTotal = ${kg * 1000} + $g = ${kg * 1000 + g} g.") }
        else -> { val l = r.nextInt(1, 12); val ml = r.nextInt(1, 999)
            gen("How many millilitres are there in $l L $ml mL?", numOpts(r, l * 1000 + ml, l * 100 + ml, l + ml, l * 1000 + ml - 100, suffix = " mL"),
                "1 L = 1000 mL.\n$l L = ${l * 1000} mL.\nTotal = ${l * 1000} + $ml = ${l * 1000 + ml} mL.") }
    }

    private fun angles(r: Random): Gen {
        val a = r.nextInt(10, 85)
        return if (r.nextBoolean()) gen(
            "What is the complement of $a°?",
            numOpts(r, 90 - a, 180 - a, 360 - a, 90 + a, suffix = "°"),
            "Complementary angles add up to 90°.\nComplement = 90° − $a° = ${90 - a}°."
        ) else gen(
            "What is the supplement of ${a + 50}°?",
            numOpts(r, 180 - a - 50, 90 - a, 360 - a - 50, 180 - a, suffix = "°"),
            "Supplementary angles add up to 180°.\nSupplement = 180° − ${a + 50}° = ${130 - a}°."
        )
    }

    private val primeList = listOf(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97)

    private fun primes(r: Random): Gen {
        val p = primeList.drop(4).random(r)
        val comps = listOf(21, 27, 33, 39, 49, 51, 57, 63, 69, 77, 81, 87, 91, 93).shuffled(r).take(3)
        val o = (comps.map { it.toString() } + p.toString()).shuffled(r)
        return Gen(
            "Which of the following is a prime number?", o, o.indexOf(p.toString()),
            "A prime number has exactly two factors: 1 and itself.\n$p has no divisor other than 1 and $p.\n" +
                comps.joinToString("\n") { c -> "$c = ${primeFactors(c)} (composite)" } +
                "\nTip: test divisibility only by primes up to √n."
        )
    }

    private fun numbers(r: Random): Gen {
        val n = r.nextInt(10000, 999999)
        val digits = n.toString()
        val pos = r.nextInt(1, digits.length)
        val d = digits[digits.length - 1 - pos].digitToInt()
        if (d == 0) return numbers(r)
        var place = 1; repeat(pos) { place *= 10 }
        val names = listOf("ones", "tens", "hundreds", "thousands", "ten thousands", "lakhs")
        return gen(
            "What is the place value of the digit $d at the ${names[pos]} place in $n?",
            numOpts(r, d * place, d, d * place / 10, d * place * 10),
            "Place value = digit × value of its place.\nThe digit $d is in the ${names[pos]} place (value $place).\nPlace value = $d × $place = ${d * place}.\n(Face value is just $d.)"
        )
    }

    private fun timeCalc(r: Random): Gen {
        val h1 = r.nextInt(6, 12); val m1 = r.nextInt(0, 60); val dh = r.nextInt(1, 5); val dm = r.nextInt(5, 60)
        var endM = m1 + dm; var endH = h1 + dh + endM / 60; endM %= 60
        val fmt = { h: Int, m: Int -> "%d:%02d".format(if (h > 12) h - 12 else h, m) + if (h >= 12) " PM" else " AM" }
        val ans = fmt(endH, endM)
        return gen(
            "A train leaves at ${fmt(h1, m1)} and the journey takes $dh h $dm min. When does it arrive?",
            opts(r, ans, fmt(endH + 1, endM), fmt(endH, (endM + 10) % 60), fmt(endH - 1, endM)),
            "Add minutes: $m1 + $dm = ${m1 + dm} min${if (m1 + dm >= 60) " = 1 h ${(m1 + dm) - 60} min" else ""}.\nAdd hours: $h1 + $dh${if (m1 + dm >= 60) " + 1" else ""} = $endH.\nArrival = $ans."
        )
    }

    private fun temperature(r: Random): Gen {
        val c = r.nextInt(0, 21) * 5
        val f = c * 9 / 5 + 32
        return gen(
            "Convert $c °C into Fahrenheit.",
            numOpts(r, f, f - 32, c + 32, f + 10, suffix = " °F"),
            "F = (9/5) × C + 32\n= (9/5) × $c + 32\n= ${c * 9 / 5} + 32 = $f °F.\nKey facts: water freezes at 0 °C = 32 °F and boils at 100 °C = 212 °F."
        )
    }

    private fun squares(r: Random): Gen {
        val n = r.nextInt(11, 99)
        return gen(
            "Find the square root of ${n * n}.",
            numOpts(r, n, n + 1, n - 2, n + 10),
            "Find by prime factorisation or estimation:\n${n * n} = ${primeFactors(n * n)}\nPair the factors and take one from each pair → $n.\nCheck: $n × $n = ${n * n}.\nUnit digit tip: the square's last digit ${(n * n) % 10} narrows the root's last digit."
        )
    }

    private fun cubes(r: Random): Gen {
        val n = r.nextInt(3, 25)
        return gen(
            "Find the cube root of ${n * n * n}.",
            numOpts(r, n, n + 1, n - 1, n * 3),
            "Prime factorisation: ${n * n * n} = ${primeFactors(n * n * n)}.\nMake groups of three equal factors and take one from each group → $n.\nCheck: $n × $n × $n = ${n * n * n}."
        )
    }

    private fun exponents(r: Random): Gen {
        val base = listOf(2, 3, 5).random(r); val m = r.nextInt(2, 6); val n = r.nextInt(1, 5); val k = r.nextInt(1, m + n)
        val exp = m + n - k
        var v = 1; repeat(exp) { v *= base }
        return gen(
            "Simplify: ($base^$m × $base^$n) ÷ $base^$k",
            numOpts(r, v, v * base, (v / base).coerceAtLeast(1) + if (v / base == v) 1 else 0, v + base),
            "Laws of exponents: aᵐ × aⁿ = aᵐ⁺ⁿ and aᵐ ÷ aⁿ = aᵐ⁻ⁿ.\n$base^$m × $base^$n = $base^${m + n}.\n$base^${m + n} ÷ $base^$k = $base^$exp = $v."
        )
    }

    private fun linearEq(r: Random): Gen {
        val x = r.nextInt(-9, 15); val a = r.nextInt(2, 9); val b = r.nextInt(-20, 21).let { if (it == 0) 7 else it }
        val c = a * x + b
        val bs = if (b >= 0) "+ $b" else "− ${-b}"
        return gen(
            "Solve for x: ${a}x $bs = $c",
            numOpts(r, x, x + 1, -x + if (x == 0) 2 else 0, (c + b) / a + if ((c + b) / a == x) 3 else 0),
            "${a}x $bs = $c\nTranspose ${if (b >= 0) b else -b} to the right side: ${a}x = $c ${if (b >= 0) "− $b" else "+ ${-b}"} = ${c - b}\nDivide by $a: x = ${c - b} ÷ $a = $x.\nCheck: $a × $x $bs = $c ✓"
        )
    }

    private fun compoundInterest(r: Random): Gen {
        val p = r.nextInt(1, 30) * 1000; val rate = listOf(5, 10, 20).random(r)
        val a1 = p + p * rate / 100; val a2 = a1 + a1 * rate / 100
        val ci = a2 - p; val si = 2 * p * rate / 100
        return gen(
            "Find the compound interest on ₹$p at $rate% per annum for 2 years, compounded annually.",
            numOpts(r, ci, si, a2, ci + 100).let { (o, i) -> o.map { "₹$it" } to i },
            "A = P(1 + R/100)ⁿ.\nYear 1: interest = $rate% of $p = ${p * rate / 100}; amount = $a1.\nYear 2: interest = $rate% of $a1 = ${a1 * rate / 100}; amount = $a2.\nCI = A − P = $a2 − $p = ₹$ci.\n(Simple interest would be only ₹$si — CI earns interest on interest.)"
        )
    }

    private fun identities(r: Random): Gen {
        val base = listOf(10, 20, 50, 100).random(r); val d = r.nextInt(1, 9); val plus = r.nextBoolean()
        val n = if (plus) base + d else base - d
        return gen(
            "Using an identity, evaluate $n².",
            numOpts(r, n * n, n * n + 10, base * base + d * d, n * n - 2 * d),
            "Write $n = $base ${if (plus) "+" else "−"} $d.\nUse (a ${if (plus) "+" else "−"} b)² = a² ${if (plus) "+" else "−"} 2ab + b².\n= ${base * base} ${if (plus) "+" else "−"} ${2 * base * d} + ${d * d}\n= ${n * n}."
        )
    }

    private fun mensuration(r: Random): Gen = when (r.nextInt(3)) {
        0 -> { val a = r.nextInt(3, 15)
            gen("Find the total surface area of a cube of edge $a cm.", numOpts(r, 6 * a * a, a * a * a, 4 * a * a, 12 * a, suffix = " cm²"),
                "A cube has 6 equal square faces.\nTSA = 6a² = 6 × $a² = 6 × ${a * a} = ${6 * a * a} cm².\n(Lateral surface area = 4a² = ${4 * a * a} cm².)") }
        1 -> { val a = r.nextInt(4, 20); val b = r.nextInt(2, 12); val h = r.nextInt(2, 10) * 2
            gen("Find the area of a trapezium whose parallel sides are $a cm and $b cm and height is $h cm.",
                numOpts(r, (a + b) * h / 2, (a + b) * h, a * b * h / 2, (a + b + h), suffix = " cm²"),
                "Area of trapezium = ½ × (sum of parallel sides) × height\n= ½ × ($a + $b) × $h = ½ × ${a + b} × $h = ${(a + b) * h / 2} cm².") }
        else -> { val d1 = r.nextInt(3, 15) * 2; val d2 = r.nextInt(3, 15)
            gen("The diagonals of a rhombus are $d1 cm and $d2 cm. Find its area.",
                numOpts(r, d1 * d2 / 2, d1 * d2, d1 + d2, d1 * d2 / 4 + 1, suffix = " cm²"),
                "Area of rhombus = ½ × d₁ × d₂ = ½ × $d1 × $d2 = ${d1 * d2 / 2} cm².") }
    }

    private fun triangles(r: Random): Gen {
        return if (r.nextBoolean()) {
            val (p, q, h) = listOf(Triple(3, 4, 5), Triple(5, 12, 13), Triple(8, 15, 17), Triple(7, 24, 25)).random(r)
            val k = r.nextInt(1, 4)
            gen(
                "In a right-angled triangle the two sides containing the right angle are ${p * k} cm and ${q * k} cm. Find the hypotenuse.",
                numOpts(r, h * k, (p + q) * k, h * k + 1, q * k + 1, suffix = " cm"),
                "Pythagoras theorem: hypotenuse² = base² + perpendicular².\n= ${p * k}² + ${q * k}² = ${p * k * p * k} + ${q * k * q * k} = ${h * k * h * k}.\nHypotenuse = √${h * k * h * k} = ${h * k} cm."
            )
        } else {
            val a = r.nextInt(30, 90); val b = r.nextInt(20, 160 - a)
            gen(
                "Two angles of a triangle are $a° and $b°. Find the third angle.",
                numOpts(r, 180 - a - b, 360 - a - b, 90 - (a + b) / 2, 180 - a, suffix = "°"),
                "Angle sum property: the three angles of a triangle add to 180°.\nThird angle = 180° − ($a° + $b°) = 180° − ${a + b}° = ${180 - a - b}°."
            )
        }
    }

    private fun probability(r: Random): Gen {
        val red = r.nextInt(2, 9); val blue = r.nextInt(2, 9); val green = r.nextInt(1, 7)
        val total = red + blue + green
        val ans = frac(red, total)
        return gen(
            "A bag has $red red, $blue blue and $green green balls. One ball is drawn at random. What is the probability that it is red?",
            opts(r, ans, frac(blue, total), frac(red, blue + green), frac(green, total).let { if (it == ans) "1/$total" else it }),
            "Probability = favourable outcomes ÷ total outcomes.\nFavourable (red) = $red.\nTotal balls = $red + $blue + $green = $total.\nP(red) = $red/$total${if (gcd(red, total) > 1) " = $ans" else ""}."
        )
    }

    private fun dataHandling(r: Random): Gen {
        val nums = List(7) { r.nextInt(1, 40) }
        val sorted = nums.sorted()
        return if (r.nextBoolean()) gen(
            "Find the median of: ${nums.joinToString(", ")}",
            numOpts(r, sorted[3], nums[3], sorted[2], sorted[4]),
            "Step 1: Arrange in ascending order: ${sorted.joinToString(", ")}.\nStep 2: n = 7 (odd), so median = value at position (n + 1)/2 = 4th.\nMedian = ${sorted[3]}."
        ) else {
            val range = sorted.last() - sorted.first()
            gen(
                "Find the range of the data: ${nums.joinToString(", ")}",
                numOpts(r, range, sorted.last(), sorted.last() + sorted.first(), range + 1),
                "Range = Highest value − Lowest value = ${sorted.last()} − ${sorted.first()} = $range."
            )
        }
    }

    private fun timeWork(r: Random): Gen {
        val (a, b) = listOf(12 to 24, 10 to 15, 20 to 30, 6 to 12, 12 to 36, 15 to 30, 18 to 36, 4 to 12, 21 to 42, 20 to 60).random(r)
        val t = a * b / (a + b)
        return gen(
            "A can finish a job in $a days and B can finish it in $b days. In how many days will they finish it working together?",
            numOpts(r, t, (a + b) / 2, a + b, b - a, suffix = " days"),
            "A's one-day work = 1/$a, B's one-day work = 1/$b.\nTogether per day = 1/$a + 1/$b = ${(a + b) / gcd(a + b, a * b)}/${a * b / gcd(a + b, a * b)}.\nDays = reciprocal = ($a × $b)/($a + $b) = ${a * b}/${a + b} = $t days."
        )
    }

    private fun proportion(r: Random): Gen {
        val men = r.nextInt(2, 13); val days = r.nextInt(2, 13); val work = men * days
        val divisors = (2..work).filter { work % it == 0 && it != men }
        val men2 = divisors.random(r)
        val d2 = work / men2
        return gen(
            "$men workers can build a wall in $days days. How many days will $men2 workers take to build the same wall?",
            numOpts(r, d2, days * men2 / men.coerceAtLeast(1) + 1, days, d2 + 2, suffix = " days"),
            "More workers → fewer days (inverse proportion), so men × days stays constant.\n$men × $days = $work man-days.\nDays for $men2 workers = $work ÷ $men2 = $d2 days."
        )
    }

    private fun polygons(r: Random): Gen {
        val n = listOf(5, 6, 8, 9, 10, 12, 15, 18, 20).random(r)
        return if (r.nextBoolean()) gen(
            "Find the measure of each exterior angle of a regular polygon with $n sides.",
            numOpts(r, 360 / n, 180 - 360 / n, 180 / n, 360 / n + 10, suffix = "°"),
            "The exterior angles of any polygon add to 360°.\nFor a regular polygon each exterior angle = 360° ÷ n = 360° ÷ $n = ${360 / n}°.\n(Each interior angle = 180° − ${360 / n}° = ${180 - 360 / n}°.)"
        ) else gen(
            "Find the sum of the interior angles of a polygon with $n sides.",
            numOpts(r, (n - 2) * 180, n * 180, (n - 1) * 180, 360, suffix = "°"),
            "Sum of interior angles = (n − 2) × 180°\n= ($n − 2) × 180° = ${n - 2} × 180° = ${(n - 2) * 180}°."
        )
    }

    private fun circles(r: Random): Gen {
        val rad = r.nextInt(1, 6) * 7
        return if (r.nextBoolean()) gen(
            "Find the circumference of a circle of radius $rad cm. (Take π = 22/7)",
            numOpts(r, 2 * 22 * rad / 7, 22 * rad * rad / 7, 22 * rad / 7, 2 * rad, suffix = " cm"),
            "Circumference = 2πr = 2 × 22/7 × $rad = ${2 * 22 * rad / 7} cm."
        ) else gen(
            "Find the area of a circle of radius $rad cm. (Take π = 22/7)",
            numOpts(r, 22 * rad * rad / 7, 2 * 22 * rad / 7, 22 * rad / 7 * 2 + rad, rad * rad, suffix = " cm²"),
            "Area = πr² = 22/7 × $rad × $rad = ${22 * rad * rad / 7} cm²."
        )
    }

    private fun rational(r: Random): Gen {
        val a = r.nextInt(1, 9); val b = r.nextInt(2, 10); val c = r.nextInt(1, 9); val d = r.nextInt(2, 10)
        val num = a * c; val den = b * d
        val ans = frac(num, den)
        return gen(
            "Find the product: (−$a/$b) × (−$c/$d)",
            opts(r, ans, "−$ans", frac(a + c, b + d), frac(a * d, b * c)),
            "Negative × negative = positive.\nMultiply numerators: $a × $c = $num; denominators: $b × $d = $den.\nResult = $num/$den${if (gcd(num, den) > 1) " = $ans (lowest terms)" else ""}."
        )
    }

    private fun volume(r: Random): Gen {
        val l = r.nextInt(2, 20); val b = r.nextInt(2, 15); val h = r.nextInt(2, 12)
        return gen(
            "Find the volume of a cuboid of length $l cm, breadth $b cm and height $h cm.",
            numOpts(r, l * b * h, 2 * (l * b + b * h + h * l), l * b + h, l * b * h + l, suffix = " cm³"),
            "Volume of cuboid = l × b × h = $l × $b × $h = ${l * b * h} cm³.\n(1 litre = 1000 cm³.)"
        )
    }

    // ---------- Reasoning ----------

    private fun series(r: Random): Gen = when (r.nextInt(4)) {
        0 -> { val a = r.nextInt(1, 30); val d = r.nextInt(2, 12); val s = List(5) { a + it * d }
            gen("Find the next number: ${s.joinToString(", ")}, ?", numOpts(r, a + 5 * d, a + 5 * d + 1, a + 6 * d, a + 4 * d + 1),
                "Differences: ${(1 until 5).joinToString(", ") { "+$d" }} (constant).\nNext = ${s.last()} + $d = ${a + 5 * d}.") }
        1 -> { val a = r.nextInt(1, 5); val m = r.nextInt(2, 4); val s = List(5) { var v = a; repeat(it) { v *= m }; v }
            gen("Find the next number: ${s.joinToString(", ")}, ?", numOpts(r, s.last() * m, s.last() + m, s.last() * m + 1, s.last() * 2 + 1),
                "Each term is multiplied by $m.\nNext = ${s.last()} × $m = ${s.last() * m}.") }
        2 -> { val st = r.nextInt(1, 8); val s = List(5) { (st + it) * (st + it) }
            gen("Find the next number: ${s.joinToString(", ")}, ?", numOpts(r, (st + 5) * (st + 5), s.last() + 2 * (st + 4), (st + 5) * (st + 5) + 1, s.last() + 10),
                "The terms are perfect squares: ${(0 until 5).joinToString(", ") { "${st + it}²" }}.\nNext = ${st + 5}² = ${(st + 5) * (st + 5)}.") }
        else -> { val a = r.nextInt(1, 10); val s = mutableListOf(a); for (i in 1..5) s += s.last() + i * 2
            gen("Find the next number: ${s.take(5).joinToString(", ")}, ?", numOpts(r, s[5], s[5] + 2, s[4] + 8, s[5] - 1),
                "Differences: +2, +4, +6, +8 (increase by 2 each time).\nNext difference = +10 → ${s[4]} + 10 = ${s[5]}.") }
    }

    private fun letterSeries(r: Random): Gen {
        val start = r.nextInt(0, 8); val step = r.nextInt(2, 4)
        val letters = List(5) { 'A' + start + it * step }
        val next = 'A' + start + 5 * step
        return gen(
            "Find the next letter: ${letters.joinToString(", ")}, ?",
            opts(r, "$next", "${next + 1}", "${next - 1}", "${next + step}"),
            "Positions: ${letters.joinToString(", ") { "$it=${it - 'A' + 1}" }}.\nEach letter moves $step steps forward.\nNext = ${letters.last()} + $step = $next (${next - 'A' + 1})."
        )
    }

    private val words = listOf("BOOK", "TREE", "FISH", "LAMP", "DESK", "GOLD", "MILK", "SHIP", "KING", "ROSE", "CAKE", "BIRD", "NEST", "WOLF", "DRUM", "COIN")
    private fun shift(w: String, k: Int) = w.map { 'A' + ((it - 'A' + k + 26) % 26) }.joinToString("")

    private fun coding(r: Random): Gen {
        val (w1, w2) = words.shuffled(r).take(2)
        val k = listOf(1, 2, 3, -1, -2).random(r)
        val ans = shift(w2, k)
        return gen(
            "In a certain code, $w1 is written as ${shift(w1, k)}. How is $w2 written in that code?",
            opts(r, ans, shift(w2, k + 1), shift(w2, -k), shift(w2, k).reversed()),
            "Compare letters: ${w1.zip(shift(w1, k)).joinToString(", ") { "${it.first}→${it.second}" }}.\nEach letter moves ${abs(k)} step${if (abs(k) > 1) "s" else ""} ${if (k > 0) "forward" else "backward"}.\nApply to $w2: ${w2.zip(ans).joinToString(", ") { "${it.first}→${it.second}" }} = $ans."
        )
    }

    private fun direction(r: Random): Gen {
        val (a, b, c) = listOf(Triple(3, 4, 5), Triple(6, 8, 10), Triple(5, 12, 13), Triple(8, 15, 17), Triple(9, 12, 15)).random(r)
        return if (r.nextBoolean()) gen(
            "Riya walks $a km towards North, then turns right and walks $b km. How far is she from the starting point?",
            numOpts(r, c, a + b, b - a, c + 1, suffix = " km"),
            "North then right turn → East. The paths form a right angle.\nDistance = √($a² + $b²) = √(${a * a} + ${b * b}) = √${c * c} = $c km."
        ) else {
            val dirs = listOf("North", "East", "South", "West")
            val startIdx = r.nextInt(4); val turns = List(3) { if (r.nextBoolean()) 1 else -1 }
            var idx = startIdx
            val stepsTxt = turns.map { t -> idx = (idx + t + 4) % 4; if (t == 1) "right" else "left" }
            val ans = dirs[idx]
            gen(
                "A man faces ${dirs[startIdx]}. He turns ${stepsTxt.joinToString(", then ")}. Which direction is he facing now?",
                opts(r, ans, dirs[(idx + 1) % 4], dirs[(idx + 2) % 4], dirs[(idx + 3) % 4]),
                "Right turn = 90° clockwise, left turn = 90° anticlockwise (N → E → S → W clockwise).\nStart: ${dirs[startIdx]}\n" +
                    run { var j = startIdx; turns.joinToString("\n") { t -> j = (j + t + 4) % 4; "Turn ${if (t == 1) "right" else "left"} → ${dirs[j]}" } } +
                    "\nFinal direction = $ans."
            )
        }
    }

    private fun ranking(r: Random): Gen {
        val top = r.nextInt(5, 30); val bottom = r.nextInt(5, 30)
        val total = top + bottom - 1
        return gen(
            "In a class, Arjun is ${ord(top)} from the top and ${ord(bottom)} from the bottom. How many students are there in the class?",
            numOpts(r, total, top + bottom, total - 1, top + bottom + 1),
            "Total = (rank from top) + (rank from bottom) − 1\n= $top + $bottom − 1 = $total.\nWe subtract 1 because Arjun is counted twice."
        )
    }

    private fun ord(n: Int) = "$n" + when { n % 100 in 11..13 -> "th"; n % 10 == 1 -> "st"; n % 10 == 2 -> "nd"; n % 10 == 3 -> "rd"; else -> "th" }

    private fun analogy(r: Random): Gen = when (r.nextInt(3)) {
        0 -> { val a = r.nextInt(2, 12); val b = r.nextInt(2, 15).let { if (it == a) it + 1 else it }
            gen("Complete the analogy: $a : ${a * a} :: $b : ?", numOpts(r, b * b, b * a, b * b + 1, b * 2),
                "Relation: second number is the square of the first ($a² = ${a * a}).\nSo $b² = ${b * b}.") }
        1 -> { val a = r.nextInt(2, 9); val b = r.nextInt(2, 9).let { if (it == a) it + 1 else it }
            gen("Complete the analogy: $a : ${a * a * a} :: $b : ?", numOpts(r, b * b * b, b * b, b * b * b + 1, b * 3),
                "Relation: second number is the cube of the first ($a³ = ${a * a * a}).\nSo $b³ = ${b * b * b}.") }
        else -> { val k = r.nextInt(1, 5); val c1 = 'A' + r.nextInt(0, 10); val c2 = 'A' + r.nextInt(10, 20)
            gen("Complete the analogy: $c1 : ${c1 + k} :: $c2 : ?", opts(r, "${c2 + k}", "${c2 + k + 1}", "${c2 - k}", "${c2 + 2 * k + 1}"),
                "$c1 (${c1 - 'A' + 1}) → ${c1 + k} (${c1 - 'A' + 1 + k}): move $k step(s) forward.\nSo $c2 (${c2 - 'A' + 1}) → ${c2 + k}.") }
    }

    private fun oddOne(r: Random): Gen {
        val k = listOf(3, 4, 6, 7, 8, 9, 11).random(r)
        val mults = (2..12).shuffled(r).take(3).map { it * k }
        var odd: Int
        do { odd = r.nextInt(10, 100) } while (odd % k == 0)
        val o = (mults + odd).shuffled(r).map { it.toString() }
        return Gen(
            "Find the odd one out: ${o.joinToString(", ")}",
            o, o.indexOf(odd.toString()),
            "${mults.joinToString(", ")} are all multiples of $k (${mults.joinToString(", ") { "$k × ${it / k}" }}).\n$odd is not divisible by $k, so it is the odd one."
        )
    }

    private val days = listOf("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday")

    private fun calendar(r: Random): Gen {
        val start = r.nextInt(7); val date = r.nextInt(8, 31)
        val idx = (start + (date - 1)) % 7
        return gen(
            "If the 1st of a month is a ${days[start]}, what day of the week is the ${ord(date)} of that month?",
            opts(r, days[idx], days[(idx + 1) % 7], days[(idx + 6) % 7], days[(idx + 2) % 7]),
            "Days passed after the 1st = $date − 1 = ${date - 1}.\nOdd days = ${date - 1} mod 7 = ${(date - 1) % 7}.\n${days[start]} + ${(date - 1) % 7} days = ${days[idx]}.\nTip: the same weekday repeats every 7 days (1, 8, 15, 22, 29)."
        )
    }

    private fun clock(r: Random): Gen {
        val h = r.nextInt(1, 12); val m = listOf(0, 10, 20, 30, 40, 50, 15, 45).random(r)
        val raw = abs(30 * h - 5.5 * m)
        val ang = if (raw > 180) 360 - raw else raw
        fun f(d: Double) = if (d % 1.0 == 0.0) d.toInt().toString() else d.toString()
        val ans = "${f(ang)}°"
        return gen(
            "What is the angle between the hour and minute hands of a clock at $h:${"%02d".format(m)}?",
            opts(r, ans, "${f(ang + 15)}°", "${f(360 - ang)}°", "${f(ang + 30)}°"),
            "Formula: θ = |30H − 5.5M|\n= |30 × $h − 5.5 × $m| = |${30 * h} − ${f(5.5 * m)}| = ${f(raw)}°${if (raw > 180) "\nSince it is more than 180°, take 360° − ${f(raw)}° = ${f(ang)}°" else ""}.\nAngle = $ans."
        )
    }

    private fun missingNumber(r: Random): Gen {
        val rows = List(3) { val a = r.nextInt(2, 10); val b = r.nextInt(2, 10); Triple(a, b, a * b + a) }
        return gen(
            "Find the missing number:\n${rows[0].first}  ${rows[0].second}  → ${rows[0].third}\n${rows[1].first}  ${rows[1].second}  → ${rows[1].third}\n${rows[2].first}  ${rows[2].second}  → ?",
            numOpts(r, rows[2].third, rows[2].first * rows[2].second, rows[2].third + rows[2].second, rows[2].first + rows[2].second),
            "Find the rule from the complete rows:\n${rows[0].first} × ${rows[0].second} + ${rows[0].first} = ${rows[0].third}\n${rows[1].first} × ${rows[1].second} + ${rows[1].first} = ${rows[1].third}\nRule: (first × second) + first.\nSo ${rows[2].first} × ${rows[2].second} + ${rows[2].first} = ${rows[2].third}."
        )
    }

    private fun alphabet(r: Random): Gen {
        val n = r.nextInt(3, 13)
        return if (r.nextBoolean()) gen(
            "Which letter is ${ord(n)} from the right end of the English alphabet?",
            opts(r, "${'Z' - (n - 1)}", "${'A' + (n - 1)}", "${'Z' - n}", "${'Z' - (n - 2)}"),
            "Position from right = n → position from left = 27 − n = ${27 - n}.\nThe ${ord(27 - n)} letter from the left is ${'Z' - (n - 1)}.\nTip: remember the pairs EJOTY = 5, 10, 15, 20, 25."
        ) else {
            val w = words.random(r)
            val sum = w.sumOf { it - 'A' + 1 }
            gen(
                "If A = 1, B = 2, … Z = 26, what is the sum of the letters of the word $w?",
                numOpts(r, sum, sum + 1, sum - 2, sum + 5),
                "${w.map { "$it=${it - 'A' + 1}" }.joinToString(", ")}\nSum = ${w.map { it - 'A' + 1 }.joinToString(" + ")} = $sum."
            )
        }
    }
}
