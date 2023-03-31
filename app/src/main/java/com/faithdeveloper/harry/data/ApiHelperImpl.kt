package com.faithdeveloper.harry.data

import com.faithdeveloper.harry.retrofit.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart

class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {
    override suspend fun getAllCharacters(): Flow<ApiResult> = flow {
        emit(ApiResult(Status.SUCCESS, apiService.getAllCharacters()))
    }.onStart {
        emit(ApiResult(Status.LOADING, emptyList()))
    }.catch {
        emit(ApiResult(Status.ERROR, emptyList()))
    }

    override suspend fun getCharactersByHouse(house: String): Flow<ApiResult> = flow {
        emit(ApiResult(Status.SUCCESS, apiService.getCharactersByHouse(house)))
    }.onStart {
        emit(ApiResult(Status.LOADING, emptyList()))
    }.catch {
        emit(ApiResult(Status.ERROR, emptyList()))
    }

    override suspend fun getCharactersByName(name: String): Flow<ApiResult> = flow {
        emit(
            ApiResult(Status.SUCCESS, apiService.getAllCharacters().filter {
                it.name.contains(name, true)
            })
        )
    }.onStart {
        emit(ApiResult(Status.LOADING, emptyList()))
    }.catch {
        emit(ApiResult(Status.ERROR, emptyList()))
    }
}