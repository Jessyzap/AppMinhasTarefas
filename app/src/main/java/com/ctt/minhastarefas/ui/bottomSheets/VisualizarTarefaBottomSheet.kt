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
import com.ctt.minhastarefas.model.TarefaAFazer

class VisualizarTarefaBottomSheet : BaseBottomSheetDialogFragment() {

    private lateinit var botaoIniciar: Button
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

        val contextoVisualizar =
            inflater.inflate(R.layout.bottom_sheet_visualizar_tarefa, container, false)
        botaoIniciar = contextoVisualizar.findViewById(R.id.btnIniciarTarefa)
        botaoExcluir = contextoVisualizar.findViewById(R.id.btnExcluirTarefaVisualizar)
        botaoEditar = contextoVisualizar.findViewById(R.id.btnEditarVisualizar)
        return contextoVisualizar
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tituloTarefa = view.findViewById<TextView>(R.id.txtTituloVisualizar)
        val descricaoTarefa = view.findViewById<TextView>(R.id.txtDescricaoVisualizar)

        val tituloTarefaRecebido = arguments?.getString("TITULO")
        val descricaoTarefaRecebida = arguments?.getString("DESCRICAO")
        val posicaoTarefaRecebida = arguments?.getInt("POSICAO")
        val id = arguments?.getInt("ID")

        tituloTarefa.text = tituloTarefaRecebido
        descricaoTarefa.text = descricaoTarefaRecebida

        botaoIniciar.setOnClickListener {

            if (id != null && tituloTarefaRecebido != null) {
                setFragmentResult(
                    "tarefaFazer",
                    bundleOf(
                        "tarefaFazerMover" to TarefaAFazer(
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
                val bottomSheetEditar = EditarFazerBottomSheet()
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
                    "tarefaFazer",
                    bundleOf(
                        "tarefaFazerRemover" to TarefaAFazer(
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