package pu.master.gui.views.profile;


import jakarta.annotation.security.PermitAll;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AccessDeniedErrorRouter;

import pu.master.core.services.UserService;
import pu.master.domain.models.uibeans.UserUIBean;
import pu.master.gui.views.MainLayout;
import pu.master.gui.views.utils.Routes;


@PermitAll
@Route(value = Routes.PROFILE, layout = MainLayout.class)
@PageTitle("Profile | TMS")
@AccessDeniedErrorRouter
public class ProfileView extends VerticalLayout
{

    private final UserService userService;

    private final Binder<UserUIBean> binder = new Binder<>(UserUIBean.class);

    private final TextField username = new TextField("Username");
    private final TextField firstName = new TextField("First Name");
    private final TextField lastName = new TextField("Last Name");
    private final EmailField email = new EmailField("Email");
    private final TextField phoneNumber = new TextField("Phone Number");
    private final TextField createdAt = new TextField("Created At");
    private final TextField lastChange = new TextField("Last Change");

    private final Button editButton = new Button("Edit", VaadinIcon.EDIT.create());
    private final Button saveButton = new Button("Save", VaadinIcon.CHECK.create());
    private final Button cancelButton = new Button("Cancel", VaadinIcon.CLOSE.create());

    private UserUIBean currentUser;


    @Autowired
    public ProfileView(final UserService userService)
    {
        this.userService = userService;

        setSizeFull();
        setPadding(true);
        setSpacing(true);

        updateUser();
        configureFields();
        configureBinder();
        configureButtons();

        add(buildLayout());

        setViewMode();
    }


    private void updateUser()
    {
        this.currentUser = userService.getCurrentLoggedInUserUIBean();
        binder.setBean(currentUser);
    }


    private void updateUser(final UserUIBean userUIBean)
    {
        this.currentUser = userUIBean;
        binder.setBean(currentUser);
    }


    private void configureFields()
    {
        username.setReadOnly(true);
        username.setValue(currentUser.getUsername());

        firstName.setValue(currentUser.getFirstName());
        lastName.setValue(currentUser.getLastName());

        email.setReadOnly(true);
        email.setValue(currentUser.getEmail());

        phoneNumber.setValue(currentUser.getPhoneNumber());

        createdAt.setReadOnly(true);
        createdAt.setValue(currentUser.getDateCreatedAt().toString());

        lastChange.setReadOnly(true);
        lastChange.setValue(currentUser.getDateCreatedAt().toString());
    }


    private void configureBinder()
    {
        binder.forField(firstName)
              .bind(UserUIBean::getFirstName, UserUIBean::setFirstName);

        binder.forField(lastName)
              .bind(UserUIBean::getLastName, UserUIBean::setLastName);

        binder.forField(phoneNumber)
              .withValidator(value -> !userService.phoneExists(value), "Phone already in use")
              .bind(UserUIBean::getPhoneNumber, UserUIBean::setPhoneNumber);
    }


    private void configureButtons()
    {
        saveButton.addClickListener(e -> {
            if (binder.validate().isOk())
            {
                updateUser(userService.updateUserProfile(binder.getBean()));
                Notification.show("Profile updated successfully");
                setViewMode();
            }
        });

        cancelButton.addClickListener(e -> {
            binder.readBean(currentUser);
            setViewMode();
        });

        editButton.addClickListener(e -> setEditMode());
    }


    private Component buildLayout()
    {
        final H2 header = new H2("Your Profile");

        final FormLayout form = new FormLayout(
                        username,
                        firstName,
                        lastName,
                        email,
                        phoneNumber,
                        createdAt,
                        lastChange
        );

        form.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 1));

        final HorizontalLayout actions = new HorizontalLayout(editButton, saveButton, cancelButton);
        actions.setSpacing(true);

        return new VerticalLayout(header, form, actions);
    }


    private void setViewMode()
    {
        firstName.setReadOnly(true);
        lastName.setReadOnly(true);
        email.setReadOnly(true);
        phoneNumber.setReadOnly(true);

        editButton.setVisible(true);
        saveButton.setVisible(false);
        cancelButton.setVisible(false);
    }


    private void setEditMode()
    {
        firstName.setReadOnly(false);
        lastName.setReadOnly(false);
        email.setReadOnly(false);
        phoneNumber.setReadOnly(false);

        editButton.setVisible(false);
        saveButton.setVisible(true);
        cancelButton.setVisible(true);
    }
}
