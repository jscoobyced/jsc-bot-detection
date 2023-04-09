package io.narok.services

import io.narok.services.Hash.toHex
import kotlin.test.Test
import kotlin.test.assertEquals

class HashTest {

    @Test
    fun `can create MD5`() {
        val input = "1234567890qwertyuiop"
        val expected = "dca21ba951f9e80597d1d47a0e2bf1b1e4e33d0ecb86741150b04f5d1b73cd92"
        val md5 = Hash.md5(input)
        val result = md5.toHex()

        assertEquals(expected, result)
    }
}