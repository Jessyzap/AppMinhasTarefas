package com.ctt.minhastarefas.adapterListas

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ctt.minhastarefas.R
import com.ctt.minhastarefas.model.TarefaFeita
import com.ctt.minhastarefas.ui.bottomSheets.FinalizadaTarefaBottomSheet

class TarefasFeitasAdapter(val context: Context?) :
    ListAdapter<TarefaFeita, TarefasFeitasAdapter.TarefaViewHolder>(TASKS_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TarefaViewHolder {
        return TarefaViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: TarefaViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current, position, context)
    }

    class TarefaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nomeDaTarefa: TextView = itemView.findViewById(R.id.txtTarefaFeita)
        private val descricaoDaTarefa: TextView =
            itemView.findViewById(R.id.txtDescricaoTarefaFeita)

        fun bind(tarefa: TarefaFeita, posicao: Int, context: Context?) {
            nomeDaTarefa.text = tarefa.nomeTarefa
            descricaoDaTarefa.text = tarefa.descricaoTarefa
            itemView.setOnClickListener {
                val bottomSheetVisualizar = FinalizadaTarefaBottomSheet()
                val bundle = Bundle()
                bundle.putString("TITULO", tarefa.nomeTarefa)
                bundle.putString("DESCRICAO", tarefa.descricaoTarefa)
                bundle.putInt("POSICAO", posicao)
                bundle.putInt("ID", tarefa.id)
                bottomSheetVisualizar.arguments = bundle
                bottomSheetVisualizar.show(
                    (context as AppCompatActivity).supportFragmentManager,
                    ""
                )
            }
        }

        companion object {
            fun create(parent: ViewGroup): TarefaViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_tarefas_feitas, parent, false)
                return TarefaViewHolder(view)
            }
        }
    }

    companion object {
        private val TASKS_COMPARATOR = object : DiffUtil.ItemCallback<TarefaFeita>() {
            override fun areItemsTheSame(
                oldItem: TarefaFeita,
                newItem: TarefaFeita
            ): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(
                oldItem: TarefaFeita,
                newItem: TarefaFeita
            ): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}