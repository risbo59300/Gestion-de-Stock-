package fr.gestionStock.repositories;

import org.springframework.data.repository.CrudRepository;

import fr.gestionStock.persistance.entities.Categorie;

//@Repository
public interface CategorieRepository extends CrudRepository<Categorie, Integer>{

	
	
}