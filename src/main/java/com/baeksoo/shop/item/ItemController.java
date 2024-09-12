package com.baeksoo.shop.item;


import com.baeksoo.shop.dto.ItemDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/item")
public class ItemController {
    private final ItemService itemService;

    @GetMapping("")
    public String ItemAll(Model model) {
        var result = itemService.itemFindAll();
//        System.out.println(result.toString());
        model.addAttribute("items", result);
        return "list";
    }

    // 글작성 폼 이동
    @GetMapping("/write")
    public String write() {
        return "write";
    }

    // 글작성
    @PostMapping("")
    public String createItem(@ModelAttribute ItemDto.Request request) {
        var result = itemService.createItem(request);
        return "redirect:/api/item";
    }

    // 아이템 상세 페이지
    @GetMapping("/{id}")
    @ResponseBody
    public String detail(@PathVariable Long id, Model model) throws Exception {
        Optional<ItemEntity> result = itemService.detailItem(id);
        if (result.isPresent()) {
            model.addAttribute("item", result.get());
            return "detail";
        } else {
            return "해당 게시물이 존재 하지 않습니다.";
        }
    }

    // 수정 페이지 이동
    @GetMapping("/edit/{id}")
    public String editP(@PathVariable Long id, Model model) {
        Optional<ItemEntity> result = itemService.detailItem(id);
        if (result.isPresent()) {
            model.addAttribute("data", result.get());
            return "edit";
        } else {
            return "redirect:/api/item";
        }
    }

    // 게시물 수정
    @PostMapping("/edit")
    public String editProc(@RequestParam Long id,
                           @RequestParam String title,
                           @RequestParam Integer price) {
        itemService.editItem(id, title, price);

        return "redirect:/api/item";
    }
}
