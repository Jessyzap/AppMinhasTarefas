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
import com.ctt.minhastarefas.R
import com.ctt.minhastarefas.adapterListas.TarefasFazerAdapter
import com.ctt.minhastarefas.database.TarefaApplication
import com.ctt.minhastarefas.esconderPlaceHolder
import com.ctt.minhastarefas.model.TarefaAFazer
import com.ctt.minhastarefas.model.TarefaEmProgresso
import com.ctt.minhastarefas.mostrarPlaceHolder
import com.ctt.minhastarefas.ui.bottomSheets.CriarTarefaBottomSheet
import com.ctt.minhastarefas.ui.bottomSheets.LupaFazerBottomSheet
import com.ctt.minhastarefas.ui.viewmodel.TarefaViewModel
import com.ctt.minhastarefas.ui.viewmodel.TarefaViewModelFactory

class FazerFragment : Fragment() {

    private lateinit var imagemFazerVazia: ImageView
    private lateinit var textoFazerVazia: TextView
    private lateinit var segundoTextoFazerVazia: TextView
    private lateinit var botao: Button
    private lateinit var botaoLupaFazer: Button
    private lateinit var adapterTarefasFazer: TarefasFazerAdapter
    var listaTarefasFazer = mutableListOf<TarefaAFazer>()
    private val tarefaViewModel: TarefaViewModel by activityViewModels {
        TarefaViewModelFactory((requireActivity().application as TarefaApplication).repository)
    }

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
        swipe()
        recyclerConfig()
        listenerPesquisa()
        listener()
        botoes()
    }

    private fun listenerPesquisa() {
        setFragmentResultListener("pesquisaFazer") { _, bundle ->
            val pesquisa = bundle.get("pesquisa")

            pesquisa?.let {
                if (it == true) {
                    var lista = listOf<TarefaAFazer>()
                    tarefaViewModel.listaPesquisa.observe(viewLifecycleOwner) { listaPesquisa ->
                        lista = listaPesquisa
                    }
                    adapterTarefasFazer.submitList(lista)
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
        val rvFazer = view?.findViewById<RecyclerView>(R.id.rvListaFazer)
        adapterTarefasFazer = TarefasFazerAdapter(context)
        rvFazer?.adapter = adapterTarefasFazer
        observador()
    }

    private fun botoes() {
        val botao = view?.findViewById<Button>(R.id.btnAdicionar)
        val bottomSheetCriar = CriarTarefaBottomSheet()
        val bottomSheetLupaFazer = LupaFazerBottomSheet()

        botao?.setOnClickListener {
            activity?.supportFragmentManager?.let {
                bottomSheetCriar.show(it, "")
            }
        }

        botaoLupaFazer.setOnClickListener {
            activity?.supportFragmentManager?.let {
                bottomSheetLupaFazer.show(it, "")
            }
        }
    }

    private fun placeholder() {
        view?.let {
            imagemFazerVazia = it.findViewById(R.id.imgFazerVazia)
            textoFazerVazia = it.findViewById(R.id.txtFazerVazia)
            segundoTextoFazerVazia = it.findViewById(R.id.txt2FazerVazia)

            if (listaTarefasFazer.isNotEmpty())
                esconderPlaceHolder(
                    imagemFazerVazia,
                    textoFazerVazia,
                    segundoTextoFazerVazia
                )
            else
                mostrarPlaceHolder(
                    imagemFazerVazia,
                    textoFazerVazia,
                    segundoTextoFazerVazia
                )
        }
    }

    private fun listener() {
        setFragmentResultListener("tarefaFazer") { _, bundle ->
            val insert = bundle.get("tarefaCriar")
            val edit = bundle.get("tarefaAfazerEditar")
            val remove = bundle.get("tarefaFazerRemover")
            val move = bundle.get("tarefaFazerMover")

            insert?.let {
                tarefaViewModel.insert(insert as TarefaAFazer)
                Toast.makeText(context, "Tarefa cadastrada!", Toast.LENGTH_SHORT).show()
                bundle.clear()
            }

            edit?.let {
                tarefaViewModel.update(edit as TarefaAFazer)
                Toast.makeText(context, "A tarefa foi alterada", Toast.LENGTH_LONG).show()
                bundle.clear()
            }

            remove?.let {
                tarefaViewModel.remove((remove as TarefaAFazer).id)
                Toast.makeText(context, "A tarefa foi excluída", Toast.LENGTH_SHORT).show()
                bundle.clear()
            }

            move?.let {
                val tarefa = move as TarefaAFazer

                tarefaViewModel.insertInProgress(
                    TarefaEmProgresso(
                        tarefa.id,
                        tarefa.nomeTarefa,
                        tarefa.descricaoTarefa
                    )
                )
                Toast.makeText(context, "A tarefa foi iniciada", Toast.LENGTH_SHORT).show()
                tarefaViewModel.remove((tarefa).id)
                bundle.clear()
            }
        }
    }

    private fun observador() {
        tarefaViewModel.tarefasAfazer.observe(viewLifecycleOwner) { tarefas ->
            tarefas?.let {
                listaTarefasFazer = tarefas.toMutableList()
                adapterTarefasFazer.submitList(listaTarefasFazer)
                placeholder()
            }
        }
    }

}