//package pu.master.gui.components;
//
//import java.io.ByteArrayInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//
//import com.vaadin.flow.component.avatar.Avatar;
//import com.vaadin.flow.component.button.Button;
//import com.vaadin.flow.component.dialog.Dialog;
//import com.vaadin.flow.component.notification.Notification;
//import com.vaadin.flow.component.upload.Upload;
//import com.vaadin.flow.component.upload.UploadI18N;
//import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
//import com.vaadin.flow.server.StreamResource;
//
//import pu.master.core.exceptions.TMSRuntimeException;
//import pu.master.core.services.UserService;
//import pu.master.domain.models.uibeans.UserUIBean;
//import pu.master.gui.services.ProfilePictureValidator;
//
///**
// * Reusable component for uploading profile pictures.
// * Handles the upload dialog, validation, and persistence.
// * Follows Single Responsibility Principle by focusing on upload operations.
// */
//public class ProfilePictureUploadDialog
//{
//    private final Dialog dialog;
//    private final UserService userService;
//    private final ProfilePictureValidator validator;
//    private final Avatar profilePicture;
//    private final Runnable onSuccessCallback;
//
//    public ProfilePictureUploadDialog(
//            final Avatar profilePicture,
//            final UserService userService,
//            final ProfilePictureValidator validator,
//            final Runnable onSuccessCallback)
//    {
//        this.profilePicture = profilePicture;
//        this.userService = userService;
//        this.validator = validator;
//        this.onSuccessCallback = onSuccessCallback;
//        this.dialog = new Dialog();
//
//        buildDialog();
//    }
//
//    private void buildDialog()
//    {
//        dialog.setHeaderTitle("Upload Profile Picture (" + validator.getAllowedTypesAsString() + ", max 10MB)");
//
//        final Upload upload = createUpload();
//        dialog.add(upload);
//
//        final Button removeButton = createRemoveButton();
//        final Button closeButton = new Button("Close", e -> dialog.close());
//
//        dialog.getFooter().add(removeButton);
//        dialog.getFooter().add(closeButton);
//    }
//
//    private Upload createUpload()
//    {
//        final MemoryBuffer buffer = new MemoryBuffer();
//        final Upload upload = new Upload(buffer);
//
//        configureUploadComponent(upload);
//        configureUploadI18n(upload);
//        attachUploadListeners(upload, buffer);
//
//        return upload;
//    }
//
//    private void configureUploadComponent(final Upload upload)
//    {
//        upload.setAcceptedFileTypes(validator.getAllowedMimeTypes());
//        upload.setMaxFiles(1);
//        upload.setMaxFileSize(validator.getMaxFileSize());
//    }
//
//    private void configureUploadI18n(final Upload upload)
//    {
//        final UploadI18N i18n = new UploadI18N();
//        i18n.setError(new UploadI18N.Error()
//                .setTooManyFiles("Only one file allowed.")
//                .setFileIsTooBig("File is too large. Maximum size is 10MB.")
//                .setIncorrectFileType("Invalid file type! Only " + validator.getAllowedTypesAsString() + " are allowed."));
//        upload.setI18n(i18n);
//    }
//
//    private void attachUploadListeners(final Upload upload, final MemoryBuffer buffer)
//    {
//        upload.addSucceededListener(event -> handleUploadSuccess(event, buffer));
//        upload.addFailedListener(event -> showNotification("Failed to upload file.", 4000));
//        upload.addFileRejectedListener(this::handleFileRejected);
//    }
//
//    private void handleUploadSuccess(final Upload.SucceededEvent event, final MemoryBuffer buffer)
//    {
//        try
//        {
//            final String mimeType = event.getMIMEType();
//
//            // FIX: Inverted logic - should check if NOT valid
//            if (!validator.isValidMimeType(mimeType))
//            {
//                showNotification("Invalid file type: " + mimeType, 4000);
//                return;
//            }
//
//            final InputStream inputStream = buffer.getInputStream();
//            final byte[] fileBytes = inputStream.readAllBytes();
//
//            // Display preview
//            final StreamResource resource = new StreamResource(
//                    event.getFileName(),
//                    () -> new ByteArrayInputStream(fileBytes));
//            profilePicture.setImageResource(resource);
//
//            // Persist to database
//            userService.updateUserProfilePicture(getCurrentUser(), fileBytes);
//
//            showNotification("Profile picture updated successfully!", 3000);
//            dialog.close();
//
//            if (onSuccessCallback != null)
//            {
//                onSuccessCallback.run();
//            }
//        }
//        catch (final IOException ex)
//        {
//            throw new TMSRuntimeException("Failed to read uploaded file", ex);
//        }
//    }
//
//    private void handleFileRejected(final Upload.FileRejectedEvent event)
//    {
//        final String errorMessage = event.getErrorMessage();
//
//        if (errorMessage.contains("too large"))
//        {
//            showNotification("File is too large. Maximum size is 10MB.", 4000);
//        }
//        else if (errorMessage.contains("type"))
//        {
//            showNotification("Invalid file type! Only " + validator.getAllowedTypesAsString() + " are allowed.", 4000);
//        }
//        else if (errorMessage.contains("many files"))
//        {
//            showNotification("Only one file allowed.", 4000);
//        }
//        else
//        {
//            showNotification("Failed to upload file.", 4000);
//        }
//    }
//
//    private Button createRemoveButton()
//    {
//        final Button removeButton = new Button("Remove Picture", e -> {
//            profilePicture.setImageResource(null);
//            profilePicture.setName(getCurrentUser().getUsername());
//            userService.updateUserProfilePicture(getCurrentUser(), null);
//            showNotification("Profile picture removed.", 3000);
//            dialog.close();
//
//            if (onSuccessCallback != null)
//            {
//                onSuccessCallback.run();
//            }
//        });
//        removeButton.getStyle().set("margin-right", "auto");
//        return removeButton;
//    }
//
//    private UserUIBean getCurrentUser()
//    {
//        return userService.getCurrentLoggedInUserUIBean();
//    }
//
//    private void showNotification(final String message, final int duration)
//    {
//        Notification.show(message, duration, Notification.Position.TOP_CENTER);
//    }
//
//    public void open()
//    {
//        dialog.open();
//    }
//
//    public Dialog getDialog()
//    {
//        return dialog;
//    }
//}
