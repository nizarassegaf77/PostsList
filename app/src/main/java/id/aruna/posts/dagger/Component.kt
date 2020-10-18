package id.aruna.posts.dagger

import dagger.Component
import id.aruna.posts.datastore.set.PostsRemoteDataStore
import id.aruna.posts.main.MainActivity
import id.aruna.posts.mvvm.SetListFragment
import javax.inject.Singleton

/**
 * Created by Nizar Assegaf on 17,October,2020
 */

@Singleton
@Component(modules = [RepositoryModule::class, DatabaseModule::class])
interface Component {
    fun inject(setListFragment: SetListFragment)
}