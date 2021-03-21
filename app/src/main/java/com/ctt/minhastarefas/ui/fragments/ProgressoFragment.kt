package com.ctt.minhastarefas.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ctt.minhastarefas.R
import com.ctt.minhastarefas.adapterListas.TarefasProgressoAdapter
import com.ctt.minhastarefas.ui.bottomSheets.LupaProgressoBottomSheet
import com.ctt.minhastarefas.model.Tarefa

class ProgressoFragment : Fragment() {

    private lateinit var imagemProgressoVazia: ImageView
    private lateinit var textoProgressoVazia: TextView
    private lateinit var segundoTextoProgressoVazia: TextView
    private lateinit var botaoLupaProgresso: Button


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val contextoProgresso =  inflater.inflate(R.layout.fragment_progresso, container, false)
        botaoLupaProgresso = contextoProgresso.findViewById(R.id.btnLupaProgresso)
        return contextoProgresso
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        imagemProgressoVazia = view.findViewById(R.id.imgProgressoVazia)
        textoProgressoVazia = view.findViewById(R.id.txtProgressoVazia)
        segundoTextoProgressoVazia = view.findViewById(R.id.txt2ProgressoVazia)


        if(listaTarefasProgresso.isNotEmpty()){
            imagemProgressoVazia.visibility = View.GONE
            textoProgressoVazia.visibility = View.GONE
            segundoTextoProgressoVazia.visibility = View.GONE
        }

        val bottomSheetLupaProgresso = LupaProgressoBottomSheet()


        // Pesquisar por tarefas em progresso
        botaoLupaProgresso.setOnClickListener {
            fragmentManager?.let { it1 ->
                bottomSheetLupaProgresso.show(it1, "LupaProgressoBottomSheet")
            }
        }

        val rvProgresso = view.findViewById<RecyclerView>(R.id.rvListaProgresso)
        val adapterTarefasProgresso = TarefasProgressoAdapter(listaTarefasProgresso,activity!!)
        rvProgresso.adapter = adapterTarefasProgresso
        rvProgresso.layoutManager = LinearLayoutManager(context)
    }

    companion object {
        val listaTarefasProgresso = mutableListOf<Tarefa>()
    }
}