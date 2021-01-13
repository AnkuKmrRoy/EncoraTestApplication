package com.leopold.mvvm.di

import com.encora.encoratestapplication.data.db.EncoraAssignmentDatabase
import com.encora.encoratestapplication.data.repositories.EncoraSongsRepository

import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.module

/**
 * @author Ankesh
 */
val roomModule = module {
    single { EncoraAssignmentDatabase.getInstance(androidApplication()) }
    single(createOnStart = false) { get<EncoraAssignmentDatabase>().getEncoraAssignmentDao() }
    single { EncoraSongsRepository(get()) }
}


