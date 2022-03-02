package com.ctt.minhastarefas.repository

import androidx.annotation.WorkerThread
import com.ctt.minhastarefas.database.TarefaDao
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

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertDone(tarefa: TarefaFeita) {
        tarefaDao.insertDone(tarefa)
    }

    suspend fun update(tarefa: TarefaAFazer) {
        tarefaDao.update(tarefa)
    }

    suspend fun updateInProgress(tarefa: TarefaEmProgresso) {
        tarefaDao.updateInProgress(tarefa)
    }

    suspend fun updateDone(tarefa: TarefaFeita) {
        tarefaDao.updateDone(tarefa)
    }

    suspend fun remove(id: Int) {
        tarefaDao.deleteDo(id)
    }

    suspend fun removeDoing(id: Int) {
        tarefaDao.deleteDoing(id)
    }
    suspend fun removeDone(id: Int) {
        tarefaDao.deleteDone(id)
    }
}