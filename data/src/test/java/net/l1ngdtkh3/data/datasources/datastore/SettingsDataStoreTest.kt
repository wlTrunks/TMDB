package net.l1ngdtkh3.data.datasources.datastore

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.runBlocking
import net.l1ngdtkh3.data.datasources.db.MoviesType
import net.l1ngdtkh3.testlib.CoroutineTestRule
import net.l1ngdtkh3.testlib.TestRobolectric
import org.junit.Before
import org.junit.Rule
import org.junit.Test

internal class SettingsDataStoreTest : TestRobolectric() {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    lateinit var settingsDataStore: SettingsDataStore

    @get:Rule
    var coroutineTestRule = CoroutineTestRule()

    @Before
    fun setUp() {
        settingsDataStore = SettingsDataStoreImpl(context)
    }

    @Test
    fun `set last time update NOW_PLAYING`() = runBlocking {
        settingsDataStore.setLastTimeUpdate(MoviesType.NOW_PLAYING, 1)
        assert(settingsDataStore.getLastTimeUpdate(MoviesType.NOW_PLAYING) == 1L)
    }

    @Test
    fun `set last time update UP_COMING`() = runBlocking {
        settingsDataStore.setLastTimeUpdate(MoviesType.UP_COMING, 2)
        assert(settingsDataStore.getLastTimeUpdate(MoviesType.UP_COMING) == 2L)
    }

    @Test
    fun `set last time update POPULAR`() = runBlocking {
        settingsDataStore.setLastTimeUpdate(MoviesType.UP_COMING, 2)
        assert(settingsDataStore.getLastTimeUpdate(MoviesType.UP_COMING) == 2L)
    }
}
