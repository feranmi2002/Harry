package com.faithdeveloper.harry.ui.details_screen

import androidx.compose.runtime.Composable
import com.faithdeveloper.harry.model.HarryCharacter
import com.faithdeveloper.harry.util.Utils

@Composable
fun DetailsRoute(character:HarryCharacter, onClickBack:() -> Unit){
    DetailsScreen(character = mapCharacterToTitle(character) ) {
        onClickBack.invoke()
    }
}

fun mapCharacterToTitle(character:HarryCharacter) = mapOf(
    NAME to character.name,
    ACTOR to character.actor,
    ALIVE to character.alive.toString(),
    IMAGE to character.image,
    ANCESTRY to character.ancestry,
    SPECIES to character.species,
    GENDER to character.gender,
    ALTERNATE_ACTORS to Utils.formatListOfStrings(character.alternate_actors),
    HOUSE to character.house,
    DATE_OF_BIRTH to character.dateOfBirth,
    YEAR_OF_BIRTH to character.yearOfBirth.toString(),
    ALTERNATE_NAMES to Utils.formatListOfStrings(character.alternate_names),
    WIZARD to character.wizard.toString(),
    EYE_COLOUR to character.eyeColour,
    HAIR_COLOUR to character.hairColour,
    PATRONUS to character.patronus,
    HOGWARTS_STAFF to character.hogwartsStaff.toString(),
    HOGWARTS_STUDENT to character.hogwartsStudent.toString(),
    WAND_WOOD to character.wand.wood,
    WAND_CORE to character.wand.core,
    WAND_LENGTH to character.wand.length.toString()


)

const val NAME = "Name"
const val ACTOR = "Actor"
const val ALIVE = "Alive"
const val ID = "ID"
const val IMAGE = "Image Link"
const val ALTERNATE_NAMES ="Alternate Names"
const val SPECIES = "Species"
const val GENDER = "Gender"
const val HOUSE = "House"
const val DATE_OF_BIRTH = "Date of Birth"
const val YEAR_OF_BIRTH = "Year of Birth"
const val WIZARD = "Wizard"
const val ANCESTRY = "Ancestry"
const val EYE_COLOUR = "Eye Colour"
const val HAIR_COLOUR = "Hair Colour"
const val WAND_WOOD = "Wand Wood"
const val WAND_CORE = "Wand Core"
const val WAND_LENGTH = "Wand Length"
const val PATRONUS = "Patronus"
const val HOGWARTS_STUDENT = "Hogwart's Student"
const val HOGWARTS_STAFF = "Hogwart's Staff"
const val ALTERNATE_ACTORS = "Alternate Actors"