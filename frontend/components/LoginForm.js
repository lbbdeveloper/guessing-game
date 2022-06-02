import React, { useState, useEffect } from 'react'
import { useRouter } from 'next/router'
import { Box, Typography, Divider, TextField, Paper, Button } from '@mui/material';
import * as gameService from "../services/gameService";
import { useGameContext } from '../context/gameContext';

const LoginForm = () => {
    const router = useRouter()
    const [input, setInput] = useState({username: ""})
    const {gameID, setGameID} = useGameContext()
  
    const handleChange = (e) => {
        setInput({
            ...input,
            [e.target.name]: e.target.value,})
      }

    const handleSubmit = async (e) => {
        e.preventDefault()
        console.log(input)
        gameService.startGame(input)
        .then((res) => {
            setGameID(res.data.toString())
        });
        console.log(gameID)

        // router.push(`/game/${gameID}`)
      }

    //   useEffect(() => {
    //     gameService
    //       .test()
    //       .then((res) => {
    //         console.log(res.data)
    //       })
    //       .catch((err) => console.error(err));
    //     }, []);


  return (
    <Box sx={{ display: 'flex', flexDirection: 'column', alignItems: 'center', justifyContent: 'center', height: '70vh' }}>
        <form autoComplete='off' onSubmit={handleSubmit}>
        <Paper elevation={2} sx={{ margin: '2rem', padding: '2rem',  display: 'flex', flexDirection: 'column', gap: '2rem' }} >
        <Typography  variant="h6" component="div" gutterBottom>
            Welcome! Please let us know your name: 
        </Typography>
        <Divider />
            <TextField id="outlined-basic" label="Name" variant="outlined" name="username" onChange={handleChange}/>
            <Button disabled={input.username == "" } variant="outlined" type='submit' >Start Game</Button>
        </Paper>  
        </form>
    </Box>
  )
}

export default LoginForm;