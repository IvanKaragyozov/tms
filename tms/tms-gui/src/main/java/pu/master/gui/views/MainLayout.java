package pu.master.gui.views;


import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.contextmenu.ContextMenu;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Footer;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.theme.lumo.LumoUtility;
import java.time.LocalDate;
import pu.master.domain.models.dtos.UserDto;
import pu.master.gui.views.home.HomeView;
import pu.master.gui.views.projects.ProjectsView;
import pu.master.gui.views.registration.RegistrationDialog;
import pu.master.gui.views.tasks.TasksView;


/**
 * The main view is a top-level placeholder for other views.
 */
public class MainLayout extends AppLayout {

    private H1 viewTitle;
    private HorizontalLayout headerLayout;
    private Avatar avatar;
    private Button registerButton;
    private SideNav nav;

    public MainLayout() {
        setPrimarySection(Section.DRAWER);
        addDrawerContent();
        addHeaderContent();
        updateNavigationVisibility(isLoggedIn());
    }

    private void addHeaderContent() {
        DrawerToggle toggle = new DrawerToggle();
        toggle.setAriaLabel("Menu toggle");

        viewTitle = new H1();
        viewTitle.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);

        avatar = new Avatar();
        avatar.setName("IK");
        avatar.setAbbreviation("IK");

        ContextMenu avatarMenu = new ContextMenu(avatar);
        avatarMenu.setOpenOnClick(true);
        avatarMenu.addItem("Profile", e -> openProfileDialog());
        avatarMenu.addItem("Logout", e -> handleLogout());

        headerLayout = new HorizontalLayout(toggle, viewTitle, avatar);
        headerLayout.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        headerLayout.expand(viewTitle);
        headerLayout.setWidthFull();
        headerLayout.setSpacing(true);
        headerLayout.getStyle().set("padding-right", "var(--lumo-space-m)");

        addToNavbar(headerLayout);
    }

    private void openProfileDialog() {
        // Fetch current user info (replace with actual user fetching logic)
        UserDto currentUser = getCurrentUser();

        Dialog dialog = new Dialog();
        dialog.setWidth("400px");

        TextField usernameField = new TextField("Username");
        usernameField.setValue(currentUser.getUsername());
        usernameField.setReadOnly(true);
        usernameField.setRequired(true);

        TextField firstNameField = new TextField("First Name");
        firstNameField.setValue(currentUser.getFirstName());
        firstNameField.setReadOnly(true);

        TextField lastNameField = new TextField("Last Name");
        lastNameField.setValue(currentUser.getLastName());
        lastNameField.setReadOnly(true);

        EmailField emailField = new EmailField("Email");
        emailField.setValue(currentUser.getEmail());
        emailField.setReadOnly(true);

        TextField phoneNumberField = new TextField("Phone Number");
        phoneNumberField.setValue(currentUser.getPhoneNumber());
        phoneNumberField.setReadOnly(true);

        TextField dateCreatedAtField = new TextField("Date Created At");
        dateCreatedAtField.setValue(currentUser.getDateCreatedAt().toString());
        dateCreatedAtField.setReadOnly(true);

        TextField dateLastModifiedAtField = new TextField("Last Modified At");
        dateLastModifiedAtField.setValue(currentUser.getDateLastModifiedAt().toString());
        dateLastModifiedAtField.setReadOnly(true);

        Button editButton = new Button(VaadinIcon.EDIT.create());
        editButton.addClickListener(e -> {
            boolean isEditing = !firstNameField.isReadOnly();
            if (isEditing) {
                editButton.setIcon(VaadinIcon.EDIT.create());
                firstNameField.setReadOnly(true);
                lastNameField.setReadOnly(true);
                emailField.setReadOnly(true);
                phoneNumberField.setReadOnly(true);
            } else {
                editButton.setIcon(VaadinIcon.CHECK.create());
                firstNameField.setReadOnly(false);
                lastNameField.setReadOnly(false);
                emailField.setReadOnly(false);
                phoneNumberField.setReadOnly(false);
            }
        });

        Button deleteButton = new Button(VaadinIcon.TRASH.create(), event -> {
            // Create a confirmation dialog
            Dialog confirmationDialog = new Dialog();
            confirmationDialog.setHeaderTitle("Confirm Deletion");

            Span message = new Span("Are you sure you want to delete your account?");
            Button confirmButton = new Button("Delete", VaadinIcon.TRASH.create(), confirmEvent -> {
                // Implement delete logic here (e.g., call a service to delete the user)
                Notification.show("User deleted");
                confirmationDialog.close();
                dialog.close();
            });
            confirmButton.addThemeVariants(ButtonVariant.LUMO_ERROR);

            Button cancelButton = new Button("Cancel", cancelEvent -> confirmationDialog.close());

            HorizontalLayout confirmationButtons = new HorizontalLayout(cancelButton, confirmButton);
            confirmationButtons.setWidthFull();
            confirmationButtons.setJustifyContentMode(FlexComponent.JustifyContentMode.END); // Aligns cancel to the right
            confirmationDialog.add(new VerticalLayout(message, confirmationButtons));
            confirmationDialog.open();
        });

        HorizontalLayout buttonLayout = new HorizontalLayout(editButton, deleteButton);
        buttonLayout.setWidthFull();
        buttonLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN); // Edit button on left, delete on right

        FormLayout formLayout = new FormLayout(
                        usernameField,
                        firstNameField,
                        lastNameField,
                        emailField,
                        phoneNumberField,
                        dateCreatedAtField,
                        dateLastModifiedAtField
        );
        formLayout.setResponsiveSteps(
                        new FormLayout.ResponsiveStep("0", 1)
        );

        VerticalLayout dialogLayout = new VerticalLayout(formLayout, buttonLayout);
        dialogLayout.setSizeFull();
        dialog.add(dialogLayout);
        dialog.open();
    }


    private void handleLogout() {
        // Simulate logout by removing avatar and showing register button
        removeAvatarFromHeader();
        showRegisterButton();
        updateNavigationVisibility(false); // Hide tasks and projects
        getUI().ifPresent(ui -> ui.navigate(HomeView.class));
    }

    private void removeAvatarFromHeader() {
        headerLayout.remove(avatar);
    }

    private void showRegisterButton() {
        registerButton = new Button("Register", event -> openRegisterDialog());
        headerLayout.add(registerButton);
    }

    private void updateNavigationVisibility(boolean isLoggedIn) {
        nav.getChildren().forEach(component -> {
            if (component instanceof SideNavItem) {
                SideNavItem item = (SideNavItem) component;
                String label = item.getLabel();
                // Hide "Tasks" and "Projects" if not logged in
                if ("Tasks".equals(label) || "Projects".equals(label)) {
                    item.setVisible(isLoggedIn);
                }
            }
        });
    }

    private boolean isLoggedIn() {
        // Implement your logic here to determine if the user is logged in
        // For now, we'll assume the user is logged out if the avatar is not visible
        return avatar.isVisible();
    }

    private void openRegisterDialog() {
        RegistrationDialog registrationDialog = new RegistrationDialog();
        registrationDialog.open();
    }

    private void addDrawerContent() {
        Span appName = new Span("TMS");
        appName.addClassNames(LumoUtility.FontWeight.SEMIBOLD, LumoUtility.FontSize.LARGE);
        Header header = new Header(appName);

        nav = createNavigation();
        Scroller scroller = new Scroller(nav);

        addToDrawer(header, scroller, createFooter());
    }

    private SideNav createNavigation() {
        nav = new SideNav();

//        nav.addItem(new SideNavItem("Home", HomeView.class, LineAwesomeIcon.HOUSE_DAMAGE_SOLID.create()));
//        nav.addItem(new SideNavItem("Tasks", TasksView.class, LineAwesomeIcon.PEN_ALT_SOLID.create()));
//        nav.addItem(new SideNavItem("Projects", ProjectsView.class, LineAwesomeIcon.CLIPBOARD_LIST_SOLID.create()));

        return nav;
    }

    private Footer createFooter() {
        Footer layout = new Footer();

        return layout;
    }

    private UserDto getCurrentUser() {
        // Replace with actual current user fetching logic
        UserDto user = new UserDto();
        user.setUsername("Ivan K");
        user.setFirstName("Ivan");
        user.setLastName("Karagyozov");
        user.setEmail("ivankaragyozov@gmail.com");
        user.setPhoneNumber("0885349981");
        user.setDateCreatedAt(LocalDate.of(2023, 6, 13));
        user.setDateLastModifiedAt(LocalDate.of(2023, 8, 26));
        return user;
    }

    @Override
    protected void afterNavigation() {
        super.afterNavigation();
        viewTitle.setText(getCurrentPageTitle());
    }

    private String getCurrentPageTitle() {
        PageTitle title = getContent().getClass().getAnnotation(PageTitle.class);
        return title == null ? "" : title.value();
    }
}

