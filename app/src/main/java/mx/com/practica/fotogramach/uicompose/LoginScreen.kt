package mx.com.practica.fotogramach.uicompose

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import mx.com.practica.fotogramach.R
import mx.com.practica.fotogramach.api.ApiResponseStatus
import mx.com.practica.fotogramach.composables.AuthField
import mx.com.practica.fotogramach.composables.ErrorDialog
import mx.com.practica.fotogramach.composables.LoadingWheel
import mx.com.practica.fotogramach.model.User
import mx.com.practica.fotogramach.viewmodels.AuthViewModel

@Composable
fun LoginScreen(
    status: ApiResponseStatus<User>?,
    onLoginButtonClick: (String, String) -> Unit,
    onErrorDialogDismiss: () -> Unit,
    authViewModel: AuthViewModel
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
//            .background(colorResource(id = R.color.secondary_background))
            .padding(start = 8.dp, end = 8.dp, bottom = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        LoginInformation(
            onLoginButtonClick = onLoginButtonClick,
            resetFieldErrors = { authViewModel.resetErrors() },
            authViewModel = authViewModel
        )
        if (status is ApiResponseStatus.Loading) {
            LoadingWheel()
        } else if (status is ApiResponseStatus.Error) {
            ErrorDialog(status.messageId, onErrorDialogDismiss)
        }
    }
}

@Composable
fun LoginInformation(
    onLoginButtonClick: (String, String) -> Unit,
    resetFieldErrors: () -> Unit,
    authViewModel: AuthViewModel
) {
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = 32.dp,
                start = 16.dp,
                end = 16.dp,
                bottom = 16.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        AuthField(
            label = stringResource(id = R.string.user),
            modifier = Modifier.fillMaxWidth(),
            email = email.value,
            errorMessageId = authViewModel.userError.value,
            onTextChanged = {
                email.value = it
                resetFieldErrors()
            },
        )
        AuthField(
            label = stringResource(id = R.string.password),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            email = password.value,
            onTextChanged = {
                password.value = it
                resetFieldErrors()
            },
            errorMessageId = authViewModel.passwordError.value,
            visualTransformation = PasswordVisualTransformation(),
        )
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .semantics { testTag = "sign-up-button" },
            onClick = { onLoginButtonClick(email.value, password.value) },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = colorResource(id = R.color.colorAccent),
                contentColor = colorResource(id = R.color.white)
            )
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = stringResource(R.string.sign_up),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Medium,
            )
        }
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
//    LoginScreen(authViewModel = null)
}