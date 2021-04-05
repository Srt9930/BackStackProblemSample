package com.srt.backstackproblem

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_notification.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mViewPager = findViewById<ViewPager2>(R.id.view_pager_main_activity)
        mViewPager?.offscreenPageLimit = 2
        mViewPager?.apply {
            (getChildAt(0) as? RecyclerView)?.overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        }
        mViewPager?.isUserInputEnabled = false

        setupViewPager(mViewPager)

    }

    override fun dispatchTouchEvent(ev: MotionEvent):Boolean {
        if (currentFocus != null)
        {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
        }
        return super.dispatchTouchEvent(ev)
    }

    override fun onBackPressed() {

        if (view_pager_main_activity?.currentItem == 0)
        {
            if (view_pager_main_fragment?.currentItem == 0)
            {
                val recyclerView = findViewById<RecyclerView>(R.id.listRecyclerView)
                val appbarHome = findViewById<AppBarLayout>(R.id.appbar_home)
                val layoutManager = recyclerView?.layoutManager as LinearLayoutManager
                when {
                    layoutManager.findFirstCompletelyVisibleItemPosition() == 0 -> {
                        super.onBackPressed()
                    }
                    supportFragmentManager.backStackEntryCount != 0 -> {
                        supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
                    }
                    else -> {
                        layoutManager.scrollToPositionWithOffset(0, 0)
                        appbarHome.setExpanded(true)
                        //recyclerView.smoothScrollToPosition(0)
                    }
                }
            }
            else if (supportFragmentManager.backStackEntryCount != 0) {
                supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
            }
            else
            {
                // Otherwise, select the previous step.
                view_pager_main_fragment?.setCurrentItem(view_pager_main_fragment.currentItem - 1, false)
            }
        }
        else if (supportFragmentManager.backStackEntryCount != 0) {
            supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }
        else
        {
            // Otherwise, select the previous step.
            view_pager_main_activity?.currentItem = view_pager_main_activity.currentItem - 1
        }
    }


    private fun setupViewPager(viewPager: ViewPager2) {
        val vAdapter = MyViewPagerAdapter(this)
        vAdapter.addFragment(MainFragment())
        vAdapter.addFragment(MessengerFragmentContainer())
        viewPager.adapter = vAdapter
    }

}

internal class MyViewPagerAdapter(fragmentActivity: FragmentActivity)
    : FragmentStateAdapter(fragmentActivity)
{

    private val fragments: ArrayList<Fragment> = ArrayList()

    fun addFragment(fragment: Fragment)
    {
        fragments.add(fragment)
    }

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
//        when (position) {
//            0 -> {
//                return MainFragment()
//            }
//            1 ->{
//                return MessengerFragmentContainer()
//            }
//        }
        return fragments[position]
    }

}