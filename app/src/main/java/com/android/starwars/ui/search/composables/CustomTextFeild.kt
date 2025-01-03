package com.android.starwars.ui.search.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.starwars.R

@Composable
fun CostumeTextField(
    userInput: String,
    placeholder: @Composable () -> Unit,
    leadingIcon: Int = R.drawable.ic_search,
    trailingIcon: Int = R.drawable.ic_close,
    onValueChange: (String) -> Unit = {},
    onTrailingIconClick: (() -> Unit)? = null,
    modifier: Modifier = Modifier.fillMaxWidth(),
    shape: Shape = RoundedCornerShape(12.dp),
    colors: TextFieldColors = TextFieldDefaults.colors().copy(
        focusedContainerColor = Color.White,
        unfocusedContainerColor =Color.White,
        unfocusedPlaceholderColor = MaterialTheme.colorScheme.inverseSurface,
        cursorColor = MaterialTheme.colorScheme.onSurface,
        disabledLabelColor = MaterialTheme.colorScheme.tertiaryContainer,
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
    )
) {
    Column {
        TextField(
            value = userInput,
            placeholder = {
                placeholder.invoke()
            },
            leadingIcon =
                {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = leadingIcon),
                        contentDescription = "",
                        tint = Color.Unspecified
                    )
                },
            trailingIcon =
                {
                    Icon(imageVector = ImageVector.vectorResource(id = trailingIcon),
                        contentDescription = "",
                        tint = Color.Unspecified,
                        modifier = Modifier
                            .padding(4.dp)
                            .clip(shape)
                            .clickable { onTrailingIconClick?.invoke() })
                },
            onValueChange = onValueChange,
            modifier = modifier,
            singleLine = true,
            colors = colors,
            shape = shape,
        )
    }
}

@Composable
fun DefaultPlaceHolder(text: String) {
    Text(
        text = text,
        fontWeight = FontWeight.Normal,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        style = MaterialTheme.typography.labelSmall,
        color = MaterialTheme.colorScheme.inverseSurface,
        textAlign = TextAlign.Center,
    )
}