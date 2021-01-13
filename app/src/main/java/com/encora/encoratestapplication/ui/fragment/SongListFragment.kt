package com.encora.encoratestapplication.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.encora.encoratestapplication.R
import com.encora.encoratestapplication.data.db.entity.EncoraSongsEntity
import com.encora.encoratestapplication.databinding.FragmentSongListBinding
import com.encora.encoratestapplication.ui.adapter.SongListAdapter
import com.encora.encoratestapplication.ui.adapter.onSongClickedListener
import com.encora.encoratestapplication.view_model.EncoraAssignmentViewModel
import com.leopold.mvvm.ui.BindingFragment

import com.mr.mrtestapplication.utils.EncoraTestConstants.Companion.PAGE_LIMIT
import kotlinx.android.synthetic.main.fragment_song_list.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class SongListFragment : BindingFragment<FragmentSongListBinding>(), onSongClickedListener {

    companion object {
        @JvmStatic
        fun newInstance() = SongListFragment()

    }

    private var layoutManager:LinearLayoutManager? = null
    private  val songsListViewModel: EncoraAssignmentViewModel by viewModel()
    //var songsList = ArrayList<EntryDataModel>()
    var adapter: SongListAdapter? = null
    var isLoading = false
    var rowsArrayList: ArrayList<String> = ArrayList()
    var currentPageLimit = PAGE_LIMIT;
    var isDataPresent:Boolean = false
    var audioDetails: ShareAudioDetails? = null


    override fun getLayoutResId() = R.layout.fragment_song_list;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.songListViewModel = songsListViewModel
        binding.setLifecycleOwner(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rvSongList?.layoutManager = layoutManager
        rvSongList.setHasFixedSize(true)


    }

    override fun onStart() {
        super.onStart()
        if(!isDataPresent)
        getSongList()

        displayAudioList()
    }


    fun getSongList(){
        GlobalScope.launch {
            songsListViewModel.getDataFromAPI(currentPageLimit)
        }

    }

    fun displayAudioList(){

        val audioList = songsListViewModel.getAudioListFromDb()

        audioList?.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            if (it.size > 0) {
                rvSongList.layoutManager = LinearLayoutManager(activity)
                rvSongList.adapter = SongListAdapter(requireContext(), it, this)
                isDataPresent = true
            }
        })
    }

    override fun songListItemClicked(songItemData: EncoraSongsEntity) {
        val itemClicked = Bundle().apply {
            putParcelable("SONG_DETAILS", songItemData)
        }
        audioDetails?.shareDetails(songItemData)
        activity?.findNavController(R.id.nav_host_fragment)?.navigate(R.id.action_SongListFragment_to_SongDetailsFragment,itemClicked)
    }

    interface ShareAudioDetails {
        fun shareDetails(data: EncoraSongsEntity?)
    }

}