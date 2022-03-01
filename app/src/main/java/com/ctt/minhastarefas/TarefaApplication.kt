package com.ctt.minhastarefas

import android.app.Application

class TarefaApplication : Application() {
    val database by lazy { TarefaRoomDatabase.getDatabase(this) }
    val repository by lazy { Repository(database.tarefaDao()) }
}