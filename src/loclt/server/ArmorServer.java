/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loclt.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import loclt.armor.ArmorDTO;
import loclt.armorInterface.ArmorInterface;

/**
 *
 * @author WIN
 */
public class ArmorServer extends UnicastRemoteObject implements ArmorInterface {

    private String fileName;

    public ArmorServer(String filename) throws RemoteException {
        super();
        this.fileName = filename;
    }

    public ArmorServer() throws RemoteException {
        super();
        fileName = "";
    }

    @Override
    public boolean createArmor(ArmorDTO dto) throws RemoteException {
        FileWriter fileWriter;
        BufferedWriter bw;
        PrintWriter pw = null;
        try {
            fileWriter = new FileWriter(fileName, true);
            bw = new BufferedWriter(fileWriter);
            pw = new PrintWriter(bw);
            pw.println(dto.toString());
        } catch (IOException e) {
            return false;
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
        return true;
    }

    @Override
    public ArmorDTO findByArmorID(String id) throws RemoteException {
//        ArmorDTO dto = null;
//        try {
//            FileReader fr = new FileReader(fileName);
//            BufferedReader br = new BufferedReader(fr);
//            String line = "";
//            while ((line = br.readLine()) != null) {
//                StringTokenizer stk = new StringTokenizer(line, "###");
//                String armorID = stk.nextToken().trim();
//                if (armorID.equals(id)) {
//                    String classfication = stk.nextToken().trim();
//                    String description = stk.nextToken().trim();
//                    String replaceDescription = description.replaceAll("!!~~", "\n");
//                    String status = stk.nextToken().trim();
//                    Date timeOfCreate
//                            = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss").parse(stk.nextToken().trim());
//                    int defense
//                            = Integer.parseInt(stk.nextToken().trim());
//                    dto = new ArmorDTO(armorID, classfication, replaceDescription, status, timeOfCreate, defense);
//                }
//            }
//            br.close();
//            fr.close();
//        } catch (FileNotFoundException e) {
//            System.out.println("File in FindByID:" + e.getMessage());
//        } catch (IOException | ParseException ex) {
//            System.out.println("FindByID: " + ex.getMessage());
//        }
//        return dto;
        ArmorDTO dto = null;
        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            StringTokenizer stk = new StringTokenizer(line, "###");
            while ((line = br.readLine()) != null) {
                String armorID = stk.nextToken().trim();
                if (armorID.equals(id)){
                    String classfication = stk.nextToken().trim();
                    String description = stk.nextToken().trim();
                    String resDis = description.replaceAll("!!~~", "\n");
                    String status = stk.nextToken().trim();
                    int defense = Integer.parseInt(stk.nextToken().trim());
                    Date date = new SimpleDateFormat("yyyy/MM/dd").parse(stk.nextToken().trim());
                    dto = new ArmorDTO(armorID, classfication, resDis, status, date, defense);
                }
            }
            br.close();
            fr.close();
        } catch (IOException | NumberFormatException | ParseException e) {
        }
        return dto;
    }

    @Override
    public List<ArmorDTO> findArmor() throws RemoteException {
        List<ArmorDTO> list = null;
        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            list = new ArrayList<>();
            StringTokenizer stk;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.length() > 0) {
                    stk = new StringTokenizer(line, "###");
                    String armorID = stk.nextToken().trim();
                    String classfication = stk.nextToken().trim();
                    String description = stk.nextToken().trim();
                    String replaceDescription = description.replaceAll("!!~~", "\n");
                    String status = stk.nextToken().trim();
                    String date = "yyyy/MM/dd hh:mm:ss";
                    Date timeDate = new SimpleDateFormat(date).parse(stk.nextToken().trim());
                    int defense = Integer.parseInt(stk.nextToken());
                    ArmorDTO dto = new ArmorDTO(armorID, classfication, replaceDescription, status, timeDate, defense);
                    list.add(dto);
                }
            }
        } catch (IOException | NumberFormatException | ParseException | NoSuchElementException e) {
            System.out.println("Find Armor: " + e.getMessage());
        }
        return list;
    }

    @Override
    public boolean removeArmor(String id) throws RemoteException {
        List<ArmorDTO> list = findArmor();
        boolean Delete = false;
        for (ArmorDTO armorDTO : list) {
            if (armorDTO.getArmorID().equals(id)) {
                list.remove(armorDTO);
                Delete = true;
                break;
            }
        }
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(fileName);
            for (ArmorDTO armorDTO : list) {
                pw.println(armorDTO.toString());
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
        return Delete;

    }

    @Override
    public boolean updateArmor(ArmorDTO dto) throws RemoteException {
//        List<ArmorDTO> list = findArmor();
//        boolean isUpdate = false;
//        for (ArmorDTO armorDTO : list) {
//            if (armorDTO.getArmorID().equals(dto.getArmorID())) {
//                armorDTO.setClassfication(dto.getClassfication());
//                armorDTO.setDefense(dto.getDefense());
//                armorDTO.setDescription(dto.getDescription());
//                armorDTO.setStatus(dto.getStatus());
//                armorDTO.setTimeOfCreate(dto.getTimeOfCreate());
//                isUpdate = true;
//                break;
//            }
//        }
//        PrintWriter pw = null;
//        try {
//            pw = new PrintWriter(fileName);
//            for (ArmorDTO armorDTO : list) {
//                pw.println(armorDTO.toString());
//            }
//
//        } catch (FileNotFoundException e) {
//            isUpdate = false;
//        } finally {
//            if (pw != null) {
//                pw.close();
//            }
//        }
//        return isUpdate;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

}
