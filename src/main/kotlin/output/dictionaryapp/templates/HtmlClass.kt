package output.dictionaryapp.templates

object HtmlClass {
    val HEADER_READING = className("header_reading", "a")
    val SECTION_HEADING = className("section_heading", "b")
    val SUB_HEADING = className("sub_heading", "c")
    val BADGE = className("badge_pos", "d")
    val SENTENCE = className("sentence", "e")
    val DEFINITIONS = className("definitions", "f")
    val TRANSLATION_LINE = className("translation_line", "h")
    val TRANSLATION_BLOCK = className("translation_block", "i")
    val BADGE_BOX = className("badge_box", "j")
    val FIELD_INFORMATION = className("field", "k")
    val BADGE_MISC = className("badge_misc", "m")
    val PART_OF_SPEECH_MARKER = className("pos_marker", "n")
    val SENSE_INFORMATION = className("sense_information", "o")
    val MOUSEOVER_DISABLE = className("mouseover_disable", "p")
    val TRANSLATION = className("translation", "q")
    val CONTEXT = className("context", "r")

    private fun className(full: String, short: String) = when (Configuration.SIMPLIFY_TAG_CLASS_NAMES) {
        true -> short
        false -> full
    }
}
