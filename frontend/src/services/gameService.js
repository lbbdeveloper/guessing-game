import axios from "axios"

// create game in backend and get gameID from backend
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

// call backend API for feedback
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