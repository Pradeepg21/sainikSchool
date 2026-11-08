package com.satya.sainikschool.data.content

import com.satya.sainikschool.data.model.ExamClass

/** AISSEE Class 9 — General Science: 25 questions × 2 marks = 50 marks (Class 8 NCERT). */
val class9Science = subject("c9_sci", "General Science", ExamClass.CLASS_9, 25, 2, "science") {

    chapter("crops", "Crop Production and Management", "Kharif/rabi crops, agricultural practices and storage.") {
        note("Crops",
            "Kharif (rainy season, June–Sept): paddy, maize, soybean, cotton, groundnut.",
            "Rabi (winter, Oct–Mar): wheat, gram, pea, mustard, linseed.")
        note("Practices",
            "Steps: soil preparation → sowing → manuring → irrigation → weeding → harvesting → storage.",
            "Leguminous plants have Rhizobium bacteria in root nodules that fix nitrogen.",
            "Weedicide example: 2,4-D. Irrigation: drip and sprinkler systems save water.",
            "Threshing separates grain from chaff; winnowing uses wind.")
        q("Which of the following is a rabi crop?", "Paddy", "Maize", "Wheat", "Cotton", 2,
            "Wheat is sown in winter — a rabi crop.")
        q("Rhizobium bacteria are found in the root nodules of:", "wheat", "legumes", "rice", "maize", 1,
            "Rhizobium lives symbiotically in legume root nodules and fixes nitrogen.")
    }

    chapter("microorganisms", "Microorganisms: Friend and Foe", "Types of microbes, uses and diseases.") {
        note("Types",
            "Bacteria, fungi, protozoa, algae and viruses (viruses multiply only inside host cells).")
        note("Uses & harms",
            "Lactobacillus turns milk into curd; yeast is used in baking and making alcohol (fermentation, discovered by Louis Pasteur).",
            "Penicillin (antibiotic) discovered by Alexander Fleming in 1928 from a mould.",
            "Diseases: Tuberculosis, typhoid, cholera (bacteria); malaria (protozoa, female Anopheles mosquito); polio, COVID-19, chickenpox (virus).",
            "Edward Jenner discovered the smallpox vaccine.")
        q("Malaria is caused by:", "bacteria", "virus", "protozoa", "fungi", 2,
            "Malaria is caused by the protozoan Plasmodium, spread by female Anopheles mosquitoes.")
        q("The bacterium that helps in curd formation is:", "Rhizobium", "Lactobacillus", "Yeast", "Penicillium", 1,
            "Lactobacillus converts milk into curd.")
        q("Who discovered penicillin?", "Louis Pasteur", "Edward Jenner", "Alexander Fleming", "Robert Koch", 2,
            "Fleming discovered penicillin from the mould Penicillium.")
    }

    chapter("materials", "Synthetic Fibres, Plastics, Metals and Non-metals", "Polymers, properties and uses of metals and non-metals.") {
        note("Synthetic fibres",
            "Rayon (artificial silk, from wood pulp), nylon (first fully synthetic fibre), polyester (PET), acrylic (artificial wool).",
            "Thermoplastics soften on heating (PVC, polythene); thermosetting plastics do not (Bakelite, melamine).")
        note("Metals & non-metals",
            "Metals: lustrous, malleable, ductile, good conductors, sonorous. Mercury is a liquid metal.",
            "Non-metals: dull, brittle, poor conductors (graphite is an exception). Bromine is a liquid non-metal.",
            "Sodium and potassium are stored in kerosene; phosphorus is stored in water.",
            "Metal oxides are basic; non-metal oxides are acidic.")
        q("Which plastic is used for making electrical switches?", "Polythene", "PVC", "Bakelite", "Nylon", 2,
            "Bakelite is a thermosetting plastic and a poor conductor — used in switches.")
        q("Which non-metal is a good conductor of electricity?", "Sulphur", "Graphite", "Phosphorus", "Iodine", 1,
            "Graphite (a form of carbon) conducts electricity.")
        q("Sodium is stored in:", "water", "kerosene", "alcohol", "air", 1,
            "Sodium reacts vigorously with air and water, so it is kept in kerosene.")
    }

    chapter("fuels", "Coal, Petroleum, Combustion and Flame", "Fossil fuels, combustion and fuel efficiency.") {
        note("Fossil fuels",
            "Coal products: coke, coal tar, coal gas. Petroleum refining gives LPG, petrol, kerosene, diesel, lubricating oil, paraffin wax, bitumen.",
            "Petroleum is called 'black gold'. CNG is a cleaner fuel.")
        note("Combustion",
            "Needs fuel, oxygen (air) and ignition temperature.",
            "Flame zones: outermost (blue, hottest, complete combustion), middle (yellow, partial), innermost (black, unburnt vapour).",
            "Calorific value is measured in kJ/kg; hydrogen has the highest.",
            "Water must not be used on electrical or oil fires — use CO₂ extinguishers.")
        q("The hottest zone of a candle flame is the:", "innermost zone", "middle zone", "outermost zone", "wick", 2,
            "The outer blue zone has complete combustion and is the hottest.")
        q("Which fuel has the highest calorific value?", "Coal", "Petrol", "LPG", "Hydrogen", 3,
            "Hydrogen ≈ 150,000 kJ/kg — the highest among common fuels.")
    }

    chapter("cell", "Cell — Structure and Functions", "Plant and animal cells and their organelles.") {
        note("Cell parts",
            "Cell membrane controls entry and exit; nucleus controls activities (contains chromosomes).",
            "Mitochondria – powerhouse of the cell. Chloroplasts – photosynthesis (plants only).",
            "Plant cells have a cell wall and a large vacuole; animal cells do not have a cell wall.",
            "Robert Hooke discovered cells in cork (1665). Largest cell: ostrich egg; longest: nerve cell.")
        q("The powerhouse of the cell is:", "nucleus", "mitochondria", "ribosome", "chloroplast", 1,
            "Mitochondria release energy from food.")
        q("Which of these is present in plant cells but not in animal cells?", "Nucleus", "Cell membrane", "Cell wall", "Cytoplasm", 2,
            "Plant cells have a rigid cell wall made of cellulose.")
    }

    chapter("reproduction", "Reproduction and Adolescence", "Sexual and asexual reproduction; changes during adolescence.") {
        note("Reproduction",
            "Asexual: binary fission (Amoeba), budding (Hydra, yeast).",
            "Sexual: fusion of male and female gametes forms a zygote.",
            "Metamorphosis: tadpole → frog; larva → pupa → adult in insects.")
        note("Adolescence",
            "Hormones: testosterone (male), oestrogen (female) – controlled by pituitary gland.",
            "Thyroid hormone thyroxine needs iodine; deficiency causes goitre.",
            "Insulin from pancreas controls blood sugar; adrenaline helps in stress.")
        q("Hydra reproduces asexually by:", "binary fission", "budding", "spore formation", "fragmentation", 1,
            "A bud grows on Hydra and detaches as a new individual.")
        q("Which hormone controls blood sugar level?", "Thyroxine", "Adrenaline", "Insulin", "Oestrogen", 2,
            "Insulin from the pancreas lowers blood glucose.")
        q("Iodine deficiency causes:", "diabetes", "goitre", "anaemia", "scurvy", 1,
            "Lack of iodine affects thyroxine production and causes goitre.")
    }

    chapter("conservation", "Conservation of Plants and Animals", "Deforestation, biodiversity and protected areas.") {
        note("Key terms",
            "Endemic species: found only in one area (e.g. giant squirrel at Pachmarhi).",
            "Endangered species: at risk of extinction (tiger, Great Indian Bustard).",
            "Red Data Book lists endangered species.",
            "Project Tiger launched in 1973.")
        note("Protected areas",
            "Wildlife sanctuary: animals protected, limited human activity allowed.",
            "National park: protects entire ecosystem (e.g. Satpura, Kaziranga – one-horned rhino).",
            "Biosphere reserve: large area for conservation with people living there (Pachmarhi, Nilgiri).")
        q("Kaziranga National Park is famous for:", "Asiatic lion", "One-horned rhinoceros", "Snow leopard", "Bengal tiger only", 1,
            "Kaziranga in Assam hosts the largest population of the one-horned rhino.")
        q("A book that lists endangered species is the:", "Green Book", "Red Data Book", "Blue Book", "White Paper", 1,
            "The IUCN Red Data Book records threatened species.")
    }

    chapter("force", "Force, Pressure and Friction", "Types of force, pressure in fluids and friction.") {
        note("Force",
            "A push or pull; SI unit newton (N). Contact forces: muscular, friction. Non-contact: magnetic, electrostatic, gravitational.",
            "Pressure = Force/Area (unit pascal). Sharp knives and nails have small area → more pressure.",
            "Liquid pressure increases with depth; atmospheric pressure decreases with height.")
        note("Friction",
            "Opposes motion; static > sliding > rolling friction.",
            "Increased by treads on tyres; reduced by lubricants, ball bearings and streamlining.",
            "Fluid friction is called drag.")
        q("Which friction is the smallest?", "Static", "Sliding", "Rolling", "All are equal", 2,
            "Rolling friction is the least, which is why wheels are used.")
        q("A sharp knife cuts better because it has:", "more area, more pressure", "less area, more pressure", "less area, less pressure", "more weight", 1,
            "Pressure = force/area; smaller area gives larger pressure.")
        q("The SI unit of pressure is:", "newton", "pascal", "joule", "watt", 1,
            "1 pascal = 1 N/m².")
    }

    chapter("sound", "Sound", "Vibration, frequency, amplitude and the human ear.") {
        note("Facts",
            "Sound is produced by vibrations and needs a medium; cannot travel through vacuum.",
            "Speed of sound: solids > liquids > gases (≈ 343 m/s in air).",
            "Frequency (hertz) decides pitch; amplitude decides loudness (decibel).",
            "Audible range for humans: 20 Hz – 20,000 Hz. Below = infrasonic; above = ultrasonic (bats, dolphins).",
            "Sound in humans is produced by the larynx (voice box).")
        q("The audible range of human hearing is:", "2 Hz – 2,000 Hz", "20 Hz – 20,000 Hz", "200 Hz – 200,000 Hz", "0 – 20 Hz", 1,
            "Humans can hear frequencies between about 20 Hz and 20 kHz.")
        q("Sound travels fastest in:", "air", "water", "steel", "vacuum", 2,
            "Sound travels fastest in solids like steel.")
    }

    chapter("electricity", "Chemical Effects of Electric Current", "Conductors, electrolysis and electroplating.") {
        note("Facts",
            "Liquids that conduct electricity (acids, bases, salt solutions) are electrolytes; distilled water is a poor conductor.",
            "Electroplating: depositing a thin layer of a metal on another using current (chromium on taps, zinc on iron – galvanisation).",
            "In electrolysis of copper sulphate, copper deposits on the negative electrode (cathode).",
            "LEDs glow even with weak currents — useful in testers.")
        q("Coating iron with zinc to prevent rusting is called:", "electroplating", "galvanisation", "crystallisation", "distillation", 1,
            "Galvanisation is coating iron with zinc.")
        q("Which is a poor conductor of electricity?", "Lemon juice", "Salt water", "Distilled water", "Vinegar", 2,
            "Distilled water has almost no dissolved ions.")
    }

    chapter("light", "Light", "Reflection, laws, eye and dispersion.") {
        note("Reflection",
            "Angle of incidence = angle of reflection; incident ray, reflected ray and normal lie in the same plane.",
            "Regular reflection from smooth surfaces; diffused reflection from rough surfaces.",
            "Plane mirror image: virtual, erect, same size, laterally inverted.",
            "Number of images between two mirrors at angle θ = (360/θ) − 1. Kaleidoscope uses multiple reflection.")
        note("Eye",
            "Cornea, iris, pupil, lens, retina (rods for dim light, cones for colour).",
            "Persistence of vision ≈ 1/16 second. Braille system for visually challenged (Louis Braille).",
            "White light disperses into seven colours (VIBGYOR) through a prism.")
        q("Number of images formed by two plane mirrors at 60° to each other:", "4", "5", "6", "3", 1,
            "(360/60) − 1 = 5.")
        q("If the angle of incidence is 35°, the angle of reflection is:", "55°", "35°", "70°", "90°", 1,
            "By the law of reflection both angles are equal.")
        q("Which cells of the retina detect colour?", "Rods", "Cones", "Neurons", "Lens cells", 1,
            "Cones are sensitive to bright light and colour; rods to dim light.")
    }

    chapter("phenomena", "Natural Phenomena, Stars and the Solar System", "Lightning, earthquakes, constellations and planets.") {
        note("Natural phenomena",
            "Lightning is an electric discharge between charged clouds; lightning conductors protect buildings.",
            "Earthquakes are caused by movement of tectonic plates; measured on the Richter scale using a seismograph.")
        note("Space",
            "Light year = distance light travels in one year ≈ 9.46 × 10¹² km.",
            "Pole Star (Polaris) appears fixed. Constellations: Ursa Major (Big Dipper/Saptarishi), Orion, Cassiopeia.",
            "Artificial satellites: Aryabhata (India's first, 1975), INSAT, IRS. Venus is the brightest planet (morning/evening star).")
        q("The instrument that records earthquake waves is a:", "barometer", "seismograph", "hygrometer", "anemometer", 1,
            "A seismograph records seismic waves.")
        q("India's first artificial satellite was:", "INSAT-1A", "Bhaskara", "Aryabhata", "Rohini", 2,
            "Aryabhata was launched in 1975.")
        q("A light year is a unit of:", "time", "distance", "speed", "brightness", 1,
            "It is the distance travelled by light in one year.")
    }

    chapter("pollution", "Pollution of Air and Water", "Pollutants, effects and prevention.") {
        note("Air",
            "Pollutants: CO (incomplete burning), SO₂ and NO₂ (acid rain), CFCs (ozone depletion), particulate matter.",
            "Acid rain damages the Taj Mahal ('marble cancer').",
            "Greenhouse effect is caused by CO₂ and methane trapping heat.")
        note("Water",
            "Sewage, industrial waste and pesticides pollute water.",
            "Potable water: fit for drinking — purified by boiling, filtration and chlorination.",
            "Ganga Action Plan (1985); Namami Gange (2014).")
        q("'Marble cancer' of the Taj Mahal is caused by:", "smoke", "acid rain", "dust", "humidity", 1,
            "Acid rain (from SO₂ and NO₂) corrodes marble.")
        q("Which gas damages the ozone layer?", "Oxygen", "Nitrogen", "CFCs", "Hydrogen", 2,
            "Chlorofluorocarbons break down ozone molecules.")
    }
}
