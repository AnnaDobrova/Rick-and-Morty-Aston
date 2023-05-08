package com.example.rickandmorty.data.characters.list.local.converter

import androidx.room.TypeConverter
import com.example.rickandmorty.data.characters.list.local.model.CharacterLocationEntity
import com.example.rickandmorty.data.characters.list.local.model.CharacterOriginEntity
import com.google.gson.Gson

class ConverterMapper {

    @TypeConverter fun fromCharacterOriginEntity(sourceToString: CharacterOriginEntity?): String {
        return Gson().toJson(sourceToString)
    }

    @TypeConverter fun toCharacterOriginEntity(stringToSource: String?): CharacterOriginEntity {
        val gson = Gson()
        return gson.fromJson(stringToSource, CharacterOriginEntity::class.java)
    }

    @TypeConverter fun fromCharacterLocationEntity(sourceToString: CharacterLocationEntity?): String {
        return Gson().toJson(sourceToString)
    }

    @TypeConverter fun toCharacterLocationEntity(stringToSource: String?): CharacterLocationEntity {
        val gson = Gson()
        return gson.fromJson(stringToSource, CharacterLocationEntity::class.java)
    }

    @TypeConverter fun fromList(sourceToString: List<String>?): String {
        return Gson().toJson(sourceToString)
    }

    @TypeConverter fun toList(stringToSource: String?): List<String> {
        return stringToSource?.split(",") ?: emptyList()
    }
}