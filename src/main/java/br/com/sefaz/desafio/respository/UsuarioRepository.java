package br.com.sefaz.desafio.respository;

import org.springframework.data.repository.PagingAndSortingRepository;


import br.com.sefaz.desafio.models.Usuario;

public interface UsuarioRepository extends PagingAndSortingRepository<Usuario, Long> {
		Usuario findById(long id);
		
}
