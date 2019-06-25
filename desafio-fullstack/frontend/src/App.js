import React from 'react';
import { BrowserRouter } from 'react-router-dom'

import Header from './components/Header/header'
import Footer from './components/Footer/footer'
import Routes from './routes'

function App() {
  return (
    <BrowserRouter>
      <Header />
      <Routes />
      <Footer />
    </BrowserRouter>
  );
}

export default App;