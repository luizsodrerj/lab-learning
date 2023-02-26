import './css/styles.css'
import React from 'react'
import ReactDOM from 'react-dom'

import NavComponent from './components/NavComponent'
import PortfolioSection from './components/PortfolioSection'
import AboutSection from './components/AboutSection'
import Masthead from './components/Masthead'

ReactDOM.render(
  <>
    <NavComponent></NavComponent>
    <Masthead></Masthead>
    <PortfolioSection></PortfolioSection>
    <AboutSection></AboutSection>
  </>,
  document.getElementById('root')
)
