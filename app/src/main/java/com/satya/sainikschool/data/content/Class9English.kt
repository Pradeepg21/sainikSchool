package com.satya.sainikschool.data.content

import com.satya.sainikschool.data.model.ExamClass

/** AISSEE Class 9 — English: 25 questions × 2 marks = 50 marks. */
val class9English = subject("c9_eng", "English", ExamClass.CLASS_9, 25, 2, "language") {

    chapter("comprehension", "Reading Comprehension", "Factual, inferential and vocabulary questions on unseen passages.") {
        note("Approach",
            "Skim the passage for the main idea, then read questions and scan for details.",
            "Inference questions: the answer is implied, not stated — choose the most reasonable option.",
            "Tone words: optimistic, critical, humorous, sarcastic, neutral.")
        q("Passage: \"Major Somnath Sharma, the first recipient of the Param Vir Chakra, fought at Badgam in 1947 with a fractured arm, refusing to leave his men.\" The passage mainly highlights his:", "intelligence", "courage and leadership", "wealth", "humour", 1,
            "Fighting while injured and staying with his men shows courage and leadership.")
        q("In the passage above, 'recipient' means:", "giver", "one who receives", "soldier", "officer", 1,
            "A recipient is a person who receives something.")
    }

    chapter("tenses", "Tenses", "All 12 tense forms and their correct usage.") {
        note("Forms",
            "Present perfect continuous: has/have been + V-ing + since/for (She has been studying for two hours).",
            "Past perfect: the earlier of two past actions (The train had left before we reached).",
            "Future perfect: will have + V3 (By June, I will have finished).",
            "No future tense after 'if/when' in time clauses: If it rains, we will stay (not 'will rain').")
        q("By the time we reached the station, the train ___.", "left", "has left", "had left", "was leaving", 2,
            "Earlier of two past actions → past perfect 'had left'.")
        q("She ___ in this school since 2020.", "teaches", "is teaching", "has been teaching", "taught", 2,
            "Action started in the past and continuing, with 'since' → present perfect continuous.")
        q("If he ___ hard, he will pass.", "works", "will work", "worked", "would work", 0,
            "In first conditional, the if-clause takes simple present.")
    }

    chapter("voice", "Active and Passive Voice", "Changing voice in all tenses.") {
        note("Rules",
            "Object becomes subject; verb becomes 'be + V3'; subject goes after 'by'.",
            "Simple present: is/am/are + V3. Simple past: was/were + V3. Continuous: being + V3. Perfect: been + V3. Modals: modal + be + V3.",
            "Imperative: 'Let + object + be + V3' (Open the door → Let the door be opened).")
        q("Change into passive: She writes a letter.", "A letter was written by her.", "A letter is written by her.", "A letter is being written by her.", "A letter has been written by her.", 1,
            "Simple present → is + V3: 'A letter is written by her.'")
        q("Change into passive: They are building a bridge.", "A bridge is built by them.", "A bridge is being built by them.", "A bridge has been built by them.", "A bridge was being built by them.", 1,
            "Present continuous → is being + V3.")
    }

    chapter("narration", "Direct and Indirect Speech", "Reporting statements, questions and commands.") {
        note("Rules",
            "Past reporting verb shifts tenses back: is → was, has → had, will → would, can → could.",
            "Words change: now → then, today → that day, tomorrow → the next day, here → there, this → that.",
            "Questions: asked + if/whether (yes/no) or wh-word, in statement order.",
            "Commands: told/ordered + to + verb.")
        q("He said, \"I am tired.\" — Indirect:", "He said that he is tired.", "He said that he was tired.", "He said that I was tired.", "He told that he is tired.", 1,
            "Past reporting verb → 'am' becomes 'was'; 'I' becomes 'he'.")
        q("The teacher said to us, \"Sit down.\" — Indirect:", "The teacher told us to sit down.", "The teacher said us to sit down.", "The teacher told us sit down.", "The teacher asked that we sit down.", 0,
            "Command → told + object + to-infinitive.")
    }

    chapter("agreement", "Subject–Verb Agreement & Error Spotting", "Finding grammatical errors in sentences.") {
        note("Common errors",
            "'One of the + plural noun' takes a singular verb (One of the boys is absent).",
            "'The number of' → singular; 'a number of' → plural.",
            "Uncountable nouns (information, furniture, advice, luggage) are singular and have no plural.",
            "'Scarcely … when' and 'hardly … when' (not 'than'); 'no sooner … than'.")
        q("Spot the error: One of my friends (A) / are going (B) / to Delhi (C) / tomorrow (D).", "A", "B", "C", "D", 1,
            "'One of …' is singular → 'is going'.")
        q("Choose the correct sentence.", "He gave me many advices.", "He gave me much advices.", "He gave me a piece of advice.", "He gave me an advice.", 2,
            "'Advice' is uncountable — use 'a piece of advice'.")
        q("No sooner had he arrived ___ it started raining.", "when", "than", "then", "that", 1,
            "'No sooner' is followed by 'than'.")
    }

    chapter("vocabulary", "Synonyms, Antonyms and Word Usage", "Word meanings, opposites and confusing words.") {
        note("High-frequency words",
            "Abundant – plentiful (≠ scarce). Candid – frank. Diligent – hard-working (≠ lazy).",
            "Obsolete – out of date (≠ modern). Hostile – unfriendly (≠ friendly). Meagre – scanty (≠ ample).",
            "Gallant – brave. Valour – bravery. Vigilant – watchful.")
        note("Confusing pairs",
            "affect (verb) / effect (noun); principal (head) / principle (rule); stationary (still) / stationery (paper items); lose / loose; accept / except.")
        q("Choose the synonym of 'Vigilant'.", "Careless", "Watchful", "Sleepy", "Angry", 1,
            "Vigilant means alert and watchful.")
        q("Choose the antonym of 'Abundant'.", "Plentiful", "Scarce", "Huge", "Rich", 1,
            "Abundant means plentiful; opposite is scarce.")
        q("The ___ of the school addressed the assembly.", "principle", "principal", "principel", "principality", 1,
            "Principal means the head of a school; principle means a rule.")
        q("Choose the synonym of 'Valour'.", "Fear", "Bravery", "Value", "Victory", 1,
            "Valour means great courage, especially in battle.")
    }

    chapter("idioms", "Idioms, Phrases and One-word Substitution", "Meanings of idioms and single words for phrases.") {
        note("Idioms",
            "Bite the bullet – face a difficult situation bravely. Burn the midnight oil – work late at night.",
            "At the eleventh hour – at the last moment. Call it a day – stop working.",
            "Spill the beans – reveal a secret. A blessing in disguise – good result from apparent misfortune.")
        note("One-word substitution",
            "A person who can speak two languages – bilingual. Fear of heights – acrophobia.",
            "Government by the people – democracy. One who looks on the bright side – optimist.",
            "A speech made without preparation – extempore. Study of birds – ornithology.")
        q("'Burn the midnight oil' means:", "waste oil", "work late into the night", "cause a fire", "sleep early", 1,
            "It means to stay up late working or studying.")
        q("A person who always looks on the bright side:", "Pessimist", "Optimist", "Realist", "Atheist", 1,
            "An optimist expects good outcomes.")
        q("'At the eleventh hour' means:", "at 11 o'clock", "at the last moment", "very early", "never", 1,
            "It means at the latest possible time.")
    }

    chapter("prepositions", "Prepositions, Articles and Determiners", "Correct use of prepositions, articles and determiners.") {
        note("Prepositions",
            "Good at, fond of, afraid of, interested in, depend on, agree with (person), agree to (proposal), prefer to, superior to, senior to.",
            "Divide between two / among many.")
        note("Determiners",
            "few (almost none, countable), a few (some), little (almost none, uncountable), a little (some).",
            "much (uncountable), many (countable), each (individually), every (all).")
        q("He is fond ___ music.", "in", "of", "at", "with", 1,
            "The correct collocation is 'fond of'.")
        q("There is ___ milk left; buy some more.", "few", "a few", "little", "many", 2,
            "'Milk' is uncountable; 'little' means almost none, hence buy more.")
        q("This book is superior ___ that one.", "than", "to", "from", "over", 1,
            "Superior, inferior, senior, junior take 'to', not 'than'.")
    }

    chapter("sentence_arrangement", "Sentence Rearrangement & Completion", "Para-jumbles and filling blanks logically.") {
        note("Tips",
            "Find the opening sentence (introduces the topic; no pronoun referring back).",
            "Link pronouns to nouns, and 'then/after that/finally' to sequences.",
            "In fill-in-the-blanks, read the whole sentence and check grammar and meaning.")
        q("Arrange: P: He saved money. Q: Ravi wanted a bicycle. R: Finally he bought one. S: So he worked part-time.", "QSPR", "QPSR", "SQPR", "QRSP", 0,
            "Want (Q) → work (S) → save (P) → buy (R).")
        q("Complete: The soldier was awarded a medal for his ___ in battle.", "cowardice", "bravery", "laziness", "absence", 1,
            "Medals are awarded for bravery.")
    }

    chapter("clauses", "Clauses, Conjunctions and Transformation", "Simple, compound and complex sentences; question tags.") {
        note("Types of sentence",
            "Simple: one main clause. Compound: two main clauses joined by and/but/or/so.",
            "Complex: one main clause + subordinate clause (because, although, when, which).")
        note("Question tags",
            "Positive statement → negative tag (She is ready, isn't she?).",
            "Negative statement → positive tag (They didn't come, did they?).",
            "I am → aren't I? Let's → shall we?")
        q("He is a good player, ___?", "is he", "isn't he", "wasn't he", "doesn't he", 1,
            "Positive statement → negative tag with the same auxiliary 'is'.")
        q("Let's go for a walk, ___?", "will we", "shall we", "don't we", "won't we", 1,
            "'Let's' takes the tag 'shall we?'.")
        q("Although he was ill, he attended school. This is a ___ sentence.", "simple", "compound", "complex", "imperative", 2,
            "It has a subordinate clause beginning with 'although' → complex.")
    }
}
