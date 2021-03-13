package com.ctt.minhastarefas.adapterListas

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.ctt.minhastarefas.R
import com.ctt.minhastarefas.bottomSheets.VisualizarTarefaBottomSheet
import com.ctt.minhastarefas.model.Tarefa
import com.ctt.minhastarefas.model.msgViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog

class TarefasFazerAdapter(private val listaTarefasFazer: MutableList<Tarefa>, private val contexto: Context) :
    RecyclerView.Adapter<TarefasFazerAdapter.TarefaHolder>() {


    private var model: msgViewModel? = null

    class TarefaHolder(view: View) : RecyclerView.ViewHolder(view) {

        val nome: TextView = view.findViewById(R.id.txtTarefaFazer)
        val descricao: TextView = view.findViewById(R.id.txtDescricaoTarefaFazer)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TarefaHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_tarefas_fazer, parent, false)
        return TarefaHolder(view)
    }

    override fun onBindViewHolder(holder: TarefaHolder, position: Int) {

        //val listaParaBottomSheet = mutableListOf<Tarefa>()

        holder.nome.text = listaTarefasFazer[position].nomeTarefa
        holder.descricao.text = listaTarefasFazer[position].descricaoTarefa
        holder.itemView.setOnClickListener {

            val bottomSheetVisualizar = VisualizarTarefaBottomSheet()
            bottomSheetVisualizar.show((contexto as AppCompatActivity).supportFragmentManager,"BottomSheetVisualizar")

//            listaParaBottomSheet.add(
//                Tarefa(
//                    listaTarefasFazer[position].nomeTarefa,
//                    listaTarefasFazer[position].descricaoTarefa
//                )
//            )
//            if (model != null) {
//                model!!.dadosTarefa(listaParaBottomSheet)
//            }

//            val layoutBottomSheet = LayoutInflater.from(it.context).inflate(R.layout.bottom_sheet_visualizar_tarefa, null)
//            val contexto = BottomSheetDialog(it.context)
//            contexto.setContentView(layoutBottomSheet)
//            contexto.show()
//            VisualizarTarefaBottomSheet().apply {
//                fragmentManager?.let { it1 -> show(it1, VisualizarTarefaBottomSheet.TAG) }
//
//            }
        }
    }

    override fun getItemCount(): Int = listaTarefasFazer.size
}