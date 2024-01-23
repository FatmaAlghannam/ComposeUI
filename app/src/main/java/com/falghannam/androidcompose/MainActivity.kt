package com.falghannam.androidcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.falghannam.androidcompose.ui.theme.AndroidComposeTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import androidx.compose.material3.MaterialTheme.colorScheme as colorScheme1


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = colorScheme1.background
                ) {
                    TrueFalseGame()


                }
            }
        }
    }
//    @Composable
//    fun Question(mutableState: .MutableState<Boolean>,modifier: Modifier = Modifier) {
//        var showCorrectAnswer by remember { mutableStateOf( false ) }
//        val question = listOf("Does cats eat fish?","Is the sky blue?", "2 x 2 = 5 ")
//        val answer = listOf(true,true, false)
//        var showWrongAnswer by remember { mutableStateOf(false)
//
//        }
//    }



    @Composable
    fun TrueFalseGame() {
        var isCorrect by remember { mutableStateOf(false) }
        var isSelectedAnsr by remember { mutableStateOf(false) }
        var isButtonSelect by remember { mutableStateOf(false) }
        var currentQuestionIndex by remember { mutableStateOf(0) }
        val questions = listOf(
            Question("Does cats eat fish?", true),
            Question("Is the sky blue?", true),
            Question("2 x 2 = 5 ?", false)
        )
        val currentQuestion = questions.getOrNull(currentQuestionIndex)
        var selectedAnswer by remember { mutableStateOf<Boolean?>(null) }
        var userScore by remember { mutableStateOf(0) }

        if (isSelectedAnsr) {
            isCorrect = selectedAnswer == currentQuestion?.answer
        }

        Column(
            modifier = Modifier
                .clip(CircleShape)
                .fillMaxSize()
                .width(200.dp)
                .padding(40.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween


        ) {
            Text(
                text = currentQuestion?.text ?: "GAME OVER!! Try Again",
                modifier = Modifier.padding(bottom = 20.dp)
            )


            if (isCorrect) {
                AnswerFeedback("Correct!", colorScheme1.secondary)
            } else {
                AnswerFeedback("Wrong!", colorScheme1.error)
            }
            if (isButtonSelect) {
                Button(
                    onClick = {
                        isButtonSelect = false
                        selectedAnswer = null
                        if (currentQuestionIndex < questions.size - 1) {
                            if (isCorrect) userScore++
                            currentQuestionIndex++
                        } else {
                            userScore = 0
                            currentQuestionIndex = 0
                        }
                    },
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .align(Alignment.End)
                        .width(200.dp)
                        .padding(10.dp),
                ) {
                    Text(text = if (currentQuestionIndex < questions.size - 1) "Next Question" else "RETRY AGAIN ")
                }
            } else {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly

                ) {
                    // Step 3
                    TrueFalseButton("True") {
                        isSelectedAnsr = true
                        isButtonSelect = true
                    }
                    TrueFalseButton("False") {
                        isSelectedAnsr = true
                        selectedAnswer = false
                    }
                }
            }
        }

    }


    @Composable
    fun TrueFalseButton(text: String, onSelected: () -> Unit) {
        Button(
            onClick = {
                onSelected()
            },
            modifier = Modifier
                .width(120.dp)
                .height(40.dp)
        ) {
            Text(text = text)
        }
    }


    @Composable
    fun AnswerFeedback(message: String, backgroundColor: androidx.compose.ui.graphics.Color) {
        Box(
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .clip(MaterialTheme.shapes.large)
                .background(backgroundColor)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(

                    text = message,
                    style = MaterialTheme.typography.labelMedium,
                    color = colorScheme1.onPrimary

                )
            }
        }
    }

    data class Question(val text: String, val answer: Boolean) {
        val isCorrect: Any?
            get() {
                TODO()
            }
    }

    @Preview(showBackground = true)
    @Composable
    fun TrueFalseGamePreview() {
        TrueFalseGame()
    }

}


@Composable
fun ResetButton(currentQuestionIndex: MutableState<Int>, userScore: MutableState<Int>) {
    Button(onClick = {
        currentQuestionIndex.value = 0
        userScore.value = 0
    }) {
        Text(text = "Reset Game")
    }
}




@Preview(showBackground = true)
@Composable
fun TrueFalseGamePreview() {
    TrueFalseGame()
}

@Composable
fun TrueFalseGame() {
    TODO("Not yet implemented")
}


//@Composable
//fun EveryThing(modifier: Modifier, color: CornerSize){
//    Column {
//        //Counter(modifier = Modifier.background(Color.Blue))
//        Row {
//            Button(onClick = { /*TODO*/ }) {
//                Text(text = "next question ")
//
//
//            }
//            Button(onClick = { /*TODO*/ }) {
//                Text(text = "True")
//
//            }
//            Button(onClick = { /*TODO*/ }) {
//                Text(text = "False")
//
//            }
//
//        }
//    }
//}

//@Composable
//fun Counter(modifier: Modifier = Modifier) {
//    Column(
//        modifier = modifier
//            .padding(14.dp)
//            .background(Color.White),
//        verticalArrangement = Arrangement.SpaceBetween,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Greeting("Android is an operating system ")
//        Text(text = "0", fontSize = 25.sp, fontWeight = FontWeight.Bold)
//        val new = null
//
//
//    }
//}
//
//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Text(
//        text = "Hello $name!",
//        modifier = modifier,
//        fontSize = 25.sp,
//        fontWeight = FontWeight.Bold
//
//
//    )
//}
//
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    AndroidComposeTheme {
//        Counter()
//
//    }
//}
