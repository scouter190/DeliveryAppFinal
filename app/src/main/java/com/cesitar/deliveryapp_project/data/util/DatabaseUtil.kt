package com.cesitar.deliveryapp_project.data.util

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
    private val pedidoDetalleRepository: PedidoDetalleRepository
) {

    suspend fun initData() {
        clearData()
        registerData()
    }

    private suspend fun registerData() {
        // Usuarios (nombre_completo, usuario, contraseña)
        val id_usuario1 = usuarioRepository.insertarUsuario(
            Usuario(
                nombre_completo = "Cesar Flores Avila",
                usuario = "73115930",
                contraseña = "73115930"
            )
        )
        val id_usuario2 = usuarioRepository.insertarUsuario(
            Usuario(
                nombre_completo = "Celso Avila",
                usuario = "18005619",
                contraseña = "18005619"
            )
        )
        val id_usuario3 = usuarioRepository.insertarUsuario(
            Usuario(
                nombre_completo = "Luis Andrade",
                usuario = "luisan",
                contraseña = ""
            )
        )
        val id_usuario4 = usuarioRepository.insertarUsuario(
            Usuario(
                nombre_completo = "Diana Torres",
                usuario = "dianat",
                contraseña = ""
            )
        )
        val id_usuario5 = usuarioRepository.insertarUsuario(
            Usuario(
                nombre_completo = "Rosa Sánchez",
                usuario = "rosas",
                contraseña = ""
            )
        )

        val id_usuario6 = usuarioRepository.insertarUsuario(
            Usuario(
                nombre_completo = "Kevin Ramos Luján",
                usuario = "kevinr",
                contraseña = ""
            )
        )

        val id_usuario7 = usuarioRepository.insertarUsuario(
            Usuario(
                nombre_completo = "Andrea Castillo Torres",
                usuario = "andreac",
                contraseña = ""
            )
        )

        val id_usuario8 = usuarioRepository.insertarUsuario(
            Usuario(
                nombre_completo = "Carlos Peña Ruiz",
                usuario = "carlosp",
                contraseña = ""
            )
        )

        val id_usuario9 = usuarioRepository.insertarUsuario(
            Usuario(
                nombre_completo = "Melanie Suárez Díaz",
                usuario = "melaniesd",
                contraseña = ""
            )
        )

        val id_usuario10 = usuarioRepository.insertarUsuario(
            Usuario(
                nombre_completo = "Jorge Meza Guevara",
                usuario = "jorgemg",
                contraseña = ""
            )
        )



        // Artículos (nombre, stock, precio)
        val id_articulo1 = articuloRepository.insertarArticulo(
            Articulo(
                nombre = "Laptop Lenovo ThinkPad",
                stock = 10,
                precio = 3500.0f
            )
        )
        val id_articulo2 = articuloRepository.insertarArticulo(
            Articulo(
                nombre = "Smartphone Samsung Galaxy",
                stock = 15,
                precio = 2800.0f
            )
        )
        val id_articulo3 = articuloRepository.insertarArticulo(
            Articulo(
                nombre = "Mouse Logitech Inalámbrico",
                stock = 50,
                precio = 85.0f
            )
        )
        val id_articulo4 = articuloRepository.insertarArticulo(
            Articulo(
                nombre = "Monitor LG 24'' IPS",
                stock = 20,
                precio = 700.0f
            )
        )
        val id_articulo5 = articuloRepository.insertarArticulo(
            Articulo(
                nombre = "Teclado Mecánico Redragon",
                stock = 30,
                precio = 150.0f
            )
        )

        // Pedidos (estado, numero_pedido, id_usuario, documento, numero_guia, direccion_tienda, fecha_entrega, comentario, foto, firma)
        val id_pedido1 = pedidoRepository.insertarPedido(
            Pedido(
                estado = "Pendiente",
                numero_pedido = "#123456789",
                id_usuario = id_usuario6,
                documento = "BOL-0101",
                numero_guia = "GUIA-0101",
                direccion_tienda = "Av. Lima 123",
                fecha_entrega = "2025-07-05",
                comentario = "",
                foto = null,
                firma = null
            )
        )
        val id_pedido2 = pedidoRepository.insertarPedido(
            Pedido(
                estado = "Entregado",
                numero_pedido = "#98754654",
                id_usuario = id_usuario7,
                documento = "BOL-0102",
                numero_guia = "GUIA-0102",
                direccion_tienda = "Av. Arequipa 456",
                fecha_entrega = "2025-07-04",
                comentario = "",
                foto = null,
                firma = null
            )
        )
        val id_pedido3 = pedidoRepository.insertarPedido(
            Pedido(
                estado = "No entregado",
                numero_pedido = "#5465465",
                id_usuario = id_usuario4,
                documento = "BOL-0103",
                numero_guia = "GUIA-0103",
                direccion_tienda = "Av. Brasil 789",
                fecha_entrega = "2025-07-06",
                comentario = "",
                foto = null,
                firma = null
            )
        )
        val id_pedido4 = pedidoRepository.insertarPedido(
            Pedido(
                estado = "Entregado Parcial",
                numero_pedido = "#33445566",
                id_usuario = id_usuario9,
                documento = "BOL-0104",
                numero_guia = "GUIA-0104",
                direccion_tienda = "Av. Salaverry 159",
                fecha_entrega = "2025-07-03",
                comentario = "",
                foto = null,
                firma = null
            )
        )
        val id_pedido5 = pedidoRepository.insertarPedido(
            Pedido(
                estado = "Pendiente",
                numero_pedido = "#99887766",
                id_usuario = id_usuario5,
                documento = "BOL-0105",
                numero_guia = "GUIA-0105",
                direccion_tienda = "Av. Universitaria 321",
                fecha_entrega = "2025-07-02",
                comentario = "",
                foto = null,
                firma = null
            )
        )

        // PedidoDetalle (id_pedido, id_articulo, cantidad, id_usuario_registro)
        pedidoDetalleRepository.insertarPedidoDetalle(
            PedidoDetalle(
                id_pedido = id_pedido1,
                id_articulo = id_articulo1,
                cantidad = 1,
                id_usuario_registro = id_usuario1
            )
        )
        pedidoDetalleRepository.insertarPedidoDetalle(
            PedidoDetalle(
                id_pedido = id_pedido1,
                id_articulo = id_articulo3,
                cantidad = 2,
                id_usuario_registro = id_usuario1
            )
        )
        pedidoDetalleRepository.insertarPedidoDetalle(
            PedidoDetalle(
                id_pedido = id_pedido2,
                id_articulo = id_articulo2,
                cantidad = 1,
                id_usuario_registro = id_usuario2
            )
        )
        pedidoDetalleRepository.insertarPedidoDetalle(
            PedidoDetalle(
                id_pedido = id_pedido2,
                id_articulo = id_articulo4,
                cantidad = 1,
                id_usuario_registro = id_usuario2
            )
        )
        pedidoDetalleRepository.insertarPedidoDetalle(
            PedidoDetalle(
                id_pedido = id_pedido3,
                id_articulo = id_articulo5,
                cantidad = 1,
                id_usuario_registro = id_usuario3
            )
        )
        pedidoDetalleRepository.insertarPedidoDetalle(
            PedidoDetalle(
                id_pedido = id_pedido4,
                id_articulo = id_articulo3,
                cantidad = 1,
                id_usuario_registro = id_usuario4
            )
        )
        pedidoDetalleRepository.insertarPedidoDetalle(
            PedidoDetalle(
                id_pedido = id_pedido4,
                id_articulo = id_articulo4,
                cantidad = 1,
                id_usuario_registro = id_usuario4
            )
        )
        pedidoDetalleRepository.insertarPedidoDetalle(
            PedidoDetalle(
                id_pedido = id_pedido4,
                id_articulo = id_articulo5,
                cantidad = 1,
                id_usuario_registro = id_usuario4
            )
        )
        pedidoDetalleRepository.insertarPedidoDetalle(
            PedidoDetalle(
                id_pedido = id_pedido5,
                id_articulo = id_articulo2,
                cantidad = 1,
                id_usuario_registro = id_usuario5
            )
        )
        pedidoDetalleRepository.insertarPedidoDetalle(
            PedidoDetalle(
                id_pedido = id_pedido5,
                id_articulo = id_articulo5,
                cantidad = 2,
                id_usuario_registro = id_usuario5
            )
        )
    }

    private suspend fun clearData() {
        usuarioRepository.eliminarTodoUsuario()
        articuloRepository.eliminarTodoArticulo()
        pedidoRepository.eliminarTodoPedido()
        pedidoDetalleRepository.eliminarTodoPedidoDetalle()
    }
}
