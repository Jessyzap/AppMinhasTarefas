package com.ctt.minhastarefas.bottomSheets

import android.content.Intent
import android.graphics.Insets.add
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.OneShotPreDrawListener.add
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProviders
import com.ctt.minhastarefas.R
import com.ctt.minhastarefas.fragments.FazerFragment
import com.ctt.minhastarefas.model.Tarefa
import com.ctt.minhastarefas.model.msgViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.bottom_sheet_criar_tarefa.*
import kotlinx.android.synthetic.main.fragment_fazer.*

class CriarTarefaBottomSheet : BottomSheetDialogFragment() {

    private var model: msgViewModel? = null

    companion object {
        const val TAG = "CriarTarefaBottomSheet"
    }

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
            //Toast.makeText(context, "vc clicou", Toast.LENGTH_SHORT).show()

            val titulo = nomeTarefa.text.toString()
            val descricao = descricaoTarefa.text.toString()
            val lista = mutableListOf<Tarefa>()

            if(titulo == "") {
                nomeTarefa.error = "Insira um título para a sua tarefa"
            } else {
                lista.add(Tarefa(titulo,descricao))
                model!!.dadosTarefa(lista)
                Toast.makeText(context, "Tarefa cadastrada com sucesso!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}