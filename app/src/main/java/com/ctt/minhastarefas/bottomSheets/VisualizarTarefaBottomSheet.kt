package com.ctt.minhastarefas.bottomSheets

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProviders
import com.ctt.minhastarefas.R
import com.ctt.minhastarefas.adapterListas.TarefasFazerAdapter
import com.ctt.minhastarefas.adapterListas.TarefasProgressoAdapter
import com.ctt.minhastarefas.fragments.FazerFragment
import com.ctt.minhastarefas.fragments.FazerFragment.Companion.listaTarefasFazer
import com.ctt.minhastarefas.fragments.ProgressoFragment
import com.ctt.minhastarefas.fragments.ProgressoFragment.Companion.listaTarefasProgresso
import com.ctt.minhastarefas.model.Tarefa
import com.ctt.minhastarefas.model.msgViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class VisualizarTarefaBottomSheet() : BottomSheetDialogFragment() {

    private var model: msgViewModel? = null
    private lateinit var botaoIniciar: Button
    private lateinit var botaoExcluir: TextView
    private lateinit var botaoEditar: ImageView

//    companion object {
//        const val TAG = "VisualizarTarefaBottomSheet"
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(BottomSheetDialogFragment.STYLE_NORMAL, R.style.MyBottomSheetDialogTheme)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val contextoVisualizar = inflater.inflate(R.layout.bottom_sheet_visualizar_tarefa, container, false)
        botaoIniciar = contextoVisualizar.findViewById(R.id.btnIniciarTarefa)
        botaoExcluir = contextoVisualizar.findViewById(R.id.btnExcluirTarefaVisualizar)
        botaoEditar = contextoVisualizar.findViewById(R.id.btnEditarVisualizar)
        return contextoVisualizar
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        model = ViewModelProviders.of(activity!!).get(msgViewModel::class.java)

        val tituloTarefa = view.findViewById<TextView>(R.id.txtTituloVisualizar)
        val descricaoTarefa = view.findViewById<TextView>(R.id.txtDescricaoVisualizar)

        val tituloTarefaRecebido = getArguments()?.getString("TITULO")
        val descricaoTarefaRecebida = getArguments()?.getString("DESCRICAO")
        val posicaoTarefaRecebida = getArguments()?.getString("POSICAO")?.toInt()

        tituloTarefa.text = tituloTarefaRecebido
        descricaoTarefa.text = descricaoTarefaRecebida


        // Adicionar tarefa na lista progresso
        botaoIniciar.setOnClickListener {

            context?.let { it1 -> TarefasProgressoAdapter(listaTarefasProgresso, it1).adicionarTarefaProgresso(Tarefa(tituloTarefa.text as String,descricaoTarefa.text as String)) }

            // Notificar backgroung FragmentFazer
            model!!.notificar("Tarefa iniciada")

            // Atualizar ProgressoFragment
            val transicao: FragmentTransaction = getFragmentManager()!!.beginTransaction()
            transicao.replace(R.id.frProgresso, ProgressoFragment())
            transicao.commit()


            // Limpar tarefa da listaTarefasFazer
            if (posicaoTarefaRecebida != null) {
                context?.let { it1 -> TarefasFazerAdapter(FazerFragment.listaTarefasFazer, it1) }?.removerTarefaFazer(
                    posicaoTarefaRecebida
                )

                // Atualizar FazerFragment
                val transicaoFazer: FragmentTransaction = getFragmentManager()!!.beginTransaction()
                transicaoFazer.replace(R.id.frFazer, FazerFragment())
                transicaoFazer.commit()
            }

            Toast.makeText(
                context, "A tarefa foi iniciada", Toast.LENGTH_SHORT
            ).show()
            dismiss()
        }


        botaoEditar.setOnClickListener {

            context?.let { it1 ->
                if (posicaoTarefaRecebida != null) {
                    TarefasFazerAdapter(
                        listaTarefasFazer,
                        it1
                    ).editarTarefaFazer(tituloTarefa.text as String, descricaoTarefa.text as String,posicaoTarefaRecebida )
                }
            }
            dismiss()
        }

        botaoExcluir.setOnClickListener {

            if (posicaoTarefaRecebida != null) {
                context?.let { it1 -> TarefasFazerAdapter(listaTarefasFazer, it1) }
                    ?.removerTarefaFazer(
                        posicaoTarefaRecebida
                    )
            }

            val transicao: FragmentTransaction = getFragmentManager()!!.beginTransaction()
            transicao.replace(R.id.frFazer, FazerFragment())
            transicao.commit()

            Toast.makeText(context, "A tarefa foi excluída", Toast.LENGTH_SHORT).show()
            dismiss()
        }
    }

    // Definir o comportamento da bottom sheet
    override fun onStart() {
        super.onStart()
        val sheetContainer = requireView().parent as? ViewGroup ?: return
        sheetContainer.layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return BottomSheetDialog(requireContext(), theme).apply {
            behavior.state = BottomSheetBehavior.STATE_HALF_EXPANDED
            behavior.halfExpandedRatio = 0.90F
        }
    }
}