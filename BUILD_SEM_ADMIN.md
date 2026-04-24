# 🚀 Como Gerar APK sem Admin - GitHub Actions

Sem acesso admin na máquina? Sem problema! Vou usar **GitHub Actions** para compilar na nuvem.

## ✅ Pré-requisitos

- Conta GitHub (gratuita): https://github.com/signup
- Git instalado (geralmente já vem)

## 📋 Passo a Passo

### 1️⃣ **Crie um repositório no GitHub**

1. Acesse https://github.com/new
2. Nome: `INSPEC360`
3. Descrição: `Inspeção de Redes de Transmissão`
4. **Private** ou **Public** (recomendo Private)
5. Clique **Create repository**

### 2️⃣ **Configure Git localmente**

Abra **PowerShell** na pasta do projeto:

```powershell
cd "c:\INSPEC360 v1\android"

# Configure seu email e nome (uma vez)
git config --global user.email "seu.email@example.com"
git config --global user.name "Seu Nome"

# Inicie o repositório
git init
git add .
git commit -m "Initial commit: INSPEC360 Android app"
```

### 3️⃣ **Conecte ao GitHub**

Na página do repositório criado, copie o comando `git remote add origin` e cole:

```powershell
git remote add origin https://github.com/SEUUSER/INSPEC360.git
git branch -M main
git push -u origin main
```

⚠️ **Se pedir senha**: Use seu **Personal Access Token** (PAT):
1. Acesse https://github.com/settings/tokens
2. Gere novo token com permissão `repo`
3. Cole como senha

### 4️⃣ **GitHub Actions Compila Automaticamente**

Após fazer push:

1. Acesse seu repositório: `https://github.com/SEUUSER/INSPEC360`
2. Clique na aba **Actions**
3. Veja o workflow **"Build INSPEC360 APK"** rodando
4. Aguarde ~3-5 minutos até verde ✅

### 5️⃣ **Baixe o APK**

Quando o build terminar:

**Opção A - Via Artifacts:**
1. Clique no workflow completado
2. Scroll down até **Artifacts**
3. Baixe `inspec360-release-apk`
4. Arquivo: `app-release-unsigned.apk`

**Opção B - Via Releases:**
1. Clique em **Releases** no repositório
2. Baixe `app-release-unsigned.apk`

---

## 📱 Próximo Passo: Testar o APK

### **Opção 1: Appetize.io (Recomendado - Zero instalação)**

1. Acesse https://appetize.io
2. Clique **Upload**
3. Selecione `app-release-unsigned.apk`
4. Pronto! APK rodando na web

**Login teste:**
- Usuário: `inspetor1`
- Senha: `senha123`

### **Opção 2: Tablet Físico (via adb)**

Se tiver acesso a tablet com USB:

```powershell
# Instale adb (se não tiver):
choco install adb
# Ou baixe manual: https://developer.android.com/studio/command-line/adb

# Conecte tablet por USB e:
adb install -r app-release-unsigned.apk
```

---

## 🔧 Troubleshooting

### Build falhou no GitHub?
- Veja os logs em **Actions > workflow > logs**
- Erros comuns: syntax Kotlin, imports faltando
- Abra issue se precisar de ajuda

### Arquivo gradle-wrapper.jar não existe?
- GitHub Actions download automaticamente
- Não precisa adicionar ao git (ignorado por .gitignore)

### APK muito grande (>100MB)?
- Normal para primeira build
- Próximas serão menores com cache

---

## ⚡ Quick Recap

```bash
cd "c:\INSPEC360 v1\android"
git init
git add .
git commit -m "Initial commit"
git remote add origin https://github.com/SEUUSER/INSPEC360.git
git push -u origin main
# Aguarde Actions terminar → Download APK → Teste no Appetize.io
```

**Tempo total: ~15 minutos** ⏱️

---

## ❓ Dúvidas?

Se o build falhar, envie a saída do workflow para análise. Qualquer problema, avise!
