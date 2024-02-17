package biju.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.util.SerializableSupplier;

public class IOUtil {

	public static final String IMAGE_PNG = "image/png";

	
	public static StreamedContent imageBytesToStreamedContent(byte[] imageBytes) {
		return imageBytesToStreamedContent(
					imageBytes, 
					IMAGE_PNG
				);	
    }

	public static StreamedContent imageBytesToStreamedContent(byte[] imageBytes, String contentType) {
		if (imageBytes == null) {
			return null;
		}
		try {
			return DefaultStreamedContent.builder()
                .contentType(contentType)
                .stream(new SerializableSupplier<InputStream>() {
    				private static final long serialVersionUID = 1L;

					@Override
    				public InputStream get() {
                        try {
                            return new ByteArrayInputStream(imageBytes);
                        } catch (Exception e) {
                            e.printStackTrace();
                            throw new RuntimeException(e);
                        }
    				}
    			})
                .build();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
	
}




