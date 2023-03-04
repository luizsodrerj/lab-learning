import React from 'react'
import ReactDOM from 'react-dom'

export default () =>
	<> 
		<footer className="footer text-center">
            <div className="container">
                <div className="row">
                    <div className="col-lg-4 mb-5 mb-lg-0">
                        <h4 className="text-uppercase mb-4">Localiza&ccedil;&atilde;o</h4>
                        <p className="lead mb-0">
                            Rua Andr&eacute; Azevedo, 5 bocas em Olaria
                            <br />
                            Stand da loja na feira de artesanato das 5 bocas, pr&oacute;ximo ao Hospital Balbino
                            <br />
                            Das 8:00h &agrave;s 15:00h 
                        </p>
                    </div>
                    <div className="col-lg-4 mb-5 mb-lg-0">
                        <h4 className="text-uppercase mb-4">Contatos e Redes Sociais</h4>
                        <a className="btn btn-outline-light btn-social mx-1" href="https://www.facebook.com/patriciacarvalho.dasilva.73">
                        	<i className="fab fa-fw fa-facebook-f"></i>
                        </a>
                        <a className="btn btn-outline-light btn-social mx-1" href="https://www.instagram.com/bijus_girls22">
                        	<i className="fab fa-fw fa-instagram"></i>
                        </a>
                        <a className="btn btn-outline-light btn-social mx-1" href="mailto:patybijugirl@gmail.com">
                        	<i className="fab fa-fw fa-google"></i>
                        </a>
                        <a className="btn btn-outline-light btn-social mx-1" href="https://whatsapp.com">
                        	<i className="fab fa-fw fa-whatsapp"></i>
                        </a>
                    </div>
                    <div className="col-lg-4">
                        <h4 className="text-uppercase mb-4">BijuGirls</h4>
                        <p className="lead mb-0">
                            BijuGirls pode acessada no seguinte link
                            <a href="http://www.bijugirls.com.br">WebSite BijuGirls</a>
                        </p>
                    </div>
                </div>
            </div>
        </footer>
        <div className="copyright py-4 text-center text-white">
            <div className="container"><small>Copyright &copy; BijuGirls Bijuterias</small></div>
        </div>
	</>
        