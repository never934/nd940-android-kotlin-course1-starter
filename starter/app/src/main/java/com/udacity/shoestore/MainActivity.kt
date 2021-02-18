package com.udacity.shoestore

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private var navHostFragment: Fragment? = null
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Timber.plant(Timber.DebugTree())
        NavigationUI.setupActionBarWithNavController(this, findNavController(R.id.nav_host_fragment))
        appBarConfiguration = AppBarConfiguration(setOf(R.id.loginFragment, R.id.shoesFragment))
        setupActionBarWithNavController(findNavController(R.id.nav_host_fragment), appBarConfiguration)
        navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(findNavController(R.id.nav_host_fragment), appBarConfiguration)
    }

    override fun onBackPressed() {
        if(navHostFragment?.childFragmentManager?.backStackEntryCount == 0){
            openExitDialog()
        }else{
            super.onBackPressed()
        }
    }

    private fun openExitDialog(){
        val builder = AlertDialog.Builder(this)
        builder.setMessage(R.string.exit_dialog_title)
            .setPositiveButton(R.string.ok) { _, _ ->
                finishAffinity()
            }
            .setNegativeButton(R.string.cancel, null)
        builder.create()
        builder.show()
    }
}
