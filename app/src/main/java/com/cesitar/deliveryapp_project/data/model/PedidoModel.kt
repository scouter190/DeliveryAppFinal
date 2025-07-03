package com.cesitar.deliveryapp_project.data.model

import com.cesitar.deliveryapp_project.data.entity.Pedido


data class PedidoModel(
    val id_pedido: Int = 0,
    val estado: String = "pendiente",
    val numero_pedido: String,
    val direccion_tienda: String,
    val id_usuario: Long,
    val numero_guia: String?,
    val comentario: String?,
    val fecha_entrega: String?,
    val documento: String?,
    val firma: String?,
    val foto: String?,
    var pedidoDetalle: List<PedidoDetalleModel>,
    var usuario: UsuarioModel?
) {
    companion object {
        fun from(pedido: Pedido): PedidoModel {
            return PedidoModel(
                id_pedido = pedido.id_pedido,
                estado = pedido.estado,
                numero_pedido = pedido.numero_pedido,
                direccion_tienda = pedido.direccion_tienda,
                id_usuario = pedido.id_usuario,
                fecha_entrega = pedido.fecha_entrega,
                comentario = pedido.comentario,
                numero_guia = pedido.numero_guia,
                documento = pedido.documento,
                firma = pedido.firma,
                foto = pedido.foto,
                pedidoDetalle = emptyList(),
                usuario = null
            )
        }
    }
}

