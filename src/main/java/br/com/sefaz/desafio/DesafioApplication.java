package br.com.sefaz.desafio;




import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class DesafioApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafioApplication.class, args);
		
		//ConfigurableApplicationContext context =SpringApplication.run(DesafioApplication.class, args);

		// UsuarioRepository ur = context.getBean(UsuarioRepository.class);

		/*
		 * Telefone tel = new Telefone(); Telefone tel2 = new Telefone(); Telefone tel3=
		 * new Telefone(); Usuario usu = new Usuario(); usu.setNome("Maria");
		 * usu.setEmail("androgenol@.com"); usu.setSenha("caligula");
		 * 
		 * tel.setDdd(81); tel.setTipo("celular"); tel.setNumero("3938398");
		 * 
		 * tel2.setDdd(58); tel2.setTipo("fixo"); tel2.setNumero("4848344r676");
		 * 
		 * tel3.setDdd(45); tel3.setTipo("fixo"); tel3.setNumero("4409582229222");
		 * 
		 * List <Telefone> tels = new ArrayList<Telefone>(); tels.add(tel);
		 * tels.add(tel2); tels.add(tel3);
		 * 
		 * usu.setTelefones(tels);
		 * 
		 * ur.save(usu);
		 */
	}
}
