package id.aruna.posts.repository

import id.aruna.posts.datastore.set.PostsDataStore
import id.aruna.posts.mvvm.model.PostsModel
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class PostsRepositoryTest {
    @Mock
    var localDataStore: PostsDataStore? = null

    @Mock
    var remoteDataStore: PostsDataStore? = null

    var postsRepository: PostsRepository? = null

    var mutableList = mutableListOf<PostsModel>()

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        postsRepository = PostsRepository.instance.apply {
            init(localDataStore!!, remoteDataStore!!)
        }
    }

    @Test
    fun shouldNotGetPostsFromRemoteWhenLocalIsNotNull() {
        runBlocking {
            `when`(localDataStore?.getPostsData()).thenReturn(mutableList)
            postsRepository?.getSets()

            verify(remoteDataStore, never())?.getPostsData()
            verify(localDataStore, never())?.insertAllPostsData(mutableList)
        }
    }

    @Test
    fun shouldCallGetPostsFromRemoteAndSaveToLocalWhenLocalIsNull() {
        runBlocking {
            `when`(localDataStore?.getPostsData()).thenReturn(null)
            `when`(remoteDataStore?.getPostsData()).thenReturn(mutableList)
            postsRepository?.getSets()

            verify(remoteDataStore, times(1))?.getPostsData()
            verify(localDataStore, times(1))?.insertAllPostsData(mutableList)
        }
    }

    @Test
    fun shouldThrowExceptionWhenRemoteThrowAnException() {
        runBlocking {
            `when`(localDataStore?.getPostsData()).thenReturn(null)
            `when`(remoteDataStore?.getPostsData()).thenAnswer { throw Exception() }

            try {
                postsRepository?.getSets()
            } catch (ex: Exception) {
            }
        }
    }
}