package biju.service;

import java.io.InputStream;
import java.io.Serializable;
import java.util.List;

import org.apache.commons.io.IOUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import biju.data.dto.CategoriaDTO;
import biju.data.dto.StatusProdutoDTO;
import biju.repo.CargaDadosRepo;
import framework.persistence.jpa.PersistenceServiceUtil;
import pdv.domain.Categoria;
import pdv.domain.StatusProduto;

public class CargaService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private CargaDadosRepo cargaRepo = new CargaDadosRepo();
	
	
	
	public List<StatusProduto> cargaStatus() {
		PersistenceServiceUtil persistence = new PersistenceServiceUtil();
		try {
			InputStream stream = getClass().getResourceAsStream("/status-produto.json");
			String json 	   = new String(IOUtils.toByteArray(stream), "UTF-8");
			
			List<StatusProdutoDTO>statusList = new ObjectMapper().readValue(json, new TypeReference<List<StatusProdutoDTO>>(){});
			
			persistence.beginTransaction();
			
			for (StatusProdutoDTO statusTO: statusList) {
				StatusProduto status = new StatusProduto();
				status.setStatus(statusTO.getStatus());
				
				persistence.persist(status);
			}
			persistence.commit();
			
			return persistence.findAll(StatusProduto.class, null);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			persistence.close();
		} 
	}
	
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
		
		List<StatusProduto>list = carga.cargaStatus();
		
		for (StatusProduto status: list) {
			System.out.println(status.getStatus()); 
		}
	}
}










