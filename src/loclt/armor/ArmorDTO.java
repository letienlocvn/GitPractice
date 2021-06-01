/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loclt.armor;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author WIN
 */
public class ArmorDTO implements Serializable{

    private String ArmorID, Classfication, Description, Status;
    private Date TimeOfCreate;
    private int Defense;

    public ArmorDTO(String ArmorID, String Classfication, String Description, String Status, Date TimeOfCreate, int Defense) {
        this.ArmorID = ArmorID;
        this.Classfication = Classfication;
        this.Description = Description;
        this.Status = Status;
        this.TimeOfCreate = TimeOfCreate;
        this.Defense = Defense;
    }

    /**
     * @return the ArmorID
     */
    public String getArmorID() {
        return ArmorID;
    }

    /**
     * @param ArmorID the ArmorID to set
     */
    public void setArmorID(String ArmorID) {
        this.ArmorID = ArmorID;
    }

    /**
     * @return the Classfication
     */
    public String getClassfication() {
        return Classfication;
    }

    /**
     * @param Classfication the Classfication to set
     */
    public void setClassfication(String Classfication) {
        this.Classfication = Classfication;
    }

    /**
     * @return the Description
     */
    public String getDescription() {
        return Description;
    }

    /**
     * @param Description the Description to set
     */
    public void setDescription(String Description) {
        this.Description = Description;
    }

    /**
     * @return the Status
     */
    public String getStatus() {
        return Status;
    }

    /**
     * @param Status the Status to set
     */
    public void setStatus(String Status) {
        this.Status = Status;
    }

    /**
     * @return the TimeOfCreate
     */
    public Date getTimeOfCreate() {
        return TimeOfCreate;
    }

    /**
     * @param TimeOfCreate the TimeOfCreate to set
     */
    public void setTimeOfCreate(Date TimeOfCreate) {
        this.TimeOfCreate = TimeOfCreate;
    }

    /**
     * @return the Defense
     */
    public int getDefense() {
        return Defense;
    }

    /**
     * @param Defense the Defense to set
     */
    public void setDefense(int Defense) {
        this.Defense = Defense;
    }
    
    @Override
    public String toString(){
        DateFormat date = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
        String replaceDescription = Description.replaceAll("\n","!!~~");
        return ArmorID + "###" + Classfication + "###" + replaceDescription + "###" + Status + "###" + date.format(TimeOfCreate) + "###" + Defense;
    }

    public Object [] toArray() {
        return new Object[]{
            this.getArmorID(), this.getClassfication(),
            this.getTimeOfCreate(), this.getDefense()};
        }
    }

