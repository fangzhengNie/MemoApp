# 📝 MemoApp

一个简洁实用的 Android 备忘录应用，支持备忘录的创建、查看、编辑与删除功能，帮助你随时记录重要内容。

## ✨ 功能特性

- 📌 创建新备忘录（包含标题、内容、时间戳）
- 📋 列表浏览所有备忘录
- ✏️ 点击编辑已有备忘录
- 🗑️ 长按删除备忘录
- 🧠 使用本地数据库 Room 进行持久化存储

## 📱 截图预览
登录界面
![image](https://github.com/user-attachments/assets/535357c5-a758-4884-9033-dff99b64b2b9)
主界面
![image](https://github.com/user-attachments/assets/0f4c8408-91a4-4099-8b05-8babe8af4169)
备忘录
![image](https://github.com/user-attachments/assets/deca134e-f229-4b17-a95c-eed2921ae69e)


## 🛠️ 技术栈

- Kotlin
- Android Jetpack
  - Room（本地数据库）
  - ViewModel & LifecycleScope
  - RecyclerView + ListAdapter
- Material Design
- Coroutine（异步加载）

## 🚀 快速开始

### 前置要求

- Android Studio Hedgehog 以上版本
- Android SDK 33+
- 模拟器或 Android 实体设备

### 目录结构

MemoApp/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/example/memo/
│   │   │   │   ├── MemoListActivity.kt       // 主页面
│   │   │   │   ├── AddMemoActivity.kt        // 添加备忘录
│   │   │   │   ├── EditMemoActivity.kt       // 编辑备忘录
│   │   │   │   ├── MemoAdapter.kt            // RecyclerView 适配器
│   │   │   │   └── data/
│   │   │   │       ├── Memo.kt               // 数据实体
│   │   │   │       ├── MemoDao.kt            // DAO 接口
│   │   │   │       └── MemoDatabase.kt       // Room 数据库实例
│   │   │   └── res/
│   │   │       ├── layout/                   // XML 布局文件
│   │   │       └── values/                   // 字符串、样式资源
