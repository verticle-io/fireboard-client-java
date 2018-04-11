package io.verticle.oss.fireboard.client;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MessagePropertySection implements Serializable{

    final static long serialVersionUID = -5990878359416184870L;
    private String name;
    private String uuid;
    private List<MessageProperty> messageProperties = new ArrayList<MessageProperty>();

    /**
     * Creates a new MessagePropertySection.
     * 
     */
    public MessagePropertySection() {
        super();
    }

    /**
     * Creates a new MessagePropertySection.
     * @param name section name
     * @param uuid section id
     * @param messageProperties properties
     */
    public MessagePropertySection(String name, String uuid, List<MessageProperty> messageProperties) {
        super();
        this.name = name;
        this.uuid = uuid;
        this.messageProperties = messageProperties;
    }


    /**
     * Returns the name.
     * 
     * @return
     *     name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name.
     * 
     * @param name
     *     the new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the uuid.
     * 
     * @return
     *     uuid
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * Set the uuid.
     * 
     * @param uuid
     *     the new uuid
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * Returns the messageProperties.
     * 
     * @return
     *     messageProperties
     */
    public List<MessageProperty> getMessageProperties() {
        return messageProperties;
    }

    /**
     * Set the messageProperties.
     * 
     * @param messageProperties
     *     the new messageProperties
     */
    public void setMessageProperties(List<MessageProperty> messageProperties) {
        this.messageProperties = messageProperties;
    }

}
