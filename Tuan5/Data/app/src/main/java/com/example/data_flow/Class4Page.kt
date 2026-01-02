package com.example.data_flow

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.VpnKey
import com.example.data_flow.R

open class Class4Page(
    @DrawableRes val iconBack:Int?=null,
    @DrawableRes val image:Int,
    val text1:String,
    val text2:String,
    val text3:String,
    val button:String?=null,
    val emailtextfield1:String?=null,
    val passcodetextfield2:String?=null,
    val passwordtext3:String?=null,
    val confirmpasswordtextfield3:String?=null,
    val showEmailError:String?=null,
    val showPasscodeError:String?=null,
    val showPasswordError:String?=null
)

class page1:Class4Page(
    image = R.drawable.anhuth,
    text1 = "Smart Tasks",
    text2 = "Forget Password?",
    text3 = "Enter your Email, we will send you a verification code.",
    button = "Next",
    emailtextfield1="Nhập email"
)

class page2:Class4Page(
    iconBack = R.drawable.ic_back_24,
    image = R.drawable.anhuth,
    text1 = "Smart Tasks",
    text2 = "Verify Code",
    text3 = "Enter the the code we just sent you on your registered Email",
    button = "Next",
    passcodetextfield2="Nhập mã xác nhận 6 số"
)

class page3:Class4Page(
    iconBack = R.drawable.ic_back_24,
    image = R.drawable.anhuth,
    text1 = "Smart Tasks",
    text2 = "Create new password",
    text3 = "Your new password must be different form previously used password",
    button = "Next",
    passwordtext3="Nhập mật khẩu",
    confirmpasswordtextfield3="Nhập lại mật khẩu"
)

class page4:Class4Page(
    iconBack = R.drawable.ic_back_24,
    image = R.drawable.anhuth,
    text1 = "Smart Tasks",
    text2 = "Confirm",
    text3 = "We are here to help you!",
    button = "Submit",
    showEmailError = "Email",
    showPasscodeError = "Passcode",
    showPasswordError = "Password"
)

@Composable
fun Class4PageUi(
    pagescreen: Class4Page,
    onNextClick: () -> Unit = {},
    onBackClick: () -> Unit = {},
    currentEmail: String = "",
    currentPasscode: String = "",
    currentPassword: String = "",
    onEmailChange: (String) -> Unit = {},
    onPasscodeChange: (String) -> Unit = {},
    onPasswordChange: (String) -> Unit = {}
){

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 35.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            if (pagescreen.iconBack != null) {
                IconButton(
                    onClick = onBackClick,
                    modifier = Modifier
                        .padding(start = 10.dp)
                ) {
                    Icon(
                        painter = painterResource(pagescreen.iconBack),
                        contentDescription = "Back Icon",
                        tint = Color.Blue,
                        modifier = Modifier.size(38.dp)
                    )
                }// iconbutton
            }//if
        }//row
        Box {
            Box(
                modifier = Modifier.fillMaxWidth().padding(top = 50.dp),
                contentAlignment = Alignment.Center

            ) {
                Image(
                    painter = painterResource(pagescreen.image),
                    contentDescription = "Image",
                    modifier = Modifier.size(200.dp)
                )

            Box(
                modifier = Modifier.fillMaxWidth().offset(y = 55.dp),
                contentAlignment = Alignment.Center
            ){
                Text(text=pagescreen.text1,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Blue)
            }
            }//box
        }//box lớn
        Spacer(modifier = Modifier.height(8.dp))

        Text(text=pagescreen.text2,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black)

        Spacer(modifier = Modifier.height(8.dp))

        Text(text=pagescreen.text3,
            fontSize = 17.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Normal,
            color = Color.Gray,
            modifier = Modifier.padding(horizontal = 20.dp)
        )
        Spacer(modifier = Modifier.height(30.dp))
        var isError by remember { mutableStateOf(false) }
        if(pagescreen.emailtextfield1!=null){
            OutlinedTextField(
                value = currentEmail,
                onValueChange = {
                    onEmailChange(it)
                    isError = it.isBlank() || !it.contains("@")
                },
                label = { Text(text = pagescreen.emailtextfield1,fontSize = 18.sp) },
                isError = isError,
                supportingText = {
                    if (isError) {
                        Text(text = "Email không được để trống và phải có @", color = Color.Red)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth().padding(horizontal = 20.dp),
                shape=RoundedCornerShape(12.dp),
                leadingIcon = {
                     Icon(imageVector = Icons.Default.Email, contentDescription = "Email Icon")
                }
            )
        }
        

        if(pagescreen.passcodetextfield2!=null) {
            OutlinedTextField(
                value = currentPasscode,
                onValueChange = { 
                    if (it.length <= 6 && it.all { char -> char.isDigit() }) {
                        onPasscodeChange(it)
                    }
                },
                label = { Text(text = pagescreen.passcodetextfield2, fontSize = 18.sp) },
                modifier = Modifier
                    .fillMaxWidth().padding(horizontal = 20.dp),
                shape = RoundedCornerShape(12.dp),
                leadingIcon = {
                    Icon(imageVector = Icons.Default.VpnKey, contentDescription = "Passcode Icon")
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
        }


        if(pagescreen.passwordtext3!=null){
            OutlinedTextField(
                value = currentPassword,
                onValueChange = { onPasswordChange(it) },
                label = {Text(text = pagescreen.passwordtext3,fontSize = 18.sp)},
                modifier = Modifier
                    .fillMaxWidth().padding(horizontal = 20.dp),
                shape=RoundedCornerShape(12.dp),
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Lock, contentDescription = "Password Icon")
                }
                )

        }
        
        Spacer(modifier = Modifier.height(15.dp))

        var confirmpassword by remember { mutableStateOf("")}
        if(pagescreen.confirmpasswordtextfield3!=null){
            OutlinedTextField(
                value = confirmpassword,
                onValueChange = { confirmpassword = it},
                label = {Text(text = pagescreen.confirmpasswordtextfield3,fontSize = 18.sp)},
                modifier = Modifier
                    .fillMaxWidth().padding(horizontal = 20.dp),
                shape=RoundedCornerShape(12.dp),
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Lock, contentDescription = "Confirm Password Icon")
                }
            )

        }
        

        if (pagescreen.showEmailError != null) {
            OutlinedTextField(
                value = currentEmail,
                onValueChange = {},
                enabled = false, 
                readOnly = true,
                label = { Text("Email đã nhập") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                shape = RoundedCornerShape(12.dp),
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Email, contentDescription = null)
                },
                colors = OutlinedTextFieldDefaults.colors(
                    disabledTextColor = Color.Black,
                    disabledLabelColor = Color.Gray,
                    disabledBorderColor = Color.Gray,
                    disabledLeadingIconColor = Color.Gray
                )
            )
            Spacer(modifier = Modifier.height(10.dp))
        }

        if (pagescreen.showPasscodeError != null) {
            OutlinedTextField(
                value = currentPasscode,
                onValueChange = {},
                enabled = false,
                readOnly = true,
                label = { Text("Mã Code đã nhập") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                shape = RoundedCornerShape(12.dp),
                leadingIcon = {
                    Icon(imageVector = Icons.Default.VpnKey, contentDescription = null)
                },
                colors = OutlinedTextFieldDefaults.colors(
                    disabledTextColor = Color.Black,
                    disabledLabelColor = Color.Gray,
                    disabledBorderColor = Color.Gray,
                    disabledLeadingIconColor = Color.Gray
                )
            )
            Spacer(modifier = Modifier.height(10.dp))
        }

        if (pagescreen.showPasswordError != null) {
            OutlinedTextField(
                value = currentPassword,
                onValueChange = {},
                enabled = false,
                readOnly = true,
                label = { Text("Mật khẩu mới") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                shape = RoundedCornerShape(12.dp),
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Lock, contentDescription = null)
                },
                colors = OutlinedTextFieldDefaults.colors(
                    disabledTextColor = Color.Black,
                    disabledLabelColor = Color.Gray,
                    disabledBorderColor = Color.Gray,
                    disabledLeadingIconColor = Color.Gray
                )
            )
        }

        if (pagescreen.button != null) {
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = onNextClick,
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .padding(horizontal = 20.dp)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
            ) {
                Text(text = pagescreen.button, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }
        }
    }//Cloumn
}
@Preview(showBackground = true,showSystemUi = true)
@Composable
fun Class4PageUiPreview(){
    Class4PageUi(pagescreen = page1())
}