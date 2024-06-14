package pu.master.core.services;


import java.util.List;
import java.util.Objects;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import pu.master.core.exceptions.ForbiddenActionException;
import pu.master.core.exceptions.TaskNotFoundException;
import pu.master.core.mappers.TaskMapper;
import pu.master.core.repositories.TaskRepository;
import pu.master.core.utils.SecurityUtils;
import pu.master.domain.models.dtos.TaskDto;
import pu.master.domain.models.entities.Task;
import pu.master.domain.models.entities.User;
import pu.master.domain.models.requests.TaskRequest;


@RequiredArgsConstructor
@Service
public class TaskService
{

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskService.class);

    private final TaskRepository taskRepository;

    private final UserService userService;

    private final TaskMapper taskMapper;

    private final SecurityUtils securityUtils;

    private final JavaMailSender mailSender;


    public Task createTask(final TaskRequest taskRequest)
    {
        final Task task = this.taskMapper.mapTaskRequestToTask(taskRequest);
        return createTask(task);
    }

    public Task createTask(final Task task)
    {
        final User taskOwner = this.securityUtils.getCurrentLoggedInUser();

        LOGGER.debug("Adding current logged in user [{}] to task [{}]", taskOwner.getUsername(), task.getTitle());
        task.setOwner(taskOwner);

        return this.taskRepository.save(task);
    }
    public Task updateTask(final TaskRequest taskRequest)
    {
        final Task taskForUpdate = this.taskMapper.mapTaskRequestToTask(taskRequest);
        return updateTask(taskForUpdate);
    }


    public Task updateTask(final Task taskForUpdate)
    {
        final boolean canTaskBeModified = canModifyTask(taskForUpdate);
        if (canTaskBeModified)
        {
            throw new RuntimeException("Task cannot be modified!");
        }

        return this.taskRepository.save(taskForUpdate);
    }

    public Task deleteTask(final TaskRequest taskRequest)
    {
        final Task taskForDelete = this.taskMapper.mapTaskRequestToTask(taskRequest);
        return deleteTask(taskForDelete);
    }

    public Task deleteTask(final Task taskForDelete)
    {
        final boolean canTaskBeModified = canModifyTask(taskForDelete);
        if (canTaskBeModified)
        {
            throw new ForbiddenActionException("Task cannot be modified!");
        }

        this.taskRepository.delete(taskForDelete);

        return taskForDelete;
    }

    public void handleInvitation(final long taskId, final String email, final boolean isAccepted)
    {
        if (isAccepted)
        {
            acceptInvitation(taskId, email);
        }
        else
        {
            declineInvitation(taskId, email);
        }
    }


    private void acceptInvitation(final long taskId, final String email)
    {
        final Task task = getTaskById(taskId);
        final User user = this.userService.getUserByEmail(email);

        LOGGER.debug("User [{}] accepted invitation for task [{}]. Adding the invited user to task's users.",
                     user.getUsername(),
                     task.getTitle());
        task.addUser(user);

        this.taskRepository.save(task);
    }


    private void declineInvitation(long taskId, final String email)
    {
        final Task declinedTask = getTaskById(taskId);
        LOGGER.info("User [{}] declined the invitation to task [{}]", email, declinedTask.getTitle());
    }


    public void inviteUser(final long taskId, final String email, final HttpServletRequest request)
    {
        final Task task = getTaskById(taskId);
        final User invitedUser = this.userService.getUserByEmail(email);

        final String invitationLink = generateInvitationLink(request, task.getId(), invitedUser.getEmail());
        sendInvitationEmail(invitedUser, task, invitationLink);
    }


    private void sendInvitationEmail(final User invited, final Task task, final String link)
    {
        final User inviter = this.securityUtils.getCurrentLoggedInUser();

        final String message = String.format(
                        "<html>" +
                        "<body>" +
                        "<h2>You have been invited to collaborate with</h2>" +
                        "<h3>%s</h3>" +
                        "<h2> on the task: </h2>" +
                        "<h3>%s.</h3>" +
                        "<p>Acceptation link: <a href=\"%s\">%s</a></p>" +
                        "</body>" +
                        "</html>",
                        inviter.getUsername(), task.getTitle(), link, link
        );

        sendTaskInvitationEmail(invited.getEmail(), message);
    }


    private void sendTaskInvitationEmail(final String to, final String message)
    {
        final MimeMessage mimeMessage = mailSender.createMimeMessage();
        try
        {
            final MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setTo(to);
            helper.setSubject("Task Invitation");
            helper.setText(message, true);
            this.mailSender.send(mimeMessage);
        }
        catch (final MessagingException e)
        {
            LOGGER.error("Failed to send email", e);
            throw new RuntimeException("Failed to send email", e);
        }
    }


    private String generateInvitationLink(final HttpServletRequest request, final long taskId, final String email)
    {
        final String domain = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        return domain + "/tasks/" + taskId + "/invitation?email=" + email;
    }


    public Task getTaskById(final long taskId)
    {
        return this.taskRepository.findById(taskId).orElseThrow(() -> {
            LOGGER.error(String.format("Could not find task with id [%d]!", taskId));
            return new TaskNotFoundException(String.format("Task with id [%d] not found!", taskId));
        });
    }


    public List<TaskDto> getTasksByUserId(final long userId)
    {
        final User user = this.userService.getUserById(userId);

        return this.taskRepository.findTasksByUsersId(user.getId())
                                  .stream().map(this.taskMapper::mapTaskToDto)
                                  .toList();
    }


    public List<TaskDto> getTasksByUserEmail(final String userEmail)
    {
        final User user = this.userService.getUserByEmail(userEmail);

        return this.taskRepository.findTasksByUsersEmail(user.getEmail())
                                  .stream().map(this.taskMapper::mapTaskToDto)
                                  .toList();
    }


    public List<TaskDto> getTasksByUserUsername(final String username)
    {
        final User user = this.userService.getUserByUsername(username);

        return this.taskRepository.findTasksByUsersUsername(user.getEmail())
                                  .stream().map(this.taskMapper::mapTaskToDto)
                                  .toList();
    }

    private boolean canModifyTask(final Task task)
    {
        Objects.requireNonNull(task);
        return task.getOwner() == this.securityUtils.getCurrentLoggedInUser()
                        && this.securityUtils.isCurrentLoggedInUserAdmin();
    }


    public List<Task> getTasksByCurrentLoggedInUser()
    {
        final User currentLoggedInUser = this.securityUtils.getCurrentLoggedInUser();

        List<Task> tasks;
        if (this.securityUtils.isCurrentLoggedInUserAdmin())
        {
            tasks = this.taskRepository.findAll();
        }
        else
        {
            tasks = this.taskRepository.findTasksByUsersId(currentLoggedInUser.getId());
        }

        return tasks;
    }
}
