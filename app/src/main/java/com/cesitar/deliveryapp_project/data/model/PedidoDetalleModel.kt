package com.cesitar.deliveryapp_project.data.model

import com.cesitar.deliveryapp_project.data.entity.PedidoDetalle


data class PedidoDetalleModel(
    val id_pedido_detalle: Int = 0,
    val id_pedido: Long,
    val id_usuario_registro: Long,
    val id_articulo: Long,
    val cantidad: Int,
    var articulo: ArticuloModel?,
    var usuario_registro: UsuarioModel?
) {
    companion object {
        fun from(pedidoDetalle: PedidoDetalle): PedidoDetalleModel {
            return PedidoDetalleModel(
                id_pedido_detalle = pedidoDetalle.id_pedido_detalle,
                id_pedido = pedidoDetalle.id_pedido,
                id_usuario_registro = pedidoDetalle.id_usuario_registro,
                id_articulo = pedidoDetalle.id_articulo,
                cantidad = pedidoDetalle.cantidad,
                articulo = null,
                usuario_registro = null,
            )
        }
    }
}