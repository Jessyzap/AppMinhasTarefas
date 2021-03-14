package com.ctt.minhastarefas.adapterListas

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.ctt.minhastarefas.R
import com.ctt.minhastarefas.bottomSheets.VisualizarTarefaBottomSheet
import com.ctt.minhastarefas.model.Tarefa
import com.ctt.minhastarefas.model.msgViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.lang.reflect.Array.get

class TarefasFazerAdapter(private val listaTarefasFazer: MutableList<Tarefa>, private val contexto: Context) :
    RecyclerView.Adapter<TarefasFazerAdapter.TarefaHolder>() {

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
        holder.itemView.setOnClickListener {

            val bottomSheetVisualizar = VisualizarTarefaBottomSheet()
            val bundle = Bundle()
            bundle.putString("TITULO", listaTarefasFazer[position].nomeTarefa)
            bundle.putString("DESCRICAO", listaTarefasFazer[position].descricaoTarefa)
            bottomSheetVisualizar.setArguments(bundle)
            bottomSheetVisualizar.show((contexto as AppCompatActivity).supportFragmentManager,"BottomSheetVisualizar")
        }
    }

    override fun getItemCount(): Int = listaTarefasFazer.size
}