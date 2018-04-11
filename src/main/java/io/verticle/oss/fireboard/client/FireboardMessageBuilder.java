package io.verticle.oss.fireboard.client;

import java.net.URL;
import java.util.List;

/**
 * Convenient helper to create {{@link Status}} messages fluently.
 * @author Jens Saade
 */
public class FireboardMessageBuilder {

    private static Status status;

    public FireboardMessageBuilder(){
        status = new Status();
    }


    public FireboardMessageBuilder category(String category){
        status.setCategory(category);
        return this;
    }

    public FireboardMessageBuilder severity(int severity){
        status.setSeverity(String.valueOf(severity));
        return this;
    }

    public FireboardMessageBuilder event(String event){
        status.setEvent(event);
        return this;
    }

    public FireboardMessageBuilder message(String message){
        status.setMessage(message);
        return this;
    }

    public FireboardMessageBuilder ident(String ident){
        status.setIdent(ident);
        return this;
    }

    public FireboardMessageBuilder status(StatusEnum status){
        FireboardMessageBuilder.status.setStatus(status.name());
        return this;
    }

    public FireboardMessageBuilder link(URL link){
        status.setLink(link.toExternalForm());
        return this;
    }

    public FireboardMessageBuilder properties(List<MessagePropertySection> messagePropertySectionList){
        status.setMessagePropertySection(messagePropertySectionList);
        return this;
    }

    public FireboardMessageBuilder uuid(String uuid) {
        status.setUuid(uuid);
        return this;
    }

    public Status build(){
        return status;
    }
}
