package id.aruna.posts.repository

import id.aruna.posts.database.AppDatabase
import id.aruna.posts.datastore.set.PostsLocalDataStore
import id.aruna.posts.datastore.set.PostsRemoteDataStore
import id.aruna.posts.mvvm.model.PostsModel
import id.aruna.posts.webservice.RetrofitApp

class PostsRepository(appDatabase: AppDatabase) {
    private var localDataStore = PostsLocalDataStore(appDatabase.postsDao())
    private var remoteDataStore = PostsRemoteDataStore(RetrofitApp.webService)

    suspend fun getSets(): MutableList<PostsModel>? {
        val cache = localDataStore.getPostsData()
        if (cache != null) return cache
        val response = remoteDataStore.getPostsData()
        localDataStore.insertAllPostsData(response)
        return response
    }

    suspend fun getSetsByUser(title: String): MutableList<PostsModel>? {
        return localDataStore.getPostsDataByTitle(title)
    }

}

