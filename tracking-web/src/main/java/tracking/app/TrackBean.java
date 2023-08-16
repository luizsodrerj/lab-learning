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

	public String onClickBtDelete() {
		Integer id  = getParamId();
		TimeTrk trk = service.getById(id);

		service.remove(trk);
		
		return listTrackings();
	}
 
	
	public String onClickIdLink() {
		Integer id = getParamId();
		TimeTrk trk    	= service.getById(id);
		
		detalhe = trk.getDetalhe();
		data	= trk.getData();
		
		return "/detail.xhtml";
	}

	private Integer getParamId() {
		FacesContext f = FacesContext.getCurrentInstance();
		
		return Integer.valueOf(
				((HttpServletRequest)f.getExternalContext()
									  .getRequest())
									  .getParameter("id")
				);
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
