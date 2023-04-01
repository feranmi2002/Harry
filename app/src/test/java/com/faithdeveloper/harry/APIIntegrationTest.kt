package com.faithdeveloper.harry

import com.faithdeveloper.harry.retrofit.ApiService
import com.faithdeveloper.harry.retrofit.ServiceBuilder
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import mockwebserver3.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit

@OptIn(ExperimentalCoroutinesApi::class)
class APIIntegrationTest {
    private lateinit var mockWebServer: MockWebServer
    private lateinit var apiService: ApiService
    private lateinit var serviceBuillder:ServiceBuilder

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        mockWebServer.start()
        serviceBuillder = ServiceBuilder
        apiService = serviceBuillder.apiService
    }

    @After
    fun tearSown() {
        mockWebServer.shutdown()
    }


    @Test
    fun testGettingAllCharacters() = runTest {
        val response = apiService.getAllCharacters()
        assertThat(response).isNotEmpty()
    }

    @Test
    fun testGettingCharacterByHouse() = runTest {
        val response = apiService.getCharactersByHouse("gryffindor")
        assertThat(response).isNotEmpty()
    }

}