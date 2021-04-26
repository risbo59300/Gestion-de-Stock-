package fr.gestionStock.view.managedBean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.gestionStock.persistance.entities.Produit;
import fr.gestionStock.persistance.service.ProduitService;

@ManagedBean
@SessionScoped
@Component
public class ProduitMBean 
{
	private  Produit prod = new  Produit();
	private  Produit selectprod = new  Produit();
	
	@Autowired
	ProduitService produitService;
	
	
	public ProduitService getProduitService() {
		return produitService;
	}

	public void setProduitService(ProduitService produitService) {
		this.produitService = produitService;
	}

	private List< Produit> listProduits = new ArrayList< Produit>();
	
	/**
	 * @return the prod
	 */
	public Produit getProd() {
		return prod;
	}

	/**
	 * @param com the prod to set
	 */
	public void setProd(Produit prod) {
		this.prod = prod;
	}

	/**
	 * @return the selectprod
	 */
	public Produit getSelectprod() {
		return selectprod;
	}

	/**
	 * @param selectprod the selectprod to set
	 */
	public void setSelectprod(Produit selectprod) {
		this.selectprod = selectprod;
	}

	/**
	 * @return the listProduits
	 */
	public List<Produit> getListProduits() {
		listProduits = produitService.findAll();
		return listProduits;
	}

	/**
	 * @param listProduits the listProduits to set
	 */
	public void setListProduits(List<Produit> listProduits) {
		this.listProduits = listProduits;
	}

	public void addProduit(ActionEvent e) 
	{
		produitService.add(prod);
		prod = new Produit();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Ajout produit effectué avec succès"));
	}
	
	public void deleteProduit(ActionEvent e) 
	{
		if (selectprod == null || selectprod.getIdproduit() == new BigDecimal(0))
		{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Veuillez selectionner un produit à supprimer!"));
		}
		else
		{
			produitService.delete(selectprod);
		
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Suppression produit effectué avec succès"));
		}
	}
	
	public String editProduit()
	{
		return "showProduit.xhtml";
	}
	
	public String updateProduit() 
	{
		produitService.update(selectprod);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("produit modifier effectué avec succès"));

		return "showProduit.xhtml";
		
	}
}
