package ru.gb.springbootlesson3.repository;

import org.springframework.stereotype.Repository;
import ru.gb.springbootlesson3.entity.Issue;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class IssueRepository {
    private List<Issue> list = new ArrayList<>();

    public void createIssue(Issue issue){
        list.add(issue);
    }

    public Issue findById(long id){
        return list.stream().filter(e -> e.getId() == id)
                .findFirst()
                .orElse(null);
    }


    public List<Issue> getAllIssue(){
        return list;
    }

    public void deleteIssue(long id){
        list.remove(findById(id));
    }

    public List<Issue> findByReader(long id){
        return list.stream().filter(e -> e.getIdReader() == id)
                .collect(Collectors.toList());
    }
}
