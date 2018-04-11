package io.verticle.oss.fireboard.client;


import java.util.ArrayList;
import java.util.List;

/**
 * @author Jens Saade
 */
public class MessagePropertyHelper {

    private List<MessagePropertySection> sections = new ArrayList<MessagePropertySection>();
    private MessagePropertySection current;




    public MessagePropertyHelper section(String name){
        MessagePropertySection section = new MessagePropertySection();
        section.setName(name);
        section.setMessageProperties(new ArrayList<MessageProperty>());
        sections.add(section);
        current = section;

        return this;
    }

    public MessagePropertyHelper property(String name, String value){
        MessageProperty p = new MessageProperty();
        p.setName(name);
        p.setValue(value);
        current.getMessageProperties().add(p);
        return this;
    }

    public List<MessagePropertySection> build(){
        return sections;
    }

}
