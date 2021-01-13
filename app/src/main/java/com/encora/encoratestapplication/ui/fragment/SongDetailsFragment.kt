package com.encora.encoratestapplication.ui.fragment

import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import com.encora.encoratestapplication.R
import com.encora.encoratestapplication.data.db.entity.EncoraSongsEntity
import com.encora.encoratestapplication.databinding.FragmentSongDetailsBinding
import com.encora.encoratestapplication.view_model.EncoraAssignmentViewModel
import com.leopold.mvvm.ui.BindingFragment
import kotlinx.android.synthetic.main.fragment_song_details.*
import kotlinx.android.synthetic.main.song_row_item.*
import kotlinx.android.synthetic.main.song_row_item.view.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.IOException


class SongDetailsFragment : BindingFragment<FragmentSongDetailsBinding>() ,View.OnClickListener{

    lateinit var songUrl:String
    var isPLAYING:Boolean=false

    private  val imageListViewModel: EncoraAssignmentViewModel by viewModel()
    companion object {
        @JvmStatic
        fun newInstance() = SongDetailsFragment()
    }

    override fun getLayoutResId() = R.layout.fragment_song_details;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.songDetailsViewModel = imageListViewModel
        binding.setLifecycleOwner(this)
        initData()
    }

    fun initData(){
        val songData = arguments?.getParcelable<EncoraSongsEntity>("SONG_DETAILS")
        Glide.with(context).load(songData!!.image_url).into(binding.songImage)
        binding.tvSongTitle.setText(songData.title)
        songUrl = songData.href!!

        binding.ivPalySong.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.getId()){
            R.id.ivPalySong -> {
                GlobalScope.launch {
                    playAudio()
                }
            }
        }
    }

    fun playAudio(){
        val mp = MediaPlayer()
        if (!isPLAYING) {
            isPLAYING = true

            try {
                mp.setDataSource(songUrl)
                mp.prepare()
                mp.start()
            } catch (e: IOException) {
                Log.e("Exp", "prepare() failed")
            }
        } else {
            isPLAYING = false
            mp.release()
        }
    }

    private fun stopPlaying(mp: MediaPlayer) {
        mp.release()
    }

}