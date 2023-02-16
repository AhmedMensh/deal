package com.example.register

import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.designsystem.R
import com.example.designsystem.core.component.DealFilledButton

@Composable
fun RegisterRoute() {
    RegisterScreen()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel = hiltViewModel()
) {
    Scaffold(
        topBar = {
            TopAppBar(backgroundColor = Color.White) {
                IconButton(onClick = { viewModel.navigateBack() }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "")
                }
            }
        },
        content = { innerPadding ->
            BoxWithConstraints(
                Modifier.padding(innerPadding),
                contentAlignment = Alignment.TopCenter
            ) {
                RegisterScreenContent()
            }
        }
    )

}

@Composable
fun RegisterScreenContent(
    viewModel: RegisterViewModel = hiltViewModel()
) {
    val firsNameValue = remember { mutableStateOf("") }
    val lastNameValue = remember { mutableStateOf("") }
    val emailValue = remember { mutableStateOf("") }
    val passwordValue = remember { mutableStateOf("") }
    val passwordVisibility = remember { mutableStateOf(false) }
    val mContext = LocalContext.current
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {


        Image(
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = "",
            modifier = Modifier
                .width(80.dp)
                .height(80.dp)
        )
        Spacer(modifier = Modifier.padding(10.dp))
        Text(
            text = stringResource(id = R.string.sign_up),
            style = MaterialTheme.typography.headlineLarge.copy(color = Color.Black),
        )
        Spacer(modifier = Modifier.padding(30.dp))

        OutlinedTextField(
            value = firsNameValue.value,
            onValueChange = {
                firsNameValue.value = it
            },
            label = { Text(text = stringResource(id = R.string.first_name)) },
            placeholder = { Text(text = stringResource(id = R.string.enter_first_name)) },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(0.9f)
        )
        OutlinedTextField(
            value = lastNameValue.value,
            onValueChange = {
                lastNameValue.value = it
            },
            label = { Text(text = stringResource(id = R.string.last_name)) },
            placeholder = { Text(text = stringResource(id = R.string.enter_last_name)) },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(0.9f)
        )
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
        DealFilledButton(
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black
            ),
            onClick = {
                Toast.makeText(mContext, emailValue.value, Toast.LENGTH_SHORT).show()
            },
            modifier = Modifier
                .fillMaxWidth(.7f)
                .height(50.dp),

            ) {
            Text(
                text = stringResource(id = R.string.sign_up),

                style = MaterialTheme.typography.labelLarge.copy(color = Color.White)
            )
        }
        Spacer(modifier = Modifier.padding(10.dp))
        Row {
            Text(
                text = stringResource(id = R.string.already_have_an_account),
                modifier = Modifier.clickable {
                    viewModel.navigateBack()
                })
            Spacer(modifier = Modifier.padding(2.dp))
            Text(
                text = stringResource(id = R.string.login),
                style = TextStyle(
                    color = Color.Black,
                    textDecoration = TextDecoration.Underline
                ),
                modifier = Modifier.clickable {
                    viewModel.navigateBack()
                })
        }


    }
}