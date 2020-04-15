package ucll.be.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ucll.be.todo.domain.SubTask;
import ucll.be.todo.domain.Task;

import java.util.List;

public interface SubTaskRepository extends JpaRepository<SubTask,Integer> {
    SubTask findByIDAndTaskID(Integer idS,Integer idT);
    @Override
    SubTask getOne(Integer integer);
    @Override
    List<SubTask> findAll();
    @Override
    <S extends SubTask> S save(S s);
}
