package io.narok.services

import io.narok.services.Hash.toHex
import org.junit.Test
import kotlin.test.assertEquals

class HashTest {

    @Test
    fun `can create MD5`() {
        val input = "1234567890qwertyuiop"
        val expected = "dd9703027d674a59654ecba9623127c0"
        val md5 = Hash.md5(input)
        val result = md5.toHex()

        assertEquals(expected, result)
    }
}