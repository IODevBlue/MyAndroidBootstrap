/**
 * Extension function to convert a [String] to a [UUID].
 *
 * **NOTE:** The source string would not be retrievable since UUID encryption is irreversible. Use [toBase64] for reversible encryption.
 * @return [UUID] representation of provided [String].
 */
fun String.toUUID(): String {
    return UUID.nameUUIDFromBytes(this.toByteArray()).toString().replace("-", "")
}

/**
 * Extension function to convert a [String] to a [Base64].
 *
 * Decrypt the source [String] using [fromBase64].
 * @return [UUID] representation of provided [String].
 */
fun String.toBase64(): String { //Working
    return Base64.encodeToString(this.toByteArray(Charset.defaultCharset()), Base64.DEFAULT)
}

/**
 * Extension function to convert a [Base64] string representation back to its original [String].
 * @return Original [String] representation of the provided [UUID] string.
 */
fun String.fromBase64(): String? { //Working
    return try {
        String(Base64.decode(this, Base64.DEFAULT), Charset.defaultCharset())
    } catch (e: IllegalArgumentException) {
        null // Invalid Base64 string
    }
}

/**
 * Checks if the provided string is likely Base64-encoded.
 * @return `true` if the string is likely Base64-encoded, `false` otherwise.
 */
fun String.isBase64Encoded(): Boolean {//Working
    return try {
        // Attempt to decode the input string using Base64 decoding
        Base64.decode(this, Base64.DEFAULT)
        true
    } catch (e: IllegalArgumentException) {
        // Catch the exception if decoding fails
        false
    }
}