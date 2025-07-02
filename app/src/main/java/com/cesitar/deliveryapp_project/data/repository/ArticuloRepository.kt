package com.cesitar.deliveryapp_project.data.repository
import com.cesitar.deliveryapp_project.data.dao.ArticuloDao
import com.cesitar.deliveryapp_project.data.entity.Articulo

class ArticuloRepository(private val articuloDao: ArticuloDao) {

    suspend fun listarArticulos(): List<Articulo> {
        return articuloDao.listarArticulos();
    }

    suspend fun obtenerArticuloPorId(id: Long): Articulo {
        return articuloDao.obtenerArticuloPorId(id);
    }

    suspend fun insertarArticulo(articulo: Articulo): Long {
        return articuloDao.insertarArticulo(articulo)
    }

    suspend fun eliminarTodoArticulo(){
        articuloDao.eliminarTodoArticulo();
    }
}