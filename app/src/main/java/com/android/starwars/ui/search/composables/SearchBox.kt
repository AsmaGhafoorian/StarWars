package com.android.starwars.ui.search.composables

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android.starwars.ui.theme.StarWarsTheme

@Composable
fun SearchBox(
    input: String,
    placeholder: String,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit
) {
    Box(
        modifier = modifier.border(
            width = 1.dp,
            shape = RoundedCornerShape(8.dp),
            color = MaterialTheme.colorScheme.outlineVariant
        )
    )
    {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            CostumeTextField(
                userInput = input,
                placeholder = { DefaultPlaceHolder(placeholder) },
                onValueChange = { onValueChange(it) },
                onTrailingIconClick = { onValueChange("") },
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun SearchBoxPreview() {
    StarWarsTheme {
        SearchBox(
            input = "",
            placeholder = "search"
        ) { }
    }
}