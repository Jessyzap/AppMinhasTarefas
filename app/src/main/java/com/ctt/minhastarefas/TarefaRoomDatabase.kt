package com.ctt.minhastarefas

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ctt.minhastarefas.model.TarefaAFazer
import com.ctt.minhastarefas.model.TarefaEmProgresso
import com.ctt.minhastarefas.model.TarefaFeita

@Database(
    entities = [TarefaAFazer::class, TarefaEmProgresso::class, TarefaFeita::class],
    version = 1,
    exportSchema = false
)
abstract class TarefaRoomDatabase : RoomDatabase() {

    abstract fun tarefaDao(): TarefaDao

    companion object {
        @Volatile
        private var INSTANCE: TarefaRoomDatabase? = null

        fun getDatabase(context: Context): TarefaRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TarefaRoomDatabase::class.java,
                    "task_database"
                ).build()

                INSTANCE = instance
                instance
            }
        }
    }
}