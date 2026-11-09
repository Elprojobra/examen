package com.example.proyectoexam

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyectoexam.ui.theme.ProyectoExamTheme
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProyectoExamTheme {
                MegaProfileScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MegaProfileScreen() {
    val scrollState = rememberScrollState()

    // Coordenadas UPP
    val ubicacion = LatLng(20.0763, -98.7058)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(ubicacion, 15f)
    }

    // Paleta de Colores Modo Oscuro Premium
    val darkBg = Color(0xFF0F172A)       // Azul noche oscuro
    val cardBg = Color(0xFF1E293B)       // Superficie de tarjetas
    val cyanAccent = Color(0xFF38BDF8)   // Neón Cyan
    val goldAccent = Color(0xFFF59E0B)   // Dorado / DBZ Aura

    Scaffold(
        containerColor = darkBg,
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = { /* Acción */ },
                containerColor = cyanAccent,
                contentColor = Color.Black,
                shape = RoundedCornerShape(16.dp),
                icon = { Icon(Icons.Default.Edit, contentDescription = "Editar") },
                text = { Text("Editar Perfil", fontWeight = FontWeight.Bold) }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(scrollState)
        ) {
            // 1. PORTADA CON DEGRADADO + AVATAR CON ANILLO NEÓN
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp)
            ) {
                // Banner de Portada
                Image(
                    painter = painterResource(id = R.drawable.mi_portada),
                    contentDescription = "Portada DBZ",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp),
                    contentScale = ContentScale.Crop
                )

                // Capa de gradiente transparente hacia la interfaz oscura
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    darkBg.copy(alpha = 0.7f),
                                    darkBg
                                )
                            )
                        )
                )

                // Foto de perfil superpuesta con anillo cyan e indicador "Online"
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(start = 20.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.mi_foto),
                        contentDescription = "Foto de perfil",
                        modifier = Modifier
                            .size(110.dp)
                            .clip(CircleShape)
                            .border(3.dp, cyanAccent, CircleShape),
                        contentScale = ContentScale.Crop
                    )
                    // Punto verde de estado activo
                    Box(
                        modifier = Modifier
                            .size(20.dp)
                            .clip(CircleShape)
                            .background(Color(0xFF22C55E))
                            .border(3.dp, darkBg, CircleShape)
                            .align(Alignment.BottomEnd)
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // 2. NOMBRE, TÍTULO Y BADGES DE TECNOLOGÍA
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            ) {
                Text(
                    text = "Johan Cervantes",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    text = "ITID Student @ UPP • Mobile Developer",
                    fontSize = 14.sp,
                    color = cyanAccent,
                    fontWeight = FontWeight.Medium
                )

                Spacer(modifier = Modifier.height(10.dp))

                // Chips estilo etiqueta
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    TechBadge(text = "Kotlin", color = cyanAccent)
                    TechBadge(text = "Compose", color = goldAccent)
                    TechBadge(text = "DBZ Fan", color = Color(0xFFEF4444))
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // 3. TARJETA ABOUT CON ICONO
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = cardBg)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Info,
                            contentDescription = null,
                            tint = cyanAccent
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "About",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Estudiante de Ingeniería en Tecnologías de la Información e Innovación Digital en la UPP. Apasionado por el desarrollo Android con Kotlin y Jetpack Compose, arquitecturas de software y proyectos tecnológicos.",
                        fontSize = 14.sp,
                        color = Color(0xFFCBD5E1),
                        lineHeight = 20.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // 4. LISTA HORIZONTAL DE 10 TARJETAS CON METADATOS Y ESTILOS
            Text(
                text = "Stats & Tech Stack",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(horizontal = 20.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            val statsList = listOf(
                "⚡ Kotlin" to "Level: Pro",
                "🎨 Compose" to "UI Expert",
                "📍 Maps API" to "Integrado",
                "💻 Android" to "Studio IDE",
                "🛠️ Git" to "theAnub1s",
                "🐉 Dragon Ball" to "Z / Kaiju",
                "🎯 UI/UX" to "Figma",
                "🌐 Laravel" to "Fullstack",
                "🗄️ SQL" to "Oracle/MySQL",
                "⭐ Estado" to "10/10"
            )

            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(statsList) { (title, subtitle) ->
                    Card(
                        modifier = Modifier
                            .width(125.dp)
                            .height(75.dp),
                        shape = RoundedCornerShape(14.dp),
                        colors = CardDefaults.cardColors(containerColor = cardBg)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(10.dp),
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = title,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                            Text(
                                text = subtitle,
                                fontSize = 11.sp,
                                color = cyanAccent
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // 5. TARJETA GOOGLE MAPS CON CABECERA CUSTOM
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = cardBg)
            ) {
                Column {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(14.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = null,
                            tint = Color(0xFFEF4444)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Ubicación • UPP Pachuca",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(210.dp)
                            .clip(RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp))
                    ) {
                        GoogleMap(
                            modifier = Modifier.fillMaxSize(),
                            cameraPositionState = cameraPositionState
                        ) {
                            Marker(
                                state = MarkerState(position = ubicacion),
                                title = "UPP",
                                snippet = "Universidad Politécnica de Pachuca"
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(90.dp))
        }
    }
}

// Componente para los Badges estilizados
@Composable
fun TechBadge(text: String, color: Color) {
    Surface(
        color = color.copy(alpha = 0.15f),
        shape = RoundedCornerShape(8.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, color.copy(alpha = 0.5f))
    ) {
        Text(
            text = text,
            color = color,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
        )
    }
}