package biju.util;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class FacesUtil {


	public static final Object getParameter(String paramName) {
		return getRequest().getParameter(paramName);
	}
	
	public static final HttpSession getSession() {
		return getRequest().getSession();
	}
	
	public static final HttpServletRequest getRequest() {
		FacesContext context = FacesContext.getCurrentInstance();
		
		return (HttpServletRequest)context.getExternalContext()
										  .getRequest();
	}

}
