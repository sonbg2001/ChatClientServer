
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
                            if(msg.contains("hi") || msg.contains("hello") || msg.contains("chào")) {   
                                 text = "chào cậu";
                            } else if(msg.contains("how are you")) {
                                text = "I'm Good :).Thank you.And you?";
                            } else if(msg.contains("what is your name")) {
                                text = "I'm your chatbot";
                            } else if(msg.contains("bạn tên là gì")) {
                                text = "Tôi là Chatbot tư vấn khoá học lập trình web";
                            } else if(msg.contains("bây giờ là mấy giờ")) {
                                text = "Bây giờ là " + java.time.LocalTime.now();
                            }else if(msg.contains("Hôm nay là ngày")) {
                                text = "Hôm nay là ngày " + java.time.LocalDate.now();
                            } else if(msg.contains("bạn tên là ")) {
                                text = "Tôi là Chatbot tư vấn khoá học" ;
                            }
                            if(count == 0) {
                                if(msg.contains("hi") || msg.contains("hello") || msg.contains("chào")) {   
                                    text = "chào cậu";
//                                    count++;
                                }                             

                            } 
//                            count=1;
                            if(count<=1) {
                                if(msg.contains("tư vấn")) {
                                    text = "Không biết là bạn muốn tư vấn về khoá học nào của chúng mình ạ!" ;
                                    text += "\n1. HTML/CSS cơ bản";
                                    text += "\n2. ReactJS cơ bản";
                                    text += "\n3. JavaScript cơ bản";
                                    text += "\n4. JavaScript nâng cao";
                                    count=2;
                                }                             

                            } else if(count==2) {
                                if(msg.contains("1") || msg.contains("HTML/CSS")) {
                                    text = "Khoá học HTML/CSS cơ bản thì bạn sẽ học trong 2 tháng, mỗi tuần 3 buổi bạn có thể sắp xếp" ;
                                    text += "\nChi phí đăng ký là 2 triệu 500 ngàn";
                                    text += "\nBạn có muốn đăng ký luôn không ạ?";
                                    count=3;
                                } else if(msg.contains("2") || msg.contains("ReactJS")) {
                                    text = "Khoá học ReactJS cơ bản thì bạn sẽ học trong 3 tháng, mỗi tuần 3 buổi bạn có thể sắp xếp" ;
                                    text += "\nChi phí đăng ký là 3 triệu đồng";
                                    text += "\nBạn có muốn đăng ký luôn không ạ?";
                                    count=3;
                                }  else if(msg.contains("3") || msg.contains("JavaScript cơ bản")) {
                                    text = "Khoá học JavaScript cơ bản thì bạn sẽ học trong 2 tháng, mỗi tuần 3 buổi bạn có thể sắp xếp" ;
                                    text += "\nChi phí đăng ký là 4 triệu đồng";
                                    text += "\nBạn có muốn đăng ký luôn không ạ?";
                                    count=3;
                                }    else if(msg.contains("4") || msg.contains("JavaScript nâng")) {
                                    text = "Khoá học JavaScript nâng cao thì bạn sẽ học trong 2 tháng, mỗi tuần 3 buổi bạn có thể sắp xếp" ;
                                    text += "\nChi phí đăng ký là 4 triệu đồng";
                                    text += "\nBạn có muốn đăng ký luôn không ạ?";
                                    count=3;
                                }        

                                
                            } else if(count==3) {
                                if(msg.contains("khoá học khác")) {
                                    text = "Đây là danh sách khoá học bên mình ạ" ;
                                    text += "\n1. HTML/CSS cơ bản";
                                    text += "\n2. ReactJS cơ bản";
                                    text += "\n3. JavaScript cơ bản";
                                    text += "\n4. JavaScript nâng cao";
                                    count=1;
                                } else if(msg.contains("có") || msg.contains("đăng ký")) {
                                    text = "Bạn vui lòng để lại thông tin số điện thoại để bên mình có thể liên hệ với bạn trong thời gian sớm nhất ạ" ;
                                    count++;
                                } else if(msg.contains("không") || msg.contains("chưa")) {
    //                                text = "" ;
                                    count++;
                                    text += "Vậy bạn có thể cho bên mình số điện thoại để bên mình có thể liên hệ tư vấn trực tiếp trao đổi với bạn ạ";
                                } 
                                
                            } else if(count>3) {
                                text += "Cảm ơn bạn đã liên hệ với bên mình ạ. Chúc bạn một ngày thật vui vẻ!";
                                count=0;
                            }
                            if(text.isEmpty()) text="Xin lỗi, tôi không hiểu ý bạn!";
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
