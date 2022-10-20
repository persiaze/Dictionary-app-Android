package Word;

public class Word {

    public int id;
    public String word_mn;
    public String word_en;
    public static String visibility_mode;

    public Word(int id, String word_mn,String word_en){
        this.id = id;
        this.word_mn = word_mn;
        this.word_en = word_en;
    }

    public int getId() {
        return id;
    }
    public void setWord_mn(String word_mn) {
        this.word_mn = word_mn;
    }
    public String getWord_mn() {
        return word_mn;
    }
    public void setWord_en(String word_en) {
        this.word_en = word_en;
    }
    public String getWord_en() {
        return word_en;
    }

    @Override
    public String toString() {
        switch(visibility_mode){
            case "MonEng":
                return word_mn + " - " + word_en;
            case "Mon":
                return word_mn;
            case "Eng":
                return word_en;
            default:
                return word_en + " - " + word_mn;
        }
    }
}
