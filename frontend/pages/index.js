import Head from 'next/head'
import Image from 'next/image'
import styles from '../styles/Home.module.css'
import { Box, Typography, Divider} from '@mui/material';
import LoginForm from '../components/LoginForm';
import Layout from '../components/Layout';

export default function Home() {
  
  return (
    <Layout >
      <LoginForm />
    </Layout>
        
  )
}
