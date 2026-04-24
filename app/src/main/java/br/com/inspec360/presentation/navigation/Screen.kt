package br.com.inspec360.presentation.navigation

sealed class Screen(val route: String) {
    data object Login : Screen("login")
    data object Dashboard : Screen("dashboard")
    data object Structures : Screen("structures")
    data object StructureDetail : Screen("structure_detail/{structureId}") {
        fun createRoute(structureId: Long) = "structure_detail/$structureId"
    }
    data object InspectionStart : Screen("inspection_start/{structureId}") {
        fun createRoute(structureId: Long) = "inspection_start/$structureId"
    }
    data object AnomalyRegistration : Screen("anomaly_registration/{inspectionId}") {
        fun createRoute(inspectionId: Long) = "anomaly_registration/$inspectionId"
    }
    data object History : Screen("history")
    data object Settings : Screen("settings")
}
