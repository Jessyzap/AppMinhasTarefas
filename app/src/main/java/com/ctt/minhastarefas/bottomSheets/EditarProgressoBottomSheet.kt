package com.ctt.minhastarefas.bottomSheets

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import com.ctt.minhastarefas.R
import com.ctt.minhastarefas.adapterListas.TarefasProgressoAdapter
import com.ctt.minhastarefas.fragments.ProgressoFragment
import com.ctt.minhastarefas.fragments.ProgressoFragment.Companion.listaTarefasProgresso
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class EditarProgressoBottomSheet() : BottomSheetDialogFragment() {

    private lateinit var botaoSalvar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(BottomSheetDialogFragment.STYLE_NORMAL, R.style.MyBottomSheetDialogTheme)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val contextoFinalizar = inflater.inflate(R.layout.bottom_sheet_editar_tarefa, container, false)
        botaoSalvar = contextoFinalizar.findViewById(R.id.btnSalvarEditar)
        return contextoFinalizar
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val tituloTarefa = view.findViewById<EditText>(R.id.edtTituloEditar)
        val descricaoTarefa = view.findViewById<EditText>(R.id.edtDescricaoEditar)

        val tituloTarefaRecebido = getArguments()?.getString("TITULO")
        val descricaoTarefaRecebida = getArguments()?.getString("DESCRICAO")
        val posicaoTarefaRecebida = getArguments()?.getString("POSICAO")?.toInt()


        tituloTarefa.setText(tituloTarefaRecebido)
        descricaoTarefa.setText(descricaoTarefaRecebida)


        botaoSalvar.setOnClickListener {
            context?.let { it1 -> TarefasProgressoAdapter(listaTarefasProgresso, it1) }?.substituirTarefaProgresso(
                tituloTarefa.text.toString(), descricaoTarefa.text.toString(), posicaoTarefaRecebida.toString()
            )

            val transicao: FragmentTransaction = getFragmentManager()!!.beginTransaction()
            transicao.replace(R.id.frProgresso, ProgressoFragment())
            transicao.commit()

            Toast.makeText(context, "A tarefa foi alterada", Toast.LENGTH_LONG).show()
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