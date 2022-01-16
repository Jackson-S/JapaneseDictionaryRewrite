package output.dictionary.templates.auxiliary

import output.common.OutputFile
import output.dictionary.templates.HtmlClass.BADGE_BOX
import output.dictionary.templates.HtmlClass.CONTEXT
import output.dictionary.templates.HtmlClass.DEFINITIONS
import output.dictionary.templates.HtmlClass.FIELD_INFORMATION
import output.dictionary.templates.HtmlClass.HEADER_READING
import output.dictionary.templates.HtmlClass.PART_OF_SPEECH_MARKER
import output.dictionary.templates.HtmlClass.SENSE_INFORMATION
import output.dictionary.templates.HtmlClass.SUB_HEADING
import output.dictionary.templates.HtmlClass.TRANSLATION
import output.dictionary.templates.HtmlClass.TRANSLATION_BLOCK
import output.dictionary.templates.HtmlClass.TRANSLATION_LINE

object Stylesheet: OutputFile {
    private val BASE_STYLE = """
        @charset "UTF-8";
        
        h1	{
            font-size: 150%;
        }

        h3	{
            font-size: small;
            font-weight: bold;
        }

        header > h1 {
            display: inline;
        }

        .$HEADER_READING::before {
            content: "【";
        }

        .$HEADER_READING::after {
            content: "】";
        }
        
        .$SUB_HEADING {
            background-color: #E0E0E0;
            padding: 10px;
            border-radius: 5px;
        }

        .$SUB_HEADING > h3, p {
            margin: 2px 0;
        }
        
        .$DEFINITIONS {
            border: 1px solid lightgrey;
            margin: 10px 0;
            border-radius: 5px;
            padding: 10px;
        }
        
        .$TRANSLATION_LINE {
            display: flex;
            flex-direction: column;
            justify-content: flex-start;
            margin: 10px;
            padding-bottom: 5px;
            border-bottom: dotted 1.5px lightgray;
        }
        
        .$TRANSLATION_BLOCK {
            width: auto;
            display: flex;
            flex-flow: wrap;
        }
        
        .$TRANSLATION_BLOCK > p {
            margin-right: 4px;
        }
        
        .$TRANSLATION_BLOCK > p::after {
            content: ",";
        }
        
        .$TRANSLATION_BLOCK > p:last-child::after {
            display: none;
        }
        
        .$PART_OF_SPEECH_MARKER {
            width: fit-content;
            color: gray;
            font-weight: lighter;
            font-size: smaller;
            font-style: italic;
        }
        
        .$FIELD_INFORMATION::after {
            content: ":" !important;
        }
        
        .$PART_OF_SPEECH_MARKER::after {
            content: ")" !important;
        }
        
        .$PART_OF_SPEECH_MARKER::before {
            content: "(";
        }
        
        .$BADGE_BOX {
            display: inline-flex;
            width: auto;
        }
        
        .$SENSE_INFORMATION {
            display: flex;
            flex-direction: column;
            grid-column-start: 2;
            grid-column-end: 4;
        }
        
        .$SENSE_INFORMATION > p {
            width: auto;
        }

        .$TRANSLATION {
            display: flex;
            gap: 10pt;
            margin-right: 10px;
        }
        
        .$CONTEXT {
            display: flex;
            flex-wrap: wrap;
        }
        
        .$CONTEXT > p {
            margin-right: 3pt;
        }
        
        .$CONTEXT::before {
            content: "(";
        }
        
        .$CONTEXT > p:last-child {
            margin-right: 0;
        }
        
        .$CONTEXT::after {
            content: ")";
        }
        
        .$CONTEXT > p::after {
            content: ",";
        }
        
        .$CONTEXT > p:last-child::after {
            content: "";
        }
    """.trimIndent()

    val DARK_MODE = """
        @media (prefers-color-scheme: dark)
        {
            .$SUB_HEADING {
                background-color: #464646;
            }
            
            .$PART_OF_SPEECH_MARKER {
                color: lightgray;
            }
        }
    """.trimIndent()

    val POPUP_STYLE = """
         html.apple_client-panel body {
             margin-top: 0em;
         }

        html.apple_client-panel * {
            font-family: sans-serif;
        }

        html.apple_client-panel ol {
            list-style-type: none;
            padding: 0;
        }

        /* Hide subheader, sentences and subheadings to reduce onscreen elements */
        html.apple_client-panel .$SUB_HEADING,
        html.apple_client-panel $SENSE_INFORMATION,
        html.apple_client-panel h3,
        html.apple_client-panel details {
            display: none;
        }

        /* Set the title font to be small */
        html.apple_client-panel header > h1 {
            font-size: small;
            margin: 0;
        }

        /* Remove background colouring and replace with a border element */
        html.apple_client-panel .$DEFINITIONS,
        html.apple_client-panel .$TRANSLATION_LINE {
            border: none;
            padding: 0;
            margin: 0;
        }

        /* Reduce spacing in-between definitions in translation text */
        html.apple_client-panel .$TRANSLATION_LINE > p {
          margin-right: 2px;
        }
    """.trimIndent()

    val SPOTLIGHT_STYLE = """
        /* Spotlight Style 
         * The apple_client-spotlight class is applied using Javascript on the 
         * entry page, as there is not normally a way of differentiating between 
         * them using only CSS easily.
         */
        html.apple_client-spotlight * {
            font-family: sans-serif;
        }

        /* Remove the margin from the header */
        html.apple_client-spotlight header > h1 {
            margin: 0;
        }

        /* Hide the subheader information */
        html.apple_client-spotlight .$SUB_HEADING > * {
            display: none;
        }
    """.trimIndent()

    override val fileName = "JapaneseDictionary.css"
    override val subdirectory: String? = null
    override fun data() = listOf(BASE_STYLE, DARK_MODE, POPUP_STYLE, SPOTLIGHT_STYLE).joinToString("\n\n")
}
