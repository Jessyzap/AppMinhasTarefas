package com.ctt.minhastarefas

import androidx.annotation.WorkerThread
import com.ctt.minhastarefas.model.TarefaAFazer
import com.ctt.minhastarefas.model.TarefaEmProgresso
import com.ctt.minhastarefas.model.TarefaFeita
import kotlinx.coroutines.flow.Flow

class Repository(private val tarefaDao: TarefaDao) {

    val tarefasAfazer: Flow<List<TarefaAFazer>> = tarefaDao.tarefasAfazer()
    val tarefasEmProgresso: Flow<List<TarefaEmProgresso>> = tarefaDao.tarefasEmProgresso()
    val tarefasFeitas: Flow<List<TarefaFeita>> = tarefaDao.tarefasFeitas()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(tarefa: TarefaAFazer) {
        tarefaDao.insert(tarefa)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertInProgress(tarefa: TarefaEmProgresso) {
        tarefaDao.insertInProgress(tarefa)
    }

    suspend fun update(tarefa: TarefaAFazer) {
        tarefaDao.update(tarefa)
    }

    suspend fun remove(id: Int) {
        tarefaDao.deleteDo(id)
    }
}