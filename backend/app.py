from datetime import datetime

from flask import Flask, jsonify, request
from flask_cors import CORS

app = Flask(__name__)
CORS(app)

HARBOURS = [
    {"id": 1, "name": "基隆港", "city": "基隆市", "latitude": 25.1524, "longitude": 121.7392},
    {"id": 2, "name": "淡水漁人碼頭", "city": "新北市", "latitude": 25.1833, "longitude": 121.4104},
    {"id": 3, "name": "竹圍漁港", "city": "桃園市", "latitude": 25.1167, "longitude": 121.2435},
    {"id": 4, "name": "梧棲漁港", "city": "臺中市", "latitude": 24.2878, "longitude": 120.5154},
    {"id": 5, "name": "安平漁港", "city": "臺南市", "latitude": 22.9974, "longitude": 120.1587},
    {"id": 6, "name": "旗津漁港", "city": "高雄市", "latitude": 22.6099, "longitude": 120.2688},
    {"id": 7, "name": "東港漁港", "city": "屏東縣", "latitude": 22.4672, "longitude": 120.4387},
    {"id": 8, "name": "蘇澳港", "city": "宜蘭縣", "latitude": 24.5959, "longitude": 121.8708},
]

ALERT_LOGS = []


@app.get("/health")
def health():
    return jsonify({"status": "ok", "service": "Fishing Harbor Weather Alert API"})


@app.get("/harbors")
def harbors():
    return jsonify({"items": HARBOURS})


@app.post("/alert-log")
def create_alert_log():
    data = request.get_json(silent=True) or {}
    log = {
        "id": len(ALERT_LOGS) + 1,
        "harborName": data.get("harborName", "未知漁港"),
        "level": data.get("level", "UNKNOWN"),
        "message": data.get("message", ""),
        "createdAt": datetime.utcnow().isoformat() + "Z",
    }
    ALERT_LOGS.append(log)
    return jsonify(log), 201


@app.get("/alert-log")
def list_alert_logs():
    return jsonify({"items": ALERT_LOGS})


if __name__ == "__main__":
    app.run(host="0.0.0.0", port=5000, debug=True)
