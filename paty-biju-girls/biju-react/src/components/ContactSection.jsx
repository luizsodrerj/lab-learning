import React from 'react'
import ReactDOM from 'react-dom'

import What from './assets/img/social/what.png'
import Insta from './assets/img/social/insta.png'
import Face from './assets/img/social/face.png'
import Twit from './assets/img/social/twit.png'
import Gmail from './assets/img/social/g.png'

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
                <div className="col-md-3"></div>
                <div className="col-md-8">
                    <h3 className="section-subheading text-muted text-left">
                        <img src={What} style={{width: '55px'}} alt="" />
                        WhatsApp - 21 9999-9999
                    </h3>
                </div>
                <div className="col-md-3"></div>
                <div className="col-md-8">
                    <h3 className="section-subheading text-muted text-left">
                        <img src={Insta} style={{width: '55px'}} alt="" />
                        Instagram - www.instagram.com/bijus
                    </h3>
                </div>
                <div className="col-md-3"></div>
                <div className="col-md-8">
                    <h3 className="section-subheading text-muted text-left">
                        <img src={Face} style={{width: '55px'}} alt="" />
                        Facebook - www.facebook.com
                    </h3>
                </div>
                <div className="col-md-3"></div>
                <div className="col-md-8">
                    <h3 className="section-subheading text-muted text-left">
                        <img src={Twit} style={{width: '55px'}} alt="" />
                        Twitter - www.twitter.com
                    </h3>
                </div>
                <div className="col-md-3"></div>
                <div className="col-md-8">
                    <h3 className="section-subheading text-muted text-left">
                        <img src={Gmail} style={{width: '55px'}} alt="" />
                        E-mail - bijugirls@gmail.com
                    </h3>
                </div>
            </div>
        </div>
    </div>
</section>
