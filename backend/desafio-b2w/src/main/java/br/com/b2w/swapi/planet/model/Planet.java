package br.com.b2w.swapi.planet.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.querydsl.core.annotations.QueryEntity;

@QueryEntity
@Document(collection="planets")
public class Planet {
	@Id
    private String id;
    
	@Indexed(/*unique = true,*/ direction = IndexDirection.ASCENDING)
    private String nome;
	
    private String clima;
    private String terreno;
    private Integer filmes; 
	
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getClima() {
		return clima;
	}
	public void setClima(String clima) {
		this.clima = clima;
	}
	public String getTerreno() {
		return terreno;
	}
	public void setTerreno(String terreno) {
		this.terreno = terreno;
	}
	public Integer getFilmes() {
		return filmes;
	}
	public void setFilmes(Integer filmes) {
		this.filmes = filmes;
	}
}
