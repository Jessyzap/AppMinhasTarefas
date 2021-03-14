package com.ctt.minhastarefas.bottomSheets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.ctt.minhastarefas.R
import com.ctt.minhastarefas.adapterListas.TarefasFazerAdapter
import com.ctt.minhastarefas.fragments.FazerFragment.Companion.listaTarefasFazer
import com.ctt.minhastarefas.fragments.ProgressoFragment.Companion.listaTarefasProgresso
import com.ctt.minhastarefas.model.Tarefa
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class VisualizarTarefaBottomSheet() : BottomSheetDialogFragment() {

    private lateinit var botaoIniciar: Button

    companion object {
        const val TAG = "VisualizarTarefaBottomSheet"
    }
    //Editar tarefa
    //Excluir tarefa
    //Iniciar tarefa

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(BottomSheetDialogFragment.STYLE_NORMAL, R.style.MyBottomSheetDialogTheme)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val contextoVisualizar = inflater.inflate(R.layout.bottom_sheet_visualizar_tarefa, container, false)
        botaoIniciar = contextoVisualizar.findViewById(R.id.btnIniciarTarefa)
        return contextoVisualizar
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tituloTarefa = view.findViewById<TextView>(R.id.txtTituloVisualizar)
        val descricaoTarefa = view.findViewById<TextView>(R.id.txtDescricaoVisualizar)

        val tituloTarefaRecebido = getArguments()?.getString("TITULO")
        val descricaoTarefaRecebida = getArguments()?.getString("DESCRICAO")

        tituloTarefa.text = tituloTarefaRecebido
        descricaoTarefa.text = descricaoTarefaRecebida

        // adicionar na lista progresso
        botaoIniciar.setOnClickListener {
            listaTarefasProgresso.add(Tarefa(tituloTarefa.text as String, descricaoTarefa.text as String))
        }
    }
}