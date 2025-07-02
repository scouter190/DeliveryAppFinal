package com.cesitar.deliveryapp_project.data.dao

import androidx.room.*
import com.cesitar.deliveryapp_project.data.entity.PedidoDetalle
import androidx.room.Insert
import androidx.lifecycle.LiveData



@Dao
interface PedidoDetalleDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertarPedidoDetalle(pedidoDetalle: PedidoDetalle): Long

    @Query("SELECT * FROM Pedido_Detalle WHERE id_pedido = :id_pedido")
    suspend fun listarPedidoDetalle(id_pedido: Long): List<PedidoDetalle>

    @Query("DELETE FROM Pedido_Detalle")
    suspend fun eliminarTodoPedidoDetalle()

}