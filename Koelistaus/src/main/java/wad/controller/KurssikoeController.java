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