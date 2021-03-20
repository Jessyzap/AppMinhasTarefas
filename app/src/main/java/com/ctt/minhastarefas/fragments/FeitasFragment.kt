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
import com.ctt.minhastarefas.adapterListas.TarefasFeitasAdapter
import com.ctt.minhastarefas.model.Tarefa
import com.ctt.minhastarefas.model.msgViewModel

class FeitasFragment : Fragment() {

    private var model: msgViewModel? = null
    private lateinit var imagemFeitasVazia: ImageView
    private lateinit var textoFeitasVazia: TextView
    private lateinit var segundoTextoFeitasVazia: TextView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        model = ViewModelProviders.of(activity!!).get(msgViewModel::class.java)

        model!!.textoNotificacao.observe(viewLifecycleOwner, object : Observer<String> {
            override fun onChanged(texto: String) {
                if (texto == "Tarefa finalizar"){
                    imagemFeitasVazia.visibility = View.GONE
                    textoFeitasVazia.visibility = View.GONE
                    segundoTextoFeitasVazia.visibility = View.GONE
                }
            }
        })

        return inflater.inflate(R.layout.fragment_feitas, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        model = ViewModelProviders.of(activity!!).get(msgViewModel::class.java)


        imagemFeitasVazia = view.findViewById(R.id.imgFeitasVazia)
        textoFeitasVazia = view.findViewById(R.id.txtFeitasVazia)
        segundoTextoFeitasVazia = view.findViewById(R.id.txt2FeitasVazia)


        val rvFeitas = view.findViewById<RecyclerView>(R.id.rvListaFeitas)
        val adapterTarefasFeitas = TarefasFeitasAdapter(listaTarefasFeitas,activity!!)
        rvFeitas.adapter = adapterTarefasFeitas
        rvFeitas.layoutManager = LinearLayoutManager(context)
    }

    companion object {
        val listaTarefasFeitas = mutableListOf<Tarefa>()
    }
}