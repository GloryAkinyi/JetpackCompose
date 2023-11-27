package com.wanyama.imageupload

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wanyama.imageupload.ui.theme.ImageUploadTheme
import com.wanyama.imageupload.ui.theme.ShimmerColorShades
import com.wanyama.imageupload.ui.theme.brownColor

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ImageUploadTheme(darkTheme = false) {
                Surface(color = MaterialTheme.colorScheme.background) {
                    LazyColumn {
                        repeat(1) {
                            item {
                                ShimmerAnimation()


                            }
                        }
                    }
                }
            }

        }
    }
}

@Composable
fun ShimmerItem(
    brush: Brush
) {

    Column(modifier = Modifier.padding(16.dp)) {

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .size(250.dp)
                .background(brush = brush)
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(30.dp)
                .padding(vertical = 8.dp)
                .background(brush = brush)
        )
    }
}


@Composable
fun ShimmerAnimation() {
    val context = LocalContext.current
    val transition = rememberInfiniteTransition()
    val translateAnim by transition.animateFloat(initialValue = 0f, targetValue = 1000f, animationSpec = infiniteRepeatable(
            tween(durationMillis = 1200, easing = FastOutSlowInEasing), RepeatMode.Reverse))

    Column(modifier = Modifier.fillMaxSize()) {
        val brush = Brush.linearGradient(colors = ShimmerColorShades, start = Offset(10f, 10f), end = Offset(translateAnim, translateAnim))
        ShimmerItem(brush = brush)

       Box(modifier = Modifier.fillMaxWidth(),
           contentAlignment = Alignment.Center) {
           Button(onClick = {context.startActivity(Intent(context, PDFEXtractor::class.java))},
               shape = RectangleShape,
               colors = ButtonDefaults.buttonColors(brownColor),) {
               Text(text = "PDF EXTRACTOR")
           }

       }

    }
}



@Preview(showBackground = true)
@Composable
fun ShimmerPreview() {
    ShimmerAnimation()

}