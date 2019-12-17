package model.dao;

import java.util.List;

import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;

import model.bean.Motorista;

public class MotoristaDAO extends DAO{

	public Motorista findById(Integer id) {
		Motorista motorista = null;
		try {
			motorista = em.find(Motorista.class, id);
		} catch (Exception e) {
			System.err.println(e);
		} finally {
			closeTransacao();
		}
		return motorista;
	}
	
	@SuppressWarnings("unchecked")
	public List<Motorista> findAll(){
		List<Motorista> listaMotorista = null;
		try {
			listaMotorista = em.createQuery("from Motorista m order by m.nome asc").getResultList();
		} catch (Exception e) {
			System.err.println(e);
		} finally {
			closeTransacao();
		}
		return listaMotorista;
	}
	
	@SuppressWarnings("unchecked")
	public List<Motorista> findByNomeHabilitacao(String nome, String habilitacao){
		List<Motorista> listaMotorista = null;
		try {
			if(StringUtils.isBlank(nome) && StringUtils.isBlank(habilitacao)) {
				listaMotorista = findAll();
			} else {
				String likeNome = StringUtils.isNotBlank(nome) ? " or upper(m.nome) like '%" + nome.toUpperCase() + "%'" : "";
				Query query = em.createQuery("select m from Motorista m where m.hab_num = '" + habilitacao + "' " + likeNome);
				listaMotorista = query.getResultList();
			}
		} catch (Exception e) {
			System.err.println(e);
		} finally {
			closeTransacao();
		}
		return listaMotorista;
	}
	
	public Motorista saveOrUpdate(Motorista motorista) {
		try {
			iniciarTransacao();
			if(motorista.getId() == null) {
				em.persist(motorista);
			} else {
				em.merge(motorista);
			}
			commitTransacao();
		} catch (Exception e) {
			System.err.println(e);
			rollbackTransacao();
		} finally {
			closeTransacao();
		}
		return motorista;
	}
	
	public Motorista delete(Integer id) {
		Motorista motorista = null;
		try {
			motorista = em.find(Motorista.class, id);
			iniciarTransacao();
			em.remove(motorista);
			commitTransacao();
		} catch (Exception e) {
			System.err.println(e);
			rollbackTransacao();
		} finally {
			closeTransacao();
		}
		return motorista;
	}
	
}
