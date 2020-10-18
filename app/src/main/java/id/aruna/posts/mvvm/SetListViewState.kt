package id.aruna.posts.mvvm

import id.aruna.posts.mvvm.model.PostsModel

data class SetListViewState (
    var loading: Boolean = false,
    var error: Exception? = null,
    var data: MutableList<PostsModel>? = null
)