package br.com.inspec360.presentation.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import br.com.inspec360.domain.model.Structure
import br.com.inspec360.presentation.ui.components.SecondaryButton
import br.com.inspec360.presentation.ui.components.TextInput
import br.com.inspec360.presentation.ui.theme.INSPEC360Colors

@Composable
fun StructuresScreen(
    structures: List<Structure>,
    onStructureSelected: (Structure) -> Unit,
    onSearch: (String) -> Unit,
    onBack: () -> Unit
) {
    var searchQuery by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(INSPEC360Colors.White)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Header
        Text(
            text = "Estruturas",
            style = MaterialTheme.typography.displayMedium,
            color = INSPEC360Colors.PrimaryDarkGreen
        )

        // Search
        TextInput(
            value = searchQuery,
            onValueChange = { query ->
                searchQuery = query
                onSearch(query)
            },
            placeholder = "Buscar por número ou tipo"
        )

        // List
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(structures) { structure ->
                StructureCard(
                    structure = structure,
                    onClick = { onStructureSelected(structure) }
                )
            }
        }

        // Back Button
        SecondaryButton(
            text = "Voltar",
            onClick = onBack
        )
    }
}

@Composable
fun StructureCard(
    structure: Structure,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = INSPEC360Colors.LightGray,
                shape = RoundedCornerShape(8.dp)
            )
            .border(
                width = if (structure.critica) 2.dp else 1.dp,
                color = if (structure.critica) INSPEC360Colors.SeverityCritical else INSPEC360Colors.BorderGray,
                shape = RoundedCornerShape(8.dp)
            )
            .clickable { onClick() }
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "Estrutura ${structure.numero}",
                style = MaterialTheme.typography.titleLarge,
                color = INSPEC360Colors.PrimaryDarkGreen
            )

            Text(
                text = "Tipo: ${structure.tipo}",
                style = MaterialTheme.typography.bodyMedium,
                color = INSPEC360Colors.DarkGray
            )

            Text(
                text = "Classe: ${structure.classe}",
                style = MaterialTheme.typography.bodySmall,
                color = INSPEC360Colors.DarkGray
            )

            if (structure.critica) {
                Text(
                    text = "⚠️ ESTRUTURA CRÍTICA",
                    style = MaterialTheme.typography.labelMedium,
                    color = INSPEC360Colors.SeverityCritical
                )
            }
        }
    }
}
