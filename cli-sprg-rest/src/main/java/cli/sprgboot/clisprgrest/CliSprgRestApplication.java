package cli.sprgboot.clisprgrest;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import cli.sprgboot.entity.Cliente;
import cli.sprgboot.repo.ClienteRepository;

@SpringBootApplication
@EnableJpaRepositories("cli.sprgboot.repo")
@EntityScan("cli.sprgboot.entity")
public class CliSprgRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(CliSprgRestApplication.class, args);
	}

	 @Bean
	 public CommandLineRunner demo(ClienteRepository repository) {
	    return (args) -> {
	    	Cliente c1 = repository.findById(1).get();
	    	
	    	System.out.println("Cliente 1 - "+c1);
	    	System.out.println("razao social "+c1.getNomeRazaoSocial());
	    	
	    	Cliente c2 = repository.findById(2).get();
	    	System.out.println("Cliente 2 - "+c2);
	    	System.out.println("razao social "+c2.getNomeRazaoSocial());
	    	
	    };
	 }
}
