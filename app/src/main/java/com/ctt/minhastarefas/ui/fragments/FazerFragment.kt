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
import com.ctt.minhastarefas.adapterListas.TarefasFazerAdapter
import com.ctt.minhastarefas.ui.bottomSheets.CriarTarefaBottomSheet
import com.ctt.minhastarefas.ui.bottomSheets.LupaFazerBottomSheet
import com.ctt.minhastarefas.model.Tarefa

class FazerFragment : Fragment() {

    private lateinit var imagemFazerVazia: ImageView
    private lateinit var textoFazerVazia: TextView
    private lateinit var segundoTextoFazerVazia: TextView
    private lateinit var botao: Button
    private lateinit var botaoLupaFazer: Button


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val contextoFazer = inflater.inflate(R.layout.fragment_fazer, container, false)
        botao = contextoFazer.findViewById(R.id.btnAdicionar)
        botaoLupaFazer = contextoFazer.findViewById(R.id.btnLupaFazer)
        return contextoFazer
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        imagemFazerVazia = view.findViewById(R.id.imgFazerVazia)
        textoFazerVazia = view.findViewById(R.id.txtFazerVazia)
        segundoTextoFazerVazia = view.findViewById(R.id.txt2FazerVazia)


        if(listaTarefasFazer.isNotEmpty()){
            imagemFazerVazia.visibility = View.GONE
            textoFazerVazia.visibility = View.GONE
            segundoTextoFazerVazia.visibility = View.GONE
        }


        val botao = view.findViewById<Button>(R.id.btnAdicionar)


        val bottomSheetCriar = CriarTarefaBottomSheet()
        val bottomSheetLupaFazer = LupaFazerBottomSheet()


        // Abrir bottom sheet para criar a tarefa
        botao.setOnClickListener {
            fragmentManager?.let { it1 ->
                bottomSheetCriar.show(it1, "CriarTarefaBottomSheet")
            }
        }


        // Pesquisar por tarefas a fazer
        botaoLupaFazer.setOnClickListener {
            fragmentManager?.let { it1 ->
                bottomSheetLupaFazer.show(it1, "LupaFazerBottomSheet")
            }
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




