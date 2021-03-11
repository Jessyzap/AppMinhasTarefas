package com.ctt.minhastarefas.adapterListas

import android.annotation.SuppressLint
import android.app.Activity
import android.app.PendingIntent.getActivity
import android.content.Context
import android.content.ContextWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.graphics.drawable.DrawableCompat.unwrap
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.ctt.minhastarefas.R
import com.ctt.minhastarefas.bottomSheets.VisualizarTarefaBottomSheet
import com.ctt.minhastarefas.model.Tarefa
import com.google.android.material.bottomsheet.BottomSheetBehavior
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.transition.FragmentTransitionSupport
import com.ctt.minhastarefas.PrincipalActivity
import com.ctt.minhastarefas.fragments.FazerFragment
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.internal.ContextUtils.getActivity
import java.security.AccessController.getContext

class TarefasFazerAdapter(private val listaTarefasFazer: MutableList<Tarefa>) :
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

            val layoutBottomSheet = LayoutInflater.from(it.context).inflate(R.layout.bottom_sheet_visualizar_tarefa,null)
            val contexto = BottomSheetDialog(it.context)
            contexto.setContentView(layoutBottomSheet)
            contexto.show()
        }
    }

    override fun getItemCount(): Int = listaTarefasFazer.size
}