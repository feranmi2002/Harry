package com.faithdeveloper.harry.model

data class Characters(
    val id:String,
    val name:String,
    val names:List<String>,
    val species:String,
    val gender:String,
    val house:String,
    val dateOfBirth:String,
    val yearOfBirth:Long,
    val wizard:Boolean,
    val ancestry:String,
    val eyeColour:String,
    val hairColour:String,
    val wand:Wand,
    val patronus:String,
    val hogwartsStudent:Boolean,
    val hogwartsStaff:Boolean,
    val actor:String,
    val alternate_actors:List<String>,
    val alive:Boolean,
    val image:String,
)
