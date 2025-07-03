package com.cesitar.deliveryapp_project.data.entity


import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Ignore
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity
    (
    tableName = "PEDIDO",
    foreignKeys = [
        ForeignKey(
            entity = Usuario::class,
            parentColumns = ["id_usuario"],
            childColumns = ["id_usuario"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)

data class Pedido(
    @PrimaryKey(autoGenerate = true)
    val id_pedido: Int = 0,
    var estado: String = "pendiente",
    val numero_pedido: String,
    val direccion_tienda: String,
    val id_usuario: Long,
    val numero_guia: String?,
    val comentario: String?,
    val fecha_entrega: String?,
    val documento: String?,
    var foto: String?,
    var firma: String?
)
