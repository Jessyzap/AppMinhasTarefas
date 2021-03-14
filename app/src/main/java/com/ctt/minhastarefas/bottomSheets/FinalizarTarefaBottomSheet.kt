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

class FinalizarTarefaBottomSheet() : BottomSheetDialogFragment() {

    private lateinit var botaoFinalizar: Button

    companion object {
        const val TAG = "FinalizarTarefaBottomSheet"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(BottomSheetDialogFragment.STYLE_NORMAL, R.style.MyBottomSheetDialogTheme)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val contextoFinalizar = inflater.inflate(R.layout.bottom_sheet_finalizar_tarefa, container, false)
        botaoFinalizar = contextoFinalizar.findViewById(R.id.btnFinalizarTarefa)
        return contextoFinalizar
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tituloTarefa = view.findViewById<TextView>(R.id.txtTituloProgresso)
        val descricaoTarefa = view.findViewById<TextView>(R.id.txtDescricaoProgresso)

        val tituloTarefaRecebido = getArguments()?.getString("TITULO")
        val descricaoTarefaRecebida = getArguments()?.getString("DESCRICAO")

        tituloTarefa.text = tituloTarefaRecebido
        descricaoTarefa.text = descricaoTarefaRecebida

        // adicionar na lista feitas
        botaoFinalizar.setOnClickListener {
            listaTarefasFeitas.add(Tarefa(tituloTarefa.text as String,descricaoTarefa.text as String))
        }
    }
}