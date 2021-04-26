package fr.gestionStock.persistance.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.gestionStock.persistance.entities.Categorie;
import fr.gestionStock.repositories.CategorieRepository;

@Service 
@Transactional
public class CategorieServiceImpl implements CategorieService {

	@Autowired
	private CategorieRepository categorieRepository;

	
	public CategorieRepository getCategorieRepository() {
		return categorieRepository;
	}

	public void setCategorieRepository(CategorieRepository categorieRepository) {
		this.categorieRepository = categorieRepository;
	}

	@Override
	public void add(Categorie categorie) {
		// TODO Auto-generated method stub
		categorieRepository.save(categorie);
	}

	@Override
	public List<Categorie> findAll() {
		// TODO Auto-generated method stub
		return (List<Categorie>) categorieRepository.findAll();
	}

	@Override
	public void update(Categorie categorie) {
		// TODO Auto-generated method stub
		categorieRepository.save(categorie);
	}

	@Override
	public void delete(Categorie categorie) {
		// TODO Auto-generated method stub
		categorieRepository.delete(categorie);
	}

	@Override
	public Categorie findById(int categorie) {
		// TODO Auto-generated method stub
		return categorieRepository.findOne(categorie);
	}

}
