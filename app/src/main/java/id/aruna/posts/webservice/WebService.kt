package id.aruna.posts.webservice

import id.aruna.posts.mvvm.model.PostsModel
import retrofit2.Response
import retrofit2.http.GET

interface WebService {
    @GET("posts")
    suspend fun getSets(): Response<MutableList<PostsModel>>
}