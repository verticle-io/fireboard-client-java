package io.verticle.oss.fireboard.client;


import java.io.Serializable;

public class MessageProperty implements Serializable {

    final static long serialVersionUID = -5995835809066237254L;
    private String name;
    private String value;

    /**
     * Creates a new MessageProperty.
     * 
     */
    public MessageProperty() {
        super();
    }

    /**
     * Creates a new MessageProperty.
     * 
     */
    public MessageProperty(String name, String value) {
        super();
        this.name = name;
        this.value = value;
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
     * Returns the value.
     * 
     * @return
     *     value
     */
    public String getValue() {
        return value;
    }

    /**
     * Set the value.
     * 
     * @param value
     *     the new value
     */
    public void setValue(String value) {
        this.value = value;
    }

}
