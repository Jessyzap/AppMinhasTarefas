package com.ctt.minhastarefas

import androidx.lifecycle.*
import com.ctt.minhastarefas.model.TarefaFeita
import com.ctt.minhastarefas.repository.Repository
import kotlinx.coroutines.launch

class TarefaFeitaViewModel(
    private val repository: Repository
) : ViewModel() {

    val tarefasFeitas: LiveData<List<TarefaFeita>> =
        repository.tarefasFeitas.asLiveData()

    private val _listaPesquisa = MutableLiveData<List<TarefaFeita>>()
    val listaPesquisa: MutableLiveData<List<TarefaFeita>>
        get() = _listaPesquisa

    fun popularListaPesquisa(listaPesquisa: List<TarefaFeita>) {
        _listaPesquisa.value = listaPesquisa
    }

    fun update(tarefa: TarefaFeita) = viewModelScope.launch {
        repository.updateDone(tarefa)
    }

    fun remove(id: Int) = viewModelScope.launch {
        repository.removeDone(id)
    }

}

class TarefaFeitaViewModelFactory(private val repository: Repository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TarefaFeitaViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TarefaFeitaViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}