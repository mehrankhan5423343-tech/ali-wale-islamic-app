package com.example.data.content

import com.example.data.model.Ayah
import com.example.data.model.JuzInfo
import com.example.data.model.Surah

object QuranData {

    val surahs: List<Surah> = listOf(
        Surah(1, "الفاتحة", "Al-Fatihah", "The Opening", "Meccan", 7, 1),
        Surah(2, "البقرة", "Al-Baqarah", "The Cow", "Medinan", 286, 1),
        Surah(3, "آل عمران", "Ali 'Imran", "Family of Imran", "Medinan", 200, 3),
        Surah(4, "النساء", "An-Nisa", "The Women", "Medinan", 176, 4),
        Surah(5, "المائدة", "Al-Ma'idah", "The Table Spread", "Medinan", 120, 6),
        Surah(6, "الأنعام", "Al-An'am", "The Cattle", "Meccan", 165, 7),
        Surah(7, "الأعراف", "Al-A'raf", "The Heights", "Meccan", 206, 8),
        Surah(8, "الأنفال", "Al-Anfal", "The Spoils of War", "Medinan", 75, 9),
        Surah(9, "التوبة", "At-Tawbah", "The Repentance", "Medinan", 129, 10),
        Surah(10, "يونس", "Yunus", "Jonah", "Meccan", 109, 11),
        Surah(11, "هود", "Hud", "Hud", "Meccan", 123, 11),
        Surah(12, "يوسف", "Yusuf", "Joseph", "Meccan", 111, 12),
        Surah(13, "الرعد", "Ar-Ra'd", "The Thunder", "Medinan", 43, 13),
        Surah(14, "إبراهيم", "Ibrahim", "Abraham", "Meccan", 52, 13),
        Surah(15, "الحجر", "Al-Hijr", "The Rocky Tract", "Meccan", 99, 14),
        Surah(16, "النحل", "An-Nahl", "The Bee", "Meccan", 128, 14),
        Surah(17, "الإسراء", "Al-Isra", "The Night Journey", "Meccan", 111, 15),
        Surah(18, "الكهف", "Al-Kahf", "The Cave", "Meccan", 110, 15),
        Surah(19, "مريم", "Maryam", "Mary", "Meccan", 98, 16),
        Surah(20, "طه", "Taha", "Ta-Ha", "Meccan", 135, 16),
        Surah(21, "الأنبياء", "Al-Anbiya", "The Prophets", "Meccan", 112, 17),
        Surah(22, "الحج", "Al-Hajj", "The Pilgrimage", "Medinan", 78, 17),
        Surah(23, "المؤمنون", "Al-Mu'minun", "The Believers", "Meccan", 118, 18),
        Surah(24, "النور", "An-Nur", "The Light", "Medinan", 64, 18),
        Surah(25, "الفرقان", "Al-Furqan", "The Criterion", "Meccan", 77, 18),
        Surah(26, "الشعراء", "Ash-Shu'ara", "The Poets", "Meccan", 227, 19),
        Surah(27, "النمل", "An-Naml", "The Ant", "Meccan", 93, 19),
        Surah(28, "القصص", "Al-Qasas", "The Stories", "Meccan", 88, 20),
        Surah(29, "العنكبوت", "Al-'Ankabut", "The Spider", "Meccan", 69, 20),
        Surah(30, "الروم", "Ar-Rum", "The Romans", "Meccan", 60, 21),
        Surah(31, "لقمان", "Luqman", "Luqman", "Meccan", 34, 21),
        Surah(32, "السجدة", "As-Sajdah", "The Prostration", "Meccan", 30, 21),
        Surah(33, "الأحزاب", "Al-Ahzab", "The Combined Forces", "Medinan", 73, 21),
        Surah(34, "سبأ", "Saba", "Sheba", "Meccan", 54, 22),
        Surah(35, "فاطر", "Fatir", "Originator", "Meccan", 45, 22),
        Surah(36, "يس", "Ya-Sin", "Ya-Sin", "Meccan", 83, 22),
        Surah(37, "الصافات", "As-Saffat", "Those who set the Ranks", "Meccan", 182, 23),
        Surah(38, "ص", "Sad", "The Letter Sad", "Meccan", 88, 23),
        Surah(39, "الزمر", "Az-Zumar", "The Troops", "Meccan", 75, 23),
        Surah(40, "غافر", "Ghafir", "The Forgiver", "Meccan", 85, 24),
        Surah(41, "فصلت", "Fussilat", "Explained in Detail", "Meccan", 54, 24),
        Surah(42, "الشورى", "Ash-Shura", "The Consultation", "Meccan", 53, 25),
        Surah(43, "الزخرف", "Az-Zukhruf", "The Ornaments of Gold", "Meccan", 89, 25),
        Surah(44, "الدخان", "Ad-Dukhan", "The Smoke", "Meccan", 59, 25),
        Surah(45, "الجاثية", "Al-Jathiyah", "The Crouching", "Meccan", 37, 25),
        Surah(46, "الأحقاف", "Al-Ahqaf", "The Wind-Curved Sandhills", "Meccan", 35, 26),
        Surah(47, "محمد", "Muhammad", "Muhammad", "Medinan", 38, 26),
        Surah(48, "الفتح", "Al-Fath", "The Victory", "Medinan", 29, 26),
        Surah(49, "الحجرات", "Al-Hujurat", "The Rooms", "Medinan", 18, 26),
        Surah(50, "ق", "Qaf", "The Letter Qaf", "Meccan", 45, 26),
        Surah(51, "الذاريات", "Adh-Dhariyat", "The Winnowing Winds", "Meccan", 60, 26),
        Surah(52, "الطور", "At-Tur", "The Mount", "Meccan", 49, 27),
        Surah(53, "النجم", "An-Najm", "The Star", "Meccan", 62, 27),
        Surah(54, "القمر", "Al-Qamar", "The Moon", "Meccan", 55, 27),
        Surah(55, "الرحمن", "Ar-Rahman", "The Beneficent", "Medinan", 78, 27),
        Surah(56, "الواقعة", "Al-Waqi'ah", "The Inevitable", "Meccan", 96, 27),
        Surah(57, "الحديد", "Al-Hadid", "The Iron", "Medinan", 29, 27),
        Surah(58, "المجادلة", "Al-Mujadila", "The Pleading Woman", "Medinan", 22, 28),
        Surah(59, "الحشر", "Al-Hashr", "The Exile", "Medinan", 24, 28),
        Surah(60, "الممتحنة", "Al-Mumtahanah", "She that is to be examined", "Medinan", 13, 28),
        Surah(61, "الصف", "As-Saff", "The Ranks", "Medinan", 14, 28),
        Surah(62, "الجمعة", "Al-Jumu'ah", "The Congregation, Friday", "Medinan", 11, 28),
        Surah(63, "المنافقون", "Al-Munafiqun", "The Hypocrites", "Medinan", 11, 28),
        Surah(64, "التغابن", "At-Taghabun", "Mutual Disillusion", "Medinan", 18, 28),
        Surah(65, "الطلاق", "At-Talaq", "The Divorce", "Medinan", 12, 28),
        Surah(66, "التحريم", "At-Tahrim", "The Prohibition", "Medinan", 12, 28),
        Surah(67, "الملك", "Al-Mulk", "The Sovereignty", "Meccan", 30, 29),
        Surah(68, "القلم", "Al-Qalam", "The Pen", "Meccan", 52, 29),
        Surah(69, "الحاقة", "Al-Haqqah", "The Inevitable Reality", "Meccan", 52, 29),
        Surah(70, "المعارج", "Al-Ma'arij", "The Ascending Stairways", "Meccan", 44, 29),
        Surah(71, "نوح", "Nuh", "Noah", "Meccan", 28, 29),
        Surah(72, "الجن", "Al-Jinn", "The Jinn", "Meccan", 28, 29),
        Surah(73, "المزمل", "Al-Muzzammil", "The Enshrouded One", "Meccan", 20, 29),
        Surah(74, "المدثر", "Al-Muddaththir", "The Cloaked One", "Meccan", 56, 29),
        Surah(75, "القيامة", "Al-Qiyamah", "The Resurrection", "Meccan", 40, 29),
        Surah(76, "الإنسان", "Al-Insan", "Man", "Medinan", 31, 29),
        Surah(77, "المرسلات", "Al-Mursalat", "The Emissaries", "Meccan", 50, 29),
        Surah(78, "النبأ", "An-Naba", "The Tidings", "Meccan", 40, 30),
        Surah(79, "النازعات", "An-Nazi'at", "Those who drag forth", "Meccan", 46, 30),
        Surah(80, "عبس", "Abasa", "He Frowned", "Meccan", 42, 30),
        Surah(81, "التكوير", "At-Takwir", "The Overthrowing", "Meccan", 29, 30),
        Surah(82, "الانفطار", "Al-Infitar", "The Cleaving", "Meccan", 19, 30),
        Surah(83, "المطففين", "Al-Mutaffifin", "Defrauding", "Meccan", 36, 30),
        Surah(84, "الانشقاق", "Al-Inshiqaq", "The Splitting Asunder", "Meccan", 25, 30),
        Surah(85, "البروج", "Al-Buruj", "The Mansions of the Stars", "Meccan", 22, 30),
        Surah(86, "الطارق", "At-Tariq", "The Morning Star", "Meccan", 17, 30),
        Surah(87, "الأعلى", "Al-A'la", "The Most High", "Meccan", 19, 30),
        Surah(88, "الغاشية", "Al-Ghashiyah", "The Overwhelming", "Meccan", 26, 30),
        Surah(89, "الفجر", "Al-Fajr", "The Dawn", "Meccan", 30, 30),
        Surah(90, "البلد", "Al-Balad", "The City", "Meccan", 20, 30),
        Surah(91, "الشمس", "Ash-Shams", "The Sun", "Meccan", 15, 30),
        Surah(92, "الليل", "Al-Layl", "The Night", "Meccan", 21, 30),
        Surah(93, "الضحى", "Ad-Duha", "The Morning Hours", "Meccan", 11, 30),
        Surah(94, "الشرح", "Ash-Sharh", "The Relief", "Meccan", 8, 30),
        Surah(95, "التين", "At-Tin", "The Fig", "Meccan", 8, 30),
        Surah(96, "العلق", "Al-'Alaq", "The Clot", "Meccan", 19, 30),
        Surah(97, "القدر", "Al-Qadr", "The Night of Decree", "Meccan", 5, 30),
        Surah(98, "البينة", "Al-Bayyinah", "The Clear Proof", "Medinan", 8, 30),
        Surah(99, "الزلزلة", "Az-Zalzalah", "The Earthquake", "Medinan", 8, 30),
        Surah(100, "العاديات", "Al-'Adiyat", "The Courser", "Meccan", 11, 30),
        Surah(101, "القارعة", "Al-Qari'ah", "The Calamity", "Meccan", 11, 30),
        Surah(102, "التكاثر", "At-Takathur", "Rivalry in world increase", "Meccan", 8, 30),
        Surah(103, "العصر", "Al-'Asr", "The Declining Day", "Meccan", 3, 30),
        Surah(104, "الهمزة", "Al-Humazah", "The Traducer", "Meccan", 9, 30),
        Surah(105, "الفيل", "Al-Fil", "The Elephant", "Meccan", 5, 30),
        Surah(106, "قريش", "Quraysh", "Quraysh", "Meccan", 4, 30),
        Surah(107, "الماعون", "Al-Ma'un", "Small Kindness", "Meccan", 7, 30),
        Surah(108, "الكوثر", "Al-Kawthar", "Abundance", "Meccan", 3, 30),
        Surah(109, "الكافرون", "Al-Kafirun", "The Disbelievers", "Meccan", 6, 30),
        Surah(110, "النصر", "An-Nasr", "The Divine Support", "Medinan", 3, 30),
        Surah(111, "المسد", "Al-Masad", "The Palm Fiber", "Meccan", 5, 30),
        Surah(112, "الإخلاص", "Al-Ikhlas", "The Sincerity", "Meccan", 4, 30),
        Surah(113, "الفلق", "Al-Falaq", "The Daybreak", "Meccan", 5, 30),
        Surah(114, "الناس", "An-Nas", "Mankind", "Meccan", 6, 30)
    )

    val juzList: List<JuzInfo> = listOf(
        JuzInfo(1, "الم", 1, 1, "Al-Fatihah"),
        JuzInfo(2, "سيقول", 2, 142, "Al-Baqarah"),
        JuzInfo(3, "تلك الرسل", 2, 253, "Al-Baqarah"),
        JuzInfo(4, "لن تنالوا", 3, 92, "Ali 'Imran"),
        JuzInfo(5, "والمحصنات", 4, 24, "An-Nisa"),
        JuzInfo(6, "لا يحب الله", 4, 148, "An-Nisa"),
        JuzInfo(7, "وإذا سمعوا", 5, 82, "Al-Ma'idah"),
        JuzInfo(8, "ولو أننا", 6, 111, "Al-An'am"),
        JuzInfo(9, "قال الملأ", 7, 88, "Al-A'raf"),
        JuzInfo(10, "واعلموا", 8, 41, "Al-Anfal"),
        JuzInfo(11, "يعتذرون", 9, 93, "At-Tawbah"),
        JuzInfo(12, "وما من دابة", 11, 6, "Hud"),
        JuzInfo(13, "وما أبرئ", 12, 53, "Yusuf"),
        JuzInfo(14, "ربما", 15, 1, "Al-Hijr"),
        JuzInfo(15, "سبحان الذي", 17, 1, "Al-Isra"),
        JuzInfo(16, "قال ألم", 18, 75, "Al-Kahf"),
        JuzInfo(17, "اقترب للناس", 21, 1, "Al-Anbiya"),
        JuzInfo(18, "قد أفلح", 23, 1, "Al-Mu'minun"),
        JuzInfo(19, "وقال الذين", 25, 21, "Al-Furqan"),
        JuzInfo(20, "أمن خلق", 27, 56, "An-Naml"),
        JuzInfo(21, "اتل ما أوحي", 29, 45, "Al-'Ankabut"),
        JuzInfo(22, "ومن يقنت", 33, 31, "Al-Ahzab"),
        JuzInfo(23, "وما أنزلنا", 36, 28, "Ya-Sin"),
        JuzInfo(24, "فمن أظلم", 39, 32, "Az-Zumar"),
        JuzInfo(25, "إليه يرد", 41, 47, "Fussilat"),
        JuzInfo(26, "حم", 46, 1, "Al-Ahqaf"),
        JuzInfo(27, "قال فما خطبكم", 51, 31, "Adh-Dhariyat"),
        JuzInfo(28, "قد سمع الله", 58, 1, "Al-Mujadila"),
        JuzInfo(29, "تبارك الذي", 67, 1, "Al-Mulk"),
        JuzInfo(30, "عمّ", 78, 1, "An-Naba")
    )

    private val ayahsDatabase: Map<Int, List<Ayah>> = mapOf(
        1 to listOf(
            Ayah(1, 1, "بِسْمِ اللَّهِ الرَّحْمَٰنِ الرَّحِيمِ", "Bismi Allāhi ar-raḥmāni ar-raḥīm", "In the name of Allah, the Entirely Merciful, the Especially Merciful."),
            Ayah(1, 2, "الْحَمْدُ لِلَّهِ رَبِّ الْعَالَمِينَ", "Al-ḥamdu lillāhi rabbi al-'ālamīn", "[All] praise is [due] to Allah, Lord of the worlds -"),
            Ayah(1, 3, "الرَّحْمَٰنِ الرَّحِيمِ", "Ar-raḥmāni ar-raḥīm", "The Entirely Merciful, the Especially Merciful,"),
            Ayah(1, 4, "مَالِكِ يَوْمِ الدِّينِ", "Māliki yawmi ad-dīn", "Sovereign of the Day of Recompense."),
            Ayah(1, 5, "إِيَّاكَ نَعْبُدُ وَإِيَّاكَ نَسْتَعِينُ", "Iyyāka na'budu wa-iyyāka nasta'īn", "It is You we worship and You we ask for help."),
            Ayah(1, 6, "اهْدِنَا الصِّرَاطَ الْمُسْتَقِيمَ", "Ihdinā aṣ-ṣirāṭa al-mustaqīm", "Guide us to the straight path -"),
            Ayah(1, 7, "صِرَاطَ الَّذِينَ أَنْعَمْتَ عَلَيْهِمْ غَيْرِ الْمَغْضُوبِ عَلَيْهِمْ وَلَا الضَّالِّينَ", "Ṣirāṭa alladhīna an'amta 'alayhim ghayri al-maghḍūbi 'alayhim walā aḍ-ḍāllīn", "The path of those upon whom You have bestowed favor, not of those who have evoked [Your] anger or of those who are astray.")
        ),
        2 to listOf(
            Ayah(2, 255, "اللَّهُ لَا إِلَٰهَ إِلَّا هُوَ الْحَيُّ الْقَيُّومُ ۚ لَا تَأْخُذُهُ سِنَةٌ وَلَا نَوْمٌ ۚ لَّهُ مَا فِي السَّمَاوَاتِ وَمَا فِي الْأَرْضِ ۗ مَن ذَا الَّذِي يَشْفَعُ عِندَهُ إِلَّا بِإِذْنِهِ ۚ يَعْلَمُ مَا بَيْنَ أَيْدِيهِمْ وَمَا خَلْفَهُمْ ۖ وَلَا يُحِيطُونَ بِشَيْءٍ مِّنْ عِلْمِهِ إِلَّا بِمَا شَاءَ ۚ وَسِعَ كُرْسِيُّهُ السَّمَاوَاتِ وَالْأَرْضَ ۖ وَلَا يَئُودُهُ حِفْظُهُمَا ۚ وَهُوَ الْعَلِيُّ الْعَظِيمُ", "Allāhu lā ilāha illā huwa al-ḥayyu al-qayyūm, lā ta'khudhuhu sinatun walā nawm, lahu mā fī as-samāwāti wamā fī al-arḍ...", "Allah! There is no deity except Him, the Ever-Living, the Sustainer of all existence. Neither drowsiness overtakes Him nor sleep. To Him belongs whatever is in the heavens and whatever is on the earth. Who is it that can intercede with Him except by His permission? He knows what is before them and what will be after them, and they encompass not a thing of His knowledge except for what He wills. His Kursi extends over the heavens and the earth, and their preservation tires Him not. And He is the Most High, the Most Great. (Ayat al-Kursi)"),
            Ayah(2, 285, "آمَنَ الرَّسُولُ بِمَا أُنزِلَ إِلَيْهِ مِن رَّبِّهِ وَالْمُؤْمِنُونَ ۚ كُلٌّ آمَنَ بِاللَّهِ وَمَلَائِكَتِهِ وَكُتُبِهِ وَرُسُلِهِ لَا نُفَرِّقُ بَيْنَ أَحَدٍ مِّن رُّسُلِهِ ۚ وَقَالُوا سَمِعْنَا وَأَطَعْنَا ۖ غُفْرَانَكَ رَبَّنَا وَإِلَيْكَ الْمَصِيرُ", "Āmana ar-rasūlu bimā unzila ilayhi min rabbihi wal-mu'minūn...", "The Messenger has believed in what was revealed to him from his Lord, and [so have] the believers. All of them have believed in Allah and His angels and His books and His messengers, [saying], 'We make no distinction between any of His messengers.' And they say, 'We hear and we obey. [We seek] Your forgiveness, our Lord, and to You is the final destination.'"),
            Ayah(2, 286, "لَا يُكَلِّفُ اللَّهُ نَفْسًا إِلَّا وُسْعَهَا ۚ لَهَا مَا كَسَبَتْ وَعَلَيْهَا مَا اكْتَسَبَتْ ۗ رَبَّنَا لَا تُؤَاخِذْنَا إِن نَّسِينَا أَوْ أَخْطَأْنَا ۚ رَبَّنَا وَلَا تَحْمِلْ عَلَيْنَا إِصْرًا كَمَا حَمَلْتَهُ عَلَى الَّذِينَ مِن قَبْلِنَا ۚ رَبَّنَا وَلَا تُحَمِّلْنَا مَا لَا طَاقَةَ لَنَا بِهِ ۖ وَاعْفُ عَنَّا وَاغْفِرْ لَنَا وَارْحَمْنَا ۚ أَنتَ مَوْلَانَا فَانصُرْنَا عَلَى الْقَوْمِ الْكَافِرِينَ", "Lā yukallifu Allāhu nafsan illā wus'ahā...", "Allah does not burden a soul beyond that it can bear. It will have [the consequence of] what [good] it has gained, and it will bear [the consequence of] what [evil] it has earned. 'Our Lord, do not impose blame upon us if we have forgotten or erred. Our Lord, and lay not upon us a burden like that which You laid upon those before us. Our Lord, and burden us not with that which we have no ability to bear. And pardon us; and forgive us; and have mercy upon us. You are our protector, so give us victory over the disbelieving people.'")
        ),
        36 to listOf(
            Ayah(36, 1, "يس", "Yā-Sīn", "Ya, Seen."),
            Ayah(36, 2, "وَالْقُرْآنِ الْحَكِيمِ", "Wal-qur'āni al-ḥakīm", "By the wise Qur'an,"),
            Ayah(36, 3, "إِنَّكَ لَمِنَ الْمُرْسَلِينَ", "Innaka lamina al-mursalīn", "Indeed you, [O Muhammad], are from among the messengers,"),
            Ayah(36, 4, "عَلَىٰ صِرَاطٍ مُّسْتَقِيمٍ", "'Alā ṣirāṭin mustaqīm", "On a straight path."),
            Ayah(36, 5, "تَنزِيلَ الْعَزِيزِ الرَّحِيمِ", "Tanzīla al-'azīzi ar-raḥīm", "[This is] a revelation of the Exalted in Might, the Merciful,")
        ),
        67 to listOf(
            Ayah(67, 1, "تَبَارَكَ الَّذِي بِيَدِهِ الْمُلْكُ وَهُوَ عَلَىٰ كُلِّ شَيْءٍ قَدِيرٌ", "Tabāraka alladhī biyadihi al-mulku wahuwa 'alā kulli shay'in qadīr", "Blessed is He in whose hand is dominion, and He is over all things competent -"),
            Ayah(67, 2, "الَّذِي خَلَقَ الْمَوْتَ وَالْحَيَاةَ لِيَبْلُوَكُمْ أَيُّكُمْ أَحْسَنُ عَمَلًا ۚ وَهُوَ الْعَزِيزُ الْغَفُورُ", "Alladhī khalaqa al-mawta wal-ḥayāta liyabluwakum ayyukum aḥsanu 'amalā...", "[He] who created death and life to test you [as to] which of you is best in deed - and He is the Exalted in Might, the Forgiving -"),
            Ayah(67, 3, "الَّذِي خَلَقَ سَبْعَ سَمَاوَاتٍ طِبَاقًا ۖ مَّا تَرَىٰ فِي خَلْقِ الرَّحْمَٰنِ مِن تَفَاوُتٍ", "Alladhī khalaqa sab'a samāwātin ṭibāqan...", "[And] who created seven heavens in layers. You see no disparity in the creation of the Most Merciful."),
            Ayah(67, 4, "فَارْجِعِ الْبَصَرَ هَلْ تَرَىٰ مِن فُطُورٍ", "Farji'i al-baṣara hal tarā min fuṭūr", "So return [your] vision [to the sky]; do you see any breaks?")
        ),
        93 to listOf(
            Ayah(93, 1, "وَالضُّحَىٰ", "Waḍ-ḍuḥā", "By the morning brightness"),
            Ayah(93, 2, "وَاللَّيْلِ إِذَا سَجَىٰ", "Wal-layli idhā sajā", "And [by] the night when it covers with darkness,"),
            Ayah(93, 3, "مَا وَدَّعَكَ رَبُّكَ وَمَا قَلَىٰ", "Mā wadda'aka rabbuka wamā qalā", "Your Lord has not taken leave of you, [O Muhammad], nor has He detested [you]."),
            Ayah(93, 4, "وَلَلْآخِرَةُ خَيْرٌ لَّكَ مِنَ الْأُولَىٰ", "Walal-ākhiratu khayrun laka mina al-ūlā", "And the Hereafter is better for you than the first [life]."),
            Ayah(93, 5, "وَلَسَوْفَ يُعْطِيكَ رَبُّكَ فَتَرْضَىٰ", "Walasawfa yu'ṭīka rabbuka fatarḍā", "And your Lord is going to give you, and you will be satisfied."),
            Ayah(93, 6, "أَلَمْ يَجِدْكَ يَتِيمًا فَآوَىٰ", "Alam yajidka yatīman fa-āwā", "Did He not find you an orphan and give [you] refuge?"),
            Ayah(93, 7, "وَوَجَدَكَ ضَالًّا فَهَدَىٰ", "Wawajadaka ḍāllan fahadā", "And He found you lost and guided [you],"),
            Ayah(93, 8, "وَوَجَدَكَ عَائِلًا فَأَغْنَىٰ", "Wawajadaka 'ā'ilan fa-aghnā", "And He found you in need and made [you] self-sufficient."),
            Ayah(93, 9, "فَأَمَّا الْيَتِيمَ فَلَا تَقْهَرْ", "Fa-ammā al-yatīma falā taqhar", "So as for the orphan, do not oppress [him]."),
            Ayah(93, 10, "وَأَمَّا السَّائِلَ فَلَا تَنْهَرْ", "Wa-ammā as-sā'ila falā tanhar", "And as for the petitioner, do not repel [him]."),
            Ayah(93, 11, "وَأَمَّا بِنِعْمَةِ رَبِّكَ فَحَدِّثْ", "Wa-ammā bini'mati rabbika faḥaddith", "But as for the favor of your Lord, report [it].")
        ),
        94 to listOf(
            Ayah(94, 1, "أَلَمْ نَشْرَحْ لَكَ صَدْرَكَ", "Alam nashraḥ laka ṣadrak", "Did We not expand for you, [O Muhammad], your breast?"),
            Ayah(94, 2, "وَوَضَعْنَا عَنكَ وِزْرَكَ", "Wawaḍa'nā 'anka wizrak", "And We removed from you your burden"),
            Ayah(94, 3, "الَّذِي أَنقَضَ ظَهْرَكَ", "Alladhī anqaḍa ẓahrak", "Which had weighed upon your back"),
            Ayah(94, 4, "وَرَفَعْنَا لَكَ ذِكْرَكَ", "Warafa'nā laka dhikrak", "And raised high for you your repute."),
            Ayah(94, 5, "فَإِنَّ مَعَ الْعُسْرِ يُسْرًا", "Fa-inna ma'a al-'usri yusrā", "For indeed, with hardship [will be] ease."),
            Ayah(94, 6, "إِنَّ مَعَ الْعُسْرِ يُسْرًا", "Inna ma'a al-'usri yusrā", "Indeed, with hardship [will be] ease."),
            Ayah(94, 7, "فَإِذَا فَرَغْتَ فَانصَبْ", "Fa-idhā faraghta fanṣab", "So when you have finished [your duties], then stand up [for worship]."),
            Ayah(94, 8, "وَإِلَىٰ رَبِّكَ فَارْغَب", "Wa-ilā rabbika farghab", "And to your Lord direct [your] longing.")
        ),
        103 to listOf(
            Ayah(103, 1, "وَالْعَصْرِ", "Wal-'aṣr", "By time,"),
            Ayah(103, 2, "إِنَّ الْإِنسَانَ لَفِي خُسْرٍ", "Inna al-insāna lafī khusr", "Indeed, mankind is in loss,"),
            Ayah(103, 3, "إِلَّا الَّذِينَ آمَنُوا وَعَمِلُوا الصَّالِحَاتِ وَتَوَاصَوْا بِالْحَقِّ وَتَوَاصَوْا بِالصَّبْرِ", "Illā alladhīna āmanū wa'amilū aṣ-ṣāliḥāti watawāṣaw bil-ḥaqqi watawāṣaw biṣ-ṣabr", "Except for those who have believed and done righteous deeds and advised each other to truth and advised each other to patience.")
        ),
        108 to listOf(
            Ayah(108, 1, "إِنَّا أَعْطَيْنَاكَ الْكَوْثَرَ", "Innā a'ṭaynāka al-kawthar", "Indeed, We have granted you, [O Muhammad], al-Kawthar."),
            Ayah(108, 2, "فَصَلِّ لِرَبِّكَ وَانْحَرْ", "Faṣalli lirabbika wanḥar", "So pray to your Lord and sacrifice [to Him alone]."),
            Ayah(108, 3, "إِنَّ شَانِئَكَ هُوَ الْأَبْتَرُ", "Inna shāni'aka huwa al-abtar", "Indeed, your enemy is the one cut off.")
        ),
        112 to listOf(
            Ayah(112, 1, "قُلْ هُوَ اللَّهُ أَحَدٌ", "Qul huwa Allāhu aḥad", "Say, 'He is Allah, [who is] One,"),
            Ayah(112, 2, "اللَّهُ الصَّمَدُ", "Allāhu aṣ-ṣamad", "Allah, the Eternal Refuge."),
            Ayah(112, 3, "لَمْ يَلِدْ وَلَمْ يُولَدْ", "Lam yalid walam yūlad", "He neither begets nor is born,"),
            Ayah(112, 4, "وَلَمْ يَكُن لَّهُ كُفُوًا أَحَدٌ", "Walam yakun lahu kufuwan aḥad", "Nor is there to Him any equivalent.'")
        ),
        113 to listOf(
            Ayah(113, 1, "قُلْ أَعُوذُ بِرَبِّ الْفَلَقِ", "Qul a'ūdhu birabbi al-falaq", "Say, 'I seek refuge in the Lord of daybreak"),
            Ayah(113, 2, "مِن شَرِّ مَا خَلَقَ", "Min sharri mā khalaq", "From the evil of that which He created"),
            Ayah(113, 3, "وَمِن شَرِّ غَاسِقٍ إِذَا وَقَبَ", "Wamin sharri ghāsiqin idhā waqab", "And from the evil of darkness when it settles"),
            Ayah(113, 4, "وَمِن شَرِّ النَّفَّاثَاتِ فِي الْعُقَدِ", "Wamin sharri an-naffāthāti fī al-'uqad", "And from the evil of the blowers in knots"),
            Ayah(113, 5, "وَمِن شَرِّ حَاسِدٍ إِذَا حَسَدَ", "Wamin sharri ḥāsidin idhā ḥasad", "And from the evil of an envier when he envies.'")
        ),
        114 to listOf(
            Ayah(114, 1, "قُلْ أَعُوذُ بِرَبِّ النَّاسِ", "Qul a'ūdhu birabbi an-nās", "Say, 'I seek refuge in the Lord of mankind,"),
            Ayah(114, 2, "مَلِكِ النَّاسِ", "Maliki an-nās", "The Sovereign of mankind,"),
            Ayah(114, 3, "إِلَٰهِ النَّاسِ", "Ilāhi an-nās", "The God of mankind,"),
            Ayah(114, 4, "مِن شَرِّ الْوَسْوَاسِ الْخَنَّاسِ", "Min sharri al-waswāsi al-khannās", "From the evil of the retreating whisperer -"),
            Ayah(114, 5, "الَّذِي يُوَسْوِسُ فِي صُدُورِ النَّاسِ", "Alladhī yuwaswisu fī ṣudūri an-nās", "Who whispers into the breasts of mankind -"),
            Ayah(114, 6, "مِنَ الْجِنَّةِ وَالنَّاسِ", "Mina al-jinnati wan-nās", "From among the jinn and mankind.'")
        )
    )

    fun getAyahsForSurah(surahNumber: Int): List<Ayah> {
        return ayahsDatabase[surahNumber] ?: listOf(
            Ayah(
                surahNumber = surahNumber,
                ayahNumber = 1,
                textArabic = "بِسْمِ اللَّهِ الرَّحْمَٰنِ الرَّحِيمِ",
                textTransliteration = "Bismi Allāhi ar-raḥmāni ar-raḥīm",
                textTranslation = "In the name of Allah, the Entirely Merciful, the Especially Merciful."
            ),
            Ayah(
                surahNumber = surahNumber,
                ayahNumber = 2,
                textArabic = "الْحَمْدُ لِلَّهِ رَبِّ الْعَالَمِينَ ۝ قُلْ إِنَّ صَلَاتِي وَنُسُكِي وَمَحْيَايَ وَمَمَاتِي لِلَّهِ رَبِّ الْعَالَمِينَ",
                textTransliteration = "Qul inna ṣalātī wanusukī wamaḥyāya wamamātī lillāhi rabbi al-'ālamīn",
                textTranslation = "Say, 'Indeed, my prayer, my rites of sacrifice, my living and my dying are for Allah, Lord of the worlds.' (Quran 6:162)"
            )
        )
    }

    fun getSurahByNumber(number: Int): Surah? {
        return surahs.find { it.number == number }
    }
}
