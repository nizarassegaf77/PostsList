package id.aruna.posts.dagger

import android.app.Application
import android.util.Log
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import dagger.Module
import dagger.Provides
import id.aruna.posts.database.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Singleton

/**
 * Created by Nizar Assegaf on 17,October,2020
 */

@Module
class DatabaseModule(application: Application) {

    private var libraryApplication = application
    private lateinit var libraryDatabase: AppDatabase

    @Singleton
    @Provides
    fun providesRoomDatabase(): AppDatabase {
        libraryDatabase = Room.databaseBuilder(libraryApplication, AppDatabase::class.java, "library_database")
            .fallbackToDestructiveMigration()
            .build()
        return libraryDatabase
    }

    @Singleton
    @Provides
    fun providesPostDAO(libraryDatabase: AppDatabase) = libraryDatabase.postsDao()


}