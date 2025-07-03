package com.cesitar.deliveryapp_project.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.cesitar.deliveryapp_project.data.entity.Usuario
import androidx.room.Insert
import com.cesitar.deliveryapp_project.data.entity.Articulo


@Dao
interface UsuarioDao {
  @Insert(onConflict = OnConflictStrategy.IGNORE)
  suspend fun insertarUsuario(usuario: Usuario): Long

  @Query("SELECT * FROM Usuario ORDER BY id_usuario ASC")
  suspend fun listarUsuarios() : List<Usuario>

  @Query("SELECT * FROM Usuario WHERE id_usuario = :id_usuario")
  suspend fun obtenerUsuarioPorId(id_usuario: Long): Usuario

  @Query("DELETE FROM Usuario")
  suspend fun eliminarTodoUsuario()

  @Query("SELECT * FROM usuario WHERE usuario = :usuario AND contraseña = :contraseña LIMIT 1")
  suspend fun validar(usuario: String, contraseña: String): Usuario?

}
