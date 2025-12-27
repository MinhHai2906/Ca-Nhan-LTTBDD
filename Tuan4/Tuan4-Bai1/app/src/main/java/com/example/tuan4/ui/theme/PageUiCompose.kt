package com.example.tuan4.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tuan4.R

data class ThanhPhanUI(
    val tieuDe: String,
    val moTa: String,
    val idManHinh: String
)

data class NhomUI(
    val tenNhom: String,
    val danhSachThanhPhan: List<ThanhPhanUI>
)


@Composable
fun PageUiScreen(onBack: () -> Unit, onChuyenManHinh: (String) -> Unit) {
    val danhSachDuLieu = listOf(
        NhomUI(
            tenNhom = "Display",
            danhSachThanhPhan = listOf(
                // Gán idManHinh tương ứng cho từng item
                ThanhPhanUI("Text", "Displays text", "man_hinh_text"),
                ThanhPhanUI("Image", "Displays an image", "man_hinh_image")
            )
        ),
        NhomUI(
            tenNhom = "Input",
            danhSachThanhPhan = listOf(
                ThanhPhanUI("TextField", "Input field for text", "man_hinh_textfield"),
                ThanhPhanUI("PasswordField", "Input field for passwords", "man_hinh_password")
            )
        ),
        NhomUI(
            tenNhom = "Layout",
            danhSachThanhPhan = listOf(
                ThanhPhanUI("Column", "Arranges elements vertically", "man_hinh_column"),
                ThanhPhanUI("Row", "Arranges elements horizontally", "man_hinh_row")
            )
        ),
        NhomUI(
            tenNhom = "Other",
            danhSachThanhPhan = listOf(
                ThanhPhanUI("TopAppBar", "Displays a top bar", "man_hinh_topappbar"),
                ThanhPhanUI("AlertDialog", "Displays an alert dialog", "man_hinh_alertdialog"),
                ThanhPhanUI("Snackbar", "Displays a snackbar", "man_hinh_snackbar")

            )
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp, bottom = 10.dp)
        ) {

            IconButton(
                onClick = onBack,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 10.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = "Quay lại",
                    tint = Color.Blue
                )
            }

            Text(
                text = "UI Components List",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Blue,
                modifier = Modifier.align(Alignment.Center),
                textAlign = TextAlign.Center
            )
        }


        LazyColumn(
            modifier = Modifier.padding(horizontal = 16.dp),
            contentPadding = PaddingValues(bottom = 20.dp)
        ) {
            items(danhSachDuLieu) { nhom ->

                Text(
                    text = nhom.tenNhom,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(vertical = 8.dp)
                )

                nhom.danhSachThanhPhan.forEach { thanhPhan ->
                    TheHienThiThanhPhan(thanhPhan, onClick = {
                        // Khi click, gọi hàm chuyển màn hình với ID tương ứng
                        onChuyenManHinh(thanhPhan.idManHinh)
                    })
                    Spacer(modifier = Modifier.height(15.dp))
                }
            }
        }
    }
}

@Composable
fun TheHienThiThanhPhan(duLieu: ThanhPhanUI, onClick: () -> Unit) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color(0xFFB3E5FC)),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Column(
            modifier = Modifier
                .padding(start = 16.dp, top = 5.dp, bottom = 5.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = duLieu.tieuDe,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Color.Black
            )
            Text(
                text = duLieu.moTa,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color.DarkGray
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PageUiScreenPreview() {
    Tuan4Theme(dynamicColor = false) {
        PageUiScreen(onBack = {}, onChuyenManHinh = {})
    }
}
