package com.raywenderlich.android.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import org.mozilla.geckoview.ContentBlocking
import org.mozilla.geckoview.ContentBlocking.AntiTracking.*
import org.mozilla.geckoview.ContentBlocking.CookieBehavior.ACCEPT_NON_TRACKERS
import org.mozilla.geckoview.GeckoRuntime
import org.mozilla.geckoview.GeckoSession
import org.mozilla.geckoview.GeckoView


class FragmentOne : Fragment() {
    private lateinit var geckoView: GeckoView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_one, container, false)
        setupGeckoView(view)

        view.findViewById<View>(R.id.button).setOnClickListener {

            val action =
                FragmentOneDirections.actionFragmentOneToFragmentTwo()

            findNavController().navigate(action)
        }

        return view
    }


    private fun setupGeckoView(view: View) {
        geckoView = view.findViewById(R.id.geckoview)

        if (runtime == null)
            runtime = GeckoRuntime.create(requireContext())

        if (!geckoSession.isOpen) {
            geckoSession.open(runtime!!)
        }
        geckoView.setSession(geckoSession)
        geckoSession.loadUri("www.remolacha.net")


    }

    companion object {
        private val geckoSession = GeckoSession()
        var runtime: GeckoRuntime? = null

        fun setRecommended() {
            geckoSession.settings.useTrackingProtection = false


            val etpLevel = ContentBlocking.EtpLevel.STRICT
            val getAntiTrackingPolicy = AD + ANALYTIC + SOCIAL + TEST + CRYPTOMINING + STP

            runtime!!.settings.contentBlocking.setEnhancedTrackingProtectionLevel(etpLevel)
            runtime!!.settings.contentBlocking.setStrictSocialTrackingProtection(false)
            runtime!!.settings.contentBlocking.setAntiTracking(getAntiTrackingPolicy)
            runtime!!.settings.contentBlocking.setCookieBehavior(ACCEPT_NON_TRACKERS)
            geckoSession.reload()
        }

        fun setStrict() {
            geckoSession.settings.useTrackingProtection = true

            val etpLevel = ContentBlocking.EtpLevel.STRICT
            val getAntiTrackingPolicy =
                AD + ANALYTIC + SOCIAL + TEST + CRYPTOMINING + FINGERPRINTING + STP

            runtime!!.settings.contentBlocking.setEnhancedTrackingProtectionLevel(etpLevel)
            runtime!!.settings.contentBlocking.setStrictSocialTrackingProtection(true)
            runtime!!.settings.contentBlocking.setAntiTracking(getAntiTrackingPolicy)
            runtime!!.settings.contentBlocking.setCookieBehavior(ACCEPT_NON_TRACKERS)
            geckoSession.reload()
        }
    }
}
