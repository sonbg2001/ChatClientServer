
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.DateFormat;
import javax.swing.JTextPane;


public class ChatMessageSoket {
    private Socket socket;
    private JTextPane txpMessageBoard;
    private PrintWriter out;
    private BufferedReader reader;
    public static boolean autoChat = true;
    public static int count = 0;
    public ChatMessageSoket(Socket socket, JTextPane txpMessageBoard) throws IOException {
        this.socket = socket;
        this.txpMessageBoard = txpMessageBoard;
        
        out = new PrintWriter(socket.getOutputStream());
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        
//        receive();
    }

    public void setAutoChat(boolean autoChat) {
        this.autoChat = autoChat;
    }

    

    public boolean isAutoChat() {
        return autoChat;
    }
    
    public void receive() {
        Thread th = new Thread() {
            public void run() {
                while (true) {                    
                     try {
                        String line = reader.readLine();
//                        boolean auto = reader.ready();
//                         setAutoChat(auto);
                        if(line!=null) {
                            txpMessageBoard.setText(txpMessageBoard.getText() + "\n" + line );
                        }
                    } catch (Exception e) {
                        
                    }
                }
            }
        };
        th.start();
    }
    
    public void serverReceive() {
        Thread th = new Thread() {
            public void run() {
                while (true) {                    
                     try {
                        String msg = reader.readLine();
                        
                        if(msg!=null && autoChat)  {
                            String text = "";
                            if(msg.contains("hi") || msg.contains("hello") || msg.contains("ch??o")) {   
                                 text = "ch??o c???u";
                            } else if(msg.contains("how are you")) {
                                text = "I'm Good :).Thank you.And you?";
                            } else if(msg.contains("what is your name")) {
                                text = "I'm your chatbot";
                            } else if(msg.contains("b???n t??n l?? g??")) {
                                text = "T??i l?? Chatbot t?? v???n kho?? h???c l???p tr??nh web";
                            } else if(msg.contains("b??y gi??? l?? m???y gi???")) {
                                text = "B??y gi??? l?? " + java.time.LocalTime.now();
                            }else if(msg.contains("H??m nay l?? ng??y")) {
                                text = "H??m nay l?? ng??y " + java.time.LocalDate.now();
                            } else if(msg.contains("b???n t??n l?? ")) {
                                text = "T??i l?? Chatbot t?? v???n kho?? h???c" ;
                            }
                            if(count == 0) {
                                if(msg.contains("hi") || msg.contains("hello") || msg.contains("ch??o")) {   
                                    text = "ch??o c???u";
//                                    count++;
                                }                             

                            } 
//                            count=1;
                            if(count<=1) {
                                if(msg.contains("t?? v???n")) {
                                    text = "Kh??ng bi???t l?? b???n mu???n t?? v???n v??? kho?? h???c n??o c???a ch??ng m??nh ???!" ;
                                    text += "\n1. HTML/CSS c?? b???n";
                                    text += "\n2. ReactJS c?? b???n";
                                    text += "\n3. JavaScript c?? b???n";
                                    text += "\n4. JavaScript n??ng cao";
                                    count=2;
                                }                             

                            } else if(count==2) {
                                if(msg.contains("1") || msg.contains("HTML/CSS")) {
                                    text = "Kho?? h???c HTML/CSS c?? b???n th?? b???n s??? h???c trong 2 th??ng, m???i tu???n 3 bu???i b???n c?? th??? s???p x???p" ;
                                    text += "\nChi ph?? ????ng k?? l?? 2 tri???u 500 ng??n";
                                    text += "\nB???n c?? mu???n ????ng k?? lu??n kh??ng ????";
                                    count=3;
                                } else if(msg.contains("2") || msg.contains("ReactJS")) {
                                    text = "Kho?? h???c ReactJS c?? b???n th?? b???n s??? h???c trong 3 th??ng, m???i tu???n 3 bu???i b???n c?? th??? s???p x???p" ;
                                    text += "\nChi ph?? ????ng k?? l?? 3 tri???u ?????ng";
                                    text += "\nB???n c?? mu???n ????ng k?? lu??n kh??ng ????";
                                    count=3;
                                }  else if(msg.contains("3") || msg.contains("JavaScript c?? b???n")) {
                                    text = "Kho?? h???c JavaScript c?? b???n th?? b???n s??? h???c trong 2 th??ng, m???i tu???n 3 bu???i b???n c?? th??? s???p x???p" ;
                                    text += "\nChi ph?? ????ng k?? l?? 4 tri???u ?????ng";
                                    text += "\nB???n c?? mu???n ????ng k?? lu??n kh??ng ????";
                                    count=3;
                                }    else if(msg.contains("4") || msg.contains("JavaScript n??ng")) {
                                    text = "Kho?? h???c JavaScript n??ng cao th?? b???n s??? h???c trong 2 th??ng, m???i tu???n 3 bu???i b???n c?? th??? s???p x???p" ;
                                    text += "\nChi ph?? ????ng k?? l?? 4 tri???u ?????ng";
                                    text += "\nB???n c?? mu???n ????ng k?? lu??n kh??ng ????";
                                    count=3;
                                }        

                                
                            } else if(count==3) {
                                if(msg.contains("kho?? h???c kh??c")) {
                                    text = "????y l?? danh s??ch kho?? h???c b??n m??nh ???" ;
                                    text += "\n1. HTML/CSS c?? b???n";
                                    text += "\n2. ReactJS c?? b???n";
                                    text += "\n3. JavaScript c?? b???n";
                                    text += "\n4. JavaScript n??ng cao";
                                    count=1;
                                } else if(msg.contains("c??") || msg.contains("????ng k??")) {
                                    text = "B???n vui l??ng ????? l???i th??ng tin s??? ??i???n tho???i ????? b??n m??nh c?? th??? li??n h??? v???i b???n trong th???i gian s???m nh???t ???" ;
                                    count++;
                                } else if(msg.contains("kh??ng") || msg.contains("ch??a")) {
    //                                text = "" ;
                                    count++;
                                    text += "V???y b???n c?? th??? cho b??n m??nh s??? ??i???n tho???i ????? b??n m??nh c?? th??? li??n h??? t?? v???n tr???c ti???p trao ?????i v???i b???n ???";
                                } 
                                
                            } else if(count>3) {
                                text += "C???m ??n b???n ???? li??n h??? v???i b??n m??nh ???. Ch??c b???n m???t ng??y th???t vui v???!";
                                count=0;
                            }
                            if(text.isEmpty()) text="Xin l???i, t??i kh??ng hi???u ?? b???n!";
                            txpMessageBoard.setText(txpMessageBoard.getText() + "\n" + msg  );                            
                            if(autoChat) send("ChatBot : " + text);
                        } else if(msg!=null) {                       
                            txpMessageBoard.setText(txpMessageBoard.getText() + "\n" + msg  );                           
                            
                        }            
                               
                        
                    } catch (Exception e) {
                        
                    }
                }
            }
        };
        th.start();
    }
    
    public void send(String msg) {
        txpMessageBoard.setText(txpMessageBoard.getText() + "\n" + msg);
        out.println(msg);
//        out.
        out.flush();
    }
    public void close() {
        try {
            out.close();
            reader.close();
            socket.close();
        } catch (Exception e) {
        }
    }
}
