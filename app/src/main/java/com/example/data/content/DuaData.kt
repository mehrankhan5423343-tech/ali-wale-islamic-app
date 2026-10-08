package com.example.data.content

import com.example.data.model.DuaCategory
import com.example.data.model.DuaItem

object DuaData {

    val allDuas: List<DuaItem> = listOf(
        // 1. Morning Duas
        DuaItem(
            id = "morning_1",
            category = DuaCategory.MORNING,
            title = "Morning Praise & Protection",
            textArabic = "أَصْبَحْنَا وَأَصْبَحَ الْمُلْكُ لِلَّهِ، وَالْحَمْدُ لِلَّهِ، لَا إِلٰهَ إِلَّا اللَّهُ وَحْدَهُ لَا شَرِيكَ لَهُ، لَهُ الْمُلْكُ وَلَهُ الْحَمْدُ وَهُوَ عَلَىٰ كُلِّ شَيْءٍ قَدِيرٌ",
            textTransliteration = "Aṣbaḥnā wa-aṣbaḥa al-mulku lillāh, wal-ḥamdu lillāh, lā ilāha illā Allāhu waḥdahu lā sharīka lah, lahu al-mulku walahu al-ḥamdu wahuwa 'alā kulli shay'in qadīr.",
            textTranslation = "We have entered a new morning and unto Allah belongs all dominion. All praise is due to Allah. None has the right to be worshipped except Allah alone, without partner. To Him belongs all dominion and praise, and He has power over all things.",
            reference = "Sahih Muslim (No. 2723)",
            benefitOrContext = "Recited once every morning to place your day in Allah's care."
        ),
        DuaItem(
            id = "morning_2",
            category = DuaCategory.MORNING,
            title = "Master of Forgiveness (Sayyid al-Istighfar)",
            textArabic = "اللَّهُمَّ أَنْتَ رَبِّي لَا إِلٰهَ إِلَّا أَنْتَ، خَلَقْتَنِي وَأَنَا عَبْدُكَ، وَأَنَا عَلَىٰ عَهْدِكَ وَوَعْدِكَ مَا اسْتَطَعْتُ، أَعُوذُ بِكَ مِنْ شَرِّ مَا صَنَعْتُ، أَبُوءُ لَكَ بِنِعْمَتِكَ عَلَيَّ، وَأَبُوءُ بِذَنْبِي فَاغْفِرْ لِي فَإِنَّهُ لَا يَغْفِرُ الذُّنُوبَ إِلَّا أَنْتَ",
            textTransliteration = "Allāhumma anta rabbī lā ilāha illā ant, khalaqtanī wa-anā 'abduk, wa-anā 'alā 'ahdika wawa'dika mastaṭa't, a'ūdhu bika min sharri mā ṣana't, abū'u laka bini'matika 'alayy, wa-abū'u bidhanbī faghfir lī fa-innahu lā yaghfiru adh-dhunūba illā ant.",
            textTranslation = "O Allah, You are my Lord; there is no deity worthy of worship except You. You created me and I am Your servant, and I abide by Your covenant and promise as best as I am able. I seek refuge in You from the evil of what I have done. I acknowledge before You Your favors upon me, and I acknowledge my sins, so forgive me, for indeed none forgives sins except You.",
            reference = "Sahih al-Bukhari (No. 6306)",
            benefitOrContext = "Whoever says this with conviction in the morning and dies before evening will enter Paradise."
        ),

        // 2. Evening Duas
        DuaItem(
            id = "evening_1",
            category = DuaCategory.EVENING,
            title = "Evening Affirmation of Faith",
            textArabic = "أَمْسَيْنَا وَأَمْسَى الْمُلْكُ لِلَّهِ، وَالْحَمْدُ لِلَّهِ، لَا إِلٰهَ إِلَّا اللَّهُ وَحْدَهُ لَا شَرِيكَ لَهُ، لَهُ الْمُلْكُ وَلَهُ الْحَمْدُ وَهُوَ عَلَىٰ كُلِّ شَيْءٍ قَدِيرٌ",
            textTransliteration = "Amsaynā wa-amsā al-mulku lillāh, wal-ḥamdu lillāh, lā ilāha illā Allāhu waḥdahu lā sharīka lah, lahu al-mulku walahu al-ḥamdu wahuwa 'alā kulli shay'in qadīr.",
            textTranslation = "We have reached the evening and unto Allah belongs all sovereignty, and all praise is for Allah. None has the right to be worshipped except Allah alone, without partner. To Him belongs sovereignty and all praise, and He is over all things competent.",
            reference = "Sahih Muslim (No. 2723)",
            benefitOrContext = "Affirms gratitude and sovereignty upon sunset."
        ),

        // 3. Before Sleeping
        DuaItem(
            id = "sleep_1",
            category = DuaCategory.BEFORE_SLEEP,
            title = "Supplication Before Sleeping",
            textArabic = "بِاسْمِكَ رَبِّي وَضَعْتُ جَنْبِي، وَبِكَ أَرْفَعُهُ، فَإِنْ أَمْسَكْتَ نَفْسِي فَارْحَمْهَا، وَإِنْ أَرْسَلْتَهَا فَاحْفَظْهَا بِمَا تَحْفَظُ بِهِ عِبَادَكَ الصَّالِحِينَ",
            textTransliteration = "Bismika rabbī waḍa'tu janbī, wabika arfa'uh, fa-in amsakta nafsī farḥamhā, wa-in arsaltahā faḥfaẓhā bimā taḥfaẓu bihi 'ibādaka aṣ-ṣāliḥīn.",
            textTranslation = "In Your name, my Lord, I lay down my side, and in Your name I raise it. If You take my soul, have mercy on it, and if You release it, protect it with that by which You protect Your righteous servants.",
            reference = "Sahih al-Bukhari (No. 6320), Sahih Muslim (No. 2714)",
            benefitOrContext = "Recited while dusting the bed three times."
        ),

        // 4. After Waking
        DuaItem(
            id = "waking_1",
            category = DuaCategory.AFTER_WAKING,
            title = "Supplication Upon Waking Up",
            textArabic = "الْحَمْدُ لِلَّهِ الَّذِي أَحْيَانَا بَعْدَ مَا أَمَاتَنَا وَإِلَيْهِ النُّشُورُ",
            textTransliteration = "Al-ḥamdu lillāhi alladhī aḥyānā ba'da mā amātanā wa-ilayhi an-nushūr.",
            textTranslation = "All praise is due to Allah Who gave us life after having caused us to die, and unto Him is the resurrection.",
            reference = "Sahih al-Bukhari (No. 6312)",
            benefitOrContext = "Sunnah upon opening one's eyes in the morning."
        ),

        // 5. Anxiety and Difficulty
        DuaItem(
            id = "anxiety_1",
            category = DuaCategory.ANXIETY,
            title = "Relief from Anxiety and Sorrow",
            textArabic = "اللَّهُمَّ إِنِّي عَبْدُكَ، ابْنُ عَبْدِكَ، ابْنُ أَمَتِكَ، نَاصِيَتِي بِيَدِكَ، مَاضٍ فِيَّ حُكْمُكَ، عَدْلٌ فِيَّ قَضَاؤُكَ، أَسْأَلُكَ بِكُلِّ اسْمٍ هُوَ لَكَ سَمَّيْتَ بِهِ نَفْسَكَ، أَوْ عَلَّمْتَهُ أَحَدًا مِنْ خَلْقِكَ، أَوْ أَنْزَلْتَهُ فِي كِتَابِكَ، أَوِ اسْتَأْثَرْتَ بِهِ فِي عِلْمِ الْغَيْبِ عِنْدَكَ، أَنْ تَجْعَلَ الْقُرْآنَ رَبِيعَ قَلْبِي، وَنُورَ صَدْرِي، وَجَلَاءَ حُزْنِي، وَذَهَابَ هَمِّي",
            textTransliteration = "Allāhumma innī 'abduk, ibnu 'abdik, ibnu amatik, nāṣiyatī biyadika, māḍin fiyya ḥukmuk, 'adlun fiyya qaḍā'uk, as'aluka bikulli ismin huwa laka sammayta bihi nafsak...",
            textTranslation = "O Allah, I am Your servant, son of Your servant, son of Your maidservant, my forelock is in Your hand, Your command over me is ever executed and Your decree over me is just. I ask You by every name belonging to You... to make the Quran the spring of my heart, the light of my chest, the remover of my sorrow and the pacifier of my anxiety.",
            reference = "Musnad Ahmad (No. 3712), Sahih Ibn Hibban",
            benefitOrContext = "Whenever distress strikes, Allah replaces grief with comfort and solace."
        ),
        DuaItem(
            id = "anxiety_2",
            category = DuaCategory.ANXIETY,
            title = "Supplication of Prophet Yunus (Dhun-Nun)",
            textArabic = "لَا إِلٰهَ إِلَّا أَنْتَ سُبْحَانَكَ إِنِّي كُنْتُ مِنَ الظَّالِمِينَ",
            textTransliteration = "Lā ilāha illā anta subḥānaka innī kuntu mina aẓ-ẓālimīn.",
            textTranslation = "There is no deity except You; exalted are You. Indeed, I have been of the wrongdoers.",
            reference = "Surah Al-Anbiya (21:87), Jami' at-Tirmidhi (No. 3505)",
            benefitOrContext = "No Muslim calls upon Allah with this supplication for anything except that Allah responds to him."
        ),

        // 6. Protection
        DuaItem(
            id = "protection_1",
            category = DuaCategory.PROTECTION,
            title = "Universal Protection Against All Harm",
            textArabic = "بِسْمِ اللَّهِ الَّذِي لَا يَضُرُّ مَعَ اسْمِهِ شَيْءٌ فِي الْأَرْضِ وَلَا فِي السَّمَاءِ وَهُوَ السَّمِيعُ الْعَلِيمُ",
            textTransliteration = "Bismi Allāhi alladhī lā yaḍurru ma'a ismihi shay'un fī al-arḍi walā fī as-samā'i wahuwa as-samī'u al-'alīm.",
            textTranslation = "In the name of Allah, with whose name nothing on earth or in the sky can cause harm, and He is the All-Hearing, the All-Knowing.",
            reference = "Sunan Abi Dawud (No. 5088), Jami' at-Tirmidhi (No. 3388)",
            benefitOrContext = "Recited 3 times morning and evening for complete safeguarding."
        ),

        // 7. Forgiveness
        DuaItem(
            id = "forgiveness_1",
            category = DuaCategory.FORGIVENESS,
            title = "Dua for Complete Forgiveness",
            textArabic = "رَبَّنَا ظَلَمْنَا أَنفُسَنَا وَإِن لَّمْ تَغْفِرْ لَنَا وَتَرْحَمْنَا لَنَكُونَنَّ مِنَ الْخَاسِرِينَ",
            textTransliteration = "Rabbanā ẓalamnā anfusanā wa-in lam taghfir lanā watarḥamnā lanakūnanna mina al-khāsirīn.",
            textTranslation = "Our Lord, we have wronged ourselves, and if You do not forgive us and have mercy upon us, we will surely be among the losers.",
            reference = "Surah Al-A'raf (7:23) - The Prayer of Adam and Hawwa",
            benefitOrContext = "Foundational Quranic prayer of repentance and humility."
        ),

        // 8. Gratitude
        DuaItem(
            id = "gratitude_1",
            category = DuaCategory.GRATITUDE,
            title = "Supplication for Continued Gratitude",
            textArabic = "اللَّهُمَّ أَعِنِّي عَلَىٰ ذِكْرِكَ، وَشُكْرِكَ، وَحُسْنِ عِبَادَتِكَ",
            textTransliteration = "Allāhumma a'innī 'alā dhikrika, washukrika, waḥusni 'ibādatik.",
            textTranslation = "O Allah, help me to remember You, to give thanks to You, and to worship You in an excellent manner.",
            reference = "Sunan Abi Dawud (No. 1522), Sunan an-Nasa'i (No. 1303)",
            benefitOrContext = "Taught by the Prophet ﷺ to Mu'adh ibn Jabal to say at the end of every prayer."
        ),

        // 9. Travel
        DuaItem(
            id = "travel_1",
            category = DuaCategory.TRAVEL,
            title = "Supplication When Setting Out on a Journey",
            textArabic = "سُبْحَانَ الَّذِي سَخَّرَ لَنَا هَٰذَا وَمَا كُنَّا لَهُ مُقْرِنِينَ، وَإِنَّا إِلَىٰ رَبِّنَا لَمُنقَلِبُونَ، اللَّهُمَّ إِنَّا نَسْأَلُكَ فِي سَفَرِنَا هَٰذَا الْبِرَّ وَالتَّقْوَىٰ، وَمِنَ الْعَمَلِ مَا تَرْضَىٰ",
            textTransliteration = "Subḥāna alladhī sakhkhara lanā hādhā wamā kunnā lahu muqrinīn, wa-innā ilā rabbinā lamunqalibūn. Allāhumma innā nas'aluka fī safarinā hādhā al-birra wat-taqwā, wamina al-'amali mā tarḍā.",
            textTranslation = "Glory to Him who has brought this under our control, though we were unable to subdue it ourselves. And indeed, to our Lord we will surely return. O Allah, we ask You on this journey of ours for righteousness, piety, and actions that please You.",
            reference = "Sahih Muslim (No. 1342)",
            benefitOrContext = "Sunnah when embarking on any travel (car, plane, train)."
        ),

        // 10. Parents
        DuaItem(
            id = "parents_1",
            category = DuaCategory.PARENTS,
            title = "Quranic Supplication for Parents",
            textArabic = "رَّبِّ ارْحَمْهُمَا كَمَا رَبَّيَانِي صَغِيرًا",
            textTransliteration = "Rabbi irḥamhumā kamā rabbayānī ṣaghīrā.",
            textTranslation = "My Lord, have mercy upon them as they brought me up when I was small.",
            reference = "Surah Al-Isra (17:24)",
            benefitOrContext = "A gentle, eternal supplication for both living and passed parents."
        ),

        // 11. Knowledge
        DuaItem(
            id = "knowledge_1",
            category = DuaCategory.KNOWLEDGE,
            title = "Asking for Increase in Beneficial Knowledge",
            textArabic = "رَبِّ زِدْنِي عِلْمًا",
            textTransliteration = "Rabbi zidnī 'ilmā.",
            textTranslation = "My Lord, increase me in knowledge.",
            reference = "Surah Ta-Ha (20:114)",
            benefitOrContext = "The only worldly matter the Prophet ﷺ was commanded to pray for an increase in."
        ),

        // 12. Rizq
        DuaItem(
            id = "rizq_1",
            category = DuaCategory.RIZQ,
            title = "Seeking Halal Provision and Ease of Debt",
            textArabic = "اللَّهُمَّ اكْفِنِي بِحَلَالِكَ عَنْ حَرَامِكَ، وَأَغْنِنِي بِفَضْلِكَ عَمَّنْ سِوَاكَ",
            textTransliteration = "Allāhumma ikfinī biḥalālika 'an ḥarāmik, wa-aghninī bifaḍlika 'amman siwāk.",
            textTranslation = "O Allah, suffice me with what is lawful against what is prohibited, and enrich me by Your grace so that I am independent of anyone other than You.",
            reference = "Jami' at-Tirmidhi (No. 3563), Sunan Ahmad",
            benefitOrContext = "Known as the supplication for freedom from debt and pure sustenance."
        ),

        // 13. Health
        DuaItem(
            id = "health_1",
            category = DuaCategory.HEALTH,
            title = "Supplication for Physical and Spiritual Well-being (Afiyah)",
            textArabic = "اللَّهُمَّ إِنِّي أَسْأَلُكَ الْعَفْوَ وَالْعَافِيَةَ فِي الدُّنْيَا وَالْآخِرَةِ، اللَّهُمَّ إِنِّي أَسْأَلُكَ الْعَفْوَ وَالْعَافِيَةَ فِي دِينِي وَدُنْيَايَ وَأَهْلِي وَمَالِي",
            textTransliteration = "Allāhumma innī as'aluka al-'afwa wal-'āfiyata fī ad-dunyā wal-ākhirah, Allāhumma innī as'aluka al-'afwa wal-'āfiyata fī dīnī wadunyāya wa-ahlī wamālī.",
            textTranslation = "O Allah, I ask You for pardon and well-being in this world and the Hereafter. O Allah, I ask You for pardon and well-being in my religion, my worldly affairs, my family, and my wealth.",
            reference = "Sunan Abi Dawud (No. 5074), Sunan Ibn Majah (No. 3871)",
            benefitOrContext = "Recited morning and evening for sound body, peace of mind, and security."
        ),

        // 14. General
        DuaItem(
            id = "general_1",
            category = DuaCategory.GENERAL,
            title = "The Comprehensive Prayer for Good in Both Worlds",
            textArabic = "رَبَّنَا آتِنَا فِي الدُّنْيَا حَسَنَةً وَفِي الْآخِرَةِ حَسَنَةً وَقِنَا عَذَابَ النَّارِ",
            textTransliteration = "Rabbanā ātinā fī ad-dunyā ḥasanatan wafī al-ākhirati ḥasanatan waqinā 'adhāba an-nār.",
            textTranslation = "Our Lord, give us in this world [that which is] good and in the Hereafter [that which is] good and protect us from the punishment of the Fire.",
            reference = "Surah Al-Baqarah (2:201), Sahih al-Bukhari (No. 4522)",
            benefitOrContext = "The most frequent supplication made by the Prophet Muhammad ﷺ."
        )
    )

    fun getDuasByCategory(category: DuaCategory): List<DuaItem> {
        return allDuas.filter { it.category == category }
    }

    fun getDuaById(id: String): DuaItem? {
        return allDuas.find { it.id == id }
    }
}
