package np.com.bimalkafle.firebaseauth.ui.theme

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content
import kotlinx.coroutines.launch
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import np.com.bimalkafle.firebaseauth.ui.theme.MessageModel
import com.google.ai.client.generativeai.*


class ChatViewModel : ViewModel() {

    val messageList by lazy {
        mutableStateListOf<MessageModel>()
    }

    val generativeModel : GenerativeModel = GenerativeModel(
        modelName = "gemini-2.0-flash",
        apiKey = Constants.apiKey
    )

    fun sendMessage(question : String){
        viewModelScope.launch {

            try{
                val chat = generativeModel.startChat(
                    history = messageList.map {
                        content(it.role){ text(it.message) }
                    }.toList()
                )

                messageList.add(MessageModel(question,"user"))
                messageList.add(MessageModel("Typing....","model"))

                val response = chat.sendMessage(question)
                messageList.removeLast()
                messageList.add(MessageModel(response.text.toString(),"model"))
            }catch (e : Exception){
                messageList.removeLast()
                messageList.add(MessageModel("Error : "+e.message.toString(),"model"))
            }


        }
    }
}