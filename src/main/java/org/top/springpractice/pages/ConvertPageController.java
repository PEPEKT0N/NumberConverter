package org.top.springpractice.pages;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.top.springpractice.converter.DigitConverter;

import java.util.List;

@Controller
@RequestMapping("convert")
public class ConvertPageController {
    private final DigitConverter digitConverter;

    public ConvertPageController(DigitConverter digitConverter) {
        this.digitConverter = digitConverter;
    }

    @GetMapping
    public String getConvertPage(Model model) {
        List<String> numberSystems = digitConverter.supportedNumberSystem();
        model.addAttribute("numberSystems", numberSystems);
        Object from = model.getAttribute("from");
        if (from == null) {
            model.addAttribute("from", numberSystems.getFirst());
        }
        Object to = model.getAttribute("to");
        if (to == null) {
            model.addAttribute("to", numberSystems.getFirst());
        }
        Object value = model.getAttribute("value");
        if (value == null) {
            model.addAttribute("value", "1");
        }
        Object result = model.getAttribute("result");
        if (result == null) {
            model.addAttribute("result", digitConverter.convert(numberSystems.getFirst(), numberSystems.getFirst(), "1"));
        }

        return "converter-form";
    }

    @PostMapping
    public String postConvertPage(
            @RequestParam String from,
            @RequestParam String to,
            @RequestParam String value,
            RedirectAttributes ra) {

        String result = this.digitConverter.convert(from, to, value);

        ra.addFlashAttribute("result", result);
        ra.addFlashAttribute("from", from);
        ra.addFlashAttribute("to", to);
        ra.addFlashAttribute("value", value);

        return "redirect:/convert";
    }
}
