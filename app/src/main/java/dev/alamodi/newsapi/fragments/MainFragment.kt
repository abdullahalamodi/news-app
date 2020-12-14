package dev.alamodi.newsapi.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.widget.ContentLoadingProgressBar
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dev.alamodi.newsapi.NewsListViewModel
import dev.alamodi.newsapi.R
import dev.alamodi.newsapi.data.models.News

class MainFragment : Fragment() {
    lateinit var newsListViewModel: NewsListViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ContentLoadingProgressBar
    private lateinit var adapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        newsListViewModel =
            ViewModelProviders.of(this).get(NewsListViewModel::class.java)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_main, container, false)
        recyclerView = view.findViewById(R.id.news_recycler_view)
        progressBar = view.findViewById(R.id.progress_circular)
        recyclerView.layoutManager = LinearLayoutManager(context)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressBar.show()
        newsListViewModel.newsLiveData.observe(
            viewLifecycleOwner,
            Observer {newsList ->
                newsList?.let {
                    updateUI(newsList)
                }
            }
        )
    }

    private fun updateUI(newsList: List<News>) {
        progressBar.hide();
        adapter = NewsAdapter(newsList)
        recyclerView.adapter = adapter
    }

    companion object {
        @JvmStatic
        fun newInstance() = MainFragment();

    }



    private class NewsHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        private val newsImg = itemView.findViewById(R.id.news_img) as ImageView
        private val newsTitle = itemView.findViewById(R.id.news_title) as TextView
        private val newsDes = itemView.findViewById(R.id.news_des) as TextView

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(news: News) {
            newsTitle.text = news.title;
            newsDes.text = news.description;
        }

        override fun onClick(p0: View?) {}
    }

    private class NewsAdapter(private val newsList: List<News>) :
        RecyclerView.Adapter<NewsHolder>() {
        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): NewsHolder {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.news_item_list, parent, false)
            return NewsHolder(view)
        }

        override fun getItemCount(): Int = newsList.size

        override fun onBindViewHolder(holder: NewsHolder, position: Int) {
            val news = newsList[position]
            holder.bind(news)
        }

    }
}