package com.baeksoo.shop.book;

import com.baeksoo.shop.admin.RequestDto;
import com.baeksoo.shop.category.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;

    public Page<Book> findByIdDescPage() {
        Pageable pageable = PageRequest.of(0, 10);
        return bookRepository.findByIdOrderByDesc(pageable);
    }

    public Page<Book> findPage(Integer page) {
       return bookRepository.findByIdOrderByDesc(PageRequest.of(page - 1, 20));
    }

    public Book Detail(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    public void saveBook(RequestDto requestDto) {
       var category = categoryRepository.findById(requestDto.getCategoryId());

        Book book = Book.builder()
                .title(requestDto.getTitle())
                .author(requestDto.getAuthor())
                .price(requestDto.getPrice())
                .category(category.get())
                .build();
        try {
            bookRepository.save(book);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
