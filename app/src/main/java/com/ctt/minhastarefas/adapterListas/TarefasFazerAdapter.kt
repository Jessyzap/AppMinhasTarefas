package com.ctt.minhastarefas.adapterListas

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ctt.minhastarefas.R
import com.ctt.minhastarefas.model.Tarefa

class TarefasFazerAdapter(private val listaTarefasFazer: MutableList<Tarefa>) :
    RecyclerView.Adapter<TarefasFazerAdapter.TarefaHolder>() {

    private val listaTarefasFazerRemover = mutableListOf<Tarefa>()

    class TarefaHolder(view: View) : RecyclerView.ViewHolder(view) {

        val nome: TextView = view.findViewById(R.id.txtTarefaFazer)
        val descricao: TextView = view.findViewById(R.id.txtDescricaoTarefaFazer)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TarefaHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_tarefas_fazer, parent, false)
        return TarefaHolder(view)
    }

    override fun onBindViewHolder(holder: TarefaHolder, position: Int) {

        holder.nome.text = listaTarefasFazer[position].nomeTarefa
        holder.descricao.text = listaTarefasFazer[position].descricaoTarefa
    }

    override fun getItemCount(): Int = listaTarefasFazer.size

    fun adicionarTarefa(tarefa: Tarefa) {
        listaTarefasFazer.add(tarefa)
        notifyDataSetChanged()
    }

    fun removerTarefa() {
        listaTarefasFazer.removeAll(listaTarefasFazerRemover)
        notifyDataSetChanged()
        //ListaTarefasActivity.listaCompanion.removeAll(listaTarefasFazerRemover)
        listaTarefasFazerRemover.clear()
    }
}