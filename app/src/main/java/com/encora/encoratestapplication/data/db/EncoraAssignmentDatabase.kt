package com.encora.encoratestapplication.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.encora.encoratestapplication.data.db.EncoraAssignmentDatabase.Companion.DB_VERSION
import com.encora.encoratestapplication.data.db.dao.EncoraAssignmentDao
import com.encora.encoratestapplication.data.db.entity.EncoraSongsEntity


@Database(entities = [EncoraSongsEntity::class], version = DB_VERSION, exportSchema = false)

abstract class EncoraAssignmentDatabase : RoomDatabase() {
    abstract fun getEncoraAssignmentDao(): EncoraAssignmentDao

    companion object {
        const val DB_VERSION = 1
        private const val DB_NAME = "encora_assignment.db"

        @Volatile
        private var INSTANCE: EncoraAssignmentDatabase? = null

        fun getInstance(context: Context?): EncoraAssignmentDatabase{
            synchronized(this){
                var instance:EncoraAssignmentDatabase?= INSTANCE
                if(instance == null){
                    instance = Room.databaseBuilder(
                        context!!.applicationContext,
                        EncoraAssignmentDatabase::class.java,
                        "mr_assignment_database"
                    ).build()
                }
                return instance
            }

        }

    }

}