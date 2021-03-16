package com.ctt.minhastarefas.bottomSheets

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.ctt.minhastarefas.R
import com.ctt.minhastarefas.fragments.FazerFragment.Companion.listaTarefasFazer
import com.ctt.minhastarefas.model.Tarefa
import com.ctt.minhastarefas.model.msgViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.PEEK_HEIGHT_AUTO
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class LupaFazerBottomSheet : BottomSheetDialogFragment() {

    private lateinit var botaoProcurarFazer: Button
    private lateinit var nomeTarefa: EditText

    companion object {
        const val TAG = "LupaFazerBottomSheet"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(BottomSheetDialogFragment.STYLE_NORMAL, R.style.MyBottomSheetDialogTheme)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val contextoPesquisarFazer = inflater.inflate(R.layout.bottom_sheet_lupa_fazer, container, false)
        botaoProcurarFazer = contextoPesquisarFazer.findViewById(R.id.btnProcurarFazer)
        return contextoPesquisarFazer
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        nomeTarefa = view.findViewById(R.id.txtProcurarTituloTarefa)

        botaoProcurarFazer.setOnClickListener {

            val titulo = nomeTarefa.text.toString()

            when {
                titulo == "" -> {
                    nomeTarefa.error = "Insira um título para pesquisar"
                }
                listaTarefasFazer.isEmpty() -> {
                    Toast.makeText(context, "A sua lista está vazia!", Toast.LENGTH_SHORT).show()
                }
                listaTarefasFazer.isNotEmpty() -> {
                    for (tarefa in listaTarefasFazer) {
                        if (titulo in tarefa.nomeTarefa) {
                            // abrir bottom sheet
                            val bottomSheetVisualizar = VisualizarTarefaBottomSheet()
                            val bundle = Bundle()
                            bundle.putString("TITULO", tarefa.nomeTarefa)
                            bundle.putString("DESCRICAO", tarefa.descricaoTarefa)
                            bottomSheetVisualizar.setArguments(bundle)
                            bottomSheetVisualizar.show(
                                (context as AppCompatActivity).supportFragmentManager,
                                "BottomSheetVisualizar"
                            )
                        } else {
                           // Toast.makeText(context, "Essa tarefa não está na lista!", Toast.LENGTH_SHORT).show()

                        }
                    }
                }
            }
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
            behavior.halfExpandedRatio = 0.55F
        }
    }
}