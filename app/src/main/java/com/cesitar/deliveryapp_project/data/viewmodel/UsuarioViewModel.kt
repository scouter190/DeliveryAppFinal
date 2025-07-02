package com.cesitar.deliveryapp_project.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.cesitar.deliveryapp_project.data.database.AppDatabase
import com.cesitar.deliveryapp_project.data.entity.Usuario
import com.cesitar.deliveryapp_project.data.repository.UsuarioRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
/*
class UsuarioViewModel(application: Application): AndroidViewModel(application){
    private val listarUsuarios: LiveData<List<Usuario>>
    private val repository: UsuarioRepository

    init{
        val usuarioDao = AppDatabase.getDatabase(application).usuarioDao()
        repository = UsuarioRepository(usuarioDao)
        listarUsuarios = repository.listarUsuarios
    }

    fun insertarUsuario(usuario: Usuario){
        viewModelScope.launch(Dispatchers.IO){
            repository.insertarUsuario(usuario)
        }
    }

}

 */