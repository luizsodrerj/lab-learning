import React from 'react'
import ReactDOM from 'react-dom'

function NavComponent() {
  return (
    <nav class="navbar navbar-expand-lg bg-secondary text-uppercase fixed-top" id="mainNav">
        <div class="container">
            <a class="navbar-brand" href="#page-top">Biju Girls</a>
            <button
                class="navbar-toggler text-uppercase font-weight-bold bg-primary text-white rounded"
                data-bs-target="#navbarResponsive"
                aria-controls="navbarResponsive"
                aria-label="Toggle navigation"
                data-bs-toggle="collapse"
                aria-expanded="false"
                type="button">
                Menu
                <i class="fas fa-bars"></i>
            </button>
            <div class="collapse navbar-collapse" id="navbarResponsive">
                <ul class="navbar-nav ms-auto">
                    <li class="nav-item mx-0 mx-lg-1">
                        <a class="nav-link py-3 px-0 px-lg-3 rounded" href="#portfolio">
                            Mostru&aacute;rio
                        </a>
                    </li>
                    <li class="nav-item mx-0 mx-lg-1">
                        <a class="nav-link py-3 px-0 px-lg-3 rounded" href="#about">
                            Sobre a Loja
                        </a>
                    </li>
                    <li class="nav-item mx-0 mx-lg-1">
                        <a class="nav-link py-3 px-0 px-lg-3 rounded" href="#contact">
                            Contatos
                        </a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
  )
}

export default NavComponent
