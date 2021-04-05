package com.srt.backstackproblem

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import kotlinx.android.synthetic.main.fragment_main.view.*

class MainFragment : Fragment() {

    private  var prevMenuItem: MenuItem? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_main, container, false)

        val viewPager2 = view.findViewById<ViewPager2>(R.id.view_pager_main_fragment)
       // viewPager2.offscreenPageLimit = 3
        viewPager2.isUserInputEnabled = false
        viewPager2?.apply {
            (getChildAt(0) as? RecyclerView)?.overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        }

        viewPager2?.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }
            override fun onPageSelected(position: Int) {
                prevMenuItem?.isChecked = false
                view?.bottomNavView?.menu?.getItem(position)?.isChecked = true
                prevMenuItem = view?.bottomNavView?.menu?.getItem(position)
            }
            override fun onPageScrollStateChanged(state: Int) {
            }
        })

        view?.bottomNavView?.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.homeFragment -> viewPager2.setCurrentItem(0, false)
                R.id.searchFragment -> viewPager2.setCurrentItem(1, false)
                R.id.notificationsFragment -> viewPager2.setCurrentItem(2, false)
            }
            false
        }

        setupViewPager(viewPager2)

        return view
    }

    private fun setupViewPager(viewPager: ViewPager2) {
        val vAdapter = activity?.let { ViewPagerAdapter(it) }
        vAdapter?.addFragment(HomeFragmentContainer())
        vAdapter?.addFragment(SearchFragmentContainer())
        vAdapter?.addFragment(NotificationFragmentContainer())
        viewPager.adapter = vAdapter
    }
}

internal class ViewPagerAdapter(fragmentActivity: FragmentActivity)
    : FragmentStateAdapter(fragmentActivity)
{
    private val fragments: ArrayList<Fragment> = ArrayList()

    fun addFragment(fragment: Fragment)
    {
        fragments.add(fragment)
    }

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }

}