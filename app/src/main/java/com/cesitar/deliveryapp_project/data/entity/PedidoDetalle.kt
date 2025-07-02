package com.cesitar.deliveryapp_project.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.util.Date

@Entity(
    tableName = "PEDIDO_DETALLE",
    foreignKeys = [
        ForeignKey(
            entity = Pedido::class,
            parentColumns = ["id_pedido"],
            childColumns = ["id_pedido"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Usuario::class,
            parentColumns = ["id_usuario"],
            childColumns = ["id_usuario_registro"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Articulo::class,
            parentColumns = ["id_articulo"],
            childColumns = ["id_articulo"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class PedidoDetalle(
    @PrimaryKey(autoGenerate = true)
    val id_pedido_detalle: Int = 0,
    val id_pedido: Long,
    val id_usuario_registro: Long,
    val id_articulo: Long,
    val cantidad: Int
)
