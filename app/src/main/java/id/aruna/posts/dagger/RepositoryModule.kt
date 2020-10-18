package id.aruna.posts.dagger

import dagger.Module
import dagger.Provides
import id.aruna.posts.datastore.set.PostsLocalDataStore
import id.aruna.posts.datastore.set.PostsRemoteDataStore
import id.aruna.posts.repository.PostsRepository
import javax.inject.Singleton

/**
 * Created by Nizar Assegaf on 18,October,2020
 */

@Module
class RepositoryModule(
    private var remoteDataStore: PostsRemoteDataStore,
    private var localDataStore: PostsLocalDataStore
) {
    @Singleton
    @Provides
    fun providesCategoryRepository(

    ): PostsRepository {
        return PostsRepository(localDataStore, remoteDataStore)
    }
}