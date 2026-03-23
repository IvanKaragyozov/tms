package pu.master.gui.views.profile;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.server.StreamResource;

import pu.master.core.exceptions.TMSRuntimeException;
import pu.master.core.services.UserService;


public class UploadProfilePictureDialog
{
    private final Dialog profilePictureDialog = new Dialog();

    private final UserService userService;


    public UploadProfilePictureDialog(final UserService userService)
    {
        this.userService = userService;
    }


//    private Upload getUpload(final Avatar profilePicture)
//    {
//        final MemoryBuffer buffer = new MemoryBuffer();
//        final Upload upload = new Upload(buffer);
//        upload.setAcceptedFileTypes("image/png", "image/jpeg", "image/jpg");
//        upload.setMaxFiles(1);
//        upload.setMaxFileSize(10 * 1024 * 1024); // 10 MB
//
//        upload.addSucceededListener(event -> {
//            try
//            {
//                final InputStream inputStream = buffer.getInputStream();
//                final byte[] pfpInBytes = inputStream.readAllBytes();
//
//                final StreamResource resource = new StreamResource(event.getFileName(),
//                                                                   () -> new ByteArrayInputStream(pfpInBytes));
//                profilePicture.setImageResource(resource);
//                userService.updateUserProfilePicture(currentUser, pfpInBytes);
//
//                profilePictureDialog.close();
//            }
//            catch (final IOException ex)
//            {
//                throw new TMSRuntimeException("Failed to read uploaded file", ex);
//            }
//        });
//
//        return upload;
//    }
}
