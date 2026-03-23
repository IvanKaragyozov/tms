//package pu.master.gui.components;
//
//import com.vaadin.flow.component.avatar.Avatar;
//import com.vaadin.flow.component.html.Div;
//import com.vaadin.flow.component.orderedlayout.FlexComponent;
//import com.vaadin.flow.component.orderedlayout.VerticalLayout;
//import com.vaadin.flow.server.StreamResource;
//
//import pu.master.domain.models.uibeans.UserUIBean;
//
//import java.io.ByteArrayInputStream;
//
///**
// * Reusable component for displaying and managing profile pictures with upload overlay.
// * Handles the UI for the avatar display with hover overlay.
// * Follows Single Responsibility Principle by focusing on UI presentation.
// */
//public class ProfilePictureComponent extends VerticalLayout
//{
//    private static final String AVATAR_SIZE = "6em";
//    private final Avatar avatar;
//    private final Div overlay;
//    private final ProfilePictureUploadDialog uploadDialog;
//
//    public ProfilePictureComponent(
//            final UserUIBean user,
//            final ProfilePictureUploadDialog uploadDialog)
//    {
//        this.uploadDialog = uploadDialog;
//        this.avatar = new Avatar();
//        this.overlay = new Div();
//
//        initializeComponent(user);
//    }
//
//    private void initializeComponent(final UserUIBean user)
//    {
//        // Configure avatar
//        avatar.setWidth(AVATAR_SIZE);
//        avatar.setHeight(AVATAR_SIZE);
//        setProfilePictureImage(avatar, user);
//
//        // Configure overlay
//        configureOverlay();
//
//        // Create container with hover effect
//        final Div avatarContainer = new Div(avatar, overlay);
//        configureAvatarContainer(avatarContainer);
//        attachHoverEffect(avatarContainer);
//
//        // Open upload dialog on click
//        overlay.addClickListener(e -> uploadDialog.open());
//
//        // Configure layout
//        add(avatarContainer);
//        setAlignItems(FlexComponent.Alignment.CENTER);
//    }
//
//    private void configureOverlay()
//    {
//        overlay.setText("Upload");
//        overlay.getStyle()
//               .set("position", "absolute")
//               .set("bottom", "0")
//               .set("left", "0")
//               .set("width", "100%")
//               .set("height", "40%")
//               .set("background", "rgba(0,0,0,0.6)")
//               .set("color", "white")
//               .set("display", "flex")
//               .set("align-items", "center")
//               .set("justify-content", "center")
//               .set("border-bottom-left-radius", "50%")
//               .set("border-bottom-right-radius", "50%")
//               .set("opacity", "0")
//               .set("cursor", "pointer")
//               .set("transition", "opacity 0.2s ease");
//    }
//
//    private void configureAvatarContainer(final Div container)
//    {
//        container.getStyle()
//                 .set("position", "relative")
//                 .set("display", "inline-block")
//                 .set("border-radius", "50%")
//                 .set("overflow", "hidden");
//    }
//
//    private void attachHoverEffect(final Div container)
//    {
//        container.getElement().executeJs(
//                "const container = this;" +
//                "const overlay = container.children[1];" +
//                "container.addEventListener('mouseenter', () => overlay.style.opacity = '1');" +
//                "container.addEventListener('mouseleave', () => overlay.style.opacity = '0');"
//        );
//    }
//
//    private void setProfilePictureImage(final Avatar avatar, final UserUIBean user)
//    {
//        final byte[] profilePictureBytes = user.getProfilePicture();
//
//        if (profilePictureBytes != null && profilePictureBytes.length > 0)
//        {
//            avatar.setImageResource(
//                    new StreamResource(
//                            "profile.png",
//                            () -> new ByteArrayInputStream(profilePictureBytes)));
//        }
//        else
//        {
//            avatar.setName(user.getUsername());
//        }
//    }
//
//    public Avatar getAvatar()
//    {
//        return avatar;
//    }
//
//    public void refresh(final UserUIBean user)
//    {
//        setProfilePictureImage(avatar, user);
//    }
//}
