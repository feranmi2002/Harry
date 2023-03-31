package com.faithdeveloper.harry.model

data class Wand(
    val wood:String,
    val core:String,
    val length:Float
){
    constructor():this("","",0f)
}
