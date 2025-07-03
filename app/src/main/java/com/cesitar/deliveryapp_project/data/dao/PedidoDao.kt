package com.cesitar.deliveryapp_project.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.cesitar.deliveryapp_project.data.entity.Pedido
import com.cesitar.deliveryapp_project.data.entity.Usuario


@Dao
interface PedidoDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertarPedido(pedido: Pedido): Long

    @Update()
    suspend fun updatePedido(pedido: Pedido)

    @Query("SELECT * FROM Pedido WHERE id_pedido = :id_pedido")
    suspend fun obtenerPedidoPorId(id_pedido: Long): Pedido?

    @Query("SELECT * FROM Pedido ORDER BY id_pedido ASC")
    suspend fun listarPedidos() : List<Pedido>

    @Query("DELETE FROM Pedido")
    suspend fun eliminarTodoPedido()
}
