package com.ctt.minhastarefas.ui.bottomSheets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import com.ctt.minhastarefas.R
import com.ctt.minhastarefas.model.TarefaFeita

class EditarFeitaBottomSheet : BaseBottomSheetDialogFragment() {

    private lateinit var botaoSalvar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.MyBottomSheetDialogTheme)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val contextoFinalizada =
            inflater.inflate(R.layout.bottom_sheet_editar_tarefa, container, false)
        botaoSalvar = contextoFinalizada.findViewById(R.id.btnSalvarEditar)
        return contextoFinalizada
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tituloTarefa = view.findViewById<EditText>(R.id.edtTituloEditar)
        val descricaoTarefa = view.findViewById<EditText>(R.id.edtDescricaoEditar)

        val tituloTarefaRecebido = arguments?.getString("TITULO")
        val descricaoTarefaRecebida = arguments?.getString("DESCRICAO")
        val id = arguments?.getInt("ID")

        tituloTarefa.setText(tituloTarefaRecebido)
        descricaoTarefa.setText(descricaoTarefaRecebida)

        botaoSalvar.setOnClickListener {
            id?.let {
                editar(
                    TarefaFeita(
                        id = it,
                        nomeTarefa = tituloTarefa.text.toString(),
                        descricaoTarefa = descricaoTarefa.text.toString()
                    )
                )
            }
        }
    }

    private fun editar(tarefa: TarefaFeita) {
        setFragmentResult("tarefaFeita", bundleOf("tarefaFeitaEditar" to tarefa))
        dismiss()
    }

}