package br.unasp.meusgastos.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Componente sem estado: recebe tudo por parâmetro, devolve o evento por lambda.
 * É usado em dois lugares diferentes (categoria do formulário e filtro da lista).
 */
@Composable
fun Etiqueta(
    texto: String,
    ativa: Boolean,
    aoClicar: () -> Unit,
    modifier: Modifier = Modifier
) {
    Text(
        text = texto,
        fontSize = 13.sp,
        color = if (ativa) Color.White else Muted,
        fontWeight = if (ativa) FontWeight.Bold else FontWeight.Normal,
        modifier = modifier
            .clip(CircleShape)
            .background(if (ativa) Coral else Card)
            .clickable { aoClicar() }
            .padding(horizontal = 14.dp, vertical = 8.dp)
    )
}

@Preview(showBackground = true, backgroundColor = 0xFFF2F6FA)
@Composable
private fun EtiquetaAtivaPreview() {
    Etiqueta(texto = "Alimentação", ativa = true, aoClicar = {}, modifier = Modifier.padding(12.dp))
}

@Preview(showBackground = true, backgroundColor = 0xFFF2F6FA)
@Composable
private fun EtiquetaInativaPreview() {
    Etiqueta(texto = "Transporte", ativa = false, aoClicar = {}, modifier = Modifier.padding(12.dp))
}
