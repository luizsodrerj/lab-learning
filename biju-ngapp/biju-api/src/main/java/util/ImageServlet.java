package util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bijus.entity.Peca;
import framework.util.CollectionsUtil;

public class ImageServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Peca>collection = (List<Peca>)request.getSession().getAttribute("imageList"); 
		Integer id 			 = Integer.valueOf(request.getParameter("id"));
        Peca peca 			 = CollectionsUtil.find(collection,"id",id);
		byte[]imagem		 = peca.getImagem();
        
        response.setContentType("image/png");
        response.setHeader("Content-Disposition", "inline;filename=\"" + "image.png" + "\"");
         
        try (
        	ReadableByteChannel input = Channels.newChannel(new ByteArrayInputStream(imagem));
            WritableByteChannel output = Channels.newChannel(response.getOutputStream());
        ) {
            for (ByteBuffer buffer = ByteBuffer.allocateDirect(10240); input.read(buffer) != -1; buffer.clear()) {
            	output.write((ByteBuffer) buffer.flip());
            }
         }		
	}
}




