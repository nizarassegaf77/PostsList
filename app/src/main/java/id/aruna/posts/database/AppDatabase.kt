package id.aruna.posts.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import id.aruna.posts.mvvm.model.PostsModel
import id.aruna.posts.webservice.WebService

@Database(entities = [PostsModel::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun postsDao(): PostsDao

    companion object {
        private var instance: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase {
            instance?.let { return it }
            instance = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "db-posts-aruna"
            ).build()
            return instance!!
        }
    }
}