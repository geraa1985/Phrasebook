package com.geraa1985.phrasebook.ca_d_frameworks.ui.rv_adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.geraa1985.phrasebook.ca_a_entities.DataModel
import com.geraa1985.phrasebook.databinding.RvItemMeaningsBinding

class MeaningsListAdapter(private var data: List<DataModel>) :
    RecyclerView.Adapter<MeaningsListAdapter.RecyclerItemViewHolder>() {

    fun setData(data: List<DataModel>) {
        this.data = data
        notifyDataSetChanged()
    }

    private lateinit var binding: RvItemMeaningsBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerItemViewHolder {
        binding = RvItemMeaningsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecyclerItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerItemViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class RecyclerItemViewHolder(
        private val binding: RvItemMeaningsBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(data: DataModel) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                binding.textMeaning.text = data.text
                binding.textTranslation.text = data.meanings?.get(0)?.translation?.translation
            }
        }
    }
}
