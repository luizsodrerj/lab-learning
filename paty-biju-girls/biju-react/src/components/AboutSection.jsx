import React from 'react'
import ReactDOM from 'react-dom'

import Caroussel from './Caroussel'

export default () =>   
	<section className="page-section bg-primary text-white mb-0" id="about">
        <div className="container">
            <h2 className="page-section-heading text-center text-uppercase text-white">
                Sobre a Loja
            </h2>
            <div className="divider-custom divider-light">
                <div className="divider-custom-line"></div>
                <div className="divider-custom-icon"><i className="fas fa-star"></i></div>
                <div className="divider-custom-line"></div>
            </div>
            <div className="row">
                <div className="col-lg-4 ms-auto">
                  <p className="lead">
                	Vendemos diversos tipos de Jóias, Semijoias, Bijuterias variadas e muitos Acessórios.
                  </p>
                </div>
                <div className="col-lg-4 me-auto">
                  <p className="lead">
                	Aproveite e misture nossos diferentes acessórios e bijuterias 
                	para ter um look estiloso! Eternize momentos incríveis presenteando com nossas peças. 
               	  </p>
               	</div>
            </div>
        	<div className="row">
        		<div className="col-lg-3"></div>
        		<div className="col-lg-6">
					<Caroussel></Caroussel>
				</div>	
			</div>
        </div>
    </section>


