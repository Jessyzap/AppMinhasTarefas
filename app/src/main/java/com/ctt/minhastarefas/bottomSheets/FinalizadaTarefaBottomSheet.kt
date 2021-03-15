package com.ctt.minhastarefas.bottomSheets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.ctt.minhastarefas.R
import com.ctt.minhastarefas.adapterListas.TarefasFeitasAdapter
import com.ctt.minhastarefas.fragments.FeitasFragment.Companion.listaTarefasFeitas
import com.ctt.minhastarefas.model.Tarefa
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class FinalizadaTarefaBottomSheet() : BottomSheetDialogFragment() {

    private lateinit var botaoExcluir: TextView

    companion object {
        const val TAG = "FinalizadaTarefaBottomSheet"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(BottomSheetDialogFragment.STYLE_NORMAL, R.style.MyBottomSheetDialogTheme)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val contextoFinalizada = inflater.inflate(R.layout.bottom_sheet_tarefa_finalizada, container, false)
        botaoExcluir = contextoFinalizada.findViewById(R.id.btnExcluirTarefa)
        return contextoFinalizada
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tituloTarefa = view.findViewById<TextView>(R.id.txtTituloExcluir)
        val descricaoTarefa = view.findViewById<TextView>(R.id.txtDescricaoExcluir)

        val tituloTarefaRecebido = getArguments()?.getString("TITULO")
        val descricaoTarefaRecebida = getArguments()?.getString("DESCRICAO")

        tituloTarefa.text = tituloTarefaRecebido
        descricaoTarefa.text = descricaoTarefaRecebida

        botaoExcluir.setOnClickListener {

            context?.let { it1 -> TarefasFeitasAdapter(listaTarefasFeitas, it1) }?.removerTarefaFeitas(
                listaTarefasFeitas.indexOf(
                    Tarefa(
                        tituloTarefa.text as String,
                        descricaoTarefa.text as String
                    )
                )
            )
            Toast.makeText(context, "clicou", Toast.LENGTH_SHORT).show()
        }
    }
}