package com.stefanoq21.whatsappgemini.domain.di

import android.app.Application
import android.content.Context
import org.junit.Test
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.test.KoinTest
import org.koin.test.verify.verify

class MyModulesTest : KoinTest {

    @OptIn(KoinExperimentalAPI::class)
    @Test
    fun testMyModules() {
        myModules.verify(
            extraTypes = listOf(
                Application::class,
                Context::class,
            )
        )
    }
}