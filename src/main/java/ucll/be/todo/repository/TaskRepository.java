package ucll.be.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ucll.be.todo.domain.Task;

public interface TaskRepository extends JpaRepository<Task,Integer> {
}
