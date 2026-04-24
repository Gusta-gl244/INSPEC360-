package br.com.inspec360.presentation.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.inspec360.domain.model.Severity
import br.com.inspec360.presentation.ui.components.PrimaryButton
import br.com.inspec360.presentation.ui.components.SecondaryButton
import br.com.inspec360.presentation.ui.components.SeverityBadge
import br.com.inspec360.presentation.ui.components.TextInput
import br.com.inspec360.presentation.ui.theme.INSPEC360Colors

@Composable
fun AnomalyRegistrationScreen(
    onSave: (
        componente: String,
        tipoAnomalia: String,
        severidade: Severity,
        observacao: String,
        fotoPath: String?
    ) -> Unit,
    onBack: () -> Unit,
    isLoading: Boolean = false
) {
    var componente by remember { mutableStateOf("") }
    var tipoAnomalia by remember { mutableStateOf("") }
    var selectedSeverity by remember { mutableStateOf(Severity.LEVE) }
    var observacao by remember { mutableStateOf("") }
    var fotoPath by remember { mutableStateOf<String?>(null) }
    var temFoto by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(INSPEC360Colors.White)
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Registrar Anomalia",
            style = MaterialTheme.typography.displayMedium,
            color = INSPEC360Colors.PrimaryDarkGreen
        )

        // Componente
        Text(
            text = "Componente",
            style = MaterialTheme.typography.titleMedium
        )
        TextInput(
            value = componente,
            onValueChange = { componente = it },
            placeholder = "Ex: Isolador, Condutor"
        )

        // Tipo de Anomalia
        Text(
            text = "Tipo de Anomalia",
            style = MaterialTheme.typography.titleMedium
        )
        TextInput(
            value = tipoAnomalia,
            onValueChange = { tipoAnomalia = it },
            placeholder = "Ex: Trinca, Sujidade"
        )

        // Severidade
        Text(
            text = "Severidade",
            style = MaterialTheme.typography.titleMedium
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Severity.values().forEach { severity ->
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .background(
                            color = if (selectedSeverity == severity)
                                INSPEC360Colors.AccentGold else INSPEC360Colors.LightGray,
                            shape = androidx.compose.foundation.shape.RoundedCornerShape(8.dp)
                        )
                        .padding(8.dp)
                ) {
                    SeverityBadge(severity)
                }
            }
        }

        // Foto
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Tirar Foto",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.weight(1f)
            )
            Checkbox(
                checked = temFoto,
                onCheckedChange = { temFoto = it },
                colors = CheckboxDefaults.colors(
                    checkedColor = INSPEC360Colors.AccentGold,
                    uncheckedColor = INSPEC360Colors.BorderGray
                )
            )
        }

        // Observação
        Text(
            text = "Observação",
            style = MaterialTheme.typography.titleMedium
        )
        TextInput(
            value = observacao,
            onValueChange = { observacao = it },
            placeholder = "Detalhes adicionais",
            singleLine = false
        )

        Spacer(modifier = Modifier.weight(1f))

        PrimaryButton(
            text = "SALVAR ANOMALIA",
            onClick = {
                onSave(componente, tipoAnomalia, selectedSeverity, observacao, fotoPath)
            },
            enabled = !isLoading && componente.isNotEmpty() && tipoAnomalia.isNotEmpty()
        )

        Spacer(modifier = Modifier.height(12.dp))

        SecondaryButton(
            text = "Cancelar",
            onClick = onBack
        )
    }
}
