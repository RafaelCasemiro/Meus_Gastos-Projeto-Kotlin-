package br.unasp.meusgastos.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ResumoTotal(total: Double, quantidade: Int, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(Navy)
            .padding(horizontal = 16.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text("Total", color = AzulClaro, fontSize = 12.sp)
            Text(
                text = formatarReal(total),
                color = Color.White,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Text("$quantidade gasto(s)", color = AzulClaro, fontSize = 13.sp)
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF2F6FA)
@Composable
private fun ResumoTotalPreview() {
    ResumoTotal(total = 201.70, quantidade = 4, modifier = Modifier.padding(16.dp))
}

@Preview(showBackground = true, backgroundColor = 0xFFF2F6FA)
@Composable
private fun ResumoVazioPreview() {
    ResumoTotal(total = 0.0, quantidade = 0, modifier = Modifier.padding(16.dp))
}
