package biju.service;

import java.io.InputStream;
import java.io.Serializable;
import java.util.List;

import org.apache.commons.io.IOUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import biju.data.dto.CategoriaDTO;
import biju.repo.CargaDadosRepo;
import pdv.domain.Categoria;

public class CargaService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private CargaDadosRepo cargaRepo = new CargaDadosRepo();
	
	
	
	public List<Categoria> cargaCategorias() {
		try {
			InputStream stream = getClass().getResourceAsStream("/categorias.json");
			String json 	   = new String(IOUtils.toByteArray(stream), "UTF-8");
			
			List<CategoriaDTO>categs = new ObjectMapper().readValue(json, new TypeReference<List<CategoriaDTO>>(){});
			
			return cargaRepo.cargaCategorias(categs);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} 
	}

	
	public static void main(String[] args) {
		CargaService carga = new CargaService();
		
		List<Categoria>list = carga.cargaCategorias();
		
		for (Categoria categoria : list) {
			System.out.println(categoria.getNome()); 
		}
	}
}










