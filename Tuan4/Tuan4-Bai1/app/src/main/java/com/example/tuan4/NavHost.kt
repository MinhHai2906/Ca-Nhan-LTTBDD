package com.example.tuan4

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.tuan4.ui.theme.PageUiScreen

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "home") {

        composable("home") {
            HomeScreen(onNavigateToPageUi = {
                navController.navigate("pageuicompose")
            })
        }

        composable("pageuicompose") {
            PageUiScreen(
                onBack = {
                    navController.popBackStack()
                },
                onChuyenManHinh = { idManHinh ->
                    navController.navigate(idManHinh)
                }
            )
        }

        composable("man_hinh_text") {
            TextScreen(onBack = {
                navController.popBackStack()
            })
        }

        composable("man_hinh_image") {
            ImageScreen(onBack = {
                navController.popBackStack()
            })
        }

        composable("man_hinh_textfield") { 
            TextFieldScreen(onBack = {
                navController.popBackStack()
            }) 
        }
        composable("man_hinh_password") { 
            PassWordScreen(onBack = {
                navController.popBackStack()
            }) 
        }
        composable("man_hinh_column") { 
            ColumnScreen(onBack = {
                navController.popBackStack()
            }) 
        }
        composable("man_hinh_row") { 
            RowScreen(onBack = {
                navController.popBackStack()
            }) 
        }
        
        composable("man_hinh_topappbar") {
             TopbarScreen(onBack = {
                 navController.popBackStack()
             })
        }
        
        composable("man_hinh_alertdialog") {
            AlertDialogScreen(onBack = {
                navController.popBackStack()
            })
        }
        
        composable("man_hinh_snackbar") {
            SnackbarScreen(onBack = {
                navController.popBackStack()
            })
        }
    }
}
