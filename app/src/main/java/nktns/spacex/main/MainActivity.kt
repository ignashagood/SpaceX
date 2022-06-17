package nktns.spacex.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import nktns.spacex.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, MainFragment())
                .commit()
        }
    }
}
