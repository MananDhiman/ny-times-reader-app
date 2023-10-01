package manandhiman.ny_times_reader_unofficial.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import manandhiman.ny_times_reader_unofficial.fragments.AboutFragment
import manandhiman.ny_times_reader_unofficial.fragments.PopularNewsFragment
import manandhiman.ny_times_reader_unofficial.fragments.SearchNewsFragment

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle):
FragmentStateAdapter(fragmentManager, lifecycle){

  override fun getItemCount(): Int = 3

  override fun createFragment(position: Int): Fragment {
    when (position) {
      0 -> return PopularNewsFragment()
      1 -> return SearchNewsFragment()
      2 -> return AboutFragment()
    }
    return PopularNewsFragment()
  }
}