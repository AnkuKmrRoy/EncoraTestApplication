package com.encora.encoratestapplication.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.encora.encoratestapplication.data.db.entity.EncoraSongsEntity

@Dao
interface EncoraAssignmentDao {

    @Query("SELECT * FROM EncoraTestAssignment")
     fun getAllSongs(): LiveData<List<EncoraSongsEntity>>

    @Insert
     suspend fun insertSongs(songList: List<EncoraSongsEntity>)


}

