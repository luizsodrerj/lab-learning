import React from 'react'
import ReactDOM from 'react-dom'

import BorboDourada from './assets/img/portfolio/borbo-dourada.PNG'
import Coracao from './assets/img/portfolio/coracao.PNG'
import Pulseira3ping from './assets/img/portfolio/pulseira-3-ping.PNG'
import Pulseira3pinprata from './assets/img/portfolio/pulseira-3-pin-prata.PNG'
import Relogiocobre from './assets/img/portfolio/relogio-cobre.PNG'
import Relogiodourado from './assets/img/portfolio/relogio-dourado.PNG'

import Caroussel from './Caroussel'


const portfolioImages = [{
    photo: BorboDourada,
    dataBsTarget: "#portfolioModal1",
    divClass: "col-md-6 col-lg-4 mb-5"
  },
  {
    photo: Coracao,
    dataBsTarget: "#portfolioModal2",
    divClass: "col-md-6 col-lg-4 mb-5"
  },
  {
    photo: Pulseira3ping,
    dataBsTarget: "#portfolioModal3",
    divClass: "col-md-6 col-lg-4 mb-5"
  },
  {
    photo: Pulseira3pinprata,
    dataBsTarget: "#portfolioModal4",
    divClass: "col-md-6 col-lg-4 mb-5 mb-lg-0"
  },
  {
    photo: Relogiocobre,
    dataBsTarget: "#portfolioModal5",
    divClass: "col-md-6 col-lg-4 mb-5 mb-md-0"
  },
  {
    photo: Relogiodourado,
    dataBsTarget: "#portfolioModal6",
    divClass: "col-md-6 col-lg-4"
  }
];

function PortfolioSection() {
  return (
    <section className="page-section portfolio" id="portfolio">
      <div className="container">
        <h2 className="page-section-heading text-center text-uppercase text-secondary mb-0">
            Mostru&aacute;rio
        </h2>
        <div className="divider-custom">
            <div className="divider-custom-line"></div>
            <div className="divider-custom-icon"><i className="fas fa-star"></i></div>
            <div className="divider-custom-line"></div>
        </div>
        <div className="row justify-content-center">
            {portfolioImages.map(e =>
              <div className={e.divClass}>
                  <div className="portfolio-item mx-auto" data-bs-toggle="modal" data-bs-target={e.dataBsTarget}>
                      <div className="portfolio-item-caption d-flex align-items-center justify-content-center h-100 w-100">
                          <div className="portfolio-item-caption-content text-center text-white"><i className="fas fa-plus fa-3x"></i></div>
                      </div>
                      <img className="img-fluid" src={e.photo} alt="..." />
                  </div>
              </div>
            )}
        </div>
      </div> 
	  <div className="row" style={{marginTop: "25px"}}>
		<div className="col-lg-3"></div>
			<div className="col-lg-6">
				<Caroussel></Caroussel>
			</div>	
	  	</div>
    </section> 
  )
}


export default PortfolioSection
