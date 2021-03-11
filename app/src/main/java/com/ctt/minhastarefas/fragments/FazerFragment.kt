package com.ctt.minhastarefas.fragments

import android.os.Bundle
import android.text.Layout
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ctt.minhastarefas.R
import com.ctt.minhastarefas.adapterListas.TarefasFazerAdapter
import com.ctt.minhastarefas.bottomSheets.CriarTarefaBottomSheet
import com.ctt.minhastarefas.model.Tarefa
import com.ctt.minhastarefas.model.msgViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_fazer.*


class FazerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val contextoFazer = inflater.inflate(R.layout.fragment_fazer, container, false)
        return contextoFazer
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val model = ViewModelProviders.of(activity!!).get(msgViewModel::class.java)

        var tituloTarefa: String
        var descricaoTarefa: String

        val listaTarefasFazer = mutableListOf<Tarefa>()

        model.listaDados.observe(viewLifecycleOwner,
            { t ->
                tituloTarefa = t[0].nomeTarefa
                descricaoTarefa = t[0].descricaoTarefa
                listaTarefasFazer.add(Tarefa(tituloTarefa, descricaoTarefa))
            })

        val rvFazer = view.findViewById<RecyclerView>(R.id.rvListaFazer)
        val adapterTarefasFazer = TarefasFazerAdapter(listaTarefasFazer)
        rvFazer.adapter = adapterTarefasFazer
        rvFazer.layoutManager = LinearLayoutManager(context)
    }
}

