package ucll.be.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ucll.be.todo.domain.Task;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task,Integer> {
    @Override
    Task getOne(Integer integer);
    @Override
    List<Task> findAll();
    @Override
    <S extends Task> S save(S s);
}
