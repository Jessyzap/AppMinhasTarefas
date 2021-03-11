package com.ctt.minhastarefas.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ctt.minhastarefas.R
import com.ctt.minhastarefas.adapterListas.TarefasProgressoAdapter
import com.ctt.minhastarefas.model.Tarefa

class ProgressoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_progresso, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listaTarefasProgresso = mutableListOf(
            Tarefa("A", "A"),
            Tarefa("A", "A"),
            Tarefa("A", "A"),
            Tarefa("A", "A"),
            Tarefa("A", "A"),
            Tarefa("A", "A"),
            Tarefa("A", "A"),
            Tarefa("A", "A"),
            Tarefa("A", "A"),
            Tarefa("A", "A"),
            Tarefa("A", "A"),
            Tarefa("A", "A"),
            Tarefa("A", "A")
        )

        val rvProgresso = view.findViewById<RecyclerView>(R.id.rvListaProgresso)
        val adapterTarefasProgresso = TarefasProgressoAdapter(listaTarefasProgresso)
        rvProgresso.adapter = adapterTarefasProgresso
        rvProgresso.layoutManager = LinearLayoutManager(context)
    }
}