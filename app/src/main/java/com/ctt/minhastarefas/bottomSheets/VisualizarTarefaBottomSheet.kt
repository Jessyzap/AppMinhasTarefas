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
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.ctt.minhastarefas.R
import com.ctt.minhastarefas.adapterListas.TarefasFazerAdapter
import com.ctt.minhastarefas.adapterListas.TarefasFeitasAdapter
import com.ctt.minhastarefas.fragments.FazerFragment
import com.ctt.minhastarefas.fragments.FazerFragment.Companion.listaTarefasFazer
import com.ctt.minhastarefas.fragments.FeitasFragment
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
    //Editar tarefa
    //Excluir tarefa
    //Iniciar tarefa

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

        tituloTarefa.text = tituloTarefaRecebido
        descricaoTarefa.text = descricaoTarefaRecebida

        // adicionar na lista progresso
        botaoIniciar.setOnClickListener {
            model!!.notificar("Tarefa iniciada")
            listaTarefasProgresso.add(Tarefa(tituloTarefa.text as String, descricaoTarefa.text as String))

            context?.let { it1 -> TarefasFazerAdapter(FazerFragment.listaTarefasFazer, it1) }?.removerTarefaFazer(
                FeitasFragment.listaTarefasFeitas.indexOf(
                    Tarefa(
                        tituloTarefa.text as String,
                        descricaoTarefa.text as String
                    )
                )
            )
            Toast.makeText(context, "A tarefa foi iniciada", Toast.LENGTH_SHORT).show()
        }


        botaoEditar.setOnClickListener {

            context?.let { it1 ->
                TarefasFazerAdapter(
                    listaTarefasFazer,
                    it1
                ).editarTarefaFazer(tituloTarefa.text as String, descricaoTarefa.text as String)
            }
        }



        botaoExcluir.setOnClickListener {
            Toast.makeText(context, "A tarefa foi excluída", Toast.LENGTH_SHORT).show()
            //listaTarefasProgresso.remove(Tarefa(tituloTarefa.text as String, descricaoTarefa.text as String))
        }
    }


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