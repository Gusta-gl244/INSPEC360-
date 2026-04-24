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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.inspec360.domain.model.Structure
import br.com.inspec360.presentation.ui.components.ErrorMessage
import br.com.inspec360.presentation.ui.components.PrimaryButton
import br.com.inspec360.presentation.ui.components.SecondaryButton
import br.com.inspec360.presentation.ui.theme.INSPEC360Colors

@Composable
fun InspectionStartScreen(
    structure: Structure,
    onStart: () -> Unit,
    onBack: () -> Unit,
    isLoading: Boolean = false,
    error: String? = null
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(INSPEC360Colors.White)
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Iniciar Inspeção",
            style = MaterialTheme.typography.displayMedium,
            color = INSPEC360Colors.PrimaryDarkGreen
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Estrutura ${structure.numero}",
            style = MaterialTheme.typography.headlineMedium,
            color = INSPEC360Colors.DarkGray
        )

        StructureInfoCard(structure)

        if (error != null) {
            ErrorMessage(error)
        }

        Spacer(modifier = Modifier.weight(1f))

        PrimaryButton(
            text = "INICIAR INSPEÇÃO",
            onClick = onStart,
            enabled = !isLoading
        )

        Spacer(modifier = Modifier.height(12.dp))

        SecondaryButton(
            text = "Cancelar",
            onClick = onBack
        )
    }
}

@Composable
fun StructureInfoCard(structure: Structure) {
    Column(
        modifier = Modifier
            .background(
                color = INSPEC360Colors.LightGray,
                shape = androidx.compose.foundation.shape.RoundedCornerShape(8.dp)
            )
            .padding(16.dp)
    ) {
        InfoRow("Tipo:", structure.tipo)
        InfoRow("Classe:", structure.classe)
        InfoRow("Progressiva:", "${structure.progressiva}km")
        if (!structure.travessia.isNullOrEmpty()) {
            InfoRow("Travessia:", structure.travessia)
        }
        if (structure.critica) {
            InfoRow("Status:", "⚠️ CRÍTICA", color = INSPEC360Colors.SeverityCritical)
        }
    }
}

@Composable
fun InfoRow(label: String, value: String, color: androidx.compose.ui.graphics.Color = INSPEC360Colors.Black) {
    Row(
        modifier = Modifier
            .background(INSPEC360Colors.White, shape = androidx.compose.foundation.shape.RoundedCornerShape(4.dp))
            .padding(12.dp)
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = INSPEC360Colors.DarkGray,
            modifier = Modifier.weight(0.3f)
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            color = color,
            modifier = Modifier.weight(0.7f)
        )
    }
}

@Composable
fun Row(
    modifier: Modifier = Modifier,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Start,
    verticalAlignment: androidx.compose.ui.Alignment.Vertical = androidx.compose.ui.Alignment.Top,
    content: @Composable () -> Unit
) {
    androidx.compose.foundation.layout.Row(
        modifier = modifier,
        horizontalArrangement = horizontalArrangement,
        verticalAlignment = verticalAlignment,
        content = content
    )
}
