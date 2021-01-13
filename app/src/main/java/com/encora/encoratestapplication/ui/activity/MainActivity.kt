package com.encora.encoratestapplication.ui.activity

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.encora.encoratestapplication.R
import com.encora.encoratestapplication.databinding.ActivityMainBinding
import com.encora.encoratestapplication.view_model.EncoraAssignmentViewModel
import com.leopold.mvvm.ui.BindingActivity

import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BindingActivity<ActivityMainBinding>() {
    private  val encoraAssignmentViewModel: EncoraAssignmentViewModel by viewModel()
    private lateinit var navigationController: NavController
    override fun getLayoutResId() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.encoraAssignmentViewModel = encoraAssignmentViewModel
        binding.setLifecycleOwner(this)
        val host = NavHostFragment.create(R.navigation.fragment_navigation)
        supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment, host)
            .setPrimaryNavigationFragment(host).commit()

    }


}

