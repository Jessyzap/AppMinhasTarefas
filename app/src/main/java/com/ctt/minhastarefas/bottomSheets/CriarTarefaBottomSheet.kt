package com.ctt.minhastarefas.bottomSheets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.ctt.minhastarefas.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_fazer.*

class CriarTarefaBottomSheet : BottomSheetDialogFragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    interface FragmentListener {
        fun getView(view: View?)
    }

    var mFragmentListener: FragmentListener? = null

    fun bottomSheetFragment(listener: FragmentListener?) {
        mFragmentListener = listener
        return bottomSheetFragment(listener)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.bottom_sheet_criar_tarefa, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mFragmentListener?.getView(view)

//        btn.setOnClickListener {
//          Toast.makeText(context,"vc clicou",Toast.LENGTH_SHORT).show()     botão interno
//        }
    }
}