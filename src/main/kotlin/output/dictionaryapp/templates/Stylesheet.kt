package output.dictionaryapp.templates

object Stylesheet {
    val HEADER = if (Configuration.SIMPLIFY_TAG_CLASS_NAMES) "a" else "header"
    val PAGE_TITLE = if (Configuration.SIMPLIFY_TAG_CLASS_NAMES) "b" else "page_title"
    val PRIMARY_READING = if (Configuration.SIMPLIFY_TAG_CLASS_NAMES) "c" else "primary_reading"
    val READINGS = if (Configuration.SIMPLIFY_TAG_CLASS_NAMES) "d" else "readings"
    val READING = if (Configuration.SIMPLIFY_TAG_CLASS_NAMES) "e" else "reading"
    val SECTION_HEADING = if (Configuration.SIMPLIFY_TAG_CLASS_NAMES) "f" else "section_heading"
    val SUB_HEADER = if (Configuration.SIMPLIFY_TAG_CLASS_NAMES) "g" else "sub_heading"
    val BADGE = if (Configuration.SIMPLIFY_TAG_CLASS_NAMES) "h" else "badge"
    val SENTENCES = if (Configuration.SIMPLIFY_TAG_CLASS_NAMES) "i" else "sentences"
    val SENTENCE = if (Configuration.SIMPLIFY_TAG_CLASS_NAMES) "j" else "sentence"
    val DEFINITIONS = if (Configuration.SIMPLIFY_TAG_CLASS_NAMES) "k" else "definitions"
    val ENTRY_NUMBER = if (Configuration.SIMPLIFY_TAG_CLASS_NAMES) "l" else "entry_number"
    val TRANSLATION_LINE = if (Configuration.SIMPLIFY_TAG_CLASS_NAMES) "m" else "translation_line"
    val TRANSLATION_BLOCK = if (Configuration.SIMPLIFY_TAG_CLASS_NAMES) "n" else "translation_block"
    val BADGE_BOX = if (Configuration.SIMPLIFY_TAG_CLASS_NAMES) "o" else "badge_box"

    private val ELEMENT_STYLES = """
        d|entry {
        }

        h1	{
          font-size: 150%;
        }

        h3	{
          font-size: 100%;
        }

        span.column {
          display: block;
          border: solid 2px #c0c0c0;
          margin-left: 2em;
          margin-right: 2em;
          margin-top: 0.5em;
          margin-bottom: 0.5em;
          padding: 0.5em;
        }
        
        p {
          margin: 0px 0;
          width: min-content;
          white-space: nowrap;
        }

        summary {
          cursor: pointer !important;
        }

        summary:focus {
          outline: none;
        }
        
        h1 {
          display: inline-block;
        }
    """.trimIndent()

    private val CLASS_STYLES = """
        .$HEADER .$PRIMARY_READING::before {
          content: "【"
        }
        
        .$HEADER .$PRIMARY_READING::after {
          content: "】"
        }
        
        .$SECTION_HEADING {
          font-size: small;
          font-weight: normal;
          margin: 2px 0;
        }
        
        .$SUB_HEADER {
          display: flex;
          column-gap: 4%;
          align-content: flex-start;
          background-color: rgb(230,230,230);
          padding: 10px;
          border-radius: 5px;
        }
        
        .$READING {
          display: flex;
          column-gap: 5px;
        }
        
        .$READINGS .$BADGE {
          background: white;
        }
        
        .$READINGS {
          display: inline-flex;
          flex-direction: column;
        }
        
        .$BADGE {
          display: inline;
          padding: 2px 5px;
          margin: 0 2px;
          background: rgb(230,230,230);
          height: min-content;
          max-width: 10em;
          white-space: nowrap;
          border-radius: 5px;
          font-size: 80%;
        }
        
        .$DEFINITIONS {
          border: 1px solid lightgrey;
          margin: 10px 0;
          border-radius: 5px;
          padding: 10px    
        }
        
        .$TRANSLATION_LINE {
          display: flex;
          grid-gap: 10px;
          margin: 10px;
          padding-bottom: 5px;
          border-bottom: dotted 1.5px lightgray;
        }
        
        .$TRANSLATION_BLOCK {
          flex-grow: 1;
          display: flex;
          flex-flow: wrap;
        }
        
        .$BADGE_BOX {
          display: inline-flex;
        }
        
        .$ENTRY_NUMBER {
          display: inline;
          width: min-content;
        }
        
        .$ENTRY_NUMBER::after {
          content: "."
        }
    """.trimIndent()

    fun stylesheet() = ELEMENT_STYLES + CLASS_STYLES
}
