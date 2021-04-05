package com.srt.backstackproblem

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_home, container, false)

        view?.go_to_chat_btn?.setOnClickListener {
            val viewPagerActivity = activity?.findViewById<ViewPager2>(R.id.view_pager_main_activity)
            viewPagerActivity?.currentItem = 1
        }

        view?.go_to_profile_btn?.setOnClickListener {
            val containerId = (view.parent as ViewGroup).id
            activity?.supportFragmentManager?.beginTransaction()?.add(containerId, ProfileFragment())?.addToBackStack(null)?.commit()
        }


        //Fake work

        val recyclerView = view?.findViewById(R.id.listRecyclerView) as RecyclerView
        val linearLayoutManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = linearLayoutManager
        val data = ArrayList<Data>()


            for(num in 1..50) {
                data.add(Data(num))
            }


        val adapter = CustomAdapter(data)
        recyclerView.adapter = adapter

        return view
    }

}

//Fake work

class CustomAdapter(private val dataList: ArrayList<Data>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.an_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(dataList[position])
    }
    override fun getItemCount(): Int {
        return dataList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(data: Data) {
            val textView = itemView.findViewById(R.id.theTextView) as TextView
            textView.text = data.number.toString()
        }
    }
}

data class Data(val number: Int)
