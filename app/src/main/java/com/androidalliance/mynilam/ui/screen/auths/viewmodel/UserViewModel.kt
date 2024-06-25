package com.androidalliance.mynilam.ui.screen.auths.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androidalliance.mynilam.data.models.User
import com.androidalliance.mynilam.data.repositories.interfaces.UserRepository
import com.androidalliance.mynilam.domain.use_case.auth_validation.ValidateConfirmPassword
import com.androidalliance.mynilam.domain.use_case.auth_validation.ValidateEmail
import com.androidalliance.mynilam.domain.use_case.auth_validation.ValidatePassword
import com.androidalliance.mynilam.domain.use_case.auth_validation.ValidateUsername
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserViewModel(
    private val validateEmail: ValidateEmail = ValidateEmail(),
    private val validateUsername: ValidateUsername = ValidateUsername(),
    private val validatePassword: ValidatePassword = ValidatePassword(),
    private val validateConfirmPassword: ValidateConfirmPassword = ValidateConfirmPassword(),
    private val userRepository: UserRepository

): ViewModel(){

    var state by mutableStateOf(AuthFormState())

    private val _showTopAppBar = MutableStateFlow(true)
    val sharedShowTopAppBar = _showTopAppBar.asStateFlow()

    private val _userState = MutableStateFlow<User?>(null)
    val sharedUserState = _userState.asStateFlow()

    private val validationEventsChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventsChannel.receiveAsFlow()

    private fun updateUserSession(user: User){
        viewModelScope.launch {
            withContext(Dispatchers.Main){
                _userState.value = user
            }
        }
    }

    fun showTopAppBar(){
        viewModelScope.launch {
            withContext(Dispatchers.Main){
                _showTopAppBar.value = true
            }
        }
    }

    fun hideTopAppBar() {
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                _showTopAppBar.value = false
            }
        }
    }

    fun clearForm(){
        state = AuthFormState()
    }

    suspend fun getUserInfoById(userId: Int) :User?{
        return withContext(Dispatchers.IO){
            userRepository.getUserById(userId)
        }
    }

    fun onRegisterEvent(event: RegistrationEvent){
        when(event){
            is RegistrationEvent.EmailChanged -> {
                state = state.copy(email = event.email)
            }
            is RegistrationEvent.UsernameChanged -> {
                state = state.copy(username = event.username)
            }
            is RegistrationEvent.PasswordChanged -> {
                state = state.copy(password = event.password)
            }
            is RegistrationEvent.ConfirmPasswordChanged -> {
                state = state.copy(confirmPassword = event.confirmPassword)
            }
            is RegistrationEvent.Submit -> {
                submitData()
            }
        }
    }

    fun onLoginEvent(event: LoginEvent){
        when(event){
            is LoginEvent.UsernameChanged ->{
                state = state.copy(username = event.username)
            }
            is LoginEvent.PasswordChanged ->{
                state = state.copy(password = event.password)
            }
            is LoginEvent.Submit -> {
                loginUser()
            }
        }
    }

    private fun submitData() {
        val emailResult = validateEmail.execute(state.email)
        val usernameResult = validateUsername.execute(state.username)
        val passwordResult = validatePassword.execute(state.password)
        val confirmPasswordResult = validateConfirmPassword.execute(state.password, state.confirmPassword)

        val hasError = listOf(
            emailResult,
            usernameResult,
            passwordResult,
            confirmPasswordResult
        ).any{ !it.successful }

        if(hasError){
            state = state.copy(
                emailError = emailResult.errorMessage,
                usernameError = usernameResult.errorMessage,
                passwordError = passwordResult.errorMessage,
                confirmPasswordError = confirmPasswordResult.errorMessage
            )
            return
        }
        viewModelScope.launch {
            // do something
            val isUserExist = withContext(Dispatchers.IO){
                userRepository.isUserExists(state.email, state.username)
            }
            if(isUserExist){
                validationEventsChannel.send(ValidationEvent.Failure)
            }
            else{
                validationEventsChannel.send(ValidationEvent.Success)
                val createUser = User(username = state.username, password = state.password, email = state.email)
                userRepository.createUserAndProfile(createUser)
                clearForm()
            }
        }
    }

    private fun loginUser() {
        val usernameResult = validateUsername.execute(state.username)
        val passwordResult = validatePassword.execute(state.password)

        val hasError = listOf(
            usernameResult,
            passwordResult,
        ).any{ !it.successful }

        if(hasError){
            state = state.copy(
                usernameError = usernameResult.errorMessage,
                passwordError = passwordResult.errorMessage,
            )
            return
        }

        viewModelScope.launch {
            val user = withContext(Dispatchers.IO){
                userRepository.loginUser(state.username, state.password)
            }
            if(user == null){
                validationEventsChannel.send(ValidationEvent.Failure)
            }else{
                validationEventsChannel.send(ValidationEvent.Success)
                updateUserSession(user)
                clearForm()
            }
        }

    }

    fun signOut(){
        clearForm()
        updateUserSession(User(
            username = "",
            password = "",
            email = ""
        ))
    }

    sealed class ValidationEvent {
        object Success : ValidationEvent()
        object Failure : ValidationEvent()
    }

}