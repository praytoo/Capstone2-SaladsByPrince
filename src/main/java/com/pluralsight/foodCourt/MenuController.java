package com.pluralsight.foodCourt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MenuController {

    private final ToppingService toppingService;
    private final CartService cartService;
    private final MenuService menuService;

    @Autowired
    public MenuController(ToppingService toppingService,
                          CartService cartService,
                          MenuService menuService) {
        this.toppingService = toppingService;
        this.cartService = cartService;
        this.menuService = menuService;
    }

    // ----- Salad -----
    @GetMapping("/addSalad")
    public String saladForm(Model model) {
        model.addAttribute("sizes", Size.values());
        model.addAttribute("regularToppings", toppingService.getRegularToppings());
        model.addAttribute("premiumToppings", toppingService.getPremiumToppings());
        return "add-salad";
    }

    @PostMapping("/addSalad")
    public String addSalad(@RequestParam Size size,
                           @RequestParam String green,
                           @RequestParam(required = false) List<String> toppingNames,
                           @RequestParam String dressing) {

        List<Topping> toppings = new ArrayList<>();
        if (toppingNames != null) {
            toppings.addAll(
                    toppingService.getRegularToppings()
                            .stream().filter(t -> toppingNames.contains(t.getName())).toList()
            );
            toppings.addAll(
                    toppingService.getPremiumToppings()
                            .stream().filter(t -> toppingNames.contains(t.getName())).toList()
            );
        }

        Salad salad = new Salad(size, GreenType.valueOf(green), toppings, new Dressing(dressing));
        cartService.addItem(salad);
        return "redirect:/";
    }

    // ----- Pizza -----
    @GetMapping("/addPizza")
    public String pizzaForm(Model model) {
        model.addAttribute("sizes", Size.values());
        model.addAttribute("crusts", List.of("PAN", "DEEPDISH", "THIN"));
        model.addAttribute("regularToppings", toppingService.getRegularToppings());
        model.addAttribute("premiumToppings", toppingService.getPremiumToppings());
        return "add-pizza";
    }

    @PostMapping("/addPizza")
    public String addPizza(@RequestParam Size size,
                           @RequestParam String crust,
                           @RequestParam(required = false) List<String> toppingNames,
                           @RequestParam(defaultValue = "false") boolean stuffedCrust) {

        List<Topping> toppings = new ArrayList<>();
        if (toppingNames != null) {
            toppings.addAll(toppingService.getRegularToppings()
                    .stream().filter(t -> toppingNames.contains(t.getName())).toList());
            toppings.addAll(toppingService.getPremiumToppings()
                    .stream().filter(t -> toppingNames.contains(t.getName())).toList());
        }

        Pizza pizza = new Pizza(size, crust, toppings, stuffedCrust);
        cartService.addItem(pizza);
        return "redirect:/";
    }

    // ----- Sandwich -----
    @GetMapping("/addSandwich")
    public String sandwichForm(Model model) {
        model.addAttribute("sizes", Size.values());
        model.addAttribute("breads", List.of("White", "Wheat", "Sourdough", "Rye"));
        model.addAttribute("regularToppings", toppingService.getRegularToppings());
        model.addAttribute("premiumToppings", toppingService.getPremiumToppings());
        return "add-sandwich";
    }

    @PostMapping("/addSandwich")
    public String addSandwich(@RequestParam Size size,
                              @RequestParam String bread,
                              @RequestParam(required = false) List<String> toppingNames,
                              @RequestParam(defaultValue = "false") boolean toasted) {

        List<Topping> toppings = new ArrayList<>();
        if (toppingNames != null) {
            toppings.addAll(toppingService.getRegularToppings()
                    .stream().filter(t -> toppingNames.contains(t.getName())).toList());
            toppings.addAll(toppingService.getPremiumToppings()
                    .stream().filter(t -> toppingNames.contains(t.getName())).toList());
        }

        Sandwich sandwich = new Sandwich(size, bread, toppings, toasted);
        cartService.addItem(sandwich);
        return "redirect:/";
    }

    // ----- Taco -----
    @GetMapping("/addTaco")
    public String tacoForm(Model model) {
        model.addAttribute("sizes", Size.values());
        model.addAttribute("shells", List.of("Flour Tortilla", "Corn Tortilla"));
        model.addAttribute("regularToppings", toppingService.getRegularToppings());
        model.addAttribute("premiumToppings", toppingService.getPremiumToppings());
        return "add-taco";
    }

    @PostMapping("/addTaco")
    public String addTaco(@RequestParam Size size,
                          @RequestParam String shell,
                          @RequestParam(required = false) List<String> toppingNames,
                          @RequestParam(defaultValue = "false") boolean doubleShell) {

        List<Topping> toppings = new ArrayList<>();
        if (toppingNames != null) {
            toppings.addAll(toppingService.getRegularToppings()
                    .stream().filter(t -> toppingNames.contains(t.getName())).toList());
            toppings.addAll(toppingService.getPremiumToppings()
                    .stream().filter(t -> toppingNames.contains(t.getName())).toList());
        }

        Taco taco = new Taco(size, shell, toppings, doubleShell);
        cartService.addItem(taco);
        return "redirect:/";
    }

    // ----- Cart -----
    @GetMapping("/cart")
    public String viewCart(Model model) {
        model.addAttribute("items", cartService.getItems());
        model.addAttribute("total", cartService.getTotal());
        return "cart";
    }

    @GetMapping("/checkout")
    public String checkout() {
        return "redirect:/receipt";
    }

    // ----- Home / Menu Service -----
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("menuItems", menuService.getAllItems());
        return "home";
    }
}
