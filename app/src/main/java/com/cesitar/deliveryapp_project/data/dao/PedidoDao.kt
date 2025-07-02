package com.cesitar.deliveryapp_project.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.cesitar.deliveryapp_project.data.entity.Pedido


@Dao
interface PedidoDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertarPedido(pedido: Pedido): Long

    @Query("SELECT * FROM Pedido ORDER BY id_pedido ASC")
    suspend fun listarPedidos() : List<Pedido>

    @Query("DELETE FROM Pedido")
    suspend fun eliminarTodoPedido()
}
