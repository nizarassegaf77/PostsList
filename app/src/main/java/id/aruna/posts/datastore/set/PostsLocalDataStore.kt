package id.aruna.posts.datastore.set

import android.util.Log
import com.google.gson.Gson
import id.aruna.posts.database.PostsDao
import id.aruna.posts.mvvm.model.PostsModel

class PostsLocalDataStore(private val postsDao: PostsDao) : PostsDataStore {
    override suspend fun getPostsData(): MutableList<PostsModel>? {
        val response = postsDao.getAll()
        return if (response.isEmpty()) null else response
    }

    override suspend fun getPostsDataByTitle(title: String): MutableList<PostsModel>? {
        val response = postsDao.getByTitle(title)
        return if (response.isEmpty()) null else response
    }

    override suspend fun insertAllPostsData(sets: MutableList<PostsModel>?) {
        sets?.let { postsDao.insertAll(*it.toTypedArray()) }
    }
}