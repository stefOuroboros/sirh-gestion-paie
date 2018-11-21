package dev.paie.web.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import dev.paie.entite.RemunerationEmploye;
import dev.paie.repository.EntrepriseRepository;
import dev.paie.repository.GradeRepository;
import dev.paie.repository.ProfilRemunerationRepository;
import dev.paie.repository.RemunerationEmployeRepository;

@Controller
@RequestMapping("/employes")
public class RemunerationEmployeController {

	@Autowired RemunerationEmployeRepository remunerationEmployeRepos;
	@Autowired EntrepriseRepository entrepriseRepos;
	@Autowired ProfilRemunerationRepository profilRemunerationRepos;
	@Autowired GradeRepository gradeRepos;
	
    // Version générique @RequestMapping(method = RequestMethod.GET, path = "/creer")
    @GetMapping("/creer")
    public ModelAndView creerEmploye() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("employes/creerEmploye");
        RemunerationEmploye employe = new RemunerationEmploye();
//        employe.setMatricule("Veuillez saisir un matricule");
        mv.addObject("employe", employe);
        mv.addObject("listeEntreprises",entrepriseRepos.findAll());
        mv.addObject("listeProfilRemuneration", profilRemunerationRepos.findAll());
        mv.addObject("listeGrades", gradeRepos.findAll());
        return mv;
    }
    @PostMapping("/creer")
    public ModelAndView enregistrerEmployer(@ModelAttribute("employe") RemunerationEmploye remunerationEmploye) {
    	remunerationEmploye.setDateHeureCreation(LocalDateTime.now());
    	remunerationEmployeRepos.save(remunerationEmploye);
		return creerEmploye();
    }
    
    @GetMapping("/liste-employes")
    public ModelAndView afficherEmployes() {
    	ModelAndView mv = new ModelAndView();
    	mv.setViewName("employes/liste-employes");
    	mv.addObject("listeEmployes", remunerationEmployeRepos.findAll());
		return mv;
    }

}
