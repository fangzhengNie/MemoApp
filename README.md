一个简洁实用的 Android 备忘录应用，支持备忘录的创建、查看、编辑与删除功能，帮助你随时记录和管理重要内容。

### ✨ 功能特性
📌 创建新备忘录（包含标题、内容、时间戳）

📋 列表浏览所有备忘录，支持美观的分隔和展示

✏️ 点击进入编辑页面，修改或删除已有备忘录

🗑️ 长按备忘录项，弹出删除确认，支持删除操作

🧠 使用本地数据库 Room 进行持久化存储，数据安全可靠

📱 Material Design 风格，界面简洁现代

⚡ 异步加载数据，流畅响应用户操作

### 🛠️ 技术栈
Kotlin 编程语言

Android Jetpack 组件集（ViewModel、LiveData、LifecycleScope）

Room 持久化数据库

RecyclerView + ListAdapter 高效列表渲染

Material Design 主题与控件

Kotlin Coroutine 协程处理异步任务

### 🚀 快速开始
前置条件
Android Studio Hedgehog 及以上版本

Android SDK 33 或更高版本

Android 模拟器或真机设备

### 项目结构
```
MemoApp/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/example/memo/
│   │   │   │   ├── MemoListActivity.kt       // 备忘录列表主页面
│   │   │   │   ├── AddMemoActivity.kt        // 新增备忘录页面
│   │   │   │   ├── EditMemoActivity.kt       // 编辑备忘录页面
│   │   │   │   ├── MemoAdapter.kt             // RecyclerView 适配器
│   │   │   │   └── data/
│   │   │   │       ├── Memo.kt                 // 备忘录数据实体类
│   │   │   │       ├── MemoDao.kt              // 数据访问接口（DAO）
│   │   │   │       └── MemoDatabase.kt         // Room 数据库单例
│   │   │   └── res/
│   │   │       ├── layout/                      // XML 布局文件
│   │   │       └── values/                      // 字符串、颜色、样式资源
```
### 应用截图
登录界面
![298d9a55b47b0426054a573e8153f56e](https://github.com/user-attachments/assets/4a1ba023-3a52-43cb-a6ca-fadf7e7b5f03)
主界面
![976d1cc55fc38a2d9c19912949b36d85](https://github.com/user-attachments/assets/cfc558fc-1f75-4416-8b56-09faea3cbf80)
添加备忘录
![fe75cc72d33b22d56b5cb9bae41d56c8](https://github.com/user-attachments/assets/8bcbf4a4-05fd-461d-8ee9-3612e9e2dadc)
编辑备忘录
![2d01d8e28e7c917a9a42f3de8c176199](https://github.com/user-attachments/assets/0f63d51e-df2e-4016-8c79-046825447200)

