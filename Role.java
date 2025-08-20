package com.forohub.domain.usuario;


public enum Role {
ADMIN,
USER
}


UsuarioRepository.java

  package com.forohub.domain.usuario;


import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;


public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
Optional<Usuario> findByLogin(String login);
}

CustomUserDetailsService.java

  package com.forohub.domain.usuario;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {


private final UsuarioRepository usuarioRepository;


@Override
public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
return usuarioRepository.findByLogin(username)
.orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));
}

