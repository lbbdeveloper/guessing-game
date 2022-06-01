import React, { useState } from 'react'
import { useRouter } from 'next/router'
import { Box, Typography, Divider, TextField, Paper, Button } from '@mui/material';

const LoginForm = () => {
    const router = useRouter()
    const [name, setName] = useState('')
  
    const handleSubmit = (e) => {
        e.preventDefault()
        return router.push('/game/abc')
      }

  return (
    <Box sx={{ display: 'flex', flexDirection: 'column', alignItems: 'center', justifyContent: 'center', height: '70vh' }}>
        <form autoComplete='off' onSubmit={handleSubmit}>
        <Paper elevation={2} sx={{ margin: '2rem', padding: '2rem',  display: 'flex', flexDirection: 'column', gap: '2rem' }} >
        <Typography  variant="h6" component="div" gutterBottom>
            Welcome! Please let us know your name: 
        </Typography>
        <Divider />
            <TextField id="outlined-basic" label="Name" variant="outlined" name="roll" />
            <Button disabled={name == ""} variant="outlined" type='submit' >Start Game</Button>
        </Paper>  
        </form>
    </Box>
  )
}

export default LoginForm;