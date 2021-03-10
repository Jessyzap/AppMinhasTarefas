package com.ctt.minhastarefas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.viewpager.widget.ViewPager
import com.ctt.minhastarefas.bottomSheets.CriarTarefaBottomSheet
import com.google.android.material.button.MaterialButton
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_fazer.*

class PrincipalActivity : AppCompatActivity(), CriarTarefaBottomSheet.FragmentListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)

        val tabLayout = findViewById<TabLayout>(R.id.tabLayout)
        val viewPager = findViewById<ViewPager>(R.id.viewPager)

        viewPager.adapter = PagerAdapter(supportFragmentManager)
        tabLayout.setupWithViewPager(viewPager)

//        val bottomSheetCriarTarefa = CriarTarefaBottomSheet()
//        btnAdicionar.setOnClickListener{
//            bottomSheetCriarTarefa.show(supportFragmentManager,"BottomSheetDialog")

//        }
    }

    override fun getView(view: View?) {
        val bottomSheetCriarTarefa = CriarTarefaBottomSheet()
        btnAdicionar.setOnClickListener {
            bottomSheetCriarTarefa.show(supportFragmentManager, "BottomSheetDialog")
        }
    }
}