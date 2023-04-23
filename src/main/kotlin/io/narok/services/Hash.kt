package io.narok.services

import java.security.MessageDigest
import kotlin.text.Charsets.UTF_8

object Hash {

    fun md5(str: String): ByteArray = MessageDigest.getInstance("MD5")
        .digest(str.toByteArray(UTF_8))

    fun ByteArray.toHex() = joinToString(separator = "") { byte -> "%02x".format(byte) }

}