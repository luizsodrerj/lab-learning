import React from 'react'
import ReactDOM from 'react-dom'

import patyBijuImg from './assets/img/party-biju.PNG';

function Masthead() {
  return (
    <header class="masthead bg-primary text-white text-center">
        <div class="container d-flex align-items-center flex-column">
            <img class="masthead-avatar mb-5" src={patyBijuImg} alt="..." />
            <h1 class="masthead-heading text-uppercase mb-0">Biju Girls</h1>
            <div class="divider-custom divider-light">
                <div class="divider-custom-line"></div>
                <div class="divider-custom-icon"><i class="fas fa-star"></i></div>
                <div class="divider-custom-line"></div>
            </div>
            <p class="masthead-subheading font-weight-light mb-0">
                <h3>J&oacute;ias - Semijoias - Bijuterias - Acess&oacute;rios</h3>
            </p>
        </div>
    </header>
  )
}

export default Masthead
