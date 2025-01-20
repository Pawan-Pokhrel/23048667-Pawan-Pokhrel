package com.NPLTicket.views;
import com.NPLTicket.controller.algorithms.BinarySearch;
import com.NPLTicket.controller.algorithms.InsertionSort;
import com.NPLTicket.controller.algorithms.MergeSort;
import com.NPLTicket.controller.algorithms.SearchMatchCards;
import com.NPLTicket.controller.algorithms.SelectionSort;
import com.NPLTicket.model.MatchModel;
import com.NPLTicket.model.TicketMatchModel;
import com.NPLTicket.model.TicketModel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import javax.swing.table.DefaultTableModel;

/**
 * @LMUID 23048667
 * @author Pawan Pokhrel
 */
public class NPLTicket extends javax.swing.JFrame {
    
    private LinkedList<MatchModel> matchList;
    private LinkedList<TicketMatchModel> ticketMatchList;
    private ArrayList<TicketModel> ticketList;
    private DefaultTableModel matchTableModel;
    private DefaultTableModel ticketTableModel;
    private DefaultTableModel ticketMatchesTableModel;
    private CardLayout menuLayout;
    private CardLayout bodyLayout;
    private CardLayout topLayout;
    private String user = "";
    
    public NPLTicket() {
        initComponents();
        loadComponents();
        initializeLayout();
        addInitialData();
        setIconImage(new ImageIcon(getClass().getResource("/com/NPLTicket/resources/icons/NPLImage.png")).getImage());
        setLocationRelativeTo(null);
    }
    
    
    private void initializeLayout() {
        bodyLayout = new CardLayout();
        pnlMainBody.setLayout(bodyLayout);
        
        pnlMainBody.add(pnlAdminMenu, "Admin Menu");
        pnlMainBody.add(pnlUserMenu, "User Menu");
        pnlMainBody.add(pnlHome, "Home");
        pnlMainBody.add(pnlManageMatchDetails, "Add Matches");
        pnlMainBody.add(pnlMatchesPage, "Matches");
        pnlMainBody.add(pnlDashboard, "Dashboard");
        pnlMainBody.add(pnlBuyTickets, "Buy Tickets");
        pnlMainBody.add(pnlManageTickets, "Tickets");
        pnlMainBody.add(pnlInvalidUserMessage, "Not Admin");
        bodyLayout.show(pnlMainBody, "Home");

        topLayout = new CardLayout();
        getContentPane().setLayout(topLayout);
        
        getContentPane().add(pnlLogIn, "Login");
        getContentPane().add(pnlMain, "Main");
        
        menuLayout = new CardLayout();
        pnlMenu.setLayout(menuLayout);
        
        pnlMenu.add(pnlAdminMenu, "Admin Menu");
        pnlMenu.add(pnlUserMenu, "User Menu");
        
        menuLayout.show(pnlMenu, "User Menu");
        topLayout.show(getContentPane(), "Main");
    }
    
    private void loadComponents() 
    {
        this.pnlLogIn.setOpaque(false);
        JPanel pnlBackground = new JPanel() {
            private final Image backgroundImage = new ImageIcon(getClass().getResource("/com/NPLTicket/resources/background.png")).getImage();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        pnlBackground.add(pnlLogIn);
        setContentPane(pnlBackground);
        hoverLabels();
        
        if(!"".equals(user)) {
            btnHomeLogIn.setText("Log Out");
        } else {
            btnHomeLogIn.setText("Log In");
        }
        
        pnlHomeHeader.setOpaque(false);   
    }
    
    private void setActive(javax.swing.JLabel label) {
        lblMenuHome.setBorder(null);
        lblMenuAddMatches.setBorder(null);
        lblMenuDashboard.setBorder(null);
        lblMenuMatches.setBorder(null);
        lblMenuTicket.setBorder(null);
        lblUserMenuHome.setBorder(null);
        lblUserMenuMatches.setBorder(null);
        lblUserMenuTicket.setBorder(null);
        lblMenuHome.setBackground(null);
        lblMenuAddMatches.setBackground(null);
        lblMenuDashboard.setBackground(null);
        lblMenuMatches.setBackground(null);
        lblMenuTicket.setBackground(null);
        lblUserMenuHome.setBackground(null);
        lblUserMenuMatches.setBackground(null);
        lblUserMenuTicket.setBackground(null);
        if(label != lblMenuHome && label != lblUserMenuHome) {
            label.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.WHITE));
            label.setBackground(new Color(59,84,108));
        }
    }
    
    private void hoverLabels() {
        addHoverEffect(lblMenuHome);
        addHoverEffect(lblMenuAddMatches);
        addHoverEffect(lblMenuDashboard);
        addHoverEffect(lblMenuMatches);
        addHoverEffect(lblMenuTicket);
        addHoverEffect(lblLogInToHome);
        addHoverEffect(lblLogInToMatches);
        addHoverEffect(lblLogInToTickets);
        addHoverEffect(lblUserMenuHome);
        addHoverEffect(lblUserMenuTicket);
        addHoverEffect(lblUserMenuMatches);
    }
    
    private void addHoverEffect(javax.swing.JLabel label) {
        Color defaultColor = Color.WHITE;
        Color hoverColor = new Color(200, 200, 200);

        label.setForeground(defaultColor);

        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                label.setForeground(hoverColor);
                label.setOpaque(true);
                label.setBackground(new Color(59,84,108));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                label.setForeground(defaultColor);
                label.setOpaque(false);
                label.setBackground(null);
            }
        });
    }
    
    private void addInitialData()
    { 
        addMatchData();
        addTicketData();
        addMatchCardData();
        addDashboardData();
        addTicketMatchData();
    }
    
    private void addMatchData() {
        
        matchList = new LinkedList<>(); 
        
        MatchModel match1 = new MatchModel(1, "Biratnagar Kings vs Janakpur Bolts", LocalDate.parse("2024-11-30"), LocalTime.parse("12:15"), "Janakpur won by 8 wickets", "19342");
        MatchModel match2 = new MatchModel(2, "Kathmandu Gurkhas vs Chitwan Rhinos", LocalDate.parse("2024-12-02"), LocalTime.parse("09:15"), "Chitwan won by 5 wickets", "6987");
        MatchModel match3 = new MatchModel(3, "Janakpur Bolts vs Karnali Yaks", LocalDate.parse("2024-12-02"), LocalTime.parse("13:15"), "Janakpur won by 8 wickets", "8976");
        MatchModel match4 = new MatchModel(4, "Biratnagar Kings vs Sudur Paschim Royals", LocalDate.parse("2024-12-03"), LocalTime.parse("09:15"), "Sudur Paschim won by 90 runs", "7852");
        MatchModel match5 = new MatchModel(5, "Chitwan Rhinos vs Pokhara Avengers", LocalDate.parse("2024-12-03"), LocalTime.parse("13:15"), "Chitwan won by 87 runs", "9430");
        MatchModel match6 = new MatchModel(6, "Kathmandu Gurkhas vs Karnali Yaks", LocalDate.parse("2024-12-04"), LocalTime.parse("09:15"), "Kathmandu won by 3 wickets", "12043");
        MatchModel match7 = new MatchModel(7, "Biratnagar Kings vs Lumbini Lions", LocalDate.parse("2024-12-04"), LocalTime.parse("13:15"), "Biratnagar won by 2 wickets", "11078");
        MatchModel match8 = new MatchModel(8, "Janakpur Bolts vs Pokhara Avengers", LocalDate.parse("2024-12-05"), LocalTime.parse("09:15"), "Janakpur won by 7 wickets", "8902");
        MatchModel match9 = new MatchModel(9, "Kathmandu Gurkhas vs Sudur Paschim Royals", LocalDate.parse("2024-12-05"), LocalTime.parse("13:15"), "Sudur Paschim won by 73 runs", "7623");
        MatchModel match10 = new MatchModel(10, "Chitwan Rhinos vs Karnali Yaks", LocalDate.parse("2024-12-06"), LocalTime.parse("09:15"), "Karnali won by 6 wickets", "10398");
        MatchModel match11 = new MatchModel(11, "Lumbini Lions vs Pokhara Avengers", LocalDate.parse("2024-12-06"), LocalTime.parse("13:15"), "Pokhara won by 10 wickets", "8320");
        MatchModel match12 = new MatchModel(12, "Lumbini Lions vs Sudur Paschim Royals", LocalDate.parse("2024-12-07"), LocalTime.parse("09:15"), "Sudur Paschim won by 45 runs", "7012");
        MatchModel match13 = new MatchModel(13, "Biratnagar Kings vs Karnali Yaks", LocalDate.parse("2024-12-07"), LocalTime.parse("13:15"), "Karnali won by 7 runs", "9500");
        MatchModel match14 = new MatchModel(14, "Janakpur Bolts vs Lumbini Lions", LocalDate.parse("2024-12-08"), LocalTime.parse("09:15"), "Janakpur won by 1 run", "11009");
        MatchModel match15 = new MatchModel(15, "Chitwan Rhinos vs Sudur Paschim Royals", LocalDate.parse("2024-12-08"), LocalTime.parse("13:15"), "Chitwan won by 33 runs", "9784");
        MatchModel match16 = new MatchModel(16, "Chitwan Rhinos vs Lumbini Lions", LocalDate.parse("2024-12-09"), LocalTime.parse("09:15"), "Lumbini won by 33 runs", "8540");
        MatchModel match17 = new MatchModel(17, "Karnali yaks vs Pokhara Avengers", LocalDate.parse("2024-12-09"), LocalTime.parse("13:15"), "Karnali won by 7 wickets", "10023");
        MatchModel match18 = new MatchModel(18, "Janakpur Bolts vs Sudur Paschim Royals", LocalDate.parse("2024-12-10"), LocalTime.parse("09:15"), "Sudur Paschim won by 72 runs", "9310");
        MatchModel match19 = new MatchModel(19, "Kathmandu Gurkhas vs Lumbini Lions", LocalDate.parse("2024-12-10"), LocalTime.parse("13:15"), "Kathmadu won by 18 runs", "12045");
        MatchModel match20 = new MatchModel(20, "Biratnagar Kings vs Pokhara Avengers", LocalDate.parse("2024-12-11"), LocalTime.parse("09:15"), "Match Tied (Pokhara won the Super over)", "8492");
        MatchModel match21 = new MatchModel(21, "Janakpur Bolts vs Kathmandu Gurkhas", LocalDate.parse("2024-12-11"), LocalTime.parse("13:15"), "Janakpur won by 5 wickets", "8931");
        MatchModel match22 = new MatchModel(22, "Karnali Yaks vs Lumbini Lions", LocalDate.parse("2024-12-12"), LocalTime.parse("09:15"), "Karnali won by 5 runs", "10132");
        MatchModel match23 = new MatchModel(23, "Biratnagar Kings vs Chitwan Rhinos", LocalDate.parse("2024-12-12"), LocalTime.parse("13:15"), "TBD", "0");
        MatchModel match24 = new MatchModel(24, "Kathmandu Gurkhas vs Pokhara Avengers", LocalDate.parse("2024-12-13"), LocalTime.parse("09:15"), "TBD", "0");
        MatchModel match25 = new MatchModel(25, "Chitwan Rhinos vs Janakpur Bolts", LocalDate.parse("2024-12-13"), LocalTime.parse("13:15"), "TBD", "0");
        MatchModel match26 = new MatchModel(26, "Biratnagar Kings vs Kathmandu Gurkhas", LocalDate.parse("2024-12-14"), LocalTime.parse("09:15"), "TBD", "0");
        MatchModel match27 = new MatchModel(27, "Pokhara Avengers vs Sudur Paschim Royals", LocalDate.parse("2024-12-14"), LocalTime.parse("13:15"), "TBD", "0");
        MatchModel match28 = new MatchModel(28, "Karnali Yaks vs Sudur Paschim Royals", LocalDate.parse("2024-12-15"), LocalTime.parse("09:15"), "TBD", "0");

        addMatch(match1);
        addMatch(match2);
        addMatch(match3);
        addMatch(match4);
        addMatch(match5);
        addMatch(match6);
        addMatch(match7);
        addMatch(match8);
        addMatch(match9);
        addMatch(match10);
        addMatch(match11);
        addMatch(match12);
        addMatch(match13);
        addMatch(match14);
        addMatch(match15);
        addMatch(match16);
        addMatch(match17);
        addMatch(match18);
        addMatch(match19);
        addMatch(match20);
        addMatch(match21);
        addMatch(match22);
        addMatch(match23);
        addMatch(match24);
        addMatch(match25);
        addMatch(match26);
        addMatch(match27);
        addMatch(match28);
    }
    
    private void addTicketData() {
        
        ticketList = new ArrayList<>();
        
        TicketModel ticket1 = new TicketModel("ZoneVIP_1", 1, "Keshav Raj Pokharel", "9840275490", "keshabpokharel054@gmail.com", "VIP", LocalDate.parse("2024-11-26"), "E-sewa");
        TicketModel ticket2 = new TicketModel("Zone3_2", 1, "Mijal Sapkota", "9863535178", "sapmzal12@gmail.com", "3", LocalDate.parse("2024-11-28"), "Khalti");
        TicketModel ticket3 = new TicketModel("Zone1_3", 2, "Anisha Mahato", "9845627381", "mahatoanisha2003@gmail.com", "1", LocalDate.parse("2024-11-29"), "Bank Transfer");
        TicketModel ticket4 = new TicketModel("Zone3_4", 2, "Rahul Kumar Shah", "9802312788", "rkshah078@gmail.com", "3", LocalDate.parse("2024-11-30"), "E-sewa");
        TicketModel ticket5 = new TicketModel("ZoneVIP_5", 3, "Pawan Pokhrel", "9746478675", "pawanpokhrel1111@gmail.com", "VIP", LocalDate.parse("2024-11-29"), "IME Pay");
        TicketModel ticket6 = new TicketModel("Zone2_6", 3, "Sita Sharma", "9812345678", "sita.sharma98@gmail.com", "2", LocalDate.parse("2024-11-28"), "E-sewa");
        TicketModel ticket7 = new TicketModel("Zone1_7", 4, "Rajendra Yadav", "9846791234", "rajendra.yadav@gmail.com", "1", LocalDate.parse("2024-11-29"), "Bank Transfer");
        TicketModel ticket8 = new TicketModel("ZoneVIP_8", 4, "Maya Devi", "9865987654", "maya.devi12@gmail.com", "VIP", LocalDate.parse("2024-12-01"), "Khalti");
        TicketModel ticket9 = new TicketModel("Zone3_9", 5, "Binod Pandey", "9817654321", "binod.pandey@gmail.com", "3", LocalDate.parse("2024-12-01"), "E-sewa");
        TicketModel ticket10 = new TicketModel("Zone2_10", 5, "Rohit Sharma", "9801234567", "rohit.sharma@gmail.com", "2", LocalDate.parse("2024-11-30"), "Bank Transfer");
        TicketModel ticket11 = new TicketModel("Zone1_11", 6, "Nisha Thapa", "9844567890", "nisha.thapa12@gmail.com", "1", LocalDate.parse("2024-12-01"), "E-sewa");
        TicketModel ticket12 = new TicketModel("ZoneVIP_12", 6, "Suraj Kumar", "9812345689", "suraj.kumar22@gmail.com", "VIP", LocalDate.parse("2024-12-02"), "IME Pay");
        TicketModel ticket13 = new TicketModel("Zone3_13", 7, "Laxmi Rai", "9846781234", "laxmi.rai99@gmail.com", "3", LocalDate.parse("2024-12-01"), "Bank Transfer");
        TicketModel ticket14 = new TicketModel("Zone2_14", 7, "Ramesh Gurung", "9809876543", "ramesh.gurung21@gmail.com", "2", LocalDate.parse("2024-12-03"), "Khalti");
        TicketModel ticket15 = new TicketModel("Zone1_15", 8, "Bishal Kumar", "9812134578", "bishal.kumar@gmail.com", "1", LocalDate.parse("2024-12-02"), "E-sewa");
        TicketModel ticket16 = new TicketModel("ZoneVIP_16", 8, "Manisha Joshi", "9863456789", "manisha.joshi@gmail.com", "VIP", LocalDate.parse("2024-12-03"), "Bank Transfer");
        TicketModel ticket17 = new TicketModel("Zone3_17", 9, "Ravi Shrestha", "9849012345", "ravi.shrestha77@gmail.com", "3", LocalDate.parse("2024-12-04"), "IME Pay");
        TicketModel ticket18 = new TicketModel("Zone2_18", 9, "Pooja Khadka", "9812346790", "pooja.khadka@gmail.com", "2", LocalDate.parse("2024-12-02"), "E-sewa");
        TicketModel ticket19 = new TicketModel("Zone1_19", 10, "Shiva Prasad", "9845678901", "shiva.prasad12@gmail.com", "1", LocalDate.parse("2024-12-03"), "Bank Transfer");
        TicketModel ticket20 = new TicketModel("ZoneVIP_20", 10, "Gita Rathi", "9862345678", "gita.rathi22@gmail.com", "VIP", LocalDate.parse("2024-12-05"), "Khalti");
        TicketModel ticket21 = new TicketModel("Zone3_21", 11, "Dinesh Kumar", "9810987654", "dinesh.kumar@hotmail.com", "3", LocalDate.parse("2024-12-04"), "E-sewa");
        TicketModel ticket22 = new TicketModel("Zone2_22", 11, "Suman Poudel", "9807654321", "suman.poudel23@gmail.com", "2", LocalDate.parse("2024-12-05"), "Bank Transfer");
        TicketModel ticket23 = new TicketModel("ZoneVIP_23", 12, "Pradeep Rai", "9846712345", "pradeep.rai45@gmail.com", "VIP", LocalDate.parse("2024-12-06"), "IME Pay");
        TicketModel ticket24 = new TicketModel("Zone1_24", 12, "Prashant Yadav", "9817654323", "prashant.yadav@gmail.com", "1", LocalDate.parse("2024-12-05"), "E-sewa");
        TicketModel ticket25 = new TicketModel("Zone3_25", 13, "Sabina Thapa", "9846798987", "sabina.thapa29@gmail.com", "3", LocalDate.parse("2024-12-07"), "Bank Transfer");
        TicketModel ticket26 = new TicketModel("Zone2_26", 13, "Dinesh Adhikari", "9812345676", "dinesh.adhikari90@gmail.com", "2", LocalDate.parse("2024-12-05"), "E-sewa");
        TicketModel ticket27 = new TicketModel("Zone1_27", 14, "Samir Thapa", "9845674321", "samir.thapa11@gmail.com", "1", LocalDate.parse("2024-12-06"), "IME Pay");
        TicketModel ticket28 = new TicketModel("ZoneVIP_28", 14, "Rohit Joshi", "9865687432", "rohit.joshi77@gmail.com", "VIP", LocalDate.parse("2024-12-06"), "Bank Transfer");
        TicketModel ticket29 = new TicketModel("Zone3_29", 15, "Rina Rai", "9849012387", "rina.rai30@gmail.com", "3", LocalDate.parse("2024-12-08"), "Khalti");
        TicketModel ticket30 = new TicketModel("Zone2_30", 15, "Ramesh Karki", "9801238765", "ramesh.karki99@gmail.com", "2", LocalDate.parse("2024-12-08"), "E-sewa");
        TicketModel ticket31 = new TicketModel("Zone1_31", 16, "Sushant Shrestha", "9810982345", "sushant.shrestha@gmail.com", "1", LocalDate.parse("2024-12-09"), "IME Pay");
        TicketModel ticket32 = new TicketModel("ZoneVIP_32", 16, "Manoj Kumar", "9865671234", "manoj.kumar45@gmail.com", "VIP", LocalDate.parse("2024-12-08"), "E-sewa");
        TicketModel ticket33 = new TicketModel("Zone3_33", 17, "Sunita Shah", "9846571234", "sunita.shah44@gmail.com", "3", LocalDate.parse("2024-12-07"), "Bank Transfer");
        TicketModel ticket34 = new TicketModel("Zone2_34", 17, "Anil Pandey", "9812145678", "anil.pandey67@gmail.com", "2", LocalDate.parse("2024-12-08"), "E-sewa");
        TicketModel ticket35 = new TicketModel("Zone1_35", 18, "Nabin Thapa", "9845612345", "nabin.thapa87@gmail.com", "1", LocalDate.parse("2024-12-09"), "Khalti");
        TicketModel ticket36 = new TicketModel("ZoneVIP_36", 18, "Bhanu Prasad", "9865678901", "bhanu.prasad98@gmail.com", "VIP", LocalDate.parse("2024-12-07"), "IME Pay");
        TicketModel ticket37 = new TicketModel("Zone3_37", 19, "Pritam Kumari", "9810987654", "pritam.kumari@gmail.com", "3", LocalDate.parse("2024-12-09"), "Bank Transfer");
        TicketModel ticket38 = new TicketModel("Zone2_38", 19, "Suman Joshi", "9801234756", "suman.joshipnp@gmail.com", "2", LocalDate.parse("2024-12-10"), "E-sewa");
        TicketModel ticket39 = new TicketModel("Zone1_39", 20, "Raj Kumar", "9846783490", "raj.kumar24@gmail.com", "1", LocalDate.parse("2024-12-10"), "Bank Transfer");
        TicketModel ticket40 = new TicketModel("ZoneVIP_40", 20, "Sita Kumari", "9866543210", "sita.kumari40@gmail.com", "VIP", LocalDate.parse("2024-12-11"), "Khalti");
        TicketModel ticket41 = new TicketModel("Zone3_41", 21, "Bishal Kumar", "9810987623", "bishal.kumar@live.com", "3", LocalDate.parse("2024-12-11"), "E-sewa");
        TicketModel ticket42 = new TicketModel("Zone2_42", 21, "Gita Sharma", "9809876543", "gita.sharma44@gmail.com", "2", LocalDate.parse("2024-12-12"), "IME Pay");
        TicketModel ticket43 = new TicketModel("Zone1_43", 22, "Anjana Rai", "9812345789", "anjana.rai76@gmail.com", "1", LocalDate.parse("2024-12-13"), "Bank Transfer");
        TicketModel ticket44 = new TicketModel("ZoneVIP_44", 22, "Sunil Shrestha", "9862345687", "sunil.shrestha@gmail.com", "VIP", LocalDate.parse("2024-12-13"), "E-sewa");
        TicketModel ticket45 = new TicketModel("Zone3_45", 23, "Sanjeev Adhikari", "9810982345", "sanjeev.adhikari67@gmail.com", "3", LocalDate.parse("2024-12-14"), "Bank Transfer");
        TicketModel ticket46 = new TicketModel("Zone2_46", 23, "Sujan Pandey", "9801234756", "sujan.pandey90@gmail.com", "2", LocalDate.parse("2024-12-15"), "IME Pay");
        TicketModel ticket47 = new TicketModel("Zone1_47", 24, "Amit Kumar", "9846781234", "amit.kumar12@gmail.com", "1", LocalDate.parse("2024-12-14"), "E-sewa");
        TicketModel ticket48 = new TicketModel("ZoneVIP_48", 24, "Aparna Yadav", "9863456789", "aparna.yadav12@gmail.com", "VIP", LocalDate.parse("2024-12-16"), "Khalti");
        TicketModel ticket49 = new TicketModel("Zone3_49", 25, "Bikash Kumar", "9810987654", "bikash.kumar65@gmail.com", "3", LocalDate.parse("2024-12-16"), "Bank Transfer");
        TicketModel ticket50 = new TicketModel("Zone2_50", 25, "Nina Thapa", "9801234789", "nina.thapa12@gmail.com", "2", LocalDate.parse("2024-12-17"), "E-sewa");
        TicketModel ticket51 = new TicketModel("Zone1_51", 26, "Sarita Kumar", "9845789012", "sarita.kumar87@gmail.com", "1", LocalDate.parse("2024-12-18"), "IME Pay");
        TicketModel ticket52 = new TicketModel("ZoneVIP_52", 26, "Pooja Sharma", "9864321567", "pooja.sharma@gmail.com", "VIP", LocalDate.parse("2024-12-18"), "Bank Transfer");
        TicketModel ticket53 = new TicketModel("Zone3_53", 27, "Dinesh Yadav", "9810987654", "dinesh.yadav12@gmail.com", "3", LocalDate.parse("2024-12-19"), "E-sewa");
        TicketModel ticket54 = new TicketModel("Zone2_54", 27, "Samjhana Thapa", "9801234567", "samjhana.thapa23@gmail.com", "2", LocalDate.parse("2024-12-19"), "IME Pay");
        TicketModel ticket55 = new TicketModel("Zone1_55", 28, "Sujata Joshi", "9846781235", "sujata.joshi@yahoo.com", "1", LocalDate.parse("2024-12-20"), "Bank Transfer");
        TicketModel ticket56 = new TicketModel("ZoneVIP_56", 28, "Suman Sharma", "9867654321", "suman.sharma@gmail.com", "VIP", LocalDate.parse("2024-12-20"), "E-sewa");


        addTicket(ticket1);
        addTicket(ticket2);
        addTicket(ticket3);
        addTicket(ticket4);
        addTicket(ticket5);
        addTicket(ticket6);
        addTicket(ticket7);
        addTicket(ticket8);
        addTicket(ticket9);
        addTicket(ticket10);
        addTicket(ticket11);
        addTicket(ticket12);
        addTicket(ticket13);
        addTicket(ticket14);
        addTicket(ticket15);
        addTicket(ticket16);
        addTicket(ticket17);
        addTicket(ticket18);
        addTicket(ticket19);
        addTicket(ticket20);
        addTicket(ticket21);
        addTicket(ticket22);
        addTicket(ticket23);
        addTicket(ticket24);
        addTicket(ticket25);
        addTicket(ticket26);
        addTicket(ticket27);
        addTicket(ticket28);
        addTicket(ticket29);
        addTicket(ticket30);
        addTicket(ticket31);
        addTicket(ticket32);
        addTicket(ticket33);
        addTicket(ticket34);
        addTicket(ticket35);
        addTicket(ticket36);
        addTicket(ticket37);
        addTicket(ticket38);
        addTicket(ticket39);
        addTicket(ticket40);
        addTicket(ticket41);
        addTicket(ticket42);
        addTicket(ticket43);
        addTicket(ticket44);
        addTicket(ticket45);
        addTicket(ticket46);
        addTicket(ticket47);
        addTicket(ticket48);
        addTicket(ticket49);
        addTicket(ticket50);
        addTicket(ticket51);
        addTicket(ticket52);
        addTicket(ticket53);
        addTicket(ticket54);
        addTicket(ticket55);
        addTicket(ticket56);
    }
    
    
    
    private void addMatchCardData() {
        pnlMatches.removeAll();
        pnlMatches.revalidate();
        pnlMatches.repaint();
        pnlMatches.setLayout(new FlowLayout());
        Icon versusIcon = new ImageIcon(getClass().getResource("/com/NPLTicket/resources/matches/versusIcon.png"));
        LinkedList<MatchModel> matchesList = new LinkedList();
        if("".equals(txtFldMatchesSearch.getText())) {
            matchesList.addAll(matchList);
        } else {
            SearchMatchCards searchMatch = new SearchMatchCards();
            matchesList.addAll(searchMatch.searchMatches(txtFldMatchesSearch.getText(), matchList));
        }

        for (int i = 0; i < matchesList.size(); i++) {
            String teams = matchesList.get(i).getTeams();
            int indexOfVs = teams.indexOf(" vs ");

            String team1 = teams.substring(0, indexOfVs);
            String team2 = teams.substring(indexOfVs + 4);
            
            Icon team1Img;
            Icon team2Img;

            try { 
                team1Img = new ImageIcon(getClass().getResource("/com/NPLTicket/resources/matches/" + team1.substring(0, 3) + ".png"));
            } catch (NullPointerException e) {
                team1Img = new ImageIcon(getClass().getResource("/com/NPLTicket/resources/matches/tbd.png"));
            }
            
            try {
                team2Img = new ImageIcon(getClass().getResource("/com/NPLTicket/resources/matches/" + team2.substring(0, 3) + ".png"));
            } catch (NullPointerException e) {
                team2Img = new ImageIcon(getClass().getResource("/com/NPLTicket/resources/matches/tbd.png"));
            }

            JPanel matchPanel = new JPanel();
            matchPanel.setMaximumSize(new Dimension(215,200));
            matchPanel.setMinimumSize(new Dimension(215,200));
            matchPanel.setPreferredSize(new Dimension(215,200));
            matchPanel.setLayout(new FlowLayout());

            matchPanel.setBackground(Color.WHITE);

            JLabel lblTeam1Img = new JLabel("", team1Img, JLabel.LEFT);
            JLabel lblVs = new JLabel("", versusIcon, JLabel.CENTER);
            JLabel lblTeam2Img = new JLabel("", team2Img, JLabel.RIGHT);

            JLabel lblTeam1 = new JLabel(team1 + " vs ");
            JLabel lblTeam2 = new JLabel(team2);
            JLabel lblMatchDate = new JLabel("Match Date: " + matchesList.get(i).getMatchDate());
            JLabel lblMatchTime = new JLabel("Time: " + matchesList.get(i).getMatchTime());
            
            JLabel txtAreaMatchStatus = new JLabel(matchesList.get(i).getMatchStatus());
            txtAreaMatchStatus.setForeground(Color.BLUE );
            txtAreaMatchStatus.setFocusable(false);

            matchPanel.add(lblTeam1Img);
            matchPanel.add(lblVs);
            matchPanel.add(lblTeam2Img);
            matchPanel.add(lblTeam1);
            matchPanel.add(lblTeam2);
            matchPanel.add(lblMatchDate);
            matchPanel.add(lblMatchTime);
            matchPanel.add(txtAreaMatchStatus);

            pnlMatches.add(matchPanel);
        }
    }
    
    private void addDashboardData() {
        int playedMatches = 0;
        int totalTicketSales = 0;
        for(MatchModel match: matchList) {
            if(!"tbd".equals(match.getMatchStatus().toLowerCase())){
                playedMatches++;
            }
            totalTicketSales += Integer.parseInt(match.getMatchSeats());
        }
        lblPlayedMatchesCount.setText(String.valueOf(playedMatches));
        lblNextMatch.setText(matchList.get(playedMatches).getTeams());
        lblNextMatchDate.setText("Date : " + matchList.get(playedMatches).getMatchDate() + "           Time : " + matchList.get(playedMatches).getMatchTime());
        lblRemMatchesCount.setText(String.valueOf(matchList.size() - playedMatches));
        lblTotalTicketSales.setText(String.valueOf(totalTicketSales));
        lblTicketEarnings.setText("Rs. " + (totalTicketSales * 300));
    }
    
    private void addTicketMatchData() {
        ticketMatchList = new LinkedList<>();
        ticketMatchesTableModel = (DefaultTableModel) tblTicketMatches.getModel();
        ticketMatchesTableModel.setRowCount(0);
        cbBuyTicketMatches.removeAllItems();
        cbBuyTicketMatches.addItem("--Select--");
        for (MatchModel match: matchList) {
            TicketMatchModel ticketMatch = new TicketMatchModel(match.getMatchNo(), match.getTeams(), match.getMatchDate(), match.getMatchTime());
            if("tbd".equals(match.getMatchStatus().toLowerCase())) {
                ticketMatchList.add(ticketMatch);
                ticketMatchesTableModel.addRow(new Object[] {
                    match.getMatchNo(), match.getTeams(), match.getMatchDate(), match.getMatchTime()
                });
                cbBuyTicketMatches.addItem(match.getTeams());
            }
        }
    }
    
    private void addMatchSearchData() {
        try {
            int searchMatch = Integer.parseInt(txtFldSearchMatch.getText().trim());

            SelectionSort selectionSort = new SelectionSort();
            selectionSort.sortByMatchNo(matchList, true);

            BinarySearch search = new BinarySearch();
            MatchModel searchedData = search.searchMatchDetails(searchMatch, matchList, 0, matchList.size() - 1);

            if (searchedData == null) {
                JOptionPane.showMessageDialog(null, "No match found for Match No: " + searchMatch, "Search Result", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            DefaultTableModel tblMatchModel = (DefaultTableModel) tblMatches.getModel();
            tblMatchModel.setRowCount(0);
            tblMatchModel.addRow(new Object[]{
                searchedData.getMatchNo(),
                searchedData.getTeams(),
                searchedData.getMatchDate(),
                searchedData.getMatchTime(),
                searchedData.getMatchStatus(),
                searchedData.getMatchSeats()
            });
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter a valid match number.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "An unexpected error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    
    private void addTicketSearchData() {
        String searchValue = txtFldSearchTicketOwner.getText();
        BinarySearch search = new BinarySearch();
        SelectionSort selectionSort = new SelectionSort();
        selectionSort.sortByTicketOwnerName(ticketList, true);
        TicketModel searchedData = search.searchTicketDetails(searchValue, ticketList, 0, ticketList.size() - 1);
        ticketTableModel = (DefaultTableModel) tblTicketDetails.getModel();
        ticketTableModel.setRowCount(0);
        ticketTableModel.addRow(new Object[] {
            searchedData.getTicketId(), searchedData.getMatchNo(), searchedData.getTicketOwnerName(), searchedData.getTicketOwnerPhone(), searchedData.getTicketOwnerEmail(),searchedData.getTicketZone(), searchedData.getBookingDate(), searchedData.getPaymentMethod()
        });
    }

    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlLogIn = new javax.swing.JPanel();
        lblLogInFullLogo = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lblLogInHeader = new javax.swing.JLabel();
        lblLogInPassword = new javax.swing.JLabel();
        lblLogInUsername = new javax.swing.JLabel();
        txtFldLogInUsername = new javax.swing.JTextField();
        pswFldLogInPassword = new javax.swing.JPasswordField();
        btnLogIn = new javax.swing.JButton();
        lblForgotPassword = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        lblLogInNPLImage = new javax.swing.JLabel();
        lblLogInNPLTxt = new javax.swing.JLabel();
        pnlHome = new javax.swing.JPanel();
        lblHomeHeader = new javax.swing.JLabel();
        btnHomeLogIn = new javax.swing.JButton();
        btnHomeToLeagueStandings = new javax.swing.JButton();
        btnHomeToMatches = new javax.swing.JButton();
        lblHomeLeftImage = new javax.swing.JLabel();
        lblHomeRightImage = new javax.swing.JLabel();
        pnlPitch = new javax.swing.JPanel();
        pnlMidPitch = new javax.swing.JPanel();
        lblWelcome1 = new javax.swing.JLabel();
        lblWelcome2 = new javax.swing.JLabel();
        lblWelcome3 = new javax.swing.JLabel();
        lblWelcome4 = new javax.swing.JLabel();
        lblWelcome5 = new javax.swing.JLabel();
        lblWelcome6 = new javax.swing.JLabel();
        lblWelcome7 = new javax.swing.JLabel();
        pnlCrease1 = new javax.swing.JPanel();
        pnlCrease2 = new javax.swing.JPanel();
        pnlDashboard = new javax.swing.JPanel();
        scrlPaneTableMatches = new javax.swing.JScrollPane();
        tblMatches = new javax.swing.JTable();
        scrlPaneTicketDetailsTable = new javax.swing.JScrollPane();
        tblTicketDetails = new javax.swing.JTable();
        lblTicketDetailsTable = new javax.swing.JLabel();
        lblMatchDetailsTable = new javax.swing.JLabel();
        btnManageMatchDetails = new javax.swing.JButton();
        btnManageTicketDetails = new javax.swing.JButton();
        pnlAdmin = new javax.swing.JPanel();
        pnlManageMatch = new javax.swing.JPanel();
        pnlMatchDetailsForm = new javax.swing.JPanel();
        lblMatchTeams = new javax.swing.JLabel();
        txtFldMatchTeams = new javax.swing.JTextField();
        lblMatchDate = new javax.swing.JLabel();
        txtFldMatchDate = new javax.swing.JTextField();
        lblMatchDate1 = new javax.swing.JLabel();
        txtFldMatchTime = new javax.swing.JTextField();
        btnAddMatch = new javax.swing.JButton();
        lblAddMatch = new javax.swing.JLabel();
        pnlUpdateMatchStatus = new javax.swing.JPanel();
        lblUpdateMatchStatus = new javax.swing.JLabel();
        lblUpdateMatchNo = new javax.swing.JLabel();
        txtUpdateMatchNo = new javax.swing.JTextField();
        btnUpdateMatchStatus = new javax.swing.JButton();
        lblUpdateStatus = new javax.swing.JLabel();
        txtFldMatchStatus = new javax.swing.JTextField();
        pnlManageTicketDetails = new javax.swing.JPanel();
        pnlAddTicketDetails = new javax.swing.JPanel();
        lblAddTicketDetails = new javax.swing.JLabel();
        lblTicketOwnername = new javax.swing.JLabel();
        lblTicketOwnerPhone = new javax.swing.JLabel();
        lblTicketOwnerEmail = new javax.swing.JLabel();
        lblTicketMatchNo = new javax.swing.JLabel();
        lblTicketZone = new javax.swing.JLabel();
        txtFldTicketOwnerName = new javax.swing.JTextField();
        txtFldTicketOwnerPhone = new javax.swing.JTextField();
        txtFldTicketOwnerEmail = new javax.swing.JTextField();
        cbTicketZone = new javax.swing.JComboBox<>();
        txtFldTicketMatchNo = new javax.swing.JTextField();
        btnAddTicket = new javax.swing.JButton();
        pnlRemoveTicket = new javax.swing.JPanel();
        lblRemoveTicket = new javax.swing.JLabel();
        btnRemoveTicket = new javax.swing.JButton();
        txtFldRemoveTicketID = new javax.swing.JTextField();
        lblRemoveTicketID = new javax.swing.JLabel();
        pnlMatchesPage = new javax.swing.JPanel();
        scrlPaneMatches = new javax.swing.JScrollPane();
        pnlMatches = new javax.swing.JPanel();
        lblMatchesPageHeader = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        pnlMain = new javax.swing.JPanel();
        pnlMainMenu = new javax.swing.JPanel();
        lblMenuLogo = new javax.swing.JLabel();
        lblMenuDashboard = new javax.swing.JLabel();
        lblMenuHome = new javax.swing.JLabel();
        lblMenuAdmin = new javax.swing.JLabel();
        lblMenuContactUs = new javax.swing.JLabel();
        lblMenuMatches = new javax.swing.JLabel();
        lblMenuTicket = new javax.swing.JLabel();
        pnlFooter = new javax.swing.JPanel();
        pnlMainBody = new javax.swing.JPanel();

        pnlLogIn.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        pnlLogIn.setMaximumSize(new java.awt.Dimension(1120, 630));
        pnlLogIn.setMinimumSize(new java.awt.Dimension(1120, 630));
        pnlLogIn.setPreferredSize(new java.awt.Dimension(1120, 630));

        lblLogInFullLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/NPLTicket/views/NPLFullLogo.png"))); // NOI18N

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setMaximumSize(new java.awt.Dimension(400, 450));
        jPanel2.setMinimumSize(new java.awt.Dimension(400, 450));
        jPanel2.setOpaque(false);

        lblLogInHeader.setFont(new java.awt.Font("SansSerif", 1, 48)); // NOI18N
        lblLogInHeader.setForeground(new java.awt.Color(255, 255, 255));
        lblLogInHeader.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLogInHeader.setText("Log In");

        lblLogInPassword.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        lblLogInPassword.setForeground(new java.awt.Color(255, 255, 255));
        lblLogInPassword.setText("Password:");

        lblLogInUsername.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        lblLogInUsername.setForeground(new java.awt.Color(255, 255, 255));
        lblLogInUsername.setText("Username:");

        txtFldLogInUsername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFldLogInUsernameActionPerformed(evt);
            }
        });

        btnLogIn.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        btnLogIn.setText("Log In");
        btnLogIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogInActionPerformed(evt);
            }
        });

        lblForgotPassword.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        lblForgotPassword.setForeground(new java.awt.Color(255, 255, 255));
        lblForgotPassword.setText("Forgot Password\n");
        lblForgotPassword.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnLogIn)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(pswFldLogInPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addContainerGap()
                                            .addComponent(lblLogInHeader))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addGap(70, 70, 70)
                                            .addComponent(lblLogInPassword)))
                                    .addComponent(lblLogInUsername))
                                .addGap(113, 113, 113)))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(69, 69, 69)
                            .addComponent(txtFldLogInUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lblForgotPassword))
                .addContainerGap(123, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnLogIn, pswFldLogInPassword, txtFldLogInUsername});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblLogInHeader)
                .addGap(36, 36, 36)
                .addComponent(lblLogInUsername)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtFldLogInUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblLogInPassword)
                .addGap(18, 18, 18)
                .addComponent(pswFldLogInPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(lblForgotPassword)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnLogIn)
                .addContainerGap(113, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnLogIn, pswFldLogInPassword});

        jPanel3.setMaximumSize(new java.awt.Dimension(560, 450));
        jPanel3.setMinimumSize(new java.awt.Dimension(560, 450));
        jPanel3.setOpaque(false);
        jPanel3.setPreferredSize(new java.awt.Dimension(560, 450));

        lblLogInNPLImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/NPLTicket/views/NPLImage.png"))); // NOI18N

        lblLogInNPLTxt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/NPLTicket/views/NPLTxt.png"))); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(lblLogInNPLImage))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(lblLogInNPLTxt)))
                .addContainerGap(216, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(lblLogInNPLImage, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblLogInNPLTxt)
                .addContainerGap(79, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlLogInLayout = new javax.swing.GroupLayout(pnlLogIn);
        pnlLogIn.setLayout(pnlLogInLayout);
        pnlLogInLayout.setHorizontalGroup(
            pnlLogInLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLogInLayout.createSequentialGroup()
                .addGroup(pnlLogInLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblLogInFullLogo)
                    .addGroup(pnlLogInLayout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlLogInLayout.setVerticalGroup(
            pnlLogInLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLogInLayout.createSequentialGroup()
                .addGroup(pnlLogInLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlLogInLayout.createSequentialGroup()
                        .addComponent(lblLogInFullLogo)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlLogInLayout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 524, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(53, Short.MAX_VALUE))
        );

        pnlHome.setBackground(new java.awt.Color(241, 253, 255));
        pnlHome.setMaximumSize(new java.awt.Dimension(939, 630));
        pnlHome.setMinimumSize(new java.awt.Dimension(939, 630));

        lblHomeHeader.setFont(new java.awt.Font("Comic Sans MS", 1, 36)); // NOI18N
        lblHomeHeader.setText("Seat Reservation System (NPL) ");

        btnHomeLogIn.setText("Log In");
        btnHomeLogIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHomeLogInActionPerformed(evt);
            }
        });

        btnHomeToLeagueStandings.setText("League Standings");
        btnHomeToLeagueStandings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHomeToLeagueStandingsActionPerformed(evt);
            }
        });

        btnHomeToMatches.setText("See Matches");
        btnHomeToMatches.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHomeToMatchesActionPerformed(evt);
            }
        });

        lblHomeLeftImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/NPLTicket/views/ballOnFire.png"))); // NOI18N

        lblHomeRightImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/NPLTicket/views/playerWithBat.png"))); // NOI18N

        pnlPitch.setBackground(new java.awt.Color(254, 226, 202));

        pnlMidPitch.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        pnlMidPitch.setOpaque(false);

        lblWelcome1.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        lblWelcome1.setForeground(new java.awt.Color(51, 51, 51));
        lblWelcome1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblWelcome1.setText("\"Welcome to the Seat Reservation System of NPL!\"");

        lblWelcome2.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        lblWelcome2.setForeground(new java.awt.Color(51, 51, 51));
        lblWelcome2.setText("Get ready for an unforgettable cricket experience!🏏 Secure your seat for the thrilling matches of");

        lblWelcome3.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        lblWelcome3.setForeground(new java.awt.Color(51, 51, 51));
        lblWelcome3.setText("the Nepal Premier League. Whether you're a die-hard fan or a first-time attendee, we’re here to");

        lblWelcome4.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        lblWelcome4.setForeground(new java.awt.Color(51, 51, 51));
        lblWelcome4.setText("make your journey smooth and your seat perfectly reserved.");

        lblWelcome5.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        lblWelcome5.setForeground(new java.awt.Color(51, 51, 51));
        lblWelcome5.setText("Choose your ideal spot, relax, and enjoy the excitement of NPL – where passion for cricket meets the");

        lblWelcome6.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        lblWelcome6.setForeground(new java.awt.Color(51, 51, 51));
        lblWelcome6.setText("spirit of Nepal! 🎉");

        lblWelcome7.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        lblWelcome7.setForeground(new java.awt.Color(51, 51, 51));
        lblWelcome7.setText("Thank you for being part of the NPL family. Let’s create unforgettable memories together!");

        javax.swing.GroupLayout pnlMidPitchLayout = new javax.swing.GroupLayout(pnlMidPitch);
        pnlMidPitch.setLayout(pnlMidPitchLayout);
        pnlMidPitchLayout.setHorizontalGroup(
            pnlMidPitchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMidPitchLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(pnlMidPitchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblWelcome7)
                    .addComponent(lblWelcome6)
                    .addComponent(lblWelcome5)
                    .addComponent(lblWelcome3)
                    .addComponent(lblWelcome4)
                    .addGroup(pnlMidPitchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(lblWelcome1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblWelcome2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlMidPitchLayout.setVerticalGroup(
            pnlMidPitchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMidPitchLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(lblWelcome1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblWelcome2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblWelcome3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblWelcome4)
                .addGap(18, 18, 18)
                .addComponent(lblWelcome5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblWelcome6)
                .addGap(18, 18, 18)
                .addComponent(lblWelcome7)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        pnlCrease1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 4));
        pnlCrease1.setOpaque(false);

        javax.swing.GroupLayout pnlCrease1Layout = new javax.swing.GroupLayout(pnlCrease1);
        pnlCrease1.setLayout(pnlCrease1Layout);
        pnlCrease1Layout.setHorizontalGroup(
            pnlCrease1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 42, Short.MAX_VALUE)
        );
        pnlCrease1Layout.setVerticalGroup(
            pnlCrease1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 163, Short.MAX_VALUE)
        );

        pnlCrease2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 4));
        pnlCrease2.setOpaque(false);

        javax.swing.GroupLayout pnlCrease2Layout = new javax.swing.GroupLayout(pnlCrease2);
        pnlCrease2.setLayout(pnlCrease2Layout);
        pnlCrease2Layout.setHorizontalGroup(
            pnlCrease2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 42, Short.MAX_VALUE)
        );
        pnlCrease2Layout.setVerticalGroup(
            pnlCrease2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 163, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pnlPitchLayout = new javax.swing.GroupLayout(pnlPitch);
        pnlPitch.setLayout(pnlPitchLayout);
        pnlPitchLayout.setHorizontalGroup(
            pnlPitchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPitchLayout.createSequentialGroup()
                .addComponent(pnlCrease1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(pnlMidPitch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlCrease2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlPitchLayout.setVerticalGroup(
            pnlPitchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMidPitch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlPitchLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(pnlCrease2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlPitchLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pnlCrease1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44))
        );

        javax.swing.GroupLayout pnlHomeLayout = new javax.swing.GroupLayout(pnlHome);
        pnlHome.setLayout(pnlHomeLayout);
        pnlHomeLayout.setHorizontalGroup(
            pnlHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlHomeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblHomeLeftImage, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblHomeHeader)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblHomeRightImage)
                .addGap(18, 18, 18))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlHomeLayout.createSequentialGroup()
                .addGap(130, 130, 130)
                .addComponent(btnHomeToLeagueStandings)
                .addGap(137, 137, 137)
                .addComponent(btnHomeLogIn, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnHomeToMatches, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(141, 141, 141))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlHomeLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pnlPitch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56))
        );

        pnlHomeLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnHomeLogIn, btnHomeToLeagueStandings, btnHomeToMatches});

        pnlHomeLayout.setVerticalGroup(
            pnlHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHomeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblHomeRightImage)
                    .addComponent(lblHomeLeftImage)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlHomeLayout.createSequentialGroup()
                        .addComponent(lblHomeHeader, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(77, 77, 77)))
                .addComponent(pnlPitch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addGroup(pnlHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnHomeToLeagueStandings, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHomeLogIn, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHomeToMatches))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        pnlHomeLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnHomeLogIn, btnHomeToLeagueStandings, btnHomeToMatches});

        pnlDashboard.setBackground(new java.awt.Color(199, 213, 224));

        tblMatches.setBackground(new java.awt.Color(230, 245, 251));
        tblMatches.setForeground(new java.awt.Color(102, 102, 102));
        tblMatches.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Match No", "Teams", "Date", "Time", "Status", "Seats(Max: 20000)"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scrlPaneTableMatches.setViewportView(tblMatches);
        if (tblMatches.getColumnModel().getColumnCount() > 0) {
            tblMatches.getColumnModel().getColumn(0).setResizable(false);
            tblMatches.getColumnModel().getColumn(0).setPreferredWidth(60);
            tblMatches.getColumnModel().getColumn(1).setResizable(false);
            tblMatches.getColumnModel().getColumn(1).setPreferredWidth(100);
            tblMatches.getColumnModel().getColumn(2).setResizable(false);
            tblMatches.getColumnModel().getColumn(2).setPreferredWidth(80);
            tblMatches.getColumnModel().getColumn(3).setResizable(false);
            tblMatches.getColumnModel().getColumn(3).setPreferredWidth(60);
            tblMatches.getColumnModel().getColumn(4).setResizable(false);
            tblMatches.getColumnModel().getColumn(4).setPreferredWidth(60);
            tblMatches.getColumnModel().getColumn(5).setResizable(false);
            tblMatches.getColumnModel().getColumn(5).setPreferredWidth(120);
        }

        tblTicketDetails.setBackground(new java.awt.Color(230, 245, 251));
        tblTicketDetails.setForeground(new java.awt.Color(102, 102, 102));
        tblTicketDetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Ticket ID", "Match No", "Name", "Phone", "Email", "Zone", "Booking Date"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scrlPaneTicketDetailsTable.setViewportView(tblTicketDetails);
        if (tblTicketDetails.getColumnModel().getColumnCount() > 0) {
            tblTicketDetails.getColumnModel().getColumn(0).setResizable(false);
            tblTicketDetails.getColumnModel().getColumn(0).setPreferredWidth(60);
            tblTicketDetails.getColumnModel().getColumn(1).setResizable(false);
            tblTicketDetails.getColumnModel().getColumn(1).setPreferredWidth(70);
            tblTicketDetails.getColumnModel().getColumn(2).setResizable(false);
            tblTicketDetails.getColumnModel().getColumn(2).setPreferredWidth(100);
            tblTicketDetails.getColumnModel().getColumn(3).setResizable(false);
            tblTicketDetails.getColumnModel().getColumn(3).setPreferredWidth(70);
            tblTicketDetails.getColumnModel().getColumn(4).setResizable(false);
            tblTicketDetails.getColumnModel().getColumn(4).setPreferredWidth(100);
            tblTicketDetails.getColumnModel().getColumn(5).setResizable(false);
            tblTicketDetails.getColumnModel().getColumn(5).setPreferredWidth(40);
            tblTicketDetails.getColumnModel().getColumn(6).setResizable(false);
            tblTicketDetails.getColumnModel().getColumn(6).setPreferredWidth(100);
        }

        lblTicketDetailsTable.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        lblTicketDetailsTable.setForeground(new java.awt.Color(255, 255, 255));
        lblTicketDetailsTable.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTicketDetailsTable.setText("Ticket Details");

        lblMatchDetailsTable.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        lblMatchDetailsTable.setForeground(new java.awt.Color(255, 255, 255));
        lblMatchDetailsTable.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMatchDetailsTable.setText("Match Details");

        btnManageMatchDetails.setBackground(new java.awt.Color(30, 58, 95));
        btnManageMatchDetails.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        btnManageMatchDetails.setForeground(new java.awt.Color(255, 255, 255));
        btnManageMatchDetails.setText("Manage Match Details");
        btnManageMatchDetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnManageMatchDetailsActionPerformed(evt);
            }
        });

        btnManageTicketDetails.setBackground(new java.awt.Color(30, 58, 95));
        btnManageTicketDetails.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        btnManageTicketDetails.setForeground(new java.awt.Color(255, 255, 255));
        btnManageTicketDetails.setText("Manage Ticket Details");
        btnManageTicketDetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnManageTicketDetailsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlDashboardLayout = new javax.swing.GroupLayout(pnlDashboard);
        pnlDashboard.setLayout(pnlDashboardLayout);
        pnlDashboardLayout.setHorizontalGroup(
            pnlDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDashboardLayout.createSequentialGroup()
                .addGroup(pnlDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDashboardLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(pnlDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(scrlPaneTableMatches, javax.swing.GroupLayout.DEFAULT_SIZE, 875, Short.MAX_VALUE)
                            .addComponent(scrlPaneTicketDetailsTable)
                            .addComponent(lblTicketDetailsTable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblMatchDetailsTable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(pnlDashboardLayout.createSequentialGroup()
                        .addGap(330, 330, 330)
                        .addComponent(btnManageMatchDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(30, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDashboardLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnManageTicketDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(334, 334, 334))
        );
        pnlDashboardLayout.setVerticalGroup(
            pnlDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDashboardLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblMatchDetailsTable, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrlPaneTableMatches, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnManageMatchDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblTicketDetailsTable, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrlPaneTicketDetailsTable, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnManageTicketDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pnlAdmin.setBackground(new java.awt.Color(224, 240, 251));
        pnlAdmin.setFocusable(false);
        pnlAdmin.setMaximumSize(new java.awt.Dimension(925, 630));
        pnlAdmin.setMinimumSize(new java.awt.Dimension(925, 630));
        pnlAdmin.setPreferredSize(new java.awt.Dimension(925, 630));

        pnlManageMatch.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        pnlManageMatch.setOpaque(false);

        pnlMatchDetailsForm.setOpaque(false);

        lblMatchTeams.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        lblMatchTeams.setText("Teams : ");

        txtFldMatchTeams.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        txtFldMatchTeams.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFldMatchTeamsActionPerformed(evt);
            }
        });

        lblMatchDate.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        lblMatchDate.setText("Date    : ");

        txtFldMatchDate.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        txtFldMatchDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFldMatchDateActionPerformed(evt);
            }
        });

        lblMatchDate1.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        lblMatchDate1.setText("Time    : ");

        txtFldMatchTime.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        txtFldMatchTime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFldMatchTimeActionPerformed(evt);
            }
        });

        btnAddMatch.setBackground(new java.awt.Color(30, 58, 95));
        btnAddMatch.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        btnAddMatch.setForeground(new java.awt.Color(255, 255, 255));
        btnAddMatch.setText("Add Match");
        btnAddMatch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddMatchActionPerformed(evt);
            }
        });

        lblAddMatch.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        lblAddMatch.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAddMatch.setText("Add Match");

        javax.swing.GroupLayout pnlMatchDetailsFormLayout = new javax.swing.GroupLayout(pnlMatchDetailsForm);
        pnlMatchDetailsForm.setLayout(pnlMatchDetailsFormLayout);
        pnlMatchDetailsFormLayout.setHorizontalGroup(
            pnlMatchDetailsFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMatchDetailsFormLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlMatchDetailsFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMatchDetailsFormLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(pnlMatchDetailsFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(pnlMatchDetailsFormLayout.createSequentialGroup()
                                .addComponent(lblMatchTeams)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtFldMatchTeams, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE))
                            .addGroup(pnlMatchDetailsFormLayout.createSequentialGroup()
                                .addComponent(lblMatchDate)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtFldMatchDate, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlMatchDetailsFormLayout.createSequentialGroup()
                                .addComponent(lblMatchDate1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlMatchDetailsFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtFldMatchTime, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnAddMatch, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addComponent(lblAddMatch, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pnlMatchDetailsFormLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txtFldMatchDate, txtFldMatchTeams, txtFldMatchTime});

        pnlMatchDetailsFormLayout.setVerticalGroup(
            pnlMatchDetailsFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMatchDetailsFormLayout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(lblAddMatch)
                .addGap(18, 18, 18)
                .addGroup(pnlMatchDetailsFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMatchTeams)
                    .addComponent(txtFldMatchTeams, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlMatchDetailsFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMatchDate)
                    .addComponent(txtFldMatchDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlMatchDetailsFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMatchDate1)
                    .addComponent(txtFldMatchTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnAddMatch, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pnlUpdateMatchStatus.setOpaque(false);

        lblUpdateMatchStatus.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        lblUpdateMatchStatus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUpdateMatchStatus.setText("Update Match Status");

        lblUpdateMatchNo.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        lblUpdateMatchNo.setText("Match No. :");

        txtUpdateMatchNo.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        txtUpdateMatchNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUpdateMatchNoActionPerformed(evt);
            }
        });

        btnUpdateMatchStatus.setBackground(new java.awt.Color(30, 58, 95));
        btnUpdateMatchStatus.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        btnUpdateMatchStatus.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdateMatchStatus.setText("Update");
        btnUpdateMatchStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateMatchStatusActionPerformed(evt);
            }
        });

        lblUpdateStatus.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        lblUpdateStatus.setText("Status        :");

        txtFldMatchStatus.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N

        javax.swing.GroupLayout pnlUpdateMatchStatusLayout = new javax.swing.GroupLayout(pnlUpdateMatchStatus);
        pnlUpdateMatchStatus.setLayout(pnlUpdateMatchStatusLayout);
        pnlUpdateMatchStatusLayout.setHorizontalGroup(
            pnlUpdateMatchStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlUpdateMatchStatusLayout.createSequentialGroup()
                .addGap(91, 91, 91)
                .addComponent(btnUpdateMatchStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(118, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlUpdateMatchStatusLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlUpdateMatchStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblUpdateMatchStatus, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlUpdateMatchStatusLayout.createSequentialGroup()
                        .addGroup(pnlUpdateMatchStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblUpdateMatchNo)
                            .addComponent(lblUpdateStatus))
                        .addGap(18, 18, 18)
                        .addGroup(pnlUpdateMatchStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtFldMatchStatus, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                            .addComponent(txtUpdateMatchNo))))
                .addContainerGap())
        );
        pnlUpdateMatchStatusLayout.setVerticalGroup(
            pnlUpdateMatchStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlUpdateMatchStatusLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(lblUpdateMatchStatus)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlUpdateMatchStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUpdateMatchNo)
                    .addComponent(txtUpdateMatchNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlUpdateMatchStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUpdateStatus)
                    .addComponent(txtFldMatchStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnUpdateMatchStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlManageMatchLayout = new javax.swing.GroupLayout(pnlManageMatch);
        pnlManageMatch.setLayout(pnlManageMatchLayout);
        pnlManageMatchLayout.setHorizontalGroup(
            pnlManageMatchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlManageMatchLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlMatchDetailsForm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 145, Short.MAX_VALUE)
                .addComponent(pnlUpdateMatchStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlManageMatchLayout.setVerticalGroup(
            pnlManageMatchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlManageMatchLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlMatchDetailsForm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(pnlUpdateMatchStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pnlManageTicketDetails.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        pnlManageTicketDetails.setOpaque(false);

        pnlAddTicketDetails.setOpaque(false);

        lblAddTicketDetails.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        lblAddTicketDetails.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAddTicketDetails.setText("Add Ticket Details");

        lblTicketOwnername.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        lblTicketOwnername.setText("Person Name  :");

        lblTicketOwnerPhone.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        lblTicketOwnerPhone.setText("Phone              :");

        lblTicketOwnerEmail.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        lblTicketOwnerEmail.setText(" Email              :");

        lblTicketMatchNo.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        lblTicketMatchNo.setText("Match No        :");

        lblTicketZone.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        lblTicketZone.setText("Stadium Zone :");

        txtFldTicketOwnerName.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        txtFldTicketOwnerName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFldTicketOwnerNameActionPerformed(evt);
            }
        });

        txtFldTicketOwnerPhone.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        txtFldTicketOwnerPhone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFldTicketOwnerPhoneActionPerformed(evt);
            }
        });

        txtFldTicketOwnerEmail.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        txtFldTicketOwnerEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFldTicketOwnerEmailActionPerformed(evt);
            }
        });

        cbTicketZone.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        cbTicketZone.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Select --", "VIP", "1", "2", "3", " " }));
        cbTicketZone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTicketZoneActionPerformed(evt);
            }
        });

        txtFldTicketMatchNo.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        txtFldTicketMatchNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFldTicketMatchNoActionPerformed(evt);
            }
        });

        btnAddTicket.setBackground(new java.awt.Color(30, 58, 95));
        btnAddTicket.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        btnAddTicket.setForeground(new java.awt.Color(255, 255, 255));
        btnAddTicket.setText("Add");
        btnAddTicket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddTicketActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlAddTicketDetailsLayout = new javax.swing.GroupLayout(pnlAddTicketDetails);
        pnlAddTicketDetails.setLayout(pnlAddTicketDetailsLayout);
        pnlAddTicketDetailsLayout.setHorizontalGroup(
            pnlAddTicketDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAddTicketDetailsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlAddTicketDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblAddTicketDetails, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlAddTicketDetailsLayout.createSequentialGroup()
                        .addGroup(pnlAddTicketDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(pnlAddTicketDetailsLayout.createSequentialGroup()
                                .addComponent(lblTicketZone)
                                .addGap(18, 18, 18)
                                .addComponent(cbTicketZone, 0, 162, Short.MAX_VALUE))
                            .addGroup(pnlAddTicketDetailsLayout.createSequentialGroup()
                                .addGroup(pnlAddTicketDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblTicketOwnerPhone)
                                    .addComponent(lblTicketOwnerEmail)
                                    .addComponent(lblTicketMatchNo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblTicketOwnername, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(pnlAddTicketDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtFldTicketOwnerName)
                                    .addComponent(txtFldTicketOwnerPhone)
                                    .addComponent(txtFldTicketMatchNo)
                                    .addComponent(txtFldTicketOwnerEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE))))
                        .addGap(0, 39, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(pnlAddTicketDetailsLayout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addComponent(btnAddTicket, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlAddTicketDetailsLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cbTicketZone, txtFldTicketMatchNo, txtFldTicketOwnerEmail, txtFldTicketOwnerName, txtFldTicketOwnerPhone});

        pnlAddTicketDetailsLayout.setVerticalGroup(
            pnlAddTicketDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAddTicketDetailsLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(lblAddTicketDetails)
                .addGap(18, 18, 18)
                .addGroup(pnlAddTicketDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTicketOwnername)
                    .addComponent(txtFldTicketOwnerName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlAddTicketDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTicketMatchNo)
                    .addComponent(txtFldTicketMatchNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlAddTicketDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTicketOwnerPhone)
                    .addComponent(txtFldTicketOwnerPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlAddTicketDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTicketOwnerEmail)
                    .addComponent(txtFldTicketOwnerEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlAddTicketDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTicketZone)
                    .addComponent(cbTicketZone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAddTicket, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlRemoveTicket.setOpaque(false);

        lblRemoveTicket.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        lblRemoveTicket.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblRemoveTicket.setText("Remove Ticket Details");

        btnRemoveTicket.setBackground(new java.awt.Color(30, 58, 95));
        btnRemoveTicket.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        btnRemoveTicket.setForeground(new java.awt.Color(255, 255, 255));
        btnRemoveTicket.setText("Remove");
        btnRemoveTicket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveTicketActionPerformed(evt);
            }
        });

        txtFldRemoveTicketID.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N

        lblRemoveTicketID.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        lblRemoveTicketID.setText("Ticket ID : ");

        javax.swing.GroupLayout pnlRemoveTicketLayout = new javax.swing.GroupLayout(pnlRemoveTicket);
        pnlRemoveTicket.setLayout(pnlRemoveTicketLayout);
        pnlRemoveTicketLayout.setHorizontalGroup(
            pnlRemoveTicketLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRemoveTicketLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlRemoveTicketLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblRemoveTicket, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlRemoveTicketLayout.createSequentialGroup()
                        .addComponent(lblRemoveTicketID, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addGroup(pnlRemoveTicketLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnRemoveTicket, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFldRemoveTicketID, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)))
                .addContainerGap())
        );
        pnlRemoveTicketLayout.setVerticalGroup(
            pnlRemoveTicketLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRemoveTicketLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblRemoveTicket)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlRemoveTicketLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblRemoveTicketID)
                    .addComponent(txtFldRemoveTicketID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                .addComponent(btnRemoveTicket, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout pnlManageTicketDetailsLayout = new javax.swing.GroupLayout(pnlManageTicketDetails);
        pnlManageTicketDetails.setLayout(pnlManageTicketDetailsLayout);
        pnlManageTicketDetailsLayout.setHorizontalGroup(
            pnlManageTicketDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlManageTicketDetailsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlAddTicketDetails, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pnlRemoveTicket, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        pnlManageTicketDetailsLayout.setVerticalGroup(
            pnlManageTicketDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlManageTicketDetailsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlManageTicketDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlAddTicketDetails, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlRemoveTicket, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlAdminLayout = new javax.swing.GroupLayout(pnlAdmin);
        pnlAdmin.setLayout(pnlAdminLayout);
        pnlAdminLayout.setHorizontalGroup(
            pnlAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlAdminLayout.createSequentialGroup()
                .addContainerGap(72, Short.MAX_VALUE)
                .addGroup(pnlAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(pnlManageTicketDetails, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlManageMatch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(134, 134, 134))
        );
        pnlAdminLayout.setVerticalGroup(
            pnlAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlAdminLayout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addComponent(pnlManageMatch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pnlManageTicketDetails, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );

        scrlPaneMatches.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrlPaneMatches.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrlPaneMatches.setMaximumSize(new java.awt.Dimension(917, 630));
        scrlPaneMatches.setMinimumSize(new java.awt.Dimension(917, 630));
        scrlPaneMatches.setPreferredSize(new java.awt.Dimension(917, 630));

        pnlMatches.setMaximumSize(new java.awt.Dimension(917, 1830));
        pnlMatches.setMinimumSize(new java.awt.Dimension(917, 1830));
        pnlMatches.setPreferredSize(new java.awt.Dimension(917, 1830));

        javax.swing.GroupLayout pnlMatchesLayout = new javax.swing.GroupLayout(pnlMatches);
        pnlMatches.setLayout(pnlMatchesLayout);
        pnlMatchesLayout.setHorizontalGroup(
            pnlMatchesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 917, Short.MAX_VALUE)
        );
        pnlMatchesLayout.setVerticalGroup(
            pnlMatchesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1830, Short.MAX_VALUE)
        );

        scrlPaneMatches.setViewportView(pnlMatches);

        lblMatchesPageHeader.setText("Matches");

        jLabel1.setText("jLabel1");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(210, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlMatchesPageLayout = new javax.swing.GroupLayout(pnlMatchesPage);
        pnlMatchesPage.setLayout(pnlMatchesPageLayout);
        pnlMatchesPageLayout.setHorizontalGroup(
            pnlMatchesPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMatchesPageLayout.createSequentialGroup()
                .addGroup(pnlMatchesPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMatchesPageLayout.createSequentialGroup()
                        .addGap(397, 397, 397)
                        .addComponent(lblMatchesPageHeader))
                    .addGroup(pnlMatchesPageLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(488, Short.MAX_VALUE))
            .addGroup(pnlMatchesPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMatchesPageLayout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(scrlPaneMatches, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        pnlMatchesPageLayout.setVerticalGroup(
            pnlMatchesPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMatchesPageLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(lblMatchesPageHeader)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(534, Short.MAX_VALUE))
            .addGroup(pnlMatchesPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMatchesPageLayout.createSequentialGroup()
                    .addContainerGap(122, Short.MAX_VALUE)
                    .addComponent(scrlPaneMatches, javax.swing.GroupLayout.PREFERRED_SIZE, 502, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("NPL Seat Reservation");
        setMaximumSize(new java.awt.Dimension(1120, 630));
        setMinimumSize(new java.awt.Dimension(1120, 630));
        setResizable(false);

        pnlMain.setMaximumSize(new java.awt.Dimension(1120, 630));
        pnlMain.setMinimumSize(new java.awt.Dimension(1120, 630));
        pnlMain.setPreferredSize(new java.awt.Dimension(1120, 630));

        pnlMainMenu.setBackground(new java.awt.Color(244, 249, 251));
        pnlMainMenu.setMaximumSize(new java.awt.Dimension(195, 630));
        pnlMainMenu.setMinimumSize(new java.awt.Dimension(195, 630));
        pnlMainMenu.setPreferredSize(new java.awt.Dimension(195, 630));

        lblMenuLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/NPLTicket/views/NPLFullLogo.png"))); // NOI18N

        lblMenuDashboard.setBackground(new java.awt.Color(204, 204, 255));
        lblMenuDashboard.setText("Dashboard");
        lblMenuDashboard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblMenuDashboardMouseClicked(evt);
            }
        });

        lblMenuHome.setBackground(new java.awt.Color(204, 204, 255));
        lblMenuHome.setText("Home");
        lblMenuHome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblMenuHomeMouseClicked(evt);
            }
        });

        lblMenuAdmin.setBackground(new java.awt.Color(204, 204, 255));
        lblMenuAdmin.setText("Admin");
        lblMenuAdmin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblMenuAdminMouseClicked(evt);
            }
        });

        lblMenuContactUs.setBackground(new java.awt.Color(204, 204, 255));
        lblMenuContactUs.setText("Contact Us");

        lblMenuMatches.setBackground(new java.awt.Color(204, 204, 255));
        lblMenuMatches.setText("Matches");
        lblMenuMatches.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblMenuMatchesMouseClicked(evt);
            }
        });

        lblMenuTicket.setBackground(new java.awt.Color(204, 204, 255));
        lblMenuTicket.setText("Tickets");

        javax.swing.GroupLayout pnlFooterLayout = new javax.swing.GroupLayout(pnlFooter);
        pnlFooter.setLayout(pnlFooterLayout);
        pnlFooterLayout.setHorizontalGroup(
            pnlFooterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pnlFooterLayout.setVerticalGroup(
            pnlFooterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 184, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pnlMainMenuLayout = new javax.swing.GroupLayout(pnlMainMenu);
        pnlMainMenu.setLayout(pnlMainMenuLayout);
        pnlMainMenuLayout.setHorizontalGroup(
            pnlMainMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlMainMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlFooter, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblMenuLogo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblMenuDashboard, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblMenuAdmin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblMenuHome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblMenuMatches, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblMenuTicket, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblMenuContactUs, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        pnlMainMenuLayout.setVerticalGroup(
            pnlMainMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblMenuLogo)
                .addGap(33, 33, 33)
                .addComponent(lblMenuHome, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblMenuAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblMenuDashboard, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblMenuMatches, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblMenuTicket, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1120, 1120, 1120)
                .addComponent(lblMenuContactUs, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlFooter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlMainBody.setMaximumSize(new java.awt.Dimension(925, 630));
        pnlMainBody.setMinimumSize(new java.awt.Dimension(925, 630));
        pnlMainBody.setPreferredSize(new java.awt.Dimension(925, 630));

        javax.swing.GroupLayout pnlMainBodyLayout = new javax.swing.GroupLayout(pnlMainBody);
        pnlMainBody.setLayout(pnlMainBodyLayout);
        pnlMainBodyLayout.setHorizontalGroup(
            pnlMainBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 925, Short.MAX_VALUE)
        );
        pnlMainBodyLayout.setVerticalGroup(
            pnlMainBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 630, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pnlMainLayout = new javax.swing.GroupLayout(pnlMain);
        pnlMain.setLayout(pnlMainLayout);
        pnlMainLayout.setHorizontalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addComponent(pnlMainMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(pnlMainBody, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(3, Short.MAX_VALUE))
        );
        pnlMainLayout.setVerticalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMainMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addComponent(pnlMainBody, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1120, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(pnlMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 630, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(pnlMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtFldLogInUsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFldLogInUsernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFldLogInUsernameActionPerformed

    private void btnLogInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogInActionPerformed
        // TODO add your handling code here:
        try {
            String userName = txtFldLogInUsername.getText();
            char[] password = pswFldLogInPassword.getPassword();

            if ("".equals(userName) || password == null || password.length == 0) {
                JOptionPane.showMessageDialog(pnlMain, "Username or Password cannot be empty!", "Invalid Inputs !!", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (null == userName.toLowerCase()) {
                JOptionPane.showMessageDialog(pnlMain, "Invalid Username!", "Invalid Inputs !!", JOptionPane.ERROR_MESSAGE);
            } else switch (userName.toLowerCase()) {
                case "admin" -> {
                    if (Arrays.equals(password, "admin@123".toCharArray())) {
                        logInSuccess("Admin", "Admin Menu");
                    } else {
                        JOptionPane.showMessageDialog(pnlMain, "Invalid Password for Admin!", "Invalid Inputs !!", JOptionPane.ERROR_MESSAGE);
                    }
                }
                case "pawan" -> {
                    if (Arrays.equals(password, "23048667".toCharArray())) {
                        logInSuccess("Pawan", "User Menu");
                    } else {
                        JOptionPane.showMessageDialog(pnlMain, "Invalid Password for Pawan!", "Invalid Inputs !!", JOptionPane.ERROR_MESSAGE);
                    }
                }
                default -> JOptionPane.showMessageDialog(pnlMain, "Invalid Username!", "Invalid Inputs !!", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(pnlMain, "Unexpected Error: " + e.getMessage(), "Invalid Inputs !!", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnLogInActionPerformed

    private void logInSuccess(String userName, String menuType) {
        topLayout = (CardLayout) getContentPane().getLayout();
        topLayout.show(getContentPane(), "Main");
        bodyLayout.show(pnlMainBody, "Home");

        user = userName;
        emptyTextFields();

        if ("admin".equals(user.toLowerCase())) {
            btnHomeLogIn.setText("Log Out");
            lblMenuAdmin.setText(user);
        } else {
            btnUserMenuLogIn.setText("Log Out");
            lblMenuUser.setText(user);
        }

        menuLayout.show(pnlMenu, menuType);
    }
    
    private void btnHomeLogInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHomeLogInActionPerformed
        // TODO add your handling code here:
        if("Log Out".equals(btnHomeLogIn.getText()) || "Log Out".equals(btnUserMenuLogIn.getText())) {
            this.user = "";
        }
        
        if("admin".equals(user.toLowerCase())) {
            menuLayout.show(pnlMenu, "Admin Menu");
            btnHomeLogIn.setText("Log out");
            lblMenuAdmin.setText(this.user);
        } else if(!"".equals(user) ) {
            menuLayout.show(pnlMenu, "User Menu");
            btnUserMenuLogIn.setText("Log Out");
            lblMenuUser.setText(this.user);
        } else {
            btnHomeLogIn.setText("Log In");
            lblMenuAdmin.setText("User");
        }
        topLayout.show(getContentPane(), "Login");
        emptyTextFields();
        addTicketMatchData();
    }//GEN-LAST:event_btnHomeLogInActionPerformed

    private void txtFldMatchTeamsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFldMatchTeamsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFldMatchTeamsActionPerformed

    private void txtFldMatchDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFldMatchDateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFldMatchDateActionPerformed

    private void txtFldMatchTimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFldMatchTimeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFldMatchTimeActionPerformed

    private void btnAddMatchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddMatchActionPerformed
        // TODO add your handling code here:
        
        if (isInputMatchValid()) {
            int matchNo = matchList.isEmpty() ? 1 : matchList.get(matchList.size() - 1).getMatchNo() + 1;
            String matchStatus = "".equals(txtFldMatchStatus.getText()) ? "TBD" : txtFldMatchStatus.getText();
            MatchModel matchDetails = new MatchModel(matchNo, txtFldMatchTeams.getText(), LocalDate.parse(txtFldMatchDate.getText()), LocalTime.parse(txtFldMatchTime.getText()), matchStatus, "0");
            addMatch(matchDetails);
            addMatchCardData();
            addTicketMatchData();
            JOptionPane.showMessageDialog(pnlMain,"Match No: " +  matchNo + ", " + txtFldMatchTeams.getText() + ", has been successfully added to the table!!", "Match Added !!", JOptionPane.INFORMATION_MESSAGE);
            emptyTextFields();
            addDashboardData();
        }
    }//GEN-LAST:event_btnAddMatchActionPerformed

    private void txtFldMatchNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFldMatchNoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFldMatchNoActionPerformed

    private void btnUpdateMatchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateMatchActionPerformed
        // TODO add your handling code here:
        if(isInputMatchValid()) { 
            if(Integer.parseInt(txtFldMatchNo.getText()) <= 32 && Integer.parseInt(txtFldMatchNo.getText()) > 0) {
                for(MatchModel match: matchList) {
                    int matchNo = Integer.parseInt(txtFldMatchNo.getText());
                    String teams = txtFldMatchTeams.getText();
                    LocalDate matchDate = LocalDate.parse(txtFldMatchDate.getText()); 
                    LocalTime matchTime = LocalTime.parse(txtFldMatchTime.getText());
                    String matchStatus = txtFldMatchStatus.getText();
                    if (match.getMatchNo() == matchNo) {
                        match.setTeams(teams);
                        match.setMatchDate(matchDate);
                        match.setMatchTime(matchTime);
                        match.setMatchStatus(matchStatus);
                        loadDataToMatchTable();
                        loadDataToTicketMatchTable();
                        JOptionPane.showMessageDialog(pnlMain,"Match Details Updated for Match No: " + matchNo , "Match Details Updated!!", JOptionPane.INFORMATION_MESSAGE);
                        emptyTextFields();
                        btnAddMatch.setEnabled(true);
                        tblMatches.clearSelection();
                        return;
                    }
                }
                JOptionPane.showMessageDialog(pnlMain, "Match No. does not exist in table!", "Invalid Inputs !!", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(pnlMain, "Invalid Match No (1-32)!", "Match No limit exceeded !!", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnUpdateMatchActionPerformed

    private void lblMenuHomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMenuHomeMouseClicked
        // TODO add your handling code here:
        setActive(lblMenuHome);
        bodyLayout.show(pnlMainBody, "Home");
    }//GEN-LAST:event_lblMenuHomeMouseClicked

    private void lblMenuAddMatchesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMenuAddMatchesMouseClicked
        // TODO add your handling code here:
        setActive(lblMenuAddMatches);
        if("Admin".equals(user)){
            bodyLayout.show(pnlMainBody, "Add Matches");
        } else {
            bodyLayout.show(pnlMainBody, "Not Admin");
        }
    }//GEN-LAST:event_lblMenuAddMatchesMouseClicked

    private void lblMenuMatchesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMenuMatchesMouseClicked
        // TODO add your handling code here:
        setActive(lblMenuMatches);
        bodyLayout.show(pnlMainBody, "Matches");
    }//GEN-LAST:event_lblMenuMatchesMouseClicked

    private void txtFldTicketOwnerNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFldTicketOwnerNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFldTicketOwnerNameActionPerformed

    private void cbTicketZoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTicketZoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbTicketZoneActionPerformed

    private void btnRemoveTicketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveTicketActionPerformed
        // TODO add your handling code here:
        try {
            if(!"".equals(txtFldTicketID.getText())) {
                String ticketID = txtFldTicketID.getText();
                for(TicketModel ticket: ticketList) {
                    if(ticket.getTicketId().equals(ticketID)) {
                        ticketList.remove(ticketList.indexOf(ticket));
                        loadDataToTicketTable();
                        JOptionPane.showMessageDialog(pnlMain, "Ticket Details of " + ticketID +" removed successfully!", "Ticket Details Removed !!", JOptionPane.INFORMATION_MESSAGE);
                        emptyTextFields();
                        addDashboardData();
                        btnAddTicket.setEnabled(true);
                        tblTicketDetails.clearSelection();
                        return;
                    } 
                }
                JOptionPane.showMessageDialog(pnlMain, "TicketID doesn't exist!", "Invalid Inputs !!", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(pnlMain, "Empty fields detected!", "Invalid Inputs !!", JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(pnlMain, "Invalid Match No: " + e, "Invalid Inputs !!", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnRemoveTicketActionPerformed

    private void btnAddTicketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddTicketActionPerformed
        // TODO add your handling code here:
        try {
            if(!"".equals(txtFldTicketMatchNo.getText()) && !"".equals(txtFldTicketOwnerName.getText()) && !"".equals(txtFldTicketOwnerPhone.getText()) && !"".equals(txtFldTicketOwnerEmail.getText())) {
                if (cbTicketZone.getSelectedIndex() > 0 && cbTicketZone.getSelectedIndex() < 4) {
                    if(Integer.parseInt(txtFldTicketMatchNo.getText()) > 0 && Integer.parseInt(txtFldTicketMatchNo.getText()) < 33 ) {
                        int a = 0;
                        for(MatchModel match: matchList) {
                            if(match.getMatchNo() == Integer.parseInt(txtFldTicketMatchNo.getText())) {
                                a = 1;
                                break;
                            }
                        }
                        if(a != 0) {
                            String ticketId = ticketList.isEmpty()? "Zone" + cbTicketZone.getSelectedItem() + "_1": "Zone" + cbTicketZone.getSelectedItem() + "_" + (ticketList.size() + 1);
                            LocalDate systemDate =  LocalDate.now();
                            TicketModel ticket = new TicketModel(ticketId, Integer.parseInt(txtFldTicketMatchNo.getText()), txtFldTicketOwnerName.getText(), txtFldTicketOwnerPhone.getText(), txtFldTicketOwnerEmail.getText(), cbTicketZone.getSelectedItem().toString(), systemDate, cbTicketPaymentMethod.getSelectedItem().toString());
                            addTicket(ticket);
                            JOptionPane.showMessageDialog(pnlMain,"(" +  ticketId + "," + txtFldTicketMatchNo.getText() + "," + txtFldTicketOwnerPhone.getText() + ",\n" + txtFldTicketOwnerEmail.getText() + "," + cbTicketZone.getSelectedItem().toString() + ")", "Ticket Added!!", JOptionPane.INFORMATION_MESSAGE);
                            emptyTextFields();
                            addDashboardData();
                        } else {
                            JOptionPane.showMessageDialog(pnlMain, "Match No doesn't exist!" , "Invalid Inputs !!", JOptionPane.WARNING_MESSAGE);
                        }
                        
                    } else {
                        
                        JOptionPane.showMessageDialog(pnlMain, "Invalid Match No!" , "Invalid Inputs !!", JOptionPane.WARNING_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(pnlMain, "Please select a ticket zone !!" , "Empty Inputs !!", JOptionPane.WARNING_MESSAGE);
                }
            }else {
                JOptionPane.showMessageDialog(pnlMain, "Empty fields Detected !!" , "Invalid Inputs !!", JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(pnlMain, "Unexpected Error: " + e, "Invalid Inputs !!", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnAddTicketActionPerformed

    private void txtFldTicketOwnerPhoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFldTicketOwnerPhoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFldTicketOwnerPhoneActionPerformed

    private void txtFldTicketOwnerEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFldTicketOwnerEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFldTicketOwnerEmailActionPerformed

    private void lblMenuDashboardMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMenuDashboardMouseClicked
        // TODO add your handling code here:
        setActive(lblMenuDashboard);
        bodyLayout.show(pnlMainBody, "Dashboard");
    }//GEN-LAST:event_lblMenuDashboardMouseClicked

    private void txtFldTicketMatchNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFldTicketMatchNoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFldTicketMatchNoActionPerformed

    private void lblLogInToHomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLogInToHomeMouseClicked
        // TODO add your handling code here:
        setActive(lblMenuHome);
        menuLayout.show(pnlMenu, "User Menu");
        topLayout.show(getContentPane(), "Main");
        bodyLayout.show(pnlMainBody, "Home");
    }//GEN-LAST:event_lblLogInToHomeMouseClicked

    private void lblLogInToMatchesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLogInToMatchesMouseClicked
        // TODO add your handling code here:
        setActive(lblMenuMatches);
        menuLayout.show(pnlMenu, "User Menu");
        topLayout.show(getContentPane(), "Main");
        bodyLayout.show(pnlMainBody, "Matches");
    }//GEN-LAST:event_lblLogInToMatchesMouseClicked

    private void pnlAdminMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlAdminMenuMouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_pnlAdminMenuMouseClicked

    private void lblMenuTicketMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMenuTicketMouseClicked
        // TODO add your handling code here:
        setActive(lblMenuTicket);
        topLayout.show(getContentPane(), "Main");
        bodyLayout.show(pnlMainBody, "Tickets");
    }//GEN-LAST:event_lblMenuTicketMouseClicked

    private void lblLogInToTicketsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLogInToTicketsMouseClicked
        // TODO add your handling code here:
        setActive(lblMenuTicket);
        topLayout.show(getContentPane(), "Main");
        bodyLayout.show(pnlMainBody, "Tickets");
    }//GEN-LAST:event_lblLogInToTicketsMouseClicked

    private void cbSortMatchTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbSortMatchTableActionPerformed
        // TODO add your handling code here:
        SelectionSort selectionSort = new SelectionSort();
        InsertionSort insertionSort = new InsertionSort();
        MergeSort mergeSort = new MergeSort();
        switch(cbSortMatchTable.getSelectedIndex()){
            case 0 -> {
                selectionSort.sortByMatchNo(matchList, true);
            }
            case 1 -> insertionSort.sortBySeats(matchList, false);
            case 2 -> mergeSort.sortByMatchDate(matchList, false);
            default -> {
                addMatchData();
            }
                
        }
        loadDataToMatchTable();
    }//GEN-LAST:event_cbSortMatchTableActionPerformed

    private void cbSortTicketTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbSortTicketTableActionPerformed
        SelectionSort selectionSort = new SelectionSort();
        InsertionSort insertionSort = new InsertionSort();
        MergeSort mergeSort = new MergeSort();
        switch(cbSortTicketTable.getSelectedIndex()) {
            case 0 -> {
                selectionSort.sortByBookingDate(ticketList, true);
            }
            case 1 -> insertionSort.sortByTicketOwnerName(ticketList, true);
            case 2 -> mergeSort.sortByZone(ticketList, false);
            default -> addTicketData();
        }
        loadDataToTicketTable();
    }//GEN-LAST:event_cbSortTicketTableActionPerformed

    private void txtFldMatchesSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFldMatchesSearchActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txtFldMatchesSearchActionPerformed

    private void txtFldMatchesSearchKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFldMatchesSearchKeyTyped
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txtFldMatchesSearchKeyTyped

    private void txtFldMatchesSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFldMatchesSearchKeyReleased
        // TODO add your handling code here:
        addMatchCardData();
    }//GEN-LAST:event_txtFldMatchesSearchKeyReleased

    private void lblMatchSearchIconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMatchSearchIconMouseClicked
        // TODO add your handling code here:
        if("".equals(txtFldSearchMatch.getText())) {
            loadDataToMatchTable();
        } else {
            addMatchSearchData();
        }
    }//GEN-LAST:event_lblMatchSearchIconMouseClicked

    private void txtFldSearchMatchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFldSearchMatchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFldSearchMatchActionPerformed

    private void txtFldSearchMatchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFldSearchMatchKeyReleased
        // TODO add your handling code here:
        if("".equals(txtFldSearchMatch.getText())) {
            loadDataToMatchTable();
        } else {
            addMatchSearchData();
        }
        
    }//GEN-LAST:event_txtFldSearchMatchKeyReleased

    private void txtFldSearchTicketOwnerKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFldSearchTicketOwnerKeyReleased
        // TODO add your handling code here:
        if("".equals(txtFldSearchTicketOwner.getText())) {
            loadDataToTicketTable();
        } else {
            addTicketSearchData();
        }
    }//GEN-LAST:event_txtFldSearchTicketOwnerKeyReleased

    private void lblSearchTicketIconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSearchTicketIconMouseClicked
        // TODO add your handling code here:
        if("".equals(txtFldSearchTicketOwner.getText())) {
            loadDataToTicketTable();
        } else {
            addTicketSearchData();
        }
    }//GEN-LAST:event_lblSearchTicketIconMouseClicked

    private void pnlMatchTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlMatchTableMouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_pnlMatchTableMouseClicked

    private void txtFldMatchStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFldMatchStatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFldMatchStatusActionPerformed

    private void tblMatchesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMatchesMouseClicked
        // TODO add your handling code here:
        int selectedRow = tblMatches.getSelectedRow();
        MatchModel match = matchList.get(selectedRow);
        txtFldMatchNo.setText(String.valueOf(match.getMatchNo()));
        txtFldMatchTeams.setText(match.getTeams());
        txtFldMatchDate.setText(String.valueOf(match.getMatchDate()));
        txtFldMatchTime.setText(String.valueOf(match.getMatchTime()));
        if(!"tbd".equals(match.getMatchStatus().toLowerCase())) {
            txtFldMatchStatus.setText(match.getMatchStatus());
        } else {
            txtFldMatchStatus.setText("");
        }
        btnAddMatch.setEnabled(false);
    }//GEN-LAST:event_tblMatchesMouseClicked

    private Boolean rememberUser = false;
    
    private void ckbRememberMeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckbRememberMeActionPerformed
        // TODO add your handling code here:
        rememberUser = ckbRememberMe.isSelected();
    }//GEN-LAST:event_ckbRememberMeActionPerformed

    private void btnDeleteMatchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteMatchActionPerformed
        // TODO add your handling code here:
        try {
            
            String matchNoText = txtFldMatchNo.getText();

            if (matchNoText.isEmpty()) {
                JOptionPane.showMessageDialog(pnlMain, "Match No field cannot be empty.", "Invalid Inputs !!", JOptionPane.ERROR_MESSAGE);
                txtFldMatchNo.requestFocus();
                return;
            
            }
            int matchNo;
            
            try {
                matchNo = Integer.parseInt(txtFldMatchNo.getText());
                if (matchNo < 1 || matchNo > 32) {
                    JOptionPane.showMessageDialog(pnlMain, "Match No must be between 1 and 32.", "Invalid Inputs !!", JOptionPane.ERROR_MESSAGE);
                    txtFldMatchNo.requestFocus();
                    return;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(pnlMain, "Match No must be a valid number between 1 and 32.", "Invalid Inputs !!", JOptionPane.ERROR_MESSAGE);
                txtFldMatchNo.requestFocus();
                return;
            }

            for (MatchModel match : matchList) {
                if (match.getMatchNo() == matchNo) {
                    int response = JOptionPane.showConfirmDialog(pnlMain, 
                            "Are you sure you want to delete details for Match no: " + matchNo, 
                            "Delete Match Details?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (response == JOptionPane.YES_OPTION) {
                        matchList.remove(match);
                        loadDataToMatchTable();
                        loadDataToTicketMatchTable();
                        emptyTextFields();
                        btnAddMatch.setEnabled(true);
                        tblMatches.clearSelection();
                        addDashboardData();
                        return;
                    }
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(pnlMain, "Unexpected Error: " + e.getMessage(), "Invalid Inputs !!", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnDeleteMatchActionPerformed

    private void txtFldTicketBookingDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFldTicketBookingDateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFldTicketBookingDateActionPerformed

    private void btnUpdateTicketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateTicketActionPerformed
        // TODO add your handling code here:
        if(isInputTicketValid()) {
            for(TicketModel ticket: ticketList) {
                if(ticket.getTicketId().equals(txtFldTicketID.getText())) {
                    ticket.setTicketId(txtFldTicketID.getText());
                    ticket.setMatchNo(Integer.parseInt(txtFldTicketMatchNo.getText()));
                    ticket.setTicketOwnerName(txtFldTicketOwnerName.getText());
                    ticket.setTicketOwnerPhone(txtFldTicketOwnerPhone.getText());
                    ticket.setTicketOwnerEmail(txtFldTicketOwnerEmail.getText());
                    ticket.setTicketZone(cbTicketZone.getSelectedItem().toString());
                    ticket.setBookingDate(LocalDate.parse(txtFldTicketBookingDate.getText()));
                    ticket.setPaymentMethod(cbTicketPaymentMethod.getSelectedItem().toString());
                    loadDataToTicketTable();
                    addDashboardData();
                    JOptionPane.showMessageDialog(pnlMain,"Ticket Details Updated for  " + txtFldTicketOwnerName.getText() , "Ticket Details Updated!!", JOptionPane.INFORMATION_MESSAGE);
                    emptyTextFields();
                    btnAddTicket.setEnabled(true);
                    tblTicketDetails.clearSelection();
                    return;
                }
            }
        }
    }//GEN-LAST:event_btnUpdateTicketActionPerformed

    private void tblTicketDetailsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTicketDetailsMouseClicked
        // TODO add your handling code here:
        int selectedRow = tblTicketDetails.getSelectedRow();
        TicketModel ticket = ticketList.get(selectedRow);
        txtFldTicketID.setText(ticket.getTicketId());
        txtFldTicketMatchNo.setText(String.valueOf(ticket.getMatchNo()));
        txtFldTicketOwnerName.setText(ticket.getTicketOwnerName());
        txtFldTicketOwnerPhone.setText(ticket.getTicketOwnerPhone());
        txtFldTicketOwnerEmail.setText(ticket.getTicketOwnerEmail());
        cbTicketZone.setSelectedItem(ticket.getTicketZone());
        txtFldTicketBookingDate.setText(String.valueOf(ticket.getBookingDate()));
        cbTicketPaymentMethod.setSelectedItem(ticket.getPaymentMethod());
        btnAddTicket.setEnabled(false);
    }//GEN-LAST:event_tblTicketDetailsMouseClicked

    private void lblLogoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLogoMouseClicked
        // TODO add your handling code here:
        setActive(lblMenuHome);
        bodyLayout.show(pnlMainBody, "Home");
    }//GEN-LAST:event_lblLogoMouseClicked

    private void lblUserMenuHomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblUserMenuHomeMouseClicked
        // TODO add your handling code here:
        setActive(lblUserMenuHome);
        bodyLayout.show(pnlMainBody, "Home");
    }//GEN-LAST:event_lblUserMenuHomeMouseClicked

    private void lblUserMenuMatchesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblUserMenuMatchesMouseClicked
        // TODO add your handling code here:
        setActive(lblUserMenuMatches);
        bodyLayout.show(pnlMainBody, "Matches");
    }//GEN-LAST:event_lblUserMenuMatchesMouseClicked

    private void lblUserMenuTicketMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblUserMenuTicketMouseClicked
        // TODO add your handling code here:
        setActive(lblUserMenuTicket);
        bodyLayout.show(pnlMainBody, "Buy Tickets");
    }//GEN-LAST:event_lblUserMenuTicketMouseClicked

    private void lblUserMenuLogoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblUserMenuLogoMouseClicked
        // TODO add your handling code here:
        lblLogoMouseClicked(evt);
    }//GEN-LAST:event_lblUserMenuLogoMouseClicked

    private void btnUserMenuLogInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUserMenuLogInActionPerformed
        // TODO add your handling code here:
        btnHomeLogInActionPerformed(evt);
    }//GEN-LAST:event_btnUserMenuLogInActionPerformed

    private void pnlUserMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlUserMenuMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_pnlUserMenuMouseClicked

    private void txtFldBuyTicketOwnerNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFldBuyTicketOwnerNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFldBuyTicketOwnerNameActionPerformed

    private void txtFldBuyTicketOwnerPhoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFldBuyTicketOwnerPhoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFldBuyTicketOwnerPhoneActionPerformed

    private void txtFldBuyTicketOwnerEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFldBuyTicketOwnerEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFldBuyTicketOwnerEmailActionPerformed

    private void cbBuyTicketZoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbBuyTicketZoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbBuyTicketZoneActionPerformed

    private void cbBuyTicketMatchesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbBuyTicketMatchesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbBuyTicketMatchesActionPerformed

    private void btnBuyTicketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuyTicketActionPerformed
        // TODO add your handling code here:
        if (isInputBuyTicketValid()) {
            String match = cbBuyTicketMatches.getSelectedItem().toString();
            String ownerName = txtFldBuyTicketOwnerName.getText();
            String paymentMethod = cbBuyTicketPaymentMethod.getSelectedItem().toString();
            String ownerPhone = txtFldBuyTicketOwnerPhone.getText();

            String ticketId = ticketList.get(ticketList.size() - 1).getTicketId();
            int indexOfZone = ticketId.indexOf("_");
            ticketId = ticketId.substring(0, indexOfZone) + String.valueOf(ticketList.size() + 1);
            LocalDate systemDate =  LocalDate.now();
            int matchNo = -1;
            for(MatchModel matchObj: matchList) {
                if(cbBuyTicketMatches.getSelectedItem().toString().equals(matchObj.getTeams())) {
                    matchNo = matchObj.getMatchNo();
                }
            }
            TicketModel ticket = new TicketModel(ticketId, matchNo, txtFldBuyTicketOwnerName.getText(), txtFldBuyTicketOwnerPhone.getText(), txtFldBuyTicketOwnerEmail.getText(), cbBuyTicketZone.getSelectedItem().toString(), systemDate, cbBuyTicketPaymentMethod.getSelectedItem().toString());
            addTicket(ticket);

            JOptionPane.showMessageDialog(pnlMain, 
                "Ticket successfully purchased!\n" +
                "Match :" + match + "\n" +
                "Name: " + ownerName + "\n" +
                "Phone: " + ownerPhone + "\n" +
                "Payment Method: " + paymentMethod, 
                "Success", 
                JOptionPane.INFORMATION_MESSAGE);

            emptyTextFields();
        }
    }//GEN-LAST:event_btnBuyTicketActionPerformed

    private void tblTicketMatchesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTicketMatchesMouseClicked
        // TODO add your handling code here:
        DefaultTableModel tblTicketMatchesModel = (DefaultTableModel) tblTicketMatches.getModel();
        int selectedRow = tblTicketMatches.getSelectedRow();
        cbBuyTicketMatches.setSelectedItem(tblTicketMatchesModel.getValueAt(selectedRow, 1));
    }//GEN-LAST:event_tblTicketMatchesMouseClicked

    private void cbBuyTicketPaymentMethodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbBuyTicketPaymentMethodActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbBuyTicketPaymentMethodActionPerformed

    private void cbTicketPaymentMethodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTicketPaymentMethodActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbTicketPaymentMethodActionPerformed

    private void pnlManageMatchDetailsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlManageMatchDetailsMouseClicked
        // TODO add your handling code here:
        tblMatches.clearSelection();
        btnAddMatch.setEnabled(true);
    }//GEN-LAST:event_pnlManageMatchDetailsMouseClicked

    private void pnlManageTicketsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlManageTicketsMouseClicked
        // TODO add your handling code here:
        tblTicketDetails.clearSelection();
        btnAddTicket.setEnabled(true);
    }//GEN-LAST:event_pnlManageTicketsMouseClicked
    
    private void emptyTextFields() {
        txtFldMatchNo.setText("");
        txtFldMatchTeams.setText("");
        txtFldMatchDate.setText("");
        txtFldMatchTime.setText("");
        txtFldMatchStatus.setText("");
        txtFldTicketMatchNo.setText("");
        txtFldTicketOwnerName.setText("");
        txtFldTicketOwnerPhone.setText("");
        txtFldTicketOwnerEmail.setText("");
        cbTicketZone.setSelectedIndex(0);
        txtFldTicketID.setText("");
        txtFldTicketBookingDate.setText("");
        cbTicketPaymentMethod.setSelectedIndex(0);
        pswFldLogInPassword.setText("");
        if(!rememberUser) {
            txtFldLogInUsername.setText("");
        }
        txtFldBuyTicketOwnerName.setText("");
        txtFldBuyTicketOwnerPhone.setText("");
        txtFldBuyTicketOwnerEmail.setText("");
        cbBuyTicketMatches.setSelectedIndex(0);
        cbBuyTicketZone.setSelectedIndex(0);
        cbBuyTicketPaymentMethod.setSelectedIndex(0);
        txtFldSearchMatch.setText("");
        txtFldSearchTicketOwner.setText("");
        txtFldMatchesSearch.setText("");
        addMatchCardData();
    }
    
    private Boolean isInputMatchValid() {
        try {
            String matchNoText = txtFldMatchNo.getText();

            if (matchNoText.isEmpty()) {
                JOptionPane.showMessageDialog(pnlMain, "Match No field cannot be empty.", "Invalid Inputs !!", JOptionPane.ERROR_MESSAGE);
                txtFldMatchNo.requestFocus();
                return false;
            
            }
            
            try {
                int matchNo = Integer.parseInt(txtFldMatchNo.getText());
                if (matchNo < 1 || matchNo > 32) {
                    JOptionPane.showMessageDialog(pnlMain, "Match No must be between 1 and 32.", "Invalid Inputs !!", JOptionPane.ERROR_MESSAGE);
                    txtFldMatchNo.requestFocus();
                    return false;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(pnlMain, "Match No must be a number between 1 and 32.", "Invalid Inputs !!", JOptionPane.ERROR_MESSAGE);
                txtFldMatchNo.requestFocus();
                return false;
            }
            
            if ("".equals(txtFldMatchTeams.getText())) {
                JOptionPane.showMessageDialog(pnlMain, "Match Teams field cannot be empty.", "Invalid Inputs !!", JOptionPane.ERROR_MESSAGE);
                txtFldMatchTeams.requestFocus();
                return false;
            }

            if ("".equals(txtFldMatchDate.getText())) {
                JOptionPane.showMessageDialog(pnlMain, "Match Date field cannot be empty.", "Invalid Inputs !!", JOptionPane.ERROR_MESSAGE);
                txtFldMatchDate.requestFocus();
                return false;
            }

            if ("".equals(txtFldMatchTime.getText())) {
                JOptionPane.showMessageDialog(pnlMain, "Match Time field cannot be empty.", "Invalid Inputs !!", JOptionPane.ERROR_MESSAGE);
                txtFldMatchTime.requestFocus();
                return false;
            }

            try {
                LocalDate parsedDate = LocalDate.parse(txtFldMatchDate.getText());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(pnlMain, "Invalid Date format. Please use (yyyy-mm-dd).", "Invalid Inputs !!", JOptionPane.ERROR_MESSAGE);
                txtFldMatchDate.requestFocus();
                return false;
            }

            try {
                LocalTime parsedTime = LocalTime.parse(txtFldMatchTime.getText());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(pnlMain, "Invalid Time format. Please use (hh:mm).", "Invalid Inputs !!", JOptionPane.ERROR_MESSAGE);
                txtFldMatchTime.requestFocus();
                return false;
            }

            if ("".equals(txtFldMatchNo.getText())) {
                JOptionPane.showMessageDialog(pnlMain, "Match No field cannot be empty.", "Invalid Inputs !!", JOptionPane.ERROR_MESSAGE);
                txtFldMatchNo.requestFocus();
                return false;
            }

            return true;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(pnlMain, "Unexpected Error: " + e.getMessage(), "Invalid Inputs !!", JOptionPane.WARNING_MESSAGE);
            return false;
        }
    }
    
    private Boolean isInputTicketValid() {
        try {
            if ("".equals(txtFldTicketMatchNo.getText())) {
                JOptionPane.showMessageDialog(pnlMain, "Match No field cannot be empty.", "Invalid Inputs !!", JOptionPane.ERROR_MESSAGE);
                txtFldTicketMatchNo.requestFocus();
                return false;
            }

            try {
                int matchNo = Integer.parseInt(txtFldTicketMatchNo.getText());
                if (matchNo < 1 || matchNo > 32) {
                    JOptionPane.showMessageDialog(pnlMain, "Match No must be between 1 and 32.", "Invalid Inputs !!", JOptionPane.ERROR_MESSAGE);
                    txtFldTicketMatchNo.requestFocus();
                    return false;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(pnlMain, "Match No must be a number between 1 and 32.", "Invalid Inputs !!", JOptionPane.ERROR_MESSAGE);
                txtFldTicketMatchNo.requestFocus();
                return false;
            }

            if ("--Select--".equals(cbTicketZone.getSelectedItem())) {
                JOptionPane.showMessageDialog(pnlMain, "Please select a Zone.", "Invalid Inputs !!", JOptionPane.ERROR_MESSAGE);
                cbTicketZone.requestFocus();
                return false;
            }

            if ("".equals(txtFldTicketOwnerName.getText())) {
                JOptionPane.showMessageDialog(pnlMain, "Owner Name field cannot be empty.", "Invalid Inputs !!", JOptionPane.ERROR_MESSAGE);
                txtFldTicketOwnerName.requestFocus();
                return false;
            }

            if ("".equals(txtFldTicketOwnerPhone.getText())) {
                JOptionPane.showMessageDialog(pnlMain, "Owner Phone field cannot be empty.", "Invalid Inputs !!", JOptionPane.ERROR_MESSAGE);
                txtFldTicketOwnerPhone.requestFocus();
                return false;
            }

            if ("".equals(txtFldTicketOwnerEmail.getText())) {
                JOptionPane.showMessageDialog(pnlMain, "Owner Email field cannot be empty.", "Invalid Inputs !!", JOptionPane.ERROR_MESSAGE);
                txtFldTicketOwnerEmail.requestFocus();
                return false;
            }

            try {
                String phonePattern = "^[0-9]{10}$";
                if (!txtFldTicketOwnerPhone.getText().matches(phonePattern)) {
                    JOptionPane.showMessageDialog(pnlMain, "Invalid Phone Number. Please enter a 10-digit phone number.", "Invalid Inputs !!", JOptionPane.ERROR_MESSAGE);
                    txtFldTicketOwnerPhone.requestFocus();
                    return false;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(pnlMain, "Unexpected error in phone validation: " + e.getMessage(), "Invalid Inputs !!", JOptionPane.WARNING_MESSAGE);
                return false;
            }

            try {
                String emailPattern = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
                if (!txtFldTicketOwnerEmail.getText().matches(emailPattern)) {
                    JOptionPane.showMessageDialog(pnlMain, "Invalid Email format.", "Invalid Inputs !!", JOptionPane.ERROR_MESSAGE);
                    txtFldTicketOwnerEmail.requestFocus();
                    return false;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(pnlMain, "Unexpected error in email validation: " + e.getMessage(), "Invalid Inputs !!", JOptionPane.WARNING_MESSAGE);
                return false;
            }

            if ("--Select--".equals(cbTicketPaymentMethod.getSelectedItem())) {
                JOptionPane.showMessageDialog(pnlMain, "Please select a Payment Method.", "Invalid Inputs !!", JOptionPane.ERROR_MESSAGE);
                cbTicketPaymentMethod.requestFocus();
                return false;
            }

            return true;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(pnlMain, "Unexpected Error: " + e.getMessage(), "Invalid Inputs !!", JOptionPane.WARNING_MESSAGE);
            return false;
        }
    }
    
    private Boolean isInputBuyTicketValid() {
        try {
            if ("".equals(txtFldBuyTicketOwnerName.getText())) {
                JOptionPane.showMessageDialog(pnlMain, "Owner Name field cannot be empty.", "Invalid Inputs !!", JOptionPane.ERROR_MESSAGE);
                txtFldBuyTicketOwnerName.requestFocus();
                return false;
            }

            if ("".equals(txtFldBuyTicketOwnerPhone.getText())) {
                JOptionPane.showMessageDialog(pnlMain, "Owner Phone field cannot be empty.", "Invalid Inputs !!", JOptionPane.ERROR_MESSAGE);
                txtFldBuyTicketOwnerPhone.requestFocus();
                return false;
            }

            if ("".equals(txtFldBuyTicketOwnerEmail.getText())) {
                JOptionPane.showMessageDialog(pnlMain, "Owner Email field cannot be empty.", "Invalid Inputs !!", JOptionPane.ERROR_MESSAGE);
                txtFldBuyTicketOwnerEmail.requestFocus();
                return false;
            }

            try {
                String phonePattern = "^[0-9]{10}$";
                if (!txtFldBuyTicketOwnerPhone.getText().matches(phonePattern)) {
                    JOptionPane.showMessageDialog(pnlMain, "Invalid Phone Number. Please enter a 10-digit phone number.", "Invalid Inputs !!", JOptionPane.ERROR_MESSAGE);
                    txtFldBuyTicketOwnerPhone.requestFocus();
                    return false;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(pnlMain, "Unexpected error in phone validation: " + e.getMessage(), "Invalid Inputs !!", JOptionPane.WARNING_MESSAGE);
                return false;
            }

            try {
                String emailPattern = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
                if (!txtFldBuyTicketOwnerEmail.getText().matches(emailPattern)) {
                    JOptionPane.showMessageDialog(pnlMain, "Invalid Email format.", "Invalid Inputs !!", JOptionPane.ERROR_MESSAGE);
                    txtFldBuyTicketOwnerEmail.requestFocus();
                    return false;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(pnlMain, "Unexpected error in email validation: " + e.getMessage(), "Invalid Inputs !!", JOptionPane.WARNING_MESSAGE);
                return false;
            }
            
            if (cbBuyTicketMatches.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(pnlMain, "Please select a Match.", "Invalid Inputs !!", JOptionPane.ERROR_MESSAGE);
                cbBuyTicketMatches.requestFocus();
                return false;
            }

            if (cbBuyTicketZone.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(pnlMain, "Please select a Zone.", "Invalid Inputs !!", JOptionPane.ERROR_MESSAGE);
                cbBuyTicketZone.requestFocus();
                return false;
            }

            if (cbBuyTicketPaymentMethod.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(pnlMain, "Please select a Payment Method for ticket purchase.", "Invalid Inputs !!", JOptionPane.ERROR_MESSAGE);
                cbBuyTicketPaymentMethod.requestFocus();
                return false;
            }

            return true;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(pnlMain, "Unexpected Error: " + e.getMessage(), "Invalid Inputs !!", JOptionPane.WARNING_MESSAGE);
            return false;
        }
    }
    
    private void addMatch(MatchModel match) 
    {
        matchList.add(match);
        addDataToMatchTable(match);
    }
    
    private void addTicket (TicketModel ticket) 
    {
        ticketList.add(ticket);
        addDataToTicketTable(ticket);
    }
    
    private void loadDataToMatchTable()
    {
        matchTableModel = (DefaultTableModel) tblMatches.getModel();
        matchTableModel.setRowCount(0);
        for(MatchModel match: matchList) {
            matchTableModel.addRow(new Object[] {
                match.getMatchNo(), match.getTeams(), match.getMatchDate(), match.getMatchTime(), match.getMatchStatus(), match.getMatchSeats()
            });
        }
    }
    
    private void loadDataToTicketTable() 
    {
        ticketTableModel = (DefaultTableModel) tblTicketDetails.getModel();
        ticketTableModel.setRowCount(0);
        for(TicketModel ticket: ticketList) {
            ticketTableModel.addRow(new Object[] {
                ticket.getTicketId(), ticket.getMatchNo(), ticket.getTicketOwnerName(), ticket.getTicketOwnerPhone(), ticket.getTicketOwnerEmail(),ticket.getTicketZone(), ticket.getBookingDate(), ticket.getPaymentMethod()
            });
        }
    }
    
    private void loadDataToTicketMatchTable() {
        ticketMatchesTableModel = (DefaultTableModel) tblTicketMatches.getModel();
        ticketMatchesTableModel.setRowCount(0);
        for(TicketMatchModel match: ticketMatchList){
            ticketMatchesTableModel.addRow(new Object[] {
                match.getMatchNo(), match.getTeams(), match.getMatchDate(), match.getMatchTime()
            });
        }
    }
    
    private void addDataToMatchTable(MatchModel match) 
    {
        matchTableModel = (DefaultTableModel) tblMatches.getModel();
        matchTableModel.addRow(new Object[] {
            match.getMatchNo(), match.getTeams(), match.getMatchDate(), match.getMatchTime(), match.getMatchStatus(), match.getMatchSeats()
        });
    }
    
    private void addDataToTicketTable(TicketModel ticket) 
    {
        ticketTableModel = (DefaultTableModel) tblTicketDetails.getModel();
        ticketTableModel.addRow(new Object[] {
            ticket.getTicketId(), ticket.getMatchNo(), ticket.getTicketOwnerName(), ticket.getTicketOwnerPhone(), ticket.getTicketOwnerEmail(),ticket.getTicketZone(), ticket.getBookingDate(), ticket.getPaymentMethod()
        });
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NPLTicket.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NPLTicket.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NPLTicket.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NPLTicket.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NPLTicket().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddMatch;
    private javax.swing.JButton btnAddTicket;
    private javax.swing.JButton btnHomeLogIn;
    private javax.swing.JButton btnHomeToLeagueStandings;
    private javax.swing.JButton btnHomeToMatches;
    private javax.swing.JButton btnLogIn;
    private javax.swing.JButton btnManageMatchDetails;
    private javax.swing.JButton btnManageTicketDetails;
    private javax.swing.JButton btnRemoveTicket;
    private javax.swing.JButton btnUpdateMatchStatus;
    private javax.swing.JComboBox<String> cbTicketZone;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lblAddMatch;
    private javax.swing.JLabel lblAddTicketDetails;
    private javax.swing.JLabel lblForgotPassword;
    private javax.swing.JLabel lblHomeHeader;
    private javax.swing.JLabel lblHomeLeftImage;
    private javax.swing.JLabel lblHomeRightImage;
    private javax.swing.JLabel lblLogInFullLogo;
    private javax.swing.JLabel lblLogInHeader;
    private javax.swing.JLabel lblLogInNPLImage;
    private javax.swing.JLabel lblLogInNPLTxt;
    private javax.swing.JLabel lblLogInPassword;
    private javax.swing.JLabel lblLogInUsername;
    private javax.swing.JLabel lblMatchDate;
    private javax.swing.JLabel lblMatchDate1;
    private javax.swing.JLabel lblMatchDetailsTable;
    private javax.swing.JLabel lblMatchTeams;
    private javax.swing.JLabel lblMatchesPageHeader;
    private javax.swing.JLabel lblMenuAdmin;
    private javax.swing.JLabel lblMenuContactUs;
    private javax.swing.JLabel lblMenuDashboard;
    private javax.swing.JLabel lblMenuHome;
    private javax.swing.JLabel lblMenuLogo;
    private javax.swing.JLabel lblMenuMatches;
    private javax.swing.JLabel lblMenuTicket;
    private javax.swing.JLabel lblRemoveTicket;
    private javax.swing.JLabel lblRemoveTicketID;
    private javax.swing.JLabel lblTicketDetailsTable;
    private javax.swing.JLabel lblTicketMatchNo;
    private javax.swing.JLabel lblTicketOwnerEmail;
    private javax.swing.JLabel lblTicketOwnerPhone;
    private javax.swing.JLabel lblTicketOwnername;
    private javax.swing.JLabel lblTicketZone;
    private javax.swing.JLabel lblUpdateMatchNo;
    private javax.swing.JLabel lblUpdateMatchStatus;
    private javax.swing.JLabel lblUpdateStatus;
    private javax.swing.JLabel lblWelcome1;
    private javax.swing.JLabel lblWelcome2;
    private javax.swing.JLabel lblWelcome3;
    private javax.swing.JLabel lblWelcome4;
    private javax.swing.JLabel lblWelcome5;
    private javax.swing.JLabel lblWelcome6;
    private javax.swing.JLabel lblWelcome7;
    private javax.swing.JPanel pnlAddTicketDetails;
    private javax.swing.JPanel pnlAdmin;
    private javax.swing.JPanel pnlCrease1;
    private javax.swing.JPanel pnlCrease2;
    private javax.swing.JPanel pnlDashboard;
    private javax.swing.JPanel pnlFooter;
    private javax.swing.JPanel pnlHome;
    private javax.swing.JPanel pnlLogIn;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JPanel pnlMainBody;
    private javax.swing.JPanel pnlMainMenu;
    private javax.swing.JPanel pnlManageMatch;
    private javax.swing.JPanel pnlManageTicketDetails;
    private javax.swing.JPanel pnlMatchDetailsForm;
    private javax.swing.JPanel pnlMatches;
    private javax.swing.JPanel pnlMatchesPage;
    private javax.swing.JPanel pnlMidPitch;
    private javax.swing.JPanel pnlPitch;
    private javax.swing.JPanel pnlRemoveTicket;
    private javax.swing.JPanel pnlUpdateMatchStatus;
    private javax.swing.JPasswordField pswFldLogInPassword;
    private javax.swing.JScrollPane scrlPaneMatches;
    private javax.swing.JScrollPane scrlPaneTableMatches;
    private javax.swing.JScrollPane scrlPaneTicketDetailsTable;
    private javax.swing.JTable tblMatches;
    private javax.swing.JTable tblTicketDetails;
    private javax.swing.JTextField txtFldLogInUsername;
    private javax.swing.JTextField txtFldMatchDate;
    private javax.swing.JTextField txtFldMatchStatus;
    private javax.swing.JTextField txtFldMatchTeams;
    private javax.swing.JTextField txtFldMatchTime;
    private javax.swing.JTextField txtFldRemoveTicketID;
    private javax.swing.JTextField txtFldTicketMatchNo;
    private javax.swing.JTextField txtFldTicketOwnerEmail;
    private javax.swing.JTextField txtFldTicketOwnerName;
    private javax.swing.JTextField txtFldTicketOwnerPhone;
    private javax.swing.JTextField txtUpdateMatchNo;
    // End of variables declaration//GEN-END:variables
}
