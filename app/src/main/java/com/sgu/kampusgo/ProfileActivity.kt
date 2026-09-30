package com.sgu.kampusgo

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sgu.kampusgo.ui.theme.KampusGoProjectTheme

// Explicit Intent =
class ProfileActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("KampusGo", "onCreate")
        val activity = this
        val name = intent.getStringExtra("name") ?: "Guest"
        val npm = intent.getStringExtra("npm")?.takeIf { it.isNotBlank() } ?: "-"
        setContent() {
            KampusGoProjectTheme {
                Column(modifier = Modifier.padding(24.dp)) {
                    Text(text = "Hello $name")
                    Text(text = "NPM = $npm")
                    Button(onClick = {
                        val dial = Intent(Intent.ACTION_DIAL, Uri.parse("tel:0211234567"))
                        try {
                            activity.startActivity(dial)
                        } catch (e: ActivityNotFoundException) {
                            Toast.makeText(
                                activity,
                                "No dialer on this service",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }) {
                        Text("Call Campus")
                    }
                    Button(onClick = {
                        val sgu = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.sgu.ac.id"))
                        try {
                            activity.startActivity((sgu))
                        } catch (e: ActivityNotFoundException) {
                            Toast.makeText(
                                activity,
                                "No browser on this service",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }) {
                        Text("Open Campus Website")
                    }
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("KampusGoProject", "onStart")
    }
    override fun onPause() {
        super.onPause()
        Log.d("KampusGoProject", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("KampusGoProject", "onStop")
    }
}