package com.pa2.milk.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table
public class Cliente extends AbstractModel<Integer>{

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CLIENTE")
	@SequenceGenerator(name = "SEQ_CLIENTE", sequenceName = "seq_cliente", allocationSize = 1)
	private Integer id;
	
//	@NotNull
//  @Fetch(FetchMode.SELECT)
//  @ElementCollection(fetch = FetchType.EAGER)
//	private List<String> telefone;
	
//	@JsonIgnore
//  @OneToMany(mappedBy = "fazenda")
//  private List<Fazenda> fazenda;
//	
//	@JsonIgnore
//  @OneToMany(mappedBy = "solicitacao")
//  private List<Solicitacao> solicitacao;	

	@Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

	/*
	 * public List<String> getTelefone() { return telefone; }
	 * 
	 * public void setTelefone(List<String> telefone) { this.telefone = telefone; }
	 */

	/*
	 * public List<Fazenda> getFazenda() { return fazenda; }
	 * 
	 * public void setFazenda(List<Fazenda> fazenda) { this.fazenda = fazenda; }
	 * 
	 * public List<Solicitacao> getSolicitacao() { return solicitacao; }
	 * 
	 * public void setSolicitacao(List<Solicitacao> solicitacao) { this.solicitacao
	 * = solicitacao; }
	 */
}
