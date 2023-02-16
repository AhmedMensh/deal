package com.example.login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.designsystem.R
import com.example.designsystem.core.component.DealFilledButton
import com.example.designsystem.core.component.DealLoadingWheel

@Composable
fun LoginRoute() {
    LoginScreen()
}

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel()
) {

    val emailValue = remember { mutableStateOf("") }
    val passwordValue = remember { mutableStateOf("") }
    val passwordVisibility = remember { mutableStateOf(false) }
    val mContext = LocalContext.current
    val uiState by viewModel.loginState.collectAsState()
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {

        Image(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_logo),
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth(.5f)
                .fillMaxHeight(.3f)
        )
        Spacer(modifier = Modifier.padding(10.dp))
        Text(
            text = stringResource(id = R.string.sign_in),
            style = MaterialTheme.typography.headlineLarge.copy(color = Color.Black),
        )
        Spacer(modifier = Modifier.padding(30.dp))

        OutlinedTextField(
            value = emailValue.value,
            onValueChange = {
                emailValue.value = it
            },
            label = { Text(text = stringResource(id = R.string.email)) },
            placeholder = { Text(text = stringResource(id = R.string.enter_your_email)) },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(0.9f)
        )

        OutlinedTextField(
            value = passwordValue.value,
            onValueChange = {
                passwordValue.value = it
            },
            label = { Text(text = stringResource(id = R.string.password)) },
            placeholder = { Text(text = stringResource(id = R.string.enter_your_password)) },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(0.9f),
            trailingIcon = {
                IconButton(onClick = { passwordVisibility.value = !passwordVisibility.value }) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = if (passwordVisibility.value) R.drawable.ic_visibility_off else R.drawable.ic_visibility),
                        contentDescription = "",
                        tint = if (passwordVisibility.value) Color.Red else Color.Gray
                    )
                }
            },
            visualTransformation = if (passwordVisibility.value) VisualTransformation.None
            else PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.padding(20.dp))
        when (uiState) {
            LoginUiState.Loading -> {
                DealLoadingWheel(contentDesc = "")
            }
            is LoginUiState.Error -> {
                Toast.makeText(
                    mContext,
                    (uiState as LoginUiState.Error).message,
                    Toast.LENGTH_SHORT
                ).show()
                LoginButton {
                    viewModel.requestLogin(emailValue.value, passwordValue.value)
                }
            }
            LoginUiState.Success -> {
                viewModel.navigateToHomeScreen()
            }
            else -> {
                LoginButton {
                    viewModel.requestLogin(emailValue.value, passwordValue.value)
                }
            }
        }

        Spacer(modifier = Modifier.padding(10.dp))
        Text(text = stringResource(id = R.string.create_account), modifier = Modifier.clickable {
            viewModel.navigateToRegisterScreen()
        })

    }
}

@Composable
fun LoginButton(
    onClick: () -> Unit
) {
    DealFilledButton(
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Black
        ),
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth(.7f)
            .height(50.dp),

        ) {
        Text(
            text = "Login",
            style = MaterialTheme.typography.labelLarge.copy(color = Color.White)
        )
    }
}