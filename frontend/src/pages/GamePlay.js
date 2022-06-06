import React, { useState, useEffect } from 'react'
import {useParams} from 'react-router-dom';
import { Box, Paper, Button, Typography, TextField, Divider, List} from '@mui/material';
import Layout from '../components/Layout';
import * as gameService from "../services/gameService";

export default function GamePlay() {
  const params = useParams();
  const [input, setInput] = useState([0,0,0,0])
  const [feedback, setFeedback] = useState({})
  const [previousInput, setPreviousInput] = useState([0,0,0,0])
  const [winner, setWinner] = useState(false)
  const [gameStatus, setGameStatus] = useState('')
  const [attemps, setAttemps] = useState(10)
  const [history, setHistory] = useState([])

  // watch input change
  const handleChange = (e) => {
    let newInput = [...input]
    newInput[e.target.name] = parseInt(e.target.value)
    setInput(newInput)
  }
  
  // call gameService function, to get backend feedback
  const handleSubmit = (e) => {
    e.preventDefault()
    const obj = { playerAnswer: input }
    gameService.playGame( params.id, obj )
    .then((res) => {
      setFeedback(res.data.feedback)
      setPreviousInput(res.data.playerAnswer)
    });
  }

  // update gameStatus when input is updated
  useEffect(() => {
    gameService
        .checkGameStatus(params.id)
        .then((res) => {
          console.log(res.data)
          setAttemps(res.data.attemptsRemaining)
          setHistory(res.data.history)
          setWinner(res.data.winner)
          setGameStatus(res.data.gameStatus)
        })
        .catch((err) => console.error(err));
  }, [previousInput]);

  // show different view based on gameStatus value
  return (
    <Layout >
      { gameStatus != "Finished" ? 
      <Box>
        <Box sx={{ display: 'flex', alignItems: 'center', justifyContent: 'center', height: '50vh', gap: '24px', justifyContent: 'space-around' }}>
        
        <Paper elevation={2} sx={{ padding: '16px', minHeight: '300px', minWidth: '300px' }} >
        <List sx={{
            width: '100%',
            maxWidth: 300,
            overflow: 'auto',
            maxHeight: 300,
          }}>
            <Typography  variant="h6"  >
                  Game History: 
            </Typography>
            { history ? history.map( (item, i) => (
              <Box key={i}>
                <Divider sx={{ margin: '16px 0' }} />
              [  {item.playerAnswer.map( n => `${n},` ) }  ]
              <Typography  variant="p" component="div" >
                  correct number: {item.feedback.correctNum.toString()}
                </Typography>
                <Typography  variant="p" component="div" >
                      correct number and location: {item.feedback.correctNumAndLocation.toString()}
                </Typography>
                <Typography  variant="p" component="div" >
                      Winner: {item.feedback.winner.toString()}
                </Typography>
              </Box>
            ) ) : null}
          </List>
          </Paper>
        <Paper elevation={2} >
            <form autoComplete='off' onSubmit={handleSubmit}>
            <Box sx={{ margin: '30px', padding: '30px',  display: 'flex', flexDirection: 'column', gap: '30px', width: '350px' }} >
                <Typography  variant="h6" component="div" gutterBottom>
                        Enter your answer: (Integers between 0-7)
                </Typography>
                <Box sx={{ display: 'flex', gap: '8px' }} >
                  <TextField
                    name="0"
                    label="#1"
                    type="number"
                    InputLabelProps={{
                      shrink: true,
                    }}
                    onChange={handleChange}
                  />
                  <TextField
                    name="1"
                    label="#2"
                    type="number"
                    InputLabelProps={{
                      shrink: true,
                    }}
                    onChange={handleChange}
                  />
                  <TextField
                    name="2"
                    label="#3"
                    type="number"
                    InputLabelProps={{
                      shrink: true,
                    }}
                    onChange={handleChange}
                  />
                  <TextField
                    name="3"
                    label="#4"
                    type="number"
                    InputLabelProps={{
                      shrink: true,
                    }}
                    onChange={handleChange}
                  />
                </Box>
                <Button variant="outlined" type='submit' >Submit</Button>
            </Box>
            </form>
          </Paper>
        <Paper elevation={2} sx={{ padding: '16px', minHeight: '300px', minWidth: '300px'  }} >
            <Typography  variant="h6" component="div" gutterBottom>
                  You have: 
            </Typography>
            <Typography  variant="h2" component="div" >
                  {attemps}
            </Typography>
            <Typography  variant="h6" component="div" gutterBottom>
                  attemps remaining 
            </Typography>
          </Paper>
        </Box>
        <Paper elevation={2} >
          {Object.keys(feedback).length != 0 && 
          <Box sx={{ padding: '16px', margin: '16px' }} >
            <Typography  variant="h6" component="div" gutterBottom>
                    Feedback: 
            </Typography>
            <Divider />
            <Typography  variant="p" component="div" gutterBottom>
                    you entered: [ {previousInput.map( n => `${n},` ) } ]
            </Typography>
            {feedback.correctNum ? <Typography  variant="p" component="div" sx={{color: 'green'}} >1. You have guess a correct number!</Typography> : <Typography  variant="p" component="div">1. None of the number is correct!!</Typography>}
            {feedback.correctNumAndLocation ? <Typography  variant="p" component="div" sx={{color: 'green'}} >2. You have guess a correct number and it's location!</Typography> : <Typography  variant="p" component="div">2. None of the location is correct!!</Typography>}
            {feedback.winner ? <Typography  variant="p" component="div" sx={{color: 'green'}} >3. Congrats! You have guess the correct combination!</Typography> : <Typography  variant="p" component="div">3. You don't have a correct answer yet. Try again</Typography>}
          </Box>
          }
        </Paper>
      </Box> : 
      <Box sx={{ display: 'flex', flexDirection: 'column', alignItems: 'center', justifyContent: 'center', gap: '32px', height: '80vh', }}>
        {winner ? 
        <Typography  variant="h2" component="div" sx={{ color: 'green'}} >
            You Won!!!
        </Typography> : 
        <Typography  variant="h2" component="div" sx={{ color: 'red'}}  >
            Failed
        </Typography> }
        <Typography  variant="h5" component="div" >
            End of Game!
        </Typography>
        <Button href='/' >Home</Button>
      </Box>
      }
    </Layout>
  )
}
