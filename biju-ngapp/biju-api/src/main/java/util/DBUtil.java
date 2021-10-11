package util;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.hibernate.ejb.Ejb3Configuration;

import bijus.entity.Peca;

public class DBUtil {

	static String sql = "CREATE MEMORY TABLE Peca (         " +
						"id			INTEGER IDENTITY PRIMARY KEY," +
						"descricao	VARCHAR(500),                " +
						"categoria	VARCHAR(100),                " +
						"tipo		VARCHAR(100),                " +
						"imagem		LONGVARBINARY,               " +
						"status		VARCHAR(70),                 " +
						"preco		DOUBLE                       " +
						")";
	
	static String sqlIns = "INSERT INTO Peca ( " +
							"descricao, " +
							"categoria, " +
							"tipo	,   " +
							"preco	    " +
							") VALUES ("
							+ "'Peca Mostruario',"
							+ "'Joia',"
							+ "'Cordao',"
							+ "10.0"
							+ ")";                                         

	
	public static void main(String[] args) {
		try {
//			initDB();
//			carga();
		
			testJPABlob();
			
			System.out.println("Base de Dados criada com sucesso!");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static void testJPABlob() throws Exception {
		EntityManagerFactory emf = initJPA();
		EntityManager em = null;
		
		try {
			em = emf.createEntityManager();
			em.getTransaction().begin();
			
			Peca p = new Peca();
			p.setDescricao("Teste Joia com Imagem");
			p.setCategoria("Joia");
			p.setPreco(10.0);

			String path = "/home/tqi_lsodre/Desktop/Sodre/MyWorkspace/framework-labweb/bijus/src/main/webapp/img/bijus/11.41.43.jpeg";
			byte[]bytes = Files.readAllBytes(Paths.get(path));
			p.setImagem(bytes);
			
			em.persist(p);
			
			em.getTransaction().commit();
			
		} finally {
			em.close();
			emf.close();
		}
	}
	
	static EntityManagerFactory initJPA() throws Exception {
		Properties properties = new Properties();
        properties.put("javax.persistence.provider", "org.hibernate.ejb.HibernatePersistence");
        properties.put("javax.persistence.transactionType", "RESOURCE_LOCAL");
        properties.put("hibernate.connection.username", "sa");
        properties.put("hibernate.connection.password", "");
        properties.put("hibernate.connection.driver_class", "org.hsqldb.jdbcDriver");
        properties.put("hibernate.connection.url", "jdbc:hsqldb:file:/home/tqi_lsodre/Desktop/Sodre/MyWorkspace/framework-labweb/bijus/src/main/resources/dados/bijudb;shutdown=true");
        properties.put("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.format_sql", "true");

        //Class.forName("org.hibernate.ejb.HibernatePersistence");
        
        Ejb3Configuration cfg = new Ejb3Configuration();
        cfg.addProperties(properties);
        cfg.addAnnotatedClass(Peca.class);

        return cfg.buildEntityManagerFactory();
        
//        return Persistence.createEntityManagerFactory("PU",properties);	
	}
	
	static void initDB() throws Exception {
		Connection c = null;
		try {
			c = getCon();
			PreparedStatement p = c.prepareStatement(sql);
			p.executeUpdate();
			
		} finally {
			c.close();
		}
	}

	static void carga() throws Exception {
		Connection c = null;
		try {
			c = getCon();
			PreparedStatement p = c.prepareStatement(sqlIns);
			p.executeUpdate();
			
		} finally {
			c.close();
		}
	}
	
	static Connection getCon() throws Exception {
		String path = "/home/tqi_lsodre/Desktop/Sodre/MyWorkspace/framework-labweb/bijus/src/main/resources/dados/bijudb;shutdown=true";
		String url = "jdbc:hsqldb:file:" + path;
		String u = "sa";
		String p = "";
		
		Class.forName("org.hsqldb.jdbcDriver");
		
		return  DriverManager.getConnection(url, u, p);
	}
	
}





