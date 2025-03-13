package np.com.bimalkafle.firebaseauthdemoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import np.com.bimalkafle.firebaseauth.MyAppNavigation
import np.com.bimalkafle.firebaseauth.ui.theme.ChatViewModel
import np.com.bimalkafle.firebaseauthdemoapp.ui.theme.FirebaseAuthDemoAppTheme

class MainActivity : ComponentActivity() {
    private val authViewModel: AuthViewModel by viewModels()
    private val chatViewModel: ChatViewModel by viewModels()  // Initialize ChatViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FirebaseAuthDemoAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MyAppNavigation(
                        authViewModel = authViewModel,
                        chatViewModel = chatViewModel // Pass chatViewModel
                    )
                }
            }
        }
    }
}
