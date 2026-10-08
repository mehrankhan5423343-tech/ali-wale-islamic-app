package com.example.data.content

import com.example.data.model.KnowledgeArticle
import com.example.data.model.KnowledgeCategory

object KnowledgeData {

    val articles: List<KnowledgeArticle> = listOf(
        KnowledgeArticle(
            id = "aqeedah_1",
            category = KnowledgeCategory.AQEEDAH,
            title = "Tawheed: The Heartbeat of Islamic Belief",
            arabicSubtitle = "التوحيد الخالص لله",
            readTimeMinutes = 4,
            summary = "Understanding the three dimensions of Islamic monotheism: Rububiyyah (Lordship), Uluhiyyah (Worship), and Asma wa Sifat (Names and Attributes).",
            keyLessons = listOf(
                "Allah alone created, sustains, and governs the universe without partners.",
                "Directing any act of worship (prayer, supplication, vows) to other than Allah compromises the core covenant.",
                "Affirming Allah's beautiful names and exalted attributes as described in the Quran and Sunnah without denial or anthropomorphism."
            ),
            bodyParagraphs = listOf(
                "Tawheed is the cornerstone of Islam and the primary mission for which every prophet was sent. It literally means making something one or asserting oneness.",
                "Tawheed ar-Rububiyyah acknowledges that Allah alone is the Creator, Provider, and Sovereign of all existence. Even ancient polytheists acknowledged this dimension, as noted in Surah Luqman (31:25).",
                "Tawheed al-Uluhiyyah is where sincerity is tested: dedicating every internal and external act of devotion, hope, fear, and supplication purely to Allah alone.",
                "Tawheed al-Asma wa as-Sifat is knowing Allah through His ninety-nine beautiful names and exalted attributes, inspiring both profound reverence (khashyah) and boundless hope (raja)."
            ),
            references = listOf(
                "Quran 112:1-4 (Surah Al-Ikhlas)",
                "Quran 2:255 (Ayat al-Kursi)",
                "Kitab at-Tawheed by classical scholars"
            )
        ),
        KnowledgeArticle(
            id = "seerah_1",
            category = KnowledgeCategory.SEERAH,
            title = "The Mercy to the Worlds: Character of Muhammad ﷺ",
            arabicSubtitle = "وما أرسلناك إلا رحمة للعالمين",
            readTimeMinutes = 5,
            summary = "An exploration of the Prophet Muhammad's ﷺ gentle character, sublime forbearance, and unwavering justice towards companions, strangers, and adversaries alike.",
            keyLessons = listOf(
                "The Prophet's primary attribute was divine mercy and empathy toward all creation.",
                "He never sought personal retribution when wronged; he forgave generously.",
                "He modeled humbleness in daily domestic life, mending his own clothes and serving others."
            ),
            bodyParagraphs = listOf(
                "When asked about the character of the Messenger of Allah ﷺ, his wife Aisha (may Allah be pleased with her) famously replied: 'His character was the Quran.' (Sahih Muslim).",
                "In his interactions with the poor, the orphaned, and animals, the Prophet demonstrated deep tenderness. He advised: 'The merciful will be shown mercy by the Most Merciful. Be merciful to those on the earth and the One in the heavens will have mercy upon you.' (Sunan Abi Dawud).",
                "At the Conquest of Makkah, having been persecuted and expelled for two decades, he entered with his head lowered in profound humility and proclaimed general amnesty: 'Go, for you are free.'"
            ),
            references = listOf(
                "Quran 21:107 (Surah Al-Anbiya)",
                "Sahih Muslim (No. 746)",
                "Ar-Raheeq Al-Makhtum (The Sealed Nectar) by Safiur Rahman Mubarakpuri"
            )
        ),
        KnowledgeArticle(
            id = "prophets_1",
            category = KnowledgeCategory.PROPHETS,
            title = "Prophet Yusuf (AS): The Beauty of Patience & Trust",
            arabicSubtitle = "فصبر جميل والله المستعان",
            readTimeMinutes = 4,
            summary = "Lessons from the best of stories: transforming betrayal, abandonment, false accusation, and imprisonment into spiritual elevation through Sabr and Ihsan.",
            keyLessons = listOf(
                "Hardship is often a disguised pathway to divine elevation when met with patience.",
                "Excellence of character (Ihsan) shines even in the darkest circumstances.",
                "Forgiveness cleanses the heart and invites divine honor."
            ),
            bodyParagraphs = listOf(
                "The Quran terms the narrative of Prophet Yusuf 'Ahsan al-Qasas' (the best of stories). Thrown into a dry well by his envious siblings, sold into servitude in Egypt, and falsely imprisoned for years, Yusuf remained anchored in Tawakkul.",
                "Even in prison, inmates recognized his nobility: 'Indeed, we see you to be of the doers of good.' (Quran 12:36). He never let unjust surroundings erode his inner righteousness.",
                "When reunited in victory and authority, he held no malice, saying to his brothers: 'No blame will there be upon you today. Allah will forgive you; and He is the most merciful of the merciful.' (Quran 12:92)."
            ),
            references = listOf(
                "Surah Yusuf (Chapter 12)",
                "Tafsir Ibn Kathir"
            )
        ),
        KnowledgeArticle(
            id = "sahabah_1",
            category = KnowledgeCategory.SAHABAH,
            title = "Ali ibn Abi Talib (RA): The Gate of Knowledge & Courage",
            arabicSubtitle = "باب مدينة العلم والشجاعة",
            readTimeMinutes = 5,
            summary = "A biography of the Fourth Rightly Guided Caliph, renowned for his uncompromising justice, profound spiritual wisdom, eloquent sermons, and fearlessness in truth.",
            keyLessons = listOf(
                "Early dedication to truth: He accepted Islam as a youth and stood steadfast through every trial.",
                "Selfless devotion: He risked his life sleeping in the Prophet's bed on the night of Hijrah.",
                "Eloquent wisdom: His sayings embody deep asceticism (Zuhd), justice, and reliance on Allah."
            ),
            bodyParagraphs = listOf(
                "Ali ibn Abi Talib (may Allah be pleased with him) was the cousin and son-in-law of the Prophet ﷺ, married to Lady Fatimah az-Zahra, and father of Al-Hasan and Al-Husayn.",
                "The Prophet ﷺ honored his immense knowledge and clarity of judgment: 'I am the city of knowledge and Ali is its gate.' (Jami' at-Tirmidhi). He was consulted on complex legal matters throughout the caliphates of Abu Bakr, Umar, and Uthman.",
                "His leadership was marked by austere personal living, vigilance over the public treasury (Bayt al-Mal), and compassion for the destitute. He famously reminded his governors: 'People are of two types: either your brother in faith, or your equal in humanity.'"
            ),
            references = listOf(
                "Jami' at-Tirmidhi (No. 3723)",
                "Al-Bidayah wan-Nihayah by Ibn Kathir",
                "Nahj al-Balagha (Historical records)"
            )
        ),
        KnowledgeArticle(
            id = "akhlaq_1",
            category = KnowledgeCategory.AKHLAQ,
            title = "The Power of Gentle Speech & Restraining Anger",
            arabicSubtitle = "وقولوا للناس حسنا وكظم الغيظ",
            readTimeMinutes = 3,
            summary = "How Islam transforms internal impulses: understanding that true strength lies not in wrestling down an opponent, but in mastering one's tongue and temper.",
            keyLessons = listOf(
                "True strength is emotional mastery during moments of anger.",
                "Gentle words possess transformative power to turn enmity into friendship.",
                "Seeking refuge in Allah, changing posture, and performing wudu extinguish anger."
            ),
            bodyParagraphs = listOf(
                "The Messenger of Allah ﷺ said: 'The strong person is not the one who can wrestle someone down. Rather, the strong person is the one who controls himself when angry.' (Sahih al-Bukhari).",
                "When a man repeatedly asked the Prophet ﷺ for counsel, the Prophet replied concisely each time: 'Do not become angry.' (Sahih al-Bukhari). This advice protects relationships, mental health, and faith.",
                "The Quran praises the believers who: '...suppress anger and pardon the people; and Allah loves the doers of good.' (Quran 3:134)."
            ),
            references = listOf(
                "Sahih al-Bukhari (No. 6114, 6116)",
                "Quran 3:134 (Surah Ali 'Imran)"
            )
        ),
        KnowledgeArticle(
            id = "salah_1",
            category = KnowledgeCategory.SALAH,
            title = "Khushu': Bringing Life to Your Daily Prayers",
            arabicSubtitle = "الخشوع روح الصلاة",
            readTimeMinutes = 4,
            summary = "A practical guide to experiencing tranquility, presence of heart, and awe in daily Salah, moving beyond routine motion into intimate conversation with Allah.",
            keyLessons = listOf(
                "Salah is a personal conversation: In Al-Fatihah, Allah responds to every verse recited.",
                "Preparing with mindful wudu and arriving early calms racing thoughts before the opening Takbir.",
                "Pausing in each posture (Tuma'ninah) allows contemplation of the glorifications uttered."
            ),
            bodyParagraphs = listOf(
                "In a sacred Hadith Qudsi, Allah states: 'I have divided the prayer between Myself and My servant into two halves...' Whenever the servant recites, Allah affirms His response (Sahih Muslim).",
                "Khushu' is not an elusive state reserved for mystics; it is the deliberate stillness of the limbs and attentiveness of the mind. Looking at the place of prostration and slowing down breathing directly enhances presence.",
                "The Prophet ﷺ would say to Bilal when prayer time entered: 'Relieve us with it, O Bilal!' Prayer was their oasis of peace amid life's relentless demands."
            ),
            references = listOf(
                "Sahih Muslim (No. 395)",
                "Sunan Abi Dawud (No. 4985)",
                "Al-Khushu' fis-Salah by Ibn Rajab al-Hanbali"
            )
        ),
        KnowledgeArticle(
            id = "fasting_1",
            category = KnowledgeCategory.FASTING,
            title = "The Inner Dimensions of Fasting (Sawm)",
            arabicSubtitle = "لعلكم تتقون: أسرار الصيام",
            readTimeMinutes = 4,
            summary = "Why fasting is more than abstaining from food and drink: refining the soul, cultivating empathy, and building voluntary self-discipline for Allah's pleasure.",
            keyLessons = listOf(
                "The ultimate objective of fasting is Taqwa: mindful consciousness of Allah.",
                "Fasting of the senses: safeguarding eyes, ears, and speech from harmful content.",
                "Deep gratitude for everyday sustenance often taken for granted."
            ),
            bodyParagraphs = listOf(
                "Fasting is unique among acts of worship because it is purely hidden. In a Hadith Qudsi: 'Every deed of the son of Adam is for him, except fasting; it is for Me, and I shall reward it.' (Sahih al-Bukhari).",
                "Imam al-Ghazali classified fasting into three levels: the fast of the general public (abstaining from food and desires), the fast of the select (protecting limbs and speech from transgression), and the fast of the elite (guarding the heart from worldly distraction).",
                "Experiencing hunger voluntarily connects the heart directly with the struggles of the less fortunate, igniting genuine charity and generosity."
            ),
            references = listOf(
                "Quran 2:183 (Surah Al-Baqarah)",
                "Sahih al-Bukhari (No. 1904)",
                "Ihya' Ulum al-Din by Imam al-Ghazali"
            )
        ),
        KnowledgeArticle(
            id = "zakat_1",
            category = KnowledgeCategory.ZAKAT,
            title = "Zakat & Sadaqah: Purifying Wealth, Uplifting Humanity",
            arabicSubtitle = "خذ من أموالهم صدقة تطهرهم",
            readTimeMinutes = 4,
            summary = "The social and spiritual philosophy of Islamic philanthropy: why giving never decreases wealth and how Zakat dissolves economic division.",
            keyLessons = listOf(
                "Zakat is an obligatory right of the underprivileged, not discretionary charity.",
                "Wealth is a trust (Amanah) from Allah to be utilized ethically.",
                "Sadaqah encompasses every kind act: smiling, removing harm from a path, or speaking comfort."
            ),
            bodyParagraphs = listOf(
                "The word Zakat derives from root meanings signifying purification and increase. By contributing 2.5% of accumulated surplus wealth above the Nisab threshold, the believer purges their remaining wealth of greed and apathy.",
                "The Prophet ﷺ made a profound oath: 'Charity does not decrease wealth.' (Sahih Muslim). While mathematical numbers decrease, divine blessing (Barakah), inner peace, and spiritual protection multiply.",
                "Furthermore, Islam widens charity beyond monetary bounds: 'Every good deed is charity; your smiling in the face of your brother is charity.' (Jami' at-Tirmidhi)."
            ),
            references = listOf(
                "Quran 9:103 (Surah At-Tawbah)",
                "Sahih Muslim (No. 2588)",
                "Jami' at-Tirmidhi (No. 1956)"
            )
        ),
        KnowledgeArticle(
            id = "hajj_1",
            category = KnowledgeCategory.HAJJ,
            title = "Hajj: The Ultimate Journey of Unity & Equality",
            arabicSubtitle = "رحلة العمر إلى بيت الله الحرام",
            readTimeMinutes = 5,
            summary = "Discovering the spiritual milestones of Hajj: Ihram, Tawaf, Sa'i, and standing on the plains of Arafat where worldly distinctions dissolve.",
            keyLessons = listOf(
                "The plain white garments of Ihram strip away status, wealth, and nationality.",
                "The day of Arafat mirrors the ultimate standing before Allah in hope and repentance.",
                "Tracing the footsteps of Prophet Ibrahim, Hajar, and Prophet Muhammad ﷺ."
            ),
            bodyParagraphs = listOf(
                "Once in a lifetime for those who are physically and financially able, Hajj stands as the pinnacle of Islamic collective worship. Millions gather facing the single Qibla, unified in word and goal.",
                "Wearing the simple two white sheets of Ihram, monarchs and laborers stand shoulder to shoulder. All distinctions of social class, skin tone, and lineage vanish, manifesting true human fraternity.",
                "The core of Hajj is the day of Arafat. The Prophet ﷺ declared: 'Hajj is Arafat.' (Sunan an-Nasa'i). It is a day of intense supplication, tears, and divine forgiveness."
            ),
            references = listOf(
                "Quran 22:27-28 (Surah Al-Hajj)",
                "Sunan an-Nasa'i (No. 3016)",
                "Fiqh as-Sunnah by Sayyid Sabiq"
            )
        ),
        KnowledgeArticle(
            id = "manners_1",
            category = KnowledgeCategory.DAILY_MANNERS,
            title = "Everyday Islamic Etiquette: Adab in Modern Life",
            arabicSubtitle = "آداب المسلم وحسن المعاملة",
            readTimeMinutes = 3,
            summary = "How ordinary daily actions—greeting others, eating with the right hand, visiting the sick, and honoring neighbors—transform into continuous worship.",
            keyLessons = listOf(
                "Spreading the greeting of peace ('Assalamu Alaikum') builds mutual love.",
                "Beginning actions with Bismillah and concluding with Alhamdulillah brings barakah.",
                "The neighbor holds immense rights regardless of faith or background."
            ),
            bodyParagraphs = listOf(
                "Islam does not confine spirituality to the mosque; it transforms everyday habits into rewarded acts through intention (Niyyah).",
                "The Prophet ﷺ emphasized: 'You will not enter Paradise until you believe, and you will not believe until you love one another. Shall I not guide you to something that if you do, you will love one another? Spread peace (salam) among yourselves.' (Sahih Muslim).",
                "Eating moderately, sharing food, honoring guests, and maintaining cleanliness are all fundamental components of Islamic good character."
            ),
            references = listOf(
                "Sahih Muslim (No. 54)",
                "Sahih al-Bukhari (No. 6018)",
                "Riyad as-Salihin by Imam an-Nawawi"
            )
        ),
        KnowledgeArticle(
            id = "history_1",
            category = KnowledgeCategory.HISTORY,
            title = "Faith & Reason: The Golden Age of Islamic Science",
            arabicSubtitle = "الحضارة الإسلامية وإشعاع العلم",
            readTimeMinutes = 5,
            summary = "How early Muslim scholars integrated sincere devotion with scientific inquiry, pioneering advances in medicine, mathematics, astronomy, and optics.",
            keyLessons = listOf(
                "The Quranic injunction to reflect on creation inspired empirical scientific exploration.",
                "Institutions like the House of Wisdom (Bayt al-Hikmah) fostered translation and innovation.",
                "Scholars like Ibn al-Haytham, Al-Khwarizmi, and Ibn Sina balanced faith with rigorous methodology."
            ),
            bodyParagraphs = listOf(
                "Far from creating friction between faith and intellect, the Islamic worldview stimulated deep curiosity about the natural world as signs (Ayat) of the Creator.",
                "Al-Khwarizmi laid down algebra and algorithms; Ibn al-Haytham founded the modern scientific method in optics; Al-Zahrawi wrote surgical encyclopedias used for centuries in medical universities.",
                "This heritage demonstrates that true Islamic commitment embraces education, humanitarian service, and rational inquiry."
            ),
            references = listOf(
                "Lost Islamic History by Firas Alkhateeb",
                "1001 Inventions: Muslim Heritage in Our World"
            )
        )
    )

    fun getArticlesByCategory(category: KnowledgeCategory): List<KnowledgeArticle> {
        return articles.filter { it.category == category }
    }

    fun getArticleById(id: String): KnowledgeArticle? {
        return articles.find { it.id == id }
    }
}
