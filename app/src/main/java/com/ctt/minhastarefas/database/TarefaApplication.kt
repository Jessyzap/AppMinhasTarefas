package com.ctt.minhastarefas.database

import android.app.Application
import com.ctt.minhastarefas.repository.Repository

class TarefaApplication : Application() {
    val database by lazy { TarefaRoomDatabase.getDatabase(this) }
    val repository by lazy { Repository(database.tarefaDao()) }
}