package pu.master.gui.security.providers;


import org.springframework.stereotype.Component;

import pu.master.core.security.providers.LoginViewProvider;
import pu.master.gui.views.login.LoginView;


@Component
public class LoginViewProviderImpl implements LoginViewProvider
{
    @Override
    public Class<? extends com.vaadin.flow.component.Component> getLoginView()
    {
        return LoginView.class;
    }
}
