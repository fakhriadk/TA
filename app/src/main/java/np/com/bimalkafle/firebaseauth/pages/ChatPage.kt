package np.com.bimalkafle.firebaseauth.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import np.com.bimalkafle.firebaseauth.ui.theme.ChatViewModel
import np.com.bimalkafle.firebaseauth.ui.theme.MessageModel

@Composable
fun ChatPage(navController: NavController, chatViewModel: ChatViewModel) {
    Column(modifier = Modifier.fillMaxSize()) {
        AppHeader(navController)
        MessageList(modifier = Modifier.weight(1f), messageList = chatViewModel.messageList)
        MessageInput(onMessageSend = { chatViewModel.sendMessage(it) })
    }
}

@Composable
fun AppHeader(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary)
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
            }
            Text(
                text = "Mental Health Chatbot",
                color = Color.White,
                fontSize = 22.sp,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }
}

@Composable
fun MessageList(modifier: Modifier = Modifier, messageList: List<MessageModel>) {
    if (messageList.isEmpty()) {
        Column(
            modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Halo, aku CalmBot! Aku asisten pribadimu untuk membantu mengelola emosi dan kecemasan. Apa yang bisa aku bantu hari ini?",
                fontSize = 16.sp,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
    } else {
        LazyColumn(modifier = modifier, reverseLayout = true) {
            items(messageList.reversed()) {
                MessageRow(messageModel = it)
            }
        }
    }
}

@Composable
fun MessageRow(messageModel: MessageModel) {
    val isModel = messageModel.role == "model"

    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier
                    .align(if (isModel) Alignment.BottomStart else Alignment.BottomEnd)
                    .padding(start = if (isModel) 8.dp else 70.dp, end = if (isModel) 70.dp else 8.dp, top = 8.dp, bottom = 8.dp)
                    .clip(RoundedCornerShape(48f))
                    .background(if (isModel) Color(0xFFB3E5FC) else Color(0xFFFFCDD2))
                    .padding(16.dp)
            ) {
                SelectionContainer {
                    Text(text = messageModel.message, fontWeight = FontWeight.W500, color = Color.Black)
                }
            }
        }
    }
}

@Composable
fun MessageInput(onMessageSend: (String) -> Unit) {
    var message by remember { mutableStateOf("") }

    Row(modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
        OutlinedTextField(
            modifier = Modifier.weight(1f),
            value = message,
            onValueChange = { message = it },
            placeholder = { Text("Ketik pesan...") }
        )
        IconButton(onClick = {
            if (message.isNotEmpty()) {
                onMessageSend(message)
                message = ""
            }
        }) {
            Icon(imageVector = Icons.Default.Send, contentDescription = "Send")
        }
    }
}
