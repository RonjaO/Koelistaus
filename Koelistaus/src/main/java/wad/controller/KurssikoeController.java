package wad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import javax.validation.Valid;
import java.util.List;
import java.util.Date;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import wad.repository.KurssikoeRepository;
import wad.domain.Kurssikoe;

@Controller
@RequestMapping("*")
public class KurssikoeController {
    
    @Autowired
    private KurssikoeRepository kurssikoeRepository;

    @ModelAttribute
    public Kurssikoe getKurssikoe() {
        return new Kurssikoe();
    }

    @RequestMapping(method=RequestMethod.GET)
    public String view(Model model) {
        model.addAttribute("kokeet", kurssikoeRepository.findAll());
        
        return "kurssikokeet";
    }
    
    @RequestMapping(value="/kurssikokeet/haku", method=RequestMethod.GET)
    public String etsi(Model model,
            @RequestParam(required=false) String kurssinNimi,
            @RequestParam(required=false) String mista,
            @RequestParam(required=false) String mihin) {
        List<Kurssikoe> hakutulokset = new ArrayList<Kurssikoe>();
        hakutulokset.addAll(kurssikoeRepository.findByKurssinNimi(kurssinNimi));
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        
        if (mista.equals("") && mihin.equals("")) {
            model.addAttribute(hakutulokset);
            return "kurssikokeet";
        } else if (mista == null) {
            mista = "01.01.20000";
        } else if (mihin == null) {
            mihin = "31.12.2025";
        }
        Date mistaDate = null;
        Date mihinDate = null;
        
        try {
            mistaDate = sdf.parse(mista);
            mihinDate = sdf.parse(mihin);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        for (Kurssikoe koe : kurssikoeRepository.findAll()) {
            if (koe.getAika() == mistaDate || koe.getAika() == mihinDate || (koe.getAika().after(mistaDate) && koe.getAika().before(mihinDate))) {
                hakutulokset.add(koe);
            }
        }
        
        model.addAttribute("hakutulokset", hakutulokset);
        
        return "kurssikokeet";
    }
    
    @Secured("ROLE_ADMIN")
    @RequestMapping(value="/kurssikokeet/post", method=RequestMethod.POST)
    public String create(@Valid @ModelAttribute Kurssikoe kurssikoe,
            BindingResult bindingResult) {
                if (bindingResult.hasErrors()) {
                    return "kurssikokeet";
                }
        kurssikoeRepository.save(kurssikoe);
        return "redirect:/kurssikokeet";
    }
    
    @Secured("ROLE_ADMIN")
    @RequestMapping(value="/kurssikokeet/{id}", method=RequestMethod.DELETE)
    public String delete(@PathVariable("id") Long id) {
        kurssikoeRepository.delete(id);
        
        return "redirect:/kurssikokeet";
    }
        
}