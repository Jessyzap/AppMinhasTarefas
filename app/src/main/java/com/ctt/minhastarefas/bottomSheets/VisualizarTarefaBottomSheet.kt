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
import com.ctt.minhastarefas.model.msgViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class VisualizarTarefaBottomSheet() : BottomSheetDialogFragment() {

    private lateinit var botaoIniciar: Button

    companion object {
        const val TAG = "VisualizarTarefaBottomSheet"
    }

    //Editar tarefa
    //Excluir tarefa
    //Iniciar tarefa  val botaoIniciarTarefa = view.findViewById<Button>(R.id.btnIniciarTarefa)


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val contextoVisualizar = inflater.inflate(R.layout.bottom_sheet_visualizar_tarefa, container, false)
        botaoIniciar = contextoVisualizar.findViewById(R.id.btnIniciarTarefa)

        return contextoVisualizar
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val model = ViewModelProviders.of(activity!!).get(msgViewModel::class.java)

//        val btnIniciarTarefa = view.findViewById<TextView>(R.id.btnIniciarTarefa)
//        val btnEditarTarefa = view.findViewById<TextView>(R.id.imgEditar)
//        val btnTeste = view.findViewById<TextView>(R.id.imgEditar)

        val tituloTarefa = view.findViewById<TextView>(R.id.txtTituloVisualizar)
        val descricaoTarefa = view.findViewById<TextView>(R.id.txtDescricaoVisualizar)


        model.dados.observe(viewLifecycleOwner,
            { t ->
                tituloTarefa.text = t.nomeTarefa
                descricaoTarefa.text = t.descricaoTarefa
            })

//        tituloTarefa.text = "Teste1"
//        descricaoTarefa.text = "Teste2"

        botaoIniciar.setOnClickListener {
            Toast.makeText(context, "vc clicou", Toast.LENGTH_SHORT).show()
        }
    }
}