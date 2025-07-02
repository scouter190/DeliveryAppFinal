package com.cesitar.deliveryapp_project.data.model

import com.cesitar.deliveryapp_project.data.entity.Usuario

data class UsuarioModel(
    val id_usuario: Int = 0,
    val nombre_completo: String,
    val usuario: String,
    val contraseña: String
) {
    companion object {
        fun from(usuario: Usuario): UsuarioModel {
            return UsuarioModel(
                id_usuario = usuario.id_usuario,
                nombre_completo = usuario.nombre_completo,
                usuario = usuario.usuario,
                contraseña = usuario.contraseña
            )
        }
    }
}
