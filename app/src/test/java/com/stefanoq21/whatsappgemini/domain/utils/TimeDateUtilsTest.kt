package com.stefanoq21.whatsappgemini.domain.utils

import com.stefanoq21.whatsappgemini.domain.utils.TimeDateUtils.toMessageBubbleString
import org.junit.Test
import org.junit.Assert.assertEquals
import java.util.Calendar

class TimeDateUtilsTest {

    // toMessageBubbleString

    @Test
    fun `toMessageBubbleString null input`() {
        // Verify that the function returns an empty string when the input is null.
        val input:Long? = null
        val result = input.toMessageBubbleString()
        assertEquals("", result)
    }

    @Test
    fun `toMessageBubbleString valid input`() {
        // Verify that the function returns the correctly formatted time string "hh:mm" for a valid timestamp.
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, 10)
        calendar.set(Calendar.MINUTE, 30)
        val input = calendar.timeInMillis
        assertEquals("10:30", input.toMessageBubbleString())
    }
}