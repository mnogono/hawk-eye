// Generates ../../images/route-map.svg: the release route from Yekaterinburg to Houston.
// Map data: world-atlas (Natural Earth, public domain). PNG is rendered by `npm run build`.
import { readFileSync, writeFileSync, mkdirSync } from "node:fs";
import { geoNaturalEarth1, geoPath, geoInterpolate } from "d3-geo";
import { feature, mesh } from "topojson-client";

const WIDTH = 1600;
const HEIGHT = 700;
const OUT_DIR = new URL("../../images/", import.meta.url);

// label: dx/dy offset from the marker, anchor of the text
const STOPS = [
  { version: "0.1", name: "Yekaterinburg", lon: 60.6057, lat: 56.8389, dx: 0, dy: 34, anchor: "middle" },
  { version: "0.2", name: "Moscow", lon: 37.6173, lat: 55.7558, dx: 0, dy: -20, anchor: "middle" },
  { version: "0.3", name: "Berlin", lon: 13.405, lat: 52.52, dx: 0, dy: -20, anchor: "middle" },
  { version: "0.4", name: "Lisbon", lon: -9.1393, lat: 38.7223, dx: -12, dy: 36, anchor: "end" },
  { version: "0.5", name: "New York", lon: -74.006, lat: 40.7128, dx: 12, dy: 36, anchor: "start" },
  { version: "1.0", name: "Houston", lon: -95.3698, lat: 29.7604, dx: 0, dy: 42, anchor: "middle" },
];
const OCEAN_LEG = 3; // leg from STOPS[3] (Lisbon) to STOPS[4] (New York)

const COLORS = {
  ocean: "#e8f1f8",
  land: "#f3efe6",
  border: "#d3cbbb",
  route: "#d9480f",
  text: "#212529",
  halo: "#ffffff",
};

const world = JSON.parse(readFileSync(new URL("./node_modules/world-atlas/countries-50m.json", import.meta.url)));
const land = feature(world, world.objects.land);
const borders = mesh(world, world.objects.countries, (a, b) => a !== b);

// Fit the projection to the area around the route
const area = { type: "MultiPoint", coordinates: [] };
for (let lon = -114; lon <= 74; lon += 4) {
  for (const lat of [24, 64]) area.coordinates.push([lon, lat]);
}
const projection = geoNaturalEarth1().rotate([17, 0]).fitExtent([[40, 30], [WIDTH - 40, HEIGHT - 30]], area);
const path = geoPath(projection);

function leg(a, b) {
  const interpolate = geoInterpolate([a.lon, a.lat], [b.lon, b.lat]);
  const coordinates = Array.from({ length: 65 }, (_, i) => interpolate(i / 64));
  return path({ type: "LineString", coordinates });
}

function label(stop) {
  const [x, y] = projection([stop.lon, stop.lat]);
  const attrs = `x="${(x + stop.dx).toFixed(1)}" y="${(y + stop.dy).toFixed(1)}" text-anchor="${stop.anchor}"`;
  const text = `<tspan font-weight="700">${stop.version}</tspan> ${stop.name}`;
  return `<text ${attrs} stroke="${COLORS.halo}" stroke-width="6" stroke-linejoin="round" fill="${COLORS.halo}">${text}</text>
  <text ${attrs} fill="${COLORS.text}">${text}</text>`;
}

function marker(stop, i) {
  const [x, y] = projection([stop.lon, stop.lat]);
  const isEnd = i === 0 || i === STOPS.length - 1;
  return `<circle cx="${x.toFixed(1)}" cy="${y.toFixed(1)}" r="${isEnd ? 10 : 8}" fill="${isEnd ? COLORS.route : COLORS.halo}" stroke="${COLORS.route}" stroke-width="3.5"/>`;
}

const legs = STOPS.slice(1).map((stop, i) => {
  const dash = i === OCEAN_LEG ? ` stroke-dasharray="14 10"` : "";
  return `<path d="${leg(STOPS[i], stop)}" fill="none" stroke="${COLORS.route}" stroke-width="5" stroke-linecap="round"${dash}/>`;
});

const legendY = HEIGHT - 44;
const svg = `<svg xmlns="http://www.w3.org/2000/svg" width="${WIDTH}" height="${HEIGHT}" viewBox="0 0 ${WIDTH} ${HEIGHT}" font-family="Segoe UI, Arial, sans-serif" font-size="26">
  <rect width="${WIDTH}" height="${HEIGHT}" fill="${COLORS.ocean}"/>
  <path d="${path(land)}" fill="${COLORS.land}"/>
  <path d="${path(borders)}" fill="none" stroke="${COLORS.border}" stroke-width="1"/>
  ${legs.join("\n  ")}
  ${STOPS.map(marker).join("\n  ")}
  ${STOPS.map(label).join("\n  ")}
  <g font-size="20" fill="${COLORS.text}">
    <rect x="28" y="${legendY - 34}" width="390" height="58" rx="8" fill="${COLORS.halo}" fill-opacity="0.85"/>
    <circle cx="52" cy="${legendY - 5}" r="8" fill="${COLORS.halo}" stroke="${COLORS.route}" stroke-width="3.5"/>
    <text x="70" y="${legendY + 2}">Release</text>
    <line x1="170" y1="${legendY - 5}" x2="226" y2="${legendY - 5}" stroke="${COLORS.route}" stroke-width="5" stroke-dasharray="14 10"/>
    <text x="238" y="${legendY + 2}">Ocean crossing</text>
  </g>
</svg>
`;

mkdirSync(OUT_DIR, { recursive: true });
writeFileSync(new URL("route-map.svg", OUT_DIR), svg);
console.log("images/route-map.svg written");
