package dev.paie.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import dev.paie.entite.BulletinSalaire;
import dev.paie.repository.PeriodeRepository;
import dev.paie.repository.ProfilRemunerationRepository;

@Controller
@RequestMapping("/bulletins")
public class BulletinSalaireController {
	
	@Autowired ProfilRemunerationRepository profilRemunerationRepos;
	@Autowired PeriodeRepository periodeRepos;
	
	@GetMapping("/creer-bulletin")
    public ModelAndView creerBulletin() {
		 ModelAndView mv = new ModelAndView();
	        mv.setViewName("bulletins/creerBulletin");
	        BulletinSalaire bulletin = new BulletinSalaire();
	        mv.addObject("bulletin", bulletin);
	        mv.addObject("listeProfilRemuneration", profilRemunerationRepos.findAll());
	        mv.addObject("periode", periodeRepos.findAll());
	        return mv;
	}
}
