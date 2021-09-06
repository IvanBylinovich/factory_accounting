package by.factory_accounting.controller;

import by.factory_accounting.entity.accounting.Worker;
import by.factory_accounting.entity.dto.WorkerDTO;
import by.factory_accounting.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/worker")
public class WorkerController {

    @Autowired
    WorkerService workerService;

    @GetMapping("/create")
    public ModelAndView createWorker(@ModelAttribute("workerDTO") WorkerDTO workerDTO, Model model) {
        return new ModelAndView("creationOfWorker");
    }

    @PostMapping("/create")
    public ModelAndView create(@ModelAttribute("workerDTO") @Valid WorkerDTO workerDTO, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) return new ModelAndView("creationOfWorker");

        if (workerService.existsByName(workerDTO.getName())) {
            model.addAttribute("message", "Such a worker already exists");
            return new ModelAndView("creationOfWorker");
        }

        workerService.create(new Worker(workerDTO.getName(), workerDTO.getSpecialization()));
        model.addAttribute("message", "Worker created successfully");
        return new ModelAndView("creationOfWorker");
    }

    @GetMapping("/filter")
    public ModelAndView showProductFilter(Model model) {
        model.addAttribute("workers", workerService.getAllWorker());
        return new ModelAndView("filterWorker");
    }
}
