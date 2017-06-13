package APITasks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.regex.Pattern;

public class VKTasks {
    public static String getPhoto(String vkid)throws IOException{
        URL url = new URL("https://api.vk.com/method/users.get?user_id="+vkid+"&fields=photo_50&v=5.52");
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(url.openConnection().getInputStream(), "UTF-8"));
        String buffer="";
        while (true) {
            String line = reader.readLine();
            if (line == null)
                break;
            buffer+=line;
        }
        String[] params=buffer.split(",");
        for (String param:params){
            if (param.contains("\"photo_50\"")){
                buffer = param.split("\":\"")[1];
            }
        }
        return "<img src=\""+buffer+"/>";
    }

}