package com.discover.soulswipe.presentation.notes.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.discover.soulswipe.R
import com.discover.soulswipe.core.utils.NetworkResult
import com.discover.soulswipe.core.utils.loadImage
import com.discover.soulswipe.core.utils.showToast
import com.discover.soulswipe.data.model.response.notes.NotesResponse
import com.discover.soulswipe.databinding.FragmentNotesBinding
import com.discover.soulswipe.presentation.notes.ui.adapter.LikesAdapter
import com.discover.soulswipe.presentation.notes.viewmodel.NotesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotesFragment : Fragment() {
    private val notesViewModel: NotesViewModel by viewModels()
    private var binding: FragmentNotesBinding? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_notes, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        setObservers()
    }

    private fun setObservers() {
        notesViewModel.notesResponse.observe(viewLifecycleOwner) { response ->
            when (response) {
                // If data is successfully retrieved, navigate to next screen with data and hide loading view
                is NetworkResult.Success -> binding.apply {
                    response.data?.likes?.profiles?.let {
                        val adapter = LikesAdapter(it, response.data.likes.can_see_profile)
                        binding?.rvReceivedLikes?.adapter = adapter
                    }
                    if (response.data?.invites?.profiles?.isNotEmpty() == true) {
                        val profileInfo = response.data.invites.profiles[0]
                        binding?.ivProfilePicNotes?.loadImage(profileInfo.photos[0].photo)
                        binding?.tvNameAge?.text =
                            "${profileInfo.general_information.first_name}, ${profileInfo.general_information.age}"

                        if (response.data.invites.pending_invitations_count > 50)
                            binding?.tvNoNotes?.text = "Tap To Review 50+ Notes"
                        else
                            binding?.tvNoNotes?.text =
                                "Tap To Review ${response.data.invites.pending_invitations_count} Notes"
                    }

                }

                // If there is an error retrieving data, hide loading view
                is NetworkResult.Error ->
                    showToast(requireContext(), getString(R.string.some_error_occurred_please_try_again_in_sometime))

                // If data is still loading, show loading view
                is NetworkResult.Loading -> showToast(requireContext(), getString(R.string.fetching_information))
            }
        }
    }
}