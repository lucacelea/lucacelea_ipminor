package ucll.be.todo;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ucll.be.todo.dto.SubTaskDTO;
import ucll.be.todo.dto.TaskDTO;
import ucll.be.todo.service.TaskService;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TaskServiceTest {
    @Autowired
    private TaskService taskService;
    private TaskDTO task,task2,task3;

    @BeforeAll
    public void init(){
        task = new TaskDTO();
        task.setLocalDateTime(LocalDateTime.now());
        task.setDescription("testDescription");
        task.setTitle("testTitle");
        task.setID(1);

        task2 = new TaskDTO();
        task2.setLocalDateTime(LocalDateTime.now());
        task2.setDescription("testDescription2");
        task2.setTitle("testTitle2");
        task2.setID(2);

        taskService.addTask(task);
        taskService.addTask(task2);
    }

    @Test
    public void testGetTasks(){
        // method to be tested
        List<TaskDTO> taskDTOList = taskService.getTasks();

        // checks
        assertNotNull(taskDTOList);
        assertFalse(taskDTOList.isEmpty());
        assertEquals(task.getLocalDateTime(),taskDTOList.get(0).getLocalDateTime());
        assertEquals(task.getDescription(),taskDTOList.get(0).getDescription());
        assertEquals(task.getTitle(),taskDTOList.get(0).getTitle());

        assertEquals(task2.getLocalDateTime(),taskDTOList.get(1).getLocalDateTime());
        assertEquals(task2.getDescription(),taskDTOList.get(1).getDescription());
        assertEquals(task2.getTitle(),taskDTOList.get(1).getTitle());
    }

    @Test
    public void testAddTask(){
        // setup
        task3 = new TaskDTO();
        task3.setLocalDateTime(LocalDateTime.now());
        task3.setDescription("testDescription3");
        task3.setTitle("testTitle3");
        task3.setID(3);
        // method to be tested
        taskService.addTask(task3);

        // checks
        List<TaskDTO> taskDTOList = taskService.getTasks();
        assertEquals(3,taskDTOList.size());
        assertEquals(task3.getLocalDateTime(),taskDTOList.get(2).getLocalDateTime());
        assertEquals(task3.getDescription(),taskDTOList.get(2).getDescription());
        assertEquals(task3.getTitle(),taskDTOList.get(2).getTitle());
    }

    @Test
    public void testGetTaskByID() throws Exception {
        // method to be tested
        TaskDTO testByID = taskService.getTaskByID(task.getID());

        // checks
        assertEquals(task.getID(),testByID.getID());
        assertEquals(task.getLocalDateTime(),testByID.getLocalDateTime());
        assertEquals(task.getDescription(),testByID.getDescription());
        assertEquals(task.getTitle(),testByID.getTitle());
    }

    @Test
    public void testUpdateTask() throws Exception {
        // setup
        TaskDTO testUpdate = new TaskDTO();
        testUpdate.setLocalDateTime(LocalDateTime.now());
        testUpdate.setDescription("test");
        testUpdate.setTitle("test");

        // method to be tested
        taskService.updateTask(testUpdate,task2.getID());

        // checks
        TaskDTO expected = taskService.getTaskByID(task2.getID());

        assertEquals(expected.getLocalDateTime(),testUpdate.getLocalDateTime());
        assertEquals(expected.getDescription(),testUpdate.getDescription());
        assertEquals(expected.getTitle(),testUpdate.getTitle());
    }


    @Test
    public void testAddSubTask() throws Exception {
        // setup
        SubTaskDTO expectedSub = new SubTaskDTO("testSub","testSub");
        TaskDTO taskAddSub = new TaskDTO();
        taskAddSub.setLocalDateTime(LocalDateTime.now());
        taskAddSub.setTitle("testAddSub");
        taskAddSub.setDescription("testAddSub");
        taskAddSub.setID(4);

        taskService.addTask(taskAddSub);

        // method to be tested
        taskService.addSubTask(expectedSub,taskAddSub.getID());

        // checks
        TaskDTO toTest = taskService.getTaskByID(taskAddSub.getID());
        assertEquals(expectedSub.getTitle(),toTest.getSubTasks().get(0).getTitle());
        assertEquals(expectedSub.getDescription(),toTest.getSubTasks().get(0).getDescription());
    }
}
