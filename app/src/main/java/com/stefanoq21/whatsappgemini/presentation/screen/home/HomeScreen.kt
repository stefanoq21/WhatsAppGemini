package com.stefanoq21.whatsappgemini.presentation.screen.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddComment
import androidx.compose.material.icons.filled.AddIcCall
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.stefanoq21.whatsappgemini.presentation.component.home.HomeTopBar
import com.stefanoq21.whatsappgemini.presentation.navigation.BottomBarEnum
import com.stefanoq21.whatsappgemini.presentation.navigation.NavigationEvent
import com.stefanoq21.whatsappgemini.presentation.navigation.NavigationViewModel
import com.stefanoq21.whatsappgemini.presentation.screen.home.calls.CallsScreen
import com.stefanoq21.whatsappgemini.presentation.screen.home.chats.ChatsScreen
import com.stefanoq21.whatsappgemini.presentation.screen.home.communities.CommunitiesScreen
import com.stefanoq21.whatsappgemini.presentation.screen.home.updates.UpdatesScreen
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeInitScreen(
    navigationViewModel: NavigationViewModel = koinViewModel(),
) {

    HomeScreen(
        onNavigationEvent = navigationViewModel::onEvent,
    )

}


@Composable
fun HomeScreen(
    onNavigationEvent: (NavigationEvent) -> Unit,
) {
    val coroutineScope = rememberCoroutineScope()

    var currentPageIndex by remember {
        mutableIntStateOf(0)
    }
    val pagerState = rememberPagerState(pageCount = { BottomBarEnum.entries.size })

    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collect { page ->
            currentPageIndex = page
        }
    }



    NavigationSuiteScaffold(
        modifier = Modifier,
        navigationSuiteItems = {
            BottomBarEnum.entries.forEachIndexed { index, bottomBarElement ->
                item(
                    icon =
                    if (index == currentPageIndex)
                        bottomBarElement.iconSelected
                    else
                        bottomBarElement.icon,
                    selected = index == currentPageIndex,
                    alwaysShowLabel = true,
                    label = {
                        Text(
                            text = stringResource(id = bottomBarElement.title),
                            style = MaterialTheme.typography.labelMedium.copy(
                                textAlign = TextAlign.Center,
                                fontWeight = if (index == currentPageIndex) FontWeight.Bold else FontWeight.Normal
                            ),
                            maxLines = 1
                        )
                    },
                    onClick = {
                        if (index != currentPageIndex) {
                            coroutineScope.launch {
                                currentPageIndex = index
                                pagerState.scrollToPage(index)
                            }
                        }
                    }
                )
            }
        }
    ) {

        Scaffold(
            topBar = {
                HomeTopBar(element = BottomBarEnum.entries[currentPageIndex])
            },
            floatingActionButton = {
                Column {
                    AnimatedVisibility(
                        visible = BottomBarEnum.entries[currentPageIndex] == BottomBarEnum.Updates,
                        enter = slideInVertically(initialOffsetY = { it }),
                        exit = slideOutVertically(targetOffsetY = { it })
                                + fadeOut(
                            animationSpec = tween(
                                durationMillis = 100
                            )
                        ),
                    ) {

                        SmallFloatingActionButton(
                            modifier = Modifier
                                .padding(bottom = 16.dp),
                            contentColor = MaterialTheme.colorScheme.onSurface,
                            containerColor = MaterialTheme.colorScheme.surfaceContainer,
                            elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 2.dp),
                            onClick = { }) {
                            Icon(
                                imageVector = Icons.Default.Edit,
                                contentDescription = null
                            )
                        }

                    }
                    when (BottomBarEnum.entries[currentPageIndex]) {
                        BottomBarEnum.Chats -> {
                            FloatingActionButton(
                                elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 2.dp),
                                onClick = { }) {
                                Icon(
                                    imageVector = Icons.Default.AddComment,
                                    contentDescription = null
                                )
                            }
                        }

                        BottomBarEnum.Updates -> {


                            FloatingActionButton(
                                modifier = Modifier,
                                elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 2.dp),
                                onClick = { }) {
                                Icon(
                                    imageVector = Icons.Default.CameraAlt,
                                    contentDescription = null
                                )
                            }


                        }

                        BottomBarEnum.Communities -> {}
                        BottomBarEnum.Calls -> {
                            FloatingActionButton(
                                modifier = Modifier,
                                elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 2.dp),
                                onClick = { }) {
                                Icon(
                                    imageVector = Icons.Default.AddIcCall,
                                    contentDescription = null
                                )
                            }
                        }
                    }
                }
            }
        ) { innerPadding ->
            HorizontalPager(
                state = pagerState

            ) { page ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                ) {
                    when (BottomBarEnum.entries[page]) {
                        BottomBarEnum.Chats -> {
                            ChatsScreen()
                        }

                        BottomBarEnum.Updates -> {
                            UpdatesScreen()
                        }

                        BottomBarEnum.Communities -> {
                            CommunitiesScreen()
                        }

                        BottomBarEnum.Calls -> {
                            CallsScreen()
                        }
                    }
                }

            }


        }

    }
}