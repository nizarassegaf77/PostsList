package id.aruna.posts.repository

import android.util.Log
import com.google.gson.Gson
import id.aruna.posts.datastore.set.PostsDataStore
import id.aruna.posts.datastore.set.PostsLocalDataStore
import id.aruna.posts.datastore.set.PostsRemoteDataStore
import id.aruna.posts.mvvm.model.PostsModel
import javax.inject.Inject

class PostsRepository @Inject constructor(
    var localDataStore: PostsLocalDataStore,
    var remoteDataStore: PostsRemoteDataStore
) {
    suspend fun getSets(): MutableList<PostsModel>? {
        val cache = localDataStore?.getPostsData()
        if (cache != null) return cache
        val response = remoteDataStore?.getPostsData()
        localDataStore?.insertAllPostsData(response)
        return response
    }

    suspend fun getSetsByUser(title: String): MutableList<PostsModel>? {
        return localDataStore?.getPostsDataByTitle(title)
    }

    /*companion object {
        val instance by lazy { PostsRepository() }
    }*/
}

