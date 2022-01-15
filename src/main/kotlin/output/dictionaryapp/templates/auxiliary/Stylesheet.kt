package output.dictionaryapp.templates.auxiliary

import output.dictionaryapp.templates.HtmlClass.BADGE_BOX
import output.dictionaryapp.templates.HtmlClass.CONTEXT
import output.dictionaryapp.templates.HtmlClass.DEFINITIONS
import output.dictionaryapp.templates.HtmlClass.FIELD_INFORMATION
import output.dictionaryapp.templates.HtmlClass.HEADER_READING
import output.dictionaryapp.templates.HtmlClass.PART_OF_SPEECH_MARKER
import output.dictionaryapp.templates.HtmlClass.SENSE_INFORMATION
import output.dictionaryapp.templates.HtmlClass.SUB_HEADING
import output.dictionaryapp.templates.HtmlClass.TRANSLATION
import output.dictionaryapp.templates.HtmlClass.TRANSLATION_BLOCK
import output.dictionaryapp.templates.HtmlClass.TRANSLATION_LINE

object Stylesheet {
    private val STYLESHEET = """
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

    val STYLESHEET_POPUP = """
        /* Popup styles
         * All prefaced with html.apple_client-panel
         * These styles apply to the popup definition box that appears
         * on a three finger tap or force touch on a word.
         *
         * They are all prefaced with html.apple_client-panel.
         */
        /* Hide subheader and badges to reduce onscreen elements */
        html.apple_client-panel * {
          font-family: sans-serif;
        }

        html.apple_client-panel #sub_header,
        html.apple_client-panel #stroke_order,
        html.apple_client-panel .badge,
        html.apple_client-panel .section_heading,
        html.apple_client-panel .translation_info {
          display: none;
        }

        /* Set the title font to be small */
        html.apple_client-panel #header h1 {
          font-size: small;
          margin: 0;
        }

        /* Remove background colouring and replace with a border element */
        html.apple_client-panel #definitions,
        html.apple_client-panel #definitions > article,
        html.apple_client-panel #english_translations, 
        html.apple_client-panel #english_translations > article {
          border: none;
          padding: 0;
          margin: 0;
        }

        /* Reduce spacing inbetween definitions in translation text */
        html.apple_client-panel #definitions .translation_line > p, 
        html.apple_client-panel #english_translations .translation_line > p {
          margin-right: 2px;
        }

        /* Reduce the size of the definition numbers */
        html.apple_client-panel #definitions .number, 
        html.apple_client-panel #english_translations .number {
          font-size: 10px;
        }
        /* End popup style */

        /* Spotlight Style 
         * The apple_client-spotlight class is applied using Javascript on the 
         * entry page, as there is not normally a way of differentiating between 
         * them using only CSS easily.
         */
        html.apple_client-spotlight * {
          font-family: sans-serif;
        }

        /* Remove the margin from the header */
        html.apple_client-spotlight #header h1 {
          margin: 0;
        }

        /* Hide the subheader information */
        html.apple_client-spotlight #sub_header > *,
        html.apple_client-spotlight #stroke_order {
          display: none;
        }

        html.apple_client-spotlight #sub_header > .reading {
          display: block;
        }
    """.trimIndent()

    fun stylesheet() = STYLESHEET + STYLESHEET_POPUP
}
