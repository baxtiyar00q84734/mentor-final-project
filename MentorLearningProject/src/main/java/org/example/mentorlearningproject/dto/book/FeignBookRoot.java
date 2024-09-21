package org.example.mentorlearningproject.dto.book;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeignBookRoot {
    public String bookTitle;
    public String bookImage;
    public String bookDescription;
    public String bookAuthor;
    public String bookPublisher;
    public String amazonBookUrl;
    public String bookIsbn;
    public int bookRank;
}
