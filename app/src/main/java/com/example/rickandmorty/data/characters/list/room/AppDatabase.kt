package com.example.rickandmorty.data.characters.list.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.rickandmorty.data.characters.list.local.CharacterListDao
import com.example.rickandmorty.data.characters.list.local.converter.ConverterMapper
import com.example.rickandmorty.data.characters.list.local.model.CharacterListEntity
import com.example.rickandmorty.data.characters.list.local.model.CharacterLocationEntity
import com.example.rickandmorty.data.characters.list.local.model.CharacterOriginEntity

@Database(
    entities = [
        CharacterListEntity::class,
        CharacterLocationEntity::class,
        CharacterOriginEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(value = [ConverterMapper::class])
abstract class AppDatabase : RoomDatabase() {

    abstract fun characterListDao(): CharacterListDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}