package biju.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import framework.util.CollectionsUtil;
import pdv.domain.Produto;

public class ImageGeneratorServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		List<Produto>list   = (List<Produto>)session.getAttribute("produtos");
		Integer paramId 	= Integer.valueOf(request.getParameter("prodId"));
		Produto produto 	= CollectionsUtil.find(list, "id", paramId);
		
		response.setContentType(IOUtil.IMAGE_PNG);

		ByteArrayInputStream in = new ByteArrayInputStream(produto.getImagem());
		OutputStream out = response.getOutputStream();

		byte[] buf = new byte[1024];
		int count = 0;
		while ((count = in.read(buf)) >= 0) {
			out.write(buf, 0, count);
		}
		out.close();
		in.close();
	}

}




