# Checklist - Próximos Passos - INSPEC360 Android

## ✅ Concluído

- [x] Arquitetura Clean + MVVM
- [x] Database Room com 5 tabelas
- [x] DAOs e Repositories
- [x] Use Cases (7 implementados)
- [x] ViewModels com StateFlow
- [x] Jetpack Compose UI
- [x] Tema INSPEC360 (cores, tipografia)
- [x] Navegação Compose
- [x] Hilt DI
- [x] Componentes reutilizáveis
- [x] Data seeding (usuários, estruturas)
- [x] Validações básicas
- [x] ProGuard rules
- [x] Documentação (README, SETUP, ADR)

## 🔄 Próximos Passos Recomendados

### Fase 1: Funcionalidades Básicas
- [ ] **Integrar GPS Real**
  - Usar Google Play Services Location
  - Capturar coordenadas ao salvar anomalia
  - Pedir permissões em runtime (Android 6+)
  - Arquivo: Criar `LocationManager.kt` em `util/`

- [ ] **Integrar Câmera Real**
  - Jetpack CameraX para captura
  - Salvar fotos em cache interno
  - Pedir permissão CAMERA
  - Arquivo: Criar `CameraViewModel.kt`

- [ ] **Finalizar Fluxo de Inspeção**
  - Conectar view com viewmodel em InspectionViewModel
  - Implementar lista de anomalias por inspeção
  - Botão "Adicionar Anomalia" dentro da inspeção
  - Arquivo: Criar `InspectionDetailScreen.kt`

### Fase 2: Persistência Avançada
- [ ] **Data Seeding Condicional**
  - Executar apenas em DEBUG
  - Detectar primeira execução
  - Arquivo: Modificar `DatabaseModule.kt`

- [ ] **Export de Dados**
  - JSON export via Files API
  - CSV export para Excel
  - Salvar em Downloads
  - Arquivo: Criar `DataExportUseCase.kt`

- [ ] **Backup Automático**
  - WorkManager para backup periódico
  - Detectar mudanças (sincronizada=false)
  - Arquivo: Criar `BackupWorker.kt`

### Fase 3: Sincronização com Servidor (Futuro)
- [ ] **API REST**
  - Retrofit + OkHttp
  - Endpoints: /login, /structures, /inspections/sync
  - Arquivo: Criar pasta `data/remote/`

- [ ] **Sincronização Automática**
  - WorkManager para sync background
  - Conflict resolution (server wins)
  - Retry com backoff
  - Arquivo: Criar `SyncWorker.kt`

- [ ] **Autenticação Bearer Token**
  - JWT ao invés de local-only
  - Refresh token logic
  - Arquivo: Modificar `LoginUseCase.kt`

### Fase 4: Testes
- [ ] **Unit Tests (Domain)**
  - LoginUseCase
  - ValidateAnomalyUseCase
  - Arquivo: `test/java/br/com/inspec360/domain/...`

- [ ] **Integration Tests (Data)**
  - DAOs
  - Repositories
  - Arquivo: `androidTest/java/br/com/inspec360/data/...`

- [ ] **UI Tests (Compose)**
  - LoginScreen
  - StructuresScreen
  - Arquivo: `androidTest/java/br/com/inspec360/presentation/...`

### Fase 5: Otimizações
- [ ] **Performance**
  - Paginação em Structures (LazyColumn)
  - Cache de imagens com Coil
  - Comprimir JSONs ao exportar

- [ ] **Acessibilidade**
  - Testar com TalkBack
  - Contraste confirmado (WCAG AA)
  - Tamanhos de touch >= 56dp

- [ ] **Segurança**
  - BCrypt para senhas (ao invés de SHA-256)
  - Encriptar dados sensíveis (EncryptedSharedPreferences)
  - Obfuscação R8 em release

## 🚀 Instruções por Arquivo

### GPS (locationManager.kt)
```kotlin
// Criar singleton para gerenciar GPS
// Métodos:
// - startLocationUpdates()
// - stopLocationUpdates()
// - getCurrentLocation(): Pair<Double, Double>

// Integrar em:
// - AnomalyRegistrationScreen (capturar ao salvar)
// - AnomalyViewModel (armazenar coordenadas)
```

### Câmera (CameraViewModel.kt)
```kotlin
// Estender ViewModel
// Métodos:
// - takePicture(): Flow<Uri>
// - savePictureLocally(uri: Uri): String (retornar path)

// Integrar em:
// - AnomalyRegistrationScreen (botão "Tirar Foto")
// - AnomalyViewModel (armazenar fotoPath)
```

### Export (DataExportUseCase.kt)
```kotlin
// UseCase para exportar
// Formatos: JSON, CSV
// Métodos:
// - exportInspectionsJson(): Uri
// - exportInspectionsCsv(): Uri

// Chamar em:
// - SettingsScreen (botão "Exportar Dados")
// - SettingsViewModel (novo file, se necessário)
```

### API (Retrofit + Remote Data Source)
```kotlin
// Criar br/com/inspec360/data/remote/
// - ApiService.kt (interface com endpoints)
// - RemoteDataSource.kt (implementação)
// - Modificar repositories para hybrid (local + remote)
```

### Sincronização (SyncWorker.kt)
```kotlin
// Estender Worker
// Lógica:
// 1. Listar inspeções com sincronizada=false
// 2. Fazer POST para /inspections/sync
// 3. Se sucesso: sincronizada=true
// 4. Se falha: retry com backoff

// Registrar em:
// - DatabaseModule (agendar periodic)
```

## 📋 Ordem Recomendada

1. **GPS** (5 horas) - Crítico para inspeção
2. **Câmera** (8 horas) - Crítico para foto
3. **Fluxo Completo** (10 horas) - Testar tudo junto
4. **Testes** (15 horas) - Validar regras
5. **Sincronização** (20 horas) - Preparar produção
6. **UI/UX Polish** (10 horas) - Refinamento

**Total Estimado: 68 horas = ~1.7 semanas**

## 🔐 Checklist de Segurança

- [ ] Nenhuma senha em logs
- [ ] Nenhuma chave API no código
- [ ] Permissões pedidas em runtime
- [ ] ProGuard ativado em release
- [ ] APK assinado com chave privada
- [ ] Testar com USB Debugging desativado

## 📱 Checklist de Testes Manuais

- [ ] Login com credenciais corretas
- [ ] Login com credenciais incorretas
- [ ] Listar estruturas
- [ ] Buscar estrutura
- [ ] Iniciar inspeção
- [ ] Registrar anomalia (todos os tipos)
- [ ] Validar foto obrigatória (GRAVE/CRÍTICA)
- [ ] Validar GPS obrigatório
- [ ] Finalizar inspeção
- [ ] Ver histórico
- [ ] Exportar dados
- [ ] Testar orientação landscape
- [ ] Testar com luvas (tap preciso)

## 🎯 Definição de "Pronto para Produção"

✅ Todas as telas funcionando
✅ Banco persistindo dados
✅ GPS capturando coordenadas
✅ Câmera capturando fotos
✅ Validações funcionando
✅ Testes passando (>80% coverage)
✅ Build release sem warnings
✅ Manual testing completo
✅ Performance OK (< 2s init)
✅ Segurança validada

---

**Status Atual**: MVP Funcional
**Próximo Milestone**: Integração GPS + Câmera
**Data Alvo**: 2 semanas
