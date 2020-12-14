package dev.alamodi.newsapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dev.alamodi.newsapi.fragments.MainFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val isFragmentContainerEmpty = savedInstanceState == null
        if (isFragmentContainerEmpty) {
            supportFragmentManager
                    .beginTransaction()
                    .add(R.id.fragmentContainer, MainFragment.newInstance())
                    .commit()
        }
    }
}