package com.honey.randomusergenerator

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.tooling.preview.Preview
import com.honey.data.network.NetworkMonitor
import com.honey.randomusergenerator.ui.navigation.RugApp
import com.honey.randomusergenerator.ui.theme.NyaTheme
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {
    private val networkMonitor: NetworkMonitor by inject()
    private val pickMedia = registerForActivityResult(
        ActivityResultContracts.PickVisualMedia()
    ) { uri ->
        selectedUri.value = uri
    }
    private val selectedUri = mutableStateOf<Uri?>(null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NyaTheme {
                RugApp(
                    networkMonitor = networkMonitor
                )
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NyaTheme {
        Greeting("Android")
    }
}