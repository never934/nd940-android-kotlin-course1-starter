package com.udacity.shoestore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import com.udacity.shoestore.databinding.MainActivityBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: MainActivityBinding
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.main_activity);
        NavigationUI.setupActionBarWithNavController(this, findNavController(R.id.nav_host_fragment))
        appBarConfiguration = AppBarConfiguration(setOf(R.id.loginFragment, R.id.shoesFragment))
        setupActionBarWithNavController(findNavController(R.id.nav_host_fragment), appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(findNavController(R.id.nav_host_fragment), appBarConfiguration)
    }

    override fun onBackPressed() {
        if(supportFragmentManager.primaryNavigationFragment?.childFragmentManager?.backStackEntryCount == 0){
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
