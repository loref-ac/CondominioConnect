package com.condominio.connect

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.condominio.connect.ui.theme.CondominioConnectTheme
import com.google.firebase.database.FirebaseDatabase

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        //  Conexión con Realtime Database
        val database = FirebaseDatabase.getInstance()
        val ref = database.getReference("residentes") // nombre del nodo

        //  Datos del RESIDENTE (según tu tabla)
        val residente = mapOf(
            "idResidente" to "RES-001",
            "nombreCompleto" to "Lorena Félix Gutiérrez",
            "tipoResidencia" to "Propietario",  // o "Inquilino"
            "ubicacionCasa" to "Casa 77 – San Joaquin",
            "numHabitantes" to 3
        )

        //  Enviar a Firebase
        ref.push()
            .setValue(residente)
            .addOnSuccessListener {
                Log.d("Firebase", "Residente guardado correctamente")
            }
            .addOnFailureListener { e ->
                Log.e("Firebase", "Error al guardar residente", e)
            }

        //  UI (no importa para la tarea, pero la dejamos)
        setContent {
            CondominioConnectTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hola $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CondominioConnectTheme {
        Greeting("Android")
    }
}
