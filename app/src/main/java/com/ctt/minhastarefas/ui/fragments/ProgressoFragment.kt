package com.ctt.minhastarefas.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResultListener
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.ctt.minhastarefas.R
import com.ctt.minhastarefas.adapterListas.TarefasProgressoAdapter
import com.ctt.minhastarefas.database.TarefaApplication
import com.ctt.minhastarefas.esconderPlaceHolder
import com.ctt.minhastarefas.model.TarefaEmProgresso
import com.ctt.minhastarefas.model.TarefaFeita
import com.ctt.minhastarefas.mostrarPlaceHolder
import com.ctt.minhastarefas.ui.bottomSheets.LupaProgressoBottomSheet
import com.ctt.minhastarefas.ui.viewmodel.TarefaEmProgressoViewModel
import com.ctt.minhastarefas.ui.viewmodel.TarefaEmProgressoViewModelFactory

class ProgressoFragment : androidx.fragment.app.Fragment() {

    private lateinit var imagemProgressoVazia: ImageView
    private lateinit var textoProgressoVazia: TextView
    private lateinit var segundoTextoProgressoVazia: TextView
    private lateinit var botaoLupaProgresso: Button
    private lateinit var adapterTarefasProgresso: TarefasProgressoAdapter
    var listaTarefasProgresso = mutableListOf<TarefaEmProgresso>()
    private val tarefaViewModel: TarefaEmProgressoViewModel by activityViewModels {
        TarefaEmProgressoViewModelFactory((requireActivity().application as TarefaApplication).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val contextoProgresso = inflater.inflate(R.layout.fragment_progresso, container, false)
        botaoLupaProgresso = contextoProgresso.findViewById(R.id.btnLupaProgresso)
        return contextoProgresso
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
        setFragmentResultListener("pesquisaProgresso") { _, bundle ->
            val pesquisa = bundle.get("pesquisa")

            pesquisa?.let {
                if (it == true) {
                    var lista = listOf<TarefaEmProgresso>()
                    tarefaViewModel.listaPesquisa.observe(viewLifecycleOwner) { listaPesquisa ->
                        lista = listaPesquisa
                    }
                    adapterTarefasProgresso.submitList(lista)
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
        val rvProgresso = view?.findViewById<RecyclerView>(R.id.rvListaProgresso)
        adapterTarefasProgresso = TarefasProgressoAdapter(context)
        rvProgresso?.adapter = adapterTarefasProgresso
        observador()
    }

    private fun botoes() {
        val bottomSheetLupaProgresso = LupaProgressoBottomSheet()
        botaoLupaProgresso.setOnClickListener {
            activity?.supportFragmentManager?.let {
                bottomSheetLupaProgresso.show(it, "")
            }
        }
    }

    private fun placeholder() {
        view?.let {
            imagemProgressoVazia = it.findViewById(R.id.imgProgressoVazia)
            textoProgressoVazia = it.findViewById(R.id.txtProgressoVazia)
            segundoTextoProgressoVazia = it.findViewById(R.id.txt2ProgressoVazia)

            if (listaTarefasProgresso.isNotEmpty())
                esconderPlaceHolder(
                    imagemProgressoVazia,
                    textoProgressoVazia,
                    segundoTextoProgressoVazia
                )
            else
                mostrarPlaceHolder(
                    imagemProgressoVazia,
                    textoProgressoVazia,
                    segundoTextoProgressoVazia
                )
        }
    }

    private fun listener() {
        setFragmentResultListener("tarefaProgresso") { _, bundle ->
            val edit = bundle.get("tarefaProgressoEditar")
            val remove = bundle.get("tarefaProgressoRemover")
            val move = bundle.get("tarefaProgressoMover")

            edit?.let {
                tarefaViewModel.update(edit as TarefaEmProgresso)
                Toast.makeText(context, "A tarefa foi alterada", Toast.LENGTH_LONG).show()
                bundle.clear()
            }

            remove?.let {
                tarefaViewModel.removeDoing((remove as TarefaEmProgresso).id)
                Toast.makeText(context, "A tarefa foi excluída", Toast.LENGTH_SHORT).show()
                bundle.clear()
            }

            move?.let {
                val tarefa = move as TarefaEmProgresso

                tarefaViewModel.insertDone(
                    TarefaFeita(
                        tarefa.id,
                        tarefa.nomeTarefa,
                        tarefa.descricaoTarefa
                    )
                )
                Toast.makeText(context, "A tarefa foi finalizada", Toast.LENGTH_SHORT).show()
                tarefaViewModel.removeDoing((tarefa).id)
                bundle.clear()
            }
        }
    }

    private fun observador() {
        tarefaViewModel.tarefasEmProgresso.observe(viewLifecycleOwner) { tarefas ->
            tarefas?.let {
                listaTarefasProgresso = tarefas.toMutableList()
                adapterTarefasProgresso.submitList(listaTarefasProgresso)
                placeholder()
            }
        }
    }

}