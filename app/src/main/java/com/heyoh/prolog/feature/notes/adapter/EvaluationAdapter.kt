package com.heyoh.prolog.feature.notes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.heyoh.model.Evaluation
import com.heyoh.prolog.databinding.ItemEvaluationBinding
import com.heyoh.prolog.util.extensions.diffUtil

class EvaluationAdapter : RecyclerView.Adapter<EvaluationAdapter.ViewHolder>() {

    var list: List<Evaluation> by diffUtil(emptyList())

    inner class ViewHolder(private val binding: ItemEvaluationBinding) :
            RecyclerView.ViewHolder(binding.root) {
        fun bind(evaluation: Evaluation) {
            with(binding) {
                numberClassTextView.text = evaluation.numberClass
                evaluationNameTextView.text = evaluation.evaluationName
                evaluationDateTextView.text = evaluation.evaluationDate
                numberMarkTextView.text = evaluation.markNumber
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemEvaluationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }
}