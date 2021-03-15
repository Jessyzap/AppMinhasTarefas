package com.ctt.minhastarefas.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ctt.minhastarefas.R
import com.ctt.minhastarefas.adapterListas.TarefasProgressoAdapter
import com.ctt.minhastarefas.model.Tarefa
import com.ctt.minhastarefas.model.msgViewModel

class ProgressoFragment : Fragment() {

    private var model: msgViewModel? = null
    private lateinit var imagemProgressoVazia: ImageView
    private lateinit var textoProgressoVazia: TextView
    private lateinit var segundoTextoProgressoVazia: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        model = ViewModelProviders.of(activity!!).get(msgViewModel::class.java)

        model!!.textoNotificacao.observe(viewLifecycleOwner, object : Observer<String> {
            override fun onChanged(texto: String) {
                if (texto == "Tarefa iniciada"){
                    imagemProgressoVazia.visibility = View.GONE
                    textoProgressoVazia.visibility = View.GONE
                    segundoTextoProgressoVazia.visibility = View.GONE
                }
            }
        })

        return inflater.inflate(R.layout.fragment_progresso, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        model = ViewModelProviders.of(activity!!).get(msgViewModel::class.java)


        imagemProgressoVazia = view.findViewById(R.id.imgProgressoVazia)
        textoProgressoVazia = view.findViewById(R.id.txtProgressoVazia)
        segundoTextoProgressoVazia = view.findViewById(R.id.txt2ProgressoVazia)



        val rvProgresso = view.findViewById<RecyclerView>(R.id.rvListaProgresso)
        val adapterTarefasProgresso = TarefasProgressoAdapter(listaTarefasProgresso,activity!!)
        rvProgresso.adapter = adapterTarefasProgresso
        rvProgresso.layoutManager = LinearLayoutManager(context)
    }

    companion object {
        val listaTarefasProgresso = mutableListOf<Tarefa>()
    }
}