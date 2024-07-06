package api
import io.github.cdimascio.dotenv.dotenv
object Config {
    private val dotenv = dotenv()

    val lang: String = dotenv["LANG"] ?: "en"
    val id: String  = dotenv["ID"] ?: "1"
}