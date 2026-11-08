package com.satya.sainikschool.data.content

import com.satya.sainikschool.data.model.ExamClass

/**
 * AISSEE Class 6 — Language: 25 questions × 2 marks = 50 marks.
 * Candidates may choose one of 13 Indian languages; this app covers English.
 */
val class6Language = subject("c6_lang", "Language (English)", ExamClass.CLASS_6, 25, 2, "language") {

    chapter("comprehension", "Reading Comprehension", "Read a short passage and answer questions on facts, meaning and inference.") {
        note("How to attempt",
            "Read the questions first, then the passage — you will know what to look for.",
            "Answer only from the passage, not from outside knowledge.",
            "For word-meaning questions, read the full sentence in which the word appears.",
            "Title questions: choose the option that covers the whole passage, not one line.")
        q("Passage: \"The ant worked hard all summer, storing grain. The grasshopper sang and played. When winter came, the grasshopper had no food.\" What lesson does the passage teach?",
            "Singing is good", "We should plan for the future", "Ants are strong", "Winter is cold", 1,
            "The ant prepared for winter while the grasshopper did not — the moral is to plan ahead.")
        q("In the passage above, 'storing' means:", "selling", "keeping for later", "eating", "throwing", 1,
            "To store means to keep something to use later.")
    }

    chapter("nouns", "Nouns, Gender and Number", "Kinds of nouns, collective nouns, masculine/feminine and plurals.") {
        note("Kinds of nouns",
            "Proper (Delhi, Ganga), Common (city, river), Collective (army, flock), Material (gold, wood), Abstract (honesty, courage).")
        note("Collective nouns",
            "A flock of sheep, a herd of cattle, a pride of lions, a swarm of bees, a bunch of grapes, a fleet of ships, a pack of wolves, a school of fish.")
        note("Gender & number",
            "Lion–lioness, horse–mare, nephew–niece, hero–heroine, drake–duck, bull–cow.",
            "Plurals: child–children, ox–oxen, mouse–mice, tooth–teeth, leaf–leaves, sheep–sheep, deer–deer, criterion–criteria.")
        q("Choose the collective noun: A ___ of soldiers.", "herd", "army", "flock", "bunch", 1,
            "A group of soldiers is called an army (or a troop).")
        q("The feminine of 'nephew' is:", "aunt", "niece", "nephewess", "sister", 1,
            "Nephew – niece.")
        q("The plural of 'mouse' is:", "mouses", "mice", "mices", "mouse", 1,
            "Mouse is an irregular noun; plural is mice.")
        q("'Honesty' is a/an ___ noun.", "proper", "collective", "abstract", "material", 2,
            "Honesty is a quality that cannot be touched — an abstract noun.")
    }

    chapter("pronouns", "Pronouns", "Personal, possessive, reflexive and relative pronouns.") {
        note("Usage",
            "Subject forms: I, we, you, he, she, it, they. Object forms: me, us, you, him, her, it, them.",
            "Possessive: mine, ours, yours, his, hers, theirs (no apostrophe).",
            "Reflexive: myself, ourselves, yourself, himself, herself, itself, themselves.",
            "Relative: who (people), which (things), that (both), whose (possession).")
        q("Neither of the boys has done ___ homework.", "their", "his", "them", "its", 1,
            "'Neither' is singular, so use 'his'.")
        q("This is the boy ___ won the prize.", "which", "whom", "who", "whose", 2,
            "'Who' is used for a person acting as the subject.")
    }

    chapter("verbs", "Verbs and Tenses", "Present, past and future tenses; regular and irregular verbs.") {
        note("Tense forms",
            "Simple present: She writes. Present continuous: She is writing. Present perfect: She has written.",
            "Simple past: She wrote. Past continuous: She was writing. Past perfect: She had written.",
            "Simple future: She will write.")
        note("Irregular verbs",
            "go–went–gone, see–saw–seen, write–wrote–written, eat–ate–eaten, run–ran–run, sing–sang–sung, swim–swam–swum, take–took–taken.")
        note("Clue words",
            "Every day/always/usually → simple present. Now/at present → present continuous.",
            "Yesterday/last year/ago → simple past. Already/just/yet → present perfect.")
        q("She ___ to school every day.", "go", "goes", "going", "gone", 1,
            "Habitual action with a singular subject → simple present 'goes'.")
        q("They ___ football when it started raining.", "play", "were playing", "are playing", "have played", 1,
            "An action in progress in the past interrupted by another → past continuous.")
        q("The past participle of 'swim' is:", "swam", "swum", "swimmed", "swimming", 1,
            "swim – swam – swum.")
    }

    chapter("adjectives", "Adjectives and Adverbs", "Describing words and degrees of comparison.") {
        note("Degrees of comparison",
            "tall – taller – tallest; good – better – best; bad – worse – worst; many – more – most; little – less – least.",
            "Use 'than' with comparative; 'the' with superlative.")
        note("Adverbs",
            "Adverbs describe verbs, adjectives or other adverbs: quickly, very, often, here.",
            "Kinds: manner (slowly), time (now), place (there), frequency (always), degree (too).")
        q("Ravi is the ___ boy in the class.", "tall", "taller", "tallest", "more tall", 2,
            "Comparison among more than two → superlative with 'the'.")
        q("Identify the adverb: The tortoise moved slowly.", "tortoise", "moved", "slowly", "the", 2,
            "'Slowly' tells how it moved — adverb of manner.")
    }

    chapter("prepositions", "Prepositions, Articles & Conjunctions", "Small words that join and show position — in, on, at, a, an, the, and, but.") {
        note("Prepositions",
            "at – exact time/point (at 5 o'clock), on – days/dates (on Monday), in – months/years/large places (in May, in India).",
            "between – two; among – more than two. since – point of time; for – period of time.")
        note("Articles",
            "'a' before consonant sounds (a university, a one-rupee coin); 'an' before vowel sounds (an hour, an honest man).",
            "'the' before unique things (the sun), rivers, oceans, mountain ranges, superlatives.")
        note("Conjunctions",
            "and (addition), but (contrast), or (choice), because (reason), so (result), although (concession).")
        q("He has been ill ___ Monday.", "for", "since", "from", "by", 1,
            "'Since' is used with a point of time.")
        q("She is ___ honest girl.", "a", "an", "the", "no article", 1,
            "'Honest' begins with a vowel sound (h is silent) → 'an'.")
        q("I wanted to go out ___ it was raining.", "and", "or", "but", "so", 2,
            "Contrast between wanting and rain → 'but'.")
    }

    chapter("vocabulary", "Synonyms and Antonyms", "Words with similar and opposite meanings.") {
        note("Synonyms",
            "brave – courageous, big – huge, quick – rapid, happy – glad, begin – commence, ancient – old, difficult – hard.")
        note("Antonyms",
            "ancient – modern, brave – cowardly, victory – defeat, generous – mean, increase – decrease, accept – reject, rough – smooth.",
            "Prefixes make antonyms: un- (happy → unhappy), dis- (honest → dishonest), im- (possible → impossible), in- (correct → incorrect).")
        q("Choose the synonym of 'Courageous'.", "Timid", "Brave", "Weak", "Lazy", 1,
            "Courageous means brave.")
        q("Choose the antonym of 'Victory'.", "Win", "Success", "Defeat", "Triumph", 2,
            "The opposite of victory is defeat.")
        q("Choose the antonym of 'Generous'.", "Kind", "Mean", "Rich", "Large", 1,
            "Generous means giving freely; the opposite is mean/selfish.")
        q("Choose the synonym of 'Commence'.", "End", "Begin", "Stop", "Pause", 1,
            "Commence means to begin.")
    }

    chapter("one_word", "One-word Substitution & Idioms", "Replace a phrase with one word; meanings of common idioms.") {
        note("One-word substitutes",
            "One who cannot read or write – illiterate. A place where bees are kept – apiary.",
            "Life history written by oneself – autobiography. One who loves his country – patriot.",
            "A place where birds are kept – aviary. One who eats only plants – vegetarian/herbivore.",
            "A person who walks on foot – pedestrian. Medicine to fight infection – antibiotic.")
        note("Common idioms",
            "A piece of cake – very easy. Break the ice – start a conversation.",
            "Once in a blue moon – very rarely. Hit the books – study hard.",
            "Cry over spilt milk – regret what can't be undone. Under the weather – unwell.")
        q("One who loves his country is called a:", "traitor", "patriot", "soldier", "leader", 1,
            "A patriot loves and supports his country.")
        q("'Once in a blue moon' means:", "every night", "very rarely", "at full moon", "never", 1,
            "The idiom means something that happens very rarely.")
        q("A place where birds are kept:", "Apiary", "Aviary", "Aquarium", "Zoo", 1,
            "Aviary – birds; apiary – bees; aquarium – fish.")
    }

    chapter("spelling", "Spelling and Sentence Correction", "Spot the correctly spelt word and the correct sentence.") {
        note("Commonly misspelt",
            "necessary, separate, beautiful, receive, believe, government, February, Wednesday, environment, knowledge, address, tomorrow.",
            "'i before e except after c' — believe, receive (with exceptions).")
        q("Choose the correctly spelt word.", "Neccessary", "Necessary", "Necesary", "Neccesary", 1,
            "Necessary has one 'c' and double 's'.")
        q("Choose the correct sentence.", "He don't like milk.", "He doesn't likes milk.", "He doesn't like milk.", "He not like milk.", 2,
            "With 'does/doesn't' the main verb stays in base form: 'doesn't like'.")
    }

    chapter("sentence", "Sentences, Subject–Verb Agreement", "Kinds of sentences, subject and predicate, agreement and jumbled sentences.") {
        note("Kinds of sentences",
            "Declarative (statement), Interrogative (question), Imperative (command/request), Exclamatory (strong feeling).")
        note("Agreement",
            "Singular subject → singular verb (The boy runs). Plural subject → plural verb (The boys run).",
            "Each/every/either/neither take singular verbs.",
            "'Along with', 'as well as' do not change the number of the main subject.")
        q("'Please close the door.' is a/an ___ sentence.", "declarative", "imperative", "exclamatory", "interrogative", 1,
            "A request or command is an imperative sentence.")
        q("Each of the students ___ a book.", "have", "has", "are having", "were", 1,
            "'Each' takes a singular verb → has.")
        q("Rearrange: (P) is (Q) the capital (R) New Delhi (S) of India", "RPQS", "QSPR", "RQPS", "PRQS", 0,
            "New Delhi is the capital of India → R P Q S.")
    }
}
