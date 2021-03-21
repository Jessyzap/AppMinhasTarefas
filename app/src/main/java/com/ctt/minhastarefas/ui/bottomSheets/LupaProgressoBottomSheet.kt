package com.ctt.minhastarefas.ui.bottomSheets

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
import com.ctt.minhastarefas.ui.fragments.ProgressoFragment.Companion.listaTarefasProgresso
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class LupaProgressoBottomSheet : BottomSheetDialogFragment() {

    private lateinit var botaoProcurarProgresso: Button
    private lateinit var nomeTarefa: EditText
    private lateinit var tituloProvisorio: TextView

    companion object {
        const val TAG = "LupaProgressoBottomSheet"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(BottomSheetDialogFragment.STYLE_NORMAL, R.style.MyBottomSheetDialogTheme)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val contextoPesquisarProgresso = inflater.inflate(R.layout.bottom_sheet_lupa, container, false)
        botaoProcurarProgresso = contextoPesquisarProgresso.findViewById(R.id.btnProcurar)
        return contextoPesquisarProgresso
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        nomeTarefa = view.findViewById(R.id.txtProcurarTituloTarefa)
        tituloProvisorio = view.findViewById(R.id.tituloProvisorio)

        tituloProvisorio.text = "Procurar por tarefas em progresso"


        botaoProcurarProgresso.setOnClickListener {

            val titulo = nomeTarefa.text.toString()

            when {
                titulo == "" -> {
                    nomeTarefa.error = "Insira um título para pesquisar"
                }
                listaTarefasProgresso.isEmpty() -> {
                    Toast.makeText(context, "A sua lista está vazia!", Toast.LENGTH_SHORT).show()
                }
                listaTarefasProgresso.isNotEmpty() -> {
                    for (tarefa in listaTarefasProgresso) {

                        if (titulo in tarefa.nomeTarefa) {

                            val indice = listaTarefasProgresso.indexOf(tarefa)

                            // abrir bottom sheet
                            val bottomSheetFinalizar = FinalizarTarefaBottomSheet()
                            val bundle = Bundle()
                            bundle.putString("TITULO", tarefa.nomeTarefa)
                            bundle.putString("DESCRICAO", tarefa.descricaoTarefa)
                            bundle.putString("POSICAO", indice.toString())
                            bottomSheetFinalizar.setArguments(bundle)
                            bottomSheetFinalizar.show(
                                (context as AppCompatActivity).supportFragmentManager,
                                "BottomSheetFinalizar"
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