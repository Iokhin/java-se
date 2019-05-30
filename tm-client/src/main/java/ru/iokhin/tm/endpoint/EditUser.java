
package ru.iokhin.tm.endpoint;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for editUser complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="editUser"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="session" type="{http://endpoint.tm.iokhin.ru/}session" minOccurs="0"/&gt;
 *         &lt;element name="newLogin" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="newPasswordHash" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "editUser", propOrder = {
    "session",
    "newLogin",
    "newPasswordHash"
})
public class EditUser {

    protected Session session;
    protected String newLogin;
    protected String newPasswordHash;

    /**
     * Gets the value of the session property.
     * 
     * @return
     *     possible object is
     *     {@link Session }
     *     
     */
    public Session getSession() {
        return session;
    }

    /**
     * Sets the value of the session property.
     * 
     * @param value
     *     allowed object is
     *     {@link Session }
     *     
     */
    public void setSession(Session value) {
        this.session = value;
    }

    /**
     * Gets the value of the newLogin property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNewLogin() {
        return newLogin;
    }

    /**
     * Sets the value of the newLogin property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNewLogin(String value) {
        this.newLogin = value;
    }

    /**
     * Gets the value of the newPasswordHash property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNewPasswordHash() {
        return newPasswordHash;
    }

    /**
     * Sets the value of the newPasswordHash property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNewPasswordHash(String value) {
        this.newPasswordHash = value;
    }

}
