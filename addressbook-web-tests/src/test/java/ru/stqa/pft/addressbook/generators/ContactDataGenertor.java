package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Dominik on 2017-01-15.
 */
public class ContactDataGenertor {
    @Parameter(names = "-c", description = "Contact count")
    public int count;

    @Parameter (names = "-f", description = "Target file")
    public String file;

    @Parameter (names = "-d", description = "Data format")
    public String format;

    public static void main(String[] args) throws IOException {
        ContactDataGenertor generator = new ContactDataGenertor();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        } catch (ParameterException ex){
            jCommander.usage();
            return;
        }
        generator.run();
    }

    private void run() throws IOException {
        List<ContactData> contacts = generateContacts(count);
        if(format.equals("csv")) {
            saveAsCsv(contacts, new File(file));
        } else if (format.equals("xml")){
            saveAsXml(contacts,new File(file));
        } else if (format.equals("json")){
            saveAsJson(contacts,new File(file));
        }else {
            System.out.println("Unrecognized format" + format);
        }
    }

    private void saveAsJson(List<ContactData> groups, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(groups);
        try (Writer writer = new FileWriter(file)) {
            writer.write(json);
        }
    }

    private void saveAsXml(List<ContactData> groups, File file) throws IOException {
        XStream xstream = new XStream();
        xstream.processAnnotations(ContactData.class);
        String xml = xstream.toXML(groups);
        try (Writer writer = new FileWriter(file)) {
            writer.write(xml);
        }
    }

    private void saveAsCsv(List<ContactData> contacts, File file) throws IOException {
       try (Writer writer = new FileWriter(file)) {
           for (ContactData contact : contacts) {
               writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s\n", contact.getFirstname(), contact.getMiddlename(), contact.getLastname(),
                       contact.getNickname(), contact.getTitle(), contact.getCompany(), contact.getAddress(), contact.getHome(),
                       contact.getMobile(), contact.getWork(), contact.getEmail(), contact.getEmail2(), contact.getEmail3(),
                       contact.getAddress2(), contact.getGroup()));
           }
       }
    }

    private List<ContactData> generateContacts(int count) {
        List<ContactData> contacts = new ArrayList<ContactData>();
        for (int i = 0; i < count; i++){
            contacts.add(new ContactData()
                    .withFirstName(generateRandomString())
                    .withMiddlename(generateRandomString())
                    .withLastName(generateRandomString())
                    .withNickName(generateRandomString())
                    .withTitle(generateRandomString())
                    .withCompany(generateRandomString())
                    .withAddress(generateRandomString() + " " + generateRandomNumber() + " street\n" + generateRandomNumber() + "-" + generateRandomNumber() + "\n" + generateRandomString())
                    .withHome(generateRandomNumber() + "-" + generateRandomNumber() + "-" + generateRandomNumber())
                    .withMobile(generateRandomNumber() + "-" + generateRandomNumber() + "-" + generateRandomNumber())
                    .withWork(generateRandomNumber() + "-" + generateRandomNumber() + "-" + generateRandomNumber())
                    .withEmail(generateRandomString() + "@" + generateRandomString())
                    .withEmail2(generateRandomString() + "@" + generateRandomString())
                    .withEmail3(generateRandomString() + "@" + generateRandomString())
                    .withAddress2(generateRandomString() + " " + generateRandomNumber() + " street\n" + generateRandomNumber() + "-" + generateRandomNumber() + "\n" + generateRandomString())
                    .withGroup("test 1")
                         );

        }
        return contacts;
    }

    protected String generateRandomString(){
        String saltchars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 5) {
            int index = (int) (rnd.nextFloat() * saltchars.length());
            salt.append(saltchars.charAt(index));
        }
        String rndStr = salt.toString();
        return rndStr;
    }

    protected String generateRandomNumber(){
        String saltchars = "1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 3) {
            int index = (int) (rnd.nextFloat() * saltchars.length());
            salt.append(saltchars.charAt(index));
        }
        String rndStr = salt.toString();
        return rndStr;
    }
}
