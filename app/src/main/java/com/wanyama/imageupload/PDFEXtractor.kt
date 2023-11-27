package com.wanyama.imageupload

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.itextpdf.text.pdf.PdfReader
import com.itextpdf.text.pdf.parser.PdfTextExtractor
import com.wanyama.imageupload.ui.theme.ImageUploadTheme
import com.wanyama.imageupload.ui.theme.brownColor


class PDFEXtractor : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ImageUploadTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(
                        topBar = {
                            TopAppBar(colors = TopAppBarDefaults.mediumTopAppBarColors(brownColor),
                                title = {
                                    Text(
                                        text = "Text Extractor in Android",
                                        modifier = Modifier.fillMaxWidth(),
                                        textAlign = TextAlign.Center,
                                        color = androidx.compose.ui.graphics.Color.White
                                    )
                                }
                            )
                        }
                    ) {
                        textExtractor()
                    }
                }

                }
            }
        }
    }


@SuppressLint("RememberReturnType")
@Composable
fun textExtractor() {
    val extractedText = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .fillMaxSize()
            .padding(6.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = extractedText.value, color = androidx.compose.ui.graphics.Color.White, fontSize = 12.sp)
        Spacer(modifier = Modifier.height(10.dp))


        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            onClick = {
                extractData(extractedText)},
            colors = ButtonDefaults.buttonColors(brownColor),
            shape = RectangleShape) {
            Text(modifier = Modifier.padding(6.dp), text = "Extract Text from PDF")
        }
    }

}

// on below line we are creating an extract data method to extract our data.
private fun extractData(extractedString: MutableState<String>) {
    try {
        var extractedText = ""
        val pdfReader: PdfReader = PdfReader("res/raw/sample.pdf")
        val n = pdfReader.numberOfPages
        for (i in 0 until n) {
            extractedText =
                """
                 $extractedText${
                    PdfTextExtractor.getTextFromPage(pdfReader, i + 1).trim { it <= ' ' }
                }
                 
                 """.trimIndent()
        }
        extractedString.value = extractedText
        pdfReader.close()
    }
    catch (e: Exception) {
        e.printStackTrace()
    }
}

@Preview(showBackground = true)
@Composable
fun textExtractorPreview() {
   textExtractor()
}