package br.com.inspec360

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import br.com.inspec360.presentation.navigation.Screen
import br.com.inspec360.presentation.ui.screen.AnomalyRegistrationScreen
import br.com.inspec360.presentation.ui.screen.DashboardScreen
import br.com.inspec360.presentation.ui.screen.HistoryScreen
import br.com.inspec360.presentation.ui.screen.InspectionStartScreen
import br.com.inspec360.presentation.ui.screen.LoginScreen
import br.com.inspec360.presentation.ui.screen.SettingsScreen
import br.com.inspec360.presentation.ui.screen.StructuresScreen
import br.com.inspec360.presentation.ui.theme.INSPEC360Theme
import br.com.inspec360.presentation.viewmodel.AnomalyViewModel
import br.com.inspec360.presentation.viewmodel.InspectionViewModel
import br.com.inspec360.presentation.viewmodel.LoginUiState
import br.com.inspec360.presentation.viewmodel.LoginViewModel
import br.com.inspec360.presentation.viewmodel.StructuresUiState
import br.com.inspec360.presentation.viewmodel.StructuresViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            INSPEC360Theme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = Screen.Login.route
                ) {
                    // Login
                    composable(Screen.Login.route) {
                        val viewModel: LoginViewModel = hiltViewModel()
                        val uiState = androidx.lifecycle.compose.collectAsStateWithLifecycle(
                            viewModel.uiState
                        ).value

                        LoginScreen(
                            onLoginSuccess = {
                                navController.navigate(Screen.Dashboard.route) {
                                    popUpTo(Screen.Login.route) { inclusive = true }
                                }
                            },
                            onLogin = { username, password ->
                                viewModel.login(username, password)
                            },
                            isLoading = uiState is LoginUiState.Loading,
                            error = (uiState as? LoginUiState.Error)?.message
                        )
                    }

                    // Dashboard
                    composable(Screen.Dashboard.route) {
                        val loginViewModel: LoginViewModel = hiltViewModel()
                        val user = androidx.lifecycle.compose.collectAsStateWithLifecycle(
                            loginViewModel.currentUser
                        ).value

                        DashboardScreen(
                            inspectorName = user?.username ?: "Inspector",
                            onNewInspection = { navController.navigate(Screen.Structures.route) },
                            onStructures = { navController.navigate(Screen.Structures.route) },
                            onHistory = { navController.navigate(Screen.History.route) },
                            onSettings = { navController.navigate(Screen.Settings.route) }
                        )
                    }

                    // Structures
                    composable(Screen.Structures.route) {
                        val viewModel: StructuresViewModel = hiltViewModel()
                        val uiState = androidx.lifecycle.compose.collectAsStateWithLifecycle(
                            viewModel.uiState
                        ).value

                        when (uiState) {
                            is StructuresUiState.Success -> {
                                StructuresScreen(
                                    structures = uiState.structures,
                                    onStructureSelected = { structure ->
                                        navController.navigate(
                                            Screen.InspectionStart.createRoute(structure.id)
                                        )
                                    },
                                    onSearch = { query -> viewModel.search(query) },
                                    onBack = { navController.popBackStack() }
                                )
                            }
                            is StructuresUiState.Error -> {
                                StructuresScreen(
                                    structures = emptyList(),
                                    onStructureSelected = {},
                                    onSearch = {},
                                    onBack = { navController.popBackStack() }
                                )
                            }
                            is StructuresUiState.Loading -> {
                                StructuresScreen(
                                    structures = emptyList(),
                                    onStructureSelected = {},
                                    onSearch = {},
                                    onBack = { navController.popBackStack() }
                                )
                            }
                        }
                    }

                    // Inspection Start
                    composable(
                        Screen.InspectionStart.route,
                        arguments = listOf(
                            navArgument("structureId") { type = NavType.LongType }
                        )
                    ) { backStackEntry ->
                        val structureId = backStackEntry.arguments?.getLong("structureId") ?: return@composable
                        val viewModel: StructuresViewModel = hiltViewModel()
                        val inspectionViewModel: InspectionViewModel = hiltViewModel()

                        // In real implementation, you'd get the structure from viewModel
                        // For now, showing a placeholder
                        InspectionStartScreen(
                            structure = br.com.inspec360.domain.model.Structure(
                                id = structureId,
                                numero = "001",
                                tipo = "Torre",
                                classe = "Classe A",
                                progressiva = 10.5,
                                travessia = null,
                                critica = false,
                                coordenadaX = 0.0,
                                coordenadaY = 0.0
                            ),
                            onStart = {
                                inspectionViewModel.startInspection(structureId, 1)
                            },
                            onBack = { navController.popBackStack() }
                        )
                    }

                    // Anomaly Registration
                    composable(
                        Screen.AnomalyRegistration.route,
                        arguments = listOf(
                            navArgument("inspectionId") { type = NavType.LongType }
                        )
                    ) { backStackEntry ->
                        val inspectionId = backStackEntry.arguments?.getLong("inspectionId") ?: return@composable
                        val viewModel: AnomalyViewModel = hiltViewModel()

                        AnomalyRegistrationScreen(
                            onSave = { componente, tipo, severidade, obs, foto ->
                                // TODO: Save anomaly
                            },
                            onBack = { navController.popBackStack() }
                        )
                    }

                    // History
                    composable(Screen.History.route) {
                        val viewModel: InspectionViewModel = hiltViewModel()
                        val historyState = androidx.lifecycle.compose.collectAsStateWithLifecycle(
                            viewModel.historyState
                        ).value

                        when (historyState) {
                            is br.com.inspec360.presentation.viewmodel.InspectionHistoryState.Success -> {
                                HistoryScreen(
                                    inspections = historyState.inspections,
                                    onBack = { navController.popBackStack() }
                                )
                            }
                            else -> {
                                HistoryScreen(
                                    inspections = emptyList(),
                                    onBack = { navController.popBackStack() }
                                )
                            }
                        }
                    }

                    // Settings
                    composable(Screen.Settings.route) {
                        SettingsScreen(
                            appVersion = "1.0.0",
                            onExportData = { /* TODO */ },
                            onClearData = { /* TODO */ },
                            onLogout = {
                                navController.navigate(Screen.Login.route) {
                                    popUpTo(0) { inclusive = true }
                                }
                            },
                            onBack = { navController.popBackStack() }
                        )
                    }
                }
            }
        }
    }
}
