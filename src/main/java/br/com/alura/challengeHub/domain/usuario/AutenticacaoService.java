////package br.com.alura.challengeHub.domain.usuario;
////
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.security.core.userdetails.UserDetails;
////import org.springframework.security.core.userdetails.UserDetailsService;
////import org.springframework.security.core.userdetails.UsernameNotFoundException;
////import org.springframework.stereotype.Service;
////
////@Service
////public class AutenticacaoService implements UserDetailsService {
////
////    @Autowired
////    private UsuarioRepository repository;
////
////    @Override
////    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
////        return repository.findByEmail(username);
////    }
////}
//package br.com.alura.challengeHub.domain.usuario;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//public class AutenticacaoService implements UserDetailsService {
//
//    @Autowired
//    private UsuarioRepository repository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        UserDetails usuario = repository.findByEmail(username);
//
//        if (usuario == null) {
//            throw new UsernameNotFoundException("Usuário não encontrado com email: " + username);
//        }
//
//        return usuario;
//    }
//}
package br.com.alura.challengeHub.domain.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoService implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // ALTERADO: Agora busca por login em vez de email
        UserDetails usuario = repository.findByLogin(username);

        if (usuario == null) {
            throw new UsernameNotFoundException("Usuário não encontrado com login: " + username);
        }

        return usuario;
    }
}