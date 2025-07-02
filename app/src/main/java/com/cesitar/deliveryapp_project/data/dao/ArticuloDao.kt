package com.cesitar.deliveryapp_project.data.dao

import androidx.room.*
import com.cesitar.deliveryapp_project.data.entity.Articulo
import androidx.lifecycle.LiveData


@Dao
interface ArticuloDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertarArticulo(articulo: Articulo): Long

    @Query("SELECT * FROM Articulo ORDER BY id_articulo ASC")
    suspend fun listarArticulos() : List<Articulo>

    @Query("SELECT * FROM Articulo WHERE id_articulo = :id")
    suspend fun obtenerArticuloPorId(id: Long): Articulo

    @Query("DELETE FROM Articulo")
    suspend fun eliminarTodoArticulo()



}