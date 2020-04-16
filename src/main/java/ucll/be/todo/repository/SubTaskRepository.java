package ucll.be.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ucll.be.todo.domain.SubTask;

public interface SubTaskRepository extends JpaRepository<SubTask,Integer> {
}
