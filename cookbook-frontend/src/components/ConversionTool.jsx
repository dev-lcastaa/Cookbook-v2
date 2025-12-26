import { useState } from "react";
import "./ConversionTool.css";

export default function ConversionToolPage() {
  const [type, setType] = useState("volume");
  const [value, setValue] = useState("");
  const [from, setFrom] = useState("tsp");
  const [to, setTo] = useState("tbsp");
  const [result, setResult] = useState(null);

  const conversions = {
    volume: {
      label: "Volume",
      base: "ml",
      units: { tsp: 4.92892, tbsp: 14.7868, cup: 240, floz: 29.5735, ml: 1, l: 1000 },
    },
    weight: {
      label: "Weight",
      base: "g",
      units: { g: 1, kg: 1000, oz: 28.3495, lb: 453.592 },
    },
    temperature: {
      label: "Temperature",
      units: ["C", "F"],
    },
  };

  const unitOptions =
    type === "temperature" ? conversions.temperature.units : Object.keys(conversions[type].units);

  function convert() {
    if (value === "") return;

    if (type === "temperature") {
      const v = parseFloat(value);
      const r =
        from === "C" && to === "F"
          ? (v * 9) / 5 + 32
          : from === "F" && to === "C"
          ? ((v - 32) * 5) / 9
          : v;
      setResult(r.toFixed(2));
      return;
    }

    const baseValue = value * conversions[type].units[from];
    const converted = baseValue / conversions[type].units[to];
    setResult(converted.toFixed(2));
  }

  return (
    <div className="conversion-page">
      {/* Header */}
      <header className="cookbooks-header">
        <h1>♻️ Conversion Tool</h1>
      </header>

      {/* Central card */}
      <div className="cookbooks-grid">
        <div className="conversion-card">
          <select
            value={type}
            onChange={(e) => {
              setType(e.target.value);
              const units =
                e.target.value === "temperature"
                  ? conversions.temperature.units
                  : Object.keys(conversions[e.target.value].units);
              setFrom(units[0]);
              setTo(units[1]);
              setResult(null);
            }}
          >
            {Object.entries(conversions).map(([key, val]) => (
              <option key={key} value={key}>
                {val.label}
              </option>
            ))}
          </select>

          <div className="conversion-row">
            <input
              type="number"
              placeholder="Amount"
              value={value}
              onChange={(e) => setValue(e.target.value)}
            />
            <select value={from} onChange={(e) => setFrom(e.target.value)}>
              {unitOptions.map((u) => (
                <option key={u}>{u}</option>
              ))}
            </select>
          </div>

          <div className="conversion-row">
            <span className="arrow">→</span>
            <select value={to} onChange={(e) => setTo(e.target.value)}>
              {unitOptions.map((u) => (
                <option key={u}>{u}</option>
              ))}
            </select>
          </div>

          <button onClick={convert}>Convert</button>

          {result && (
            <div className="conversion-result">
              {value} {from} = <strong>{result}</strong> {to}
            </div>
          )}
        </div>
      </div>
    </div>
  );
}
