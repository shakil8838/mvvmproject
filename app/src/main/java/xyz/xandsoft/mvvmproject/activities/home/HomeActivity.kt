package xyz.xandsoft.mvvmproject.activities.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import kotlinx.android.synthetic.main.activity_home.*
import xyz.xandsoft.mvvmproject.R

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        setSupportActionBar(home_toolbar)
        val navController = Navigation.findNavController(this, R.id.home_fragment)
        NavigationUI.setupWithNavController(home_navigation_view, navController)
        NavigationUI.setupActionBarWithNavController(
            this, navController,
            home_drawer_layout
        )
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(
            Navigation.findNavController(this, R.id.home_fragment),
            home_drawer_layout
        )
    }
}