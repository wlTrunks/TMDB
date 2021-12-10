package net.l1ngdtkh3.tmdb

import android.app.Application
import com.google.android.play.core.splitinstall.testing.FakeSplitInstallManagerFactory
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class TMDBApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        FakeSplitInstallManagerFactory.create(
            this,
            getExternalFilesDir("local_testing")
        )
    }
}