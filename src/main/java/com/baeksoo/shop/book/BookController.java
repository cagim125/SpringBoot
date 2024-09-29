package com.baeksoo.shop.book;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/book")
public class BookController {

    private final BookRepository bookRepository;
    private final BookService bookService;

    @GetMapping
    public String all(Model model) {
        var result = bookRepository.findLimit100Book();
        model.addAttribute("books", result);
        return "book/list";
    }

    @GetMapping("/detail/{id}")
    public String detailP(@PathVariable Long id, Model model) {
        Book result = bookService.getBook(id);
        model.addAttribute("book", result);
        return "book/detail";
    }

    @GetMapping("/edit/{id}")
    public String editP(@PathVariable Long id, Model model) {
        Book result = bookService.getBook(id);
        model.addAttribute("book", result);
        return "book/edit";
    }

    @PostMapping("/edit")
    public String editProc(@ModelAttribute RequestDto request, Model model) {
        Book result = bookService.editBook(request);
        model.addAttribute("book", result);
        return "redirect:/api/book/edit/" + result.getId();
    }

    @DeleteMapping("/delete")
    @ResponseBody
    public ResponseEntity<String> deleteBook(@RequestParam Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.status(200).body("Book deleted");
    }

    @PostMapping("/search")
    public String searchBook(@RequestParam String keyword,
                             @RequestParam(value = "page", defaultValue = "0") Integer page,
                             Model model) {
        Pageable pageable = PageRequest.of(page, 20);
        var result = bookService.searchBook(keyword, pageable);

        System.out.println(keyword);

        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        model.addAttribute("books", result.getContent());
        model.addAttribute("hasNext", result.hasNext());

        return "book/search";
    }

    // AJAX 요청을 통한 더보기 처리
    @GetMapping("/load-more")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> loadMoreItems(
            @RequestParam("keyword") String keyword,
            @RequestParam("page") int page) {

        Pageable pageable = PageRequest.of(page, 20); // 페이지당 10개 아이템
        Slice<Book> slice = bookService.searchBook(keyword, pageable);

        Map<String, Object> response = new HashMap<>();
        response.put("books", slice.getContent());
        response.put("hasNext", slice.hasNext());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
