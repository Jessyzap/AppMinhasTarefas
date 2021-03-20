package com.ctt.minhastarefas.bottomSheets

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProviders
import com.ctt.minhastarefas.R
import com.ctt.minhastarefas.adapterListas.TarefasFazerAdapter
import com.ctt.minhastarefas.fragments.FazerFragment
import com.ctt.minhastarefas.fragments.FazerFragment.Companion.listaTarefasFazer
import com.ctt.minhastarefas.fragments.ProgressoFragment
import com.ctt.minhastarefas.model.Tarefa
import com.ctt.minhastarefas.model.msgViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.lang.ClassCastException

class CriarTarefaBottomSheet : BottomSheetDialogFragment(){

    private lateinit var nomeTarefa: EditText
    private lateinit var descricaoTarefa: EditText
    private lateinit var botaoCriarTarefa: Button

//    companion object {
//        const val TAG = "CriarTarefaBottomSheet"
//    }

    private var model: msgViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(BottomSheetDialogFragment.STYLE_NORMAL, R.style.MyBottomSheetDialogTheme)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val contextoCriar = inflater.inflate(R.layout.bottom_sheet_criar_tarefa, container, false)

        botaoCriarTarefa = contextoCriar.findViewById(R.id.btnCriarTarefa)
        return contextoCriar
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val model = ViewModelProviders.of(activity!!).get(msgViewModel::class.java)

        nomeTarefa = view.findViewById(R.id.txtTituloCriarTarefa)
        descricaoTarefa = view.findViewById(R.id.txtDescricaoCriarTarefa)
        botaoCriarTarefa = view.findViewById(R.id.btnCriarTarefa)


        botaoCriarTarefa.setOnClickListener {

            val titulo = nomeTarefa.text.toString()
            val descricao = descricaoTarefa.text.toString()

            if (titulo == "") {
                nomeTarefa.error = "Insira um título para a sua tarefa"
            } else {

                context?.let { it1 -> TarefasFazerAdapter(listaTarefasFazer, it1).adicionarTarefaFazer(Tarefa(titulo,descricao)) }

                val transicao: FragmentTransaction = getFragmentManager()!!.beginTransaction()
                transicao.replace(R.id.frFazer, FazerFragment())
                transicao.commit()

                model.notificar("Tarefa Criar")

                Toast.makeText(context, "Tarefa cadastrada!", Toast.LENGTH_SHORT).show()
                dismiss()
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
            behavior.halfExpandedRatio = 0.90F
        }
    }
}