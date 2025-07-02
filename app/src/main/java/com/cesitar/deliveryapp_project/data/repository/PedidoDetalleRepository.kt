package com.cesitar.deliveryapp_project.data.repository

import com.cesitar.deliveryapp_project.data.dao.PedidoDetalleDao
import com.cesitar.deliveryapp_project.data.entity.PedidoDetalle

class PedidoDetalleRepository(private val pedidoDetalleDao: PedidoDetalleDao) {

    suspend fun listarPedidoDetalle(id_pedido: Long): List<PedidoDetalle>{
        return pedidoDetalleDao.listarPedidoDetalle(id_pedido);
    }

    suspend fun insertarPedidoDetalle(pedidoDetalle: PedidoDetalle): Long {
        return pedidoDetalleDao.insertarPedidoDetalle(pedidoDetalle)
    }

    suspend fun eliminarTodoPedidoDetalle(){
        pedidoDetalleDao.eliminarTodoPedidoDetalle()
    }
}