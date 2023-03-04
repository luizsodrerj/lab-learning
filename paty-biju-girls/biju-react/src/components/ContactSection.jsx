import React from 'react'
import ReactDOM from 'react-dom'

import What from './assets/img/social/what.png'
import Insta from './assets/img/social/insta.png'
import Face from './assets/img/social/face.png'
import Gmail from './assets/img/social/g.png'


const contacts = [{
		icon: What,
		text: "WhatsApp - (21)98071-6352",
		link: "",
		isLink: false
	},
	{
		icon: Insta,
		text: "Instagram - https://www.instagram.com/bijus_girls22",
		link: "https://www.instagram.com/bijus_girls22",
		isLink: true
	},
	{
		icon: Face,
		text: "Facebook - https://www.facebook.com/patriciacarvalho.dasilva.73",
		link: "https://www.facebook.com/patriciacarvalho.dasilva.73",
		isLink: true
	},
	{
		icon: Gmail,
		text: "E-mail - patybijugirl@gmail.com",
		link: "",
		isLink: false
	}
]

export default () =>   
<section className="page-section" id="contact">
    <div className="container">
        <div className="text-center">
            <h2 className="section-heading text-uppercase">
                Entre em contato conosco
            </h2>
        </div>
        <div className="text-left" style={{marginTop: '35px'}}>
            <div className="row">
            	{contacts.map(contact =>
            	  <>
	                <div className="col-md-3"></div>
	                <div className="col-md-8">
	                    <h4 className="section-subheading text-muted text-left">
	                        <img src={contact.icon} style={{width: '55px'}} alt="" />
	                        {contact.isLink ?
	                        	<a href={contact.link}>{contact.text}</a> :
								<>{contact.text}</>
							}
	                    </h4>
	                </div>
            	  </>
				)}
            </div>
        </div>
    </div>
</section>




