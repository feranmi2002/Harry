package com.faithdeveloper.harry.data

import com.faithdeveloper.harry.model.HarryCharacter

data class ApiResult(
    val type:Status,
    val data: List<HarryCharacter>
)

enum class Status{
    LOADING,
    ERROR,
    SUCCESS
}