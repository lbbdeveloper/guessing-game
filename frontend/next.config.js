
module.exports = {
  // Your existing module.exports

  // This function reroutes front-end calls to `/api` to the local go server at
  // `localhost:8080`, works like a proxy
  // for use in local dev environment only
  async rewrites() {
    return [
      {
        source: "/api/:path*",
        destination: "http://localhost:8080/:path*", // Proxy to Backend
      },
    ];
  },

};