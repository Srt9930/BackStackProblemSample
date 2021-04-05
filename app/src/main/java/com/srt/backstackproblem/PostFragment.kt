package com.srt.backstackproblem

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_post.view.*

class PostFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_post, container, false)

        view?.back_button_from_post?.setOnClickListener {
            activity?.onBackPressed()
        }

        view?.go_to_profile?.setOnClickListener {
            val containerId = (view?.parent as ViewGroup).id
            activity?.supportFragmentManager?.beginTransaction()?.add(containerId, ProfileFragment())?.addToBackStack(null)?.commit()
        }

        return view
    }

}