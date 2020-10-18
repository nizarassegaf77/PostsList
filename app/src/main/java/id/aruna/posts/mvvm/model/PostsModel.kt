package id.aruna.posts.mvvm.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PostsModel(
    @PrimaryKey var id: Int,
    var userId: Int,
    var title: String,
    var body: String
) {
    data class PostsResponse(
        var postsResponses: MutableList<PostsModel>
    )
}