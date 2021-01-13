package com.encora.encoratestapplication.view_model

import android.util.Log
import androidx.databinding.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.encora.encoratestapplication.connectivity.model.ResponseData
import com.encora.encoratestapplication.connectivity.retrofit.AppAPICallServices
import com.encora.encoratestapplication.core.BaseViewModel
import com.encora.encoratestapplication.data.db.entity.EncoraSongsEntity
import com.encora.encoratestapplication.data.repositories.EncoraSongsRepository
import com.google.gson.Gson
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class EncoraAssignmentViewModel(private val repository: EncoraSongsRepository) : BaseViewModel() , Observable {
    lateinit var responseData: ResponseData

    var songList = ArrayList<EncoraSongsEntity>()
    lateinit var iconUrl:String
    lateinit var imageUrl:String
    lateinit var audioTitle:String
    lateinit var audioId:String
    lateinit var type:String
    lateinit var audioUrl:String

    fun getDataFromAPI(limit: Int){

        AppAPICallServices.songApiCall(limit).enqueue(object : Callback<ResponseData> {

            override fun onResponse(call: Call<ResponseData>, response: Response<ResponseData>) {
                if (response.isSuccessful) {
                    responseData = response.body()!!

                    val json = JSONObject(Gson().toJson(responseData.feed))
                    val jsonEntity: JSONArray = json.getJSONArray("entry")
                    for (i in 0 until jsonEntity.length()) {

                        val jsonEntryObj:JSONObject = jsonEntity.getJSONObject(i)

                        val jsonImageArray:JSONArray = jsonEntryObj.getJSONArray("im:image")
                        for(i in 0 until jsonImageArray.length()){
                            val jsonIconObj: JSONObject = jsonImageArray.getJSONObject(0)
                            val jsonImageObj: JSONObject = jsonImageArray.getJSONObject(2)
                            iconUrl = jsonIconObj.getString("label")
                            imageUrl = jsonImageObj.getString("label")
                        }
                        val jsonTitleObj :JSONObject = jsonEntryObj.getJSONObject("title")
                        audioTitle = jsonTitleObj.getString("label")
                        val jsonIdObj : JSONObject = jsonEntryObj.getJSONObject("id")
                        val idAttriObj:JSONObject = jsonIdObj.getJSONObject("attributes")
                         audioId = idAttriObj.getString("im:id")

                        val jsonLinkArray:JSONArray = jsonEntryObj.getJSONArray("link")
                        for(i in 0 until jsonLinkArray.length()){
                            val jsonLinkObj: JSONObject = jsonLinkArray.getJSONObject(i)
                            val jsonAttributesObj = jsonLinkObj.getJSONObject("attributes")
                             type = jsonAttributesObj.getString("type")
                            if(type.equals("audio/x-m4a")){
                                 audioUrl = jsonAttributesObj.getString("href")
                            }
                        }
                        songList.add(EncoraSongsEntity(0,audioId,iconUrl,imageUrl,audioTitle,type,audioUrl))
                    }
                    insertAudiListIntoDb(songList)
                }
            }



            override fun onFailure(call: Call<ResponseData>, t: Throwable) {
                Log.e("EXC", toString())
            }
        })

    }


    fun insertAudiListIntoDb(audioList: List<EncoraSongsEntity>): Job =
        viewModelScope.launch {
            repository.insertSongsFromResponseApi(audioList)
        }

     fun getAudioListFromDb(): LiveData<List<EncoraSongsEntity>>? {
        val audioList = repository.getAllSongs()
        return audioList;
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        TODO("Not yet implemented")
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        TODO("Not yet implemented")
    }

}