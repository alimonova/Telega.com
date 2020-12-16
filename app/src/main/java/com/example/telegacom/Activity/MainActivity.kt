package com.example.telegacom.Activity

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainer
import androidx.fragment.app.FragmentContainerView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.viewpager2.adapter.FragmentViewHolder
import com.example.telegacom.Fragment.*
import com.example.telegacom.MainViewModel
import com.example.telegacom.R
import com.example.telegacom.ViewModelFactory.MainViewModelFactory
import com.google.android.material.navigation.NavigationView
import timber.log.Timber

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val CORRECT_BUZZ_PATTERN = longArrayOf(100, 100, 100, 100, 100, 100)

    lateinit var toolbar: Toolbar
    lateinit var appBarConfiguration: AppBarConfiguration
    lateinit var drawer: DrawerLayout
    lateinit var linearLayout: LinearLayout
    lateinit var navigationView: NavigationView
    lateinit var viewModel : MainViewModel
    lateinit var fragment : FragmentContainerView
    var testValue : Int = 0
    lateinit var current_user_id : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        testValue = 100;
        var viewModelFactory = MainViewModelFactory(testValue);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java);
        viewModel.startTimer();
        viewModel.startTotalTimer();
        setContentView(R.layout.activity_main)
        Timber.i("onCreate called")
        val arguments : Bundle? = getIntent().extras as Bundle
        current_user_id = arguments!!.get("current_user_id") as String;
        toolbar = findViewById<Toolbar>(R.id.toolbar_main)
        this.toolbar.title = "Telega.com"
        setSupportActionBar(toolbar)

        toolbar = findViewById<Toolbar>(R.id.toolbar_main)
        toolbar.setTitle(resources.getString(R.string.app_name))
        setSupportActionBar(toolbar)

        viewModel.forAMinuteOnFragment.observe(this, Observer { isForAMinute ->
            if (isForAMinute == true) {
                forAMinuteOnFragment();
                viewModel.forAMinuteOnFragment();
            }
        })

        linearLayout = findViewById<LinearLayout>(R.id.linearLayout_main)

       drawer = findViewById(R.id.drawer_layout)
        val toggle = ActionBarDrawerToggle(
            this,
            drawer,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer.setDrawerListener(toggle)
        toggle.syncState()
        toolbar.setNavigationIcon(R.drawable.ic_baseline_menu_24)
        navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)
        navigationView.getMenu().getItem(2).setChecked(true);
        /*backStackRemove()
        supportFragmentManager.beginTransaction().replace(
            R.id.framelayout_main, ChannelsFragment(),
            "Каналы"
        ).commit()*/
    }

    private fun forAMinuteOnFragment() {
        Toast.makeText(
            this,
            "Вы находились на этой странице в течение еще одной минуты.",
            Toast.LENGTH_SHORT
        ).show()
        buzz(CORRECT_BUZZ_PATTERN)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val id = item.itemId
        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp() =
               this.findNavController(R.id.myNavHostFragment).navigateUp()


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        viewModel.UpdateSeconds();
        Log.i("test", "Navigation Item selected");
        // Handle navigation view item clicks here.
        //Checking if the item is in checked state or not, if not make it in checked state
        if (item.isChecked) item.setChecked(false) else item.setChecked(true)

        //Closing drawer on item click
        drawer.closeDrawers()
        val id = item.itemId

        when (id) {
            R.id.home -> {
                backStackRemove()
                this.findNavController(R.id.myNavHostFragment).navigate(R.id.homeFragment)
            }
            R.id.profile -> {
                backStackRemove()
                this.findNavController(R.id.myNavHostFragment).navigate(R.id.profileFragment)
            }
            R.id.channels -> {
                backStackRemove()
                this.findNavController(R.id.myNavHostFragment).navigate(R.id.channelsFragment)
            }
            R.id.settings -> {
                backStackRemove()
                this.findNavController(R.id.myNavHostFragment).navigate(R.id.settingsFragment)
            }
            R.id.contacts -> {
                backStackRemove()
                this.findNavController(R.id.myNavHostFragment).navigate(R.id.contactsFragment)
            }
            R.id.about -> {
                backStackRemove()
                this.findNavController(R.id.myNavHostFragment).navigate(R.id.aboutFragment)
            }
            R.id.rules -> {
                backStackRemove()
                this.findNavController(R.id.myNavHostFragment).navigate(R.id.rulesFragment)
            }
            R.id.login -> {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
        }
        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        drawer.closeDrawer(GravityCompat.START)
        return true
    }


    fun backStackRemove() {
        for (i in 0 until supportFragmentManager.backStackEntryCount) {
            supportFragmentManager.popBackStack()
        }
    }

    override fun onStart() {
        super.onStart();
        viewModel.focusStartTimer();
        Timber.i("onStart called")
    }

    override fun onPause() {
        super.onPause();
        Timber.i("onPause called")
    }

    override fun onResume() {
        super.onResume();
        Timber.i("onResume called")
    }

    override fun onStop() {
        super.onStop();
        viewModel.focusStopTimer();
        Timber.i("onStop called")
    }

    override fun onDestroy() {
        viewModel.stopTotalTimer();
        viewModel.stopTimer();
        super.onDestroy();
        Timber.i("onDestroy called")
    }

    override fun onRestart() {
        super.onRestart();
        Timber.i("onDestroy called")
    }

    private fun buzz(pattern: LongArray) {
        val buzzer = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        buzzer?.let {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                buzzer.vibrate(VibrationEffect.createWaveform(pattern, -1))
            } else {
                //deprecated in API 26
                buzzer.vibrate(pattern, -1)
            }
        }
        Log.i("debug", "VIBRATING")
    }
}