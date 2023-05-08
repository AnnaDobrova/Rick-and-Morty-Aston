package com.example.rickandmorty.data.local.converter

import androidx.room.TypeConverter
import com.example.rickandmorty.data.characters.list.model.SingleCharacterLocationData
import com.example.rickandmorty.data.characters.list.model.SingleCharacterOriginData
import com.example.rickandmorty.data.local.characters.model.CharacterLocationEntity
import com.example.rickandmorty.data.local.characters.model.CharacterOriginEntity
import com.google.gson.Gson

class Converters {

    @TypeConverter fun fromSingleCharacterOriginData(sourceToString: SingleCharacterOriginData?): String {
        return Gson().toJson(sourceToString)
    }

    @TypeConverter fun toSingleCharacterOriginData(stringToSource: String?): SingleCharacterOriginData {
        val gson = Gson()
        return gson.fromJson(stringToSource, SingleCharacterOriginData::class.java)
    }

    @TypeConverter fun fromSingleCharacterLocationData(sourceToString: SingleCharacterLocationData?): String {
        return Gson().toJson(sourceToString)
    }

    @TypeConverter fun toSingleCharacterLocationData(stringToSource: String?): SingleCharacterLocationData {
        val gson = Gson()
        return gson.fromJson(stringToSource, SingleCharacterLocationData::class.java)
    }

    @TypeConverter fun fromList(sourceToString: List<String>?): String {
        return Gson().toJson(sourceToString)
    }

    @TypeConverter fun toList(stringToSource: String?): List<String> {
        return stringToSource?.split(",") ?: emptyList()
    }
}