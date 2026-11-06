package br.unasp.meusgastos.ui

import androidx.compose.ui.graphics.Color

val Fundo = Color(0xFFF2F6FA)
val Navy = Color(0xFF103A5E)
val Coral = Color(0xFFF3704E)
val Card = Color(0xFFE6F0F9)
val Muted = Color(0xFF5A7A96)
val Erro = Color(0xFFC0392B)
val AzulClaro = Color(0xFF9FC0DB)

fun formatarReal(valor: Double): String =
    "R$ " + String.format("%.2f", valor).replace(".", ",")
