package com.honey.randomusergenerator.ui.screens.generator.view.fullscreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.honey.randomusergenerator.data.model.User
import com.honey.randomusergenerator.ui.screens.generator.contract.GeneratorState
import com.honey.randomusergenerator.ui.screens.generator.view.part.BigInfoCardView
import com.honey.randomusergenerator.ui.screens.generator.view.part.SmallUserCardView

@Composable
fun GeneratorViewShowUsers(
    modifier : Modifier = Modifier,
    state: GeneratorState.ShowUsers,
    onFullInfoClick: (user: User) -> Unit,
    onFavAdd: (user: User) -> Unit,
    regenerate: (amount: Int) -> Unit
) {
    val userList = state.users

    Surface(modifier = modifier.fillMaxSize()) {
        Box(modifier = Modifier.fillMaxSize()){
            LazyColumn(
                modifier = Modifier.fillMaxWidth(0.9f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (userList.size > 1){
                    userList.forEach(){ user ->
                        item {
                            SmallUserCardView(
                                user = user,
                                onCardClick = { onFullInfoClick.invoke(user) })
                        }
                    }
                } else{
                    if (userList.isNotEmpty()){
                        item { BigInfoCardView(user = userList[0]) }
                    }
                }
            }
            RegenerateRowButton(modifier = Modifier.align(Alignment.BottomCenter),
            onClick = {amount ->
                regenerate.invoke(amount)
            }
            )
        }
    }
}

@Preview
@Composable
fun RegenerateRowButton(
    modifier: Modifier = Modifier,
    onClick : ((amount: Int) -> Unit)? = null
){
    val counter = remember{ mutableStateOf(1) }
    Box(modifier = modifier.fillMaxWidth(), contentAlignment = Alignment.Center){
        Row (Modifier.fillMaxWidth(0.80f)) {
            Card(modifier = Modifier
                .weight(0.45f)
                .height(48.dp), shape = MaterialTheme.shapes.large) {
                Row(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    IconButton(
                        onClick = {
                            if (counter.value > 1){
                                counter.value--
                            }
                        }) {
                        Icon(Icons.Default.KeyboardArrowDown, contentDescription = "Minus")
                    }
                    Text(
                        modifier = Modifier.padding(horizontal = 12.dp),
                        text = "${counter.value}"
                    )
                    IconButton(
                        onClick = {
                            if (counter.value < 10){
                                counter.value++
                            }
                        }) {
                        Icon(Icons.Default.KeyboardArrowUp, contentDescription = "Plus")
                    }
                }

            }
            Spacer(modifier = Modifier.weight(0.05f))
            Button(
                onClick = { onClick?.invoke(counter.value) },
                modifier = Modifier
                    .weight(0.45f)
                    .height(48.dp)) {
                Text(text = "Regenerate!", style = MaterialTheme.typography.titleMedium)
            }
        }

    }


}