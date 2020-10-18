package id.aruna.posts.mvvm

import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.aruna.posts.dagger.ViewModelFactory
import id.aruna.posts.repository.PostsRepository
import kotlinx.coroutines.launch
import javax.inject.Inject


class SetListViewModel(
    private val postsRepository: PostsRepository
) : ViewModel() {
    private val mViewState = MutableLiveData<SetListViewState>().apply {
        value = SetListViewState(loading = true)
    }
    val viewState: LiveData<SetListViewState>
        get() = mViewState

    init {
        getSets()
    }

    fun getSets() = viewModelScope.launch {
        try {
            val data = postsRepository.getSets()
            mViewState.value = mViewState.value?.copy(loading = false, error = null, data = data)
        } catch (ex: Exception) {
            mViewState.value = mViewState.value?.copy(loading = false, error = ex, data = null)
        }
    }

    private fun isMainThread(): Boolean {
        return Looper.myLooper() == Looper.getMainLooper()
    }

    fun getSetsByTitle(title: String) = viewModelScope.launch {
        if (title.isNotEmpty()) {
            try {
                val data = postsRepository.getSetsByUser(title)
                mViewState.postValue(SetListViewState(false, error = null, data = null))
                if (isMainThread()) {
                    mViewState.value =
                        SetListViewState(false, error = null, data = data)
                } else {
                    mViewState.postValue(
                        SetListViewState(false, error = null, data = data)
                    )
                }

                /*mViewState.value =
                    mViewState.value?.copy(loading = false, error = null, data = data)*/
            } catch (ex: Exception) {
                mViewState.value = SetListViewState(false, error = ex, data = null)
            }
        } else {
            //getSets()
        }
    }
}