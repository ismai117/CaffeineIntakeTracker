package main.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import getPlatform
import kotlinx.coroutines.flow.MutableStateFlow



private fun platformSize(): Pair<Dp, Dp> {
    if (getPlatform().name == "desktop") {
        return Pair(24.dp, 500.dp)
    } else {
        return Pair(48.dp, 324.dp)
    }
}

@Composable
fun remainingIndicator(
    modifier: Modifier = Modifier,
    limit: Int = 400,
    intake: Double
){

    val intakeColor = MaterialTheme.colorScheme.primaryContainer
    val textMeasurer = rememberTextMeasurer()

    val color = MaterialTheme.colorScheme.primary

    val animateConvertedValue by animateFloatAsState(
        targetValue = (intake.toFloat() / limit.toFloat()) * 300f
    )

    val (strokeWidth, canvasSize) = platformSize()

    Canvas(
        modifier = modifier
            .padding(16.dp)
            .size(canvasSize)
//            .border(width = 1.dp, color = Color.Red)
    ){

        drawArc(
            brush = SolidColor(Color.LightGray),
            startAngle = 120f,
            sweepAngle = 300f,
            useCenter = false,
            style = Stroke(strokeWidth.value, cap = StrokeCap.Round)
        )

        drawArc(
            brush = SolidColor(intakeColor),
            startAngle = 120f,
            sweepAngle = animateConvertedValue,
            useCenter = false,
            style = Stroke(strokeWidth.value, cap = StrokeCap.Round)
        )


        val dayText = "Today"
        val dayTextStyle = TextStyle(
            color = color,
            fontSize = 42.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )
        val dayTextLayoutResult = textMeasurer.measure(AnnotatedString(dayText), style = dayTextStyle)

        val intakeText = "${intake.toInt()} / 400"
        val intakeTextStyle = TextStyle(
            color = color,
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )
        val intakeTextLayoutResult = textMeasurer.measure(AnnotatedString(intakeText), style = intakeTextStyle)

        val mgText = "mg"
        val mgTextStyle = TextStyle(
            color = color,
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )
        val mgTextLayoutResult = textMeasurer.measure(AnnotatedString(mgText), style = mgTextStyle)

        drawIntoCanvas { canvas ->

            val dayTextTopLeft = Offset(
                (size.width - dayTextLayoutResult.size.width) / 2,
                (size.height - dayTextLayoutResult.size.height) / 4
            )

            drawText(
                textLayoutResult = dayTextLayoutResult,
                topLeft = dayTextTopLeft
            )

            val intakeTextTopLeft = Offset(
                (size.width - intakeTextLayoutResult.size.width) / 2,
                (size.height- intakeTextLayoutResult.size.height) / 2 + 8.dp.toPx()
            )

            drawText(
                textLayoutResult = intakeTextLayoutResult,
                topLeft = intakeTextTopLeft
            )

            val mgTextTopLeft = Offset(
                (size.width - mgTextLayoutResult.size.width) / 2,
                intakeTextTopLeft.y + intakeTextLayoutResult.size.height + 8.dp.toPx()
            )

            drawText(
                textLayoutResult = mgTextLayoutResult,
                topLeft = mgTextTopLeft
            )
        }

    }
}