package com.cesitar.deliveryapp_project.data.repository
import com.cesitar.deliveryapp_project.data.dao.UsuarioDao
import com.cesitar.deliveryapp_project.data.entity.Usuario

class UsuarioRepository(private val usuarioDao: UsuarioDao) {

    suspend fun  listarUsuarios(): List<Usuario> {
        return usuarioDao.listarUsuarios();
    }

    suspend fun insertarUsuario(usuario: Usuario): Long {
        return usuarioDao.insertarUsuario(usuario)
    }

    suspend fun obtenerUsuarioPorId(id_usuario: Long): Usuario {
        return usuarioDao.obtenerUsuarioPorId(id_usuario)
    }

    suspend fun eliminarTodoUsuario(){
        usuarioDao.eliminarTodoUsuario()
    }
}
