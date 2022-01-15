package output.dictionaryapp.templates.auxiliary

object PropertyList {
    private fun languages(): String {
        val toLanguages = Configuration.LANGUAGE.map {
            """
                <dict>
                    <key>DCSDictionaryDescriptionLanguage</key>
                    <string>ja</string>
                    <key>DCSDictionaryIndexLanguage</key>
                    <string>${it.code2}</string>
                </dict>
            """
        }

        val fromLanguages = Configuration.LANGUAGE.map {
            """
                <dict>
                    <key>DCSDictionaryDescriptionLanguage</key>
                    <string>${it.code2}</string>
                    <key>DCSDictionaryIndexLanguage</key>
                    <string>ja</string>
                </dict>
            """
        }

        return toLanguages.joinToString() + "\n" + fromLanguages.joinToString()
    }

    private val PROPERTY_LIST = """
        <?xml version="1.0" encoding="UTF-8"?>
        <!DOCTYPE plist PUBLIC "-//Apple//DTD PLIST 1.0//EN" "http://www.apple.com/DTDs/PropertyList-1.0.dtd">
        <plist version="1.0">
        <dict>
            <key>DCSDictionaryLanguages</key>
            <array>
                ${languages()}
            </array>
            <key>Bundle display name</key>
            <string>${Configuration.DISPLAY_NAME}</string>
            <key>DCSDictionaryPrimaryLanguage</key>
            <string>ja</string>
            <key>DCSDictionaryCSS</key>
            <string>JapaneseDictionary.css</string>
            <key>CFBundleDevelopmentRegion</key>
            <string>English</string>
            <key>CFBundleIdentifier</key>
            <string>com.jackson-s.dictionary.ja-en.JapaneseDictionary</string>
            <key>CFBundleName</key>
            <string>Japanese - English</string>
            <key>CFBundleShortVersionString</key>
            <string>1.0</string>
            <key>DCSDictionaryManufacturerName</key>
            <string>Jackson S</string>
            <key>DCSDictionaryPrefsHTML</key>
            <string>JapaneseDictionary_prefs.html</string>
            <key>DCSDictionaryUseSystemAppearance</key>
            <true/>
            <key>DCSDictionaryNativeDisplayName</key>
            <string>${Configuration.DISPLAY_NAME}</string>
        </dict>
        </plist>
    """.trimIndent()

    fun propertList() = PROPERTY_LIST
}
