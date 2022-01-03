package output.web

object Constants {
    val SCRIPT = """
            var html=document.getElementsByTagName("html")[0];
            var classes=html.className.split(" ");
            if(classes.indexOf("apple_client-application")!=-1) {
            if(classes.indexOf("apple_display-separateview")==-1) {
            html.classList.add("apple_client-spotlight")}}
        """.trimIndent().replace("\n", "")
}