package tn.happ.Model;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;


public class SendMail {
    public static void sendMail(String recepient) throws Exception {
        System.out.println("Preparing to send email");
        Properties properties = new Properties();

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");//switch the connection to TLS connection
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.ssl.trust", "*");
        String username = "entegra.product@gmail.com";
        String password = "entegra123";

        Session session = Session.getInstance(properties,
                new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        Message message = prepareMessage(session,username,recepient);

        Transport.send(message);
        System.out.println("Message sent successfully");
    }

    private static Message prepareMessage(Session session, String username, String recepient) {
        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress("username"));
            message.setRecipients(Message.RecipientType.TO, new InternetAddress[]{new InternetAddress(recepient)});
            message.setSubject("Our products are worth seeing");
            message.setText("Dear Miss/Mr , \n There are new products that you must see. \n " +
                    "\n Please take a look at the  of the following List : \n " +
                    "♣ Acidity:Acid is an important component in wines – particularly whites. It gives the wine freshness and zing. The more acidic a wine, the more refreshing, crisp and mouth-watering it will feel as you drink it.\n" +
                    "\n" +
                    "♣ Angular: Wine Folly’s description of an angular wine is perfect: “It’s like putting a triangle in your mouth  \n " +
                    "♣ Rich:Wines with full, pleasant flavours that are sweet and ‘rounded’ in nature are described as rich. In dry wines, richness may come from high alcohol, by complex flavours or by an oaky vanilla character. Decidedly sweet wines are also described as rich when the sweetness is backed up by fruity, ripe flavours.\n " +
                    "♣ Zesty:is typically used to describe livelier wines with crisper qualities, noticeable acidity and citrus notes, such as Sauvignon Blanc and Pinot Grigio." +
                    "\n \n I hope that you liked and enjoyed them. \n \n Waiting For your response \n Cordially \n ENTEGRA Product ");


            return  message;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
     /* try
            {
// Etape 2 : Création de l'objet Message
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress("entegra.product@gmail.com"));
                message.setRecipients(Message.RecipientType.TO,
                        InternetAddress.parse(destinataire));
                message.setSubject("A new product is worth seeing");
                message.setText("hey ,There is a new product that you must see  ");
// Etape 3 : Envoyer le message
                Transport.send(message);
                System.out.println("Message sent");
            } catch (AddressException e) {
                e.printStackTrace();
            } catch (javax.mail.MessagingException e) {
                e.printStackTrace();
            }
        }*/



