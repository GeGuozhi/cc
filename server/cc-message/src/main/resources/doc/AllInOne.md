# cc-message


## 消息推送服务主要的 http 接口
### 检测服务是否连接正常
**URL:** http://127.0.0.1:8080/message/ping

**Type:** GET


**Content-Type:** application/x-www-form-urlencoded;charset=utf-8

**Description:** 检测服务是否连接正常

**Request-example:**
```
curl -X GET  -i http://127.0.0.1:8080/message/ping
```
**Response-fields:**

Field | Type|Description|Since
---|---|---|---
No field|string|The api directly returns the string type value.|-

**Response-example:**
```
ynj778
```


