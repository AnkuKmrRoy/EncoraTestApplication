package com.leopold.mvvm.di

import com.encora.encoratestapplication.view_model.EncoraAssignmentViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

/**
 * @author Leopold
 */
val viewModelModule = module {
    viewModel {
        EncoraAssignmentViewModel(get())
    }

}