# Spring Boot WebSocket Server Example

A minimal WebSocket server built with Spring Boot. It supports:

* Client registration with UUID
* Real-time messaging via WebSocket
* REST API to list, send, and close sessions

---

## Features

* WebSocket connection established via `/ws?uuid=xxx`
* In-memory session management (`ConcurrentHashMap`)
* RESTful control interface:

  * `GET /ws-api/list` – List all current sessions
  * `POST /ws-api/send` – Send messages to specific UUIDs
  * `POST /ws-api/close?uuid=xxx` – Close a session by UUID

---

## How to Run

```bash
# Prerequisite: JDK 11+ and Maven
mvn clean package
java -jar ./target/artifact-1.0-SNAPSHOT.jar
```

Then visit:

```http
ws://localhost:8080/ws?uuid=your-unique-id
```

Or use JavaScript WebSocket client:

```javascript
const socket = new WebSocket("ws://localhost:8080/ws?uuid=test123");
socket.onmessage = (event) => console.log("Received:", event.data);
socket.send("Hello from client");
```

---

## 中文說明（Chinese Readme）

這是一個使用 Spring Boot 製作的簡易 WebSocket Server，支援：

* 用戶以 `uuid` 進行連線識別
* 支援透過 REST API 傳送訊息、關閉連線、列出當前連線

---

### 主要功能

* WebSocket 端點：`/ws?uuid=xxx`
* REST API：

  * `GET /ws-api/list`：列出目前所有連線狀態
  * `POST /ws-api/send`：根據 UUID 傳送訊息
  * `POST /ws-api/close?uuid=xxx`：關閉指定連線

---

### 執行方式

```bash
# 確保已安裝 JDK 11+ 和 Maven
mvn clean package
java -jar ./target/artifact-1.0-SNAPSHOT.jar
```

啟動後可透過瀏覽器或 JS 建立連線：

```javascript
const socket = new WebSocket("ws://localhost:8080/ws?uuid=test123");
socket.onmessage = (event) => console.log("接收到訊息:", event.data);
socket.send("來自 client 的訊息");
```

---

## License

MIT

---

這份 README 為開發者導向，簡潔說明架構與如何啟動使用，若你打算進一步整理為教學型 repo，可再補上目錄架構說明與類別說明章節。
