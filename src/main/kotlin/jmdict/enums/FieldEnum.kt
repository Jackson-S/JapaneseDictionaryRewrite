package jmdict.enums

enum class FieldEnum(val code: String, val description: String) {
    AGRICULTURE("agric", "agriculture"),
    ANATOMY("anat", "anatomy"),
    ARCHEOLOGY("archeol", "archeology"),
    ARCHITECTURE("archit", "architecture"),
    ART("art", "art, aesthetics"),
    ASTRONOMY("astron", "astronomy"),
    AUDIOVISUAL("audvid", "audiovisual"),
    AVIATION("aviat", "aviation"),
    BASEBALL("baseb", "baseball"),
    BIOCHEMISTRY("biochem", "biochemistry"),
    BIOLOGY("biol", "biology"),
    BOTANY("bot", "botany"),
    BUDDHIST("Buddh", "Buddhism"),
    BUSINESS("bus", "business"),
    CHEMISTRY("chem", "chemistry"),
    CHRISTIANITY("Christn", "Christianity"),
    CLOTHING("cloth", "clothing"),
    COMPUTING("comp", "computing"),
    CRYSTALLOGTAPHY("cryst", "crystallography"),
    ECOLOGY("ecol", "ecology"),
    ECONOMICS("econ", "economics"),
    ELECTRICITY("elec", "electricity, elec. eng."),
    ELECTRONICS("electr", "electronics"),
    EMBRYOLOGY("embryo", "embryology"),
    ENGINEERING("engr", "engineering"),
    ENTOMOLOGY("ent", "entomology"),
    FINANCE("finc", "finance"),
    FISH("fish", "fishing"),
    FOOD("food", "food, cooking"),
    GARDENING("gardn", "gardening, horticulture"),
    GENETICS("genet", "genetics"),
    GEOGRAPHY("geogr", "geography"),
    GEOLOGY("geol", "geology"),
    GEOMETRY("geom", "geometry"),
    GO("go", "go (game)"),
    GOLF("golf", "golf"),
    GRAMMAR("gramm", "grammar"),
    GREEK_MYTHOLOGY("grmyth", "Greek mythology"),
    HANAFUDA("hanaf", "hanafuda"),
    HORSE_RACING("horse", "horse racing"),
    LAW("law", "law"),
    LINGUISTICS("ling", "linguistics"),
    LOGIC("logic", "logic"),
    MARTIAL_ARTS("MA", "martial arts"),
    MAHJONG("mahj", "mahjong"),
    MATHEMATICS("math", "mathematics"),
    MECHANICAL_ENGINEERING("mech", "mechanical engineering"),
    MEDICINE("med", "medicine"),
    METEOROLOGY("met", "meteorology"),
    MILITARY("mil", "military"),
    MUSIC("music", "music"),
    ORNITHOLOGY("ornith", "ornithology"),
    PALEONTOLOGY("paleo", "paleontology"),
    PATHOLOGY("pathol", "pathology"),
    PHARMACY("pharm", "pharmacy"),
    PHILOSOPHY("phil", "philosophy"),
    PHOTOGRAPHY("photo", "photography"),
    PHYSICS("physics", "physics"),
    PHYSIOLOGY("physiol", "physiology"),
    PRINTING("print", "printing"),
    PSYCHIATRY("psy", "psychiatry"),
    PSYCHOLOGY("psych", "psychology"),
    RAILWAY("rail", "railway"),
    SHINTO("Shinto", "Shinto"),
    SHOGI("shogi", "shogi"),
    SPORTS("sports", "sports"),
    STATISTICS("stat", "statistics"),
    SUMO("sumo", "sumo"),
    TELECOMMUNICATIONS("telec", "telecommunications"),
    TRADEMARK("tradem", "trademark"),
    VIDEO_GAMES("vidg", "video games"),
    ZOOLOGY("zool", "zoology");

    companion object {
        fun from(value: String) = try {
            values().first { it.code == value || it.description == value }
        } catch (e: NoSuchElementException) {
            throw NoSuchElementException(e.message + " $value", e.cause)
        }
    }
}
