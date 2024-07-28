package com.springboot.todo_app;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {
    private List<String> items = new ArrayList<>();

    HomeController() {
        items.add("Self Made");
    }

    @RequestMapping("/")
    @ResponseBody
    public String display() {
        return "Heloooooo";
    }

    @GetMapping("/home")
    public String html(Model model) {
        model.addAttribute("message", "Hello, Welcome to Notes");
        model.addAttribute("items", items);
        model.addAttribute("itemForm", new ItemForm());

        return "home";
    }

    @PostMapping("/submit")
    public String submit(@ModelAttribute ItemForm itemForm, Model model) {
        items.add(itemForm.getItem());
        return "redirect:/home";
    }

    @GetMapping("/edit/{index}")
    @ResponseBody
    private String edit(@PathVariable int index, Model model) {
        ItemForm itemForm = new ItemForm();
        itemForm.setIndex(index);
        itemForm.setItem(items.get(index));
        System.out.println("items: " + itemForm);
        model.addAttribute("itemForm", itemForm);
        return "home";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute ItemForm itemForm) {
        items.set(itemForm.getIndex(), itemForm.getItem());
        return "redirect:/home";
    }

    public static class ItemForm {
        private String item;
        private int index;

        public String getItem() {
            return item;
        }

        public void setItem(String item) {
            this.item = item;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }
    }
}
