package com.medhdj.features.highlights.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.medhdj.core.features.R
import javax.inject.Inject

typealias HighLightsItemClickListener = (HighlightsItemView) -> Unit

class HighlightsListAdapter @Inject constructor() :
    ListAdapter<HighlightsItemView, HighlightsListAdapter.HighlightsItemViewHolder>(DIFF_LIST) {

    var itemClickListener: HighLightsItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HighlightsItemViewHolder {
        return HighlightsItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.highlight_list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: HighlightsItemViewHolder, position: Int) {
        holder.bind(getItem(position), itemClickListener)
    }

    class HighlightsItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val titleTextView: TextView = view.findViewById(R.id.title)

        fun bind(
            highlight: HighlightsItemView,
            itemClickListener: HighLightsItemClickListener?
        ) {
            titleTextView.text = highlight.title

            itemView.setOnClickListener {
                itemClickListener?.let {
                    it(highlight)
                }
            }
        }
    }

    companion object {
        private val DIFF_LIST =
            object : DiffUtil.ItemCallback<HighlightsItemView>() {
                override fun areContentsTheSame(
                    item1: HighlightsItemView,
                    item2: HighlightsItemView
                ): Boolean =
                    item1.title == item2.title && item1.title == item2.title

                override fun areItemsTheSame(
                    item1: HighlightsItemView,
                    item2: HighlightsItemView
                ): Boolean =
                    item1 == item2
            }
    }
}


