package io.xrates.backend.events;

import io.xrates.backend.communication.XratesEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;

public class EmailVerificationListener implements ApplicationListener<SendEmailVerificationEvent>{

    @Autowired
    private XratesEmail xratesEmail;

    public void onApplicationEvent(SendEmailVerificationEvent e){
        this.verifyUser(e);
    }

    private void verifyUser(SendEmailVerificationEvent e){

    }
}
