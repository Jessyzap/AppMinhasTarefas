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
import com.ctt.minhastarefas.model.TarefaAFazer
import com.ctt.minhastarefas.randomId

class CriarTarefaBottomSheet : BaseBottomSheetDialogFragment() {

    private lateinit var nomeTarefa: EditText
    private lateinit var descricaoTarefa: EditText
    private lateinit var botaoCriarTarefa: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.MyBottomSheetDialogTheme)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val contextoCriar = inflater.inflate(R.layout.bottom_sheet_criar_tarefa, container, false)

        botaoCriarTarefa = contextoCriar.findViewById(R.id.btnCriarTarefa)
        return contextoCriar
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        nomeTarefa = view.findViewById(R.id.txtTituloCriarTarefa)
        descricaoTarefa = view.findViewById(R.id.txtDescricaoCriarTarefa)
        botaoCriarTarefa = view.findViewById(R.id.btnCriarTarefa)

        botaoCriarTarefa.setOnClickListener {

            val titulo = nomeTarefa.text.toString()
            val descricao = descricaoTarefa.text.toString()

            if (titulo == "") {
                nomeTarefa.error = "Insira um título para a sua tarefa"
            } else {

                adicionarTarefaResult(
                    TarefaAFazer(
                        id = randomId(),
                        nomeTarefa = titulo,
                        descricaoTarefa = descricao
                    )
                )

                nomeTarefa.text.clear()
                descricaoTarefa.text.clear()
                nomeTarefa.requestFocus()

                dismiss()
            }
        }
    }

    private fun adicionarTarefaResult(tarefa: TarefaAFazer) {
        setFragmentResult("tarefaFazer", bundleOf("tarefaCriar" to tarefa))
    }
}