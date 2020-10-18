package id.aruna.posts.dagger

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.aruna.posts.mvvm.SetListViewModel
import id.aruna.posts.repository.PostsRepository
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Nizar Assegaf on 18,October,2020
 */

@Singleton
class ViewModelFactory @Inject constructor(private var postsRepository: PostsRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SetListViewModel(postsRepository) as T
    }
}