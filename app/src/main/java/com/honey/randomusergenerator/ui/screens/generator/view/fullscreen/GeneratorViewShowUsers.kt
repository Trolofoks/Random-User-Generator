package com.honey.randomusergenerator.ui.screens.generator.view.fullscreen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.honey.randomusergenerator.R
import com.honey.randomusergenerator.data.model.User
import com.honey.randomusergenerator.ui.screens.generator.contract.GeneratorState
import com.honey.randomusergenerator.ui.part.BigInfoCardView
import com.honey.randomusergenerator.ui.part.MicroUserCardView
import com.honey.randomusergenerator.ui.part.SmallUserCardView
import drawVerticalScrollbar

@Composable
fun GeneratorViewShowUsers(
    modifier: Modifier = Modifier,
    state: GeneratorState.ShowUsers,
    onFullInfoClick: (user: User) -> Unit,
    onHideFullInfo: () -> Unit,
    onFavAdd: (user: User, add: Boolean) -> Unit,
    regenerate: (amount: Int) -> Unit
) {
    val userList = state.users
    Surface(modifier = modifier.fillMaxSize()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
            when (userList.size) {
                in 2..40 -> {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth(0.95f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        userList.forEach() { user ->
                            item {
                                SmallUserCardView(
                                    user = user,
                                    onCardClick = { onFullInfoClick.invoke(user) }
                                )
                            }
                        }
                    }
                }
                in 41..1000 -> {
                    val lazyState = rememberLazyGridState()
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(5),
                        state = lazyState,
                        modifier = Modifier
                            .padding(horizontal = 4.dp)
                            .drawVerticalScrollbar(lazyState, 5, false)
                    ) {
                        userList.forEach { user ->
                            item {
                                MicroUserCardView(
                                    user = user,
                                    onCardClick = { onFullInfoClick.invoke(user) })
                            }
                        }
                    }
                }
                else -> {
                    BigInfoCardView(
                        user = userList[0], favorite = { user, fav ->
                            onFavAdd(user, fav)
                        }, modifier = Modifier
                            .fillMaxWidth(0.98f)
                            .align(Alignment.Center),
                        exportCopyTypeFormat = state.exportCopyType
                    )
                }
            }
            state.selectedUser?.let { fullUser ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(colorResource(id = R.color.transparent_black))
                        .clickable {
                            onHideFullInfo.invoke()
                        },
                    contentAlignment = Alignment.Center
                ) {
                    BackHandler(onBack = {
                        onHideFullInfo.invoke()
                    })
                    BigInfoCardView(user = fullUser, favorite = { user, fav ->
                        onFavAdd.invoke(user, fav)
                    }, modifier = Modifier
                        .fillMaxWidth(0.95f)
                        .clickable(enabled = false) {},
                        exportCopyTypeFormat = state.exportCopyType
                    )
                }
            }
            RegenerateRowButton(modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 12.dp),
                onClick = { amount ->
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
    onClick: ((amount: Int) -> Unit)? = null
) {
    val counter = remember { mutableStateOf(1) }
    Box(modifier = modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        Row(Modifier.fillMaxWidth(0.80f)) {
            Counter(counter = counter, modifier = Modifier.weight(0.45f))
            Spacer(modifier = Modifier.weight(0.05f))
            Button(
                onClick = { onClick?.invoke(counter.value) },
                modifier = Modifier
                    .weight(0.45f)
                    .height(48.dp)
            ) {
                Text(text = stringResource(id = R.string.regenerate), style = MaterialTheme.typography.titleMedium)
            }
        }

    }

}

@Composable
fun Counter(counter: MutableState<Int>, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .height(48.dp), shape = MaterialTheme.shapes.large,
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Row(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = {
                    when (counter.value) {
                        in 2..10 -> {
                            counter.value--
                        }
                        in 11..100 -> {
                            counter.value -= 10
                        }
                        in 101..1000 -> {
                            counter.value -= 100
                        }
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
                    when (counter.value) {
                        in 1..9 -> {
                            counter.value++
                        }
                        in 10..99 -> {
                            counter.value += 10
                        }
                        in 100..999 -> {
                            counter.value += 100
                        }
                    }
                }) {
                Icon(Icons.Default.KeyboardArrowUp, contentDescription = "Plus")
            }
        }

    }
}