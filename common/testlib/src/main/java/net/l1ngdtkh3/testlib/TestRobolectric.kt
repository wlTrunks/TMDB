package net.l1ngdtkh3.testlib

import android.app.Application
import android.content.Context
import android.os.Build
import androidx.test.core.app.ApplicationProvider
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(
    manifest = Config.NONE,
    application = TestRobolectric.ApplicationStub::class,
    sdk = [Build.VERSION_CODES.LOLLIPOP]
)
open class TestRobolectric {

    protected val application: Application by lazy {
        ApplicationProvider.getApplicationContext<ApplicationStub>()
    }
    protected val context: Context by lazy {
        application
    }

    internal class ApplicationStub : Application()
}