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

export function playGame(id, input) {
    const res = axios({
      method: 'post',
      url: `/api/game/play/${id}`,
      headers: {
        'Content-Type': 'application/json',
    },
      data: input
  });
    return res;
}

export function checkGameStatus(id) {
  return axios.get(`/api/game/game-status/${id}`);
}