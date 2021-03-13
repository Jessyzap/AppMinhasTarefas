package com.ctt.minhastarefas.adapterListas

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.ctt.minhastarefas.R
import com.ctt.minhastarefas.bottomSheets.FinalizadaTarefaBottomSheet
import com.ctt.minhastarefas.bottomSheets.ProgressoTarefaBottomSheet
import com.ctt.minhastarefas.model.Tarefa

class TarefasFeitasAdapter(private val listaTarefasFeitas: MutableList<Tarefa>,private val contexto: Context) :
    RecyclerView.Adapter<TarefasFeitasAdapter.TarefaFeitaHolder>() {

    private val listaTarefasFeitasRemover = mutableListOf<Tarefa>()

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
            bottomSheetFinalizada.show((contexto as AppCompatActivity).supportFragmentManager, "BottomSheetProgresso")
        }
    }

    override fun getItemCount(): Int = listaTarefasFeitas.size

    fun adicionarTarefa(tarefa: Tarefa) {
        listaTarefasFeitas.add(tarefa)
        notifyDataSetChanged()
    }

    fun removerTarefa() {
        listaTarefasFeitas.removeAll(listaTarefasFeitasRemover)
        notifyDataSetChanged()
        //ListaTarefasActivity.listaCompanion.removeAll(listaTarefasFazerRemover)
        listaTarefasFeitasRemover.clear()
    }
}