package com.example.lostnfound.ui

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.lostnfound.network.request.ItemRequest
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.lostnfound.ItemApplication
import com.example.lostnfound.data.ItemRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


sealed interface _uiState {
    data class Success(val items:List<ItemRequest>) : _uiState
    data object Error : _uiState
    data object Loading : _uiState
}

class ItemViewModel(private val itemRepository: ItemRepository) : ViewModel() {
    var uiState:_uiState by mutableStateOf(_uiState.Loading)
        private set

    private val _itemName = MutableStateFlow("")
    val itemName:StateFlow<String> = _itemName.asStateFlow()

    init {
        getItems()
    }

    fun getItems() {
        viewModelScope.launch {
            uiState = _uiState.Loading
            try {
                val items = itemRepository.getItems()
                uiState = _uiState.Success(items)
            } catch (e: Exception) {
                uiState = _uiState.Error
            }
        }
    }

    fun getItem(name:String?){
        viewModelScope.launch {
            uiState = _uiState.Loading
            uiState = try {
                _uiState.Success(listOf(itemRepository.getItem(name)))
            }catch (e: Exception) {
                _uiState.Error
            }
        }
    }

    fun createItem(item:ItemRequest){
        viewModelScope.launch {
            uiState = _uiState.Loading
            try {
                itemRepository.createItem(item)
                val updatedItems = itemRepository.getItems()
                uiState = _uiState.Success(updatedItems)
                _itemName.value = item.name
            } catch (e: Exception) {
                uiState = _uiState.Error
                Log.e("DriverViewModel", "Error creating driver: ${e.message}")
            }
        }
    }

    fun deleteItem(name: String?){
        viewModelScope.launch {
            uiState = _uiState.Loading
            try {
                itemRepository.deleteItem(name)
                val updatedItems = itemRepository.getItems()
                uiState = _uiState.Success(updatedItems)
                if (name != null) {
                    _itemName.value = name
                }
            }catch (e: Exception) {
                uiState = _uiState.Error
                Log.e("DriverViewModel", "Error deleting driver: ${e.message}")
            }
        }
    }

    companion object{
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val app = this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as ItemApplication
                ItemViewModel(app.container.itemRepository)
            }
        }
    }
}