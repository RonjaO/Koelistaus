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
import wad.repository.ErilliskoeRepository;
import wad.domain.Erilliskoe;

@Controller
@RequestMapping("/erilliskokeet")
public class ErilliskoeController {
    
    @Autowired
    private ErilliskoeRepository erilliskoeRepository;


    @ModelAttribute
    public Erilliskoe getErilliskoe() {
        return new Erilliskoe();
    }

    @RequestMapping(method=RequestMethod.GET)
    public String view(Model model) {
        model.addAttribute("kokeet", erilliskoeRepository.findAll());
        
        return "erilliskokeet";
    }
    
    @Secured("ROLE_ADMIN")
    @RequestMapping(value="/post", method=RequestMethod.POST)
    public String create(@Valid @ModelAttribute Erilliskoe erilliskoe,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "erilliskokeet";
        }
        erilliskoeRepository.save(erilliskoe);
        return "redirect:/erilliskokeet";
    }
    
    @Secured("ROLE_ADMIN")
    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public String delete(@PathVariable("id") Long id) {
        erilliskoeRepository.delete(id);
        
        return "redirect:/erilliskokeet";
    }
        
}