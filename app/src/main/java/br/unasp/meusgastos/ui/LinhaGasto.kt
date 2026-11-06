package br.unasp.meusgastos.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import br.unasp.meusgastos.model.Categoria
import br.unasp.meusgastos.model.Gasto

@Composable
fun LinhaGasto(gasto: Gasto, aoRemover: () -> Unit, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White)
            .padding(14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(gasto.descricao, fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Navy)
            Text(gasto.categoria.rotulo, fontSize = 12.sp, color = Muted)
        }
        Text(formatarReal(gasto.valor), fontSize = 15.sp, color = Navy)
        Spacer(Modifier.width(12.dp))
        Text(
            text = "remover",
            fontSize = 12.sp,
            color = Coral,
            modifier = Modifier.clickable { aoRemover() }
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF2F6FA)
@Composable
private fun LinhaGastoPreview() {
    LinhaGasto(
        gasto = Gasto(1, "Almoço no bandejão", 18.50, Categoria.ALIMENTACAO),
        aoRemover = {},
        modifier = Modifier.padding(16.dp)
    )
}
