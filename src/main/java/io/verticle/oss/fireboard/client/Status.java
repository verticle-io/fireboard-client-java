package io.verticle.oss.fireboard.client;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Status implements Serializable
{

    final static long serialVersionUID = -4635841524734745733L;
    /**
     * event name/short summary
     * 
     */
    private String event;
    /**
     * timestamp
     * 
     */
    private String created;
    /**
     * the unique identity of the status event
     * 
     */
    private String uuid;
    /**
     * qualifier is a dot separated alphanumeric path of any length
     * 
     */
    private String ident;
    /**
     * success, warn, error
     * 
     */
    private String status;
    /**
     *  0 - 6
     * 
     */
    private String severity;
    /**
     * a grouping topic
     * 
     */
    private String category;
    /**
     * a detailed message of the event
     * 
     */
    private String message;
    /**
     * a URL to details
     * 
     */
    private String link;
    /**
     * optional message properties grouped into sections
     * 
     */
    private List<MessagePropertySection> messagePropertySection = new ArrayList<MessagePropertySection>();

    /**
     * Creates a new Status.
     * 
     */
    public Status() {
        super();
    }

    /**
     * Creates a new Status.
     * 
     */
    public Status(String event, String created, String uuid, String ident, String status, String severity, String category, String message, String link, List<MessagePropertySection> messagePropertySection) {
        super();
        this.event = event;
        this.created = created;
        this.uuid = uuid;
        this.ident = ident;
        this.status = status;
        this.severity = severity;
        this.category = category;
        this.message = message;
        this.link = link;
        this.messagePropertySection = messagePropertySection;
    }


    /**
     * Returns the event.
     * 
     * @return
     *     event
     */
    public String getEvent() {
        return event;
    }

    /**
     * Set the event.
     * 
     * @param event
     *     the new event
     */
    public void setEvent(String event) {
        this.event = event;
    }

    /**
     * Returns the created.
     * 
     * @return
     *     created
     */
    public String getCreated() {
        return created;
    }

    /**
     * Set the created.
     * 
     * @param created
     *     the new created
     */
    public void setCreated(String created) {
        this.created = created;
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
     * Returns the ident.
     * 
     * @return
     *     ident
     */
    public String getIdent() {
        return ident;
    }

    /**
     * Set the ident.
     * 
     * @param ident
     *     the new ident
     */
    public void setIdent(String ident) {
        this.ident = ident;
    }

    /**
     * Returns the status.
     * 
     * @return
     *     status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Set the status.
     * 
     * @param status
     *     the new status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Returns the severity.
     * 
     * @return
     *     severity
     */
    public String getSeverity() {
        return severity;
    }

    /**
     * Set the severity.
     * 
     * @param severity
     *     the new severity
     */
    public void setSeverity(String severity) {
        this.severity = severity;
    }

    /**
     * Returns the category.
     * 
     * @return
     *     category
     */
    public String getCategory() {
        return category;
    }

    /**
     * Set the category.
     * 
     * @param category
     *     the new category
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Returns the message.
     * 
     * @return
     *     message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Set the message.
     * 
     * @param message
     *     the new message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Returns the link.
     * 
     * @return
     *     link
     */
    public String getLink() {
        return link;
    }

    /**
     * Set the link.
     * 
     * @param link
     *     the new link
     */
    public void setLink(String link) {
        this.link = link;
    }

    /**
     * Returns the messagePropertySection.
     * 
     * @return
     *     messagePropertySection
     */
    public List<MessagePropertySection> getMessagePropertySection() {
        return messagePropertySection;
    }

    /**
     * Set the messagePropertySection.
     * 
     * @param messagePropertySection
     *     the new messagePropertySection
     */
    public void setMessagePropertySection(List<MessagePropertySection> messagePropertySection) {
        this.messagePropertySection = messagePropertySection;
    }

}
