import http from 'k6/http';
import { check, sleep, group } from 'k6';

export const options = {
  stages: [
    { duration: '30s', target: 5 },   // Start with 5 VUs
    { duration: '30s', target: 20 },  // Ramp to 20 VUs
    { duration: '30s', target: 50 },  // Ramp to 50 VUs
    { duration: '30s', target: 20 },  // Ramp down to 20 VUs
    { duration: '30s', target: 0 },   // End
  ],
  thresholds: {
    'http_req_duration': ['p(95)<500', 'p(99)<750', 'avg<400'],
    'http_req_failed': ['rate<0.01'],
    'checks': ['rate>0.99'],
    'http_reqs': ['rate>10'],
    'http_req_waiting': ['p(95)<450', 'avg<350'],
    'http_req_receiving': ['avg<50'],
  },
};

const API_URL = 'https://apigw.tbcbank.ge';
const FROM = 'USD';
const TO = 'GEL';
const AMOUNT = 100;

export default function () {
  group('API Request with Retry', () => {
    const url = `${API_URL}/api/v1/exchangeRates/getExchangeRate?Iso1=${FROM}&Iso2=${TO}`;

    const params = {
      headers: {
        'Accept': 'application/json',
        'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36',
      },
      timeout: '10s',
    };

    let res;
    let attempts = 3;
    for (let i = 0; i < attempts; i++) {
      res = http.get(url, params);
      if (res.status !== 403) break;
      console.log(`Retry ${i + 1} due to 403: ${res.body}`);
      sleep(1); 
    }

    const success = check(res, {
      'status is 200': (r) => r.status === 200,
      'status is not 403': (r) => {
        if (r.status === 403) {
          console.log(`403 Blocked: ${r.body}`);
          return false;
        }
        return true;
      },
      'response is valid JSON': (r) => {
        if (r.status !== 200) return false;
        try {
          JSON.parse(r.body);
          return true;
        } catch (e) {
          return false;
        }
      },
      'has buyRate and sellRate': (r) => {
        if (r.status !== 200) return false;
        try {
          const data = JSON.parse(r.body);
          return typeof data.buyRate === 'number' && typeof data.sellRate === 'number';
        } catch (e) {
          return false;
        }
      },
      'buyRate in realistic range (2.5 - 3.0)': (r) => {
        if (r.status !== 200) return false;
        try {
          const data = JSON.parse(r.body);
          const rate = data.buyRate;
          return rate >= 2.5 && rate <= 3.0;
        } catch (e) {
          return false;
        }
      },
      '100 USD → ~250–300 GEL (calculated)': (r) => {
        if (r.status !== 200) return false;
        try {
          const data = JSON.parse(r.body);
          const converted = AMOUNT * data.buyRate;
          return converted >= 250 && converted <= 300;
        } catch (e) {
          return false;
        }
      },
      'sellRate > buyRate (spread positive)': (r) => {
        if (r.status !== 200) return false;
        try {
          const data = JSON.parse(r.body);
          return data.sellRate > data.buyRate;
        } catch (e) {
          return false;
        }
      },
    });

    if (!success) {
      console.log(`Check failed: ${res.status} ${res.body}`);
    }
  });

  sleep(2); 
}