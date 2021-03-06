/*
 * Đây là bảng dùng để thống kê các từ xuất hiện.
 * 1 phần tử thuộc bảng này gồm 3 phần 
 * BasicWord(từ gốc)-KEY, 
 * MaskWord(từ để hiện thị khi in)
 * số lượng
 */
package Sta_word;

/**
 *
 * @author Bui
 */
public class StaticTable extends Table{
    
    public StaticTable (){
        super();
    }
     public void  Add(String Word, BaseDict Base){
        if (Base.checkAvailable(Word)) {
        String BasicWord= Base.getBasicWordFromDict(Word);
        if (Base.getTypeofWord(BasicWord)!=0)
        this.increaseCountWord(BasicWord,Word);
        }
    }
    private void increaseCountWord(String BasicWord, String MaskedWord){
        if (! this.checkAvailable(BasicWord) ) addData(BasicWord, MaskedWord, 1);
        else {
            int i = getNum(BasicWord);
            String MaskWord = getWord(BasicWord);
            i++;
            if (MaskWord.length() > MaskedWord.length()) addData(BasicWord, MaskedWord, i);
            else
                addData(BasicWord, MaskWord, i);
        }
    }
    
}
