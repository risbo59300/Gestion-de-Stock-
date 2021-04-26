package fr.gestionStock;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import fr.gestionStock.persistance.entities.Categorie;
import fr.gestionStock.persistance.entities.Client;
import fr.gestionStock.persistance.entities.Commande;
import fr.gestionStock.persistance.service.CategorieService;
import fr.gestionStock.persistance.service.ClientService;
import fr.gestionStock.persistance.service.CommandeService;


public class TestGestionStock {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("ApplicationContext.xml");
		
		ClientService clientService = ctx.getBean("clientServiceImpl", ClientService.class);
		
		Client clt = new Client();
		clt.setIdclient(new BigDecimal(17));
		clt.setCiviliteclient("mr");
		clt.setNomclient("Theophile");
		clt.setPrenomclient("romain");
		clt.setTelclient("0023650752673");
		//clientService.add(clt);
		
		CategorieService categService = ctx.getBean("categorieServiceImpl", CategorieService.class);
		
		Categorie ca = new Categorie();
		ca.setIdcateg(new BigDecimal(17));
		ca.setCodecateg("45");
		ca.setLibellecateg("Produit soin");
		
		//categService.add(ca);
		
		System.out.println("======================================================================================");
		System.out.println("Liste des commandes");
		
		CommandeService commandeService = ctx.getBean("commandeServiceImpl", CommandeService.class);
		
		List<Commande> listeCommande = commandeService.findAll();
		
		for (Commande commande : listeCommande) {
			System.out.println("Id commande= "+commande.getIdCommande()+" produit= "+commande.getProduit()+" Client= "+commande.getClient());
		}
		
		
		System.out.println("======================================================================================");
		
		List<Client> listClient = clientService.findAll();
		//List<Client> listClient = clientService.findByPrenomclientOrNomclientOrDatenaissanceclient("Theophile","romain", "19/05/1985");
		
		System.out.println("Voici la liste des client :");
		
		
		
		for (Client client : listClient) {
			System.out.println("Nom client ="+client.getNomclient()+" prenom= "+client.getPrenomclient());
		}
		
		
		
		
		ctx.close();
	}

}
