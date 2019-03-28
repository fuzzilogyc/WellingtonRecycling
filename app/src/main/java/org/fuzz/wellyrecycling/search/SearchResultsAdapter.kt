package org.fuzz.wellyrecycling.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import org.fuzz.wellyrecycling.R
import org.fuzz.wellyrecycling.databinding.SearchItemViewBinding

class SearchResultsAdapter(private val clickListener: OnClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    interface OnClickListener {
        fun onItemClick(item: StreetInfo)
    }

    private var items: List<StreetInfo> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ItemViewHolder(clickListener, parent)
//        if (viewType == -1) {
//            LoadingViewHolder(parent)
//        } else {
//            ItemViewHolder(parent)
//        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ItemViewHolder && items.size > position) {
            holder.bind(items[position])
        }
    }

    override fun getItemCount() = items.size

    override fun getItemViewType(position: Int) = 0

    fun update(items: List<StreetInfo>) {
        this.items = items
        notifyDataSetChanged()
    }

    companion object {
        @JvmStatic
        @BindingAdapter("items")
        fun RecyclerView.bindItems(items: List<StreetInfo>) {
            val adapter = adapter as SearchResultsAdapter
            adapter.update(items)
        }
    }

    class ItemViewHolder(
        private val clickListener: OnClickListener,
        private val parent: ViewGroup,
        private val binding: SearchItemViewBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.search_item_view,
            parent,
            false
        )
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: StreetInfo) {
            binding.root.setOnClickListener {
                clickListener.onItemClick(item)
            }
            binding.item = item
        }
    }

}
