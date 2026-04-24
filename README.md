# INSPEC360 - Aplicativo Android Nativo

Inspeção de Redes de Transmissão com Arquitetura Limpa + MVVM + Offline-First

## 📱 Características

- ✅ **100% Nativo Android** - Kotlin + Jetpack Compose
- ✅ **Offline-First** - Banco Room/SQLite local
- ✅ **Clean Architecture** - Domain, Data, Presentation bem separados
- ✅ **MVVM + StateFlow** - Reatividade moderna
- ✅ **Hilt DI** - Injeção de dependências
- ✅ **Landscape Tablet** - Otimizado para tablets 10-11"
- ✅ **Design System INSPEC360** - Cores, tipografia, componentes

## 🏗️ Arquitetura

```
br.com.inspec360/
├── domain/
│   ├── model/              # Entidades puras (User, Structure, Inspection, Anomaly, Reference)
│   ├── repository/         # Interfaces de repositório
│   └── usecase/            # Casos de uso (LoginUseCase, CreateInspectionUseCase, etc)
├── data/
│   ├── local/
│   │   ├── database/       # Room Database
│   │   ├── dao/            # Data Access Objects
│   │   └── entity/         # Room Entities
│   ├── mapper/             # Conversão Entity ↔ Domain
│   └── repository/         # Implementações de repositório
├── presentation/
│   ├── ui/
│   │   ├── screen/         # Telas Compose (Login, Dashboard, Structures, etc)
│   │   ├── components/     # Componentes reutilizáveis
│   │   └── theme/          # Tema Material + cores
│   ├── viewmodel/          # ViewModels com StateFlow
│   └── navigation/         # Navegação Jetpack Compose
├── di/                     # Módulos Hilt (Injeção de Dependência)
├── util/                   # Utilitários (Security, DataSeed)
├── Inspec360App.kt         # Application Class (Hilt)
└── MainActivity.kt         # Activity principal
```

## 🗄️ Banco de Dados

### Tabela: USERS
```
id (PK)
username
passwordHash
role (ADMIN, INSPETOR)
```

### Tabela: STRUCTURES
```
id (PK)
numero
tipo
classe
progressiva
travessia
critica
coordenadaX
coordenadaY
```

### Tabela: INSPECTIONS
```
id (PK)
estruturaId (FK)
inspetorId (FK)
dataHora
status (EM_ANDAMENTO, FINALIZADA)
sincronizada
```

### Tabela: ANOMALIES
```
id (PK)
inspecaoId (FK)
componente
tipoAnomalia
fase (FASE_A, FASE_B, FASE_C)
emenda
severidade (LEVE, MODERADA, GRAVE, CRITICA)
riscoSeguranca (BAIXO, MEDIO, ALTO, CRITICO)
riscoOperacional (BAIXO, MEDIO, ALTO, CRITICO)
observacao
fotoPath
latitude
longitude
```

### Tabela: REFERENCES
```
id (PK)
categoria (SEVERIDADE, FASE, TIPO_ANOMALIA, COMPONENTE)
valor
```

## 🎨 Design System

### Cores
- **Primária**: Verde Escuro (#2D5A3D)
- **Ação**: Dourado (#C4A747)
- **Leve**: Verde (#4CAF50)
- **Moderada**: Amarelo (#FFC107)
- **Grave**: Laranja (#FF9800)
- **Crítica**: Vermelho (#F44336)

### Tipografia
- Sem serifa (SansSerif)
- Mínimo 16sp
- Alto contraste
- Material 3

## 📲 Telas

1. **Login** - Autenticação local
2. **Dashboard** - Menu principal (Nova Inspeção, Estruturas, Histórico, Configurações)
3. **Estruturas** - Lista com busca
4. **Inspeção** - Fluxo sequencial
5. **Anomalias** - Registro com validação de foto
6. **Histórico** - Inspeções por inspetor
7. **Configurações** - Exportar, limpar, versão

## 🚀 Compilar

### Pré-requisitos
- Android Studio Giraffe+
- JDK 17+
- Android SDK 34 (compileSdk)
- minSdk 26

### Build

```bash
# Debug
./gradlew assembleDebug

# Release
./gradlew assembleRelease

# APK
./gradlew bundleRelease
```

## 🔐 Dados de Teste

Usuários pré-carregados:
- `inspetor1` / `senha123` (INSPETOR)
- `inspetor2` / `senha123` (INSPETOR)
- `admin` / `admin123` (ADMIN)

Estruturas de exemplo carregadas automaticamente.

## 📋 Use Cases Implementados

- **LoginUseCase** - Autenticação local
- **GetAllStructuresUseCase** - Listar estruturas
- **SearchStructuresUseCase** - Buscar por número/tipo
- **CreateInspectionUseCase** - Iniciar inspeção
- **FinalizeInspectionUseCase** - Finalizar inspeção
- **ValidateAnomalyUseCase** - Validar anomalia (foto obrigatória para GRAVE/CRÍTICA)
- **SaveAnomalyUseCase** - Salvar com validação
- **GetInspectionHistoryUseCase** - Histórico por inspetor

## ⚙️ ViewModels

- **LoginViewModel** - Gerencia estado de login
- **StructuresViewModel** - Lista e busca de estruturas
- **InspectionViewModel** - Ciclo de vida da inspeção
- **AnomalyViewModel** - Registro e validação de anomalias

## 🔄 Fluxo de Dados

```
UI (Compose) 
    ↓
ViewModel (StateFlow)
    ↓
UseCase
    ↓
Repository (Interface)
    ↓
RepositoryImpl
    ↓
DAO
    ↓
Room Database
```

## 🛠️ Utilitários

- **SecurityUtil** - Hash de senhas (SHA-256)
- **DataSeed** - Dados iniciais (usuários, estruturas, referências)

## 📱 Responsive Design

- Otimizado para **landscape**
- Tablet 10-11"
- Touch-friendly (botões ≥ 56dp)
- Alto contraste

## 🔄 Sincronização (Futuro)

Campo `sincronizada` nas tabelas INSPECTIONS e ANOMALIES para sincronização futura com servidor.

## 📝 Notas de Desenvolvimento

1. **Segurança**: Em produção, usar BCrypt/Argon2 ao invés de SHA-256
2. **GPS**: Integrar `play-services-location` para captura real de coordenadas
3. **Câmera**: Integrar Jetpack Camera X para fotos
4. **Export**: Implementar export JSON/CSV via File API
5. **Validações**: Adicionar validações mais robustas no domain
6. **Testes**: Adicionar testes unitários e de instrumentação

## 📄 Licença

Confidencial - INSPEC360

---

**Versão**: 1.0.0  
**Criado**: 2026
