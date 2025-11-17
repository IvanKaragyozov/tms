package pu.master.core.security.providers;


public interface LoginViewProvider
{
    Class<? extends com.vaadin.flow.component.Component> getLoginView();
}
