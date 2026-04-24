# Architecture Decision Record - INSPEC360 Android

## ADR-001: Clean Architecture + MVVM

**Decisão:** Usar Clean Architecture com MVVM para separação de responsabilidades.

**Rationale:**
- Domain layer isolada de implementação (testável)
- Data layer centralizada
- Presentation reativamente gerenciada com StateFlow
- Facilita manutenção e testes

**Consequence:**
- Mais camadas, mas código mais limpo
- Reutilização de use cases
- ViewModels agnósticos de UI

---

## ADR-002: Room para Offline-First

**Decisão:** SQLite via Room ao invés de Realm ou DataStore.

**Rationale:**
- Room é padrão Jetpack
- Melhor performance em datasets maiores
- Tipagem em Kotlin
- Migrations automáticas
- Queries compiladas em tempo de build

**Consequence:**
- Versionar schema do banco
- Usar DAOs explicitamente
- Mais boilerplate, mas safer

---

## ADR-003: Jetpack Compose ao invés de XML

**Decisão:** Compose para todas as UI.

**Rationale:**
- Mais declarativa
- Hot reload (preview)
- Menos boilerplate
- Temas e componentes reutilizáveis
- Melhor composição

**Consequence:**
- minSdk 26 (aceito para tablets)
- Composites e recomposição a gerenciar
- Navegação via NavController

---

## ADR-004: Hilt para DI

**Decisão:** Dagger Hilt ao invés de manual ou koin.

**Rationale:**
- Integração Jetpack oficial
- Type-safe
- Generated code eficiente
- Scope lifecycle aware

**Consequence:**
- @HiltViewModel required
- @AndroidEntryPoint no Activity
- @Module e @Provides para configuração

---

## ADR-005: Não usar coroutines diretamente

**Decisão:** LaunchedEffect e collectAsState no Compose.

**Rationale:**
- Lifecycle-aware automático
- Cancellation automática com composable
- Mais seguro que manual

**Consequence:**
- Menos controle fino
- Integração com compose obrigatória

---

## ADR-006: Severidade GRAVE/CRÍTICA exige foto

**Decisão:** Validação em domain layer (ValidateAnomalyUseCase).

**Rationale:**
- Regra de negócio crítica
- Executada antes de salvar
- Independente da UI

**Consequence:**
- Camera X obrigatório futuramente
- UX clara para captura

---

## ADR-007: GPS obrigatório

**Decisão:** Latitude/longitude não podem ser 0.0.

**Rationale:**
- Rastreamento de localização geográfica
- Conformidade com regulamentações
- Auditoria futura

**Consequence:**
- Play Services Location obrigatório
- Permissão ACCESS_FINE_LOCATION
- Tratamento de GPS disabled

---

## ADR-008: Dados seeding no launch

**Decisão:** Usuários, estruturas e referências carregados automaticamente.

**Rationale:**
- Dev rápido
- Demo sem API
- Dados reproduzíveis

**Consequence:**
- Remover em produção (usar migration)
- Ou fazer condicional por BuildConfig

---

## ADR-009: Cores semânticas por severidade

**Decisão:** Leve=Verde, Moderada=Amarelo, Grave=Laranja, Crítica=Vermelho.

**Rationale:**
- Clareza visual imediata
- Inspetor cansado entende em <1s
- Acessibilidade via cor + texto
- Padrão industrial

**Consequence:**
- Não mudar cores por aesthetics
- Testar com daltonismo

---

## ADR-010: Landscape obrigatório

**Decisão:** Activity com screenOrientation="landscape" travado.

**Rationale:**
- Tablet uso de campo
- Mais espaço horizontal
- Teclado virtual menor

**Consequence:**
- UI não suporta portrait
- Testar apenas landscape

---

## ADR-011: Validações duplas (Domain + Presentation)

**Decisão:** Validar no domain (use case) E no UI (ViewModel).

**Rationale:**
- Domain: garantir integridade
- UI: feedback rápido ao usuário
- Não perder dados

**Consequence:**
- Alguns if/checks duplicados
- Mas segurança garantida

---

## ADR-012: Sincronização deferred (futuro)

**Decisão:** Campo `sincronizada` boolean, não implementado agora.

**Rationale:**
- API não existe ainda
- Feature flag simples
- WorkManager futuramente

**Consequence:**
- Flag sempre false
- Prepare schema para sincronização

---

## Decisões de UX

### Botões

- **Primário** (dourado): Ação principal, sempre o CTA
- **Secundário** (cinza): Ações secundárias
- **Perigo** (vermelho): Deletar/logout
- Altura mínima: 56dp
- Largo: fillMaxWidth

### Espaçamento

- Card padding: 16dp
- Screen padding: 24dp
- Entre elementos: 12-16dp
- Material 3 standard

### Tipografia

- Sem serifa (SansSerif)
- Mínimo bodyMedium (16sp) no campo
- headlinesMedium para títulos (20sp)
- Alto contraste (Black on White)

---

## Checklist Pré-Produção

- [ ] Remover logging DEBUG
- [ ] Implementar BCrypt senha
- [ ] Desabilitar data seed por BuildConfig
- [ ] Integrar GPS real
- [ ] Integrar câmera real
- [ ] Testar com luvas
- [ ] Testar com brilho alto
- [ ] Testar syncronização API
- [ ] Testes unitários (domain)
- [ ] Testes instrumentação (UI)
- [ ] ProGuard/R8 em release
- [ ] Assinar APK
- [ ] Versionar no manifest

---

**Status**: APROVADO  
**Data**: 2026  
**Autor**: Arquiteto Senior Android
