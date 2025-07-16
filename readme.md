# WebSocket Full Demo: Spring Boot Backend + Static Frontend

This repository demonstrates a full example of a WebSocket server built with Spring Boot (2.7.18) and a simple HTML client frontend.

---

## 🔧 Structure

```
root/
├── clientHtml/                 # Frontend HTML page + server script
│   ├── frontend.html           # A WebSocket test page
│   └── server.bat              # Launches simple static server on port 8081
│
├── webserver/                  # Spring Boot backend
│   ├── pom.xml
│   └── src/main/java/example/  # WebSocket logic
│
├── LICENSE
└── readme.md
```

---

## 🚀 How to Run

### Backend (Spring Boot Server)

```bash
cd webserver
mvn clean package
java -jar target/artifact-1.0-SNAPSHOT.jar
```

Access WebSocket endpoint:

```
ws://localhost:8080/ws?uuid=your-unique-id
```

### Frontend (Static HTML Page)

```bash
cd clientHtml
python -m http.server 8081
```

Open your browser:

```
http://localhost:8081/index.html
```

---

## ✨ Features

* UUID-based session registration
* WebSocket real-time messaging
* RESTful API for control:

  * `GET /ws-api/list`: list sessions
  * `POST /ws-api/send`: send messages
  * `POST /ws-api/close`: close sessions

---

## 🖥 Frontend Demo Page Preview

Basic test page lets you:

* Enter UUID
* Connect/disconnect WebSocket
* Send messages via socket or API
* View responses in real-time

Frontend sends WebSocket messages to:

```
ws://localhost:8080/ws?uuid=test123
```

And uses REST API endpoints like:

```
POST http://localhost:8080/ws-api/send
```

---

## 📝 中文說明

這是一個包含前後端的 WebSocket 範例專案：

* 後端使用 Spring Boot 建立 WebSocket Server
* 前端提供 HTML 測試頁，可直接輸入 UUID 測試即時通訊

### ✅ 使用方式

1. 啟動後端：

```bash
cd webserver
mvn clean package
java -jar target/artifact-1.0-SNAPSHOT.jar
```

2. 啟動前端：

```bash
cd clientHtml
python -m http.server 8081
```

3. 瀏覽頁面：

```
http://localhost:8081/index.html
```

可透過畫面連線、送出訊息或呼叫 REST API 模擬行為。

---

## 📄 License

MIT

---

這份專案為開發者導向的完整示範，適合作為 WebSocket + REST API 實作範本。
如需擴充也可整合 broadcast、多用戶驗證等功能。
