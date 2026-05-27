import { useMemo, useState } from "react";

const API_BASE = "http://localhost:8080";

const goodSample = {
  seriesId: 1,
  seedMoisture: 10,
  purity: 98,
  thousandGrainMass: 250,
  germination: 90,
  germinationEnergy: 92,
  fusarium: 2,
  biologicalImpurities: false,
  ageYears: 2,
  parcelId: 101,
  soilTemperature: 15,
  soilMoisture: 75,
  insectsPresent: false,
  plowed: true,
  fertilized: true,
  ph: 6.5,
  airTemperature: 20,
  storageId: 201,
};

const badSample = {
  seriesId: 2,
  seedMoisture: 16,
  purity: 93,
  thousandGrainMass: 180,
  germination: 82,
  germinationEnergy: 65,
  fusarium: 8,
  biologicalImpurities: true,
  ageYears: 7,
  parcelId: 102,
  soilTemperature: 8,
  soilMoisture: 88,
  insectsPresent: true,
  plowed: false,
  fertilized: false,
  ph: 7.8,
  airTemperature: 12,
  storageId: 202,
};

const CEP_EVENTS = {
  storageTemperature: {
    label: "Storage temperature",
    endpoint: "/api/cep/storage-temperature",
    payload: {
      timestamp: "2026-05-27T10:00:00.000+00:00",
      storageId: 201,
      temperature: 26,
    },
  },
  storageHumidity: {
    label: "Storage humidity",
    endpoint: "/api/cep/storage-humidity",
    payload: {
      timestamp: "2026-05-27T10:00:00.000+00:00",
      storageId: 201,
      humidity: 72,
    },
  },
  storagePest: {
    label: "Storage pest",
    endpoint: "/api/cep/storage-pest",
    payload: {
      timestamp: "2026-05-27T10:00:00.000+00:00",
      storageId: 201,
      pest: true,
    },
  },
  soilMoisture: {
    label: "Soil moisture",
    endpoint: "/api/cep/soil-moisture",
    payload: {
      timestamp: "2026-05-27T10:00:00.000+00:00",
      soilId: 101,
      moisture: 84,
    },
  },
  soilTemperature: {
    label: "Soil temperature",
    endpoint: "/api/cep/soil-temperature",
    payload: {
      timestamp: "2026-05-27T10:00:00.000+00:00",
      soilId: 101,
      temperature: 8,
    },
  },
};

const seedFields = [
  ["seriesId", "Series ID", 1],
  ["seedMoisture", "Moisture %", 0.1],
  ["purity", "Purity %", 0.1],
  ["thousandGrainMass", "1000 grain mass", 0.1],
  ["germination", "Germination %", 0.1],
  ["germinationEnergy", "Germination energy %", 0.1],
  ["fusarium", "Fusarium %", 0.1],
  ["ageYears", "Age years", 1],
];

const soilFields = [
  ["parcelId", "Parcel ID", 1],
  ["soilTemperature", "Soil temp C", 0.1],
  ["soilMoisture", "Soil moisture %", 0.1],
  ["ph", "pH", 0.1],
  ["airTemperature", "Air temp C", 0.1],
  ["storageId", "Storage ID", 1],
];

const boolFields = [
  ["biologicalImpurities", "Biological impurities"],
  ["insectsPresent", "Insects in soil"],
  ["plowed", "Plowed"],
  ["fertilized", "Fertilized"],
];

const postJson = async (apiBase, path, body) => {
  const response = await fetch(`${apiBase.replace(/\/$/, "")}${path}`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(body),
  });
  const text = await response.text();
  const data = text ? JSON.parse(text) : null;
  if (!response.ok) throw new Error(text || `HTTP ${response.status}`);
  return data;
};

const StatusDot = ({ active, color = "#ace600", pulse = false }) => (
  <span style={{
    display: "inline-block",
    width: 8,
    height: 8,
    borderRadius: "50%",
    background: active ? color : "#ace600",
    color,
    boxShadow: active ? `0 0 6px ${color}, 0 0 12px ${color}40` : "none",
    animation: active && pulse ? "pulse-dot 1.4s ease-in-out infinite" : "none",
    flexShrink: 0,
  }} />
);

const Card = ({ children, style = {} }) => (
  <div style={{
    background: "#7FB069",
    border: "1px solid rgba(255,255,255,0.06)",
    borderRadius: 14,
    padding: "20px 22px",
    backdropFilter: "blur(16px)",
    ...style,
  }}>
    {children}
  </div>
);

const SectionLabel = ({ children }) => (
  <div style={{
    fontSize: 25,
    letterSpacing: "0.2em",
    textTransform: "uppercase",
    color: "#2b2b2b",
    marginBottom: 30,
    marginTop: 30,
    display: "flex",
    alignItems: "center",
    gap: 8,
  }}>
    <span style={{ flex: 1, height: 1, background: "rgba(255,255,255,0.05)" }} />
    {children}
    <span style={{ flex: 1, height: 1, background: "rgba(255,255,255,0.05)" }} />
  </div>
);

const inputStyle = {
  width: "100%",
  background: "#F4F1E8",
  border: "1px solid rgba(255,255,255,0.07)",
  borderRadius: 8,
  padding: "9px 12px",
  color: "#8a5a2b",
  outline: "none",
  fontSize: 14,
};

const buttonStyle = (variant = "default") => ({
  background: variant === "primary" ? "#8a5a2b" : "rgba(47,93,58,0.5)",
  border: variant === "primary" ? "1px solid rgba(34,197,94,0.45)" : "1px solid rgba(255,255,255,0.07)",
  borderRadius: 8,
  padding: "9px 14px",
  color: variant === "primary" ? "#fff" : "#F4F1E8",
  cursor: "pointer",
  fontSize: 12,
  letterSpacing: "0.1em",
  textTransform: "uppercase",
});

const Field = ({ label, name, value, step = 0.1, onChange }) => (
  <label style={{ display: "grid", gap: 6 }}>
    <span style={{ fontSize: 13, color: "#2b2b2b", letterSpacing: "0.12em", textTransform: "uppercase" }}>
      {label}
    </span>
    <input
      style={inputStyle}
      type="number"
      step={step}
      value={value}
      onChange={(event) => onChange(name, Number(event.target.value))}
    />
  </label>
);

const Toggle = ({ label, checked, onChange }) => (
  <label style={{
    display: "flex",
    alignItems: "center",
    gap: 8,
    padding: "8px 10px",
    background: "rgba(138,90,43,0.5)",
    border: "1px solid rgba(255,255,255,0.05)",
    borderRadius: 8,
    color: checked ? "#2b2b2b" : "#F4F1E8",
    fontSize: 12,
    letterSpacing: "0.08em",
    textTransform: "uppercase",
  }}>
    <input type="checkbox" checked={checked} onChange={(event) => onChange(event.target.checked)} />
    {label}
  </label>
);

const DecisionBadge = ({ value }) => {
  const color = value === "RECOMMENDED" ? "#22c55e" : value === "DELAY_SOWING" ? "#f59e0b" : value ? "#ef4444" : "#334155";
  return (
    <div style={{
      display: "inline-flex",
      alignItems: "center",
      gap: 8,
      padding: "7px 13px",
      background: `${color}14`,
      border: `1px solid ${color}44`,
      borderRadius: 8,
      color,
      fontSize: 11,
      letterSpacing: "0.12em",
    }}>
      <StatusDot active={Boolean(value)} color={color} pulse={value === "NOT_RECOMMENDED"} />
      {value || "NO DECISION"}
    </div>
  );
};

export default function SeedWiseDashboard() {
  const [apiBase, setApiBase] = useState(API_BASE);
  const [request, setRequest] = useState(goodSample);
  const [decision, setDecision] = useState(null);
  const [explanation, setExplanation] = useState([]);
  const [warnings, setWarnings] = useState([]);
  const [activeTab, setActiveTab] = useState("sowing");
  const [cepType, setCepType] = useState("storageTemperature");
  const [cepPayloads, setCepPayloads] = useState(
    Object.fromEntries(Object.entries(CEP_EVENTS).map(([key, config]) => [key, structuredClone(config.payload)]))
  );
  const [status, setStatus] = useState("Ready");

  const selectedCep = CEP_EVENTS[cepType];
  const requestJson = useMemo(() => JSON.stringify(request, null, 2), [request]);

  const setField = (name, value) => setRequest((current) => ({ ...current, [name]: value }));

  const evaluate = async () => {
    try {
      setStatus("Evaluating sowing request...");
      setDecision(await postJson(apiBase, "/api/sowing/evaluate", request));
      setStatus("Decision received");
    } catch (error) {
      setStatus(error.message);
    }
  };

  const explain = async () => {
    try {
      setStatus("Running backward explanation...");
      setExplanation(await postJson(apiBase, "/api/sowing/explain", request));
      setStatus("Explanation received");
    } catch (error) {
      setStatus(error.message);
    }
  };

  const sendCep = async () => {
    try {
      setStatus(`Sending ${selectedCep.label} event...`);
      setWarnings(await postJson(apiBase, selectedCep.endpoint, cepPayloads[cepType]));
      setStatus("CEP event processed");
    } catch (error) {
      setStatus(error.message);
    }
  };

  const tabs = [
    { id: "sowing", label: "Sowing" },
    { id: "cep", label: "CEP" },
    { id: "payloads", label: "Payloads" },
  ];

  return (
    <div style={{
      minHeight: "100vh",
      background: "#2F5D3A",
      color: "#e2e8f0",
      position: "relative",
      overflow: "hidden",
    }}>
      <div style={{
        position: "fixed",
        inset: 0,
        pointerEvents: "none",
        backgroundImage: `
          linear-gradient(rgba(255,255,255,0.018) 1px, transparent 1px),
          linear-gradient(90deg, rgba(255,255,255,0.018) 1px, transparent 1px)
        `,
        backgroundSize: "48px 48px",
      }} />

      <div style={{ position: "relative", maxWidth: 1440, margin: "0 auto", padding: "0 28px" }}>
        <header style={{
          display: "flex",
          alignItems: "center",
          justifyContent: "space-between",
          gap: 18,
          padding: "22px 0",
          borderBottom: "1px solid rgba(255,255,255,0.05)",
          marginBottom: 24,
          flexWrap: "wrap",
        }}>
          <div>
            <h1 style={{ fontSize: 50, fontWeight: "normal", letterSpacing: "0.08em", color: "#F4F1E8" }}>
              SEEDWISE <span style={{ color: "#e2e8f0" }}></span>
            </h1>
          </div>

          <div style={{ display: "flex", alignItems: "center", gap: 10, flexWrap: "wrap", justifyContent: "flex-end" }}>
            <div style={{
              display: "flex",
              alignItems: "center",
              gap: 7,
              padding: "6px 14px",
              background: "#F4F1E8",
              border: "1px solid rgba(255,255,255,0.05)",
              borderRadius: 7,
              color: "#2b2b2b",
              fontSize: 11,
              letterSpacing: "0.12em",
            }}>
              <StatusDot active color="#22c55e" />
              {status}
            </div>
            <input
              style={{ ...inputStyle, width: 260 }}
              value={apiBase}
              onChange={(event) => setApiBase(event.target.value)}
              aria-label="API base"
            />
          </div>
        </header>

        <nav style={{ display: "flex", gap: 3, marginBottom: 24, flexWrap: "wrap" }}>
          {tabs.map((tab) => (
            <button
              key={tab.id}
              onClick={() => setActiveTab(tab.id)}
              style={{
                ...buttonStyle(),
                background: activeTab === tab.id ? "rgba(34,197,94,0.1)" : "transparent",
                border: activeTab === tab.id ? "1px solid rgba(34,197,94,0.3)" : "1px solid transparent",
                color: activeTab === tab.id ? "#F4F1E8" : "#2b2b2b",
              }}
            >
              {tab.label}
            </button>
          ))}
        </nav>

        {activeTab === "sowing" && (
          <div style={{ display: "grid", gridTemplateColumns: "minmax(0, 1.3fr) minmax(340px, 0.7fr)", gap: 16 }}>
            <Card>
              <SectionLabel>Sowing input</SectionLabel>
              <div style={{ display: "grid", gridTemplateColumns: "repeat(4, minmax(120px, 1fr))", gap: 12 }}>
                {seedFields.map(([name, label, step]) => (
                  <Field key={name} name={name} label={label} step={step} value={request[name]} onChange={setField} />
                ))}
              </div>
              <SectionLabel>Soil and references</SectionLabel>
              <div style={{ display: "grid", gridTemplateColumns: "repeat(3, minmax(120px, 1fr))", gap: 12 }}>
                {soilFields.map(([name, label, step]) => (
                  <Field key={name} name={name} label={label} step={step} value={request[name]} onChange={setField} />
                ))}
              </div>
              <div style={{ display: "flex", gap: 8, flexWrap: "wrap", marginTop: 14 }}>
                {boolFields.map(([name, label]) => (
                  <Toggle key={name} label={label} checked={request[name]} onChange={(value) => setField(name, value)} />
                ))}
              </div>
              <div style={{ display: "flex", gap: 8, flexWrap: "wrap", marginTop: 18 }}>
                <button style={buttonStyle()} onClick={() => setRequest(goodSample)}>Good sample</button>
                <button style={buttonStyle()} onClick={() => setRequest(badSample)}>Bad sample</button>
                <button style={buttonStyle("primary")} onClick={evaluate}>Evaluate</button>
                <button style={buttonStyle("primary")} onClick={explain}>Explain</button>
              </div>
            </Card>

            <div style={{ display: "grid", gap: 16 }}>
              <Card>
                <SectionLabel>Forward decision</SectionLabel>
                <DecisionBadge value={decision?.canSow} />
                <div style={{ marginTop: 14, color: "#2b2b2b", fontSize: 12, lineHeight: 1.6 }}>
                  {decision?.explanation || "Run evaluate to get the sowing decision."}
                </div>
                <div style={{ display: "flex", gap: 8, marginTop: 14, flexWrap: "wrap" }}>
                  <span style={buttonStyle()}>Parcel {decision?.parcelId ?? "-"}</span>
                  <span style={buttonStyle()}>Series {decision?.seedSeriesId ?? "-"}</span>
                </div>
              </Card>

              <Card>
                <SectionLabel>Backward explanation</SectionLabel>
                {explanation.length === 0 ? (
                  <div style={{ color: "#2b2b2b", fontSize: 12 }}>Run explain to see failed requirements.</div>
                ) : (
                  <div style={{ display: "grid", gap: 8 }}>
                    {explanation.map((item) => (
                      <div key={item} style={{
                        padding: "10px 12px",
                        background: "rgba(239,68,68,0.06)",
                        border: "1px solid rgba(239,68,68,0.18)",
                        borderRadius: 8,
                        color: "#fca5a5",
                        fontSize: 12,
                        lineHeight: 1.5,
                      }}>
                        {item}
                      </div>
                    ))}
                  </div>
                )}
              </Card>
            </div>
          </div>
        )}

        {activeTab === "cep" && (
          <div style={{ display: "grid", gridTemplateColumns: "430px minmax(0, 1fr)", gap: 16 }}>
            <Card>
              <SectionLabel>CEP event sender</SectionLabel>
              <div style={{ display: "flex", gap: 6, flexWrap: "wrap", marginBottom: 16 }}>
                {Object.entries(CEP_EVENTS).map(([key, config]) => (
                  <button
                    key={key}
                    onClick={() => setCepType(key)}
                    style={{
                      ...buttonStyle(),
                      color: key === cepType ? "#F4F1E8" : "#2b2b2b",
                      borderColor: key === cepType ? "rgba(138,90,43,1)" : "rgba(255,255,255,0.05)",
                      background: key === cepType ? "rgba(138,90,43,0.9)" : "rgba(138,90,43,0.4)",
                    }}
                  >
                    {config.label}
                  </button>
                ))}
              </div>

              <div style={{ display: "grid", gap: 10 }}>
                {Object.entries(cepPayloads[cepType]).map(([field, value]) => (
                  <label key={field} style={{ display: "grid", gap: 6 }}>
                    <span style={{ fontSize: 9, color: "#2b2b2b", letterSpacing: "0.12em", textTransform: "uppercase" }}>
                      {field}
                    </span>
                    <input
                      style={inputStyle}
                      type={field === "timestamp" ? "text" : "number"}
                      value={value}
                      onChange={(event) => {
                        const next = field === "timestamp" ? event.target.value : Number(event.target.value);
                        setCepPayloads((current) => ({
                          ...current,
                          [cepType]: { ...current[cepType], [field]: next },
                        }));
                      }}
                    />
                  </label>
                ))}
              </div>

              <div style={{ display: "flex", gap: 8, marginTop: 16 }}>
                <button
                  style={buttonStyle()}
                  onClick={() => setCepPayloads((current) => ({
                    ...current,
                    [cepType]: structuredClone(selectedCep.payload),
                  }))}
                >
                  Reset
                </button>
                <button style={buttonStyle("primary")} onClick={sendCep}>Send event</button>
              </div>
            </Card>

            <Card>
              <SectionLabel>Latest warnings</SectionLabel>
              <pre style={{
                minHeight: 420,
                padding: 14,
                borderRadius: 10,
                background: "#F4F1E8",
                border: "1px solid rgba(255,255,255,0.06)",
                color: "#2b2b2b",
                overflow: "auto",
                fontSize: 14,
                lineHeight: 1.5,
              }}>
                {JSON.stringify(warnings, null, 2)}
              </pre>
            </Card>
          </div>
        )}

        {activeTab === "payloads" && (
          <div style={{ display: "grid", gridTemplateColumns: "1fr 1fr", gap: 16 }}>
            <Card>
              <SectionLabel>Sowing request JSON</SectionLabel>
              <pre style={{
                minHeight: 520,
                padding: 14,
                borderRadius: 10,
                background: "#F4F1E8",
                border: "1px solid rgba(255,255,255,0.06)",
                color: "#2b2b2b",
                overflow: "auto",
                fontSize: 14,
                lineHeight: 1.5,
              }}>
                {requestJson}
              </pre>
            </Card>
            <Card>
              <SectionLabel>Current CEP event JSON</SectionLabel>
              <pre style={{
                minHeight: 520,
                padding: 14,
                borderRadius: 10,
                background: "#F4F1E8",
                border: "1px solid rgba(255,255,255,0.06)",
                color: "#2b2b2b",
                overflow: "auto",
                fontSize: 14,
                lineHeight: 1.5,
              }}>
                {JSON.stringify(cepPayloads[cepType], null, 2)}
              </pre>
            </Card>
          </div>
        )}

        <footer style={{
          marginTop: 40,
          paddingTop: 20,
          paddingBottom: 28,
          borderTop: "1px solid rgba(255,255,255,0.03)",
          display: "flex",
          justifyContent: "space-between",
          alignItems: "center",
          fontSize: 9,
          color: "#F4F1E8",
          letterSpacing: "0.12em",
          flexWrap: "wrap",
          gap: 10,
        }}>
          <span>SEEDWISE SBNZ SYSTEM</span>
          <span>SPRING BOOT · DROOLS · CEP · BACKWARD CHAINING</span>
        </footer>
      </div>
    </div>
  );
}
