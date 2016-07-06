package io.xrates.backend.events;

import io.xrates.frontend.templates.SubscriptionForm;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.context.ApplicationEvent;
public class SendEmailVerificationEvent extends ApplicationEvent{

    Logger log = LoggerFactory.getLogger(SendEmailVerificationEvent.class.getName());

    private String appUrl;
    private final SubscriptionForm form;

    public SubscriptionForm getForm() {
        return form;
    }


    public String getAppUrl() {
        return appUrl;
    }

    public void setAppUrl(String appUrl) {
        this.appUrl = appUrl;
    }

    public SendEmailVerificationEvent(String appUrl, SubscriptionForm form){
        super(form);

        this.appUrl = appUrl;
        this.form = form;
        log.debug("Event triggered");
        log.debug("Form email: " + form.getEmailAddress());
    }

}
