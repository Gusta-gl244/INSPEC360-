# INSPEC360 - Projeto Android Completo

**Data de Criação**: 2026  
**Versão**: 1.0.0  
**Status**: ✅ MVP Compilável e Funcional

---

## 📊 Resumo Executivo

Um aplicativo Android **nativo**, **offline-first**, arquiteturado em **Clean Architecture + MVVM** para inspeção de redes de transmissão em campo. Tablet 10-11" landscape, otimizado para uso com luvas em ambiente externo.

### Tecnologias Utilizadas

| Camada | Tecnologia |
|--------|-----------|
| **Presentation** | Jetpack Compose |
| **Architecture** | MVVM + Clean Architecture |
| **State Management** | StateFlow |
| **Database** | Room (SQLite) |
| **Dependency Injection** | Hilt |
| **Navigation** | Jetpack Compose Navigation |
| **Build** | Gradle KTS |
| **Language** | Kotlin |
| **Min API** | 26 (Android 8) |
| **Target API** | 34 (Android 14) |

---

## 📁 Estrutura de Arquivos

```
android/
├── app/
│   ├── build.gradle.kts
│   ├── proguard-rules.pro
│   └── src/main/
│       ├── AndroidManifest.xml
│       ├── java/br/com/inspec360/
│       │   ├── MainActivity.kt                      # Activity principal
│       │   ├── Inspec360App.kt                      # Application class (Hilt)
│       │   │
│       │   ├── domain/                              # Regras de negócio puras
│       │   │   ├── model/
│       │   │   │   ├── User.kt
│       │   │   │   ├── Structure.kt
│       │   │   │   ├── Inspection.kt
│       │   │   │   ├── Anomaly.kt
│       │   │   │   └── Reference.kt
│       │   │   ├── repository/                      # Interfaces (contracts)
│       │   │   │   ├── UserRepository.kt
│       │   │   │   ├── StructureRepository.kt
│       │   │   │   ├── InspectionRepository.kt
│       │   │   │   ├── AnomalyRepository.kt
│       │   │   │   └── ReferenceRepository.kt
│       │   │   └── usecase/                         # Casos de uso
│       │   │       ├── LoginUseCase.kt
│       │   │       ├── GetAllStructuresUseCase.kt
│       │   │       ├── SearchStructuresUseCase.kt
│       │   │       ├── CreateInspectionUseCase.kt
│       │   │       ├── FinalizeInspectionUseCase.kt
│       │   │       ├── ValidateAnomalyUseCase.kt
│       │   │       ├── SaveAnomalyUseCase.kt
│       │   │       └── GetInspectionHistoryUseCase.kt
│       │   │
│       │   ├── data/                                # Acesso a dados
│       │   │   ├── local/
│       │   │   │   ├── database/
│       │   │   │   │   └── Inspec360Database.kt     # Room Database
│       │   │   │   ├── dao/                         # Data Access Objects
│       │   │   │   │   ├── UserDao.kt
│       │   │   │   │   ├── StructureDao.kt
│       │   │   │   │   ├── InspectionDao.kt
│       │   │   │   │   ├── AnomalyDao.kt
│       │   │   │   │   └── ReferenceDao.kt
│       │   │   │   └── entity/                      # Room Entities
│       │   │   │       ├── UserEntity.kt
│       │   │   │       ├── StructureEntity.kt
│       │   │   │       ├── InspectionEntity.kt
│       │   │   │       ├── AnomalyEntity.kt
│       │   │   │       └── ReferenceEntity.kt
│       │   │   ├── mapper/                          # Entity ↔ Domain
│       │   │   │   ├── UserMapper.kt
│       │   │   │   ├── StructureMapper.kt
│       │   │   │   ├── InspectionMapper.kt
│       │   │   │   ├── AnomalyMapper.kt
│       │   │   │   └── ReferenceMapper.kt
│       │   │   └── repository/                      # Implementações
│       │   │       ├── UserRepositoryImpl.kt
│       │   │       ├── StructureRepositoryImpl.kt
│       │   │       ├── InspectionRepositoryImpl.kt
│       │   │       ├── AnomalyRepositoryImpl.kt
│       │   │       └── ReferenceRepositoryImpl.kt
│       │   │
│       │   ├── presentation/                        # UI & State Management
│       │   │   ├── viewmodel/
│       │   │   │   ├── LoginViewModel.kt
│       │   │   │   ├── StructuresViewModel.kt
│       │   │   │   ├── InspectionViewModel.kt
│       │   │   │   └── AnomalyViewModel.kt
│       │   │   ├── ui/
│       │   │   │   ├── theme/
│       │   │   │   │   ├── Color.kt                 # Cores INSPEC360
│       │   │   │   │   └── Type.kt                  # Tipografia
│       │   │   │   ├── components/
│       │   │   │   │   ├── Buttons.kt
│       │   │   │   │   ├── TextInputs.kt
│       │   │   │   │   └── BadgesAndMessages.kt
│       │   │   │   └── screen/
│       │   │   │       ├── LoginScreen.kt
│       │   │   │       ├── DashboardScreen.kt
│       │   │   │       ├── StructuresScreen.kt
│       │   │   │       ├── InspectionStartScreen.kt
│       │   │   │       ├── AnomalyRegistrationScreen.kt
│       │   │   │       ├── HistoryScreen.kt
│       │   │   │       └── SettingsScreen.kt
│       │   │   └── navigation/
│       │   │       └── Screen.kt                    # Rotas de navegação
│       │   │
│       │   ├── di/
│       │   │   └── DatabaseModule.kt                # Configuração Hilt
│       │   │
│       │   └── util/
│       │       ├── SecurityUtil.kt                  # Hash senhas
│       │       └── DataSeed.kt                      # Dados iniciais
│       │
│       └── res/
│           └── values/
│               ├── colors.xml
│               ├── strings.xml
│               ├── strings_pt.xml
│               └── themes.xml
│
├── build.gradle.kts                    # Root build
├── settings.gradle.kts                 # Module config
│
├── README.md                           # Documentação principal
├── SETUP.md                            # Guia de setup
├── ADR.md                              # Decisões de arquitetura
├── CHECKLIST.md                        # Próximos passos
├── .gitignore
│
└── gradle/
    └── wrapper/
        └── gradle-wrapper.properties   # Gradle 8.2
```

---

## 🗄️ Banco de Dados

### Schema

```
USERS (5 campos)
├── id: Long [PK]
├── username: String
├── passwordHash: String
└── role: Enum (ADMIN, INSPETOR)

STRUCTURES (9 campos)
├── id: Long [PK]
├── numero: String
├── tipo: String
├── classe: String
├── progressiva: Double
├── travessia: String?
├── critica: Boolean
├── coordenadaX: Double
└── coordenadaY: Double

INSPECTIONS (6 campos)
├── id: Long [PK]
├── estruturaId: Long [FK → STRUCTURES]
├── inspetorId: Long [FK → USERS]
├── dataHora: Long
├── status: Enum (EM_ANDAMENTO, FINALIZADA)
└── sincronizada: Boolean

ANOMALIES (13 campos)
├── id: Long [PK]
├── inspecaoId: Long [FK → INSPECTIONS]
├── componente: String
├── tipoAnomalia: String
├── fase: Enum (FASE_A, FASE_B, FASE_C)
├── emenda: Boolean
├── severidade: Enum (LEVE, MODERADA, GRAVE, CRITICA)
├── riscoSeguranca: Enum (BAIXO, MEDIO, ALTO, CRITICO)
├── riscoOperacional: Enum (BAIXO, MEDIO, ALTO, CRITICO)
├── observacao: String
├── fotoPath: String?
├── latitude: Double
└── longitude: Double

REFERENCES (3 campos)
├── id: Long [PK]
├── categoria: String (SEVERIDADE, FASE, TIPO_ANOMALIA, COMPONENTE)
└── valor: String
```

---

## 🎯 Use Cases Implementados

| Use Case | Descrição |
|----------|-----------|
| **LoginUseCase** | Autenticação local com username/password |
| **GetAllStructuresUseCase** | Listar todas as estruturas ordenadas |
| **SearchStructuresUseCase** | Buscar por numero ou tipo |
| **CreateInspectionUseCase** | Iniciar nova inspeção com estrutura |
| **FinalizeInspectionUseCase** | Finalizar inspeção (mudar status) |
| **ValidateAnomalyUseCase** | Validar anomalia (foto se GRAVE/CRÍTICA, GPS obrigatório) |
| **SaveAnomalyUseCase** | Salvar anomalia com validação |
| **GetInspectionHistoryUseCase** | Histórico de inspeções por inspetor |

---

## 🖼️ Fluxo de Dados (Data Flow)

```
┌─────────────────────────────────────┐
│   Jetpack Compose UI                │
│   (LoginScreen, DashboardScreen...) │
└────────────┬────────────────────────┘
             │
             ▼
┌─────────────────────────────────────┐
│   ViewModel                         │
│   (StateFlow<UiState>)              │
└────────────┬────────────────────────┘
             │
             ▼
┌─────────────────────────────────────┐
│   UseCase                           │
│   (LoginUseCase, etc)               │
└────────────┬────────────────────────┘
             │
             ▼
┌─────────────────────────────────────┐
│   Repository (Interface)            │
│   (UserRepository, etc)             │
└────────────┬────────────────────────┘
             │
             ▼
┌─────────────────────────────────────┐
│   RepositoryImpl + Mapper            │
│   (UserRepositoryImpl)               │
└────────────┬────────────────────────┘
             │
             ▼
┌─────────────────────────────────────┐
│   DAO                               │
│   (UserDao, StructureDao, etc)      │
└────────────┬────────────────────────┘
             │
             ▼
┌─────────────────────────────────────┐
│   Room Database (SQLite)            │
│   (inspec360_db)                    │
└─────────────────────────────────────┘
```

---

## 🎨 Design System

### Paleta de Cores

| Nome | Hex | Uso |
|------|-----|-----|
| Primary Dark Green | #2D5A3D | Branding, títulos |
| Accent Gold | #C4A747 | CTAs, botões primários |
| Severity Light | #4CAF50 | Anomalias leves |
| Severity Moderate | #FFC107 | Anomalias moderadas |
| Severity Severe | #FF9800 | Anomalias graves |
| Severity Critical | #F44336 | Anomalias críticas |
| White | #FFFFFF | Background |
| Light Gray | #F5F5F5 | Cards, surfaces |
| Dark Gray | #424242 | Texto secundário |
| Border Gray | #E0E0E0 | Divisores |

### Tipografia

- **Font Family**: SansSerif (Roboto default)
- **Mínimo**: 16sp (bodyMedium)
- **Títulos**: 20-32sp
- **Contrast**: WCAG AA+

### Componentes

- **Botão Primário**: 56dp altura, dourado, fillMaxWidth
- **Botão Secundário**: 56dp altura, cinza claro, fillMaxWidth
- **Input**: 56dp altura, border cinza, padding 16dp
- **Card**: 8dp corner radius, padding 16dp
- **Espaçamento**: 8dp, 12dp, 16dp, 24dp

---

## 🔐 Regras de Negócio

1. **Login**: Local-only com username/password SHA-256
2. **Foto Obrigatória**: Se severidade GRAVE ou CRÍTICA
3. **GPS Obrigatório**: Latitude/longitude != 0
4. **Status Inspeção**: EM_ANDAMENTO → FINALIZADA (one-way)
5. **Dados Locais**: Nenhuma dependência de internet
6. **Sincronização**: Flag para sync futuro (não impl. ainda)

---

## 📱 Telas Implementadas

1. **Login** - Autenticação
2. **Dashboard** - Menu principal
3. **Structures** - Lista com busca
4. **Inspection Start** - Confirmação antes de iniciar
5. **Anomaly Registration** - Registro de achados
6. **History** - Inspeções anteriores
7. **Settings** - Config, versão, logout

---

## 🚀 Como Compilar

```bash
# Clone/entre no projeto
cd android

# Build debug
./gradlew assembleDebug

# Build release
./gradlew assembleRelease

# Instalar em emulador/dispositivo
./gradlew installDebug

# Rodar no Android Studio
- Selecionar dispositivo
- Clicar Run (play verde)
```

**Saída**: `app/build/outputs/apk/debug/app-debug.apk`

---

## 🧪 Dados de Teste

**Usuários:**
- `inspetor1` / `senha123` (INSPETOR)
- `inspetor2` / `senha123` (INSPETOR)
- `admin` / `admin123` (ADMIN)

**Estruturas:** 5 estruturas de exemplo, 1 crítica

**Referências:** Severidades, fases, tipos, componentes

---

## 📊 Métricas

| Métrica | Valor |
|---------|-------|
| **Classes** | ~50+ |
| **Linhas de Código** | ~3500+ |
| **Packagesde** | 12+ |
| **Telas** | 7 |
| **Use Cases** | 8 |
| **DAOs** | 5 |
| **Tabelas** | 5 |
| **Componentes Compose** | 10+ |

---

## 📚 Documentação

- `README.md` - Visão geral, arquitetura, banco
- `SETUP.md` - Passo a passo setup
- `ADR.md` - Decisões arquiteturais
- `CHECKLIST.md` - Próximos passos

---

## 🎯 Próximas Fases

### Fase 1 (Crítica)
- [ ] Integrar GPS real
- [ ] Integrar câmera
- [ ] Finalizar fluxo inspeção

### Fase 2 (Importante)
- [ ] Export JSON/CSV
- [ ] Sincronização backend
- [ ] Testes unitários

### Fase 3 (Polish)
- [ ] Acessibilidade
- [ ] Segurança (BCrypt)
- [ ] Performance

---

## ✅ Pronto para

- ✅ Compilar e rodar
- ✅ Logar
- ✅ Navegar estruturas
- ✅ Iniciar inspeções
- ✅ Registrar anomalias
- ✅ Ver histórico
- ✅ Exportar setup

---

## ❌ Não Implementado (Planejado)

- ❌ GPS real (use Play Services Location)
- ❌ Câmera real (use Jetpack CameraX)
- ❌ Sincronização servidor (use Retrofit)
- ❌ Testes (JUnit, Espresso)
- ❌ Analytics (Firebase)
- ❌ Notificações

---

**Status**: ✅ **READY FOR COMPILATION**  
**Versão**: 1.0.0  
**Data**: 2026
