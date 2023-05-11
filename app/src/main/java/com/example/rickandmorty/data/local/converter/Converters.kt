package com.example.rickandmorty.data.local.converter

import androidx.room.TypeConverter
import com.example.rickandmorty.data.characters.detail.model.CharacterDetailLocationData
import com.example.rickandmorty.data.characters.detail.model.CharacterDetailOriginData
import com.example.rickandmorty.data.characters.list.model.SingleCharacterLocationData
import com.example.rickandmorty.data.characters.list.model.SingleCharacterOriginData
import com.example.rickandmorty.data.episodes.list.model.SingleEpisodeListData
import com.google.gson.Gson

class Converters {

    //Для объекта SingleCharacterOriginData
    @TypeConverter fun fromSingleCharacterOriginData(sourceToString: SingleCharacterOriginData?): String {
        return Gson().toJson(sourceToString)
    }

    @TypeConverter fun toSingleCharacterOriginData(stringToSource: String?): SingleCharacterOriginData {
        val gson = Gson()
        return gson.fromJson(stringToSource, SingleCharacterOriginData::class.java)
    }

    //Для объекта SingleCharacterLocationData
    @TypeConverter fun fromSingleCharacterLocationData(sourceToString: SingleCharacterLocationData?): String {
        return Gson().toJson(sourceToString)
    }

    @TypeConverter fun toSingleCharacterLocationData(stringToSource: String?): SingleCharacterLocationData {
        val gson = Gson()
        return gson.fromJson(stringToSource, SingleCharacterLocationData::class.java)
    }

    //Для списка строк
    @TypeConverter fun fromList(sourceToString: List<String>?): String {
        return Gson().toJson(sourceToString)
    }

    @TypeConverter fun toList(stringToSource: String?): List<String> {
        return stringToSource?.split(",") ?: emptyList()
    }

    @TypeConverter fun fromCharacterDetailOriginData(sourceToString: CharacterDetailOriginData?): String {
        return Gson().toJson(sourceToString)
    }

    @TypeConverter fun toCharacterDetailOriginData(stringToSource: String?): CharacterDetailOriginData {
        val gson = Gson()
        return gson.fromJson(stringToSource, CharacterDetailOriginData::class.java)
    }

    @TypeConverter fun fromCharacterDetailLocationData(sourceToString: CharacterDetailLocationData?): String {
        return Gson().toJson(sourceToString)
    }

    @TypeConverter fun toCharacterDetailLocationData(stringToSource: String?): CharacterDetailLocationData {
        val gson = Gson()
        return gson.fromJson(stringToSource, CharacterDetailLocationData::class.java)
    }

}