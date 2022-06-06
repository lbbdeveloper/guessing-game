import axios from "axios"

const BEURL = "http://localhost:8080"
export function startGame(input) {
  const res = axios({
    method: 'post',
    url: `${BEURL}/game/start`,
    headers: {
      'Content-Type': 'application/json',
  },
    data: input
});
  return res;
}

export function playGame(id, input) {
    const res = axios({
      method: 'post',
      url: `${BEURL}/game/play/${id}`,
      headers: {
        'Content-Type': 'application/json',
    },
      data: input
  });
    return res;
}

export function checkGameStatus(id) {
  return axios.get(`${BEURL}/game/game-status/${id}`);
}