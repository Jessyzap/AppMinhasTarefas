package com.ctt.minhastarefas.bottomSheets

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.ctt.minhastarefas.R
import com.ctt.minhastarefas.fragments.FazerFragment
import com.ctt.minhastarefas.fragments.FeitasFragment.Companion.listaTarefasFeitas
import com.ctt.minhastarefas.fragments.ProgressoFragment
import com.ctt.minhastarefas.fragments.ProgressoFragment.Companion.listaTarefasProgresso
import com.ctt.minhastarefas.model.Tarefa
import com.ctt.minhastarefas.model.msgViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ProgressoTarefaBottomSheet() : BottomSheetDialogFragment() {

    private lateinit var botaoFinalizar: Button

    companion object {
        const val TAG = "FinalizarTarefaBottomSheet"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val contextoFinalizar = inflater.inflate(R.layout.bottom_sheet_finalizar_tarefa, container, false)
        botaoFinalizar = contextoFinalizar.findViewById(R.id.btnFinalizarTarefa)

        return contextoFinalizar
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val model = ViewModelProviders.of(activity!!).get(msgViewModel::class.java)

        val tituloTarefa = view.findViewById<TextView>(R.id.txtTituloProgresso)
        val descricaoTarefa = view.findViewById<TextView>(R.id.txtDescricaoProgresso)

        // colocar position(adapter e afins)
        model.dados.observe(viewLifecycleOwner,
            { t ->
                tituloTarefa.text = t.nomeTarefa
                descricaoTarefa.text = t.descricaoTarefa
            })

        // adicionar na lista feitas
        botaoFinalizar.setOnClickListener {
            Toast.makeText(context, "vc clicou", Toast.LENGTH_SHORT).show()
            //model!!.dadosTarefa(Tarefa(tituloTarefa.toString(),descricaoTarefa.toString()))

            listaTarefasFeitas.add(Tarefa(tituloTarefa.text as String,descricaoTarefa.text as String))
            //ProgressoFragment.listaTarefasProgresso.add(Tarefa("oi","teste oi"))
        }
    }
}