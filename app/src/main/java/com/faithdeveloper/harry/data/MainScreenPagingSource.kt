package com.faithdeveloper.harry.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.faithdeveloper.harry.model.Characters

class MainScreenPagingSource(private val apiHelper: ApiHelper) : PagingSource<Int, Characters>() {

    private val pageLimiter = 39

    private var apiResponse: List<Characters> = listOf()

    override fun getRefreshKey(state: PagingState<Int, Characters>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Characters> {
        return try {
            if (apiResponse.isEmpty()) {
                if (params.key == 0) {
                    apiResponse = apiHelper.getAllCharacters()
                }
            }

            val result = if (params.key == 0) {
                when {
                    apiResponse.size > pageLimiter -> {
                        apiResponse.slice(IntRange(0, pageLimiter))
                    }
                    else -> apiResponse
                }
            } else {
                val start = params.key!!.times(params.loadSize)
                apiResponse.slice(
                    IntRange(
                        start,
                        start + if (apiResponse.size.minus(start) > pageLimiter) pageLimiter
                        else apiResponse.size.minus(start)
                    )
                )
            }

            val currentKey = params.key

            val nextKey = if (result.size < params.loadSize) {
                null
            } else if (params.key != null) {
                currentKey?.plus(1)
            } else {
                null
            }


            LoadResult.Page(
                data = result,
                nextKey = nextKey,
                prevKey = null
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }
}