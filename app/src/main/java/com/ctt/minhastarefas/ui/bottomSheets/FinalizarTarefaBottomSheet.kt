package com.ctt.minhastarefas.ui.bottomSheets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import com.ctt.minhastarefas.R
import com.ctt.minhastarefas.model.TarefaEmProgresso

class FinalizarTarefaBottomSheet : BaseBottomSheetDialogFragment() {

    private lateinit var botaoFinalizar: Button
    private lateinit var botaoExcluir: TextView
    private lateinit var botaoEditar: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.MyBottomSheetDialogTheme)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val contextoFinalizar =
            inflater.inflate(R.layout.bottom_sheet_finalizar_tarefa, container, false)
        botaoFinalizar = contextoFinalizar.findViewById(R.id.btnFinalizarTarefa)
        botaoExcluir = contextoFinalizar.findViewById(R.id.btnExcluirTarefaFinalizar)
        botaoEditar = contextoFinalizar.findViewById(R.id.btnEditarFinalizar)
        return contextoFinalizar
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val tituloTarefa = view.findViewById<TextView>(R.id.txtTituloProgresso)
        val descricaoTarefa = view.findViewById<TextView>(R.id.txtDescricaoProgresso)

        val tituloTarefaRecebido = arguments?.getString("TITULO")
        val descricaoTarefaRecebida = arguments?.getString("DESCRICAO")
        val posicaoTarefaRecebida = arguments?.getString("POSICAO")?.toInt()
        val id = arguments?.getInt("ID")

        tituloTarefa.text = tituloTarefaRecebido
        descricaoTarefa.text = descricaoTarefaRecebida


        botaoFinalizar.setOnClickListener {
            if (id != null && tituloTarefaRecebido != null) {
                setFragmentResult(
                    "tarefaProgresso",
                    bundleOf(
                        "tarefaProgressoMover" to TarefaEmProgresso(
                            id = id,
                            nomeTarefa = tituloTarefaRecebido,
                            descricaoTarefa = descricaoTarefaRecebida
                        )
                    )
                )
                dismiss()
            }
        }


        botaoEditar.setOnClickListener {
            id?.let {
                val bottomSheetEditar = EditarProgressoBottomSheet()
                val bundle = Bundle()
                bundle.putString("TITULO", tituloTarefaRecebido)
                bundle.putString("DESCRICAO", descricaoTarefaRecebida)
                bundle.putString("POSICAO", posicaoTarefaRecebida.toString())
                bundle.putInt("ID", it)
                bottomSheetEditar.arguments = bundle
                bottomSheetEditar.show(
                    (context as AppCompatActivity).supportFragmentManager,
                    ""
                )
                dismiss()
            }
        }

        botaoExcluir.setOnClickListener {
            if (id != null && tituloTarefaRecebido != null)
                setFragmentResult(
                    "tarefaProgresso",
                    bundleOf(
                        "tarefaProgressoRemover" to TarefaEmProgresso(
                            id = id,
                            nomeTarefa = tituloTarefaRecebido,
                            descricaoTarefa = descricaoTarefaRecebida
                        )
                    )
                )
            dismiss()
        }
    }

}