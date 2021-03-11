package com.geraa1985.phrasebook.ca_d_frameworks.ui.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.geraa1985.phrasebook.databinding.DialogSearchBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class SearchDialogFragment: BottomSheetDialogFragment() {

    private lateinit var binding: DialogSearchBinding

    private var onSearchClickListener: OnSearchClickListener? = null

    private val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            binding.buttonSearch.isEnabled = binding.textForSerach.text?.isNotEmpty() ?: false
        }

        override fun afterTextChanged(s: Editable?) {}
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogSearchBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onStart() {
        super.onStart()

        binding.textForSerach.addTextChangedListener(textWatcher)
        binding.buttonSearch.setOnClickListener {
            onSearchClickListener?.let {
                it.onClick(binding.textForSerach.text.toString())
                dismiss()
            }
        }

        binding.textForSerach.setOnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                onSearchClickListener?.let {
                    it.onClick(binding.textForSerach.text.toString())
                    dismiss()
                    return@setOnKeyListener true
                }
            }
            return@setOnKeyListener false
        }
    }

    interface OnSearchClickListener {
        fun onClick(searchWord: String)
    }

    internal fun setOnSearchClickListener(listener: OnSearchClickListener) {
        onSearchClickListener = listener
    }

    override fun onDestroyView() {
        onSearchClickListener = null
        super.onDestroyView()
    }
}