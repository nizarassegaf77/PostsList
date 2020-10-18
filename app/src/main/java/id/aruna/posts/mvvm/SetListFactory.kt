package id.aruna.posts.mvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.aruna.posts.repository.PostsRepository

class SetListFactory(
    private val setRepository: PostsRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SetListViewModel::class.java))
            return SetListViewModel(setRepository) as T
        throw IllegalArgumentException()
    }
}