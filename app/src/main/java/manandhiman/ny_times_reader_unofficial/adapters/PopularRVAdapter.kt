package manandhiman.ny_times_reader_unofficial.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import manandhiman.ny_times_reader_unofficial.R
import manandhiman.ny_times_reader_unofficial.adapters.PopularRVAdapter.ViewHolder
import manandhiman.ny_times_reader_unofficial.model.popular.PopularNewsSingleResult

class PopularRVAdapter(private val list: List<PopularNewsSingleResult>): RecyclerView.Adapter<ViewHolder>() {

  class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val tvAbstract: TextView = itemView.findViewById(R.id.textViewAbstract)
    val tvTitle: TextView = itemView.findViewById(R.id.textViewTitle)
    val tvMisc: TextView = itemView.findViewById(R.id.tvMisc)

//    val layout: LinearLayout = itemView.findViewById(R.id.layout)
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val view = LayoutInflater.from(parent.context)
      .inflate(R.layout.news_item, parent, false)

    return ViewHolder(view)
  }

  override fun getItemCount(): Int = list.size

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val singlePost = list[position]

//    val postId =
//      val url = singlePost.url
      val date = singlePost.publishedDate
      val title = singlePost.title
      val abstractTitle = singlePost.abstract

      holder.tvTitle.text = title
      holder.tvAbstract.text = abstractTitle
      holder.tvMisc.text = "Article written on $date"
  }
}