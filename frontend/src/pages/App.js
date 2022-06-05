import React from 'react';
import { Routes, Route } from 'react-router-dom'
import Home from './Home'
import GamePlay from './GamePlay';

function App() {
  return (
    <div className="App">
      <Routes>
        <Route path='/' element={<Home />} />    
        <Route
					exact path='/game/:id'
					element={ <GamePlay /> }
				/>
      </Routes>
    </div>
  );
}

export default App;
