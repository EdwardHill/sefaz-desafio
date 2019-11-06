package br.com.sefaz.desafio.respository;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.sefaz.desafio.models.Telefone;
import br.com.sefaz.desafio.models.Usuario;

public interface TelefoneRepository extends PagingAndSortingRepository<Telefone, Long >{
	Page<Telefone> findByUsuario(Usuario usuario, Pageable page);	//encontra telefone por usuario com paginação
	
	//Iterable<Telefone> findByUsuario(Usuario usuario);
	Telefone findById(long id);
}
