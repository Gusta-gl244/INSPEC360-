# Quick Start - Validar Projeto

## ⚡ 5 Minutos: Verificar Estrutura

### 1. Estrutura de Pastas ✅
```bash
cd android
ls -la
# Deve ter: build.gradle.kts, settings.gradle.kts, app/, gradle/
```

### 2. Verificar Sincronização Gradle
```bash
# Abrir em Android Studio
# Aguardar "Build successful" na parte inferior
# Ou rodar:
./gradlew clean
```

### 3. Verificar Erros de Compilação
```bash
# Listar erros
./gradlew build --stacktrace

# Esperado: Build successful ou errors relativos a runtime
```

## 📱 10 Minutos: Primeira Execução

### 1. Emulador/Dispositivo
```bash
# Iniciar emulador
emulator -avd Tablet_10

# Ou conectar tablet real (USB Debugging ON)
adb devices
```

### 2. Build & Run
```bash
# Build debug
./gradlew installDebug

# Ou pelo Android Studio:
# - Selecionar dispositivo
# - Clicar Run (Play verde)
# - Esperar ~30-60s
```

### 3. Testar Login
```
Usuário: inspetor1
Senha: senha123
(Clickar "Entrar")
```

### Esperado
```
✅ Exibir Dashboard
✅ Botão "NOVA INSPEÇÃO" em destaque dourado
✅ Sem crashes
```

## 🔍 Validar Arquitetura

### Clean Architecture Layers

```bash
# Verificar domain/ (puro, sem Android)
find app/src/main/java/br/com/inspec360/domain -name "*.kt"
# Deve conter: model/, repository/, usecase/

# Verificar data/ (acesso dados)
find app/src/main/java/br/com/inspec360/data -name "*.kt"
# Deve conter: local/, mapper/, repository/

# Verificar presentation/ (UI)
find app/src/main/java/br/com/inspec360/presentation -name "*.kt"
# Deve conter: ui/, viewmodel/, navigation/
```

### Room Database

```bash
# Conectar ao emulador
adb shell

# Acessar banco
sqlite3 /data/data/br.com.inspec360/databases/inspec360_db

# Listar tabelas
.tables

# Esperado:
# users structures inspections anomalies references
```

## 🎯 Testes Manuais Rápidos

### Login
```
✅ Usuário inválido → Mostrar erro
✅ Senha inválida → Mostrar erro
✅ inspetor1/senha123 → Dashboard
```

### Navegação
```
✅ Clicar "Estruturas" → Lista com 5 estruturas
✅ Clicar "Histórico" → Lista vazia (OK)
✅ Clicar "Configurações" → Ver versão 1.0.0
✅ Clicar "Sair" → Voltar ao Login
```

### Busca Estruturas
```
✅ Digitar "0/1" → Filtrar 1 resultado
✅ Digitar "Torre" → Mostrar torres
✅ Limpar → Mostrar todos
```

## 🛠️ Debug

### Ver Logs
```bash
# Terminal
adb logcat | grep inspec360

# Ou Android Studio
# Windows: Ctrl+Alt+A → Logcat
# Mac: Cmd+Option+A → Logcat
```

### Inspecionar Database
```bash
# Android Studio
# Aba: App Inspection → Database Inspector
# Expandir "inspec360_db"
# Ver tabelas em tempo real
```

### Layout Inspector
```bash
# Android Studio
# Tools → Layout Inspector
# Inspecionar componentes Compose
# Ver hierarquia UI
```

## 📦 Gerar Release APK

```bash
# Build release (sem debug info, otimizado)
./gradlew assembleRelease

# APK em:
# app/build/outputs/apk/release/app-release-unsigned.apk

# Instalar (sem assinatura, só para teste)
adb install -r app/build/outputs/apk/release/app-release-unsigned.apk
```

## ⚠️ Erros Comuns

### "Gradle sync failed"
```bash
# Solução
File → Invalidate Caches
rm -rf .gradle
./gradlew clean
```

### "Unsupported Java Version"
```bash
# Verificar JDK
java -version

# Deve ser 17+
# Se não tiver, descarregar em java.com
```

### "SDK version not found"
```bash
# Solução
Android Studio → Tools → SDK Manager
Marcar: Android 14 (API 34)
Clicar Apply
```

### "emulator not found"
```bash
# Criar emulador
Android Studio → Device Manager → Create Virtual Device
- Tablet (pode selecionar)
- API 34
- System Image: Android 14
```

## ✅ Checklist Final

- [ ] Gradle sincroniza sem erro
- [ ] ./gradlew build completa
- [ ] App instala no emulador/device
- [ ] Login funciona
- [ ] Dashboard abre
- [ ] Estruturas lista
- [ ] Banco tem dados
- [ ] Logcat sem crashes críticos
- [ ] Release APK gerado

---

**Tempo Total**: ~15 minutos
**Próximo Passo**: Ler CHECKLIST.md para novas features
