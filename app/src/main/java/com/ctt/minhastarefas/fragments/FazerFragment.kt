package com.ctt.minhastarefas.fragments

import android.os.Bundle
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


class FazerFragment : Fragment() {

    private var model: msgViewModel? = null
    private lateinit var imagemFazerVazia: ImageView
    private lateinit var textoFazerVazia: TextView
    private lateinit var segundoTextoFazerVazia: TextView


    private lateinit var botao: Button


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        model = ViewModelProviders.of(activity!!).get(msgViewModel::class.java)

        model!!.dados.observe(viewLifecycleOwner, object : Observer<Tarefa> {
            override fun onChanged(tarefa: Tarefa) {
                if (tarefa.nomeTarefa != ""){
                    imagemFazerVazia.visibility = View.GONE
                    textoFazerVazia.visibility = View.GONE
                    segundoTextoFazerVazia.visibility = View.GONE
                }
            }
        })

        val contextoFazer = inflater.inflate(R.layout.fragment_fazer, container, false)
        botao = contextoFazer.findViewById(R.id.btnAdicionar)
        return contextoFazer
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imagemFazerVazia = view.findViewById(R.id.imgFazerVazia)
        textoFazerVazia = view.findViewById(R.id.txtFazerVazia)
        segundoTextoFazerVazia = view.findViewById(R.id.txt2FazerVazia)


        val bottomSheetCriar = CriarTarefaBottomSheet()

        botao.setOnClickListener {

            fragmentManager?.let { it1 ->
                bottomSheetCriar.show(it1, "CriarTarefaBottomSheet")
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




