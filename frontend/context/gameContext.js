import React, {useContext, createContext, useState} from "react";

export const GameContext = createContext({});

const GameContextProvider = ({children}) => {
    const [gameID, setGameID] = useState("")

    return (
        <GameContext.Provider value={{gameID, setGameID}} >
            {children}
        </GameContext.Provider>
    )

}

function useGameContext() {
    const context = useContext(GameContext);
    return context;
}

export {
    GameContextProvider,
    useGameContext
}