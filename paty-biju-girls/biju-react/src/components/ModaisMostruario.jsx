import React from 'react'
import ReactDOM from 'react-dom'

import BorboDourada from './assets/img/portfolio/borbo-dourada.PNG'
import Coracao from './assets/img/portfolio/coracao.PNG'
import Pulseira3ping from './assets/img/portfolio/pulseira-3-ping.PNG'
import Pulseira3pinprata from './assets/img/portfolio/pulseira-3-pin-prata.PNG'
import Relogiocobre from './assets/img/portfolio/relogio-cobre.PNG'
import Relogiodourado from './assets/img/portfolio/relogio-dourado.PNG'

const portfolioImages = [{
	text: "Colar com pingente em forma de borboleta. Possui pingente e cord\u00E3o folheados a ouro.",
	title: "Colar com Pingente em Forma de Borboleta",
    id: "portfolioModal1",
    photo: BorboDourada
  },
  {
	text: "Colar com pingente e cord\u00E3o em forma de cora\u00E7\u00E3o",
	title: "colar com cora\u00E7\u00E3o",
    id: "portfolioModal2",
    photo: Coracao
  },
  {
	text: "Pulseira dourada com 3 adornos",
	title: "Pulseira dourada com adornos",
    id: "portfolioModal3",
    photo: Pulseira3ping
  },
  {
	text: "Pulseira prateada com 3 adornos",
	title: "Pulseria prateada com adornos",
    id: "portfolioModal4",
    photo: Pulseira3pinprata
  },
  {
	text: "Kit com rel\u00F3gio cor ouro cobre e pulseira com pingente patinha de gato",
	title: "Kit com rel\u00F3gio e pulseira",
    id: "portfolioModal5",
    photo: Relogiocobre
  },
  {
	text: "Kit com rel\u00F3gio dourado e pulseira com pingente de ramos dourados",
	title: "Kit com rel\u00F3gio e pulseira",
    id: "portfolioModal6",
    photo: Relogiodourado
  }
];


export default () =>
<>
  {portfolioImages.map(i => 
    <div className="portfolio-modal modal fade" id={i.id} tabindex="-1" aria-labelledby={i.id} aria-hidden="true">
        <div className="modal-dialog modal-xl">
            <div className="modal-content">
                <div className="modal-header border-0"><button className="btn-close" type="button" data-bs-dismiss="modal" aria-label="Close"></button></div>
                <div className="modal-body text-center pb-5">
                    <div className="container">
                        <div className="row justify-content-center">
                            <div className="col-lg-8">
                                {/* Portfolio Modal - Title */} 
                                <h2 className="portfolio-modal-title text-secondary text-uppercase mb-0">
                                    {i.title}
                                </h2>
                                {/* Icon Divider*/}
                                <div className="divider-custom">
                                    <div className="divider-custom-line"></div>
                                    <div className="divider-custom-icon"><i className="fas fa-star"></i></div>
                                    <div className="divider-custom-line"></div>
                                </div>
                                {/* Portfolio Modal - Image*/}
                                <img className="img-fluid rounded mb-5" src={i.photo} alt="..." />
                                {/* Portfolio Modal - Text*/}
                                <p className="mb-4">
                                    {i.text}
                                </p>
                                <button className="btn btn-primary" data-bs-dismiss="modal">
                                    <i className="fas fa-xmark fa-fw"></i>
                                    Fechar Janela
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
  )}   
</>
  