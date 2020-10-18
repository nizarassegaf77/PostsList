package id.aruna.posts.datastore.set

import id.aruna.posts.mvvm.model.PostsModel
import id.aruna.posts.webservice.WebService
import javax.inject.Inject
import javax.inject.Singleton

class PostsRemoteDataStore(private val webService: WebService) :
    PostsDataStore {
    override suspend fun getPostsData(): MutableList<PostsModel>? {
        val response = webService.getSets()
        if (response.isSuccessful) return response.body()

        throw Exception("Terjadi kesalahan saat melakukan request data, status error ${response.code()}")
    }

    override suspend fun getPostsDataByTitle(title: String): MutableList<PostsModel>? {
        return null
    }

    override suspend fun insertAllPostsData(sets: MutableList<PostsModel>?) {
    }
}