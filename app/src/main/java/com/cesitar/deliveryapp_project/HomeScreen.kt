package com.cesitar.deliveryapp_project

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

import androidx.compose.ui.unit.dp
import com.cesitar.deliveryapp_project.data.entity.Pedido
import com.cesitar.deliveryapp_project.data.model.ArticuloModel
import com.cesitar.deliveryapp_project.data.model.PedidoDetalleModel
import com.cesitar.deliveryapp_project.data.model.PedidoModel
import com.cesitar.deliveryapp_project.data.model.UsuarioModel
import com.cesitar.deliveryapp_project.data.repository.ArticuloRepository
import com.cesitar.deliveryapp_project.data.repository.PedidoDetalleRepository
import com.cesitar.deliveryapp_project.data.repository.PedidoRepository
import com.cesitar.deliveryapp_project.data.repository.UsuarioRepository


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    usuarioRepository: UsuarioRepository,
    articuloRepository: ArticuloRepository,
    pedidoRepository: PedidoRepository,
    pedidoDetalleRepository: PedidoDetalleRepository
) {

    var listaPedidos by remember { mutableStateOf< List<Pedido>>(emptyList()) }

    LaunchedEffect(true) {
        listaPedidos = pedidoRepository.listarPedidos();
        var pedidosModel = mutableListOf<PedidoModel>()
        for (pedido in listaPedidos) {
            var pedidoDetallesModel = mutableListOf<PedidoDetalleModel>()
            var pedidoDetalles =
                pedidoDetalleRepository.listarPedidoDetalle(pedido.id_pedido.toLong())
            for(pedidoDetalle in pedidoDetalles){
                var articulo = articuloRepository.obtenerArticuloPorId(pedidoDetalle.id_articulo.toLong())
                var usuario = usuarioRepository.obtenerUsuarioPorId(pedidoDetalle.id_usuario_registro.toLong());
                var pedidoDetalleModel = PedidoDetalleModel.from(pedidoDetalle);
                pedidoDetalleModel.articulo = ArticuloModel.from(articulo);
                pedidoDetalleModel.usuario_registro = UsuarioModel.from(usuario);
                pedidoDetallesModel.add(PedidoDetalleModel.from(pedidoDetalle));
            }
            var pedidoModel = PedidoModel.from(pedido);
            pedidoModel.pedidoDetalle = pedidoDetallesModel;
            pedidosModel.add(pedidoModel);
        }
    }
        val pedidos = listOf(
            mapOf(
                "codigo" to "P001",
                "estado" to "Pendiente",
                "fecha" to "24/06/2025",
                "cliente" to "Cesar Flores",
                "direccion" to "Av. Siempre Viva 123",
                "detalle" to listOf(
                    mapOf("articulo" to "Arroz", "cantidad" to 2, "precio" to 10),
                    mapOf("articulo" to "Papa", "cantidad" to 3, "precio" to 5)
                )
            ),
            mapOf(
                "codigo" to "P002",
                "estado" to "Entregado",
                "fecha" to "25/06/2025",
                "cliente" to "María Pérez",
                "direccion" to "Calle Falsa 456",
                "detalle" to listOf(
                    mapOf("articulo" to "Aceite", "cantidad" to 1, "precio" to 20)
                )
            )
        )

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Listado de Pedidos") },
                    navigationIcon = {
                        IconButton(onClick = { }) {
                            Icon(Icons.Default.Menu, contentDescription = "Menú")
                        }
                    },
                    actions = {
                        IconButton(onClick = { /* Refrescar */ }) {
                            Icon(Icons.Default.Refresh, contentDescription = "Refrescar")
                        }
                    }
                )
            }
        ) { padding ->
            LazyColumn(
                modifier = Modifier
                    .padding(padding)
                    .padding(8.dp)
            ) {
                items(pedidos) { pedido ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {
                        Column(modifier = Modifier.padding(8.dp)) {
                            Text("Pedido: ${pedido["codigo"]}", style = MaterialTheme.typography.titleMedium)
                            Text("Estado: ${pedido["estado"]}")
                            Text("Fecha: ${pedido["fecha"]}")
                            Text("Cliente: ${pedido["cliente"]}")
                            Text("Dirección: ${pedido["direccion"]}")
                            Spacer(modifier = Modifier.height(4.dp))
                            Text("Detalle:")
                            (pedido["detalle"] as List<Map<String,Any>>).forEach { detalle ->
                                Text("- ${detalle["articulo"]} x${detalle["cantidad"]} (S/.${detalle["precio"]})")
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }


