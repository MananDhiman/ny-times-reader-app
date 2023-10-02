package manandhiman.ny_times_reader_unofficial
// Inflate the layout for this fragment
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayoutMediator
import manandhiman.ny_times_reader_unofficial.adapters.PopularNewsRecyclerViewAdapter
import manandhiman.ny_times_reader_unofficial.adapters.SearchedArticleRecyclerViewAdapter
import manandhiman.ny_times_reader_unofficial.adapters.ViewPagerAdapter
import manandhiman.ny_times_reader_unofficial.databinding.ActivityMainBinding
import manandhiman.ny_times_reader_unofficial.model.popular.PopularNewsApiResponse
import manandhiman.ny_times_reader_unofficial.model.popular.PopularNewsSingleResult
import manandhiman.ny_times_reader_unofficial.model.search_article.Doc
import manandhiman.ny_times_reader_unofficial.model.search_article.SearchArticleApiResponse
import manandhiman.ny_times_reader_unofficial.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class MainActivity : AppCompatActivity() {

  private lateinit var binding: ActivityMainBinding
  private val tabsArray = arrayOf("Popular", "Search", "About")

  override fun onCreate(savedInstanceState: Bundle?) {

    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    // add swipe refresh
    // add save last record to local db for reading
    // add save article to local db for permanent

    val adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)

    binding.viewPager.adapter = adapter

    TabLayoutMediator(binding.tabLayout, binding.viewPager) {
        tab, position -> tab.text = tabsArray[position]
    }.attach()

  }

}
