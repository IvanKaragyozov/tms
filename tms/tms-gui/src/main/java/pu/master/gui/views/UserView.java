package pu.master.gui.views;


import java.util.List;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import pu.master.core.services.UserService;
import pu.master.domain.models.dtos.UserDto;


@Route(value = "users", layout = MainLayout.class)
@PageTitle("Users | Task Management System")
public class UserView extends VerticalLayout
{

    private final UserService userService;
    private final Grid<UserDto> grid;

    public UserView(UserService userService) {
        this.userService = userService;
        this.grid = new Grid<>(UserDto.class);

        addClassName("user-view");
        setSizeFull();

        configureGrid();
        add(grid);

        updateList();
    }

    private void configureGrid() {
        grid.setSizeFull();
        grid.setColumns("id", "username", "email", "firstName", "lastName", "isActive");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }

    private void updateList() {
        List<UserDto> users = userService.getAllUserDtos();
        grid.setItems(users);
    }
}