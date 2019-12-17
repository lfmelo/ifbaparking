package model.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import model.bean.Movimento;

public class MovimentoDAO extends DAO{

	public Movimento findById(Integer id) {
		Movimento movimento = null;
		try {
			movimento = em.find(Movimento.class, id);
		} catch (Exception e) {
			System.err.println(e);
		} finally {
			closeTransacao();
		}
		return movimento;
	}
	
	@SuppressWarnings("unchecked")
	public List<Movimento> findAll(boolean fromTelaPrincipal){
		List<Movimento> listaMovimento = null;
		try {
			if(fromTelaPrincipal) {
				listaMovimento = em.createQuery("from Movimento m order by m.data desc").setMaxResults(10).getResultList();
			} else {
				listaMovimento = em.createQuery("from Movimento m order by m.data desc").getResultList();
			}
		} catch (Exception e) {
			System.err.println(e);
		} finally {
			closeTransacao();
		}
		return listaMovimento;
	}
	
	@SuppressWarnings("unchecked")
	public List<Movimento> findByDataPlaca(String dataInicio, String dataFim, String placa){//CAST(data AS DATE)
		List<Movimento> listaMovimento = null;
		try {
			if(dataInicio.trim().length() == 4 && dataFim.trim().length() == 4 && placa.trim().length() == 1) {
				listaMovimento = findAll(false);
			} else {
				String whereData = "";
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				Date inicio = (dataInicio.trim().length() == 4) ? null : sdf.parse(dataInicio);
				Date fim = (dataFim.trim().length() == 4) ? null : sdf.parse(dataFim);
				
				if(inicio != null && fim != null) {
					whereData = "or (cast(m.data as date) between :inicio and :fim) ";
				} else if(inicio != null) {
					whereData = "or cast(m.data as date) >= :inicio ";
				} else if(fim != null) {
					whereData = "or cast(m.data as date) <= :fim ";
				}
				
				Query query = em.createQuery("select m from Movimento m "
												+ "join m.veiculo as v "
												+ "where v.placa = '" + placa +"' " + whereData 
												+ "order by m.data desc "); 
				if(inicio != null)
					query.setParameter("inicio", inicio);
				if(fim!= null)
					query.setParameter("fim", fim);

				listaMovimento = query.getResultList();
			}
		} catch (Exception e) {
			System.err.println(e);
		} finally {
			closeTransacao();
		}
		return listaMovimento;
	}
	
	public Movimento saveOrUpdate(Movimento movimento) {
		try {
			iniciarTransacao();
			if(movimento.getId() == null) {
				em.persist(movimento);
			} else {
				em.merge(movimento);
			}
			commitTransacao();
		} catch (Exception e) {
			System.err.println(e);
			rollbackTransacao();
		} finally {
			closeTransacao();
		}
		VeiculoDAO veiculoDAO = new VeiculoDAO();
		veiculoDAO.updateFromMovimento(movimento.getVeiculo());
		return movimento;
	}
	
}
