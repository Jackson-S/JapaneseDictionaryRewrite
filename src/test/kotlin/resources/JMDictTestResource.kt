package resources

object JMDictTestResource {
    val string = """
        <?xml version="1.0" encoding="UTF-8"?>
        <!-- Rev 1.09 
        	Added the g_type attribute
        -->
        <!-- Rev 1.08 
        	Delete <info> and <example> elements
        -->
        <!-- Rev 1.07 
        	Revised POS tags for the adjectives
        -->
        <!-- Rev 1.06 
        	Dropped the "*" from the end of the entry element.
        	Dropped the g_lang attribute in favour of xml:lang
        	Dropped the <lang> element and replaced it with <lsource> at the
        	sense level.
        	Moved <dial> from the entry level to the sense level.
        	Changed "info*" to "info?".
        -->
        <!-- Rev 1.05 
        	Changed the <gram> element name to <pos>
        	Added the g_gend attribute
        	moved the s_inf element
        -->
        <!-- Rev 1.04 
        	Changes:
             Rename the project  "JMdict" and add the g_lang attribute to the 
        	<gloss> entity - 08 May 1999 
             Moved the <gram>, <field> and <misc> elements down to be in the
             <sense> region, as suggested by Chris Czeyka. I have also tidied up
             some of the "*" as he suggested.  - 27 May 2000
        	 Added the re_nokanji element - Sep 2003.
             -->
        <!DOCTYPE JMdict [
        <!ELEMENT JMdict (entry*)>
        <!--                                                                   -->
        <!ELEMENT entry (ent_seq, k_ele*, r_ele+, sense+)>
        <!-- Entries consist of kanji elements, reading elements, 
        	general information and sense elements. Each entry must have at 
        	least one reading element and one sense element. Others are optional.
        	-->
        <!ELEMENT ent_seq (#PCDATA)>
        <!-- A unique numeric sequence number for each entry
        	-->
        <!ELEMENT k_ele (keb, ke_inf*, ke_pri*)>
        <!-- The kanji element, or in its absence, the reading element, is 
        	the defining component of each entry.
        	The overwhelming majority of entries will have a single kanji
        	element associated with a word in Japanese. Where there are 
        	multiple kanji elements within an entry, they will be orthographical
        	variants of the same word, either using variations in okurigana, or
        	alternative and equivalent kanji. Common "mis-spellings" may be 
        	included, provided they are associated with appropriate information
        	fields. Synonyms are not included; they may be indicated in the
        	cross-reference field associated with the sense element.
        	-->
        <!ELEMENT keb (#PCDATA)>
        <!-- This element will contain a word or short phrase in Japanese 
        	which is written using at least one non-kana character (usually kanji,
        	but can be other characters). The valid characters are
        	kanji, kana, related characters such as chouon and kurikaeshi, and
        	in exceptional cases, letters from other alphabets.
        	-->
        <!ELEMENT ke_inf (#PCDATA)>
        <!-- This is a coded information field related specifically to the 
        	orthography of the keb, and will typically indicate some unusual
        	aspect, such as okurigana irregularity.
        	-->
        <!ELEMENT ke_pri (#PCDATA)>
        <!-- This and the equivalent re_pri field are provided to record
        	information about the relative priority of the entry,  and consist
        	of codes indicating the word appears in various references which
        	can be taken as an indication of the frequency with which the word
        	is used. This field is intended for use either by applications which 
        	want to concentrate on entries of  a particular priority, or to 
        	generate subset files. 
        	The current values in this field are:
        	- news1/2: appears in the "wordfreq" file compiled by Alexandre Girardi
        	from the Mainichi Shimbun. (See the Monash ftp archive for a copy.)
        	Words in the first 12,000 in that file are marked "news1" and words 
        	in the second 12,000 are marked "news2".
        	- ichi1/2: appears in the "Ichimango goi bunruishuu", Senmon Kyouiku 
        	Publishing, Tokyo, 1998.  (The entries marked "ichi2" were
        	demoted from ichi1 because they were observed to have low
        	frequencies in the WWW and newspapers.)
        	- spec1 and spec2: a small number of words use this marker when they 
        	are detected as being common, but are not included in other lists.
        	- gai1/2: common loanwords, based on the wordfreq file.
        	- nfxx: this is an indicator of frequency-of-use ranking in the
        	wordfreq file. "xx" is the number of the set of 500 words in which
        	the entry can be found, with "01" assigned to the first 500, "02"
        	to the second, and so on. (The entries with news1, ichi1, spec1, spec2
        	and gai1 values are marked with a "(P)" in the EDICT and EDICT2
        	files.)

        	The reason both the kanji and reading elements are tagged is because 
        	on occasions a priority is only associated with a particular
        	kanji/reading pair.
        	-->
        <!--                                                                   -->
        <!ELEMENT r_ele (reb, re_nokanji?, re_restr*, re_inf*, re_pri*)>
        <!-- The reading element typically contains the valid readings
        	of the word(s) in the kanji element using modern kanadzukai. 
        	Where there are multiple reading elements, they will typically be
        	alternative readings of the kanji element. In the absence of a 
        	kanji element, i.e. in the case of a word or phrase written
        	entirely in kana, these elements will define the entry.
        	-->
        <!ELEMENT reb (#PCDATA)>
        <!-- this element content is restricted to kana and related
        	characters such as chouon and kurikaeshi. Kana usage will be
        	consistent between the keb and reb elements; e.g. if the keb
        	contains katakana, so too will the reb.
        	-->
        <!ELEMENT re_nokanji (#PCDATA)>
        <!-- This element, which will usually have a null value, indicates
        	that the reb, while associated with the keb, cannot be regarded
        	as a true reading of the kanji. It is typically used for words
        	such as foreign place names, gairaigo which can be in kanji or
        	katakana, etc.
        	-->
        <!ELEMENT re_restr (#PCDATA)>
        <!-- This element is used to indicate when the reading only applies
        	to a subset of the keb elements in the entry. In its absence, all
        	readings apply to all kanji elements. The contents of this element 
        	must exactly match those of one of the keb elements.
        	-->
        <!ELEMENT re_inf (#PCDATA)>
        <!-- General coded information pertaining to the specific reading.
        	Typically it will be used to indicate some unusual aspect of 
        	the reading. -->
        <!ELEMENT re_pri (#PCDATA)>
        <!-- See the comment on ke_pri above. -->
        <!--                                                                   -->
        <!ELEMENT sense (stagk*, stagr*, pos*, xref*, ant*, field*, misc*, s_inf*, lsource*, dial*, gloss*)>
        <!-- The sense element will record the translational equivalent
        	of the Japanese word, plus other related information. Where there
        	are several distinctly different meanings of the word, multiple
        	sense elements will be employed.
        	-->
        <!ELEMENT stagk (#PCDATA)>
        <!ELEMENT stagr (#PCDATA)>
        <!-- These elements, if present, indicate that the sense is restricted
        	to the lexeme represented by the keb and/or reb. -->
        <!ELEMENT xref (#PCDATA)*>
        <!-- This element is used to indicate a cross-reference to another
        	entry with a similar or related meaning or sense. The content of
        	this element is typically a keb or reb element in another entry. In some
        	cases a keb will be followed by a reb and/or a sense number to provide
        	a precise target for the cross-reference. Where this happens, a JIS
        	"centre-dot" (0x2126) is placed between the components of the 
        	cross-reference. The target keb or reb must not contain a centre-dot.
        	-->
        <!ELEMENT ant (#PCDATA)*>
        <!-- This element is used to indicate another entry which is an
        	antonym of the current entry/sense. The content of this element
        	must exactly match that of a keb or reb element in another entry.
        	-->
        <!ELEMENT pos (#PCDATA)>
        <!-- Part-of-speech information about the entry/sense. Should use 
        	appropriate entity codes. In general where there are multiple senses
        	in an entry, the part-of-speech of an earlier sense will apply to
        	later senses unless there is a new part-of-speech indicated.
        	-->
        <!ELEMENT field (#PCDATA)>
        <!-- Information about the field of application of the entry/sense. 
        	When absent, general application is implied. Entity coding for 
        	specific fields of application. -->
        <!ELEMENT misc (#PCDATA)>
        <!-- This element is used for other relevant information about 
        	the entry/sense. As with part-of-speech, information will usually
        	apply to several senses.
        	-->
        <!ELEMENT lsource (#PCDATA)>
        <!-- This element records the information about the source
        	language(s) of a loan-word/gairaigo. If the source language is other 
        	than English, the language is indicated by the xml:lang attribute.
        	The element value (if any) is the source word or phrase.
        	-->
        <!ATTLIST lsource xml:lang CDATA "eng">
        <!-- The xml:lang attribute defines the language(s) from which
        	a loanword is drawn.  It will be coded using the three-letter language 
        	code from the ISO 639-2 standard. When absent, the value "eng" (i.e. 
        	English) is the default value. The bibliographic (B) codes are used. -->
        <!ATTLIST lsource ls_type CDATA #IMPLIED>
        <!-- The ls_type attribute indicates whether the lsource element
        	fully or partially describes the source word or phrase of the
        	loanword. If absent, it will have the implied value of "full".
        	Otherwise it will contain "part".  -->
        <!ATTLIST lsource ls_wasei CDATA #IMPLIED>
        <!-- The ls_wasei attribute indicates that the Japanese word
        	has been constructed from words in the source language, and
        	not from an actual phrase in that language. Most commonly used to
        	indicate "waseieigo". -->
        <!ELEMENT dial (#PCDATA)>
        <!-- For words specifically associated with regional dialects in
        	Japanese, the entity code for that dialect, e.g. ksb for Kansaiben.
        	-->
        <!ELEMENT gloss (#PCDATA | pri)*>
        <!-- Within each sense will be one or more "glosses", i.e. 
        	target-language words or phrases which are equivalents to the 
        	Japanese word. This element would normally be present, however it 
        	may be omitted in entries which are purely for a cross-reference.
        	-->
        <!ATTLIST gloss xml:lang CDATA "eng">
        <!-- The xml:lang attribute defines the target language of the
        	gloss. It will be coded using the three-letter language code from
        	the ISO 639 standard. When absent, the value "eng" (i.e. English)
        	is the default value. -->
        <!ATTLIST gloss g_gend CDATA #IMPLIED>
        <!-- The g_gend attribute defines the gender of the gloss (typically
        	a noun in the target language. When absent, the gender is either
        	not relevant or has yet to be provided.
        	-->
        <!ATTLIST gloss g_type CDATA #IMPLIED>
        <!-- The g_type attribute specifies that the gloss is of a particular
        	type, e.g. "lit" (literal), "fig" (figurative), "expl" (explanation).
        	-->
        <!ELEMENT pri (#PCDATA)>
        <!-- These elements highlight particular target-language words which 
        	are strongly associated with the Japanese word. The purpose is to 
        	establish a set of target-language words which can effectively be 
        	used as head-words in a reverse target-language/Japanese relationship.
        	-->
        <!ELEMENT s_inf (#PCDATA)>
        <!-- The sense-information elements provided for additional
        	information to be recorded about a sense. Typical usage would
        	be to indicate such things as level of currency of a sense, the
        	regional variations, etc.
        	-->
        <!-- The following entity codes are used for common elements within the
        various information fields.
        -->
        <!ENTITY MA "martial arts term">
        <!ENTITY X "rude or X-rated term (not displayed in educational software)">
        <!ENTITY abbr "abbreviation">
        <!ENTITY adj-i "adjective (keiyoushi)">
        <!ENTITY adj-ix "adjective (keiyoushi) - yoi/ii class">
        <!ENTITY adj-na "adjectival nouns or quasi-adjectives (keiyodoshi)">
        <!ENTITY adj-no "nouns which may take the genitive case particle `no'">
        <!ENTITY adj-pn "pre-noun adjectival (rentaishi)">
        <!ENTITY adj-t "`taru' adjective">
        <!ENTITY adj-f "noun or verb acting prenominally">
        <!ENTITY adv "adverb (fukushi)">
        <!ENTITY adv-to "adverb taking the `to' particle">
        <!ENTITY arch "archaism">
        <!ENTITY ateji "ateji (phonetic) reading">
        <!ENTITY aux "auxiliary">
        <!ENTITY aux-v "auxiliary verb">
        <!ENTITY aux-adj "auxiliary adjective">
        <!ENTITY Buddh "Buddhist term">
        <!ENTITY chem "chemistry term">
        <!ENTITY chn "children's language">
        <!ENTITY col "colloquialism">
        <!ENTITY comp "computer terminology">
        <!ENTITY conj "conjunction">
        <!ENTITY cop-da "copula">
        <!ENTITY ctr "counter">
        <!ENTITY derog "derogatory">
        <!ENTITY eK "exclusively kanji">
        <!ENTITY ek "exclusively kana">
        <!ENTITY exp "expressions (phrases, clauses, etc.)">
        <!ENTITY fam "familiar language">
        <!ENTITY fem "female term or language">
        <!ENTITY food "food term">
        <!ENTITY geom "geometry term">
        <!ENTITY gikun "gikun (meaning as reading) or jukujikun (special kanji reading)">
        <!ENTITY hon "honorific or respectful (sonkeigo) language">
        <!ENTITY hum "humble (kenjougo) language">
        <!ENTITY iK "word containing irregular kanji usage">
        <!ENTITY id "idiomatic expression">
        <!ENTITY ik "word containing irregular kana usage">
        <!ENTITY int "interjection (kandoushi)">
        <!ENTITY io "irregular okurigana usage">
        <!ENTITY iv "irregular verb">
        <!ENTITY ling "linguistics terminology">
        <!ENTITY m-sl "manga slang">
        <!ENTITY male "male term or language">
        <!ENTITY male-sl "male slang">
        <!ENTITY math "mathematics">
        <!ENTITY mil "military">
        <!ENTITY n "noun (common) (futsuumeishi)">
        <!ENTITY n-adv "adverbial noun (fukushitekimeishi)">
        <!ENTITY n-suf "noun, used as a suffix">
        <!ENTITY n-pref "noun, used as a prefix">
        <!ENTITY n-t "noun (temporal) (jisoumeishi)">
        <!ENTITY num "numeric">
        <!ENTITY oK "word containing out-dated kanji">
        <!ENTITY obs "obsolete term">
        <!ENTITY obsc "obscure term">
        <!ENTITY ok "out-dated or obsolete kana usage">
        <!ENTITY oik "old or irregular kana form">
        <!ENTITY on-mim "onomatopoeic or mimetic word">
        <!ENTITY pn "pronoun">
        <!ENTITY poet "poetical term">
        <!ENTITY pol "polite (teineigo) language">
        <!ENTITY pref "prefix">
        <!ENTITY proverb "proverb">
        <!ENTITY prt "particle">
        <!ENTITY physics "physics terminology">
        <!ENTITY quote "quotation">
        <!ENTITY rare "rare">
        <!ENTITY sens "sensitive">
        <!ENTITY sl "slang">
        <!ENTITY suf "suffix">
        <!ENTITY uK "word usually written using kanji alone">
        <!ENTITY uk "word usually written using kana alone">
        <!ENTITY unc "unclassified">
        <!ENTITY yoji "yojijukugo">
        <!ENTITY v1 "Ichidan verb">
        <!ENTITY v1-s "Ichidan verb - kureru special class">
        <!ENTITY v2a-s "Nidan verb with 'u' ending (archaic)">
        <!ENTITY v4h "Yodan verb with `hu/fu' ending (archaic)">
        <!ENTITY v4r "Yodan verb with `ru' ending (archaic)">
        <!ENTITY v5aru "Godan verb - -aru special class">
        <!ENTITY v5b "Godan verb with `bu' ending">
        <!ENTITY v5g "Godan verb with `gu' ending">
        <!ENTITY v5k "Godan verb with `ku' ending">
        <!ENTITY v5k-s "Godan verb - Iku/Yuku special class">
        <!ENTITY v5m "Godan verb with `mu' ending">
        <!ENTITY v5n "Godan verb with `nu' ending">
        <!ENTITY v5r "Godan verb with `ru' ending">
        <!ENTITY v5r-i "Godan verb with `ru' ending (irregular verb)">
        <!ENTITY v5s "Godan verb with `su' ending">
        <!ENTITY v5t "Godan verb with `tsu' ending">
        <!ENTITY v5u "Godan verb with `u' ending">
        <!ENTITY v5u-s "Godan verb with `u' ending (special class)">
        <!ENTITY v5uru "Godan verb - Uru old class verb (old form of Eru)">
        <!ENTITY vz "Ichidan verb - zuru verb (alternative form of -jiru verbs)">
        <!ENTITY vi "intransitive verb">
        <!ENTITY vk "Kuru verb - special class">
        <!ENTITY vn "irregular nu verb">
        <!ENTITY vr "irregular ru verb, plain form ends with -ri">
        <!ENTITY vs "noun or participle which takes the aux. verb suru">
        <!ENTITY vs-c "su verb - precursor to the modern suru">
        <!ENTITY vs-s "suru verb - special class">
        <!ENTITY vs-i "suru verb - included">
        <!ENTITY kyb "Kyoto-ben">
        <!ENTITY osb "Osaka-ben">
        <!ENTITY ksb "Kansai-ben">
        <!ENTITY ktb "Kantou-ben">
        <!ENTITY tsb "Tosa-ben">
        <!ENTITY thb "Touhoku-ben">
        <!ENTITY tsug "Tsugaru-ben">
        <!ENTITY kyu "Kyuushuu-ben">
        <!ENTITY rkb "Ryuukyuu-ben">
        <!ENTITY nab "Nagano-ben">
        <!ENTITY hob "Hokkaido-ben">
        <!ENTITY vt "transitive verb">
        <!ENTITY vulg "vulgar expression or word">
        <!ENTITY adj-kari "`kari' adjective (archaic)">
        <!ENTITY adj-ku "`ku' adjective (archaic)">
        <!ENTITY adj-shiku "`shiku' adjective (archaic)">
        <!ENTITY adj-nari "archaic/formal form of na-adjective">
        <!ENTITY n-pr "proper noun">
        <!ENTITY v-unspec "verb unspecified">
        <!ENTITY v4k "Yodan verb with `ku' ending (archaic)">
        <!ENTITY v4g "Yodan verb with `gu' ending (archaic)">
        <!ENTITY v4s "Yodan verb with `su' ending (archaic)">
        <!ENTITY v4t "Yodan verb with `tsu' ending (archaic)">
        <!ENTITY v4n "Yodan verb with `nu' ending (archaic)">
        <!ENTITY v4b "Yodan verb with `bu' ending (archaic)">
        <!ENTITY v4m "Yodan verb with `mu' ending (archaic)">
        <!ENTITY v2k-k "Nidan verb (upper class) with `ku' ending (archaic)">
        <!ENTITY v2g-k "Nidan verb (upper class) with `gu' ending (archaic)">
        <!ENTITY v2t-k "Nidan verb (upper class) with `tsu' ending (archaic)">
        <!ENTITY v2d-k "Nidan verb (upper class) with `dzu' ending (archaic)">
        <!ENTITY v2h-k "Nidan verb (upper class) with `hu/fu' ending (archaic)">
        <!ENTITY v2b-k "Nidan verb (upper class) with `bu' ending (archaic)">
        <!ENTITY v2m-k "Nidan verb (upper class) with `mu' ending (archaic)">
        <!ENTITY v2y-k "Nidan verb (upper class) with `yu' ending (archaic)">
        <!ENTITY v2r-k "Nidan verb (upper class) with `ru' ending (archaic)">
        <!ENTITY v2k-s "Nidan verb (lower class) with `ku' ending (archaic)">
        <!ENTITY v2g-s "Nidan verb (lower class) with `gu' ending (archaic)">
        <!ENTITY v2s-s "Nidan verb (lower class) with `su' ending (archaic)">
        <!ENTITY v2z-s "Nidan verb (lower class) with `zu' ending (archaic)">
        <!ENTITY v2t-s "Nidan verb (lower class) with `tsu' ending (archaic)">
        <!ENTITY v2d-s "Nidan verb (lower class) with `dzu' ending (archaic)">
        <!ENTITY v2n-s "Nidan verb (lower class) with `nu' ending (archaic)">
        <!ENTITY v2h-s "Nidan verb (lower class) with `hu/fu' ending (archaic)">
        <!ENTITY v2b-s "Nidan verb (lower class) with `bu' ending (archaic)">
        <!ENTITY v2m-s "Nidan verb (lower class) with `mu' ending (archaic)">
        <!ENTITY v2y-s "Nidan verb (lower class) with `yu' ending (archaic)">
        <!ENTITY v2r-s "Nidan verb (lower class) with `ru' ending (archaic)">
        <!ENTITY v2w-s "Nidan verb (lower class) with `u' ending and `we' conjugation (archaic)">
        <!ENTITY archit "architecture term">
        <!ENTITY astron "astronomy, etc. term">
        <!ENTITY baseb "baseball term">
        <!ENTITY biol "biology term">
        <!ENTITY bot "botany term">
        <!ENTITY bus "business term">
        <!ENTITY econ "economics term">
        <!ENTITY engr "engineering term">
        <!ENTITY finc "finance term">
        <!ENTITY geol "geology, etc. term">
        <!ENTITY law "law, etc. term">
        <!ENTITY mahj "mahjong term">
        <!ENTITY med "medicine, etc. term">
        <!ENTITY music "music term">
        <!ENTITY Shinto "Shinto term">
        <!ENTITY shogi "shogi term">
        <!ENTITY sports "sports term">
        <!ENTITY sumo "sumo term">
        <!ENTITY zool "zoology term">
        <!ENTITY joc "jocular, humorous term">
        <!ENTITY anat "anatomical term">
        ]>
        <!-- JMdict created: 2019-07-28 -->
        <JMdict>
            <!-- Test Duplicate entries -->
            <entry>
                <ent_seq>1165970</ent_seq>
                <k_ele>
                    <keb>一番</keb>
                    <ke_pri>ichi1</ke_pri>
                    <ke_pri>news1</ke_pri>
                    <ke_pri>nf01</ke_pri>
                </k_ele>
                <k_ele>
                    <keb>１番</keb>
                </k_ele>
                <r_ele>
                    <reb>いちばん</reb>
                    <re_pri>ichi1</re_pri>
                    <re_pri>news1</re_pri>
                    <re_pri>nf01</re_pri>
                </r_ele>
                <sense>
                    <pos>&n;</pos>
                    <pos>&adj-no;</pos>
                    <gloss>number one</gloss>
                    <gloss>first</gloss>
                    <gloss>first place</gloss>
                </sense>
                <sense>
                    <pos>&adv;</pos>
                    <gloss>best</gloss>
                    <gloss>most</gloss>
                </sense>
                <sense>
                    <pos>&n;</pos>
                    <gloss>game</gloss>
                    <gloss>round</gloss>
                    <gloss>bout</gloss>
                </sense>
                <sense>
                    <pos>&adv;</pos>
                    <xref>試しに</xref>
                    <gloss>as a test</gloss>
                    <gloss>as an experiment</gloss>
                    <gloss>by way of experiment</gloss>
                    <gloss>by way of trial</gloss>
                    <gloss>tentatively</gloss>
                </sense>
                <sense>
                    <pos>&n;</pos>
                    <gloss>song (e.g. in noh)</gloss>
                    <gloss>piece</gloss>
                </sense>
            </entry>
            <entry>
                <ent_seq>1165980</ent_seq>
                <k_ele>
                    <keb>一番</keb>
                </k_ele>
                <k_ele>
                    <keb>一つがい</keb>
                </k_ele>
                <k_ele>
                    <keb>１つがい</keb>
                </k_ele>
                <r_ele>
                    <reb>ひとつがい</reb>
                </r_ele>
                <sense>
                    <pos>&n;</pos>
                    <misc>&uk;</misc>
                    <gloss>pair</gloss>
                    <gloss>couple</gloss>
                    <gloss>brace</gloss>
                </sense>
            </entry>

            <!-- Test entry kanji ordering  -->
            <entry>
                <ent_seq>2103660</ent_seq>
                <k_ele>
                    <keb>一番上</keb>
                    <ke_pri>spec1</ke_pri>
                </k_ele>
                <r_ele>
                    <reb>いちばんうえ</reb>
                    <re_pri>spec1</re_pri>
                </r_ele>
                <sense>
                    <pos>&adj-no;</pos>
                    <gloss>topmost</gloss>
                </sense>
            </entry>

            <!-- Full dictionary version test (ignores correct languages) -->
            <entry>
                <ent_seq>2013900</ent_seq>
                <k_ele>
                    <keb>赤</keb>
                    <ke_pri>spec1</ke_pri>
                </k_ele>
                <k_ele>
                    <keb>紅</keb>
                </k_ele>
                <k_ele>
                    <keb>朱</keb>
                </k_ele>
                <k_ele>
                    <keb>緋</keb>
                    <ke_inf>&oK;</ke_inf>
                </k_ele>
                <r_ele>
                    <reb>あか</reb>
                    <re_pri>spec1</re_pri>
                </r_ele>
                <sense>
                    <pos>&n;</pos>
                    <gloss>red</gloss>
                    <gloss>crimson</gloss>
                    <gloss>scarlet</gloss>
                </sense>
                <sense>
                    <gloss>red-containing colour (e.g. brown, pink, orange)</gloss>
                </sense>
                <sense>
                    <misc>&col;</misc>
                    <s_inf>often written as アカ</s_inf>
                    <gloss>Red (i.e. communist)</gloss>
                </sense>
                <sense>
                    <xref>赤信号・1</xref>
                    <misc>&abbr;</misc>
                    <gloss>red light (traffic)</gloss>
                </sense>
                <sense>
                    <xref>赤字・1</xref>
                    <xref>赤字・2</xref>
                    <misc>&abbr;</misc>
                    <gloss>red ink (i.e. in finance or proof-reading)</gloss>
                    <gloss>(in) the red</gloss>
                </sense>
                <sense>
                    <pos>&adj-no;</pos>
                    <xref>赤の他人</xref>
                    <gloss>complete</gloss>
                    <gloss>total</gloss>
                    <gloss>perfect</gloss>
                    <gloss>obvious</gloss>
                </sense>
                <sense>
                    <stagk>赤</stagk>
                    <pos>&n;</pos>
                    <xref>銅・あかがね・1</xref>
                    <misc>&abbr;</misc>
                    <gloss>copper</gloss>
                </sense>
                <sense>
                    <gloss xml:lang="dut">1. rood {= de kleur █}</gloss>
                    <gloss xml:lang="dut">2. roodbruin {= de kleur █}</gloss>
                    <gloss xml:lang="dut">3. {hofdamesjargon} azuki</gloss>
                    <gloss xml:lang="dut">adukiboon</gloss>
                    <gloss xml:lang="dut">4. baby</gloss>
                    <gloss xml:lang="dut">kindje</gloss>
                    <gloss xml:lang="dut">5. rode rijst</gloss>
                    <gloss xml:lang="dut">6. roodkoper</gloss>
                    <gloss xml:lang="dut">7. sake</gloss>
                    <gloss xml:lang="dut">8. {cul.} roodbruine miso</gloss>
                    <gloss xml:lang="dut">9. tekort</gloss>
                    <gloss xml:lang="dut">deficit</gloss>
                    <gloss xml:lang="dut">rode cijfers</gloss>
                    <gloss xml:lang="dut">roodstand</gloss>
                    <gloss xml:lang="dut">rood</gloss>
                    <gloss xml:lang="dut">10. laatste tram</gloss>
                    <gloss xml:lang="dut">laatste trein</gloss>
                    <gloss xml:lang="dut">11. laatste bus</gloss>
                    <gloss xml:lang="dut">12. rood licht</gloss>
                    <gloss xml:lang="dut">rood verkeerslicht</gloss>
                    <gloss xml:lang="dut">stoplicht</gloss>
                    <gloss xml:lang="dut">13. {sportt.} rode ploeg</gloss>
                    <gloss xml:lang="dut">14. {pol.} rood</gloss>
                    <gloss xml:lang="dut"> {i.h.b.} een rooie</gloss>
                    <gloss xml:lang="dut">15. {kaartsp.} twaalf rode kaarten in het mekuri-kaartspel</gloss>
                    <gloss xml:lang="dut">16. {kaartsp.} aka {= naam van elk van de drie vijfpuntenkaarten in het hanafuda-kaartspel</gloss>
                    <gloss xml:lang="dut">voorgesteld door een rode papierstrook over een patroon van resp. pijnboomen</gloss>
                    <gloss xml:lang="dut">pruimenbomen en sierkersen}</gloss>
                    <gloss xml:lang="dut"> {meton.} stel van drie aka-kaarten</gloss>
                    <gloss xml:lang="dut">17. {Barg.} brand</gloss>
                    <gloss xml:lang="dut">vuur</gloss>
                    <gloss xml:lang="dut"> {i.h.b.} vuurtje</gloss>
                    <gloss xml:lang="dut">lucifer</gloss>
                    <gloss xml:lang="dut">18. {Barg.} inbraak na het openbranden van het slot</gloss>
                    <gloss xml:lang="dut">19. {Barg.} bloed</gloss>
                    <gloss xml:lang="dut"> {i.h.b.} menstruatie</gloss>
                    <gloss xml:lang="dut"> {vulg.} de rooie loop</gloss>
                    <gloss xml:lang="dut">20. {Barg.} diefstal van metaalgeld</gloss>
                    <gloss xml:lang="dut">21. {krantenjargon} kosteloze advertentie</gloss>
                    <gloss xml:lang="dut">gratis krant</gloss>
                    <gloss xml:lang="dut">22. volkomen …</gloss>
                    <gloss xml:lang="dut">op-en-top …</gloss>
                    <gloss xml:lang="dut">geheel en al …</gloss>
                    <gloss xml:lang="dut">volkomen …</gloss>
                    <gloss xml:lang="dut">op-en-top …</gloss>
                    <gloss xml:lang="dut">geheel en al …</gloss>
                    <gloss xml:lang="dut">volslagen …</gloss>
                </sense>
                <sense>
                    <gloss xml:lang="fre">rouge</gloss>
                    <gloss xml:lang="fre">pourpre</gloss>
                    <gloss xml:lang="fre">écarlate</gloss>
                </sense>
                <sense>
                    <gloss xml:lang="fre">Rouge (communiste)</gloss>
                </sense>
                <sense>
                    <gloss xml:lang="fre">feu rouge</gloss>
                </sense>
                <sense>
                    <gloss xml:lang="fre">cuivre</gloss>
                </sense>
                <sense>
                    <gloss xml:lang="ger">rot</gloss>
                    <gloss xml:lang="ger">Rot</gloss>
                    <gloss xml:lang="ger">rote Farbe</gloss>
                </sense>
                <sense>
                    <gloss xml:lang="ger">Kommunist</gloss>
                    <gloss xml:lang="ger">Roter</gloss>
                </sense>
                <sense>
                    <gloss xml:lang="ger">letzter</gloss>
                </sense>
                <sense>
                    <gloss xml:lang="ger">Korrekturen</gloss>
                </sense>
                <sense>
                    <gloss xml:lang="ger">vollkommen (nackt, fremd etc.)</gloss>
                    <gloss xml:lang="ger">rote Färbung</gloss>
                    <gloss xml:lang="ger">Röte</gloss>
                </sense>
                <sense>
                    <gloss xml:lang="hun">bolsevik</gloss>
                    <gloss xml:lang="hun">kommunista</gloss>
                    <gloss xml:lang="hun">piros</gloss>
                    <gloss xml:lang="hun">rőt</gloss>
                    <gloss xml:lang="hun">vörös</gloss>
                    <gloss xml:lang="hun">élénkvörös</gloss>
                    <gloss xml:lang="hun">skarlát</gloss>
                    <gloss xml:lang="hun">skarlátpiros</gloss>
                </sense>
                <sense>
                    <gloss xml:lang="rus">1) красное, красный цвет</gloss>
                    <gloss xml:lang="rus">2) (полит.) красный (напр. о коммунистах)</gloss>
                    <gloss xml:lang="rus">{～になる} стать красным</gloss>
                    <gloss xml:lang="rus">3) (см.) あかんぼ</gloss>
                </sense>
                <sense>
                    <gloss xml:lang="slv">rdeča</gloss>
                </sense>
                <sense>
                    <gloss xml:lang="spa">rojo</gloss>
                </sense>
            </entry>

            <!-- General small entry test -->
            <entry>
                <ent_seq>1524720</ent_seq>
                <k_ele>
                    <keb>毎日</keb>
                    <ke_pri>ichi1</ke_pri>
                    <ke_pri>news1</ke_pri>
                    <ke_pri>nf01</ke_pri>
                </k_ele>
                <r_ele>
                    <reb>まいにち</reb>
                    <re_pri>ichi1</re_pri>
                    <re_pri>news1</re_pri>
                    <re_pri>nf01</re_pri>
                </r_ele>
                <sense>
                    <pos>&n-adv;</pos>
                    <pos>&n-t;</pos>
                    <gloss>every day</gloss>
                </sense>
            </entry>

            <!-- Duplicate kanji test -->
            <entry>
                <ent_seq>1611370</ent_seq>
                <k_ele>
                    <keb>日日</keb>
                    <ke_pri>ichi1</ke_pri>
                    <ke_pri>news1</ke_pri>
                    <ke_pri>nf23</ke_pri>
                </k_ele>
                <k_ele>
                    <keb>日にち</keb>
                </k_ele>
                <r_ele>
                    <reb>ひにち</reb>
                    <re_pri>ichi1</re_pri>
                    <re_pri>news1</re_pri>
                    <re_pri>nf23</re_pri>
                </r_ele>
                <sense>
                    <pos>&n;</pos>
                    <gloss>the number of days</gloss>
                    <gloss>date</gloss>
                </sense>
            </entry>
        </JMdict>
    """.trimIndent()
}
