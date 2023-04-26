package com.wsy.ahp.activity


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.MaterialTheme

import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alibaba.android.arouter.facade.annotation.Route
import com.wsy.ahp.R



@Route(path = "/home/Login")
class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                Column {
                    LoginScreen()
                }
            }
        }
    }
}

@Composable
fun LoginScreen() {

    var userName by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }

    var showPassword by remember {
        mutableStateOf(false)
    }

    //屏幕宽度
    var width: Float
    //高度
    var height: Float

    with(LocalDensity.current) {
        width = LocalConfiguration.current.screenWidthDp.dp.toPx()
        height = LocalConfiguration.current.screenHeightDp.dp.toPx()
    }



    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        //背景图
        Image(
            painter = painterResource(id = R.drawable.earth),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        //右上->左下渐变
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.linearGradient(
                        listOf(
                            Color(0xffbb8378),
                            Color.Transparent
                        ),
                        start = Offset(x = width, y = 0f),
                        end = Offset(x = 0f, y = height)
                    )
                )
        )

        //左下->右上渐变
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.linearGradient(
                        listOf(
                            Color(0xFF149EE7),
                            Color.Transparent,
                        ),
                        start = Offset(x = 0f, y = height),
                        end = Offset(x = width, y = 0f),
                    )
                )
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Spacer(modifier = Modifier.height(height.dp/4))
                androidx.compose.material.Text(
                    text = "用户登录",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier,
                )
                Spacer(modifier = Modifier.height(20.dp))
                TextField(
                    value = userName,
                    onValueChange = { userName = it },
                    singleLine = true,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = null,
                            tint = Color.White
                        )


                    },
                    label = {
                        androidx.compose.material.Text(
                            text = "用户名",
                            fontSize = 14.sp,
                            color = Color.White
                        )
                    },
                    colors = TextFieldDefaults.textFieldColors(
//                        backgroundColor = Color.Transparent,
                        focusedIndicatorColor = Color.LightGray,
                        unfocusedIndicatorColor = Color.LightGray,
                        focusedLabelColor = Color.LightGray,
                        unfocusedLabelColor = Color.LightGray,
                        cursorColor = Color.White
                    ),

                    )

                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = password,
                    onValueChange = { password = it },
                    singleLine = true,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Password,
                            contentDescription = null,
                            tint = Color.White
                        )


                    },
                    trailingIcon = {
                        Icon(
                            imageVector = if (showPassword) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                            contentDescription = null,
                            modifier = Modifier.clickable {
                                showPassword = !showPassword
                            },
                            tint = Color.White
                        )
                    },
                    label = {
                        androidx.compose.material.Text(
                            text = "密码",
                            fontSize = 14.sp,
                            color = Color.White
                        )
                    },
                    visualTransformation = if(showPassword) VisualTransformation.None else PasswordVisualTransformation(),
                    colors = TextFieldDefaults.textFieldColors(
//                        backgroundColor = Color.Transparent,
                        focusedIndicatorColor = Color.LightGray,
                        unfocusedIndicatorColor = Color.LightGray,
                        focusedLabelColor = Color.LightGray,
                        unfocusedLabelColor = Color.LightGray,
                        cursorColor = Color.White
                    ),

                    )

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = {

                    },
                    shape = CircleShape,
                ) {

                    androidx.compose.material.Text(text = "登录", color = Color.White)

                }


            }
            TextButton(onClick = { }) {
                androidx.compose.material.Text(text = "还没有账号？点击立即注册", color = Color.LightGray, fontSize = 12.sp)
            }
        }
    }
}
