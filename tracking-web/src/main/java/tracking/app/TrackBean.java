package tracking.app;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

@ManagedBean(name = "trackBean")
public class TrackBean {

	private TimeTrackingService service = new TimeTrackingService();

	private List<TimeTrk> list = new ArrayList<TimeTrk>();

	private String detalhe;
	private Date data;

	

	public String onClickBtSalvar() {
		TimeTrk trk = new TimeTrk();
		trk.setDetalhe(detalhe);
		trk.setData(data);

		service.persist(trk);
		
		return "/index.xhtml";
	}
	
	public String onClickIdLink() {
		FacesContext f 	= FacesContext.getCurrentInstance();
		Integer id 	   	= Integer.valueOf(((HttpServletRequest)f.getExternalContext().getRequest()).getParameter("id"));
		TimeTrk trk    	= service.getById(id);
		
		detalhe = trk.getDetalhe();
		data	= trk.getData();
		
		return "/detail.xhtml";
	}
	
	public String listTrackings() {
		list = service.getAll();

		return "/result.xhtml";
	}

	public List<TimeTrk> getList() {
		return list;
	}

	public String getDetalhe() {
		return detalhe;
	}

	public void setDetalhe(String detalhe) {
		this.detalhe = detalhe;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

}
