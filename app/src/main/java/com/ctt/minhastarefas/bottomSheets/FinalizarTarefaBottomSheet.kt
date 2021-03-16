package com.ctt.minhastarefas.bottomSheets

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.ctt.minhastarefas.R
import com.ctt.minhastarefas.adapterListas.TarefasProgressoAdapter
import com.ctt.minhastarefas.fragments.FeitasFragment.Companion.listaTarefasFeitas
import com.ctt.minhastarefas.fragments.ProgressoFragment
import com.ctt.minhastarefas.model.Tarefa
import com.ctt.minhastarefas.model.msgViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class FinalizarTarefaBottomSheet() : BottomSheetDialogFragment() {

    private var model: msgViewModel? = null

    private lateinit var botaoFinalizar: Button
    private lateinit var botaoExcluir: TextView
    private lateinit var botaoEditar: ImageView


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
        botaoExcluir = contextoFinalizar.findViewById(R.id.btnExcluirTarefaFinalizar)
        botaoEditar = contextoFinalizar.findViewById(R.id.btnEditarFinalizar)
        return contextoFinalizar
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        model = ViewModelProviders.of(activity!!).get(msgViewModel::class.java)

        val tituloTarefa = view.findViewById<TextView>(R.id.txtTituloProgresso)
        val descricaoTarefa = view.findViewById<TextView>(R.id.txtDescricaoProgresso)

        val tituloTarefaRecebido = getArguments()?.getString("TITULO")
        val descricaoTarefaRecebida = getArguments()?.getString("DESCRICAO")
        val posicaoTarefaRecebida = getArguments()?.getString("POSICAO")?.toInt()

        tituloTarefa.text = tituloTarefaRecebido
        descricaoTarefa.text = descricaoTarefaRecebida

        // adicionar na lista feitas
        botaoFinalizar.setOnClickListener {
            model!!.notificar("Tarefa finalizar")
            listaTarefasFeitas.add(Tarefa(tituloTarefa.text as String, descricaoTarefa.text as String))

            if (posicaoTarefaRecebida != null) {
                context?.let { it1 -> TarefasProgressoAdapter(ProgressoFragment.listaTarefasProgresso, it1) }
                    ?.removerTarefaProgresso(
                        posicaoTarefaRecebida
                    )
            }


            Toast.makeText(context, "A tarefa foi finalizada", Toast.LENGTH_LONG).show()
            dismiss()
        }


        botaoEditar.setOnClickListener {

            context?.let { it1 ->
                TarefasProgressoAdapter(
                    ProgressoFragment.listaTarefasProgresso,
                    it1
                ).editarTarefaProgresso(tituloTarefa.text as String, descricaoTarefa.text as String,posicaoTarefaRecebida)
            }
            dismiss()
        }

        botaoExcluir.setOnClickListener {

            //listaTarefasProgresso.remove(Tarefa(tituloTarefa.text as String, descricaoTarefa.text as String))
            if (posicaoTarefaRecebida != null) {
                context?.let { it1 -> TarefasProgressoAdapter(ProgressoFragment.listaTarefasProgresso, it1) }
                    ?.removerTarefaProgresso(
                        posicaoTarefaRecebida
                    )
            }
            Toast.makeText(context, "A tarefa foi excluída", Toast.LENGTH_SHORT).show()
            dismiss()
        }
    }



    override fun onStart() {
        super.onStart()
        val sheetContainer = requireView().parent as? ViewGroup ?: return
        sheetContainer.layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return BottomSheetDialog(requireContext(), theme).apply {
            behavior.state = BottomSheetBehavior.STATE_HALF_EXPANDED
            behavior.halfExpandedRatio = 0.90F
        }
    }
}