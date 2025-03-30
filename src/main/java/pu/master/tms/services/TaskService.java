package pu.master.tms.services;


import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import pu.master.tms.models.dtos.TaskDto;
import pu.master.tms.models.dtos.TaskItemDto;
import pu.master.tms.models.dtos.UserDto;
import pu.master.tms.models.enums.TaskPriority;
import pu.master.tms.models.enums.TaskStatus;


public class TaskService
{

    // Примерни данни
    private final List<TaskDto> allTasks = List.of(
                    new TaskDto(
                                    "Домашно по математика",  // Homework for math
                                    "Да реша задачите от учебника",  // To solve the exercises from the textbook
                                    TaskPriority.High,
                                    TaskStatus.IN_PROGRESS,
                                    "01.01.2024 10:00",
                                    "02.01.2024 12:00",
                                    LocalDateTime.of(2024, 1, 15, 23, 59),
                                    "Иван К.",  // Ivan K.
                                    List.of(new UserDto("Иван К."), new UserDto("Георги")),  // Ivan K., Georgi
                                    List.of(
                                                    new TaskItemDto("Да реша задачи от раздел 1", false),
                                                    // Solve exercises from section 1
                                                    new TaskItemDto("Да проверя отговорите", true)
                                                    // Check the answers
                                    )
                    ),
                    new TaskDto(
                                    "Завършване на дипломната работа",
                                    // Completing the thesis
                                    "Да напиша заключението и да редактирам документите",
                                    // Write the conclusion and edit the documents
                                    TaskPriority.Medium,
                                    TaskStatus.FINISHED,
                                    "03.01.2024 14:00",
                                    "04.01.2024 16:00",
                                    LocalDateTime.of(2024, 8, 29, 18, 0),
                                    "Мария П.",
                                    // Maria P.
                                    List.of(new UserDto("Мария П."), new UserDto("Александър"))
                                    // Maria P., Alexander
                    ),
                    new TaskDto(
                                    "Покупка на подарък",  // Buying a gift
                                    "Да купя подарък за рождения ден",  // Buy a gift for the birthday
                                    TaskPriority.Medium,
                                    TaskStatus.DUE,
                                    "05.01.2024 09:03",
                                    "14.08.2024 10:21",
                                    LocalDateTime.of(2024, 8, 14, 17, 0),
                                    "Иван К."  // Ivan K.
                    ),
                    new TaskDto(
                                    "Подготовка за семинар",
                                    // Seminar preparation
                                    "Да подготвя презентацията и да се запозная със съдържанието",
                                    // Prepare the presentation and familiarize with the content
                                    TaskPriority.Low,
                                    TaskStatus.IN_PROGRESS,
                                    "07.01.2024 11:11",
                                    "30.09.2024 13:09",
                                    LocalDateTime.of(2024, 10, 1, 11, 0),
                                    "Александър",
                                    // Alexander
                                    List.of(new UserDto("Александър"), new UserDto("Иван К."))
                                    // Alexander, Ivan K.
                    ),
                    new TaskDto(
                                    "Почистване на гаража",  // Cleaning the garage
                                    "Да организирам инструментите и да изхвърля ненужните неща",  // Organize the tools and throw away unnecessary items
                                    TaskPriority.Medium,
                                    TaskStatus.IN_PROGRESS,
                                    "10.02.2024 08:30",
                                    "10.02.2024 14:00",
                                    LocalDateTime.of(2024, 2, 10, 18, 0),
                                    "Георги",  // Georgi
                                    List.of(new UserDto("Георги"))
                    ),
                    new TaskDto(
                                    "Подготовка на отчет",  // Preparing a report
                                    "Да събера данните и да оформя отчета",  // Collect the data and format the report
                                    TaskPriority.High,
                                    TaskStatus.DUE,
                                    "18.03.2024 09:45",
                                    "20.03.2024 17:00",
                                    LocalDateTime.of(2024, 3, 20, 16, 30),
                                    "Мария П.",  // Maria P.
                                    List.of(new UserDto("Мария П."), new UserDto("Иван К."))
                    ),
                    new TaskDto(
                                    "Подготовка за празненство",
                                    "Да направя списък с продуктите и да поръчам храната",  // Make a shopping list and order the food
                                    TaskPriority.Medium,
                                    TaskStatus.DUE,
                                    "20.12.2024 11:32",
                                    "31.12.2024 19:00",
                                    LocalDateTime.of(2024, 12, 31, 23, 0),
                                    "Иван К.",  // Ivan K.
                                    List.of(new UserDto("Иван К."))
                    ),
                    new TaskDto(
                                    "Ремонт на велосипед",  // Bike repair
                                    "Да сменя спирачките и да проверя скоростите",  // Replace the brakes and check the gears
                                    TaskPriority.Low,
                                    TaskStatus.IN_PROGRESS,
                                    "15.04.2024 10:04",
                                    "16.04.2024 14:00",
                                    LocalDateTime.of(2024, 4, 16, 18, 0),
                                    "Иван К.",  // Alexander
                                    List.of(new UserDto("Александър"))
                    )
    );


    public List<TaskDto> getTasksCreatedByCurrentUser()
    {
        return allTasks.stream()
                       .filter(task -> task.getUsers().isEmpty())
                       .collect(Collectors.toList());
    }


    public List<TaskDto> getCollaborativeTasksForCurrentUser()
    {
        return allTasks.stream()
                       .filter(task -> !task.getUsers().isEmpty() && !task.getStatus().equals(TaskStatus.FINISHED))
                       .collect(Collectors.toList());
    }


    public List<TaskDto> getArchivedTasksForCurrentUser()
    {
        return allTasks.stream()
                       .filter(task -> TaskStatus.FINISHED.equals(task.getStatus()))
                       .collect(Collectors.toList());
    }


    public List<TaskDto> getAllTasks()
    {
        return allTasks;
    }


    private final List<UserDto> users = List.of(
                    new UserDto("Иван К."),
                    new UserDto("Мария П."),
                    new UserDto("Аlex GG"),
                    new UserDto("Георги")
    );


    public List<UserDto> getUsers()
    {
        return users;
    }

}
