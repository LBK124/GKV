package gKVClient.Utils;

import data.PersonTO;
import data.DrinkTO;
import gKVClient.MainWindow.UserControl.FilterTO;

//Klasse für Universelle Methoden die man immer weider braucht
public class utils {
    public String intToString(int src)
    {
        String value = "";
        int euro, cent;
        //Vor und Nachkommastelle berechnen
        euro = src / 100;
        cent = Math.abs(src) % 100;
        //Vorskommastelle in String umwandeln
        value += Integer.toString(euro) + ",";
        //Nachkommastelle dem String je nach Fall zuweisen
        if(cent < 10){
            value += "0" + cent;
        }
        else if(cent == 0){
            value += "00";
        }
        else{
            value += Integer.toString(cent);
        }
        return value;
    }
    //ab hier Fehler nicht abgefangen !!!!!!
    //Gibt den Namen des Getränks aus dem Listen Element zurück
    public String getNameFromList(String src){
        StringBuffer dest = new StringBuffer();
        dest.append(src.substring(0, 19));
        return dest.toString().trim();
    }
    //Gibt die Anzahl des Getränks aus dem Listen Element zurück
    public String getAmountFromList(String src){
        StringBuffer dest = new StringBuffer();
        dest.append(src.substring(20,34));
        return dest.toString().trim();
    }
    //Gibt den Preis des Getränks aus dem Listen Elemnet zurück
    public String getPriceFromList(String src){
        StringBuffer dest = new StringBuffer();
        dest.append(src.substring(35));
        return dest.toString().trim();
    }
    public int getCellarIDFromPerson(PersonTO pers){
        return Integer.parseInt(Character.toString(pers.id.charAt(2)));
    }

    public DrinkTO getDrinkByName(DrinkTO src[],String name){
        String temp;
        for(DrinkTO drink : src){
            temp = drink.name.trim();
            if(temp.contentEquals(name)){
                return drink;
            }
        }
        return null;
    }

    public PersonTO[] SortByRoom(PersonTO[] users){
        PersonTO temp;
        for(int i = users.length - 1; i > 0; --i){
            for(int j = 0; j <= i - 1; ++j){
                if (Integer.parseInt(users[j].id.substring(2,5)) >= Integer.parseInt(users[i].id.substring(2,5))) {
                    temp = users[i];
                    users[i] = users[j];
                    users[j] = temp;
                }
            }
        }
        return users;
    }
    public PersonTO[] filterBy(PersonTO[] user, FilterTO filt){
        PersonTO[] temp = new PersonTO[user.length];
        int written = user.length;
        int writing = user.length;
        if(filt.key){
            writing = 0;
            for(int i = 0; i < written; ++i){
                if(user[i].key){
                    temp[writing++] = user[i];
                }
            }
            written = writing;
            user = temp;
        }
        if(filt.admin){
            writing = 0;
            for(int i = 0; i < written; ++i){
                if(user[i].admin){
                    temp[writing++] = user[i];
                }
            }
            written = writing;
            user = temp;
        }
        if(filt.state){
            writing = 0;
            for(int i = 0; i < written; ++i){
                if(!user[i].state){
                    temp[writing++] = user[i];
                }
            }
            written = writing;
            user = temp;
        }
        if(written == 0){
            return null;
        }
        PersonTO[] result = new PersonTO[written];
        System.arraycopy(user,0,result,0,written);
        return result;
    }

    public PersonTO[] InvertArray(PersonTO[] user){
        PersonTO[] temp = new PersonTO[user.length];
        for(int i = 0; i < user.length; ++i){
            temp[user.length-i-1] = user[i];
        }
        return temp;
    }
}
