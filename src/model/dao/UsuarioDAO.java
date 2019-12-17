package model.dao;

import java.util.List;

import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;

import model.bean.Usuario;

public class UsuarioDAO extends DAO{

	public boolean verificaLogin(String login, String senha) {
		Usuario usuario = null;
		
		try {
			Query query = em.createQuery("select u from Usuario u where u.matricula = :login and u.senha = :senha");
			query.setParameter("login", login);
			query.setParameter("senha", senha);
			usuario = (Usuario) query.getSingleResult();
		} catch (Exception e) {
			System.err.println(e);
		} finally {
			closeTransacao();
		}

		return usuario != null && usuario.getId() != null;
	}
	
	public Usuario getUsuarioLogado(String login, String senha) {
		Usuario usuario = null;
		
		try {
			Query query = em.createQuery("select u from Usuario u where u.matricula = :login and u.senha = :senha");
			query.setParameter("login", login);
			query.setParameter("senha", senha);
			usuario = (Usuario) query.getSingleResult();
		} catch (Exception e) {
			System.err.println(e);
		} finally {
			closeTransacao();
		}

		return usuario;
	}
	
	public Usuario findById(Integer id) {
		Usuario usuario = null;
		try {
			usuario = em.find(Usuario.class, id);
		} catch (Exception e) {
			System.err.println(e);
		} finally {
			closeTransacao();
		}
		return usuario;
	}
	
	@SuppressWarnings("unchecked")
	public List<Usuario> findAll(){
		List<Usuario> listaUsuario = null;
		try {
			listaUsuario = em.createQuery("from Usuario u").getResultList();
		} catch (Exception e) {
			System.err.println(e);
		} finally {
			closeTransacao();
		}
		return listaUsuario;
	}
	
	@SuppressWarnings("unchecked")
	public List<Usuario> findByNomeMatricula(String nome, String matricula){
		List<Usuario> listaUsuario = null;
		try {
			if(StringUtils.isBlank(nome) && StringUtils.isBlank(matricula)) {
				listaUsuario = findAll();
			} else {
				String likeNome = StringUtils.isNotBlank(nome) ? " or upper(u.nome) like '%" + nome.toUpperCase() + "%'" : "";
				Query query = em.createQuery("select u from Usuario u where u.matricula = '" + matricula + "' " + likeNome);
				listaUsuario = query.getResultList();
			}
		} catch (Exception e) {
			System.err.println(e);
		} finally {
			closeTransacao();
		}
		return listaUsuario;
	}
	
	public Usuario saveOrUpdate(Usuario usuario) {
		try {
			iniciarTransacao();
			if(usuario.getId() == null) {
				em.persist(usuario);
			} else {
				em.merge(usuario);
			}
			commitTransacao();
		} catch (Exception e) {
			System.err.println(e);
			rollbackTransacao();
		} finally {
			closeTransacao();
		}
		return usuario;
	}
	
	public Usuario delete(Integer id) {
		Usuario usuario = null;
		try {
			usuario = em.find(Usuario.class, id);
			iniciarTransacao();
			em.remove(usuario);
			commitTransacao();
		} catch (Exception e) {
			System.err.println(e);
			rollbackTransacao();
		} finally {
			closeTransacao();
		}
		return usuario;
	}
	
}
