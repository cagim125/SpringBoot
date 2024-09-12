package com.baeksoo.shop.item;


import com.baeksoo.shop.dto.ItemDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/item")
public class ItemController {
    private final ItemService itemService;

    @GetMapping("")
    public String ItemAll(Model model) {
        var result = itemService.itemFindAll();

        model.addAttribute("items", result);
        return "list";
    }
    @GetMapping("/write")
    public String write(){
        return "write";
    }

    @PostMapping("")
    public String createItem(@ModelAttribute ItemDto.Request request) {
        var result = itemService.createItem(request);
        return "redirect:/api/item";
    }
}
