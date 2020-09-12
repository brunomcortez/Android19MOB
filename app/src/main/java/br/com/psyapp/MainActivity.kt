package br.com.psyapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import br.com.psyapp.models.RequestState
import br.com.psyapp.ui.auth.BaseAuthViewModel
import br.com.psyapp.ui.extensions.adjustSystemLayout
import br.com.psyapp.ui.login.LoginActivity
import br.com.psyapp.utils.PsyTracker
import br.com.psyapp.utils.featuretoogle.FeatureToggleHelper
import br.com.psyapp.utils.featuretoogle.FeatureToggleListener
import br.com.psyapp.utils.firebase.RemoteConfigUtils
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.analytics.FirebaseAnalytics
import kotlinx.android.synthetic.main.activity_login.pb
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val baseAuthViewModel: BaseAuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        adjustSystemLayout()

        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        val tabBarItems = arrayListOf("DIARY", "SEARCH", "PROFILE", "ABOUT")
        val iterator = tabBarItems.iterator()

        navView.apply {
            setupWithNavController(navController)
        }

        baseAuthViewModel.isLoggedIn()

        registerObserver()

        setupFeatureToggle(iterator, navView)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            RESULT_LOGIN -> {
                val view = nav_view?.findViewById<View>(R.id.emotions_nav_graph)
                view?.performClick()
            }
        }
    }

    private fun registerObserver() {
        baseAuthViewModel.loggedState.observe(this, Observer {
            when (it) {
                is RequestState.Loading -> showLoading()
                is RequestState.Success -> {
                    hideLoading()
                }
                is RequestState.Error -> {
                    hideLoading()
                    startActivity(Intent(this, LoginActivity::class.java))
                }
            }
        })
    }

    private fun showLoading() {
        pb?.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        pb?.visibility = View.GONE
    }

    private fun setupFeatureToggle(
        iterator: MutableIterator<String>,
        navView: BottomNavigationView
    ) {
        RemoteConfigUtils.fetchAndActivate()
            .addOnCompleteListener {
                for ((index, itemMenu) in iterator.withIndex()) {
                    FeatureToggleHelper().configureFeature(itemMenu,
                        object : FeatureToggleListener {
                            override fun onEnabled() {
                                navView.menu.getItem(index).isVisible = true
                            }

                            override fun onInvisible() {
                                navView.menu.getItem(index).isVisible = false
                            }

                            override fun onDisabled(clickListener: (Context) -> Unit) {
                                navView.menu.getItem(index).isVisible = false
                            }
                        })
                }
            }
    }
}
