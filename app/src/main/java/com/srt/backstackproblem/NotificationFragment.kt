package com.srt.backstackproblem

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_profile.view.go_to_post

class NotificationFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_notification, container, false)

        view?.go_to_post?.setOnClickListener {
            val containerId = (view?.parent as ViewGroup).id
            activity?.supportFragmentManager?.beginTransaction()?.add(containerId, PostFragment())?.addToBackStack(null)?.commit()
        }

        return view
    }

}