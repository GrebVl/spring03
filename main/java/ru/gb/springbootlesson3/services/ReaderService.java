package ru.gb.springbootlesson3.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.gb.springbootlesson3.controllers.ReaderRequest;
import ru.gb.springbootlesson3.entity.Issue;
import ru.gb.springbootlesson3.entity.Reader;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@RequiredArgsConstructor
@Service
public class ReaderService {
    private final CommonService commonService;

    public Reader createReader(ReaderRequest request){
        if (commonService.getReaderRepository().findByName(request.getReaderName()) != null){
            log.info("Читатель с name существует " + request.getReaderName());
            throw new NoSuchElementException("Читатель с name существует " + request.getReaderName());
        }

        Reader reader = new Reader(request.getReaderName());
        commonService.getReaderRepository().createReader(reader);
        return reader;
    }

    public List<Reader> getAllReaders(){
        return commonService.getReaderRepository().getAllReaders();
    }

    public Reader getIdReader(Long id){
        if (commonService.getReaderRepository().findById(id) == null){
            log.info("Читатель не найден с id " + id);
            throw new NoSuchElementException("Читатель не найден с id " + id);
        }

        return commonService.getReaderRepository().findById(id);
    }

    public void deleteIdReader(Long id){
        if (commonService.getReaderRepository().findById(id) == null){
            log.info("Читатель не найден с id " + id);
            throw new NoSuchElementException("Читатель не найден с id " + id);
        }
        commonService.getReaderRepository().deleteReader(id);
    }

    public List<Issue> getIssue(Long id){
        return commonService.getIssueRepository().findByReader(id);
    }
}
