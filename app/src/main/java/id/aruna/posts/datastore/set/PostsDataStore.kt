package id.aruna.posts.datastore.set

import id.aruna.posts.mvvm.model.PostsModel

interface PostsDataStore {
    suspend fun getPostsData(): MutableList<PostsModel>?
    suspend fun getPostsDataByTitle(title:String): MutableList<PostsModel>?
    suspend fun insertAllPostsData(sets: MutableList<PostsModel>?)
}