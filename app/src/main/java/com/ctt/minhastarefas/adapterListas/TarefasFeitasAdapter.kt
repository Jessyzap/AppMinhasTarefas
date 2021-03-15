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
import com.ctt.minhastarefas.bottomSheets.FinalizadaTarefaBottomSheet
import com.ctt.minhastarefas.bottomSheets.VisualizarTarefaBottomSheet
import com.ctt.minhastarefas.model.Tarefa

class TarefasFeitasAdapter(private val listaTarefasFeitas: MutableList<Tarefa>, private val contexto: Context) :
    RecyclerView.Adapter<TarefasFeitasAdapter.TarefaFeitaHolder>() {


    class TarefaFeitaHolder(view: View) : RecyclerView.ViewHolder(view) {

        val nome: TextView = view.findViewById(R.id.txtTarefaFeita)
        val descricao: TextView = view.findViewById(R.id.txtDescricaoTarefaFeita)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TarefaFeitaHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_tarefas_feitas, parent, false)
        return TarefaFeitaHolder(view)
    }

    override fun onBindViewHolder(holder: TarefaFeitaHolder, position: Int) {

        holder.nome.text = listaTarefasFeitas[position].nomeTarefa
        holder.descricao.text = listaTarefasFeitas[position].descricaoTarefa
        holder.itemView.setOnClickListener {

            val bottomSheetFinalizada = FinalizadaTarefaBottomSheet()
            val bundle = Bundle()
            bundle.putString("TITULO", listaTarefasFeitas[position].nomeTarefa)
            bundle.putString("DESCRICAO", listaTarefasFeitas[position].descricaoTarefa)
            bottomSheetFinalizada.setArguments(bundle)
            bottomSheetFinalizada.show((contexto as AppCompatActivity).supportFragmentManager,"FinalizadaTarefaBottomSheet"
            )
        }
    }

    override fun getItemCount(): Int = listaTarefasFeitas.size


    fun removerTarefaFeitas(position: Int) {

        listaTarefasFeitas.removeAt(position + 1)
        notifyDataSetChanged()
        //ListaTarefasActivity.listaCompanion.removeAll(listaTarefasFazerRemover)
        listaTarefasFeitas.clear()
    }
}