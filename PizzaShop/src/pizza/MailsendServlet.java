package pizza;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MailsendServlet extends HttpServlet {
    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        //送信されたパラメータの値を取得
        String to = request.getParameter("to");
        String subject = request.getParameter("subject");
        String message = request.getParameter("message");
        String datetime = request.getParameter("datetime");//日時

        //SMTPサーバの設定
        Properties props = new Properties();

        //props.put "mail.smtp.host", "smtp.gmail.com");
        //以下は、gmailのsmtpサーバーを使う設定。
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.starttls.enable", "true");

        Session sess = Session.getInstance(props);

        try {
            MimeMessage msg = new MimeMessage(sess);
            InternetAddress[] toList = InternetAddress.parse(to);
            msg.addRecipients(MimeMessage.RecipientType.TO, toList);
            InternetAddress fromAddress = new InternetAddress("g12961ym@gm.tsuda.ac.jp");
            msg.setFrom(fromAddress);
            msg.setSentDate(new Date());
            msg.setSubject(subject, "ISO-2022-JP");

            //本文を改行文字で終わらせる。
            if (!message.endsWith("\n")) {
                message += "\n";
            }
            msg.setText(message, "ISO-2022-JP");
            //送信実行
            Transport tr = null;
            msg.saveChanges();
            tr = sess.getTransport("smtp");
            //tr.connect("g12961ym@gm.tsuda.ac.jp", pass);
            tr.sendMessage(msg, msg.getAllRecipients());
            tr.close();
        } catch (MessagingException e) {
            throw new ServletException(e);
        }
        RequestDispatcher rd = request.getRequestDispatcher("/MailSendServlet.java");
        rd.forward(request, response);

 
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
	
	
	
}
