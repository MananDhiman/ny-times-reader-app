package manandhiman.ny_times_reader_unofficial
// Inflate the layout for this fragment
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayoutMediator
import manandhiman.ny_times_reader_unofficial.adapters.ViewPagerAdapter
import manandhiman.ny_times_reader_unofficial.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

  private lateinit var binding: ActivityMainBinding
  private val tabsArray = arrayOf("Popular", "Search", "About")

  override fun onCreate(savedInstanceState: Bundle?) {

    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    val adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)

    binding.viewPager.adapter = adapter

    TabLayoutMediator(binding.tabLayout, binding.viewPager) {
        tab, position -> tab.text = tabsArray[position]
    }.attach()

  }

}
