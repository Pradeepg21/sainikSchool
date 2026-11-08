package com.satya.sainikschool.data.content

import com.satya.sainikschool.data.model.ExamClass

/** AISSEE Class 6 — General Knowledge (Science & Social Studies): 25 questions × 2 marks = 50 marks. */
val class6GK = subject("c6_gk", "General Knowledge", ExamClass.CLASS_6, 25, 2, "globe") {

    chapter("plants", "Plants and Photosynthesis", "Parts of plants, photosynthesis and plant adaptations.") {
        note("Parts and functions",
            "Root – absorbs water and minerals, holds the plant. Stem – carries water and food.",
            "Leaf – kitchen of the plant (photosynthesis). Flower – reproductive part. Fruit – ripened ovary.",
            "Stomata – tiny pores on leaves for gas exchange and transpiration.")
        note("Photosynthesis",
            "Carbon dioxide + water → (sunlight, chlorophyll) → glucose + oxygen.",
            "Chlorophyll gives leaves their green colour.",
            "Plants like cactus have spines (modified leaves) to reduce water loss.",
            "Insectivorous plant: pitcher plant, Venus flytrap.")
        q("Which gas do plants release during photosynthesis?", "Carbon dioxide", "Oxygen", "Nitrogen", "Hydrogen", 1,
            "Plants take in CO₂ and release oxygen during photosynthesis.")
        q("Which part of the plant is called its 'kitchen'?", "Root", "Stem", "Leaf", "Flower", 2,
            "Leaves make food by photosynthesis, so they are called the kitchen of the plant.")
        q("The pitcher plant traps insects because the soil lacks:", "water", "nitrogen", "oxygen", "sunlight", 1,
            "Insectivorous plants grow in nitrogen-poor soil and get nitrogen from insects.")
    }

    chapter("animals", "Animals and Their Habitats", "Classification, adaptations and interesting animal facts.") {
        note("Groups",
            "Mammals: give birth, feed milk (whale, bat, human). Birds: feathers, lay eggs. Reptiles: scales (snake, lizard).",
            "Amphibians live on land and water (frog). Fish breathe through gills.",
            "Herbivores eat plants, carnivores eat meat, omnivores eat both.")
        note("Facts",
            "Largest animal: blue whale. Fastest land animal: cheetah. Tallest: giraffe.",
            "National animal of India: tiger. National bird: peacock. National aquatic animal: Gangetic dolphin.",
            "Camel – ship of the desert. Bat – only mammal that can truly fly.")
        q("Which of the following is a mammal?", "Shark", "Whale", "Crocodile", "Penguin", 1,
            "Whales give birth to young and feed them milk — they are mammals.")
        q("The national aquatic animal of India is:", "Crocodile", "Gangetic dolphin", "Blue whale", "Turtle", 1,
            "The Gangetic river dolphin was declared the national aquatic animal in 2009.")
        q("Frogs breathe through their skin and:", "gills only", "lungs", "spiracles", "trachea", 1,
            "Adult frogs breathe through lungs and moist skin; tadpoles use gills.")
    }

    chapter("human_body", "Human Body and Health", "Organ systems, bones, senses and diseases.") {
        note("Organs and systems",
            "Heart pumps blood; lungs for breathing; kidneys filter blood; brain controls the body.",
            "Adult human has 206 bones; the smallest bone is the stapes (ear); the longest is the femur (thigh).",
            "Largest organ: skin. Largest gland: liver.")
        note("Food & nutrition",
            "Carbohydrates and fats give energy; proteins build the body; vitamins and minerals protect.",
            "Deficiency diseases: scurvy (vitamin C), rickets (vitamin D), night blindness (vitamin A), beriberi (vitamin B1), goitre (iodine), anaemia (iron).")
        q("How many bones does an adult human have?", "106", "206", "306", "212", 1,
            "An adult human skeleton has 206 bones.")
        q("Scurvy is caused by the deficiency of:", "Vitamin A", "Vitamin C", "Vitamin D", "Iron", 1,
            "Vitamin C deficiency causes scurvy (bleeding gums).")
        q("Which organ purifies blood in our body?", "Heart", "Lungs", "Kidneys", "Stomach", 2,
            "Kidneys filter waste from the blood and form urine.")
        q("Which is the largest organ of the human body?", "Liver", "Skin", "Brain", "Heart", 1,
            "Skin is the largest organ; the liver is the largest internal organ/gland.")
    }

    chapter("matter", "Matter, Materials and Simple Machines", "States of matter, changes, magnets, light and force.") {
        note("Matter",
            "Three states: solid, liquid, gas. Melting, freezing, evaporation, condensation, sublimation (camphor).",
            "Physical change is reversible (melting ice); chemical change makes new substance (burning paper, rusting).")
        note("Physics basics",
            "Magnets have north and south poles; like poles repel, unlike attract.",
            "Light travels in straight lines; shadows form when light is blocked.",
            "Simple machines: lever, pulley, wheel and axle, inclined plane, wedge, screw.",
            "Sound cannot travel through vacuum.")
        q("Which of these is a chemical change?", "Melting of ice", "Rusting of iron", "Tearing paper", "Dissolving sugar", 1,
            "Rusting forms a new substance (iron oxide) — a chemical change.")
        q("Camphor changes directly from solid to gas. This is called:", "evaporation", "condensation", "sublimation", "melting", 2,
            "Change from solid directly to gas is sublimation.")
        q("A see-saw is an example of a:", "pulley", "lever", "wedge", "screw", 1,
            "A see-saw turns about a fixed point (fulcrum) — a lever.")
    }

    chapter("solar_system", "Earth and the Solar System", "Planets, the Moon, Earth's movements, latitudes and longitudes.") {
        note("Solar system",
            "Eight planets: Mercury, Venus, Earth, Mars, Jupiter, Saturn, Uranus, Neptune.",
            "Largest planet: Jupiter. Smallest/closest to Sun: Mercury. Hottest: Venus. Red planet: Mars.",
            "Earth is called the blue planet. The Moon is Earth's only natural satellite.")
        note("Earth's movements",
            "Rotation (24 hours) causes day and night.",
            "Revolution (365¼ days) causes seasons.",
            "Equator 0° latitude; Prime Meridian 0° longitude passes through Greenwich.",
            "Tropic of Cancer (23½° N) passes through 8 Indian states.")
        q("Which planet is known as the Red Planet?", "Venus", "Mars", "Jupiter", "Saturn", 1,
            "Iron oxide on its surface makes Mars look red.")
        q("Day and night are caused by Earth's:", "revolution", "rotation", "tilt only", "orbit", 1,
            "Rotation of Earth on its axis causes day and night.")
        q("Which is the largest planet of our solar system?", "Saturn", "Earth", "Jupiter", "Neptune", 2,
            "Jupiter is the largest planet.")
        q("India's lunar mission that landed near the Moon's south pole in 2023 was:", "Chandrayaan-1", "Chandrayaan-2", "Chandrayaan-3", "Mangalyaan", 2,
            "Chandrayaan-3's Vikram lander touched down on 23 August 2023; the day is celebrated as National Space Day.")
    }

    chapter("india_geo", "India — Geography", "States, rivers, mountains, deserts and natural resources.") {
        note("Facts",
            "India has 28 states and 8 Union Territories.",
            "Largest state by area: Rajasthan; most populous: Uttar Pradesh; smallest: Goa.",
            "Longest river: Ganga. Highest peak in India: Kangchenjunga. Thar desert in Rajasthan.",
            "Standard meridian of India: 82½° E passing near Mirzapur.")
        note("Rivers & neighbours",
            "Himalayan rivers: Ganga, Yamuna, Brahmaputra, Indus. Peninsular: Godavari (Dakshin Ganga), Krishna, Kaveri, Narmada, Tapi (flow west).",
            "Neighbours: Pakistan, China, Nepal, Bhutan, Bangladesh, Myanmar, Afghanistan; Sri Lanka & Maldives across sea.")
        q("Which river is called 'Dakshin Ganga'?", "Krishna", "Kaveri", "Godavari", "Narmada", 2,
            "Godavari, the longest peninsular river, is called Dakshin Ganga.")
        q("How many states are there in India?", "26", "28", "29", "30", 1,
            "India has 28 states and 8 Union Territories.")
        q("Which river flows westward into the Arabian Sea?", "Godavari", "Krishna", "Narmada", "Mahanadi", 2,
            "Narmada flows westward through a rift valley into the Arabian Sea.")
    }

    chapter("history", "Indian History and Freedom Struggle", "Ancient India, great rulers and the national movement.") {
        note("Ancient & medieval",
            "Harappan civilisation – planned cities (Harappa, Mohenjo-daro). Great Bath at Mohenjo-daro.",
            "Ashoka – Mauryan emperor who adopted Buddhism after the Kalinga war.",
            "Akbar – Mughal emperor; Shivaji – founder of the Maratha kingdom.")
        note("Freedom struggle",
            "First War of Independence – 1857. Jallianwala Bagh massacre – 1919.",
            "Dandi March (Salt Satyagraha) – 1930. Quit India Movement – 1942.",
            "India became independent on 15 August 1947; Republic on 26 January 1950.",
            "Netaji Subhas Chandra Bose formed the Azad Hind Fauj (INA).")
        q("The Dandi March was started by Mahatma Gandhi in:", "1920", "1930", "1942", "1947", 1,
            "The Salt Satyagraha (Dandi March) began on 12 March 1930.")
        q("Who led the Azad Hind Fauj (INA) and popularised the slogan 'Jai Hind'?", "Bhagat Singh", "Subhas Chandra Bose", "Jawaharlal Nehru", "Sardar Patel", 1,
            "Netaji Subhas Chandra Bose led the Azad Hind Fauj and popularised 'Jai Hind'.")
        q("The Great Bath was found at:", "Harappa", "Mohenjo-daro", "Lothal", "Kalibangan", 1,
            "The Great Bath is at Mohenjo-daro.")
    }

    chapter("polity", "Constitution and Government", "Indian Constitution, national symbols and government.") {
        note("Constitution",
            "Adopted on 26 November 1949 (Constitution Day); came into force on 26 January 1950 (Republic Day).",
            "Dr B. R. Ambedkar – chairman of the Drafting Committee.",
            "The President is the head of the State; the Prime Minister is the head of the Government.",
            "Parliament = President + Lok Sabha + Rajya Sabha.")
        note("National symbols",
            "National anthem: Jana Gana Mana (Rabindranath Tagore), 52 seconds.",
            "National song: Vande Mataram (Bankim Chandra Chatterjee).",
            "National emblem from the Lion Capital of Sarnath; motto 'Satyameva Jayate'.",
            "Ashoka Chakra in the flag has 24 spokes.")
        q("How many spokes are there in the Ashoka Chakra of the national flag?", "12", "22", "24", "32", 2,
            "The Ashoka Chakra has 24 spokes.")
        q("Who wrote India's national song 'Vande Mataram'?", "Rabindranath Tagore", "Bankim Chandra Chatterjee", "Sarojini Naidu", "Muhammad Iqbal", 1,
            "Vande Mataram is from Bankim Chandra Chatterjee's novel Anandamath.")
        q("Who is the Supreme Commander of the Indian Armed Forces?", "Prime Minister", "Defence Minister", "President", "Chief of Defence Staff", 2,
            "The President of India is the Supreme Commander of the armed forces.")
    }

    chapter("defence", "Indian Armed Forces & Sainik Schools", "Army, Navy, Air Force, ranks, missiles and Sainik School facts.") {
        note("Armed forces",
            "Army Day – 15 January; Navy Day – 4 December; Air Force Day – 8 October.",
            "Highest wartime gallantry award – Param Vir Chakra; peacetime – Ashok Chakra.",
            "Indian missiles: Agni, Prithvi, BrahMos (with Russia), Akash, Nag.",
            "INS Vikrant – India's first indigenous aircraft carrier (commissioned 2022).",
            "National War Memorial is in New Delhi near India Gate.")
        note("Sainik Schools",
            "First Sainik Schools were set up in 1961 under V. K. Krishna Menon (Defence Minister).",
            "Run by Sainik Schools Society under the Ministry of Defence.",
            "Aim: prepare cadets academically, physically and mentally for entry into NDA and other academies.",
            "Admission through AISSEE conducted by the National Testing Agency (NTA).")
        q("Indian Army Day is celebrated on:", "15 January", "26 January", "4 December", "8 October", 0,
            "Army Day marks the day in 1949 when General K. M. Cariappa became the first Indian Commander-in-Chief.")
        q("The highest wartime gallantry award in India is:", "Ashok Chakra", "Param Vir Chakra", "Vir Chakra", "Kirti Chakra", 1,
            "Param Vir Chakra is the highest gallantry award for valour in war.")
        q("The AISSEE entrance exam for Sainik Schools is conducted by:", "CBSE", "UPSC", "NTA", "NCERT", 2,
            "The National Testing Agency conducts the All India Sainik Schools Entrance Examination.")
        q("BrahMos is a supersonic cruise missile developed by India with:", "USA", "France", "Russia", "Israel", 2,
            "BrahMos is named after the Brahmaputra and Moskva rivers — an India–Russia joint venture.")
    }

    chapter("sports_culture", "Sports, Culture, Books and Awards", "Games, dances, festivals, authors and awards.") {
        note("Sports",
            "Number of players: cricket 11, football 11, hockey 11, volleyball 6, basketball 5, kabaddi 7, kho-kho 9.",
            "Major Dhyan Chand Khel Ratna – highest sporting award. National Sports Day – 29 August (Dhyan Chand's birthday).",
            "Neeraj Chopra won India's first Olympic athletics gold (javelin, Tokyo 2020).")
        note("Culture",
            "Classical dances: Bharatanatyam (Tamil Nadu), Kathakali (Kerala), Kuchipudi (Andhra Pradesh), Odissi (Odisha), Kathak (North India), Manipuri (Manipur).",
            "Festivals: Onam (Kerala), Pongal (Tamil Nadu), Bihu (Assam), Baisakhi (Punjab).",
            "Books: Gitanjali – Rabindranath Tagore; Discovery of India – Jawaharlal Nehru; Wings of Fire – A. P. J. Abdul Kalam.",
            "Bharat Ratna – India's highest civilian award.")
        q("How many players are there in a kabaddi team on the court?", "5", "6", "7", "9", 2,
            "Each kabaddi team has 7 players on court.")
        q("'Wings of Fire' is the autobiography of:", "Mahatma Gandhi", "A. P. J. Abdul Kalam", "Jawaharlal Nehru", "C. V. Raman", 1,
            "Wings of Fire is Dr A. P. J. Abdul Kalam's autobiography.")
        q("Kathakali is a classical dance of:", "Tamil Nadu", "Kerala", "Odisha", "Manipur", 1,
            "Kathakali belongs to Kerala.")
        q("National Sports Day in India is celebrated on:", "29 August", "15 August", "5 September", "14 November", 0,
            "29 August is the birthday of hockey legend Major Dhyan Chand.")
    }

    chapter("environment", "Environment, Inventions & Important Days", "Pollution, conservation, inventors and days.") {
        note("Environment",
            "Ozone layer protects us from UV rays. Greenhouse gases (CO₂, methane) cause global warming.",
            "3 Rs: Reduce, Reuse, Recycle. Chipko movement – hugging trees to save forests.",
            "Jim Corbett – India's first national park (1936).")
        note("Inventions",
            "Telephone – Graham Bell; Electric bulb – Thomas Edison; Aeroplane – Wright brothers; Radio – Marconi; Penicillin – Alexander Fleming.",
            "Zero and the decimal system – ancient India (Aryabhata, Brahmagupta).")
        note("Important days",
            "World Environment Day – 5 June; Earth Day – 22 April; Teachers' Day – 5 September; Children's Day – 14 November; National Science Day – 28 February (Raman Effect); International Yoga Day – 21 June.")
        q("World Environment Day is celebrated on:", "22 April", "5 June", "21 June", "16 September", 1,
            "5 June is World Environment Day.")
        q("Who invented the telephone?", "Thomas Edison", "Alexander Graham Bell", "Marconi", "Newton", 1,
            "Alexander Graham Bell patented the telephone in 1876.")
        q("The Chipko movement is associated with:", "saving water", "saving forests", "saving tigers", "saving rivers", 1,
            "Villagers hugged trees to prevent them from being cut — Chipko means 'to cling'.")
    }
}
