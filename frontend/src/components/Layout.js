import { Box, Typography, Divider} from '@mui/material';

export default function Layout({children}) {
  
  return (
        <Box sx={{ width: '100%', textAlign: 'center', padding: '2rem' }}>
            <Typography variant="h4" component="div" gutterBottom>
                Mastermind Game
            </Typography>
            <Divider />
            {children}
        </Box>
  )
}
