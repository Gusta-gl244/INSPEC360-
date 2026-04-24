package br.com.inspec360.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.inspec360.domain.model.Severity
import br.com.inspec360.presentation.ui.theme.INSPEC360Colors

@Composable
fun SeverityBadge(severity: Severity) {
    val (backgroundColor, textColor) = when (severity) {
        Severity.LEVE -> INSPEC360Colors.SeverityLight to INSPEC360Colors.Black
        Severity.MODERADA -> INSPEC360Colors.SeverityModerate to INSPEC360Colors.Black
        Severity.GRAVE -> INSPEC360Colors.SeveritySevere to INSPEC360Colors.White
        Severity.CRITICA -> INSPEC360Colors.SeverityCritical to INSPEC360Colors.White
    }

    Box(
        modifier = Modifier
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(6.dp)
            )
            .padding(8.dp)
    ) {
        Text(
            text = severity.name,
            style = MaterialTheme.typography.labelMedium,
            color = textColor
        )
    }
}

@Composable
fun ErrorMessage(message: String) {
    Box(
        modifier = Modifier
            .background(
                color = INSPEC360Colors.SeverityCritical.copy(alpha = 0.1f),
                shape = RoundedCornerShape(8.dp)
            )
            .padding(12.dp)
    ) {
        Text(
            text = message,
            style = MaterialTheme.typography.bodySmall,
            color = INSPEC360Colors.SeverityCritical
        )
    }
}

@Composable
fun SuccessMessage(message: String) {
    Box(
        modifier = Modifier
            .background(
                color = INSPEC360Colors.SeverityLight.copy(alpha = 0.1f),
                shape = RoundedCornerShape(8.dp)
            )
            .padding(12.dp)
    ) {
        Text(
            text = message,
            style = MaterialTheme.typography.bodySmall,
            color = INSPEC360Colors.SeverityLight
        )
    }
}
