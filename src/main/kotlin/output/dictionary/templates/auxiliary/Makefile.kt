package output.dictionary.templates.auxiliary

import dataabstraction.FileIOInterface
import output.common.OutputFile

object Makefile : OutputFile {
    private val MAKEFILE_OPTIONS = """
        DICT_NAME		=	"${Configuration.DICTIONARY_NAME}"
        DICT_SRC_PATH	=	JapaneseDictionary.xml
        CSS_PATH		=	JapaneseDictionary.css
        PLIST_PATH		=	JapaneseDictionary.plist
        DICT_BUILD_OPTS		=	-v 10.11 -t 3
        DICT_BUILD_TOOL_DIR	=	"${FileIOInterface(Configuration.DICTIONARY_TOOLKIT_DIRECTORY).path()}"
        DICT_BUILD_TOOL_BIN	=	"$(DICT_BUILD_TOOL_DIR)/bin"
        DICT_DEV_KIT_OBJ_DIR	=	./objects
        export	DICT_DEV_KIT_OBJ_DIR
        DESTINATION_FOLDER	=	~/Library/Dictionaries
        RM			=	/bin/rm
    """.trimIndent()

    private val MAKEFILE_ACTIONS = """
        all:
        	"$(DICT_BUILD_TOOL_BIN)/build_dict.sh" $(DICT_BUILD_OPTS) $(DICT_NAME) $(DICT_SRC_PATH) $(CSS_PATH) $(PLIST_PATH)
        	echo "Done."
    
        install:
        	echo "Installing into $(DESTINATION_FOLDER)".
        	mkdir -p $(DESTINATION_FOLDER)
        	ditto --noextattr --norsrc $(DICT_DEV_KIT_OBJ_DIR)/$(DICT_NAME).dictionary  $(DESTINATION_FOLDER)/$(DICT_NAME).dictionary
        	touch $(DESTINATION_FOLDER)
        	echo "Done."
        	echo "To test the new dictionary, try Dictionary.app."
    
        clean:
        	$(RM) -rf $(DICT_DEV_KIT_OBJ_DIR)
    """.trimIndent()

    override val fileName = "Makefile"
    override val subdirectory: String? = null
    override fun data() = MAKEFILE_OPTIONS + "\n\n" + MAKEFILE_ACTIONS + "\n"
}
