/**
 * Returns the short form of a person's full name
 *
 * getMyShortName("Touhidul Islam") -> "TI"
 * getMyShortName("Touhidul") -> "TO"
 * getMyShortName("Touhid Apps Learn") -> "TAL"
 */
fun getMyShortName(fullName: String): String {

    val names = fullName
        .split(" ")
        .filter { it.isNotBlank() }

    return when {
        names.size == 1 && names.first().length <= 1 -> {
            names.first().first().toString().uppercase()
        }

        names.size == 1 && names.first().length > 1 -> {
            val name = names.first().uppercase()
            "${name.first()}${name[1].uppercase()}"
        }

        names.size == 2 || names.size >= 3 -> {
            val name = names.first().uppercase()
            val lname = names.last().uppercase()
            "${name.first()}${lname.first()}"
        }
        else -> {
            ""
        }
    }

}