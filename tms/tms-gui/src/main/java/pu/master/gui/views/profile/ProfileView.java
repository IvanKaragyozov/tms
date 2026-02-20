package pu.master.gui.views.profile;


import java.io.ByteArrayInputStream;

import jakarta.annotation.security.PermitAll;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.avatar.Avatar;
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
import com.vaadin.flow.server.StreamResource;

import pu.master.core.services.UserService;
import pu.master.core.services.formatters.TMSDateFormatter;
import pu.master.domain.models.uibeans.UserUIBean;
import pu.master.gui.views.MainLayout;
import pu.master.gui.views.utils.Routes;


@PermitAll
@Route(value = Routes.PROFILE, layout = MainLayout.class)
@PageTitle("Profile | TMS")
public class ProfileView extends VerticalLayout
{

    private final UserService userService;

    private final Binder<UserUIBean> binder = new Binder<>(UserUIBean.class);

    private final TextField username = new TextField("Username");
    private final TextField firstName = new TextField("First Name");
    private final TextField lastName = new TextField("Last Name");
    private final EmailField email = new EmailField("Email");
    private final TextField phoneNumber = new TextField("Phone Number");
    private final TextField createdAt = new TextField("Account Created");
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

        setUserToCurrent();
        configureUserFields();
        configureBinder();
        configureButtons();

        add(buildLayout());

        setViewOnlyMode();
    }


    private void setUserToCurrent()
    {
        this.currentUser = userService.getCurrentLoggedInUserUIBean();
        binder.setBean(currentUser);
    }


    private void updateUser(final UserUIBean userUIBean)
    {
        this.currentUser = userUIBean;
        binder.setBean(currentUser);
    }


    private void configureUserFields()
    {
        username.setReadOnly(true);
        username.setValue(currentUser.getUsername());

        firstName.setValue(currentUser.getFirstName());
        lastName.setValue(currentUser.getLastName());

        email.setReadOnly(true);
        email.setValue(currentUser.getEmail());

        phoneNumber.setValue(currentUser.getPhoneNumber());

        createdAt.setReadOnly(true);
        createdAt.setValue(TMSDateFormatter.format(currentUser.getDateCreatedAt()));

        lastChange.setReadOnly(true);
        lastChange.setValue(TMSDateFormatter.format(currentUser.getDateLastModifiedAt()));
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
                setViewOnlyMode();
            }
        });

        cancelButton.addClickListener(e -> {
            binder.readBean(currentUser);
            setViewOnlyMode();
        });

        editButton.addClickListener(e -> setEditMode());
    }


    private Component buildLayout()
    {

        final H2 header = new H2("Profile");

        final FormLayout form = new FormLayout(
                        username,
                        email,
                        firstName,
                        lastName,
                        phoneNumber,
                        createdAt,
                        lastChange
        );

        final HorizontalLayout actions = new HorizontalLayout(editButton, saveButton, cancelButton);
        actions.setSpacing(true);

        final VerticalLayout pfpLayout = getPfpLayout();
        return new VerticalLayout(pfpLayout, header, form, actions);
    }


    private VerticalLayout getPfpLayout()
    {
        final Avatar profilePicture = new Avatar();
        final String avatarSize = "6em";
        profilePicture.setWidth(avatarSize);
        profilePicture.setHeight(avatarSize);

        getProfilePictureUrl(profilePicture);

        final VerticalLayout pfpLayout = new VerticalLayout(profilePicture);
        pfpLayout.setAlignItems(Alignment.CENTER);

        return pfpLayout;
    }


    private void getProfilePictureUrl(final Avatar avatar)
    {
        final byte[] pfpByteArray = currentUser.getProfilePicture();
        if (pfpByteArray != null && pfpByteArray.length > 0)
        {
            avatar.setImageResource(new StreamResource("profile.png", () -> new ByteArrayInputStream(pfpByteArray)));
        }
        else
        {
            avatar.setName(currentUser.getUsername());
        }
    }


    private void setViewOnlyMode()
    {
        firstName.setReadOnly(true);
        lastName.setReadOnly(true);
        phoneNumber.setReadOnly(true);

        editButton.setVisible(true);
        saveButton.setVisible(false);
        cancelButton.setVisible(false);
    }


    private void setEditMode()
    {
        firstName.setReadOnly(false);
        lastName.setReadOnly(false);
        phoneNumber.setReadOnly(false);

        editButton.setVisible(false);
        saveButton.setVisible(true);
        cancelButton.setVisible(true);
    }
}
