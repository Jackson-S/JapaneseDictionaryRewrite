package dataabstraction

import java.nio.charset.Charset
import kotlin.io.path.Path

/**
 * Returns empty responses when called upon
 */
class NullIOInterface : IOInterface {
    override fun readContents(encoding: Charset) = ""

    override fun write(data: String, encoding: Charset) {  }

    override fun path() = Path("")
}
