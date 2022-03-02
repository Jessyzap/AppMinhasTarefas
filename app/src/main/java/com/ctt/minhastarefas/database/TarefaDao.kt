package com.ctt.minhastarefas.database

import androidx.room.*
import com.ctt.minhastarefas.model.TarefaAFazer
import com.ctt.minhastarefas.model.TarefaEmProgresso
import com.ctt.minhastarefas.model.TarefaFeita
import kotlinx.coroutines.flow.Flow

@Dao
interface TarefaDao {
    @Query("SELECT * FROM do_table")
    fun tarefasAfazer(): Flow<List<TarefaAFazer>>

    @Query("SELECT * FROM doing_table")
    fun tarefasEmProgresso(): Flow<List<TarefaEmProgresso>>

    @Query("SELECT * FROM done_table")
    fun tarefasFeitas(): Flow<List<TarefaFeita>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(tarefa: TarefaAFazer)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertInProgress(tarefa: TarefaEmProgresso)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertDone(tarefa: TarefaFeita)

    @Update(entity = TarefaAFazer::class)
    suspend fun update(tarefa: TarefaAFazer)

    @Update(entity = TarefaEmProgresso::class)
    suspend fun updateInProgress(tarefa: TarefaEmProgresso)

    @Update(entity = TarefaFeita::class)
    suspend fun updateDone(tarefa: TarefaFeita)

    @Query("DELETE FROM do_table WHERE id = :id")
    suspend fun deleteDo(id: Int)

    @Query("DELETE FROM doing_table WHERE id = :id")
    suspend fun deleteDoing(id: Int)

    @Query("DELETE FROM done_table WHERE id = :id")
    suspend fun deleteDone(id: Int)

    @Query("DELETE from done_table")
    suspend fun deleteAllDone()
}