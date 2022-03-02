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
import com.ctt.minhastarefas.model.TarefaEmProgresso
import com.ctt.minhastarefas.ui.bottomSheets.FinalizarTarefaBottomSheet

class TarefasProgressoAdapter(val context: Context?) :
    ListAdapter<TarefaEmProgresso, TarefasProgressoAdapter.TarefaViewHolder>(TASKS_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TarefaViewHolder {
        return TarefaViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: TarefaViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current, position, context)
    }

    class TarefaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nomeDaTarefa: TextView = itemView.findViewById(R.id.txtTarefaProgresso)
        private val descricaoDaTarefa: TextView =
            itemView.findViewById(R.id.txtDescricaoTarefaProgresso)

        fun bind(tarefa: TarefaEmProgresso, posicao: Int, context: Context?) {
            nomeDaTarefa.text = tarefa.nomeTarefa
            descricaoDaTarefa.text = tarefa.descricaoTarefa
            itemView.setOnClickListener {
                val bottomSheetVisualizar = FinalizarTarefaBottomSheet()
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
                    .inflate(R.layout.item_tarefas_progresso, parent, false)
                return TarefaViewHolder(view)
            }
        }
    }

    companion object {
        private val TASKS_COMPARATOR = object : DiffUtil.ItemCallback<TarefaEmProgresso>() {
            override fun areItemsTheSame(
                oldItem: TarefaEmProgresso,
                newItem: TarefaEmProgresso
            ): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(
                oldItem: TarefaEmProgresso,
                newItem: TarefaEmProgresso
            ): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}