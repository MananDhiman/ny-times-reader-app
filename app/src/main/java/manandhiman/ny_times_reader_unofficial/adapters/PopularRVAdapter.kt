package manandhiman.ny_times_reader_unofficial.adapters

import android.content.Intent
import android.net.Uri
import android.opengl.Visibility
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import manandhiman.ny_times_reader_unofficial.R
import manandhiman.ny_times_reader_unofficial.adapters.PopularRVAdapter.ViewHolder
import manandhiman.ny_times_reader_unofficial.model.popular.PopularNewsSingleResult

class PopularRVAdapter(private val list: List<PopularNewsSingleResult>): RecyclerView.Adapter<ViewHolder>() {

  class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val tvAbstract: TextView = itemView.findViewById(R.id.textViewAbstract)
    val tvTitle: TextView = itemView.findViewById(R.id.textViewTitle)
    val tvMisc: TextView = itemView.findViewById(R.id.tvMisc)

    val imageView: ImageView = itemView.findViewById(R.id.imageView)

    val layout: LinearLayout = itemView.findViewById(R.id.layout)
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val view = LayoutInflater.from(parent.context)
      .inflate(R.layout.news_item, parent, false)

    return ViewHolder(view)
  }

  override fun getItemCount(): Int = list.size

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val singlePost = list[position]

    val url = singlePost.url
    val date = singlePost.publishedDate
    val title = singlePost.title
    val abstractTitle = singlePost.abstract

    holder.tvTitle.text = title
    holder.tvAbstract.text = abstractTitle
    holder.tvMisc.text = "Article written on $date"

    val context = holder.layout.context

    holder.imageView.visibility = View.VISIBLE

    try {
      val imageUrl = singlePost.media[0].mediaMetadata[2].url
      Glide.with(context)
        .load(imageUrl)
        .into(holder.imageView)
    } catch (_: Exception) {}

    holder.layout.setOnClickListener {
      val openURL = Intent(Intent.ACTION_VIEW)
      openURL.data = Uri.parse(url)

      startActivity(context, openURL, null)
    }

  }
}