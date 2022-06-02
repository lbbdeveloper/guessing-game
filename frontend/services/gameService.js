import axios from "axios"

export function startGame(input) {
  const res = axios({
    method: 'post',
    url: '/api/game/start',
    headers: {
      'Content-Type': 'application/json',
  },
    data: input
});
  return res;
}

export function test() {
    return axios.get('/api/game/start');
  }