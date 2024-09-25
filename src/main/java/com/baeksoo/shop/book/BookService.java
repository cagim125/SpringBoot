package com.baeksoo.shop.book;

import com.baeksoo.shop.admin.RequestDto;
import com.baeksoo.shop.category.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;

    public List<Book> findLimit30() {
        return bookRepository.findLimit30Book();
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
