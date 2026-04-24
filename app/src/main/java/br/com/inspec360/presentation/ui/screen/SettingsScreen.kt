package br.com.inspec360.presentation.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.inspec360.presentation.ui.components.PrimaryButton
import br.com.inspec360.presentation.ui.components.SecondaryButton
import br.com.inspec360.presentation.ui.theme.INSPEC360Colors

@Composable
fun SettingsScreen(
    appVersion: String,
    onExportData: () -> Unit,
    onClearData: () -> Unit,
    onLogout: () -> Unit,
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
            text = "Configurações",
            style = MaterialTheme.typography.displayMedium,
            color = INSPEC360Colors.PrimaryDarkGreen
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Versão: $appVersion",
            style = MaterialTheme.typography.bodyMedium,
            color = INSPEC360Colors.DarkGray
        )

        Spacer(modifier = Modifier.weight(1f))

        SecondaryButton(
            text = "Exportar Dados",
            onClick = onExportData
        )

        Spacer(modifier = Modifier.height(12.dp))

        SecondaryButton(
            text = "Limpar Banco de Dados",
            onClick = onClearData
        )

        Spacer(modifier = Modifier.height(12.dp))

        PrimaryButton(
            text = "SAIR",
            onClick = onLogout
        )

        Spacer(modifier = Modifier.height(12.dp))

        SecondaryButton(
            text = "Voltar",
            onClick = onBack
        )
    }
}
