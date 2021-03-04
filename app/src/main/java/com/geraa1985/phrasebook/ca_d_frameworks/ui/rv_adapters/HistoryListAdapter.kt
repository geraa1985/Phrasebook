package com.geraa1985.phrasebook.ca_d_frameworks.ui.rv_adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.geraa1985.phrasebook.databinding.RvHistoryItemBinding

class HistoryListAdapter :
    RecyclerView.Adapter<HistoryListAdapter.RecyclerItemViewHolder>() {

    private var onItemClickListener: OnItemClickListener? = null

    private var data: List<String>? = null

    fun setData(data: List<String>) {
        this.data = data
        notifyDataSetChanged()
    }

    private lateinit var binding: RvHistoryItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerItemViewHolder {
        binding = RvHistoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecyclerItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerItemViewHolder, position: Int) {
        data?.get(position)?.let { holder.bind(it) }
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

    inner class RecyclerItemViewHolder(
        private val binding: RvHistoryItemBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(word: String) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                binding.textWord.text = word

                itemView.setOnClickListener {
                    onItemClickListener?.onClick(
                        binding.textWord.text.toString()
                        )
                }
            }
        }

    }

    interface OnItemClickListener {
        fun onClick(
            word: String
        )
    }

    internal fun setOnItemClickListener(listener: OnItemClickListener) {
        onItemClickListener = listener
    }
}
