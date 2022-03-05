package com.ctt.minhastarefas.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "do_table")
class TarefaAFazer(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "do_name")
    var nomeTarefa: String,

    @ColumnInfo(name = "do_description")
    var descricaoTarefa: String? = null
) : Parcelable

@Parcelize
@Entity(tableName = "doing_table")
class TarefaEmProgresso(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "doing_name")
    var nomeTarefa: String,

    @ColumnInfo(name = "doing_description")
    var descricaoTarefa: String?
) : Parcelable

@Parcelize
@Entity(tableName = "done_table")
class TarefaFeita(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "done")
    var nomeTarefa: String,

    @ColumnInfo(name = "done_description")
    var descricaoTarefa: String?
) : Parcelable