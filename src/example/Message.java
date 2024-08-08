package example;

import java.util.ArrayList;

public class Message {
    public static String message(String msg, ArrayList<AlphabetSymbol> alphabet) {
        StringBuilder msgBuilder = new StringBuilder();
        String newMsg = "err";
        msg = msg.toLowerCase();
        if (Character.isDigit(msg.charAt(0))) {
            String[] msgArray = msg.split(" ");
            if (msgArray.length % 3 == 0) {
                for (int i = 0; i < msgArray.length; i += 3) {
                    try {
                        MyCipher curCipher = new MyCipher(Long.parseLong(msgArray[i]), Long.parseLong(msgArray[i + 1]), Long.parseLong(msgArray[i + 2]));
                        for (AlphabetSymbol as : alphabet) {
                            if (as.cipher.E == curCipher.E && as.cipher.b1 == curCipher.b1 && as.cipher.b2 == curCipher.b2) {
                                msgBuilder.append(as.symbol);
                                break;
                            }
                        }
                    } catch (NumberFormatException e) {
                        System.out.println(e.getMessage());
                    }
                }
                newMsg = msgBuilder.toString();
            } else {
                newMsg = "Not enough data!";
            }
        } else {
            String[] msgArray = msg.split("");
            for (String msgChar : msgArray) {
                for (AlphabetSymbol as : alphabet) {
                    if (as.symbol.equals(msgChar)) {
                        msgBuilder.append(as.cipher.E).append(" ").append(as.cipher.b1).append(" ").append(as.cipher.b2).append(" ");
                        break;
                    }
                }
            }
            if (msgBuilder.length() > 1)
                newMsg = msgBuilder.substring(0, msgBuilder.length() - 1);
        }

        return newMsg;
    }
}
