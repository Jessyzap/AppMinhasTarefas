package com.ctt.minhastarefas.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ctt.minhastarefas.R
import com.ctt.minhastarefas.adapterListas.TarefasFazerAdapter
import com.ctt.minhastarefas.model.Tarefa


class FazerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_fazer, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listaTarefasFazer = mutableListOf(
            Tarefa("Arrumar o guarda-roupa", "Organizar as gavetas, " +
                    "separar as roupas de frio das de calor e colocar algumas peças no cabide."),
            Tarefa("Arrumar o guarda-roupa", "Organizar as gavetas, " +
                    "separar as roupas de frio das de calor e colocar algumas peças no cabide."),
            Tarefa("Arrumar o guarda-roupa", "Organizar as gavetas, " +
                    "separar as roupas de frio das de calor e colocar algumas peças no cabide."),
            Tarefa("Arrumar o guarda-roupa", "Organizar as gavetas, " +
                    "separar as roupas de frio das de calor e colocar algumas peças no cabide."),
            Tarefa("Arrumar o guarda-roupa", "Organizar as gavetas, " +
                    "separar as roupas de frio das de calor e colocar algumas peças no cabide."),
            Tarefa("Arrumar o guarda-roupa", "Organizar as gavetas, " +
                    "separar as roupas de frio das de calor e colocar algumas peças no cabide."),
            Tarefa("Arrumar o guarda-roupa", "Organizar as gavetas, " +
                    "separar as roupas de frio das de calor e colocar algumas peças no cabide."),
            Tarefa("Arrumar o guarda-roupa", "Organizar as gavetas, " +
                    "separar as roupas de frio das de calor e colocar algumas peças no cabide."),
            Tarefa("Arrumar o guarda-roupa", "Organizar as gavetas, " +
                    "separar as roupas de frio das de calor e colocar algumas peças no cabide."),
            Tarefa("Arrumar o guarda-roupa", "Organizar as gavetas, " +
                    "separar as roupas de frio das de calor e colocar algumas peças no cabide."),
            Tarefa("Arrumar o guarda-roupa", "Organizar as gavetas, " +
                    "separar as roupas de frio das de calor e colocar algumas peças no cabide."),
            Tarefa("Arrumar o guarda-roupa", "Organizar as gavetas, " +
                    "separar as roupas de frio das de calor e colocar algumas peças no cabide."),
            Tarefa("Arrumar o guarda-roupa", "Organizar as gavetas, " +
                    "separar as roupas de frio das de calor e colocar algumas peças no cabide."),
        )

        val rvFazer = view.findViewById<RecyclerView>(R.id.rvListaFazer)
        val adapterTarefasFazer = TarefasFazerAdapter(listaTarefasFazer)
        rvFazer.adapter = adapterTarefasFazer
        rvFazer.layoutManager = LinearLayoutManager(context)

    }
}