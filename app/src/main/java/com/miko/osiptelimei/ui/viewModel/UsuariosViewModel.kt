package com.miko.osiptelimei.ui.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miko.osiptelimei.Core.Ejemplo.RetrofitHelper
import com.miko.osiptelimei.Domain.Model.Usuario
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UsuariosViewModel : ViewModel(){
    var _listaUsuarios: ArrayList<Usuario> by mutableStateOf(arrayListOf())

    fun getUsuarios() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = RetrofitHelper.webService.getUsuarios()
            withContext(Dispatchers.Main) {
                if (response.body()!!.codigo == "200") {
                    _listaUsuarios = response.body()!!.data
                }
            }
        }
    }

    fun addUsuario(usuario: Usuario) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = RetrofitHelper.webService.addUsuarios(usuario)
            withContext(Dispatchers.Main) {
                if(response.body()!!.codigo == "200") {
                    getUsuarios()
                }
            }
        }
    }

    fun updateUsuario(idUsuario: String, usuario: Usuario) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = RetrofitHelper.webService.updateUsuario(idUsuario, usuario)
            withContext(Dispatchers.Main) {
                if(response.body()!!.codigo == "200") {
                    getUsuarios()
                }
            }
        }
    }

    fun deleteUsuario(idUsuario: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = RetrofitHelper.webService.deleteUsuario(idUsuario)
            withContext(Dispatchers.Main) {
                if(response.body()!!.codigo == "200") {
                    getUsuarios()
                }
            }
        }
    }

}