package pu.master.tms.services;

import java.time.LocalDateTime;
import pu.master.tms.models.dtos.ProjectDto;
import pu.master.tms.models.dtos.TaskDto;
import pu.master.tms.models.dtos.TaskItemDto;
import pu.master.tms.models.dtos.UserDto;
import pu.master.tms.models.enums.ProjectPriority;
import pu.master.tms.models.enums.TaskPriority;
import pu.master.tms.models.enums.TaskStatus;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ProjectService {

    private List<ProjectDto> projects;

    // Simulate the current user for example purposes
    private static final String CURRENT_USER = "Ivan K";

    public ProjectService() {
        this.projects = new ArrayList<>();
        generateDummyProjects();
    }

    private void generateDummyProjects() {
        // Dummy Users
        Set<UserDto> users1 = new LinkedHashSet<>();
        users1.add(new UserDto("Ivan K"));
        users1.add(new UserDto("Alex GG"));

        Set<UserDto> users2 = new LinkedHashSet<>();
        users2.add(new UserDto("Mariya G."));
        users2.add(new UserDto("Yani"));

        Set<UserDto> users3 = new LinkedHashSet<>();
        users3.add(new UserDto("Petar D."));
        users3.add(new UserDto("Anna K."));

        // Dummy Projects
        ProjectDto project1 = new ProjectDto();
        project1.setId(1L);
        project1.setTitle("Идеи");
        project1.setDescription("Тук ще запазвам идеите ми за различните ми програмни проекти.");
        project1.setDateCreated("12/07/2024");
        project1.setDateLastModified("11/07/2024");
        project1.setPriorityLevel(ProjectPriority.Low);
        project1.setProjectOwner("Ivan K");
        project1.setCollaborators(users1);
        project1.setTasks(new ArrayList<>()); // Empty task list

        ProjectDto project2 = new ProjectDto();
        project2.setId(2L);
        project2.setTitle("Домашно по математика");
        project2.setDescription("Трябва да решим задачите от 11 до 20 ст.");
        project2.setDateCreated("12.09.2024");
        project2.setDateDue("02.11.2024");
        project2.setDateLastModified("22.09.2024");
        project2.setPriorityLevel(ProjectPriority.Medium);
        project2.setProjectOwner(CURRENT_USER);
        project2.setCollaborators(users2);
        project2.setTasks(generateDummyTasks1()); // Add tasks

        ProjectDto project3 = new ProjectDto();
        project3.setId(3L);
        project3.setTitle("Проект за нов уебсайт");
        project3.setDescription("Изграждане на нов уебсайт за нашия клиент.");
        project3.setDateCreated("15/08/2024");
        project3.setDateDue("01/12/2024");
        project3.setDateLastModified("23/08/2024");
        project3.setPriorityLevel(ProjectPriority.High);
        project3.setProjectOwner("Mariya G.");
        project3.setCollaborators(users3);
        project3.setTasks(generateDummyTasks2());

        ProjectDto project4 = new ProjectDto();
        project4.setId(4L);
        project4.setTitle("Разработка на мобилно приложение");
        project4.setDescription("Разработка на приложение за проследяване на фитнес дейности.");
        project4.setDateCreated("20/08/2024");
        project4.setDateDue("15/01/2025");
        project4.setDateLastModified("05/09/2024");
        project4.setPriorityLevel(ProjectPriority.High);
        project4.setProjectOwner("Petar D.");
        project4.setCollaborators(users1);
        project4.setTasks(generateDummyTasks3());

        ProjectDto project5 = new ProjectDto();
        project5.setId(5L);
        project5.setTitle("Домашно по физика");
        project5.setDescription("Подготовка на презентации и материали за предстоящата конференция.");
        project5.setDateCreated("01.09.2024");
        project5.setDateDue("30.11.2024");
        project5.setDateLastModified("09.09.2024");
        project5.setPriorityLevel(ProjectPriority.Medium);
        project5.setProjectOwner("Ivan K");
        project5.setCollaborators(users2);
        project5.setTasks(generateDummyTasks4());


        projects.add(project1);
        projects.add(project2);
        projects.add(project3);
        projects.add(project4);
        projects.add(project5);
    }

    private List<TaskDto> generateDummyTasks1() {
        List<TaskDto> tasks = new ArrayList<>();
        TaskDto task1 = new TaskDto(
                        "Завършване на дипломната работа",
                        "Да напиша заключението и да редактирам документите",
                        TaskPriority.Medium,
                        TaskStatus.FINISHED,
                        "03.01.2024 14:00:00",
                        "04.01.2024 16:00:00",
                        LocalDateTime.of(2024, 8, 29, 18, 0),
                        "Мария П.",
                        List.of(new UserDto("Мария П."), new UserDto("Александър"))
        );

        TaskDto task2 = new TaskDto(
                        "Домашно по математика",
                        "Да реша задачите от учебника",
                        TaskPriority.High,
                        TaskStatus.IN_PROGRESS,
                        "01.01.2024 10:00:00",
                        "02.01.2024 12:00:00",
                        LocalDateTime.of(2024, 1, 15, 23, 59),
                        "Иван К.",
                        List.of(new UserDto("Иван К."), new UserDto("Георги")),
                        List.of(
                                        new TaskItemDto("Да реша задачи от раздел 1", false),
                                        new TaskItemDto("Да проверя отговорите", true)
                        )
        );
        tasks.add(task1);
        tasks.add(task2);
        return tasks;
    }

    private List<TaskDto> generateDummyTasks2() {
        List<TaskDto> tasks = new ArrayList<>();
        TaskDto task1 = new TaskDto(
                        "Дизайн на главната страница",
                        "Създаване на дизайн и макет на главната страница.",
                        TaskPriority.High,
                        TaskStatus.IN_PROGRESS,
                        "01.09.2024 10:00:00",
                        "02.09.2024 16:00:00",
                        LocalDateTime.of(2024, 9, 10, 18, 00),
                        "Мариана Т.",
                        List.of(new UserDto("Мариана Т."), new UserDto("Анна К."))
        );

        TaskDto task2 = new TaskDto(
                        "Изграждане на бекенд логика",
                        "Разработка на REST API и база данни.",
                        TaskPriority.High,
                        TaskStatus.DUE,
                        "05.09.2024 09:00:00",
                        "15.09.2024 17:00:00",
                        LocalDateTime.of(2024, 10, 10, 18, 00),
                        "Петър Д.",
                        List.of(new UserDto("Петър Д."), new UserDto("Яни")),
                        List.of(
                                        new TaskItemDto("Изграждане на моделите", false),
                                        new TaskItemDto("Настройка на базата данни", false)
                        )
        );
        tasks.add(task1);
        tasks.add(task2);
        return tasks;
    }

    private List<TaskDto> generateDummyTasks3() {
        List<TaskDto> tasks = new ArrayList<>();
        TaskDto task1 = new TaskDto(
                        "Разработка на потребителски интерфейс",
                        "Изграждане на UI елементи и навигация.",
                        TaskPriority.High,
                        TaskStatus.IN_PROGRESS,
                        "10.09.2024 08:00:00",
                        "15.09.2024 12:00:00",
                        LocalDateTime.of(2024, 9, 30, 17, 00),
                        "Петър Д.",
                        List.of(new UserDto("Петър Д."), new UserDto("Георги"))
        );

        TaskDto task2 = new TaskDto(
                        "Интеграция на API",
                        "Свързване на приложението с външни API за данни.",
                        TaskPriority.Medium,
                        TaskStatus.DUE,
                        "15.09.2024 09:00:00",
                        "20.09.2024 11:00:00",
                        LocalDateTime.of(2024, 9, 25, 16, 00),
                        "Иван К.",
                        List.of(new UserDto("Иван К."), new UserDto("Мариана Т.")),
                        List.of(
                                        new TaskItemDto("Настройка на клиентите", false),
                                        new TaskItemDto("Тестване на свързването", false)
                        )
        );
        tasks.add(task1);
        tasks.add(task2);
        return tasks;
    }

    private List<TaskDto> generateDummyTasks4() {
        List<TaskDto> tasks = new ArrayList<>();
        TaskDto task1 = new TaskDto(
                        "Създаване на презентации",
                        "Подготовка на слайдове за различните сесии.",
                        TaskPriority.Medium,
                        TaskStatus.IN_PROGRESS,
                        "05.10.2024 10:00:00",
                        "15.10.2024 14:00:00",
                        LocalDateTime.of(2024, 10, 20, 16, 00),
                        "Иван К.",
                        List.of(new UserDto("Иван К."), new UserDto("Мария Г."))
        );

        TaskDto task2 = new TaskDto(
                        "Подготвяне на промоционални материали",
                        "Дизайн и печат на рекламни брошури и банери.",
                        TaskPriority.Low,
                        TaskStatus.DUE,
                        "10.10.2024 09:00:00",
                        "20.10.2024 17:00:00",
                        LocalDateTime.of(2024, 10, 30, 18, 00),
                        "Георги",
                        List.of(new UserDto("Георги"), new UserDto("Анна К."))
        );
        tasks.add(task1);
        tasks.add(task2);
        return tasks;
    }

    public List<ProjectDto> findAll() {
        return projects;
    }

    public void save(ProjectDto newProject) {
        if (newProject != null) {
            projects.add(newProject);
        }
    }

    public void deleteTasks(long projectId, Set<TaskDto> tasksToDelete) {
        for (ProjectDto project : projects) {
            if (project.getId() == projectId) {
                project.getTasks().removeAll(tasksToDelete);
                break;
            }
        }
    }

    public void saveTask(long projectId, TaskDto newTask) {
        for (ProjectDto project : projects) {
            if (project.getId() == projectId) {
                project.getTasks().add(newTask);
                break;
            }
        }
    }

    public void addTasks(long projectId, Set<TaskDto> tasksToAdd) {
        for (ProjectDto project : projects) {
            if (project.getId() == projectId) {
                Set<TaskDto> existingTasks = new HashSet<>(project.getTasks());
                existingTasks.addAll(tasksToAdd); // Add new tasks to the existing tasks
                project.setTasks(new ArrayList<>(existingTasks)); // Set the updated list of tasks
                break;
            }
        }
    }

    // New methods for handling the "Mine", "Collab", and "Archived" tabs

    // Method to get projects created by the current user
    public List<ProjectDto> getProjectsCreatedByCurrentUser() {
        return projects.stream()
                       .filter(project -> project.getProjectOwner().equals(CURRENT_USER))
                       .collect(Collectors.toList());
    }

    // Method to get collaborative projects for the current user
    public List<ProjectDto> getCollaborativeProjectsForCurrentUser() {
        return projects.stream()
                       .filter(project -> project.getCollaborators().stream()
                                                 .anyMatch(user -> user.getUsername().equals(CURRENT_USER)))
                       .collect(Collectors.toList());
    }

    // Method to get archived collaborative projects for the current user
    public List<ProjectDto> getArchivedColaborativeForCurrentUser() {
        // For simplicity, let's assume archived projects are those with a "finished" task.
        return projects.stream()
                       .filter(project -> project.getCollaborators().stream()
                                                 .anyMatch(user -> user.getUsername().equals(CURRENT_USER)) &&
                                          project.getTasks().stream().anyMatch(task -> task.getStatus() == TaskStatus.FINISHED))
                       .collect(Collectors.toList());
    }
}
