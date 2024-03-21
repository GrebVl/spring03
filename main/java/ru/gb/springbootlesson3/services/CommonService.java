package ru.gb.springbootlesson3.services;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.gb.springbootlesson3.repository.BookRepository;
import ru.gb.springbootlesson3.repository.IssueRepository;
import ru.gb.springbootlesson3.repository.ReaderRepository;

@Slf4j
@RequiredArgsConstructor
@Service
@Data
public class CommonService {
    private final BookRepository bookRepository;
    private final IssueRepository issueRepository;
    private final ReaderRepository readerRepository;
}
