package com.example.telegacom.Activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.LinearLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.telegacom.Activity.LoginActivity
import com.example.telegacom.Fragment.*
import com.example.telegacom.R
import com.example.telegacom.TelegaTimer
import com.google.android.material.navigation.NavigationView
import timber.log.Timber

/** onSaveInstanceState Bundle Keys **/
const val KEY_REVENUE = "revenue_key"
const val KEY_DESSERT_SOLD = "dessert_sold_key"
const val KEY_TIMER_SECONDS = "timer_seconds_key"
const val KEY_TIMER_FOCUS_SECONDS = "timer_seconds_in_focus_key"

public class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var toolbar: Toolbar
    lateinit var drawer: DrawerLayout
    lateinit var linearLayout: LinearLayout
    lateinit var navigationView: NavigationView
    private lateinit var telegaTimer: TelegaTimer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.i("onCreate called")
        setContentView(R.layout.activity_main)

        val arguments : Bundle? = getIntent().extras as Bundle
        // Setup dessertTimer, passing in the lifecycle
        telegaTimer = TelegaTimer(this.lifecycle)

        if (savedInstanceState != null) {
            telegaTimer.secondsCount = savedInstanceState.getInt(KEY_TIMER_SECONDS, 0)
            telegaTimer.secondsCountInFocus = savedInstanceState.getInt(KEY_TIMER_FOCUS_SECONDS, 0)
        }

        if (arguments != null) {
            if (!arguments.isEmpty) {
                val dialog = CustomDialogFragment()
                val args = Bundle()
                if (arguments != null) {
                    args.putString(
                        "message",
                        "Вы вошли со следующими данными:\nEmail: " + arguments.get("email")
                            .toString() + "\nПароль: " + arguments.get("password").toString()
                    )
                }
                args.putString("title", "Авторизация")
                dialog.arguments = args
                dialog.show(supportFragmentManager, "custom")
            }
        }
        toolbar = findViewById<Toolbar>(R.id.toolbar_main)
        this.toolbar.title = "Telega.com"
        setSupportActionBar(toolbar)

        toolbar = findViewById<Toolbar>(R.id.toolbar_main)
        toolbar.setTitle(resources.getString(R.string.app_name))
        setSupportActionBar(toolbar)

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
        navigationView.getMenu().getItem(4).setChecked(true);
        backStackRemove()
        supportFragmentManager.beginTransaction().replace(
            R.id.framelayout_main, AboutFragment(),
            "О приложении"
        ).commit()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY_TIMER_SECONDS, telegaTimer.secondsCount)
        outState.putInt(KEY_TIMER_FOCUS_SECONDS, telegaTimer.secondsCountInFocus)
        Timber.i("onSaveInstanceState Called")
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        //Checking if the item is in checked state or not, if not make it in checked state
        if (item.isChecked) item.setChecked(false) else item.setChecked(true)

        //Closing drawer on item click
        drawer.closeDrawers()
        val id = item.itemId

        if (id == R.id.home) {
            backStackRemove()
            supportFragmentManager.beginTransaction().replace(
                R.id.framelayout_main, HomeFragment(),
                "Главная"
            ).commit()
            return true
        } else if (id == R.id.profile) {
            backStackRemove()
            supportFragmentManager.beginTransaction().replace(
                R.id.framelayout_main, ProfileFragment(),
                "Профиль"
            ).commit()
            return true
        } else if (id == R.id.channels) {
            backStackRemove()
            supportFragmentManager.beginTransaction().replace(
                R.id.framelayout_main, ChannelsFragment(),
                "Каналы"
            ).commit()
            return true
        } else if (id == R.id.settings) {
            backStackRemove()
            supportFragmentManager.beginTransaction().replace(
                R.id.framelayout_main, SettingsFragment(),
                "Настройки"
            ).commit()
            return true
        } else if (id == R.id.contacts) {
            backStackRemove()
            supportFragmentManager.beginTransaction().replace(
                R.id.framelayout_main, ContactsFragment(),
                "Контакты"
            ).commit()
            return true
        } else if (id == R.id.about) {
            backStackRemove()
            supportFragmentManager.beginTransaction().replace(
                R.id.framelayout_main, AboutFragment(),
                "О приложении"
            ).commit()
            return true
        }  else if (id == R.id.rules) {
            backStackRemove()
            supportFragmentManager.beginTransaction().replace(
                R.id.framelayout_main, RulesFragment(),
                "Правила использования"
            ).commit()
            return true
        } else if (id == R.id.login) {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
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
        Timber.i("onStop called")
    }

    override fun onDestroy() {
        super.onDestroy();
        Timber.i("onDestroy called")
    }

    override fun onRestart() {
        super.onRestart();
        Timber.i("onDestroy called")
    }
}