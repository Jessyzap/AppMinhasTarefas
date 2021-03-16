package com.ctt.minhastarefas.bottomSheets

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.ctt.minhastarefas.R
import com.ctt.minhastarefas.fragments.FazerFragment.Companion.listaTarefasFazer
import com.ctt.minhastarefas.model.Tarefa
import com.ctt.minhastarefas.model.msgViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class CriarTarefaBottomSheet : BottomSheetDialogFragment() {

    private lateinit var nomeTarefa: EditText
    private lateinit var descricaoTarefa: EditText
    private lateinit var botaoCriarTarefa: Button

//    companion object {
//        const val TAG = "CriarTarefaBottomSheet"
//    }

    private var model: msgViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(BottomSheetDialogFragment.STYLE_NORMAL, R.style.MyBottomSheetDialogTheme)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.bottom_sheet_criar_tarefa, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val model = ViewModelProviders.of(activity!!).get(msgViewModel::class.java)

        nomeTarefa = view.findViewById(R.id.txtTituloCriarTarefa)
        descricaoTarefa = view.findViewById(R.id.txtDescricaoCriarTarefa)
        botaoCriarTarefa = view.findViewById(R.id.btnCriarTarefa)

        botaoCriarTarefa.setOnClickListener {

            val titulo = nomeTarefa.text.toString()
            val descricao = descricaoTarefa.text.toString()

            if (titulo == "") {
                nomeTarefa.error = "Insira um título para a sua tarefa"
            } else {


                //  context?.let { it1 -> TarefasFazerAdapter(listaTarefasFazer, it1).adicionarTarefa(Tarefa(titulo,descricao)) }
                listaTarefasFazer.add(Tarefa(titulo, descricao))
                model.notificar("Tarefa Criar")


               // model.dadosTarefa(Tarefa(titulo,descricao))
                // context?.let { it1 -> TarefasFazerAdapter(listaTarefasFazer, it1).adicionarTarefa(Tarefa(titulo, descricao)) }
                Toast.makeText(context, "Tarefa cadastrada!", Toast.LENGTH_SHORT).show()
                dismiss()

                //onDestroy()

//                FragmentActivity.startActivityFromFragment()
//                Fragment.startActivityForResult()
//                Activity.startActivityFromFragment()
//                startActivity()

//                var intent = Intent(getActivity(), FazerFragment.class)
//                startActivity(intent)


//
            //                listaTarefasFazer.add(Tarefa(titulo, descricao))
//
//                val bottomSheetVisualizar = FragmentFazer
//                val bundle = Bundle()
//                bottomSheetVisualizar.setArguments(bundle)
//                bottomSheetVisualizar.show((context as AppCompatActivity).supportFragmentManager,"")


                //inflar fragment


//                btnBottomSheetModal.setOnClickListener {
//                    CustomBottomSheetDialogFragment().apply {
//                        show(supportFragmentManager, CustomBottomSheetDialogFragment.TAG)


//                        BottomSheetDialog(requireContext(), theme).apply {
//                            fragmentManager?.let { it1 -> show(it1, FazerFragment.TAG) }
//                        }
//
//                bottomSheetFragment.dismiss()
//                bottomSheetFragment.show(getSupportFragmentManager(), TAG)
//
//               val  sheetDialog = context?.let { it1 -> BottomSheetDialog(it1) };
//                sheetDialog.setContentView(viewBottom);


               //val  fragment = FazerFragment()
                //val viewDialog = fragment.view

//                val contentView = View.inflate(context, R.layout.fragment_fazer, null)
//                dialog!!.setContentView(contentView)




//                val contentView = View.inflate(context, R.layout.fragment_station_detail, null)
//                dialog!!.setContentView(contentView)




                //dismiss()
            }
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