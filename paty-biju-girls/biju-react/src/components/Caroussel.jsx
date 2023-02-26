import React from 'react'
import ReactDOM from 'react-dom'

import $ from 'jquery' 

import Brincdou   from './assets/img/portfolio/brinc-dou.jpeg'
import Colarolhogreg from './assets/img/portfolio/colar-olhogreg.jpeg'
import Divbijus   from './assets/img/portfolio/div-bijus.jpeg'
import Colver     from './assets/img/portfolio/col-ver.jpeg'
import Divbijcor  from './assets/img/portfolio/div-bij-cor.jpeg'
import Pulsrox    from './assets/img/portfolio/puls-rox.jpeg'
import Pulsbeje   from './assets/img/portfolio/puls-beje.jpeg'
import Pulsdetdou from './assets/img/portfolio/puls-det-dou.jpeg'
import Pulvas     from './assets/img/portfolio/pul-vas.jpeg'

const carouselImages = [
	Colarolhogreg,
	Divbijus   ,
	Colver     ,
	Divbijcor  ,
	Pulsrox    ,
	Pulsbeje   ,
	Pulsdetdou ,
	Pulvas     
]

export default () =>   
	<div id="carouselImages" className="carousel slide" data-ride="carousel">
	  <div className="carousel-inner">
	    <div className="carousel-item active">
	      <img className="d-block w-100" src={Brincdou} alt="..." style={{maxHeight: '680px'}}/>
	    </div>
		{carouselImages.map(i =>	  
		    <div className="carousel-item">
		      <img className="d-block w-100" src={i} style={{maxHeight: '680px'}} alt="..."/>
		    </div>
	    )}
	  </div>
	  <a className="carousel-control-prev" href="#carouselImages" role="button" data-slide="prev">
	    <span className="carousel-control-prev-icon" aria-hidden="true"></span>
	    <span className="sr-only">Anterior</span>
	  </a>
	  <a className="carousel-control-next" href="#carouselImages" role="button" data-slide="next">
	    <span className="carousel-control-next-icon" aria-hidden="true"></span>
	    <span className="sr-only">Próxima</span>
	  </a>
	</div>  
  
  
  
  
  
  