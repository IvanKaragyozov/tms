package pu.master.gui.views.profile;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import jakarta.annotation.security.PermitAll;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.UploadI18N;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;

import pu.master.core.exceptions.TMSIOException;
import pu.master.core.exceptions.TMSRuntimeException;
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

        setProfilePictureImage(profilePicture);

        final Div overlay = new Div();
        overlay.setText("Upload");
        overlay.getStyle()
               .set("position", "absolute")
               .set("bottom", "0")
               .set("left", "0")
               .set("width", "100%")
               .set("height", "40%")
               .set("background", "rgba(0,0,0,0.6)")
               .set("color", "white")
               .set("display", "flex")
               .set("align-items", "center")
               .set("justify-content", "center")
               .set("border-bottom-left-radius", "50%")
               .set("border-bottom-right-radius", "50%")
               .set("opacity", "0")
               .set("cursor", "pointer")
               .set("transition", "opacity 0.2s ease");

        // Container
        final Div avatarContainer = new Div(profilePicture, overlay);
        avatarContainer.getStyle()
                       .set("position", "relative")
                       .set("display", "inline-block")
                       .set("border-radius", "50%")
                       .set("overflow", "hidden");

        // Hover effect
        avatarContainer.getElement().executeJs("""
                                                   const container = this;
                                                   const overlay = container.children[1];
                                                   container.addEventListener('mouseenter', () => overlay.style.opacity = '1');
                                                   container.addEventListener('mouseleave', () => overlay.style.opacity = '0');
                                               """);

        final Dialog uploadDialog = createUploadDialog(profilePicture);
        overlay.addClickListener(e -> uploadDialog.open());

        final VerticalLayout layout = new VerticalLayout(avatarContainer);
        layout.setAlignItems(FlexComponent.Alignment.CENTER);

        return layout;
    }


    private Dialog createUploadDialog(final Avatar profilePicture)
    {
        final Dialog uploadDialog = new Dialog();

        final Upload upload = getUpload(profilePicture, uploadDialog);

        uploadDialog.add(upload);
        uploadDialog.setHeaderTitle("Upload Profile Picture (Only PNG/JPEG, max 10MB)");

        final Button removePictureButton = new Button("Remove Picture", e -> {
            profilePicture.setImageResource(null);
            profilePicture.setName(currentUser.getUsername());
            userService.updateUserProfilePicture(currentUser, null);
            uploadDialog.close();
        });
        removePictureButton.getStyle().set("margin-right", "auto");

        final Button closeButton = new Button("Close", e -> uploadDialog.close());
        uploadDialog.getFooter().add(removePictureButton);
        uploadDialog.getFooter().add(closeButton);

        return uploadDialog;
    }


    private Upload getUpload(final Avatar profilePicture, final Dialog dialog)
    {
        final MemoryBuffer buffer = new MemoryBuffer();
        final Upload upload = new Upload(buffer);
        final String[] imageTypeWhitelist = {"image/png", "image/jpeg", "image/jpg"};
        upload.setAcceptedFileTypes(imageTypeWhitelist);
        upload.setMaxFiles(1);
        upload.setMaxFileSize(10 * 1024 * 1024); // 10 MB

        final UploadI18N uploadI18N = new UploadI18N();
        uploadI18N.setError(new UploadI18N.Error()
                                            .setTooManyFiles("Only one file allowed.")
                                            .setFileIsTooBig("File is too large. Maximum size is 10MB.")
                                            .setIncorrectFileType("Invalid file type! Only PNG and JPEG images are allowed."));
        upload.setI18n(uploadI18N);

        upload.addSucceededListener(event -> {
            final String mimeType = event.getMIMEType();

            if (Arrays.asList(imageTypeWhitelist).contains(mimeType))
            {
                throw new TMSIOException("Invalid file type uploaded: " + mimeType);
            }
            try
            {
                final InputStream inputStream = buffer.getInputStream();
                final byte[] pfpInBytes = inputStream.readAllBytes();

                final StreamResource resource = new StreamResource(event.getFileName(),
                                                                   () -> new ByteArrayInputStream(pfpInBytes));
                profilePicture.setImageResource(resource);
                userService.updateUserProfilePicture(currentUser, pfpInBytes);

                dialog.close();
            }
            catch (final IOException ex)
            {
                throw new TMSRuntimeException("Failed to read uploaded file", ex);
            }
        });

        upload.addFailedListener(event -> Notification.show("Failed to upload file.",
                                                        4000,
                                                        Notification.Position.TOP_CENTER));

        upload.addFileRejectedListener(event -> {

            if (event.getErrorMessage().equals(uploadI18N.getError().getFileIsTooBig()))
            {
                Notification.show("File is too large. Maximum size is 10MB.",
                                  4000,
                                  Notification.Position.TOP_CENTER);
            }
            else if (event.getErrorMessage().equals(uploadI18N.getError().getIncorrectFileType()))
            {
                Notification.show("Invalid file type! Only PNG and JPEG images are allowed.",
                                  4000,
                                  Notification.Position.TOP_CENTER);
            }
            else if (event.getErrorMessage().equals(uploadI18N.getError().getTooManyFiles()))
            {
                Notification.show("Only one file allowed.", 4000, Notification.Position.TOP_CENTER);
            }
            else
            {
                Notification.show("Failed to upload file.",
                                  4000,
                                  Notification.Position.TOP_CENTER);
            }
        });

        return upload;
    }


    private void setProfilePictureImage(final Avatar avatar)
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
