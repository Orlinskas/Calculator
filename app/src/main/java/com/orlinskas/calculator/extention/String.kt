package ua.brander.meetingroom.extensions

import java.security.MessageDigest

fun String.hashSHA256() =
    MessageDigest.getInstance("SHA-256").digest(this.toByteArray()).fold(
        "",
        { str, it -> str + "%02x".format(it) })

fun String.carefulSubstring(limit: Int): String {
    return if (this.length > limit) {
        this.substring(0, limit)
    } else {
        this
    }
}

fun String.firstToUpperCase() = this.substring(0, 1).toUpperCase() + this.substring(1)

