package fr.gestionStock.view.managedBean;

import java.awt.event.ActionEvent;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import fr.gestionStock.persistance.entities.Utilisateur;
import fr.gestionStock.persistance.service.UtilisateurService;


@ManagedBean
@SessionScoped
@Component
public class UserMBean 
{
	
	private String username;
	private String pwd;
	
	private String message;
	
	private Utilisateur user = new Utilisateur() ;
	private Utilisateur selectuser = new Utilisateur();
	
	@Autowired
	UtilisateurService utilisateurService;
	
	public UtilisateurService getUtilisateurService()
	{
		return utilisateurService;
	}

	public void setUtilisateurService(UtilisateurService utilisateurService) 
	{
		this.utilisateurService = utilisateurService;
	}

	private List<Utilisateur> listUsers = new ArrayList<Utilisateur>();
	private String menu;
	
	public String getMenu()
	{
		if(user.getRole().equals("admin"))
			menu="menuHNordAdmin.xhtml";
		
		else
		{
			menu="menuHNordUser.xhtml";
		}
		return menu;
	}

	public void setMenu(String menu) 
	{
		this.menu = menu;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username) 
	{
		this.username = username;
	}

	public String getPwd() 
	{
		return pwd;
	}

	public void setPwd(String pwd) 
	{
		this.pwd = pwd;
	}
	
	public Utilisateur getUser()
	{
		return user;
	}

	public void setUser(Utilisateur user)
	{
		this.user = user;
	}

	public Utilisateur getSelectuser() 
	{
		return selectuser;
	}

	public void setSelectuser(Utilisateur selectuser)
	{
		this.selectuser = selectuser;
	}

	public List<Utilisateur> getListUsers() 
	{
		listUsers = utilisateurService.findAll();
		return listUsers;
	}

	public void setListUsers(List<Utilisateur> listUsers) 
	{
		this.listUsers = listUsers;
	}
	
	public String getMessage()
	{
		return message;
	}
	
	public void setMessage(String message) 
	{
		this.message = message;
	}
	
	public void addUser(ActionEvent e) 
	{
		utilisateurService.add(user);
		user = new Utilisateur();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Ajout utilisateur effectu� avec succ�s"));
	}
	
	public void deleteUser(ActionEvent e) 
	{
		if (selectuser == null || selectuser.getIdUtilisateur() == new BigDecimal(0))
		{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Veuillez selectionner un utilisateur à supprimer!"));
		}
		else
		{
			utilisateurService.delete(selectuser);
		
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Suppression utilisateur effectu� avec succ�s"));
		}
	}
	
	public String editUser() 
	{
		return "modifyUser.xhtml";
	}
	
	public String updateUser() 
	{
		utilisateurService.update(selectuser);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Utilisateur modifié avec succès"));

		return "showUsers.xhtml";
	}
	
	public String login()
	{
		
			if (utilisateurService.findByUsernameAndPassword(username, pwd).size() >0)
			{
				user=utilisateurService.findByUsernameAndPassword(username, pwd).get(0);
				System.out.println("user role="+user.getRole());
				if ("admin".equals(user.getRole())) 
				{
					System.out.println("acceuil admin");
					return "AccueilAdmin.xhtml";
				} 
				else 
				{
					return "AccueilUser.xhtml";
				}
			} 
			else
			{
				FacesContext.getCurrentInstance().addMessage(null, 
				new FacesMessage(FacesMessage.SEVERITY_ERROR, "Attention", "Utilisateur inexistant"));
			}
			return "login.xhtml";
	}	
	
	
	
	
}
