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
import com.ctt.minhastarefas.R
import com.ctt.minhastarefas.fragments.FeitasFragment.Companion.listaTarefasFeitas
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class LupaFeitasBottomSheet : BottomSheetDialogFragment() {

    private lateinit var botaoProcurarFeitas: Button
    private lateinit var nomeTarefa: EditText
    private lateinit var tituloProvisorio: TextView

    companion object {
        const val TAG = "LupaFeitasBottomSheet"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(BottomSheetDialogFragment.STYLE_NORMAL, R.style.MyBottomSheetDialogTheme)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val contextoPesquisarFeitas = inflater.inflate(R.layout.bottom_sheet_lupa, container, false)
        botaoProcurarFeitas = contextoPesquisarFeitas.findViewById(R.id.btnProcurar)
        return contextoPesquisarFeitas
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        nomeTarefa = view.findViewById(R.id.txtProcurarTituloTarefa)
        tituloProvisorio = view.findViewById(R.id.tituloProvisorio)

        tituloProvisorio.text = "Procurar por tarefas feitas"


        botaoProcurarFeitas.setOnClickListener {

            val titulo = nomeTarefa.text.toString()

            when {
                titulo == "" -> {
                    nomeTarefa.error = "Insira um título para pesquisar"
                }
                listaTarefasFeitas.isEmpty() -> {
                    Toast.makeText(context, "A sua lista está vazia!", Toast.LENGTH_SHORT).show()
                }
                listaTarefasFeitas.isNotEmpty() -> {
                    for (tarefa in listaTarefasFeitas) {

                        if (titulo in tarefa.nomeTarefa) {

                            val indice = listaTarefasFeitas.indexOf(tarefa)

                            // abrir bottom sheet
                            val bottomSheetFinalizada = FinalizadaTarefaBottomSheet()
                            val bundle = Bundle()
                            bundle.putString("TITULO", tarefa.nomeTarefa)
                            bundle.putString("DESCRICAO", tarefa.descricaoTarefa)
                            bundle.putString("POSICAO", indice.toString())
                            bottomSheetFinalizada.setArguments(bundle)
                            bottomSheetFinalizada.show(
                                (context as AppCompatActivity).supportFragmentManager,
                                "BottomSheetFinalizada"
                            )
                            nomeTarefa.text.clear()
                            dismiss()
                        } else {
                            Toast.makeText(context, "Essa tarefa não está na lista!", Toast.LENGTH_SHORT).show()

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