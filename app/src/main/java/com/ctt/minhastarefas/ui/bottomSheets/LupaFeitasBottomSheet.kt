package com.ctt.minhastarefas.ui.bottomSheets

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResult
import com.ctt.minhastarefas.R
import com.ctt.minhastarefas.TarefaFeitaViewModel
import com.ctt.minhastarefas.database.TarefaApplication
import com.ctt.minhastarefas.model.TarefaFeita
import com.ctt.minhastarefas.ui.viewmodel.TarefaViewModelFactory
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class LupaFeitasBottomSheet : BottomSheetDialogFragment() {

    private lateinit var botaoProcurarFeita: Button
    private lateinit var nomeTarefa: EditText
    private lateinit var tituloProvisorio: TextView
    private val tarefaViewModel: TarefaFeitaViewModel by activityViewModels {
        TarefaViewModelFactory((requireActivity().application as TarefaApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.MyBottomSheetDialogTheme)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val contextoPesquisarFeita =
            inflater.inflate(R.layout.bottom_sheet_lupa, container, false)
        botaoProcurarFeita = contextoPesquisarFeita.findViewById(R.id.btnProcurar)
        return contextoPesquisarFeita
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        nomeTarefa = view.findViewById(R.id.txtProcurarTituloTarefa)
        tituloProvisorio = view.findViewById(R.id.tituloProvisorio)
        tituloProvisorio.text = "Pesquise por tarefas feitas"

        botaoProcurarFeita.setOnClickListener {

            val titulo = nomeTarefa.text.toString()
            var lista = listOf<TarefaFeita>()

            tarefaViewModel.tarefasFeitas.observe(viewLifecycleOwner) { tarefas ->
                lista = tarefas
            }

            val listaTemporaria = lista.filter {
                it.nomeTarefa.contains(titulo)
            }

            tarefaViewModel.popularListaPesquisa(listaTemporaria)
            val bundle = Bundle()
            bundle.putBoolean("pesquisa", listaTemporaria.isNotEmpty())
            setFragmentResult("pesquisaFeita", bundle)

            when {
                titulo.isEmpty() -> nomeTarefa.error = "Insira um título para pesquisar"
                listaTemporaria.isEmpty() -> Toast.makeText(
                    context,
                    "Essa tarefa não está na lista!",
                    Toast.LENGTH_SHORT
                ).show()
            }
            dismiss()
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return BottomSheetDialog(requireContext(), theme).apply {
            behavior.state = BottomSheetBehavior.STATE_HALF_EXPANDED
            behavior.halfExpandedRatio = 0.50F
        }
    }

}