package br.com.inspec360.presentation.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.inspec360.presentation.ui.components.PrimaryButton
import br.com.inspec360.presentation.ui.components.SecondaryButton
import br.com.inspec360.presentation.ui.theme.INSPEC360Colors

@Composable
fun DashboardScreen(
    inspectorName: String,
    onNewInspection: () -> Unit,
    onStructures: () -> Unit,
    onHistory: () -> Unit,
    onSettings: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(INSPEC360Colors.White)
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Header
        Text(
            text = "Bem-vindo",
            style = MaterialTheme.typography.displayMedium,
            color = INSPEC360Colors.PrimaryDarkGreen
        )

        Text(
            text = inspectorName,
            style = MaterialTheme.typography.headlineMedium,
            color = INSPEC360Colors.DarkGray
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Main Action Button
        PrimaryButton(
            text = "NOVA INSPEÇÃO",
            onClick = onNewInspection,
            modifier = Modifier.height(80.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Secondary Actions - Row Layout
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            SecondaryButton(
                text = "Estruturas",
                onClick = onStructures,
                modifier = Modifier.weight(1f)
            )

            SecondaryButton(
                text = "Histórico",
                onClick = onHistory,
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        SecondaryButton(
            text = "Configurações",
            onClick = onSettings
        )
    }
}
