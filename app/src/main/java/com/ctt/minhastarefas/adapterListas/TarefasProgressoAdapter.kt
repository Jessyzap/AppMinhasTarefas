package com.ctt.minhastarefas.adapterListas

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.ctt.minhastarefas.R
import com.ctt.minhastarefas.ui.bottomSheets.EditarProgressoBottomSheet
import com.ctt.minhastarefas.ui.bottomSheets.FinalizarTarefaBottomSheet
import com.ctt.minhastarefas.model.Tarefa

class TarefasProgressoAdapter(private val listaTarefasProgresso: MutableList<Tarefa>, private val contexto: Context) :
    RecyclerView.Adapter<TarefasProgressoAdapter.TarefaProgressoHolder>() {


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

            val bottomSheetProgresso = FinalizarTarefaBottomSheet()
            val bundle = Bundle()
            bundle.putString("TITULO", listaTarefasProgresso[position].nomeTarefa)
            bundle.putString("DESCRICAO", listaTarefasProgresso[position].descricaoTarefa)
            bundle.putString("POSICAO", position.toString())
            bottomSheetProgresso.setArguments(bundle)
            bottomSheetProgresso.show((contexto as AppCompatActivity).supportFragmentManager, "BottomSheetProgresso")
        }
    }

    override fun getItemCount(): Int = listaTarefasProgresso.size


    fun editarTarefaProgresso(titulo: String, descricao: String,posicao: Int){

        val bottomSheetEditar = EditarProgressoBottomSheet()
        val bundle = Bundle()
        bundle.putString("TITULO", titulo)
        bundle.putString("DESCRICAO", descricao)
        bundle.putString("POSICAO", posicao.toString())
        bottomSheetEditar.setArguments(bundle)
        bottomSheetEditar.show((contexto as AppCompatActivity).supportFragmentManager,"")
    }

    fun substituirTarefaProgresso(tituloProgresso: String, descricaoProgresso: String, posicaoProgresso: String) {
        listaTarefasProgresso.set(posicaoProgresso.toInt(),Tarefa(tituloProgresso,descricaoProgresso))
        notifyDataSetChanged()
    }

    fun adicionarTarefaProgresso(tarefa: Tarefa) {
        listaTarefasProgresso.add(tarefa)
        notifyDataSetChanged()
    }

    fun removerTarefaProgresso(position: Int) {
        listaTarefasProgresso.removeAt(position)
        notifyDataSetChanged()
    }
}