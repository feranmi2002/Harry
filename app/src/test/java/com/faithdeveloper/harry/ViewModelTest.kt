package com.faithdeveloper.harry

import com.faithdeveloper.harry.data.ApiHelper
import com.faithdeveloper.harry.data.ApiHelperImpl
import com.faithdeveloper.harry.data.ApiResult
import com.faithdeveloper.harry.data.Status
import com.faithdeveloper.harry.retrofit.ServiceBuilder
import com.faithdeveloper.harry.viewmodel.MainScreenViewModel
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ViewModelTest {
    private lateinit var mainScreenViewModel: MainScreenViewModel
    private lateinit var apiHelper: ApiHelper

    @Before
    fun setup() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        apiHelper = ApiHelperImpl(ServiceBuilder.apiService)
        mainScreenViewModel = MainScreenViewModel(apiHelper)
    }

    @Test
    fun testGetAllCharacters() = runTest {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        val response = mainScreenViewModel.apiHelper.getAllCharacters()

//        check if Loading state is first passed to the ui
        assertThat(response.first()).isEqualTo(ApiResult(Status.LOADING, emptyList()))
//      check that the success state is finally passed to the ui
        assertThat(response.drop(1).first().type).isEqualTo(Status.SUCCESS)
    }

    @Test
    fun testNameSearch() = runTest {
        val testName = "harry"
        Dispatchers.setMain(UnconfinedTestDispatcher())
        val response = mainScreenViewModel.apiHelper.getCharactersByName(testName)

//        check that the filtering was correctly done
        assertThat(response.drop(1).first().data.first().name).contains(testName)
    }

    @Test
    fun testHouseSearch() = runTest {
        val testHouse = "Gryffindor"
        Dispatchers.setMain(UnconfinedTestDispatcher())
        val response = mainScreenViewModel.apiHelper.getCharactersByHouse(testHouse)
        assertThat(response.drop(1).first().data.first().house).contains(testHouse)
    }


}