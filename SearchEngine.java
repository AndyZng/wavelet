import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

class Handler implements URLHandler {
    ArrayList <String> list = new ArrayList<>();
    ArrayList <String> searchedList = new ArrayList<>(); 

    public String handleRequest(URI url) {
        if (url.getPath().equals("/")){
            return "GOogle";
        }
        else if (url.getPath().contains("/add")) {
            String[] parameters = url.getQuery().split("=");
            list.add(parameters[1]);
            return parameters[1] + " added"; 
        } 
        else if (url.getPath().contains("/search")){
            searchedList.clear(); 
            if(list.size() > 0){
                String[] parameters = url.getQuery().split("=");
                for (int i = 0; i < list.size(); i++){
                    if (list.get(i).contains(parameters[1])){
                        searchedList.add(list.get(i));
                    } 
                }
                return searchedList.toString(); 
            }
            else{
                return "add a string"; 
            }        }
        else{
            return "invalid input"; 
        }
        }
    }

class SearchEngine {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}