package nktns.spacex

import android.app.Application
import androidx.fragment.app.Fragment
import com.facebook.drawee.backends.pipeline.Fresco
import nktns.spacex.di.AppComponent
import nktns.spacex.di.DaggerAppComponent
import timber.log.Timber
import timber.log.Timber.Forest.plant

class MyApplication : Application() {

    private var _appComponent: AppComponent? = null

    internal val appComponent: AppComponent
        get() = checkNotNull(_appComponent) {
            "AppComponent isn't initialized"
        }

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            plant(Timber.DebugTree())
        }
        Fresco.initialize(this)
        _appComponent = DaggerAppComponent.factory().create(this)
    }
}

fun Fragment.getAppComponent(): AppComponent = (requireActivity().application as MyApplication).appComponent
