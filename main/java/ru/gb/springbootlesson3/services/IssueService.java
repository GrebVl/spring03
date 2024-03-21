package ru.gb.springbootlesson3.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.gb.springbootlesson3.controllers.IssueRequest;
import ru.gb.springbootlesson3.entity.Issue;
import ru.gb.springbootlesson3.entity.Reader;
import ru.gb.springbootlesson3.repository.BookRepository;
import ru.gb.springbootlesson3.repository.IssueRepository;
import ru.gb.springbootlesson3.repository.ReaderRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@RequiredArgsConstructor
@Service
public class IssueService{

    private final CommonService commonService;

    public Issue createIssue(IssueRequest request){
        if (commonService.getBookRepository().findById(request.getBookId()) == null){
            log.info("Не удалось найти книгу с id " + request.getBookId());
            throw new NoSuchElementException("Не удалось найти книгу с id " + request.getBookId());
        }
        if (commonService.getReaderRepository().findById(request.getReaderId()) == null){
            log.info("Не удалось найти читателя с id " + request.getReaderId());
            throw new NoSuchElementException("Не удалось найти читателя с id " + request.getReaderId());
        }
        if(commonService.getIssueRepository().findByReader(request.getReaderId()).size() > 0){
            throw new RuntimeException("Читатель имеет долг");
        }

        Issue issue = new Issue(request.getReaderId(), request.getBookId());
        commonService.getIssueRepository().createIssue(issue);
        return issue;
    }

    public List<Issue> getAllIssues(){
        return commonService.getIssueRepository().getAllIssue();
    }

    public Issue getIdIssue(Long id){
        if (commonService.getIssueRepository().findById(id) == null){
            log.info("Запись не найдена с id " + id);
            throw new NoSuchElementException("Запись не найдена с id " + id);
        }

        return commonService.getIssueRepository().findById(id);
    }
}
