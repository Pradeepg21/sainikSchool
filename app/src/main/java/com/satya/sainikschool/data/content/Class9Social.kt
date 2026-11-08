package com.satya.sainikschool.data.content

import com.satya.sainikschool.data.model.ExamClass

/** AISSEE Class 9 — Social Science: 25 questions × 2 marks = 50 marks (Class 8 NCERT History, Geography, Civics). */
val class9Social = subject("c9_sst", "Social Science", ExamClass.CLASS_9, 25, 2, "globe") {

    chapter("company_rule", "From Trade to Territory: The East India Company", "How the Company came to power in India.") {
        note("Key events",
            "East India Company got a charter from Queen Elizabeth I in 1600.",
            "Battle of Plassey (1757): Robert Clive defeated Siraj-ud-Daulah.",
            "Battle of Buxar (1764): Company won Diwani rights of Bengal, Bihar and Orissa (1765).",
            "Tipu Sultan of Mysore died in 1799 defending Seringapatam.")
        note("Policies",
            "Subsidiary Alliance – Lord Wellesley (1798).",
            "Doctrine of Lapse – Lord Dalhousie (annexed Satara, Jhansi, Nagpur…).",
            "Permanent Settlement (1793) – Lord Cornwallis; Mahalwari and Ryotwari systems in other regions.")
        q("The Battle of Plassey was fought in:", "1757", "1764", "1857", "1799", 0,
            "Robert Clive defeated Siraj-ud-Daulah at Plassey in 1757.")
        q("The Doctrine of Lapse was introduced by:", "Lord Wellesley", "Lord Dalhousie", "Lord Cornwallis", "Lord Curzon", 1,
            "Lord Dalhousie used the Doctrine of Lapse to annex states without natural heirs.")
        q("Who introduced the Subsidiary Alliance?", "Lord Wellesley", "Lord Clive", "Warren Hastings", "Lord Ripon", 0,
            "Lord Wellesley introduced it in 1798.")
    }

    chapter("revolt_1857", "The Revolt of 1857", "Causes, leaders and results of the first war of independence.") {
        note("Causes & leaders",
            "Immediate cause: greased cartridges of the Enfield rifle. Mangal Pandey at Barrackpore (March 1857).",
            "Revolt began at Meerut on 10 May 1857; rebels proclaimed Bahadur Shah Zafar emperor.",
            "Leaders: Rani Lakshmibai (Jhansi), Nana Saheb and Tantia Tope (Kanpur), Begum Hazrat Mahal (Lucknow), Kunwar Singh (Bihar).")
        note("Results",
            "Company rule ended; Government of India Act 1858 transferred power to the British Crown.",
            "Governor-General became Viceroy (Lord Canning the first).")
        q("The Revolt of 1857 began at:", "Delhi", "Meerut", "Kanpur", "Lucknow", 1,
            "Sepoys at Meerut revolted on 10 May 1857.")
        q("Who led the revolt in Lucknow?", "Rani Lakshmibai", "Begum Hazrat Mahal", "Kunwar Singh", "Tantia Tope", 1,
            "Begum Hazrat Mahal led the uprising in Awadh (Lucknow).")
    }

    chapter("reforms", "Women, Caste and Reform; Civilising the Native", "Social reformers and colonial education.") {
        note("Reformers",
            "Raja Ram Mohan Roy – Brahmo Samaj; campaign against sati (banned 1829).",
            "Ishwar Chandra Vidyasagar – widow remarriage (Act of 1856).",
            "Jyotirao Phule – Satyashodhak Samaj; book 'Gulamgiri' (1873). Savitribai Phule – first girls' school.",
            "Swami Dayanand Saraswati – Arya Samaj. Dr B. R. Ambedkar – temple entry movement (1927).",
            "E. V. Ramaswamy Naicker (Periyar) – Self-Respect Movement.")
        note("Education",
            "Macaulay's Minute (1835) promoted English education.",
            "Wood's Despatch (1854) – 'Magna Carta of English education in India'.",
            "Tagore founded Santiniketan (1901); Gandhiji proposed Nai Talim (basic education).")
        q("Who wrote the book 'Gulamgiri'?", "Raja Ram Mohan Roy", "Jyotirao Phule", "B. R. Ambedkar", "Dayanand Saraswati", 1,
            "Jyotirao Phule wrote Gulamgiri in 1873.")
        q("Sati was banned in:", "1829", "1856", "1857", "1919", 0,
            "Lord William Bentinck banned sati in 1829, supported by Raja Ram Mohan Roy.")
        q("Santiniketan was founded by:", "Mahatma Gandhi", "Rabindranath Tagore", "Swami Vivekananda", "Gopal Krishna Gokhale", 1,
            "Tagore set up Santiniketan in 1901.")
    }

    chapter("national_movement", "The Making of the National Movement (1870s–1947)", "Congress, Gandhian movements and independence.") {
        note("Timeline",
            "Indian National Congress founded 1885 (A. O. Hume; first president W. C. Bonnerjee).",
            "Partition of Bengal 1905 → Swadeshi movement. Muslim League 1906.",
            "Jallianwala Bagh 13 April 1919; Non-Cooperation 1920–22 (withdrawn after Chauri Chaura).",
            "Purna Swaraj resolution – Lahore 1929; Civil Disobedience/Dandi March 1930.",
            "Quit India Movement – 8 August 1942 ('Do or Die').",
            "Independence – 15 August 1947.")
        note("Leaders",
            "Moderates: Dadabhai Naoroji (Drain Theory), G. K. Gokhale. Extremists: Lal–Bal–Pal.",
            "Bal Gangadhar Tilak: 'Swaraj is my birthright and I shall have it.'",
            "Sardar Patel – integrated 500+ princely states; 'Iron Man of India'.")
        q("The Indian National Congress was founded in:", "1857", "1885", "1905", "1919", 1,
            "INC was founded in December 1885 in Bombay.")
        q("The 'Do or Die' slogan was given during:", "Non-Cooperation Movement", "Civil Disobedience Movement", "Quit India Movement", "Swadeshi Movement", 2,
            "Gandhiji gave the call 'Do or Die' at the launch of Quit India in 1942.")
        q("Who propounded the Drain Theory?", "Dadabhai Naoroji", "Bal Gangadhar Tilak", "Gopal Krishna Gokhale", "Lala Lajpat Rai", 0,
            "Dadabhai Naoroji explained how wealth was drained from India to Britain.")
    }

    chapter("after_independence", "India After Independence", "Constitution, states reorganisation and planning.") {
        note("Facts",
            "Constituent Assembly drafted the Constitution; adopted 26 Nov 1949, enforced 26 Jan 1950.",
            "States reorganised on linguistic basis in 1956 (Andhra State formed in 1953 after Potti Sriramulu's fast).",
            "First general elections 1951–52. Five-Year Plans began in 1951.",
            "Non-Aligned Movement – Nehru, Nasser, Tito.")
        q("Andhra State was created after the death (fast) of:", "Potti Sriramulu", "C. Rajagopalachari", "N. T. Rama Rao", "Tanguturi Prakasam", 0,
            "Potti Sriramulu's fast-unto-death led to the formation of Andhra State in 1953.")
    }

    chapter("resources", "Resources", "Types of resources and sustainable development.") {
        note("Types",
            "Natural (air, water, soil), human-made (buildings, machines), human resources (people with skills).",
            "Renewable (solar, wind) vs non-renewable (coal, petroleum).",
            "Ubiquitous (found everywhere, e.g. air) vs localised (found in some places, e.g. copper).",
            "Sustainable development: using resources carefully so future generations also benefit.")
        q("Which is a renewable resource?", "Coal", "Petroleum", "Solar energy", "Natural gas", 2,
            "Solar energy is continuously available — renewable.")
        q("Air is an example of a ___ resource.", "localised", "ubiquitous", "human-made", "non-renewable", 1,
            "Air is found everywhere — ubiquitous.")
    }

    chapter("land_soil", "Land, Soil, Water, Natural Vegetation and Wildlife", "Land use, soil types and conservation.") {
        note("Soil",
            "Alluvial soil – river plains (most fertile). Black (regur) soil – cotton (Deccan). Red soil – iron oxide. Laterite – heavy rainfall areas.",
            "Conservation: mulching, contour ploughing, terrace farming, shelter belts, rock dams.")
        note("Water & vegetation",
            "Only about 2.7% of Earth's water is fresh; ~70% of the surface is covered by water.",
            "Rainwater harvesting and drip irrigation conserve water.",
            "Vegetation types: tropical evergreen, deciduous, grasslands, thorny shrubs, tundra.")
        q("Black soil is most suitable for growing:", "rice", "tea", "cotton", "wheat", 2,
            "Black (regur) soil retains moisture — ideal for cotton.")
        q("Growing crops on step-like fields on hill slopes is called:", "mulching", "terrace farming", "shelter belts", "crop rotation", 1,
            "Terrace farming reduces soil erosion on slopes.")
    }

    chapter("agriculture", "Agriculture", "Farm system, types of farming and major crops.") {
        note("Farming types",
            "Subsistence farming: for the family's needs (intensive and primitive – shifting cultivation 'slash and burn', called jhumming in North-East).",
            "Commercial farming: for sale (plantations – tea, coffee, rubber).",
            "Inputs → processes → outputs form the farm system.")
        note("Crops",
            "Rice – needs high temperature, high humidity, rainfall. India and China are leading producers.",
            "Jute – 'golden fibre'. Coffee – Karnataka. Tea – Assam.")
        q("Jute is known as the:", "white gold", "golden fibre", "black gold", "silver fibre", 1,
            "Jute is called the golden fibre.")
        q("Shifting cultivation in North-East India is called:", "Ladang", "Jhumming", "Milpa", "Roca", 1,
            "It is known as jhum or jhumming in the North-East.")
    }

    chapter("industries", "Industries and Human Resources", "Types and location of industries; population.") {
        note("Industries",
            "Classified by raw material (agro, mineral, marine, forest), size, ownership (private, public, joint, co-operative).",
            "Factors: raw material, land, water, labour, power, capital, transport, market.",
            "Silicon Valley (USA) and Bengaluru – IT hubs. Jamshedpur (TISCO, 1907) – steel. Ahmedabad and Osaka – cotton textiles.")
        note("Population",
            "Population density = people per sq km.",
            "Factors: geographical (topography, climate, soil, water) and social/economic.",
            "Population pyramid shows age-sex composition.")
        q("Tata Iron and Steel Company (TISCO) was set up at Jamshedpur in:", "1907", "1947", "1854", "1950", 0,
            "TISCO was established in 1907.")
        q("Which city is called the 'Silicon Valley of India'?", "Hyderabad", "Chennai", "Bengaluru", "Pune", 2,
            "Bengaluru is India's leading IT hub.")
    }

    chapter("constitution", "The Indian Constitution and Secularism", "Key features, fundamental rights and duties.") {
        note("Features",
            "Federalism, parliamentary form of government, separation of powers, Fundamental Rights, secularism.",
            "Fundamental Rights: Equality, Freedom, Against Exploitation, Freedom of Religion, Cultural & Educational Rights, Constitutional Remedies (Art. 32 – 'heart and soul', Dr Ambedkar).",
            "Fundamental Duties added by the 42nd Amendment (1976).",
            "Secularism: State does not promote any religion; separation of religion from State.")
        q("Which right did Dr Ambedkar call the 'heart and soul' of the Constitution?", "Right to Equality", "Right to Freedom", "Right to Constitutional Remedies", "Right against Exploitation", 2,
            "Article 32 – Right to Constitutional Remedies.")
        q("Fundamental Duties were added by the:", "1st Amendment", "42nd Amendment", "44th Amendment", "73rd Amendment", 1,
            "The 42nd Amendment of 1976 added Fundamental Duties.")
    }

    chapter("parliament", "Parliament and the Making of Laws", "Lok Sabha, Rajya Sabha and how a bill becomes a law.") {
        note("Parliament",
            "Lok Sabha: maximum 552 members (543 elected currently); term 5 years; minimum age 25.",
            "Rajya Sabha: maximum 250 members (12 nominated by President); permanent house, 1/3 retire every 2 years; minimum age 30.",
            "Prime Minister is the leader of the majority party in Lok Sabha.",
            "A bill becomes a law after passing both Houses and receiving the President's assent.")
        q("How many members of the Rajya Sabha are nominated by the President?", "2", "10", "12", "14", 2,
            "12 members with expertise in art, literature, science and social service are nominated.")
        q("Minimum age to become a member of Lok Sabha is:", "18", "21", "25", "30", 2,
            "A Lok Sabha member must be at least 25 years old.")
    }

    chapter("judiciary", "Judiciary, Marginalisation and Public Facilities", "Courts, rights of marginalised groups and public services.") {
        note("Judiciary",
            "Supreme Court (New Delhi) → High Courts → District Courts.",
            "Independence of judiciary; judicial review; Public Interest Litigation (PIL) since the 1980s.",
            "FIR (First Information Report) starts police investigation.")
        note("Marginalisation & public facilities",
            "Adivasis, Dalits and minorities may face marginalisation; SC/ST (Prevention of Atrocities) Act 1989.",
            "Article 21 – Right to Life includes right to water.",
            "Government ensures public facilities like water, sanitation, transport, health.")
        q("The highest court of appeal in India is the:", "High Court", "District Court", "Supreme Court", "Lok Adalat", 2,
            "The Supreme Court is the apex court.")
        q("The Right to Water is considered part of:", "Article 14", "Article 19", "Article 21", "Article 32", 2,
            "Courts have held that the right to life (Art. 21) includes the right to safe water.")
    }
}
