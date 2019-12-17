package model.dao;

import java.util.List;

import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;

import model.bean.Veiculo;

public class VeiculoDAO extends DAO{

	public Veiculo findById(Integer id) {
		Veiculo veiculo = null;
		try {
			veiculo = em.find(Veiculo.class, id);
		} catch (Exception e) {
			System.err.println(e);
		} finally {
			closeTransacao();
		}
		return veiculo;
	}
	
	@SuppressWarnings("unchecked")
	public List<Veiculo> findAll(){
		List<Veiculo> listaVeiculo = null;
		try {
			listaVeiculo = em.createQuery("from Veiculo v").getResultList();
		} catch (Exception e) {
			System.err.println(e);
		} finally {
			closeTransacao();
		}
		return listaVeiculo;
	}
	
	@SuppressWarnings("unchecked")
	public List<Veiculo> findByModeloFabricantePlaca(String modelo, String fabricante, String placa){
		List<Veiculo> listaVeiculo = null;
		try {
			if(StringUtils.isBlank(modelo) && StringUtils.isBlank(fabricante) && placa.trim().length() == 1) {
				listaVeiculo = findAll();
			} else {
				String likeModelo = StringUtils.isNotBlank(modelo) ? " or upper(v.modelo) like '%" + modelo.toUpperCase() + "%'" : "";
				String likeFabricante = StringUtils.isNotBlank(fabricante) ? " or upper(v.fabricante) like '%" + fabricante.toUpperCase() + "%'" : "";
				Query query = em.createQuery("select v from Veiculo v where v.placa = '" + placa + "' " + likeModelo + likeFabricante);
				listaVeiculo = query.getResultList();
			}
		} catch (Exception e) {
			System.err.println(e);
		} finally {
			closeTransacao();
		}
		return listaVeiculo;
	}
	
	public Veiculo saveOrUpdate(Veiculo veiculo) {
		try {
			iniciarTransacao();
			if(veiculo.getId() == null) {
				em.persist(veiculo);
			} else {
				em.merge(veiculo);
			}
			commitTransacao();
		} catch (Exception e) {
			System.err.println(e);
			rollbackTransacao();
		} finally {
			closeTransacao();
		}
		return veiculo;
	}
	
	public int updateFromMovimento(Veiculo veiculo) {
		int result = 0;
		try {
			Query query = em.createQuery("update Veiculo v set local = :local");
			query.setParameter("local", !veiculo.getLocal());
			iniciarTransacao();
			result = query.executeUpdate();
			commitTransacao();
		} catch (Exception e) {
			System.err.println(e);
		} finally {
			closeTransacao();
		}
		return result;
	}
	
	public Veiculo delete(Integer id) {
		Veiculo veiculo = null;
		try {
			veiculo = em.find(Veiculo.class, id);
			iniciarTransacao();
			em.remove(veiculo);
			commitTransacao();
		} catch (Exception e) {
			System.err.println(e);
			rollbackTransacao();
		} finally {
			closeTransacao();
		}
		return veiculo;
	}
	
	public Veiculo findByEtiqueta(String etiqueta) {
		Veiculo veiculo = null;
		try {
			if(StringUtils.isNotBlank(etiqueta))
				veiculo = (Veiculo) em.createQuery("from Veiculo v where trim(v.etiqueta_id) = '" + etiqueta.trim() + "'").getSingleResult();
		} catch (Exception e) {
			System.err.println(e);
		} finally {
			closeTransacao();
		}
		return veiculo;
	}
	
}
