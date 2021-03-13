package com.ctt.minhastarefas.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ctt.minhastarefas.R
import com.ctt.minhastarefas.adapterListas.TarefasFazerAdapter
import com.ctt.minhastarefas.bottomSheets.CriarTarefaBottomSheet
import com.ctt.minhastarefas.model.Tarefa
import com.ctt.minhastarefas.model.msgViewModel


class FazerFragment : Fragment() {

    private lateinit var botao: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val contextoFazer = inflater.inflate(R.layout.fragment_fazer, container, false)

        botao = contextoFazer.findViewById(R.id.btnAdicionar)
        return contextoFazer
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val model = ViewModelProviders.of(activity!!).get(msgViewModel::class.java)

        var tituloTarefa: String
        var descricaoTarefa: String


        val bottomSheetCriar = CriarTarefaBottomSheet()
        //BottomSheetBehavior.STATE_EXPANDED


        botao.setOnClickListener {

            //Toast.makeText(context, "clicou", Toast.LENGTH_SHORT).show()
            fragmentManager?.let { it1 -> bottomSheetCriar.show(it1, "CriarTarefaBottomSheet")
            }

//            model.dados.observe(viewLifecycleOwner,
//                { t ->
//                    tituloTarefa = t.nomeTarefa
//                    descricaoTarefa = t.descricaoTarefa
//                    listaTarefasFazer.add(Tarefa(tituloTarefa, descricaoTarefa))
//                })
        }

        val rvFazer = view.findViewById<RecyclerView>(R.id.rvListaFazer)
        val adapterTarefasFazer = TarefasFazerAdapter(listaTarefasFazer, activity!!)
        rvFazer.adapter = adapterTarefasFazer
        rvFazer.layoutManager = LinearLayoutManager(context)
    }

    companion object {
        val listaTarefasFazer = mutableListOf<Tarefa>()
    }
}

