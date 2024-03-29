package com.ctt.minhastarefas.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.ctt.minhastarefas.R
import com.ctt.minhastarefas.ui.PagerAdapter
import com.google.android.material.tabs.TabLayout


class PrincipalActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)

        val tabLayout = findViewById<TabLayout>(R.id.tabLayout)
        val viewPager = findViewById<ViewPager>(R.id.viewPager)

        viewPager.adapter = PagerAdapter(supportFragmentManager)
        tabLayout.setupWithViewPager(viewPager)
    }

    override fun onStop() {
        super.onStop()
        val saida = Intent(this, SaidaActivity::class.java)
        startActivity(saida)
        finish()
    }

}