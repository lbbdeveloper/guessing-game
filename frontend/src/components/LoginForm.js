import React, { useState, useEffect } from 'react'
import { Box, Typography, Divider, TextField, Paper, Button } from '@mui/material';
import * as gameService from "../services/gameService";

const LoginForm = () => {
    const [input, setInput] = useState({username: ""})
    const [gameStarted, setGameSatrted] = useState(false)
    const [gameID, setGameID] = useState("")
  
    const handleChange = (e) => {
        setInput({
            ...input,
            [e.target.name]: e.target.value,})
      }

    const handleSubmit = async (e) => {
        e.preventDefault()
        gameService.startGame(input)
        .then((res) => {
            setGameID(res.data.toString())
            setGameSatrted(true)
        });
      }

//debug
useEffect(() => {
    console.log("test")

    gameService.test()
        .then((res) => {
           console.log(res.data)
        });
  }, []);

  return (
    <Box sx={{ display: 'flex', flexDirection: 'column', alignItems: 'center', justifyContent: 'center', height: '70vh' }}>
        <Paper elevation={2} >
        {gameStarted ? 
        <Box sx={{ margin: '2rem', padding: '2rem',  display: 'flex', flexDirection: 'column', gap: '2rem', width: '350px' }} >
            <Typography  variant="h6" component="div" gutterBottom>
                    Game Rules: 
            </Typography>
            <Divider />
            <Typography  variant="p" component="div" gutterBottom>
                You will play against the computer and guess a four-number combinations. You can make 10 attempts! 
            </Typography>
            <Button disabled={gameID == "" } variant="outlined" href={`/game/${gameID}`} >Start!</Button>
        </Box> :
        <form autoComplete='off' onSubmit={handleSubmit}>
            <Box sx={{ margin: '2rem', padding: '2rem',  display: 'flex', flexDirection: 'column', gap: '2rem', width: '350px' }} >
                <Typography  variant="h6" component="div" gutterBottom>
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