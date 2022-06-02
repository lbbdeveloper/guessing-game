import { GameContextProvider } from '../context/gameContext'
import '../styles/globals.css'

function MyApp({ Component, pageProps }) {
  return (
    <GameContextProvider >
      <Component {...pageProps} />
    </GameContextProvider>
  )
}

export default MyApp
