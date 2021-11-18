package br.com.fiap.ods.springbootdbeods.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.fiap.ods.springbootdbeods.model.Ongs;
import br.com.fiap.ods.springbootdbeods.service.OngsService;

@Controller
public class OngsController {

    @Autowired
    private OngsService ongsService;

    // display list of Ongs
    @GetMapping("/")
    public String viewHomePage(Model model) {
        model.addAttribute("listOngs", ongsService.getAllOngs());
        return "index";
    }

    @GetMapping("/showNewOngForm")
    public String showNewOngForm(Model model) {
        // create model attribute to bind form data
        Ongs ongs = new Ongs();
        model.addAttribute("ong", ongs);
        return "new_ong";
    }

    @PostMapping("/saveOng")
    public String saveOng(@ModelAttribute("ong") Ongs ongs) {
        // save Ong to database
        ongsService.saveOngs(ongs);
        return "redirect:/";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {

        // get Ong from the service
        Ongs ongs = ongsService.getOngsById(id);

        // set Ong as a model attribute to pre-populate the form
        model.addAttribute("ong", ongs);
        return "update_ong";
    }

    @GetMapping("/deleteOng/{id}")
    public String deleteOng(@PathVariable(value = "id") long id) {

        // call delete Ong method
        this.ongsService.deleteOngsById(id);
        return "redirect:/";
    }

    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable (value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {
        int pageSize = 5;

        Page<Ongs> page = ongsService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Ongs> listOngs = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listOngs", listOngs);
        return "index";
    }
}
