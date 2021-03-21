package com.ctt.minhastarefas.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ctt.minhastarefas.R
import com.ctt.minhastarefas.adapterListas.TarefasFeitasAdapter
import com.ctt.minhastarefas.ui.bottomSheets.LupaFeitasBottomSheet
import com.ctt.minhastarefas.model.Tarefa

class FeitasFragment : Fragment() {

  //  private var model: msgViewModel? = null
    private lateinit var imagemFeitasVazia: ImageView
    private lateinit var textoFeitasVazia: TextView
    private lateinit var segundoTextoFeitasVazia: TextView
    private lateinit var botaoLupaFeitas: Button


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val contextoFeitas = inflater.inflate(R.layout.fragment_feitas, container, false)
        botaoLupaFeitas = contextoFeitas.findViewById(R.id.btnLupaFeitas)
        return contextoFeitas
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imagemFeitasVazia = view.findViewById(R.id.imgFeitasVazia)
        textoFeitasVazia = view.findViewById(R.id.txtFeitasVazia)
        segundoTextoFeitasVazia = view.findViewById(R.id.txt2FeitasVazia)


        if(listaTarefasFeitas.isNotEmpty()){
            imagemFeitasVazia.visibility = View.GONE
            textoFeitasVazia.visibility = View.GONE
            segundoTextoFeitasVazia.visibility = View.GONE
        }


        val bottomSheetLupaFeitas = LupaFeitasBottomSheet()


        // Pesquisar por tarefas em progresso
        botaoLupaFeitas.setOnClickListener {
            fragmentManager?.let { it1 ->
                bottomSheetLupaFeitas.show(it1, "LupaFeitasBottomSheet")
            }
        }

        val rvFeitas = view.findViewById<RecyclerView>(R.id.rvListaFeitas)
        val adapterTarefasFeitas = TarefasFeitasAdapter(listaTarefasFeitas,activity!!)
        rvFeitas.adapter = adapterTarefasFeitas
        rvFeitas.layoutManager = LinearLayoutManager(context)
    }

    companion object {
        val listaTarefasFeitas = mutableListOf<Tarefa>()
    }
}