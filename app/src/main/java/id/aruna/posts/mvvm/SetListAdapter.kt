package id.aruna.posts.mvvm

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.aruna.posts.R
import id.aruna.posts.mvvm.model.PostsModel
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_main.*

class SetListAdapter : RecyclerView.Adapter<SetListAdapter.ViewHolder>() {
    private val mutableListPosts = mutableListOf<PostsModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_main, parent, false)
        )

    override fun getItemCount() = mutableListPosts.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(mutableListPosts[position])
    }

    fun updateData(mutableListPosts: MutableList<PostsModel>) {
        this.mutableListPosts.clear()
        this.mutableListPosts.addAll(mutableListPosts)
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view), LayoutContainer {
        override val containerView: View?
            get() = itemView

        fun bindItem(item: PostsModel) {
            /*Glide.with(containerView!!)
                .load(item.logo)
                .into(ivSetLogo)*/

            tvSetName.text = item.title
        }
    }
}
