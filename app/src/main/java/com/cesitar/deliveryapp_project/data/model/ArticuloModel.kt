package com.cesitar.deliveryapp_project.data.model

import com.cesitar.deliveryapp_project.data.entity.Articulo

data class ArticuloModel(
    var id_articulo: Int,
    var nombre: String,
    var stock: Int,
) {
    companion object {
        fun from(articulo: Articulo): ArticuloModel {
            return ArticuloModel(
                id_articulo = articulo.id_articulo,
                nombre = articulo.nombre,
                stock = articulo.stock
            )
        }
    }
}