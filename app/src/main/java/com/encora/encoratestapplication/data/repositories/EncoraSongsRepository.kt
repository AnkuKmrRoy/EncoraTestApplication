package com.encora.encoratestapplication.data.repositories

import androidx.lifecycle.LiveData
import com.encora.encoratestapplication.data.db.dao.EncoraAssignmentDao
import com.encora.encoratestapplication.data.db.entity.EncoraSongsEntity


class EncoraSongsRepository(private val dao: EncoraAssignmentDao) {

    fun  getAllSongs(): LiveData<List<EncoraSongsEntity>>? {
        val songList=dao.getAllSongs()
        return songList
    }

    suspend fun insertSongsFromResponseApi(songList:List<EncoraSongsEntity>){
        dao.insertSongs(songList)
    }

}