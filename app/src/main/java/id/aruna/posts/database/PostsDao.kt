package id.aruna.posts.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import id.aruna.posts.mvvm.model.PostsModel

@Dao
interface PostsDao {
    @Query("SELECT * FROM PostsModel")
    suspend fun getAll(): MutableList<PostsModel>

    @Query("SELECT * FROM PostsModel WHERE title LIKE '%' || :args0 || '%'")
    suspend fun getByTitle(args0:String): MutableList<PostsModel>

    @Query("DELETE FROM PostsModel")
    suspend fun deleteAll()

    @Insert
    suspend fun insertAll(vararg postsModel: PostsModel)
}