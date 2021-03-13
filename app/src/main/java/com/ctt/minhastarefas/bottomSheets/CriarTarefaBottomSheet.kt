package com.ctt.minhastarefas.bottomSheets

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.ctt.minhastarefas.R
import com.ctt.minhastarefas.fragments.FazerFragment.Companion.listaTarefasFazer
import com.ctt.minhastarefas.model.Tarefa
import com.ctt.minhastarefas.model.msgViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class CriarTarefaBottomSheet : BottomSheetDialogFragment() {

    private var model: msgViewModel? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.bottom_sheet_criar_tarefa, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        model = ViewModelProviders.of(activity!!).get(msgViewModel::class.java)

        val nomeTarefa = view.findViewById<EditText>(R.id.txtTituloCriarTarefa)
        val descricaoTarefa = view.findViewById<EditText>(R.id.txtDescricaoCriarTarefa)
        val botaoCriarTarefa = view.findViewById<Button>(R.id.btnCriarTarefa)

        botaoCriarTarefa.setOnClickListener {

            val titulo = nomeTarefa.text.toString()
            val descricao = descricaoTarefa.text.toString()

            if(titulo == "") {
                nomeTarefa.error = "Insira um título para a sua tarefa"
            } else {
                model!!.dadosTarefa(Tarefa(titulo,descricao))
                listaTarefasFazer.add(Tarefa(titulo, descricao))
                Toast.makeText(context, "Tarefa cadastrada com sucesso!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}