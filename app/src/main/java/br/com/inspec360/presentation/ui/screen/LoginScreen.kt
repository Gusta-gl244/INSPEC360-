package br.com.inspec360.presentation.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import br.com.inspec360.presentation.ui.components.ErrorMessage
import br.com.inspec360.presentation.ui.components.PasswordInput
import br.com.inspec360.presentation.ui.components.PrimaryButton
import br.com.inspec360.presentation.ui.components.TextInput
import br.com.inspec360.presentation.ui.theme.INSPEC360Colors

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit,
    onLogin: (username: String, password: String) -> Unit,
    isLoading: Boolean = false,
    error: String? = null
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "INSPEC360",
            style = MaterialTheme.typography.displayLarge,
            color = INSPEC360Colors.PrimaryDarkGreen
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Inspeção de Redes de Transmissão",
            style = MaterialTheme.typography.bodyLarge,
            color = INSPEC360Colors.DarkGray
        )

        Spacer(modifier = Modifier.height(48.dp))

        TextInput(
            value = username,
            onValueChange = { username = it },
            placeholder = "Usuário"
        )

        Spacer(modifier = Modifier.height(16.dp))

        PasswordInput(
            value = password,
            onValueChange = { password = it },
            placeholder = "Senha"
        )

        if (error != null) {
            Spacer(modifier = Modifier.height(16.dp))
            ErrorMessage(error)
        }

        Spacer(modifier = Modifier.height(32.dp))

        PrimaryButton(
            text = "Entrar",
            onClick = { onLogin(username, password) },
            enabled = !isLoading && username.isNotEmpty() && password.isNotEmpty()
        )
    }
}
