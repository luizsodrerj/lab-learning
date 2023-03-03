import './css/styles.css'
import React from 'react'
import ReactDOM from 'react-dom'

import NavComponent from './components/NavComponent'
import PortfolioSection from './components/PortfolioSection'
import AboutSection from './components/AboutSection'
import Masthead from './components/Masthead'
import ContactSection from './components/ContactSection'
import ModaisMostruario from './components/ModaisMostruario'
import Footer from './components/Footer'


ReactDOM.render(
  <>
    <NavComponent></NavComponent>
    <Masthead></Masthead>
    <PortfolioSection></PortfolioSection>
    <AboutSection></AboutSection>
    <ContactSection></ContactSection>
    <Footer></Footer>
    <ModaisMostruario></ModaisMostruario>
  </>,
  document.getElementById('root')
)
