# Guia de Setup - INSPEC360 Android

## 📋 Pré-requisitos

1. **Android Studio** (versão Giraffe ou superior)
2. **Java Development Kit (JDK)** versão 17 ou superior
3. **Android SDK** versão 34 ou superior
4. **Git** (opcional, para controle de versão)

## 🚀 Configuração Inicial

### 1. Abrir o Projeto

```bash
# Navegar para o diretório do projeto
cd android

# No Android Studio, File → Open → Selecionar pasta android/
```

### 2. Sincronizar Gradle

- Android Studio sincronizará automaticamente
- Se não sincronizar, clicar em "Sync Now"
- Aguardar download de dependências (primeira vez pode levar alguns minutos)

### 3. Instalar SDK/NDK (se necessário)

- Ir em: Tools → SDK Manager
- Aba "SDK Platforms": Marcar Android 14 (API 34)
- Aba "SDK Tools": Marcar "Google Play services"
- Clicar "Apply" e aceitar licenças

### 4. Criar Emulador ou Conectar Dispositivo

#### Opção A: Emulador
```
Tools → Device Manager → Create Virtual Device
- Device: Tablet (10-11")
- API: 34
- System Image: Android 14 (x86_64)
```

#### Opção B: Dispositivo Real
- Ligar USB Debugging
- Conectar tablet via USB
- Android Studio reconhecerá automaticamente

## 🏗️ Estrutura do Projeto

```
android/
├── app/
│   ├── build.gradle.kts      # Configuração do app
│   └── src/
│       └── main/
│           ├── AndroidManifest.xml
│           ├── java/br/com/inspec360/
│           │   ├── domain/       # Regras de negócio
│           │   ├── data/         # Acesso a dados
│           │   ├── presentation/ # UI Compose
│           │   ├── di/           # Injeção de dependências
│           │   └── util/         # Utilitários
│           └── res/
│               └── values/       # Strings, cores, temas
├── build.gradle.kts           # Configuração root
├── settings.gradle.kts        # Configuração de módulos
└── gradle/                     # Wrapper Gradle
```

## 🔧 Configuração de Dependências

### Editar build.gradle.kts (app)

As dependências já estão configuradas:

```kotlin
// Room Database
implementation("androidx.room:room-runtime:2.5.2")
kapt("androidx.room:room-compiler:2.5.2")

// Compose
implementation("androidx.compose.material3:material3:1.1.1")

// Hilt DI
implementation("com.google.dagger:hilt-android:2.48")
kapt("com.google.dagger:hilt-compiler:2.48")

// Navigation
implementation("androidx.navigation:navigation-compose:2.7.5")

// Location
implementation("com.google.android.gms:play-services-location:21.0.1")
```

## 🏃 Executar Aplicativo

### 1. Build Local Debug

```bash
./gradlew assembleDebug
```

### 2. Via Android Studio

1. Selecionar dispositivo/emulador no top menu
2. Clicar em "Run" (Play verde)
3. Selecionar "app"

### 3. Instalar APK Diretamente

```bash
adb install -r app/build/outputs/apk/debug/app-debug.apk
```

## 🧪 Dados Iniciais (Seed)

Ao primeiro acesso, o banco será populado com:

**Usuários:**
- Username: `inspetor1` | Password: `senha123` | Papel: INSPETOR
- Username: `inspetor2` | Password: `senha123` | Papel: INSPETOR
- Username: `admin` | Password: `admin123` | Papel: ADMIN

**Estruturas:** 5 estruturas de exemplo (algumas críticas)

**Referências:** Severidades, fases, tipos de anomalia, componentes

## 🔍 Debugar

### Logcat
```
Android Studio → Logcat Tab
- Filtrar por package: br.com.inspec360
- Level: Debug ou Info
```

### Database Inspector
```
Android Studio → App Inspection → Database Inspector
- Visualizar tabelas Room em tempo real
- Ver queries SQL
```

### Layout Inspector
```
Android Studio → Layout Inspector
- Inspecionar hierarquia de componentes Compose
- Debug de posicionamento e tamanhos
```

## 📦 Gerar Release APK

```bash
# Build release
./gradlew bundleRelease

# Ou APK universal
./gradlew assembleRelease

# Saída em: app/build/outputs/apk/release/app-release.apk
```

## 🐛 Troubleshooting

### Erro: "Unsupported Java Version"
```bash
# Verificar JDK
java -version

# Definir JAVA_HOME se necessário
# Windows: JAVA_HOME=C:\Program Files\Java\jdk-17
# Linux/Mac: export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk-17.jdk/Contents/Home
```

### Erro: "Gradle sync failed"
- File → Invalidate Caches → Restart
- Deletar pasta .gradle
- Clicar "Sync Now" novamente

### Emulador não aparece
- Tools → Device Manager → Clicar play no dispositivo
- Aguardar boot (pode levar 1-2 minutos)

### APK não instala
```bash
# Limpar e reinstalar
adb uninstall br.com.inspec360
adb install app/build/outputs/apk/debug/app-debug.apk
```

## 📚 Recursos Úteis

- **Jetpack Compose**: https://developer.android.com/jetpack/compose
- **Room Database**: https://developer.android.com/training/data-storage/room
- **Hilt**: https://dagger.dev/hilt
- **Navigation**: https://developer.android.com/guide/navigation

## 🎯 Próximas Passos

1. **Integrar GPS** - Usar Play Services Location
2. **Câmera** - Jetpack CameraX
3. **Sincronização** - Implementar API REST
4. **Testes** - Unit tests + Instrumentation tests
5. **Analytics** - Firebase Analytics

---

**Versão**: 1.0.0  
**Última atualização**: 2026
