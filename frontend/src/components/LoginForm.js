import React, { useState } from 'react'
import { Box, Typography, Divider, TextField, Paper, Button } from '@mui/material';
import * as gameService from "../services/gameService";

const LoginForm = () => {
    const [input, setInput] = useState({username: ""})
    const [gameStarted, setGameSatrted] = useState(false)
    const [gameID, setGameID] = useState("")
  
    const handleChange = (e) => {
        setInput({username: e.target.value})
      }

    const handleSubmit = (e) => {
        e.preventDefault()
        gameService.startGame(input)
        .then((res) => {
            setGameID(res.data.toString())
            setGameSatrted(true)
        })
      }


  return (
    <Box sx={{ display: 'flex', flexDirection: 'column', alignItems: 'center', justifyContent: 'center', height: '70vh' }}>
        <Paper elevation={2} >
        {gameStarted ? 
        <Box sx={{ margin: '30px', padding: '30px',  display: 'flex', flexDirection: 'column', gap: '20px', width: '350px' }} >
            <Typography  variant="h6" gutterBottom>
                    Game Rules: 
            </Typography>
            <Divider />
            <Typography  variant="p">
                You will play against the computer and guess a four-number combinations
            </Typography>
            <Typography  variant="p" gutterBottom>
                The numbers will be four different integers between 0-7. You can make 10 attempts! 
            </Typography>
            <Button disabled={gameID == "" } variant="outlined" href={`/game/${gameID}`} >Start!</Button>
        </Box> :
        <form autoComplete='off' onSubmit={handleSubmit}>
            <Box sx={{ margin: '30px', padding: '30px',  display: 'flex', flexDirection: 'column', gap: '30px', width: '350px' }} >
                <Typography  variant="h6" gutterBottom>
                    Welcome! Please let us know your name: 
                </Typography>
                <Divider />
                    <TextField id="outlined-basic" label="Name" variant="outlined" name="username" onChange={handleChange}/>
                    <Button disabled={input.username == "" } variant="outlined" type='submit' >Submit</Button>

            </Box>
        </form>
        }
        </Paper>  
    </Box>
  )
}

export default LoginForm;