package bijus.api.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

@Path("/upload")
public class UploadRestController {

	@POST
	@Consumes({MediaType.MULTIPART_FORM_DATA})
	public Response uploadPdfFile(
					  @FormDataParam("file") 
					  InputStream fileInputStream,
	                  @FormDataParam("file") 
					  FormDataContentDisposition fileMetaData
	                ) throws Exception {
		// /home/tqi_lsodre/Desktop/Sodre/bkp
		String path = "/home/tqi_lsodre/Desktop/Sodre/bkp/";
		
		try {
	        int read = 0;
	        byte[] bytes = new byte[1024];
	 
	        OutputStream out = new FileOutputStream(new File(path + fileMetaData.getFileName()));
	        while ((read = fileInputStream.read(bytes)) != -1) {
	            out.write(bytes, 0, read);
	        }
	        out.flush();
	        out.close();
	    } catch (Exception e) {
	    	e.printStackTrace();
	        throw e;
	    }		
		
		return Response.ok("Data uploaded successfully !!").build();
	}
	
	
}









