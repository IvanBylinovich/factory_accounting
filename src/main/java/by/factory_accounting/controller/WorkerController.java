package by.factory_accounting.controller;

import by.factory_accounting.entity.accounting.Worker;
import by.factory_accounting.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/worker")
public class WorkerController {

    @Autowired
    WorkerService workerService;

    @GetMapping("/create")
    public ModelAndView createWorker(Model model){
        model.addAttribute("worker", new Worker());
        return new ModelAndView("creationOfWorker");
    }
    @PostMapping("/create")
    public ModelAndView create(Worker worker, Model model){
        try {
            workerService.create(worker);
            model.addAttribute("message","Worker created successfully");
            return new ModelAndView("creationOfWorker");
        } catch (RuntimeException e) {
            model.addAttribute("message", e.getMessage());
            return new ModelAndView("creationOfWorker");
        }
    }

    @GetMapping("/filter")
    public ModelAndView showProductFilter(Model model){
        model.addAttribute("workers", workerService.getAllWorker());
        return new ModelAndView("filterWorker");
    }
}
