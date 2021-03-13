package com.ctt.minhastarefas.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ctt.minhastarefas.R
import com.ctt.minhastarefas.adapterListas.TarefasFeitasAdapter
import com.ctt.minhastarefas.model.Tarefa

class FeitasFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_feitas, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val listaTarefasFeitas = mutableListOf(
//            Tarefa("A", "A"),
//            Tarefa("A", "A"),
//            Tarefa("A", "A"),
//            Tarefa("A", "A"),
//            Tarefa("A", "A"),
//            Tarefa("A", "A"),
//            Tarefa("A", "A"),
//            Tarefa("A", "A"),
//            Tarefa("A", "A"),
//            Tarefa("A", "A"),
//            Tarefa("A", "A"),
//            Tarefa("A", "A"),
//            Tarefa("A", "A")
//        )

        val rvFeitas = view.findViewById<RecyclerView>(R.id.rvListaFeitas)
        val adapterTarefasFeitas = TarefasFeitasAdapter(listaTarefasFeitas,activity!!)
        rvFeitas.adapter = adapterTarefasFeitas
        rvFeitas.layoutManager = LinearLayoutManager(context)
    }

    companion object {
        val listaTarefasFeitas = mutableListOf<Tarefa>()
    }
}