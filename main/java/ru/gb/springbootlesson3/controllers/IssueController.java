package ru.gb.springbootlesson3.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.springbootlesson3.entity.Issue;
import ru.gb.springbootlesson3.entity.Reader;
import ru.gb.springbootlesson3.services.IssueService;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@RestController
@RequestMapping("issue")
@RequiredArgsConstructor
public class IssueController {

    @Autowired
    private IssueService service;



    /*
        GET - получение записей
        POST - создание записей
        PUT - изменение записей
        DELETE - запрос на удаление ресурса
     */

    @PostMapping
    public ResponseEntity<Issue> issueBook(@RequestBody IssueRequest issueRequest) {
        log.info("Поступил запрос на выдачу: readerId={}, bookId={}"
                , issueRequest.getReaderId(), issueRequest.getBookId());

        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(service.createIssue(issueRequest));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }


    @GetMapping("/all")
    public List<Issue> getAllIssues() {
        log.info("Поступил запрос на отображение списка issue");
        return service.getAllIssues();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Issue> getIdIssue(@PathVariable Long id) {
        log.info("Поступил запрос на получение описания записи " + id);
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.getIdIssue(id));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
