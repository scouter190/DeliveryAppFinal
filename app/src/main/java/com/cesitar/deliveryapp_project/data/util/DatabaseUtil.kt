package com.cesitar.deliveryapp_project.data.util

import com.cesitar.deliveryapp_project.data.dao.PedidoDetalleDao
import com.cesitar.deliveryapp_project.data.dao.UsuarioDao
import com.cesitar.deliveryapp_project.data.entity.Articulo
import com.cesitar.deliveryapp_project.data.entity.Pedido
import com.cesitar.deliveryapp_project.data.entity.PedidoDetalle
import com.cesitar.deliveryapp_project.data.entity.Usuario
import com.cesitar.deliveryapp_project.data.repository.ArticuloRepository
import com.cesitar.deliveryapp_project.data.repository.PedidoDetalleRepository
import com.cesitar.deliveryapp_project.data.repository.PedidoRepository
import com.cesitar.deliveryapp_project.data.repository.UsuarioRepository

class DatabaseUtil(
    private val usuarioRepository: UsuarioRepository,
    private val articuloRepository: ArticuloRepository,
    private val pedidoRepository: PedidoRepository,
    private val pedidoDetalleRepository: PedidoDetalleRepository) {

    suspend fun initData(){
        clearData();
        registerData();
    }

    private suspend fun registerData(){
        val id_usuario1 = usuarioRepository.insertarUsuario(Usuario(
            nombre_completo = "Cesar Flores Avila",
            usuario = "cesarfa",
            contraseña = "123"
        ))

        val id_usuario2 =  usuarioRepository.insertarUsuario(Usuario(
            nombre_completo = "Cristina Flores Avila",
            usuario = "cristinaafa",
            contraseña = "123"
        ))

        val id_articulo1 = articuloRepository.insertarArticulo(Articulo(
            nombre = "Coca Cola",
            stock = 10
        ))
        val id_articulo2 = articuloRepository.insertarArticulo(Articulo(
            nombre = "Pepsi",
            stock = 10
        ))

        val id_articulo3 = articuloRepository.insertarArticulo(Articulo(
            nombre = "Sprite",
            stock = 10
        ))

        val id_pedido1 = pedidoRepository.insertarPedido(Pedido(
            estado = "Pendiente",
            numero_pedido = "#123456789",
            id_usuario = id_usuario1,
            documento = "BOL-0101",
            numero_guia = "GUIA-0101",
            direccion_tienda = "Av. Lima 123",
            fecha_entrega = "",
            comentario = "",
        ))

        val id_pedido2 = pedidoRepository.insertarPedido(Pedido(
            estado = "Pendiente",
            numero_pedido = "#98754654",
            id_usuario = id_usuario2,
            documento = "BOL-0102",
            numero_guia = "GUIA-0102",
            direccion_tienda = "Av. Lima 123",
            fecha_entrega = "",
            comentario = "",
        ))

        val id_pedido3 = pedidoRepository.insertarPedido(Pedido(
            estado = "Pendiente",
            numero_pedido = "#5465465",
            id_usuario = id_usuario2,
            documento = "BOL-0103",
            numero_guia = "GUIA-0103",
            direccion_tienda = "Av. Lima 123",
            fecha_entrega = "",
            comentario = "",
        ))

        pedidoDetalleRepository.insertarPedidoDetalle(PedidoDetalle(
            id_pedido = id_pedido1,
            id_articulo = id_articulo1,
            cantidad = 3,
            id_usuario_registro =  id_usuario1,
        ))

        pedidoDetalleRepository.insertarPedidoDetalle(PedidoDetalle(
            id_pedido = id_pedido1,
            id_articulo = id_articulo2,
            cantidad = 2,
            id_usuario_registro =  id_usuario1,
        ))

        pedidoDetalleRepository.insertarPedidoDetalle(PedidoDetalle(
            id_pedido = id_pedido1,
            id_articulo = id_articulo3,
            cantidad = 1,
            id_usuario_registro =  id_usuario1))

        pedidoDetalleRepository.insertarPedidoDetalle(PedidoDetalle(
            id_pedido = id_pedido2,
            id_articulo = id_articulo1,
            cantidad = 3,
            id_usuario_registro =  id_usuario2,
        ))

        pedidoDetalleRepository.insertarPedidoDetalle(PedidoDetalle(
            id_pedido = id_pedido3,
            id_articulo = id_articulo2,
            cantidad = 2,
            id_usuario_registro =  id_usuario2,
        ))
    }



    private suspend fun clearData() {
        usuarioRepository.eliminarTodoUsuario();
        articuloRepository.eliminarTodoArticulo();
        pedidoRepository.eliminarTodoPedido();
        pedidoDetalleRepository.eliminarTodoPedidoDetalle();
    }
}