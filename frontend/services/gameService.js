import axios from "axios"

export function startGame(req) {
  const res = axios.post(`/api/game/start`, {
      req,
  }
  );
  return res;
}

// export function test() {
//     return fetch("/api/game/start", {
//     // return fetch("http://localhost:8080/game/start", {
//         method: "GET",
//     headers: new Headers({ "Content-Type": "application/json" }),
//   })
//   .then((res) => {
//     if (res.ok) return res
//     console.log(res.body.json)

//     throw new Error("Bad Credentials!")
//   })
// }

export function test() {
    return axios.get('/api/game/start');
  }