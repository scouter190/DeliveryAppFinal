package com.cesitar.deliveryapp_project.data.repository

import androidx.lifecycle.LiveData
import com.cesitar.deliveryapp_project.data.dao.PedidoDao
import com.cesitar.deliveryapp_project.data.entity.Pedido

class PedidoRepository(private val pedidoDao: PedidoDao) {

    suspend fun listarPedidos(): List<Pedido> {
        return pedidoDao.listarPedidos()
    }
    suspend fun insertarPedido(pedido: Pedido): Long {
        return pedidoDao.insertarPedido(pedido)
    }
    suspend fun eliminarTodoPedido(){
        pedidoDao.eliminarTodoPedido()
    }

}
