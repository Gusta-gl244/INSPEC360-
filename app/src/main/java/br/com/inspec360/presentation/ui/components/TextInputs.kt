package br.com.inspec360.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.inspec360.presentation.ui.theme.INSPEC360Colors

@Composable
fun TextInput(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    singleLine: Boolean = true
) {
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(
                color = INSPEC360Colors.White,
                shape = RoundedCornerShape(8.dp)
            )
            .border(
                width = 1.dp,
                color = INSPEC360Colors.BorderGray,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(16.dp),
        singleLine = singleLine,
        textStyle = MaterialTheme.typography.bodyMedium.copy(
            color = INSPEC360Colors.Black
        ),
        decorationBox = { innerTextField ->
            Box {
                if (value.isEmpty()) {
                    Text(
                        text = placeholder,
                        style = MaterialTheme.typography.bodyMedium,
                        color = INSPEC360Colors.DarkGray
                    )
                }
                innerTextField()
            }
        }
    )
}

@Composable
fun PasswordInput(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier
) {
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(
                color = INSPEC360Colors.White,
                shape = RoundedCornerShape(8.dp)
            )
            .border(
                width = 1.dp,
                color = INSPEC360Colors.BorderGray,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(16.dp),
        singleLine = true,
        visualTransformation = androidx.compose.ui.text.input.PasswordVisualTransformation(),
        textStyle = MaterialTheme.typography.bodyMedium.copy(
            color = INSPEC360Colors.Black
        ),
        decorationBox = { innerTextField ->
            Box {
                if (value.isEmpty()) {
                    Text(
                        text = placeholder,
                        style = MaterialTheme.typography.bodyMedium,
                        color = INSPEC360Colors.DarkGray
                    )
                }
                innerTextField()
            }
        }
    )
}
