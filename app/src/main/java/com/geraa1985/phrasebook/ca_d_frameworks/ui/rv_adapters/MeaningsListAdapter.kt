package com.geraa1985.phrasebook.ca_d_frameworks.ui.rv_adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.geraa1985.model.DataModel
import com.geraa1985.phrasebook.databinding.RvItemMeaningsBinding

class MeaningsListAdapter :
    RecyclerView.Adapter<MeaningsListAdapter.RecyclerItemViewHolder>() {

    private var onItemClickListener: OnItemClickListener? = null

    private var data: List<com.geraa1985.model.DataModel>? = null

    fun setData(data: List<com.geraa1985.model.DataModel>) {
        this.data = data
        notifyDataSetChanged()
    }

    private lateinit var binding: RvItemMeaningsBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerItemViewHolder {
        binding = RvItemMeaningsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecyclerItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerItemViewHolder, position: Int) {
        data?.get(position)?.let { holder.bind(it) }
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

    inner class RecyclerItemViewHolder(
        private val binding: RvItemMeaningsBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(data: com.geraa1985.model.DataModel) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                binding.textMeaning.text = data.text
                binding.textTranslation.text = data.meanings?.get(0)?.translation?.translation

                itemView.setOnClickListener {
                    onItemClickListener?.onClick(
                        binding.textMeaning.text.toString(),
                        data.meanings?.get(0)?.translation?.translation,
                        "https:" + data.meanings?.get(0)?.imageUrl
                        )
                }
            }
        }

    }

    interface OnItemClickListener {
        fun onClick(
            word: String,
            translation: String?,
            imgUrl: String
        )
    }

    internal fun setOnItemClickListener(listener: OnItemClickListener) {
        onItemClickListener = listener
    }
}
