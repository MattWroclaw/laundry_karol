package pl.mateusz.forothers.laundry_karol.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.mateusz.forothers.laundry_karol.entities.Customer;
import pl.mateusz.forothers.laundry_karol.entities.Laundry;
import pl.mateusz.forothers.laundry_karol.services.CustomerService;
import pl.mateusz.forothers.laundry_karol.services.LaundryService;
import pl.mateusz.forothers.laundry_karol.utils.EmailSender;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class AdminController {

    private final LaundryService laundryService;
    private final CustomerService customerService;

    private final EmailSender emailSender;

    @GetMapping({"index", "/"})
    public String goIndex(Model model){
        List<Laundry> laundries = laundryService.laundries();
        List<Customer> customers = customerService.customers();
        model.addAttribute("laundries", laundries);
        model.addAttribute("customers", customers);
        return "index";
    }

    @GetMapping("client")
    public String goClient(Model model){
        List<Customer> customers = customerService.customers();
        model.addAttribute("allCustomers", customers);
        model.addAttribute("createCustomer", new Customer());
        return "client-data";
    }

    @PostMapping("/clientForm")
    public String processClient( @ModelAttribute("createCustomer") Customer customerForm){
        customerService.addCustomer(customerForm);
        return "redirect:/index";
    }

    @GetMapping("laundry")
    public String goLaundry(Model model){
        List<Customer> customers = customerService.customers();
        model.addAttribute("allCustomers", customers);
        model.addAttribute("newLaundry", new Laundry());
        return "laundry-data";
    }

    @PostMapping("/laundryForm")
    public String processLaundry( @ModelAttribute("newLaundry") Laundry laundryForm){
        laundryService.addLaundry(laundryForm);
        return "redirect:/index";
    }

    @GetMapping("washing")
    public String goWashing(Model model){
//        Laundry newestLaundry = laundryService.findNewestLaundry();
        List<Laundry> forWashing = laundryService.readyForWashing();
        model.addAttribute("washing", forWashing);
        return "washing";
    }

    @GetMapping("/washingForm/{id}")
    public String processWashing(@PathVariable long id){
       Laundry laundry = laundryService.findById(id);
        laundry.setStartWashing(LocalDateTime.now());
        laundryService.updateLaundryProcess(laundry, "startWashing");
        return "washing";
    }

    @GetMapping("drying")
    public String goDrying(Model model){
       List <Laundry> forDrying = laundryService.readyForDrying();
        model.addAttribute("drying", forDrying);
        return "drying";
    }

    @GetMapping("/drying/{id}")
    public String processDrying(@PathVariable long id){
        Laundry laundry = laundryService.findById(id);
        laundry.setStartDrying(LocalDateTime.now());
        laundryService.updateLaundryProcess(laundry, "startDrying");
        return "drying";
    }

    @GetMapping("finish")
    public String goFinish(Model model){
        List<Laundry> forFinish = laundryService.readyForFinish();
        model.addAttribute("finish", forFinish);
        return "finish";
    }

    @GetMapping("/finishForm/{id}")
    public String processFinish(@PathVariable long id){
        Laundry laundry = laundryService.findById(id);
        laundry.setFinished(LocalDateTime.now());
        laundryService.updateLaundryProcess(laundry, "finished");
        return "finish";
    }

    @GetMapping("/mail")
    public String sendEmail(){
        emailSender.sendEmail_site();
        return "index";
    }
}
