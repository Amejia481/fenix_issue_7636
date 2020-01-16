package com.raywenderlich.android.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.mozilla.geckoview.*

class Main2Activity : AppCompatActivity() {
    private lateinit var geckoView: GeckoView
    private val geckoSession = GeckoSession()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)


        // 1
        geckoView = findViewById(R.id.geckoview)
//
        // 2
        val runtime = GeckoRuntime.create(this, GeckoRuntimeSettings.Builder()
            .aboutConfigEnabled(true)
            .consoleOutput(true)
            .build())
        geckoSession.open(runtime)

        geckoView.setSession(geckoSession)

        // 4
        geckoSession.loadUri("www.google.com")
    }
}
