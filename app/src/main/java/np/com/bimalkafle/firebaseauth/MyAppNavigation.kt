package np.com.bimalkafle.firebaseauth

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import np.com.bimalkafle.firebaseauthdemoapp.AuthViewModel
import np.com.bimalkafle.firebaseauth.pages.ChatPage
import np.com.bimalkafle.firebaseauthdemoapp.pages.HomePage
import np.com.bimalkafle.firebaseauthdemoapp.pages.LoginPage
import np.com.bimalkafle.firebaseauthdemoapp.pages.SignupPage
import np.com.bimalkafle.firebaseauth.ui.theme.ChatViewModel

@Composable
fun MyAppNavigation(authViewModel: AuthViewModel, chatViewModel: ChatViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginPage(navController = navController, authViewModel = authViewModel)
        }
        composable("signup") {
            SignupPage(navController = navController, authViewModel = authViewModel)
        }
        composable("home") {
            HomePage(navController = navController, authViewModel = authViewModel)
        }
        composable("chatbot") {
            ChatPage(navController = navController, chatViewModel = chatViewModel)
        }
    }
}
