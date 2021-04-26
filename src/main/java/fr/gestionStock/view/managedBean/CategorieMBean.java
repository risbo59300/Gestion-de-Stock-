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

import fr.gestionStock.persistance.entities.Categorie;
import fr.gestionStock.persistance.service.CategorieService;

@ManagedBean
@SessionScoped
@Component
public class CategorieMBean 
{
	private Categorie cat = new Categorie();
	private Categorie selectcat = new Categorie();
	
	@Autowired
	CategorieService categorieService;
	
	public CategorieService getCategorieService() {
		return categorieService;
	}

	public void setCategorieService(CategorieService categorieService) {
		this.categorieService = categorieService;
	}

	private List<Categorie> listCategories = new ArrayList<Categorie>();
	
	private String msg;
	
	/**
	 * @return the cat
	 */
	public Categorie getCat() 
	{
		return cat;
	}

	/**
	 * @param cat the cat to set
	 */
	public void setCat(Categorie cat) 
	{
		this.cat = cat;
	}

	/**
	 * @return the selectcat
	 */
	public Categorie getSelectcat() 
	{
		return selectcat;
	}

	/**
	 * @param selectcat the selectcat to set
	 */
	public void setSelectcat(Categorie selectcat) 
	{
		this.selectcat = selectcat;
	}

	/**
	 * @return the catDaoImpl
	 */
	

	/**
	 * @return the listCategories
	 */
	public List<Categorie> getListCategories() 
	{
		listCategories = categorieService.findAll();
		return listCategories;
	}

	/**
	 * @param listCategories the listCategories to set
	 */
	public void setListCategories(List<Categorie> listCategories) 
	{
		this.listCategories = listCategories;
	}

	public void addCategorie(ActionEvent e) 
	{
		categorieService.add(cat);
		cat = new Categorie();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Ajout categorie effectué avec succès"));
	}
	
	public void  deleteCategorie(ActionEvent e) 
	{
		if (selectcat == null || selectcat.getIdcateg() == new BigDecimal(0))
		{
			System.out.println("Veuillez selectionner une categorie à supprimer!");
			 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Veuillez selectionner une categorie à supprimer!"));
		}
		else
		{
			categorieService.delete(selectcat);
		
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Suppression categorie effectué avec succès"));
		}
	}
	
	public String editCategorie()
	{
		return "showCategorie.xhtml";
	}
	
	public String updateCategorie() 
	{
		categorieService.update(selectcat);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("categorie modifier effectué avec succès"));

		return "showCategorie.xhtml";
		
	}

	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * @param msg the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
