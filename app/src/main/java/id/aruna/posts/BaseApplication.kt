package id.aruna.posts

import android.app.Application
import id.aruna.posts.dagger.Component
import id.aruna.posts.dagger.DaggerComponent
import id.aruna.posts.dagger.RepositoryModule
import id.aruna.posts.database.AppDatabase
import id.aruna.posts.datastore.set.PostsRemoteDataStore
import id.aruna.posts.datastore.set.PostsLocalDataStore
import id.aruna.posts.repository.PostsRepository
import id.aruna.posts.webservice.RetrofitApp

class BaseApplication : Application() {

    lateinit var component: Component

    companion object {
        lateinit var instance: BaseApplication
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        val webService = RetrofitApp.WEB_SERVICE
        val appDatabase = AppDatabase.getInstance(this)

        component = DaggerComponent
            .builder()
            .repositoryModule(
                RepositoryModule(
                    localDataStore = PostsLocalDataStore(appDatabase.postsDao()),
                    remoteDataStore = PostsRemoteDataStore(webService)
                )
            ).build()

        /* PostsRepository.instance.apply {
             init(
                 PostsLocalDataStore(appDatabase.postsDao()),
                 PostsRemoteDataStore(webService)
             )
         }*/
    }
}