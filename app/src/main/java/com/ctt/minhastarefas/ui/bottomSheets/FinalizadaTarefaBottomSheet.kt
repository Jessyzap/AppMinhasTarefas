package com.ctt.minhastarefas.ui.bottomSheets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import com.ctt.minhastarefas.R
import com.ctt.minhastarefas.model.TarefaFeita

class FinalizadaTarefaBottomSheet : BaseBottomSheetDialogFragment() {

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

        val contextoFinalizada =
            inflater.inflate(R.layout.bottom_sheet_tarefa_finalizada, container, false)
        botaoExcluir = contextoFinalizada.findViewById(R.id.btnExcluirTarefa)
        botaoEditar = contextoFinalizada.findViewById(R.id.btnEditarFinalizada)
        return contextoFinalizada
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val tituloTarefa = view.findViewById<TextView>(R.id.txtTituloExcluir)
        val descricaoTarefa = view.findViewById<TextView>(R.id.txtDescricaoExcluir)

        val tituloTarefaRecebido = arguments?.getString("TITULO")
        val descricaoTarefaRecebida = arguments?.getString("DESCRICAO")
        val posicaoTarefaRecebida = arguments?.getString("POSICAO")?.toInt()
        val id = arguments?.getInt("ID")

        tituloTarefa.text = tituloTarefaRecebido
        descricaoTarefa.text = descricaoTarefaRecebida


        botaoExcluir.setOnClickListener {
            if (id != null && tituloTarefaRecebido != null)
                setFragmentResult(
                    "tarefaFeita",
                    bundleOf(
                        "tarefaFinalizadaRemover" to TarefaFeita(
                            id = id,
                            nomeTarefa = tituloTarefaRecebido,
                            descricaoTarefa = descricaoTarefaRecebida
                        )
                    )
                )
            dismiss()
        }

        botaoEditar.setOnClickListener {
            id?.let {
                val bottomSheetEditar = EditarFeitaBottomSheet()
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
    }

}