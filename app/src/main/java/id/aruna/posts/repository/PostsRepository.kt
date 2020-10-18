package id.aruna.posts.repository

import android.util.Log
import com.google.gson.Gson
import id.aruna.posts.datastore.set.PostsDataStore
import id.aruna.posts.mvvm.model.PostsModel

class PostsRepository private constructor() : BaseRepository<PostsDataStore>() {
    suspend fun getSets(): MutableList<PostsModel>? {
        val cache = localDataStore?.getPostsData()
        if (cache != null) return cache
        val response = remoteDataStore?.getPostsData()
        localDataStore?.insertAllPostsData(response)
        return response
    }

    suspend fun getSetsByUser(title:String): MutableList<PostsModel>? {
        return localDataStore?.getPostsDataByTitle(title)
    }

    companion object {
        val instance by lazy { PostsRepository() }
    }
}

