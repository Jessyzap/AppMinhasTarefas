package com.ctt.minhastarefas.adapterListas

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.ctt.minhastarefas.R
import com.ctt.minhastarefas.bottomSheets.EditarFazerBottomSheet
import com.ctt.minhastarefas.bottomSheets.VisualizarTarefaBottomSheet
import com.ctt.minhastarefas.model.Tarefa

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
            bundle.putString("POSICAO", position.toString())
            bottomSheetVisualizar.setArguments(bundle)
            bottomSheetVisualizar.show((contexto as AppCompatActivity).supportFragmentManager,"BottomSheetVisualizar")
        }
    }

    override fun getItemCount(): Int = listaTarefasFazer.size

    //fun adicionarTarefa(tarefa: Tarefa){
     //   listaTarefasFazer.add(tarefa)
    //    notifyDataSetChanged()
//    }

    fun editarTarefaFazer(titulo: String, descricao: String,posicao: Int){

        val bottomSheetEditar = EditarFazerBottomSheet()
        val bundle = Bundle()
        bundle.putString("TITULO", titulo)
        bundle.putString("DESCRICAO", descricao)
        bundle.putString("POSICAO", posicao.toString())
        bottomSheetEditar.setArguments(bundle)
        bottomSheetEditar.show((contexto as AppCompatActivity).supportFragmentManager,"")
    }

    fun substituirTarefaFazer(tituloFazer: String, descricaoFazer: String, posicaoFazer: String) {

        listaTarefasFazer.set(posicaoFazer.toInt(),Tarefa(tituloFazer,descricaoFazer))
        notifyDataSetChanged()
        //listaTarefasFazer.clear()
    }

//    fun substituirTarefaFazer(position: Int,tarefa: Tarefa) {
//
//        listaTarefasFazer.set(position,tarefa)
//        notifyDataSetChanged()
//        //listaTarefasFazer.clear()
//    }

    fun removerTarefaFazer(position: Int) {

        listaTarefasFazer.removeAt(position)
        notifyDataSetChanged()
        //listaTarefasFazer.clear()
    }
}