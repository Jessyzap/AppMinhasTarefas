package com.ctt.minhastarefas.adapterListas

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.ctt.minhastarefas.R
import com.ctt.minhastarefas.bottomSheets.ProgressoTarefaBottomSheet
import com.ctt.minhastarefas.bottomSheets.VisualizarTarefaBottomSheet
import com.ctt.minhastarefas.model.Tarefa

class TarefasProgressoAdapter(private val listaTarefasProgresso: MutableList<Tarefa>, private val contexto: Context) :
    RecyclerView.Adapter<TarefasProgressoAdapter.TarefaProgressoHolder>() {

    private val listaTarefasProgressoRemover = mutableListOf<Tarefa>()

    class TarefaProgressoHolder(view: View) : RecyclerView.ViewHolder(view) {

        val nome: TextView = view.findViewById(R.id.txtTarefaProgresso)
        val descricao: TextView = view.findViewById(R.id.txtDescricaoTarefaProgresso)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TarefaProgressoHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_tarefas_progresso, parent, false)
        return TarefaProgressoHolder(view)
    }

    override fun onBindViewHolder(holder: TarefaProgressoHolder, position: Int) {

        holder.nome.text = listaTarefasProgresso[position].nomeTarefa
        holder.descricao.text = listaTarefasProgresso[position].descricaoTarefa
        holder.itemView.setOnClickListener {

            val bottomSheetProgresso = ProgressoTarefaBottomSheet()
            bottomSheetProgresso.show((contexto as AppCompatActivity).supportFragmentManager, "BottomSheetProgresso")

        }
    }


    override fun getItemCount(): Int = listaTarefasProgresso.size

    fun adicionarTarefa(tarefa: Tarefa) {
        listaTarefasProgresso.add(tarefa)
        notifyDataSetChanged()
    }
//
//    fun removerTarefa() {
//        listaTarefasProgresso.removeAll(listaTarefasProgressoRemover)
//        notifyDataSetChanged()
//        //ListaTarefasActivity.listaCompanion.removeAll(listaTarefasFazerRemover)
//        listaTarefasProgressoRemover.clear()
//    }

}