package com.ctt.minhastarefas.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResultListener
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.ctt.minhastarefas.*
import com.ctt.minhastarefas.adapterListas.TarefasFeitasAdapter
import com.ctt.minhastarefas.database.TarefaApplication
import com.ctt.minhastarefas.model.TarefaFeita
import com.ctt.minhastarefas.ui.bottomSheets.LupaFeitasBottomSheet

class FeitasFragment : Fragment() {

    private lateinit var imagemFeitasVazia: ImageView
    private lateinit var textoFeitasVazia: TextView
    private lateinit var segundoTextoFeitasVazia: TextView
    private lateinit var botaoLupaFeitas: Button
    private lateinit var adapterTarefasFeitas: TarefasFeitasAdapter
    var listaTarefasFeitas = mutableListOf<TarefaFeita>()
    private val tarefaViewModel: TarefaFeitaViewModel by activityViewModels {
        TarefaFeitaViewModelFactory((requireActivity().application as TarefaApplication).repository)
    }

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
        swipe()
        recyclerConfig()
        listenerPesquisa()
        listener()
        botoes()
    }

    private fun listenerPesquisa() {
        setFragmentResultListener("pesquisaFeita") { _, bundle ->
            val pesquisa = bundle.get("pesquisa")

            pesquisa?.let {
                if (it == true) {
                    var lista = listOf<TarefaFeita>()
                    tarefaViewModel.listaPesquisa.observe(viewLifecycleOwner) { listaPesquisa ->
                        lista = listaPesquisa
                    }
                    adapterTarefasFeitas.submitList(lista)
                    placeholder()
                }
                bundle.clear()
            }
        }
    }

    private fun swipe() {
        val swipe = view?.findViewById<SwipeRefreshLayout>(R.id.frFazer)
        swipe?.setOnRefreshListener {
            observador()
            swipe.isRefreshing = false
        }
    }

    private fun recyclerConfig() {
        val rvFeitas = view?.findViewById<RecyclerView>(R.id.rvListaFeitas)
        adapterTarefasFeitas = TarefasFeitasAdapter(context)
        rvFeitas?.adapter = adapterTarefasFeitas
        observador()
    }

    private fun botoes() {
        val bottomSheetLupaFeitas = LupaFeitasBottomSheet()
        botaoLupaFeitas.setOnClickListener {
            activity?.supportFragmentManager?.let {
                bottomSheetLupaFeitas.show(it, "")
            }
        }
    }

    private fun placeholder() {
        view?.let {
            imagemFeitasVazia = it.findViewById(R.id.imgFeitasVazia)
            textoFeitasVazia = it.findViewById(R.id.txtFeitasVazia)
            segundoTextoFeitasVazia = it.findViewById(R.id.txt2FeitasVazia)

            if (listaTarefasFeitas.isNotEmpty())
                esconderPlaceHolder(
                    imagemFeitasVazia,
                    textoFeitasVazia,
                    segundoTextoFeitasVazia
                )
            else
                mostrarPlaceHolder(
                    imagemFeitasVazia,
                    textoFeitasVazia,
                    segundoTextoFeitasVazia
                )
        }
    }

    private fun listener() {
        setFragmentResultListener("tarefaFeita") { _, bundle ->
            val edit = bundle.get("tarefaFeitaEditar")
            val remove = bundle.get("tarefaFinalizadaRemover")

            edit?.let {
                tarefaViewModel.update(edit as TarefaFeita)
                Toast.makeText(context, "A tarefa foi alterada", Toast.LENGTH_LONG).show()
                bundle.clear()
            }

            remove?.let {
                tarefaViewModel.remove((remove as TarefaFeita).id)
                Toast.makeText(context, "A tarefa foi excluída", Toast.LENGTH_SHORT).show()
                bundle.clear()
            }
        }
    }

    private fun observador() {
        tarefaViewModel.tarefasFeitas.observe(viewLifecycleOwner) { tarefas ->
            tarefas?.let {
                listaTarefasFeitas = tarefas.toMutableList()
                adapterTarefasFeitas.submitList(listaTarefasFeitas)
                placeholder()
            }
        }
    }

}