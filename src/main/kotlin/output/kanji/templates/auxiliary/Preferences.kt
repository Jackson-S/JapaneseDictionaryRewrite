package output.kanji.templates.auxiliary

import output.common.OutputFile

object Preferences: OutputFile {
    private val PREFERENCES = """
        <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
        <html xmlns="http://www.w3.org/1999/xhtml">
        	<head>
        		<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
        	</head>
        	<body>
        		<div id="copyright">
        			<p>
                        This package uses the 
                        <a href="http://www.csse.monash.edu.au/~jwb/edict.html">EDICT</a> and
                        <a href="http://www.csse.monash.edu.au/~jwb/kanjidic.html">KANJIDIC</a> dictionary files.
                        These files are the property of the 
                        <a href="http://www.edrdg.org/"> Electronic Dictionary Research and Development Group</a>, 
                        and are used in conformance with the Group's 
                        <a href="http://www.edrdg.org/edrdg/licence.html">licence</a>.
                    </p>
        			<p>
        			    The example sentences in this dictionary are from the Tanaka Corpus, which can be found 
                        <a href="https://dict.longdo.com/about/hintcontents/tanakacorpus.html">here</a>
        			</p>
        			<p>
        			    The kanji samples in this dictionary are provided by
        				<a href="https://kanjivg.tagaini.net/">KanjiVG</a>
        			    under the
        				<a href="https://creativecommons.org/licenses/by-sa/3.0/">Creative Common Attribution-ShareAlike 3.0 Unported</a>
        			    (CC BY-SA 3.0) license and are copyright © 2009-2018 Ulrich Apel.
        			</p>
        		</div>
        	</body>
        </html>
    """.trimIndent()

    override val fileName = "KanjiDictionary_prefs.html"
    override val subdirectory = "OtherResources"
    override fun data() = PREFERENCES
}
