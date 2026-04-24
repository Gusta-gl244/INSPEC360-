package br.com.inspec360.presentation.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.inspec360.domain.model.Inspection
import br.com.inspec360.presentation.ui.components.PrimaryButton
import br.com.inspec360.presentation.ui.components.SecondaryButton
import br.com.inspec360.presentation.ui.theme.INSPEC360Colors
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun HistoryScreen(
    inspections: List<Inspection>,
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(INSPEC360Colors.White)
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Histórico de Inspeções",
            style = MaterialTheme.typography.displayMedium,
            color = INSPEC360Colors.PrimaryDarkGreen
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            if (inspections.isEmpty()) {
                Text(
                    text = "Nenhuma inspeção registrada",
                    style = MaterialTheme.typography.bodyLarge,
                    color = INSPEC360Colors.DarkGray
                )
            } else {
                inspections.forEach { inspection ->
                    InspectionHistoryCard(inspection)
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        SecondaryButton(
            text = "Voltar",
            onClick = onBack
        )
    }
}

@Composable
fun InspectionHistoryCard(inspection: Inspection) {
    val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale("pt", "BR"))
    val dateStr = dateFormat.format(Date(inspection.dataHora))

    Column(
        modifier = Modifier
            .background(
                color = INSPEC360Colors.LightGray,
                shape = androidx.compose.foundation.shape.RoundedCornerShape(8.dp)
            )
            .padding(16.dp)
    ) {
        Text(
            text = "ID: ${inspection.id}",
            style = MaterialTheme.typography.titleMedium,
            color = INSPEC360Colors.PrimaryDarkGreen
        )

        Text(
            text = dateStr,
            style = MaterialTheme.typography.bodySmall,
            color = INSPEC360Colors.DarkGray
        )

        Text(
            text = inspection.status.name,
            style = MaterialTheme.typography.labelMedium,
            color = if (inspection.status.name == "FINALIZADA") {
                INSPEC360Colors.SeverityLight
            } else {
                INSPEC360Colors.SeverityModerate
            }
        )
    }
}
