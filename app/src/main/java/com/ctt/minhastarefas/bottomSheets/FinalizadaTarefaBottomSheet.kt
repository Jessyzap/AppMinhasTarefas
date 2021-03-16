package com.ctt.minhastarefas.bottomSheets

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.ctt.minhastarefas.R
import com.ctt.minhastarefas.adapterListas.TarefasFazerAdapter
import com.ctt.minhastarefas.adapterListas.TarefasFeitasAdapter
import com.ctt.minhastarefas.adapterListas.TarefasProgressoAdapter
import com.ctt.minhastarefas.fragments.FazerFragment
import com.ctt.minhastarefas.fragments.FeitasFragment
import com.ctt.minhastarefas.fragments.FeitasFragment.Companion.listaTarefasFeitas
import com.ctt.minhastarefas.fragments.ProgressoFragment
import com.ctt.minhastarefas.model.Tarefa
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class FinalizadaTarefaBottomSheet() : BottomSheetDialogFragment() {

    private lateinit var botaoExcluir: TextView
    private lateinit var botaoEditar: ImageView

//    companion object {
//        const val TAG = "FinalizadaTarefaBottomSheet"
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(BottomSheetDialogFragment.STYLE_NORMAL, R.style.MyBottomSheetDialogTheme)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val contextoFinalizada = inflater.inflate(R.layout.bottom_sheet_tarefa_finalizada, container, false)
        botaoExcluir = contextoFinalizada.findViewById(R.id.btnExcluirTarefa)
        botaoEditar = contextoFinalizada.findViewById(R.id.btnEditarFinalizada)
        return contextoFinalizada
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        val tituloTarefa = view.findViewById<TextView>(R.id.txtTituloExcluir)
        val descricaoTarefa = view.findViewById<TextView>(R.id.txtDescricaoExcluir)

        val tituloTarefaRecebido = getArguments()?.getString("TITULO")
        val descricaoTarefaRecebida = getArguments()?.getString("DESCRICAO")
        val posicaoTarefaRecebida = getArguments()?.getString("POSICAO")?.toInt()

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
            Toast.makeText(context, "A Tarefa foi removida", Toast.LENGTH_SHORT).show()
        }

        botaoEditar.setOnClickListener {
            context?.let { it1 ->
                if (posicaoTarefaRecebida != null) {
                    TarefasFeitasAdapter(
                        FeitasFragment.listaTarefasFeitas,
                        it1
                    ).editarTarefaFeita(tituloTarefa.text as String, descricaoTarefa.text as String,posicaoTarefaRecebida )
                }
            }
            dismiss()

        }

//            context?.let { it1 ->
//                TarefasFeitasAdapter(
//                    FeitasFragment.listaTarefasFeitas,
//                    it1
//                ).editarTarefaFeita(tituloTarefa.text as String, descricaoTarefa.text as String)
//            }
//        }
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