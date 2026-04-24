# ProGuard rules for INSPEC360

# Keep data classes
-keep class br.com.inspec360.domain.model.** {
    *;
}

-keep class br.com.inspec360.data.local.entity.** {
    *;
}

# Keep Room database
-keep @androidx.room.Entity class *
-keep @androidx.room.Dao interface *
-keep @androidx.room.Database class *

# Keep Hilt
-keep class **_HiltModules.* {
    *;
}
-keep class dagger.hilt.** {
    *;
}
-keep class * extends dagger.hilt.android.internal.managers.ViewComponentManager$ViewComponentManagerFragmentContextHolder

# Keep Retrofit/OkHttp (se usar no futuro)
-dontwarn okhttp3.**
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keep interface retrofit2.** { *; }

# Keep Compose
-keep class androidx.compose.** { *; }
-keep interface androidx.compose.** { *; }

# Keep coroutines
-keep class kotlinx.coroutines.** { *; }

# Keep Kotlin
-keep class kotlin.** { *; }
-keep interface kotlin.** { *; }

# Optimize but be conservative
-optimizationpasses 5
-dontusemixedcaseclassnames
-verbose

# Logging
-assumenosideeffects class android.util.Log {
    public static *** d(...);
    public static *** v(...);
}

# Exception removal for smaller file size
-dontnote kotlin.internal.**
-dontnote **.**$Factory$ProguardMerged
