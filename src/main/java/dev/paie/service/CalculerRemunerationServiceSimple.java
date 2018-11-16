package dev.paie.service;

import java.math.BigDecimal;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.RestoreAction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.paie.entite.BulletinSalaire;
import dev.paie.entite.Grade;
import dev.paie.entite.ResultatCalculRemuneration;
import dev.paie.util.PaieUtils;

@Service
public class CalculerRemunerationServiceSimple implements CalculerRemunerationService{
	
	@Autowired private PaieUtils paieUtils;

	@Override
	public ResultatCalculRemuneration calculer(BulletinSalaire bulletin) {
		ResultatCalculRemuneration result = new ResultatCalculRemuneration();
		
		Grade grade = bulletin.getRemunerationEmploye().getGrade();
		BigDecimal salaireBase = grade.getNbHeuresBase().multiply(grade.getTauxBase());
		BigDecimal salaireBrut = salaireBase.add(bulletin.getPrimeExceptionnelle());
		
		result.setSalaireBrut(paieUtils.formaterBigDecimal(salaireBrut));
		
		
//		result.setSalaireBrut("2683.30");
//		result.setNetAPayer("2088.41");
//		result.setTotalCotisationsPatronales("1096.13");
//		result.setTotalRetenueSalarial("517.08");
//		result.setNetImposable("2166.22");
		
		return result;
	}
	
}
