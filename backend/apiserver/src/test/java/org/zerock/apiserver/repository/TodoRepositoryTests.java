package org.zerock.apiserver.repository;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.zerock.apiserver.domain.Todo;

import java.time.LocalDate;
import java.util.Optional;

@SpringBootTest
@Log4j2
public class TodoRepositoryTests {

    @Autowired // 변수 선언처럼 런타임에 자동으로 객체를 생성하고 연결
    private TodoRepository todoRepository;


    @Test
    public void test1() {
        Assertions.assertNotNull(todoRepository);
        log.info(todoRepository.getClass().getName());
    }

    @Test
    public void testInsert() {
        for (int i = 2; i <= 102; i++) {
            Todo todo = Todo.builder()
                    .title("Title" + i)
                    .content("Content" + i)
                    .dueDate(LocalDate.of(2024, 4, 30))
                    .build();

            Todo result = todoRepository.save(todo);
            log.info(result);

        }
    }

    @Test
    public void testRead() {
        Long tno = 1L;

        Optional<Todo> result = todoRepository.findById(tno);

        log.info(result);
    }

    @Test
    public void testUpdate() {
        Long tno = 1L;

        Optional<Todo> result = todoRepository.findById(tno);

        Todo todo = result.orElseThrow();

        todo.changeTitle("Update Title");
        todo.changeContent("Updated Content");

        todoRepository.save(todo);
    }

    @Test
    public void testPaging() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("tno").descending());

        Page<Todo> result = todoRepository.findAll(pageable);

        log.info(result.getContent());

    }
}
