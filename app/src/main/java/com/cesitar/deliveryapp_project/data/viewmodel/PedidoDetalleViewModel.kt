package com.cesitar.deliveryapp_project.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.cesitar.deliveryapp_project.data.database.AppDatabase
import com.cesitar.deliveryapp_project.data.entity.PedidoDetalle
import com.cesitar.deliveryapp_project.data.repository.PedidoDetalleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
/*
class PedidoDetalleViewModel(application: Application) : AndroidViewModel(application){
    private val listarPedidoDetalle: LiveData<List<PedidoDetalle>>
    private val repository: PedidoDetalleRepository

    init {
        val pedidoDetalleDao = AppDatabase.getDatabase(application).pedidoDetalleDao()
        repository = PedidoDetalleRepository(pedidoDetalleDao)
        listarPedidoDetalle = repository.listarPedidoDetalle
    }
    fun insertarPedidoDetalle(pedidoDetalle: PedidoDetalle){
        viewModelScope.launch(Dispatchers.IO){
            repository.insertarPedidoDetalle(pedidoDetalle)
        }
    }
}
 */